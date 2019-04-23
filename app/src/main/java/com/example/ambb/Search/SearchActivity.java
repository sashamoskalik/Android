package com.example.ambb.Search;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ambb.ProductFragment;
import com.example.ambb.R;

public class SearchActivity extends AppCompatActivity {

  EditText searchText;
  Button searchButton;
  TextView text;

  private String searchTextClick;

  public SearchActivity(String searchTextClick) {
    this.searchTextClick = searchTextClick;
  }

  public SearchActivity() {
  }

  public String getSearchTextClick() {
    return searchTextClick;
  }

  public void setSearchTextClick(String searchTextClick) {
    this.searchTextClick = searchTextClick;
  }

  FragmentTransaction fragmentTransaction;
  Fragment searchFragment;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search);

    Log.d("Search", "ACTIVITY");
    searchText = (EditText) findViewById(R.id.searchText);
    searchButton = (Button) findViewById(R.id.searchButton);
    text = (TextView) findViewById(R.id.text);

    searchFragment = new Fragment();
    fragmentTransaction = getFragmentManager().beginTransaction();

   /* Fragment fragment = new SearchFragment();
    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
    fragmentTransaction.add(R.id.fragment, fragment);
    fragmentTransaction.commit();

    searchFragment = new SearchFragment();*/

   View.OnClickListener onClickListener = new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Log.d("Clicl", "click");
        searchTextClick = searchText.getText().toString();

        int flag = 0;
        switch (v.getId()) {

          case R.id.searchButton:

            text.setText(searchTextClick);

            fragmentTransaction.add(R.id.fragment, searchFragment);
            Fragment fragment = getFragmentManager().findFragmentById(R.id.fragment);
            //((TextView) (fragment.getView()).findViewById(R.id.textFragmentSearch)).setText(searchTextClick);
            Log.d("String", "sq " + searchTextClick);
            //TextView textView = (TextView) fragment.getView().findViewById(R.id.textFragmentSearch);
            //textView.setText(searchTextClick);
            Log.d("FRAGMENT", "BUTFRAG");
            Log.d("mcdkcd", "MKCDS " + R.id.textFragmentSearch);
            Log.d("SEARTEXT", "JNDCD   " + searchText.getText().toString());

            fragmentTransaction.hide(searchFragment);
            fragmentTransaction.commit();

           /* Fragment fragment1 = getFragmentManager().findFragmentById(R.id.fragment);
            ((TextView) (fragment1.getView()).findViewById(R.id.textFragmentSearch)).setText(searchTextClick);

            Log.d("BUTTONTEXT","TQ" + searchTextClick);

            /*FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.fragment, searchFragment);
            fragmentTransaction.commit();
            TextView textView = (TextView) findViewById(R.id.textFragmentSearch);
            textView.setText(searchTextClick);

            //Log.d("TEXT", "TEXT" + textView);


           /*Cursor cursor = db.query(DataBaseCatalog.TABLE_NAME, new String[] {"NAME"},null,null,null,null, null);

            if (cursor.moveToFirst()){
              int nameIndex = cursor.getColumnIndex(DataBaseCatalog.KEY_NAME);

              int count = 0;
              do {
                Log.d("Search", "SER");
                if (searchTextClick.equals(cursor.getString(nameIndex))){
                  flag = count;
                }
                count++;
              }
              while (cursor.moveToNext());


            }*/
        }
        Log.d("EQUALS", "eq" + flag);
      }
        };
    searchButton.setOnClickListener(onClickListener);
  }


 /* public void onClick(View v){
    String searchTextClick = searchText.getText().toString();
    switch (v.getId()){
      case R.id.searchButton:
        //fragmentTransaction.add(R.id.fragment, searchFragment);
        Fragment fragment = getFragmentManager().findFragmentById(R.id.fragment);
        ((TextView) (fragment.getView()).findViewById(R.id.textFragmentSearch)).setText(searchTextClick);
        Log.d("String", "sq " + searchTextClick);
        //TextView textView = (TextView) fragment.getView().findViewById(R.id.textFragmentSearch);
        //textView.setText(searchTextClick);
        Log.d("FRAGMENT", "BUTFRAG");
        Log.d("mcdkcd", "MKCDS " + R.id.textFragmentSearch);
    }
    fragmentTransaction.commit();
  }
*/
}
