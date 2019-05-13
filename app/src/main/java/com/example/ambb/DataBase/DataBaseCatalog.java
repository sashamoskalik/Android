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
  public static final int DATABASE_VERSION = 26;
  public static final String TABLE_NAME = "Catalog";
  public static final String TABLE_FAVORITE = "Favorite";
  public static final String TABLE_BASKET = "Basket";

  public static final String KEY_ID = "ID";
  public static final String KEY_NAME = "NAME";
  public static final String KEY_COLOR = "COLOR";
  public static final String KEY_DESCRIPTION = "DESCRIPTION";
  public static final String KEY_PRICE = "PRICE";
  public static final String KEY_PICTURE = "PICTURE_ID";
  public static final String KEY_BIGPICTURE = "BIGPICTURE_ID";
  public static final String KEY_PLATFORM = "PLATFORM";
  public static final String KEY_PROCESSOR = "PROCESSOR";
  public static final String KEY_RATE = "RATE";
  public static final String KEY_NUMBERKERNEL = "NUMBERKERNEL";
  public static final String KEY_RAZPROCESSOR = "RAZPROCESSOR";
  public static final String KEY_TECHPROCESS = "TECHPROCESS";



  public DataBaseCatalog(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL("CREATE TABLE " + TABLE_NAME + " ( ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, COLOR TEXT, DESCRIPTION TEXT, PRICE INTEGER, PICTURE INTEGER, BIGPICTURE INTEGER, PLATFORM TEXT, PROCESSOR TEXT, RATE INTEGER, NUMBERKERNEL INTEGER, RAZPROCESSOR INTEGER, TECHPROCESS INTEGER )");
    insertCatalog(db, "Huawei P20", "black", "Android, экран 5,8 IPS, (1080x2244), HiSilicon Kirin 970, ОЗУ 4 ГБ, камера 12 Мп",
      1100 , R.drawable.p20, R.drawable.p20big, "Huawei HiSilicon", "HiSilicon Kirin 970", 2360, 8, 64, 10);
    insertCatalog(db,"Huawei P10", "black", "Android, экран 5,1 IPS, (1080x1920), HiSilicon Kirin 960, ОЗУ 4 ГБ, камера 12 Мп",
       900 , R.drawable.p10, R.drawable.p10big, "Huawei HiSilicon", "HiSilicon Kirin 658", 2100, 8, 64, 16 );
    insertCatalog(db, "Apple Iphone X", "black", "Apple iOS, экран 5,8 AMOLED, (1125x2436), Apple A11 Bionic, ОЗУ 3 ГБ, камера 12 Мп",
      1900 , R.drawable.iphone10, R.drawable.iphonexbig, "Apple A", "Apple A11 Bionic", 2390, 6, 64, 10);
    insertCatalog(db, "Xiaomi Redmi 6A", "black", "Android, экран 5,45 IPS, (720x1440), MediaTek Helio A22, ОЗУ 2 ГБ, камера 13 Мп",
      200 , R.drawable.xiaomi_redmi_6a, R.drawable.redmi6abig, "Mediatek", "MediaTek Helio A22", 2000, 4, 64, 12);

    db.execSQL("CREATE TABLE " + TABLE_FAVORITE + " ( _id INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, COLOR TEXT, DESCRIPTION TEXT, PRICE INTEGER )");

    db.execSQL("CREATE TABLE " + TABLE_BASKET + " ( _id INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, COLOR TEXT, DESCRIPTION TEXT, PRICE INTEGER )");
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITE);
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_BASKET);
    onCreate(db);
  }

  private static void insertCatalog (SQLiteDatabase db, String name, String color, String description, int price, int pictureId, int bigpictureId, String platform, String processor, int rate, int numberkernel, int raz, int tech){
    ContentValues contentValues = new ContentValues();
    contentValues.put("NAME", name);
    contentValues.put("COLOR", color);
    contentValues.put("DESCRIPTION", description);
    contentValues.put("PRICE", price);
    contentValues.put("PICTURE", pictureId);
    contentValues.put("BIGPICTURE", bigpictureId);
    contentValues.put("PLATFORM", platform);
    contentValues.put("PROCESSOR", processor);
    contentValues.put("RATE", rate);
    contentValues.put("NUMBERKERNEL", numberkernel);
    contentValues.put("RAZPROCESSOR", raz);
    contentValues.put("TECHPROCESS", tech);
    long rowId = db.insert("catalog", null, contentValues);
    Log.d("ID", "row inserted, ID" + rowId);
  }
}
