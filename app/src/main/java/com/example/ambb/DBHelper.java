package com.example.ambb;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

  public static final int DATABASE_VERSION = 1;
  public static final String DATABASE_NAME = "data.db";
  public static final String TABLE_CONTACTS = "ContactsUsers";

  public static final String KEY_ID = "Id";
  public static final String KEY_EMAIL = "EMAIL";
  public static final String KEY_NICK = "NICK";
  public static final String KEY_PASSWORD = "PASSWORD";

  public DBHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL("CREATE TABLE " + TABLE_CONTACTS + " ( ID INTEGER PRIMARY KEY AUTOINCREMENT, NICK TEXT, EMAIL TEXT, PASSWORD TEXT )");
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
    onCreate(db);
  }
}
