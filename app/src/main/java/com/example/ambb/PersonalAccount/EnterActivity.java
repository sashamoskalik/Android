package com.example.ambb.PersonalAccount;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ambb.DataBase.DBHelper;
import com.example.ambb.R;

public class EnterActivity extends AppCompatActivity {
  Button buttonEnter, buttonEnterRegistration;
  EditText email, password;

  DBHelper dbHelper;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_enter);

    email = (EditText) findViewById(R.id.email);
    password = (EditText) findViewById(R.id.password);
    final RegistrationActivity reg = new RegistrationActivity();


    dbHelper = new DBHelper(this);

    buttonEnter = (Button) findViewById(R.id.buttonEnter);
    buttonEnterRegistration = (Button) findViewById(R.id.buttonEnterRegistration);
    View.OnClickListener onClickListener = new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String enterEmail = email.getText().toString();
        String enterPassword = password.getText().toString();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        switch (v.getId()){
          case R.id.buttonEnter:
            Log.d("push", "pushEnter");

            boolean flagEmail = false;
            boolean flagPassword = false;

            Cursor cursor = db.query(DBHelper.TABLE_CONTACTS, null, null, null, null, null, null);
            if (cursor.moveToFirst()) {
              int nickIndex = cursor.getColumnIndex(DBHelper.KEY_NICK);
              int emailIndex = cursor.getColumnIndex(DBHelper.KEY_EMAIL);
              int passwordIndex = cursor.getColumnIndex(DBHelper.KEY_PASSWORD);

              do {
                Log.d("table", cursor.getString(emailIndex));
                Log.d("table", cursor.getString(nickIndex));
                if (enterEmail.equals(cursor.getString(emailIndex)) || enterEmail.equals(cursor.getString(nickIndex))){
                  flagEmail = true;
                  Log.d("true", cursor.getString(emailIndex));
                  break;
                }
              }
              while (cursor.moveToNext()) ;
              if (flagEmail == false){
                Toast toast = Toast.makeText(getApplicationContext(), "Email или никнейм не найден", Toast.LENGTH_LONG);
                toast.show();
                Log.d("error", "error in email");
              }

              cursor.moveToFirst();
              do {
                if (enterPassword.equals(cursor.getString(passwordIndex))){
                  Log.d("true", cursor.getString(passwordIndex));
                  flagPassword = true;
                  break;
                }
              }
              while (cursor.moveToNext());

              if (flagPassword == false){
                Toast toast = Toast.makeText(getApplicationContext(), "Неверный пароль", Toast.LENGTH_LONG);
                toast.show();
                Log.d("error", "error in password");
              }

              Log.d("flags", String.valueOf(flagEmail));
              Log.d("flags", String.valueOf(flagPassword));
              if (flagEmail == true && flagPassword == true){
                Intent intent = new Intent(EnterActivity.this, PersonalAccountActivity.class);
                startActivity(intent);
              }

            }
           break;
          case R.id.buttonEnterRegistration:
            Log.d("push", "enterRegistration");
            Intent intent = new Intent(EnterActivity.this, RegistrationActivity.class);
            startActivity(intent);
            break;
        }
      }
    };
    buttonEnter.setOnClickListener(onClickListener);
    buttonEnterRegistration.setOnClickListener(onClickListener);
  }
}
