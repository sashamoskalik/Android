package com.example.ambb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

  public static final int DATABASE_VERSION = 4;
  public static final String DATABASE_NAME = "contactdb";
  public static final String TABLE_CONTACTS = "Contacts";

  public static final String KEY_ID = "_Id";
  public static final String KEY_Login = "Login";
  public static final String KEY_PASSWORD = "Password";

  public DBHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL("CREATE TABLE " + TABLE_CONTACTS + "(" + KEY_ID + " INTEGER PRIMARY KEY," +
      KEY_Login + " TEXT," + KEY_PASSWORD + " TEXT" + ")");
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("drop table if exists " + TABLE_CONTACTS);
    onCreate(db);
  }
}
