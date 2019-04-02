package com.example.ambb;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.security.AccessController;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

  private String[] arrayName;
  private String[] arrayColor;
  private String[] arrayDescription;
  private String[] arrayPrice;
  private int[] arrayPicture;

  public Adapter(String[] arrayName, String[] arrayColor, String[] arrayDescription, String[] arrayPrice, int[] arrayPicture) {
    this.arrayName = arrayName;
    this.arrayColor = arrayColor;
    this.arrayDescription = arrayDescription;
    this.arrayPrice = arrayPrice;
    this.arrayPicture = arrayPicture;
  }

  @Override
  public Adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
    CardView cardView = (CardView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_product, null);
    return new  ViewHolder(cardView);
  }

  @Override
  public void onBindViewHolder(ViewHolder viewHolder, int i) {
    CardView cardView = viewHolder.cardView;
    ImageView imageView = (ImageView) cardView.findViewById(R.id.infoPicture);
    Drawable drawable = ContextCompat.getDrawable(cardView.getContext(), arrayPicture[i]);
    imageView.setImageDrawable(drawable);
    imageView.setContentDescription(arrayName[i]);
    TextView textName = (TextView) cardView.findViewById(R.id.infoName);
    textName.setText(arrayName[i]);
    TextView textColor = (TextView) cardView.findViewById(R.id.infoColor);
    textColor.setText(arrayColor[i]);
    TextView textDescription = (TextView) cardView.findViewById(R.id.infoDescription);
    textDescription.setText(arrayDescription[i]);
    TextView textPrice = (TextView) cardView.findViewById(R.id.infoPrice);
    textPrice.setText(arrayPrice[i]);
  }

  @Override
  public int getItemCount() {
    return arrayName.length;
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {

    private CardView cardView;

    public ViewHolder(CardView itemView) {
      super(itemView);
      cardView = itemView;
    }
  }
}
