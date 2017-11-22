package edu.cnm.deepdive.nmmedicalcannabis.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class SubTransaction {



  @DatabaseField(columnName = "SUB_TRANSACTION_ID", generatedId = true)
  private int id;

  @DatabaseField(columnName = "STRAIN", canBeNull = false)
  private String strain;

  @DatabaseField(columnName = "GRAMS", canBeNull = false)
  private double grams;

  @DatabaseField(columnName = "PRODUCT_TYPE", canBeNull = false, foreignAutoRefresh = true, foreign = true)
  private ProductType productType;

  @DatabaseField(columnName = "TRANSACTION_ID", foreign = true)
  private TransactionDatabase transactionDatabase;


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

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public TransactionDatabase getTransactionDatabase() {
    return transactionDatabase;
  }

  public void setTransactionDatabase(
      TransactionDatabase transactionDatabase) {
    this.transactionDatabase = transactionDatabase;
  }

  @Override
  public String toString() {
    return "" + productType +
        ", Strain = " + strain +
        ", Grams = " + grams ;
  }



}
