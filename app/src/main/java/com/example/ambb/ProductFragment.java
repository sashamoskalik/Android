package com.example.ambb;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.ambb.DataBase.DataBaseCatalog;


public class ProductFragment extends Fragment {

  DataBaseCatalog dataBaseCatalog;

  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

    dataBaseCatalog = new DataBaseCatalog(getActivity());

    SQLiteDatabase db = dataBaseCatalog.getWritableDatabase();
    Cursor cursor = db.query(DataBaseCatalog.TABLE_NAME, null, null, null, null, null, null);

    Log.d("Hello", "hello");
    RecyclerView productRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_product, container, false);

    if (cursor.moveToFirst()) {
      int nameIndex = cursor.getColumnIndex(DataBaseCatalog.KEY_NAME);
      int colorIndex = cursor.getColumnIndex(DataBaseCatalog.KEY_COLOR);
      int descriptionIndex = cursor.getColumnIndex(DataBaseCatalog.KEY_DESCRIPTION);
      int priceIndex = cursor.getColumnIndex(DataBaseCatalog.KEY_PRICE);
      int pictureIndex = cursor.getColumnIndex(DataBaseCatalog.KEY_PICTURE);

      String[] productName = new String[4];

      int i =0;
      do {
        Log.d("product","2");
        productName[i] = cursor.getString(nameIndex);
        i++;
        Log.d("Pro","ee" + productName);
      }
      while (cursor.moveToNext());


      String[] productColor = new String[4];
      cursor.moveToFirst();
      i = 0;
      do {
        productColor[i] = cursor.getString(colorIndex);
        i++;
      }
      while (cursor.moveToNext());

      String[] productDescription = new String[4];
      cursor.moveToFirst();
      i = 0;
      do {
        productDescription[i] = cursor.getString(descriptionIndex);
        i++;
      }
      while (cursor.moveToNext());


      String[] productPrice = new String[4];
      cursor.moveToFirst();
      i = 0;
      do {
        productPrice[i] = cursor.getString(priceIndex);
        i++;
      }
      while (cursor.moveToNext());


      int[] productPicture = new int[4];
      cursor.moveToFirst();
      i = 0;
      do {
        productPicture[i] = cursor.getInt(5);
        i++;
      }
      while (cursor.moveToNext());

      Adapter adapter = new Adapter(productName, productColor, productDescription, productPrice, productPicture);
      productRecycler.setAdapter(adapter);
      LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
      productRecycler.setLayoutManager(layoutManager);
    }
    return productRecycler;
  }
}
