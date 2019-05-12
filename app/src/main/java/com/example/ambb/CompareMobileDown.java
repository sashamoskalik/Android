package com.example.ambb;

import com.example.ambb.Search.Mobile;

import java.util.Comparator;

public class CompareMobileDown implements Comparator<Mobile> {
  public int compare(Mobile ob1, Mobile ob2) {
    return (ob2.getPrice() - ob1.getPrice());
  }
}
