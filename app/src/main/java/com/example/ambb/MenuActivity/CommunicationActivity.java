package com.example.ambb.MenuActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ambb.R;

public class CommunicationActivity extends AppCompatActivity {

  Button buttonCommunication;
  EditText topicCommunication, textCommunication;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_communication);


    topicCommunication = (EditText) findViewById(R.id.topicCommunication);
    textCommunication = (EditText) findViewById(R.id.textCommunication);
    buttonCommunication = (Button) findViewById(R.id.buttonCommunication);
    buttonCommunication.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        String topic = topicCommunication.getText().toString();
        String text = textCommunication.getText().toString();

        if (topic.equals("") || text.equals("")){
          Toast toast = Toast.makeText(getApplicationContext(), "Заполните тему или текст",Toast.LENGTH_LONG);
          toast.show();
          Log.d("STRING", "TOPIC");
        }
        else {
          Intent comment = new Intent(Intent.ACTION_SEND);
          comment.putExtra(Intent.EXTRA_EMAIL, new String[]{"sashamoskalik@gmail.com"});
          comment.putExtra(Intent.EXTRA_SUBJECT, topic);
          comment.putExtra(Intent.EXTRA_TEXT, text);

          comment.setType("message/rfc822");

          startActivity(Intent.createChooser(comment, "Choose email: "));

          Toast toast = Toast.makeText(getApplicationContext(), "Спасибо, вы сделали приложение лучше!",Toast.LENGTH_LONG);
          toast.show();
        }

      }
    });
  }
}
