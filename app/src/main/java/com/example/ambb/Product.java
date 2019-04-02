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
      "1100", R.drawable.p20),
    new Product("Huawei P10", "black", "bchsbhbs,cnasjncaj,cncajs,cnasjcna,ncasjcn, vsjvnsjdvnjs,vndsjnvjsdv,kvnsdjvnsjd",
      "900", R.drawable.p10),
    new Product("Huawei P10", "black", "bchsbhbs,cnasjncaj,cncajs,cnasjcna,ncasjcn, vsjvnsjdvnjs,vndsjnvjsdv,kvnsdjvnsjd",
      "900", R.drawable.p10),
    new Product("Huawei P10", "black", "bchsbhbs,cnasjncaj,cncajs,cnasjcna,ncasjcn, vsjvnsjdvnjs,vndsjnvjsdv,kvnsdjvnsjd",
      "900", R.drawable.p10)
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
