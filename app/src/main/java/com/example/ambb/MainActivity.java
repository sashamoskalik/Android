package com.example.ambb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.ambb.MenuActivity.CommunicationActivity;
import com.example.ambb.MenuActivity.HelpActivity;
import com.example.ambb.MenuActivity.PersonalSaleActivity;
import com.example.ambb.Search.SearchActivity;
import com.example.ambb.PersonalAccount.EnterActivity;
import com.example.ambb.SearchView.AndroidVersion;
import com.example.ambb.SearchView.DataAdapter;
import com.example.ambb.SearchView.JSONResponse;
import com.example.ambb.SearchView.RequestInterface;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
  implements NavigationView.OnNavigationItemSelectedListener {

  public static final String BASE_URL = "https://api.learn2crack.com";
  private RecyclerView mRecyclerView;
  private ArrayList<AndroidVersion> mArrayList;
  private DataAdapter mAdapter;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
      this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();

      NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);

    initViews();
    loadJSON();

  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);

    ///////////
    MenuItem search = menu.findItem(R.id.search);
    SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
    search(searchView);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    Log.d("he","hello");
    int id = item.getItemId();

    if (id == R.id.nav_personalAccount) {
      Intent intent = new Intent(this, EnterActivity.class);
      startActivity(intent);
    } else if (id == R.id.nav_order) {

    } else if (id == R.id.nav_basket) {

    } else if (id == R.id.nav_laying) {

    } else if (id == R.id.nav_search) {
      Intent intent = new Intent(this, SearchActivity.class);
      startActivity(intent);
    } else if (id == R.id.nav_discount) {
      Intent intent = new Intent(this, PersonalSaleActivity.class);
      startActivity(intent);
    } else if (id == R.id.nav_help){
      Intent intent = new Intent(this, HelpActivity.class);
      startActivity(intent);

    } else if (id == R.id.nav_developer){
      Intent intent = new Intent(this, CommunicationActivity.class);
      startActivity(intent);
    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }


  private void initViews(){
    mRecyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
    mRecyclerView.setHasFixedSize(true);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
    mRecyclerView.setLayoutManager(layoutManager);
  }
  private void loadJSON(){
    Retrofit retrofit = new Retrofit.Builder()
      .baseUrl(BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .build();
    RequestInterface request = retrofit.create(RequestInterface.class);
    Call<JSONResponse> call = request.getJSON();
    call.enqueue(new Callback<JSONResponse>() {
      @Override
      public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {

        JSONResponse jsonResponse = response.body();
        mArrayList = new ArrayList<>(Arrays.asList(jsonResponse.getAndroid()));
        mAdapter = new DataAdapter(mArrayList);
        mRecyclerView.setAdapter(mAdapter);
      }

      @Override
      public void onFailure(Call<JSONResponse> call, Throwable t) {
        Log.d("Error",t.getMessage());
      }
    });
  }

  private void search(SearchView searchView) {

    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String query) {

        return false;
      }

      @Override
      public boolean onQueryTextChange(String newText) {

        if (mAdapter != null) mAdapter.getFilter().filter(newText);
        return true;
      }
    });
  }
}
