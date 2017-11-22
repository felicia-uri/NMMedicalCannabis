package edu.cnm.deepdive.nmmedicalcannabis.entities;

public class SubTransaction {

  public String getStrain() {
    return strain;
  }

  public void setStrain(String strain) {
    this.strain = strain;
  }

  public double getGrams() {
    return grams;
  }

  public void setGrams(double grams) {
    this.grams = grams;
  }

  public ProductType getProductType() {
    return productType;
  }

  public void setProductType(ProductType productType) {
    this.productType = productType;
  }

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
