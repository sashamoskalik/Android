package com.example.ambb;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.ConditionVariable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

        ConditionVariable conditionVariable = new ConditionVariable();

        switch (v.getId()){
          case R.id.buttonEnter:
            Log.d("push", "pushEnter");
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
