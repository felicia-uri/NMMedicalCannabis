package edu.cnm.deepdive.nmmedicalcannabis.entities;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import java.sql.Timestamp;

@DatabaseTable(tableName = "TRANSACTIONS")
public class Transactions {

  @DatabaseField(columnName = "TRANSACTIONS_ID", generatedId = true)
  private int id;

  @DatabaseField(columnName = "CREATED", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
      format = "yyyy-MM-dd HH:mm:ss", canBeNull = false, readOnly = true)
  private Timestamp created;

  @DatabaseField(columnName = "UNITS_PURCHASED", canBeNull = false)
  private int unitsPurchased;

  @DatabaseField(columnName = "PURCHASED_FROM", canBeNull = true)
  private String purchasedFrom;

  @DatabaseField(columnName = "STRAIN_NAME", canBeNull = true)
  private String strainName;

  @DatabaseField(columnName = "PATIENT_CARD_ID", canBeNull = false, foreign = true)
  public PatientCard patientCard;

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

  public PatientCard getPatientCard() {
    return patientCard;
  }

  public void setPatientCard(
      PatientCard patientCard) {
    this.patientCard = patientCard;
  }

  @Override
  public String toString() {
    return "Transactions{" +
        "id=" + id +
        ", created=" + created +
        ", unitsPurchased=" + unitsPurchased +
        ", purchasedFrom='" + purchasedFrom + '\'' +
        ", strainName='" + strainName + '\'' +
        ", patientCard=" + patientCard +
        '}';
  }
}
