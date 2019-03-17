package com.example.ambb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

  public static final int DATABASE_VERSION = 1;
  public static final String DATABASE_NAME = "cont.db";
  public static final String TABLE_CONTACTS = "Contacts";

  public static final String KEY_ID = "Id";
  public static final String KEY_Login = "Login";
  public static final String KEY_PASSWORD = "Password";

  public DBHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
    SQLiteDatabase db = this.getWritableDatabase();
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL("CREATE TABLE " + TABLE_CONTACTS + " ( ID INTEGER PRIMARY KEY AUTOINCREMENT, LOGIN TEXT, PASSWORD TEXT )");
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
    onCreate(db);
  }
}
