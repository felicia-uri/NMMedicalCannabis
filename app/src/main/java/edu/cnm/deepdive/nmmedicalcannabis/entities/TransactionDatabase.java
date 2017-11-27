package edu.cnm.deepdive.nmmedicalcannabis.entities;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Creates transaction history database table.
 */
@DatabaseTable(tableName = "TRANSACTIONS")
public class TransactionDatabase {

  @DatabaseField(columnName = "TRANSACTIONS_ID", generatedId = true)
  private int id;

  @DatabaseField(columnName = "CREATED", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
      format = "yyyy-MM-dd HH:mm:ss", canBeNull = false, readOnly = true)
  private Timestamp created;

  @DatabaseField(columnName = "PURCHASED_DATE", format = "yyyy-MM-dd", canBeNull = true, index = true)
  private Date purchasedDate;

  @DatabaseField(columnName = "PURCHASED_FROM", canBeNull = true)
  private String purchasedFrom;

  @ForeignCollectionField
  private ForeignCollection<SubTransaction> subTransaction;

  /**
   * Gets the purchased date
   * @return returns purchase date
   */
  public Date getPurchasedDate() {
    return purchasedDate;
  }

  /**
   * Gives access to the purchased date
   * @param purchasedDate
   */
  public void setPurchasedDate(Date purchasedDate) {
    this.purchasedDate = purchasedDate;
  }

  /**
   * Gets the transaction ID (generated ID Key)
   * @return
   */
  public int getId() {
    return id;
  }

  /**
   * Gives access to the transaction ID
   * @param id
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Gets timestamp of when transaction was entered
   * @return
   */
  public Timestamp getCreated() {
    return created;
  }

  /**
   * Gives access to timestamp
   * @param created
   */
  public void setCreated(Timestamp created) {
    this.created = created;
  }

  /**
   * Gets the dispensary name purchased from
   * @return returns dispensary name
   */
  public String getPurchasedFrom() {
    return purchasedFrom;
  }

  /**
   * Gives access to purchased from
   * @param purchasedFrom
   */
  public void setPurchasedFrom(String purchasedFrom) {
    this.purchasedFrom = purchasedFrom;
  }

  /**
   * Gets the sub-transactions from the foreign collection
   * @return
   */
  public ForeignCollection<SubTransaction> getSubTransaction() {
    return subTransaction;
  }

  /**
   * Gives access to sub-transactions
   * @param subTransaction
   */
  public void setSubTransaction(
      ForeignCollection<SubTransaction> subTransaction) {
    this.subTransaction = subTransaction;
  }

  @Override
  public String toString() {
    return "TransactionDatabase{" +
        "id=" + id +
        ", created =" + created +
        ", purchased =" + purchasedDate +
        ", purchasedFrom ='" + purchasedFrom + '\'' +
        '}';
  }
}
