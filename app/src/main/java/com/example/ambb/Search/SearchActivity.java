package com.example.ambb.Search;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.example.ambb.MainActivity;
import com.example.ambb.ProductDetailActivity;
import com.example.ambb.R;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {

  public static final String BASE_URL = "https://api.myjson.com";
  private RecyclerView mRecyclerView;
  private ArrayList<AndroidVersion> mArrayList;
  private DataAdapter mAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search);
    //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    //setSupportActionBar(toolbar);

    initViews();
    loadJSON();
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
        mAdapter = new DataAdapter(SearchActivity.this ,mArrayList);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new DataAdapter.OnItemClickListener() {
          @Override
          public void onItemClick(int i, String nameProduct) {
            Intent intent = new Intent(SearchActivity.this, ProductDetailActivity.class);
            intent.putExtra(ProductDetailActivity.EXTRA_MOBILE_ID, i);
            intent.putExtra(ProductDetailActivity.EXTRA_MOBILE_NAME, nameProduct);
            startActivity(intent);

          }
        });
      }

      @Override
      public void onFailure(Call<JSONResponse> call, Throwable t) {
        Log.d("Error",t.getMessage());
      }
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {

    getMenuInflater().inflate(R.menu.search, menu);

    MenuItem search = menu.findItem(R.id.search);
    SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
    search(searchView);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    return super.onOptionsItemSelected(item);
  }

  private void search(SearchView searchView) {

    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String query) {

        return false;
      }

      @Override
      public boolean onQueryTextChange(String newText) {

        mAdapter.getFilter().filter(newText);
        return true;
      }
    });
  }
}
