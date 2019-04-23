package com.example.ambb.Search;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ambb.DataBase.DataBaseCatalog;
import com.example.ambb.R;


public class SearchFragment extends Fragment {

  DataBaseCatalog dataBaseCatalog;

  private String string;
  private EditText searchText;
 // EditText editText;
  TextView textView;

  public void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);

    SearchActivity searchActivity = new SearchActivity();
    string = searchActivity.getSearchTextClick();
    Log.d("jdnjnc", "NDJCN " + searchActivity.getSearchTextClick());
    Log.d("CREATE", "create");
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_product, null);
    View view = inflater.inflate(R.layout.activity_search, null);

    //textView = (TextView) v.findViewById(R.id.textFragmentSearch);
    //textView.setText("hello");
    /*editText = (EditText) view.findViewById(R.id.searchText);
    textView = (TextView) view.findViewById(R.id.text);

    Log.d("TEXTVIEW", "T " + textView.getText());
    Log.d("TEXTVIEW", "TEXT  " + editText.getText().toString());
    /*dataBaseCatalog = new DataBaseCatalog(getActivity());

      Log.d("FRAGMENT", "FRAG");;
    EditText searchText = (EditText)getActivity().findViewById(R.id.searchText);
    Button searchButton = (Button)getActivity().findViewById(R.id.searchButton);

   // Log.d("TOTALTEXT", "TEXT  " + textFragmentSearch);
    Log.d("SEARCHTEXT", "TEXT  " + searchText);

    //searchButton.setOnClickListener(this);*/
    return v;/*
  }
/*
  @Override
  public void onClick(View v) {
    final EditText searchText = (EditText)getActivity().findViewById(R.id.searchText);
    Log.d("Clicl", "click");
    String searchTextClick = searchText.getText().toString();
    int flag = 0;

    SQLiteDatabase db = dataBaseCatalog.getReadableDatabase();


    switch (v.getId()){
      case R.id.searchButton:
        Cursor cursor = db.query(DataBaseCatalog.TABLE_NAME, new String[] {"NAME"},null,null,null,null, null);

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

        }
    }
    Log.d("EQUALS", "eq" + flag);*/
  }

  public void onStart() {
    super.onStart();

    //textView.setText("onStart");
    Log.d("chsbhcsbhcs", "JCNDJCN  " + string);
    searchText = (EditText)getActivity().findViewById(R.id.searchText);
    Log.d("ONSTART", "START" + searchText.getText().toString());
  }
}
