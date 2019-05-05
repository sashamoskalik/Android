package com.example.ambb;

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

import com.example.ambb.DataBase.DataBaseCatalog;

public class ProductDetailActivity extends AppCompatActivity {

  public static final String EXTRA_MOBILE_ID = "mobileId";
  DataBaseCatalog dataBaseCatalog;
  ImageButton imageButton;
  private int count = 2;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_product_detail);

    dataBaseCatalog = new DataBaseCatalog(this);

    int mobileId = (Integer) getIntent().getExtras().get(EXTRA_MOBILE_ID);
    SQLiteDatabase db = dataBaseCatalog.getWritableDatabase();
    Cursor cursor = db.query(DataBaseCatalog.TABLE_NAME, null, "ID = ?", new String[]{Integer.toString(mobileId+1)}, null, null, null);

    if (cursor.moveToFirst()) {
      String mobileName = cursor.getString(1);
      String mobileColor = cursor.getString(2);
      String mobileDescription = cursor.getString(3);
      String mobilePrice = cursor.getString(4);
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

    View.OnClickListener star = new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (count%2 == 0) {
          imageButton.setImageResource(R.drawable.ic_star_yellow_24dp);
        }
        else {
          imageButton.setImageResource(R.drawable.ic_star_24dp);
        }
        count++;
      }
    };
    imageButton.setOnClickListener(star);
  }
}
