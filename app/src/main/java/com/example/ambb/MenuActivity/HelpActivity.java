package com.example.ambb.MenuActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.ambb.CompanyActivity;
import com.example.ambb.R;

public class HelpActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_help);

    final TextView com = (TextView) findViewById(R.id.company);

    com.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(HelpActivity.this, CompanyActivity.class);
        startActivity(intent);
      }
    });
  }
}
