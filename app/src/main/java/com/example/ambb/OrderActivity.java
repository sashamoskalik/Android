package com.example.ambb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity {

  Button buttonOrder;
  EditText name, mail, tel, city, adress, comment;
  private int suma;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    int sum = 0;
    String product = "";
    setContentView(R.layout.activity_order);

    final TextView summa = (TextView) findViewById(R.id.summa);
    name = (EditText) findViewById(R.id.name);
    mail = (EditText) findViewById(R.id.mail);
    tel = (EditText) findViewById(R.id.tel);
    city = (EditText) findViewById(R.id.city);
    adress = (EditText) findViewById(R.id.adress);
    comment = (EditText) findViewById(R.id.comment);
    buttonOrder = (Button) findViewById(R.id.buttonOrder);


    Bundle extras = getIntent().getExtras();
    if (extras != null) {
      sum = extras.getInt("sum");
      product = extras.getString("string");
    }
    Log.d("ORDER", String.valueOf(sum));

    summa.setText("Итоговая сумма = " + String.valueOf(sum) + " руб.");
    suma = sum;

    final String finalProduct = product;
    buttonOrder.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {

        String nameOrder = name.getText().toString();
        String mailOrder = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String telOrder = tel.getText().toString();
        String cityOrder = city.getText().toString();
        String adressOrder = adress.getText().toString();
        String commentOrder = comment.getText().toString();

        Log.d("TEXT", nameOrder );
        if (nameOrder.equals("")  || cityOrder.equals("")
        || adressOrder.equals("") || mail.getText().toString().equals("") || telOrder.equals("")){
          Toast toast = Toast.makeText(getApplicationContext(), "Заполните данные", Toast.LENGTH_LONG);
          toast.show();
        }
        else {
          Intent comment = new Intent(Intent.ACTION_SEND);
          comment.putExtra(Intent.EXTRA_EMAIL, new String[]{"sashamoskalik@gmail.com"});
          comment.putExtra(Intent.EXTRA_SUBJECT, "Новый заказ");
          comment.putExtra(Intent.EXTRA_TEXT, "Имя: " + nameOrder +"\n" +
            "E-mail: " + mail.getText().toString() +"\n" +
            "Телефон: " + telOrder +"\n" +
            "Город: " + cityOrder + "\n"+
            "Адрес: " + adressOrder +"\n" +
            "Комментарий: " + commentOrder + "\n" +
            "Товар: " + finalProduct + "\n" +
            "Сумма: " + String.valueOf(suma) + " руб.");

          Log.d("NAME", nameOrder);
          comment.setType("message/rfc822");

          startActivity(Intent.createChooser(comment, "Choose email: "));

          Toast toast = Toast.makeText(getApplicationContext(), "Заказ отправлен",Toast.LENGTH_LONG);
          toast.show();
        }
      }
    });
  }
}
