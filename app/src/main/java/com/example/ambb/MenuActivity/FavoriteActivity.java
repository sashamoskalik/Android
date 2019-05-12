package com.example.ambb.MenuActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.ambb.DataBase.DataBaseCatalog;
import com.example.ambb.MainActivity;
import com.example.ambb.ProductDetailActivity;
import com.example.ambb.R;

public class FavoriteActivity extends AppCompatActivity {

  ListView favoriteList;
  DataBaseCatalog dataBaseCatalog;
  SimpleCursorAdapter simpleCursor;
  SQLiteDatabase db;
  Cursor cursor;
  String favoriteName;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_favorite);

    favoriteList = (ListView) findViewById(R.id.favoriteList);

    dataBaseCatalog = new DataBaseCatalog(getApplicationContext());
    db = dataBaseCatalog.getReadableDatabase();

    favoriteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Cursor cursorClick = db.query(DataBaseCatalog.TABLE_FAVORITE, null, "_id = ?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursorClick.moveToFirst()){
          favoriteName = cursorClick.getString(1);
        }
        Intent intent = new Intent(FavoriteActivity.this, ProductDetailActivity.class);
        intent.putExtra(ProductDetailActivity.EXTRA_MOBILE_NAME, favoriteName);
        startActivity(intent);
      }
    });
  }

  @Override
  public void onResume(){
    super.onResume();


    Log.d("FAVORITY","RESUME");
    cursor = db.rawQuery("select * from Favorite", null);
    String[] from = new String[]{"NAME", "COLOR", "DESCRIPTION", "PRICE"};
    int[] to = new int[]{R.id.name, R.id.color, R.id.description, R.id.price};
    simpleCursor = new SimpleCursorAdapter(this, R.layout.favorite_list, cursor, from, to);
    favoriteList.setAdapter(simpleCursor);

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {

    getMenuInflater().inflate(R.menu.delete, menu);

    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.delete) {
      db.delete("Favorite", null, null);
      Intent intent = new Intent(this, MainActivity.class);
      startActivity(intent);
      Toast toast = Toast.makeText(getApplicationContext(), "Вы удалили все закладки", Toast.LENGTH_LONG);
      toast.show();
    }

    return super.onOptionsItemSelected(item);
  }




}
