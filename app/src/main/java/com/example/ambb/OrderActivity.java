package com.example.ambb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    int sum = 0;
    setContentView(R.layout.activity_order);

    TextView summa = (TextView) findViewById(R.id.summa);

    Bundle extras = getIntent().getExtras();
    if (extras != null) {
      sum = extras.getInt("sum");
    }
    Log.d("ORDER", String.valueOf(sum));

    summa.setText("Итоговая сумма = " + String.valueOf(sum) + " руб.");
  }
}
