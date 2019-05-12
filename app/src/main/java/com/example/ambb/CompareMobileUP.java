package com.example.ambb;

import com.example.ambb.Search.Mobile;

import java.util.Comparator;

public class CompareMobileUP implements Comparator<Mobile> {
  public int compare(Mobile ob1, Mobile ob2) {
    return (ob1.getPrice() - ob2.getPrice());
  }
}
