package edu.cnm.deepdive.nmmedicalcannabis.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.constraint.ConstraintLayout;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import edu.cnm.deepdive.nmmedicalcannabis.entities.PatientCard;
import edu.cnm.deepdive.nmmedicalcannabis.entities.Transactions;
import java.sql.SQLException;
import java.util.Calendar;

public class OrmHelper extends OrmLiteSqliteOpenHelper {

  private static final String DATABASE_NAME = "medicalcannabis.db";
  private static final int DATABASE_VERSION = 1;

  private Dao<PatientCard, Integer> patientCardDao = null;
  private Dao<Transactions, Integer> transactionsDao = null;

  public OrmHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
    try{
      TableUtils.createTable(connectionSource,PatientCard.class);
      TableUtils.createTable(connectionSource, Transactions.class);
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

  public synchronized Dao<PatientCard, Integer> getPatientCardDao() throws SQLException {
    if (patientCardDao == null) {
      patientCardDao = getDao(PatientCard.class);
    }
    return patientCardDao;
  }

  public synchronized Dao<Transactions, Integer> getTransactionsDao() throws SQLException {
    if (transactionsDao == null) {
      transactionsDao = getDao(Transactions.class);
    }
    return transactionsDao;
  }

  private void populateDatabase() throws SQLException {
    Calendar calendar = Calendar.getInstance();

      PatientCard patientCard = new PatientCard();
      patientCard.setCardID(2748483783949l);
      calendar.set(2017, 8, 9);
      patientCard.setIssueDate(calendar.getTime());
      calendar.set(2017, 7, 22);
      patientCard.setExpDate(calendar.getTime());
      patientCard.setUnitsAvailable(230);
      getPatientCardDao().create(patientCard);


    Transactions transactions = new Transactions();
    transactions.setPatientCard(patientCard);
    transactions.setUnitsPurchased(5);
    transactions.setPurchasedFrom("PureLife");
    transactions.setStrainName("Blue Dream");
    getTransactionsDao().create(transactions);
  }


  public interface OrmInteraction {
    OrmHelper getHelper();
  }
}
