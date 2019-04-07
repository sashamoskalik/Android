package com.example.ambb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class ProductFragment extends Fragment {

  DataBaseCatalog dataBaseCatalog;

  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

    dataBaseCatalog = new DataBaseCatalog(getActivity());

    SQLiteDatabase db = dataBaseCatalog.getWritableDatabase();
    Cursor cursor = db.query(DataBaseCatalog.TABLE_NAME, null, null, null, null, null, null);

    Log.d("Hello", "hello");
    RecyclerView productRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_product, container, false);

    /*if (cursor.moveToFirst()) {
      int nameIndex = cursor.getColumnIndex(DataBaseCatalog.KEY_NAME);
      int colorIndex = cursor.getColumnIndex(DataBaseCatalog.KEY_COLOR);
      int descriptionIndex = cursor.getColumnIndex(DataBaseCatalog.KEY_DESCRIPTION);
      int priceIndex = cursor.getColumnIndex(DataBaseCatalog.KEY_PRICE);
      int pictureIndex = cursor.getColumnIndex(DataBaseCatalog.KEY_PICTURE);
      String[] productName = new String[cursor.getCount()];
      do {
        int i = 0;
        productName[i] = cursor.getString(nameIndex);
        i++;
        Log.d("i", String.valueOf(i));
      }
      while (cursor.moveToNext());
      for (int i = 0; i < productName.length; i++) {
        productName[i] = Product.product[i].getName();*/

    String[] productName = new String[Product.product.length];
    for (int i = 0; i < productName.length; i++) {
      productName[i] = Product.product[i].getName();
    }
    String[] productColor = new String[Product.product.length];
    for (int i = 0; i < productColor.length; i++) {
      productColor[i] = Product.product[i].getColor();
    }
    String[] productDescription = new String[Product.product.length];
    for (int i = 0; i < productDescription.length; i++) {
      productDescription[i] = Product.product[i].getDescription();
    }
    String[] productPrice = new String[Product.product.length];
    for (int i = 0; i < productPrice.length; i++) {
      productPrice[i] = Product.product[i].getPrice();
    }
    int[] productPicture = new int[Product.product.length];
    for (int i = 0; i < productPicture.length; i++) {
      productPicture[i] = Product.product[i].getPicture();
    }

    Adapter adapter = new Adapter(productName, productColor, productDescription, productPrice, productPicture);
    productRecycler.setAdapter(adapter);
    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
    productRecycler.setLayoutManager(layoutManager);
    return productRecycler;
    }
}
