package com.example.ambb;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegistrationActivity extends AppCompatActivity {

  Button buttonRegistration;
  EditText login, password;

  DBHelper dbHelper;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_registration);

    login = (EditText) findViewById(R.id.login);
    password = (EditText) findViewById(R.id.password);


    buttonRegistration = (Button) findViewById(R.id.buttonRegistration);

    View.OnClickListener textViewClickListener = new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        String onlogin = login.getText().toString();
        String onpassword = password.getText().toString();


        switch (view.getId()){
          case R.id.buttonRegistration:
            //Log.d("hello", "hello");

            break;
        }
      }
    };
    buttonRegistration.setOnClickListener(textViewClickListener);
  }

 /* public void onClick(View view){
    String onlogin = login.getText().toString();
    String onpassword = password.getText().toString();

    SQLiteDatabase database = dbHelper.getWritableDatabase();
    ContentValues contentValues = new ContentValues();

    switch (view.getId()){
      case R.id.buttonRegistration:
        contentValues.put(DBHelper.KEY_Login, onlogin);
        contentValues.put(DBHelper.KEY_PASSWORD, onpassword);

        database.insert(DBHelper.TABLE_CONTACTS, null, contentValues);
        break;
    }
  }*/
}

