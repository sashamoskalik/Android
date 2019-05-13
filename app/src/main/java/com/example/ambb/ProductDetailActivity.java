package com.example.ambb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.print.PrinterId;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ambb.DataBase.DataBaseCatalog;

public class ProductDetailActivity extends AppCompatActivity {

  public static final String EXTRA_MOBILE_ID = "mobileId";
  public static final String EXTRA_MOBILE_NAME = "mobileName";
  DataBaseCatalog dataBaseCatalog;
  ImageButton imageButton;
  Button buttonBasket;
  private int count;
  private String mobileName;
  private String mobileColor;
  private String mobileDescription;
  private String mobilePlatform;
  private String mobileProcessor;
  private int mobilePrice;
  private int mobileRate;
  private int mobileKernel;
  private int mobileRaz;
  private int mobileTech;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_product_detail);

    dataBaseCatalog = new DataBaseCatalog(this);
    buttonBasket = (Button) findViewById(R.id.buttonBasket);


    String serverNameProduct = (String) getIntent().getExtras().get(EXTRA_MOBILE_NAME);
    Log.d("SERVERNAME", serverNameProduct);

    final SQLiteDatabase db = dataBaseCatalog.getWritableDatabase();
    Cursor cursor = db.query(DataBaseCatalog.TABLE_NAME, null, "NAME = ?", new String[]{serverNameProduct}, null, null, null);

    if (cursor.moveToFirst()) {
      mobileName = cursor.getString(1);
      mobileColor = cursor.getString(2);
      mobileDescription = cursor.getString(3);
      mobilePrice = cursor.getInt(4);
      int mobilePicture = cursor.getInt(6);
      mobilePlatform = cursor.getString(7);
      mobileProcessor = cursor.getString(8);
      mobileRate = cursor.getInt(9);
      mobileKernel = cursor.getInt(10);
      mobileRaz = cursor.getInt(11);
      mobileTech = cursor.getInt(12);

      TextView name = (TextView) findViewById(R.id.name_detail);
      name.setText(mobileName);
      TextView color = (TextView) findViewById(R.id.color_detail);
      color.setText(mobileColor);
      ImageView picture = (ImageView) findViewById(R.id.image_detail);
      picture.setImageDrawable(ContextCompat.getDrawable(this, mobilePicture));
      picture.setContentDescription(mobileName);
      TextView description = (TextView) findViewById(R.id.description_detail);
      description.setText(mobileDescription);
      TextView platform = (TextView) findViewById(R.id.platform);
      platform.setText(mobilePlatform);
      TextView processor = (TextView) findViewById(R.id.processor);
      processor.setText(mobileProcessor);
      TextView rate = (TextView) findViewById(R.id.rate);
      rate.setText(String.valueOf(mobileRate));
      TextView kernel = (TextView) findViewById(R.id.kernel);
      kernel.setText(String.valueOf(mobileKernel));
      TextView raz = (TextView) findViewById(R.id.raz);
      raz.setText(String.valueOf(mobileRaz));
      TextView tech = (TextView) findViewById(R.id.techprocess);
      tech.setText(String.valueOf(mobileTech));
      //TextView price = (TextView) findViewById(R.id.price_detail);
      //price.setText(mobilePrice);

      buttonBasket = (Button) findViewById(R.id.buttonBasket);
      buttonBasket.setText(String.valueOf(mobilePrice) + " руб.");
    }

    imageButton = (ImageButton) findViewById(R.id.favorite);

    Cursor cursorFavorite = db.query("Favorite", null, null, null, null, null, null);
    int flag = 0;
    if (cursorFavorite.moveToFirst()){
      int nameIndex = cursorFavorite.getColumnIndex("NAME");
      do {
        if (mobileName.equals(cursorFavorite.getString(nameIndex))){
          imageButton.setImageResource(R.drawable.ic_star_yellow_24dp);
          Log.d("STAR", "OTP");
          Log.d("NAME", mobileName);
          flag++;
          count = 1;
          break;
        }
        else {
          count = 2;
          flag++;
          imageButton.setImageResource(R.drawable.ic_star_24dp);
          Log.d("name", mobileName);
          Log.d("COUNT", "2");
        }
      }
      while (cursorFavorite.moveToNext());
    }
    if (flag == 0){
      imageButton.setImageResource(R.drawable.ic_star_24dp);
    }

    View.OnClickListener star = new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        if (count%2 == 0) {
          Log.d("Onclick", "NO STAR");
          ContentValues contentValues = new ContentValues();
          imageButton.setImageResource(R.drawable.ic_star_yellow_24dp);
          contentValues.put("NAME", mobileName);
          contentValues.put("COLOR", mobileColor);
          contentValues.put("DESCRIPTION", mobileDescription);
          contentValues.put("PRICE", mobilePrice);
          long rowId = db.insert("Favorite", null, contentValues);
          Log.d("Row", "RowId" + rowId);
          Toast toast = Toast.makeText(getApplicationContext(), "Вы добавили " + mobileName + " в закладки", Toast.LENGTH_LONG);
          toast.show();

        }
        else {
          Log.d("Onclick", "STAR");
          db.delete("Favorite", "NAME = ?", new String[]{mobileName});
          imageButton.setImageResource(R.drawable.ic_star_24dp);
          Toast toast = Toast.makeText(getApplicationContext(), "Вы удалили " + mobileName + " из закладок", Toast.LENGTH_LONG);
          toast.show();
        }
        count++;
      }
    };
    imageButton.setOnClickListener(star);

    View.OnClickListener basket = new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", mobileName);
        contentValues.put("COLOR", mobileColor);
        contentValues.put("DESCRIPTION", mobileDescription);
        contentValues.put("PRICE", mobilePrice);
        long rowID = db.insert("Basket", null, contentValues);
        Log.d("Row", "RowId" + rowID);
        Toast toast = Toast.makeText(getApplicationContext(), "Вы добавили " + mobileName + " в корзину", Toast.LENGTH_LONG);
        toast.show();
      }
    };
    buttonBasket.setOnClickListener(basket);

  }
}
