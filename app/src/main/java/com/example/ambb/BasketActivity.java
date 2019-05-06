package com.example.ambb;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ambb.DataBase.DataBaseCatalog;

public class BasketActivity extends AppCompatActivity {

  ListView basketList;
  TextView sum;
  DataBaseCatalog dataBaseCatalog;
  SimpleCursorAdapter simpleCursor;
  SQLiteDatabase db;
  Cursor cursor;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_basket);

    basketList = (ListView) findViewById(R.id.basketList);
    dataBaseCatalog = new DataBaseCatalog(getApplicationContext());

    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
          .setAction("Action", null).show();
      }
    });
  }

  @Override
  public void onResume(){
    super.onResume();
    int summa = 0;

    sum = (TextView) findViewById(R.id.sum);
    db = dataBaseCatalog.getReadableDatabase();

    Log.d("FAVORITY","RESUME");
    cursor = db.rawQuery("select * from Basket", null);
    String[] from = new String[]{"NAME", "COLOR", "PRICE"};
    int[] to = new int[]{R.id.name, R.id.color, R.id.price};
    simpleCursor = new SimpleCursorAdapter(this, R.layout.favorite_list, cursor, from, to);
    basketList.setAdapter(simpleCursor);
    if (cursor.moveToFirst()){
      int PriceIndex = cursor.getColumnIndex("PRICE");

      do {
        summa += cursor.getInt(PriceIndex);
      }
      while (cursor.moveToNext());
      Log.d("PRICE", String.valueOf(summa));
    }
    sum.setText("Итого: " + String.valueOf(summa) + " руб.");
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
      db.delete("Basket", null, null);
      Intent intent = new Intent(this, MainActivity.class);
      startActivity(intent);
      Toast toast = Toast.makeText(getApplicationContext(), "Вы удалили все из корзины ", Toast.LENGTH_LONG);
      toast.show();
    }

    return super.onOptionsItemSelected(item);
  }

}
