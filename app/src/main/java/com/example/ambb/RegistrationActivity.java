package com.example.ambb;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

  Button buttonRegistration;
  EditText nick, email, password, returnPassword;

  DBHelper dbHelper;

  final String LOG_TAG = "myLogs";
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_registration);

    nick = (EditText) findViewById(R.id.nick);
    email = (EditText) findViewById(R.id.email);
    password = (EditText) findViewById(R.id.password);
    returnPassword = (EditText) findViewById(R.id.returnPassword);

    dbHelper = new DBHelper(this);


    buttonRegistration = (Button) findViewById(R.id.buttonRegistration);

    View.OnClickListener textViewClickListener = new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        String regNick = nick.getText().toString();
        String regEmail = email.getText().toString();
        String regPassword = password.getText().toString();
        String regReturnPassword = returnPassword.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        ContentValues contentValues = new ContentValues();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        switch (view.getId()){
          case R.id.buttonRegistration:

            if (regNick.equals("")){
              Toast error = Toast.makeText(getApplicationContext(), "Укажите никнейм", Toast.LENGTH_LONG);
              error.setGravity(Gravity.TOP, 0 , 160);
              error.show();
              break;
            }

            if (regEmail.matches(emailPattern) && regEmail.length() > 0 ){
            }
            else {
              Toast error = Toast.makeText(getApplicationContext(), "Проверьте e-mail", Toast.LENGTH_LONG);
              error.setGravity(Gravity.TOP, 0 , 160);
              error.show();
              break;
            }

            if (regPassword.equals(null)){
              Toast error = Toast.makeText(getApplicationContext(), "Введите пароль", Toast.LENGTH_LONG);
              error.setGravity(Gravity.TOP, 0 , 160);
              error.show();
              break;
            }

            if (regPassword.equals(regReturnPassword)){
            }
            else {
              Toast error = Toast.makeText(getApplicationContext(), "Пароли не совпадают", Toast.LENGTH_LONG);
              error.setGravity(Gravity.TOP, 0 , 160);
              error.show();
              Log.d(LOG_TAG,"error password");
              returnPassword.setText("");
              break;
            }

            contentValues.put("nick", regNick);
            contentValues.put("email", regEmail);
            contentValues.put("password", regPassword);

            long rowID = db.insert("ContactsUsers", null, contentValues);
            Log.d(LOG_TAG, "row inserted, ID" + rowID);
            Log.d(LOG_TAG, "login and password, " + contentValues);

            Toast reg = Toast.makeText(getApplicationContext(), "Спасибо за регистрацию", Toast.LENGTH_LONG);
            reg.show();


            Intent send = new Intent(Intent.ACTION_SEND);
            send.putExtra(Intent.EXTRA_EMAIL, regEmail);
            send.putExtra(Intent.EXTRA_SUBJECT, new String[]{"AMBB"});
            send.putExtra(Intent.EXTRA_TEXT, new String[]{"Спасибо, что зарегистрировались в нашем приложении!"});
            send.putExtra(Intent.EXTRA_EMAIL, new String[]{"sashamoskalik@gmail.com"});


            send.setType("message/rfc822");

            nick.setText("");
            email.setText("");
            password.setText("");
            returnPassword.setText("");
            break;
        }
      }
    };
    buttonRegistration.setOnClickListener(textViewClickListener);
  }
}

