package edu.cnm.deepdive.nmmedicalcannabis.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "PRODUCT_TYPE")
public class ProductType {

  @DatabaseField(columnName = "PRODUCT_TYPE_ID", generatedId = true)
  private int id;

  @DatabaseField(columnName = "NAME", canBeNull = false)
  private String name;

  @DatabaseField (columnName = "MULTIPLIER", canBeNull = false)
  private double multiplier;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getMultiplier() {
    return multiplier;
  }

  public void setMultiplier(double multiplier) {
    this.multiplier = multiplier;
  }

  @Override
  public String toString() {
    return name;
  }
}

