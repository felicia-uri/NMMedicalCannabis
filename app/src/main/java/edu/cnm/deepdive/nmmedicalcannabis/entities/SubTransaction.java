package edu.cnm.deepdive.nmmedicalcannabis.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Creates database table for subtransactions
 */
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

  /**
   * Gets strain name.
   * @return returns strain name
   */
  public String getStrain() {
    return strain;
  }

  /**
   * Gives access to strain names
   * @param strain
   */
  public void setStrain(String strain) {
    this.strain = strain;
  }

  /**
   * Gets grams entered by user.
   * @return returns total grams
   */
  public double getGrams() {
    return grams;
  }

  /**
   * Gives access to grams
   * @param grams
   */
  public void setGrams(double grams) {
    this.grams = grams;
  }

  /**
   * Gets the product type of the cannabis purchased
   * @return returns type of cannabis
   */
  public ProductType getProductType() {
    return productType;
  }

  /**
   * Gives access to product type
   * @param productType
   */
  public void setProductType(ProductType productType) {
    this.productType = productType;
  }

  /**
   * Gets transaction id (foreign key)
   * @return
   */
  public int getId() {
    return id;
  }

  /**
   * Gives access to foreign key ID.
   * @param id
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Gets the transactions from database
   * @return
   */
  public TransactionDatabase getTransactionDatabase() {
    return transactionDatabase;
  }

  /**
   * Gives access to the transaction database.
   * @param transactionDatabase
   */
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
