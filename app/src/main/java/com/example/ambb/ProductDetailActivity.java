package com.example.ambb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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
  DataBaseCatalog dataBaseCatalog;
  ImageButton imageButton;
  private int count;
  private String mobileName;
  private String mobileColor;
  private String mobileDescription;
  private String mobilePrice;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_product_detail);

    dataBaseCatalog = new DataBaseCatalog(this);

    int mobileId = (Integer) getIntent().getExtras().get(EXTRA_MOBILE_ID);
    final SQLiteDatabase db = dataBaseCatalog.getWritableDatabase();
    Cursor cursor = db.query(DataBaseCatalog.TABLE_NAME, null, "ID = ?", new String[]{Integer.toString(mobileId+1)}, null, null, null);

    if (cursor.moveToFirst()) {
      mobileName = cursor.getString(1);
      mobileColor = cursor.getString(2);
      mobileDescription = cursor.getString(3);
      mobilePrice = cursor.getString(4);
      int mobilePicture = cursor.getInt(6);

      TextView name = (TextView) findViewById(R.id.name_detail);
      name.setText(mobileName);
      TextView color = (TextView) findViewById(R.id.color_detail);
      color.setText(mobileColor);
      ImageView picture = (ImageView) findViewById(R.id.image_detail);
      picture.setImageDrawable(ContextCompat.getDrawable(this, mobilePicture));
      picture.setContentDescription(mobileName);
      TextView description = (TextView) findViewById(R.id.description_detail);
      description.setText(mobileDescription);
      //TextView price = (TextView) findViewById(R.id.price_detail);
      //price.setText(mobilePrice);

      Button buttonDetail = (Button) findViewById(R.id.buttonDetail);
      buttonDetail.setText(mobilePrice);
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
  }
}