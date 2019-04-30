package com.example.ambb.Search;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.ambb.R;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> implements Filterable {
  private ArrayList<AndroidVersion> mArrayList;
  private ArrayList<AndroidVersion> mFilteredList;

  public DataAdapter(ArrayList<AndroidVersion> arrayList) {
    mArrayList = arrayList;
    mFilteredList = arrayList;
  }

  @Override
  public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_row, viewGroup, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, int i) {

    viewHolder.tv_name.setText(mFilteredList.get(i).getName());
    viewHolder.tv_color.setText(mFilteredList.get(i).getColor());
    viewHolder.tv_description.setText(mFilteredList.get(i).getDescription());
    viewHolder.tv_price.setText(mFilteredList.get(i).getPrice());
  }

  @Override
  public int getItemCount() {
    return mFilteredList.size();
  }

  @Override
  public Filter getFilter() {

    return new Filter() {
      @Override
      protected FilterResults performFiltering(CharSequence charSequence) {

        String charString = charSequence.toString();

        if (charString.isEmpty()) {

          mFilteredList = mArrayList;
        } else {

          ArrayList<AndroidVersion> filteredList = new ArrayList<>();

          for (AndroidVersion androidVersion : mArrayList) {

            if (androidVersion.getDescription().toLowerCase().contains(charString) || androidVersion.getName().toLowerCase().contains(charString) || androidVersion.getColor().toLowerCase().contains(charString) || androidVersion.getPrice().toLowerCase().contains(charString)) {

              filteredList.add(androidVersion);
            }
          }

          mFilteredList = filteredList;
        }

        FilterResults filterResults = new FilterResults();
        filterResults.values = mFilteredList;
        return filterResults;
      }

      @Override
      protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        mFilteredList = (ArrayList<AndroidVersion>) filterResults.values;
        notifyDataSetChanged();
      }
    };
  }

  public class ViewHolder extends RecyclerView.ViewHolder{
    private TextView tv_name,tv_color,tv_description, tv_price;
    public ViewHolder(View view) {
      super(view);

      tv_name = (TextView)view.findViewById(R.id.tv_name);
      tv_color = (TextView)view.findViewById(R.id.tv_color);
      tv_description = (TextView)view.findViewById(R.id.tv_description);
      tv_price = (TextView)view.findViewById(R.id.tv_price);
    }
  }

}
