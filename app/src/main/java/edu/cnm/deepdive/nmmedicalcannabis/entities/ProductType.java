package edu.cnm.deepdive.nmmedicalcannabis.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Creates database table for the type multiplier
 */
@DatabaseTable(tableName = "PRODUCT_TYPE")
public class ProductType {

  @DatabaseField(columnName = "PRODUCT_TYPE_ID", generatedId = true)
  private int id;

  @DatabaseField(columnName = "NAME", canBeNull = false)
  private String name;

  @DatabaseField (columnName = "MULTIPLIER", canBeNull = false)
  private double multiplier;

  /**
   * Gets generated ID for the product type database.
   * @return returns product type ID Key
   */
  public int getId() {
    return id;
  }

  /**
   * Gives access to the product type ID Key
   * @param id
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Gets name of strain
   * @return returns strain name
   */
  public String getName() {
    return name;
  }

  /**
   * Gives access to strain name using parameters
   * @param name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets multiplier of each type of cannabis type
   * @return returns conversion of grams to units
   */
  public double getMultiplier() {
    return multiplier;
  }

  /**
   * Gives access to multiplier
   * @param multiplier
   */
  public void setMultiplier(double multiplier) {
    this.multiplier = multiplier;
  }

  @Override
  public String toString() {
    return name;
  }
}

