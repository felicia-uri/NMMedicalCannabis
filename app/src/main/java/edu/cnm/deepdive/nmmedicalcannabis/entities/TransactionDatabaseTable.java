package edu.cnm.deepdive.nmmedicalcannabis.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.sql.Timestamp;
import java.util.Date;

@DatabaseTable(tableName = "TRANSACTIONS")
public class TransactionDatabaseTable {

  @DatabaseField(columnName = "TRANSACTIONS_ID", generatedId = true)
  private int id;

  @DatabaseField(columnName = "CREATED", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
      format = "yyyy-MM-dd HH:mm:ss", canBeNull = false, readOnly = true)
  private Timestamp created;

  public Date getPurchasedDate() {
    return purchasedDate;
  }

  public void setPurchasedDate(Date purchasedDate) {
    this.purchasedDate = purchasedDate;
  }

  @DatabaseField(columnName = "PURCHASED_DATE", format = "yyyy-MM-dd", canBeNull = false, index = true)
  private Date purchasedDate;

  @DatabaseField(columnName = "UNITS_PURCHASED", canBeNull = false)
  private int unitsPurchased;

  @DatabaseField(columnName = "PURCHASED_FROM", canBeNull = true)
  private String purchasedFrom;

  @DatabaseField(columnName = "STRAIN_NAME", canBeNull = true)
  private String strainName;

  @DatabaseField(columnName = "PATIENT_CARD_ID", canBeNull = false, foreign = true)
  public PatientCardDatabaseTable mPatientCardDatabaseTable;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Timestamp getCreated() {
    return created;
  }

  public void setCreated(Timestamp created) {
    this.created = created;
  }

  public int getUnitsPurchased() {
    return unitsPurchased;
  }

  public void setUnitsPurchased(int unitsPurchased) {
    this.unitsPurchased = unitsPurchased;
  }

  public String getPurchasedFrom() {
    return purchasedFrom;
  }

  public void setPurchasedFrom(String purchasedFrom) {
    this.purchasedFrom = purchasedFrom;
  }

  public String getStrainName() {
    return strainName;
  }

  public void setStrainName(String strainName) {
    this.strainName = strainName;
  }

  public PatientCardDatabaseTable getPatientCardDatabaseTable() {
    return mPatientCardDatabaseTable;
  }

  public void setPatientCardDatabaseTable(
      PatientCardDatabaseTable patientCardDatabaseTable) {
    this.mPatientCardDatabaseTable = patientCardDatabaseTable;
  }

  @Override
  public String toString() {
    return "TransactionDatabaseTable{" +
        "id=" + id +
        ", created=" + created +
        ", purchased=" + purchasedDate +
        ", unitsPurchased=" + unitsPurchased +
        ", purchasedFrom='" + purchasedFrom + '\'' +
        ", strainName='" + strainName + '\'' +
        ", mPatientCardDatabaseTable=" + mPatientCardDatabaseTable +
        '}';
  }
}
