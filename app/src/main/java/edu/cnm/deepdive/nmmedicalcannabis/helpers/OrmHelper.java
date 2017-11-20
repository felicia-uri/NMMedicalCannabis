package edu.cnm.deepdive.nmmedicalcannabis.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import edu.cnm.deepdive.nmmedicalcannabis.entities.PatientCardDatabaseTable;
import edu.cnm.deepdive.nmmedicalcannabis.entities.TransactionDatabaseTable;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

public class OrmHelper extends OrmLiteSqliteOpenHelper {

  private static final String DATABASE_NAME = "medicalcannabis.db";
  private static final int DATABASE_VERSION = 1;

  private Dao<PatientCardDatabaseTable, Integer> patientCardDao = null;
  private Dao<TransactionDatabaseTable, Integer> transactionsDao = null;

  public OrmHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
    try{
      TableUtils.createTable(connectionSource,PatientCardDatabaseTable.class);
      TableUtils.createTable(connectionSource, TransactionDatabaseTable.class);
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

  public synchronized Dao<PatientCardDatabaseTable, Integer> getPatientCardDao() throws SQLException {
    if (patientCardDao == null) {
      patientCardDao = getDao(PatientCardDatabaseTable.class);
    }
    return patientCardDao;
  }

  public synchronized Dao<TransactionDatabaseTable, Integer> getTransactionsDao() throws SQLException {
    if (transactionsDao == null) {
      transactionsDao = getDao(TransactionDatabaseTable.class);
    }
    return transactionsDao;
  }

  private void populateDatabase() throws SQLException {
    Calendar calendar = Calendar.getInstance();

      PatientCardDatabaseTable patientCardDatabaseTable = new PatientCardDatabaseTable();
//      patientCardDatabaseTable.setCardID();
//      calendar.set(2017, 8, 9);
//      patientCardDatabaseTable.setIssueDate(calendar.getTime());
//      calendar.set(2017, 7, 22);
//      patientCardDatabaseTable.setExpDate(calendar.getTime());
//      patientCardDatabaseTable.setUnitsAvailable(230);
//      getPatientCardDao().create(patientCardDatabaseTable);


//    TransactionDatabaseTable transactionDatabaseTable = new TransactionDatabaseTable();
//    transactionDatabaseTable.setPatientCardDatabaseTable(patientCardDatabaseTable);
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
