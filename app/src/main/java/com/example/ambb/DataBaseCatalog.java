package com.example.ambb;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseCatalog extends SQLiteOpenHelper {
  final String LOG_TAG = "myLogs";

  public static final String DATABASE_NAME = "DataCatalog.db";
  public static final int DATABASE_VERSION = 2;
  public static final String TABLE_NAME = "Catalog";

  public static final String KEY_ID = "ID";
  public static final String KEY_NAME = "NAME";
  public static final String KEY_COLOR = "COLOR";
  public static final String KEY_DESCRIPTION = "DESCRIPTION";
  public static final String KEY_PRICE = "PRICE";
  public static final String KEY_PICTURE = "PICTURE_ID";



  public DataBaseCatalog(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL("CREATE TABLE " + TABLE_NAME + " ( ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, COLOR TEXT, DESCRIPTION TEXT, PRICE TEXT, PICTURE TEXT  )");
    insertCatalog(db, "Huawei P20", "black", "jbdbchsb, sdvsdvs, vdsvsdv, dsvdvsd, sdvsdv, m dmndjvnjdv",
      "1100", R.drawable.p20);
    insertCatalog(db,"Huawei P10", "black", "bchsbhbs,cnasjncaj,cncajs,cnasjcna,ncasjcn, vsjvnsjdvnjs,vndsjnvjsdv,kvnsdjvnsjd",
      "900", R.drawable.p10);
    insertCatalog(db, "Apple Iphone X", "black", "bchsbhbs,cnasjncaj,cncajs,cnasjcna,ncasjcn, vsjvnsjdvnjs,vndsjnvjsdv,kvnsdjvnsjd",
      "1900", R.drawable.iphone10);
    insertCatalog(db, "Xiaomi Redmi 6A", "black", "bchsbhbs,cnasjncaj,cncajs,cnasjcna,ncasjcn, vsjvnsjdvnjs,vndsjnvjsdv,kvnsdjvnsjd",
      "200", R.drawable.xiaomi_redmi_6a);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    onCreate(db);
  }

  private static void insertCatalog (SQLiteDatabase db, String name, String color, String description, String price, int pictureId){
    ContentValues contentValues = new ContentValues();
    contentValues.put("NAME", name);
    contentValues.put("COLOR", color);
    contentValues.put("DESCRIPTION", description);
    contentValues.put("PRICE", price);
    contentValues.put("PICTURE", pictureId);
    long rowId = db.insert("catalog", null, contentValues);
    Log.d("ID", "row inserted, ID" + rowId);
  }
}
