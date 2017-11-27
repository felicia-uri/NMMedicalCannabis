package edu.cnm.deepdive.nmmedicalcannabis.entities;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Creates database for the patient card information
 */
@DatabaseTable(tableName = "PATIENT_CARD")
public class CardDatabase {

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
  private double unitsAvailable;

  /**
   * Creates the key column and issues an ID starting at 1.
   * @return ID Key
   */
  public int getId() {
    return id;
  }

  /**
   * Creates access to ID Key
   * @param id KEY ID
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Creates a timestamp when each transaction is made.
   * @return returns timestamp
   */
  public Timestamp getCreated() {
    return created;
  }

  /**
   * Creates access to timestamp
   * @param created the date and time transaction was created
   */
  public void setCreated(Timestamp created) {
    this.created = created;
  }

  /**
   * Gets the ID number of the patient card
   * @return returns card number
   */
  public long getCardID() {
    return cardID;
  }

  /**
   * Gives access to the card ID number
   * @param cardID the number of the patient card
   */
  public void setCardID(long cardID) {
    this.cardID = cardID;
  }

  /**
   * Gets the date of when the card was issued.
   * @return returns the issue date
   */
  public Date getIssueDate() {
    return issueDate;
  }

  /**
   * Gives access to the issue date
   * @param issueDate
   */
  public void setIssueDate(Date issueDate) {
    this.issueDate = issueDate;
  }

  /**
   * Gets the expiration date of the card. One year from the issue date.
   * @return returns expiration date.
   */
  public Date getExpDate() {
    return expDate;
  }

  /**
   * Gives access to the expiration date
   * @param expDate
   */
  public void setExpDate(Date expDate) {
    this.expDate = expDate;
  }

  /**
   * Gets the units available from when the users starts using the app
   * or if the user is a new patient
   * @return returns the units available at the time of download.
   */
  public double getUnitsAvailable() {
    return unitsAvailable;
  }

  /**
   * Gives access to the units available.
   * @param unitsAvailable
   */
  public void setUnitsAvailable(double unitsAvailable) {
    this.unitsAvailable = unitsAvailable;
  }

  @Override
  public String toString() {
    return "CardDatabase{" +
        "id=" + id +
        ", created=" + created +
        ", mPatientCardDatabaseTable=" + cardID +
        ", issueDate=" + issueDate +
        ", expDate=" + expDate +
        ", unitsAvailable=" + unitsAvailable +
        '}';

  }
}


