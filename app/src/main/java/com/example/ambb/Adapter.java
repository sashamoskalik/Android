package com.example.ambb;

import android.graphics.drawable.Drawable;
import android.net.sip.SipSession;
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
  private int[] arrayPrice;
  private int[] arrayPicture;
  private Listener listener;

  interface Listener {
    void onClick(int position);
  }

  public Adapter(String[] arrayName, String[] arrayColor, String[] arrayDescription, int[] arrayPrice, int[] arrayPicture) {
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
  public void onBindViewHolder(ViewHolder viewHolder, final int position) {
    CardView cardView = viewHolder.cardView;
    ImageView imageView = (ImageView) cardView.findViewById(R.id.infoPicture);
    Drawable drawable = ContextCompat.getDrawable(cardView.getContext(), arrayPicture[position]);
    imageView.setImageDrawable(drawable);
    imageView.setContentDescription(arrayName[position]);
    TextView textName = (TextView) cardView.findViewById(R.id.infoName);
    textName.setText(arrayName[position]);
    TextView textColor = (TextView) cardView.findViewById(R.id.infoColor);
    textColor.setText(arrayColor[position]);
    TextView textDescription = (TextView) cardView.findViewById(R.id.infoDescription);
    textDescription.setText(arrayDescription[position]);
    TextView textPrice = (TextView) cardView.findViewById(R.id.infoPrice);
    textPrice.setText("от " + String.valueOf(arrayPrice[position] + " руб."));

    cardView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (listener != null){
          listener.onClick(position);
        }
      }
    });
  }

  @Override
  public int getItemCount() {
    return arrayName.length;
  }

  public void setListener(Listener listener){
    this.listener = listener;
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {

    private CardView cardView;

    public ViewHolder(CardView itemView) {
      super(itemView);
      cardView = itemView;
    }
  }
}
