package com.example.ambb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FABActivity extends AppCompatActivity {

  Button upbutton, downbutton, popbutton;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_fab);

    upbutton = (Button)findViewById(R.id.upButton);
    downbutton = (Button)findViewById(R.id.downButton);
    popbutton = (Button)findViewById(R.id.popButton);

    upbutton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(FABActivity.this, MainActivity.class);
        intent.putExtra("sortCounter", 2);
        startActivity(intent);
      }
    });

    downbutton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(FABActivity.this, MainActivity.class);
        intent.putExtra("sortCounter", 3);
        startActivity(intent);
      }
    });

    popbutton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(FABActivity.this, MainActivity.class);
        intent.putExtra("sortCounter", 4);
        startActivity(intent);
      }
    });
  }
}
