package com.example.ambb;

  import android.content.Intent;
  import android.os.Bundle;
  import android.support.design.widget.FloatingActionButton;
  import android.util.Log;
  import android.view.View;
  import android.support.design.widget.NavigationView;
  import android.support.v4.view.GravityCompat;
  import android.support.v4.widget.DrawerLayout;
  import android.support.v7.app.ActionBarDrawerToggle;
  import android.support.v7.app.AppCompatActivity;
  import android.support.v7.widget.Toolbar;
  import android.view.Menu;
  import android.view.MenuItem;

  import com.example.ambb.DataBase.DBHelper;
  import com.example.ambb.MenuActivity.CommunicationActivity;
  import com.example.ambb.MenuActivity.FavoriteActivity;
  import com.example.ambb.MenuActivity.HelpActivity;
  import com.example.ambb.MenuActivity.PersonalSaleActivity;
  import com.example.ambb.PersonalAccount.EnterActivity;
  import com.example.ambb.Search.SearchActivity;

public class MainActivity extends AppCompatActivity
  implements NavigationView.OnNavigationItemSelectedListener {

  DBHelper dbHelper;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    dbHelper = new DBHelper(this);


    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
      this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);

    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, FABActivity.class);
        startActivity(intent);
      }
    });

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
      Intent intent = new Intent(this, BasketActivity.class);
      startActivity(intent);
    } else if (id == R.id.nav_laying) {
      Intent intent = new Intent(this, FavoriteActivity.class);
      startActivity(intent);
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

}

