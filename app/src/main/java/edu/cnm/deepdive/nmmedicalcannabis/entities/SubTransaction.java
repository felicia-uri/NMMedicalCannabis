package edu.cnm.deepdive.nmmedicalcannabis.entities;

public class SubTransaction {

  public String strain;
  public double grams;
  public ProductType productType;

  @Override
  public String toString() {
    return "Product Type" + productType +
        "strain='" + strain + '\'' +
        ", grams=" + grams +
        '}';
  }



}
