package edu.cnm.deepdive.nmmedicalcannabis.entities;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import java.sql.Timestamp;
import java.util.Date;

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

  public Date getPurchasedDate() {
    return purchasedDate;
  }

  public void setPurchasedDate(Date purchasedDate) {
    this.purchasedDate = purchasedDate;
  }

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

  public String getPurchasedFrom() {
    return purchasedFrom;
  }

  public void setPurchasedFrom(String purchasedFrom) {
    this.purchasedFrom = purchasedFrom;
  }

  public ForeignCollection<SubTransaction> getSubTransaction() {
    return subTransaction;
  }

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
