package edu.cnm.deepdive.nmmedicalcannabis.entities;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.sql.Timestamp;
import java.util.Date;

@DatabaseTable(tableName = "PATIENT_CARD")
public class PatientCardDatabaseTable {

  @DatabaseField(columnName = "CARD_ID", generatedId = true)
  private int id;

  @DatabaseField(columnName = "CREATED", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
  format = "yyyy-MM-dd HH:mm:ss", canBeNull = false, readOnly = true)
  private Timestamp created;

  @DatabaseField(columnName = "CARD_ID_NUMBER", canBeNull = false)
  private long cardID;

  @DatabaseField(columnName = "ISSUE_DATE", format = "yyyy-MM-dd", canBeNull = false)
  private Date issueDate;

  @DatabaseField(columnName = "EXPIRATION_DATE", format = "yyyy-MM-dd", canBeNull = false)
  private  Date expDate;

  @DatabaseField(columnName = "UNITS_AVAILABLE", canBeNull = false)
  private int unitsAvailable;

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

  public long getCardID() {
    return cardID;
  }

  public void setCardID(long cardID) {
    this.cardID = cardID;
  }

  public Date getIssueDate() {
    return issueDate;
  }

  public void setIssueDate(Date issueDate) {
    this.issueDate = issueDate;
  }

  public Date getExpDate() {
    return expDate;
  }

  public void setExpDate(Date expDate) {
    this.expDate = expDate;
  }

  public int getUnitsAvailable() {
    return unitsAvailable;
  }

  public void setUnitsAvailable(int unitsAvailable) {
    this.unitsAvailable = unitsAvailable;
  }

  @Override
  public String toString() {
    return "PatientCardDatabaseTable{" +
        "id=" + id +
        ", created=" + created +
        ", mPatientCardDatabaseTable=" + cardID +
        ", issueDate=" + issueDate +
        ", expDate=" + expDate +
        ", unitsAvailable=" + unitsAvailable +
        '}';

  }
}


