package com.example.ambb.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.ambb.R;

public class DataBaseCatalog extends SQLiteOpenHelper {
  final String LOG_TAG = "myLogs";

  public static final String DATABASE_NAME = "DataCatalog.db";
  public static final int DATABASE_VERSION = 20;
  public static final String TABLE_NAME = "Catalog";
  public static final String TABLE_FAVORITE = "Favorite";

  public static final String KEY_ID = "ID";
  public static final String KEY_NAME = "NAME";
  public static final String KEY_COLOR = "COLOR";
  public static final String KEY_DESCRIPTION = "DESCRIPTION";
  public static final String KEY_PRICE = "PRICE";
  public static final String KEY_PICTURE = "PICTURE_ID";
  public static final String KEY_BIGPICTURE = "BIGPICTURE_ID";



  public DataBaseCatalog(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL("CREATE TABLE " + TABLE_NAME + " ( ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, COLOR TEXT, DESCRIPTION TEXT, PRICE Text, PICTURE INTEGER, BIGPICTURE INTEGER  )");
    insertCatalog(db, "Huawei P20", "black", "Android, экран 5,8 IPS, (1080x2244), HiSilicon Kirin 970, ОЗУ 4 ГБ, камера 12 Мп",
      "1100,00" + " руб", R.drawable.p20, R.drawable.p20big);
    insertCatalog(db,"Huawei P10", "black", "Android, экран 5,1 IPS, (1080x1920), HiSilicon Kirin 960, ОЗУ 4 ГБ, камера 12 Мп",
       "900,00" + " руб", R.drawable.p10, R.drawable.p10big);
    insertCatalog(db, "Apple Iphone X", "black", "Apple iOS, экран 5,8 AMOLED, (1125x2436), Apple A11 Bionic, ОЗУ 3 ГБ, камера 12 Мп",
      "1900,00" + " руб", R.drawable.iphone10, R.drawable.iphonexbig);
    insertCatalog(db, "Xiaomi Redmi 6A", "black", "Android, экран 5,45 IPS, (720x1440), MediaTek Helio A22, ОЗУ 2 ГБ, камера 13 Мп",
      "200,00" + " руб", R.drawable.xiaomi_redmi_6a, R.drawable.redmi6abig);

    db.execSQL("CREATE TABLE " + TABLE_FAVORITE + " ( _id INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, COLOR TEXT, DESCRIPTION TEXT, PRICE Text )");
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITE);
    onCreate(db);
  }

  private static void insertCatalog (SQLiteDatabase db, String name, String color, String description, String price, int pictureId, int bigpictureId){
    ContentValues contentValues = new ContentValues();
    contentValues.put("NAME", name);
    contentValues.put("COLOR", color);
    contentValues.put("DESCRIPTION", description);
    contentValues.put("PRICE", price);
    contentValues.put("PICTURE", pictureId);
    contentValues.put("BIGPICTURE", bigpictureId);
    long rowId = db.insert("catalog", null, contentValues);
    Log.d("ID", "row inserted, ID" + rowId);
  }
}
