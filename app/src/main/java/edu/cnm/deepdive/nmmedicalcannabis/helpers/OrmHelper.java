package edu.cnm.deepdive.nmmedicalcannabis.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import edu.cnm.deepdive.nmmedicalcannabis.entities.CardDatabase;
import edu.cnm.deepdive.nmmedicalcannabis.entities.ProductType;
import edu.cnm.deepdive.nmmedicalcannabis.entities.TransactionDatabase;
import java.sql.SQLException;
import java.util.Calendar;

public class OrmHelper extends OrmLiteSqliteOpenHelper {

  private static final String DATABASE_NAME = "medicalcannabis.db";
  private static final int DATABASE_VERSION = 1;

  private Dao<CardDatabase, Integer> patientCardDao = null;
  private Dao<TransactionDatabase, Integer> transactionsDao = null;
  private Dao<ProductType, Integer> productTypeDao;

  public OrmHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
    try{
      TableUtils.createTable(connectionSource,CardDatabase.class);
      TableUtils.createTable(connectionSource, TransactionDatabase.class);
      TableUtils.createTable(connectionSource, ProductType.class);
      populateDatabase();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

  }

  @Override
  public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion,
      int newVersion) {

  }

  @Override
  public void close() {

  }

  public synchronized Dao<CardDatabase, Integer> getPatientCardDao() throws SQLException {
    if (patientCardDao == null) {
      patientCardDao = getDao(CardDatabase.class);
    }
    return patientCardDao;
  }

  public synchronized Dao<TransactionDatabase, Integer> getTransactionsDao() throws SQLException {
    if (transactionsDao == null) {
      transactionsDao = getDao(TransactionDatabase.class);
    }
    return transactionsDao;
  }

  public synchronized Dao<ProductType, Integer> getProductTypeDao() throws SQLException {
    if (productTypeDao == null) {
      productTypeDao = getDao(ProductType.class);
    }
    return productTypeDao;

  }

  private void populateDatabase() throws SQLException {
    Calendar calendar = Calendar.getInstance();

      CardDatabase cardDatabase = new CardDatabase();
      cardDatabase.getCardID();

      ProductType productType = new ProductType();
      productType.setMultiplier(1);
      productType.setName("Flower");
      getProductTypeDao().create(productType);

      productType = new ProductType();
      productType.setMultiplier(3);
      productType.setName("Concentrate");
      getProductTypeDao().create(productType);

      productType = new ProductType();
      productType.setMultiplier(0.5);
      productType.setName("Edible");
      getProductTypeDao().create(productType);

      
//      cardDatabase.setCardID();
//      calendar.set(2017, 8, 9);
//      cardDatabase.setIssueDate(calendar.getTime());
//      calendar.set(2017, 7, 22);
//      cardDatabase.setExpDate(calendar.getTime());
//      cardDatabase.setUnitsAvailable(230);
//      getPatientCardDao().create(cardDatabase);


//    TransactionDatabase transactionDatabaseTable = new TransactionDatabase();
//    transactionDatabaseTable.setPatientCardDatabaseTable(cardDatabase);
//    transactionDatabaseTable.setPurchasedDate(new Date());
//    transactionDatabaseTable.setUnitsPurchased(5);
//    transactionDatabaseTable.setPurchasedFrom("PureLife");
//    transactionDatabaseTable.setStrainName("Blue Dream");
//    getTransactionsDao().create(transactionDatabaseTable);
  }


  public interface OrmInteraction {
    OrmHelper getHelper();
  }
}
