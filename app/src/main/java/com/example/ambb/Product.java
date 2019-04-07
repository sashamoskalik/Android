package com.example.ambb;

public class Product {
  private String name;
  private String color;
  private String description;
  private String price;
  private int picture;


  public Product(String name, String color, String description, String price, int picture) {
    this.name = name;
    this.color = color;
    this.description = description;
    this.price = price;
    this.picture = picture;
  }

  public static final Product[] product = {
    new Product("Huawei P20", "black", "jbdbchsb, sdvsdvs, vdsvsdv, dsvdvsd, sdvsdv, m dmndjvnjdv",
      "Цена: " + "1100" + " руб", R.drawable.p20),
    new Product("Huawei P10", "black", "bchsbhbs,cnasjncaj,cncajs,cnasjcna,ncasjcn, vsjvnsjdvnjs,vndsjnvjsdv,kvnsdjvnsjd",
      "Цена: " + "900" + " руб", R.drawable.p10),
    new Product("Apple Iphone X", "black", "bchsbhbs,cnasjncaj,cncajs,cnasjcna,ncasjcn, vsjvnsjdvnjs,vndsjnvjsdv,kvnsdjvnsjd",
       "Цена: " + "1900" + " руб", R.drawable.iphone10),
    new Product("Xiaomi Redmi 6A", "black", "bchsbhbs,cnasjncaj,cncajs,cnasjcna,ncasjcn, vsjvnsjdvnjs,vndsjnvjsdv,kvnsdjvnsjd",
      "Цена: " + "200" + " руб", R.drawable.xiaomi_redmi_6a)
  };

  public String getName() {
    return name;
  }

  public String getColor() {
    return color;
  }

  public String getDescription() {
    return description;
  }

  public String getPrice() {
    return price;
  }

  public int getPicture() {
    return picture;
  }
}
