package edu.cnm.deepdive.nmmedicalcannabis.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import edu.cnm.deepdive.nmmedicalcannabis.R;
import edu.cnm.deepdive.nmmedicalcannabis.entities.CardDatabase;
import edu.cnm.deepdive.nmmedicalcannabis.entities.ProductType;
import edu.cnm.deepdive.nmmedicalcannabis.entities.SubTransaction;
import edu.cnm.deepdive.nmmedicalcannabis.entities.TransactionDatabase;
import edu.cnm.deepdive.nmmedicalcannabis.fragments.DatePickerGeneral.Callback;
import edu.cnm.deepdive.nmmedicalcannabis.helpers.OrmHelper.OrmInteraction;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Creates a an alert dialog popup so users can enter each transaction.
 */
public class NewTransactionDialog extends DialogFragment implements View.OnClickListener {

  /**
   * Patient card ID field
   */
  public static final String PATIENT_CARD_ID = "patientCardId";
  private Callback callback;
  private ListView transactionList;
  private List<SubTransaction> transactionListModel = new ArrayList<>();
  private Spinner spinner;
  private AutoCompleteTextView strain;
  private EditText grams;
  private EditText date;
  private TextView totalGrams;
  private TextView totalUnits;

  /**
   * Creates class constructor
   */
  public NewTransactionDialog() {

  }

  /**
   * Creates a call back for the transaction dialog using params.
   * @param callback used to listen to changes being added to transactions
   */
  public void setCallback (Callback callback) {
    this.callback = callback;
  }

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {


    //Instantiates AlertDialog.Builder with its constructor
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    LayoutInflater inflater = getActivity().getLayoutInflater();
    View inflate = inflater.inflate(R.layout.new_transaction, null);

    //Instantiates dialog fields
    final AutoCompleteTextView dispensary = inflate.findViewById(R.id.autoCompleteDispensary);
    final AutoCompleteTextView strain = inflate.findViewById(R.id.autoCompleteStrain);
    final EditText grams = inflate.findViewById(R.id.editGramsNumber);
    transactionList = inflate.findViewById(R.id.listViewNewTransactions);
    inflate.findViewById(R.id.add_sub_transaction_button);

    List<ProductType> types = new ArrayList<>();

    try {
      types = ((OrmInteraction)getActivity()).getHelper().getProductTypeDao().queryForAll();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    spinner = inflate.findViewById(R.id.typeSpinner);
    this.strain = inflate.findViewById(R.id.autoCompleteStrain);
    this.grams = inflate.findViewById(R.id.editGramsNumber);
    date = inflate.findViewById(R.id.newPurchaseDate);
    totalGrams = inflate.findViewById(R.id.totalGrams);
    totalUnits = inflate.findViewById(R.id.totalUnits);

    inflate.findViewById(R.id.newPurchaseDate).setOnClickListener(this);
    inflate.findViewById(R.id.add_sub_transaction_button).setOnClickListener(this);

    ArrayAdapter<ProductType> adapter = new ArrayAdapter<ProductType>(getContext(), R.layout.support_simple_spinner_dropdown_item, types);
    spinner.setAdapter(adapter);

    //Setter methods to set the dialog characteristics
    builder.setView(inflate);

    //Creates onClickListener for save button
    builder.setPositiveButton(R.string.button_save, new OnClickListener() {
      //On click alert pops up with dialog fields
      @Override
      public void onClick(DialogInterface dialog, int which) {
        TransactionDatabase transactionDatabase = new TransactionDatabase();
        transactionDatabase.setPurchasedFrom(dispensary.getText().toString());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        try {
          transactionDatabase.setPurchasedDate(simpleDateFormat.parse(date.getText().toString()));
        } catch (ParseException e) {
          e.printStackTrace();
          Toast.makeText(getActivity(), "Invalid Date Format", Toast.LENGTH_SHORT);
          return;
        }

        //Uses Orm helper to add user input to database
        try {
          ((OrmInteraction)getActivity()).getHelper().getTransactionsDao().create(
              transactionDatabase);

          CardDatabase cardDatabase = ((OrmInteraction) getActivity()).getHelper()
              .getPatientCardDao().queryForAll().get(0);

          for (int i = 0; i < transactionListModel.size(); i++) {
            transactionListModel.get(i).setTransactionDatabase(transactionDatabase);
            ((OrmInteraction)getActivity()).getHelper().getSubTransactionsDao().create(transactionListModel.get(i));
            double units = transactionListModel.get(i).getGrams()*transactionListModel.get(i).getProductType().getMultiplier();
            cardDatabase.setUnitsAvailable(cardDatabase.getUnitsAvailable()-units);
          }

          //saves and updates units in database
          ((OrmInteraction) getActivity()).getHelper().getPatientCardDao().update(cardDatabase);

          if (callback != null) {
            callback.refreshList();
          }
        } catch (SQLException e) {
          e.printStackTrace();
        }

      }
    })
        //sets negative button to cancel button
        .setNegativeButton(R.string.cancel_button, new DialogInterface.OnClickListener() {

          //onClick user cancels dialog
          @Override
          public void onClick(DialogInterface dialog, int which) {
            NewTransactionDialog.this.getDialog().cancel();
          }
        });
    //Creates the alert builder
    return builder.create();
  }

  @Override
  public void onClick(View v) {

    switch (v.getId()) {
      case R.id.newPurchaseDate :
        final EditText dateText = (EditText) v;
        DatePickerGeneral picker = new DatePickerGeneral();
        picker.setCallback(new DatePickerGeneral.Callback() {
          @Override
          public void update(Date date) {
            DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
            dateText.setText(format.format(date));
          }
        });
        picker.show(getActivity().getSupportFragmentManager(), picker.getClass().getSimpleName());
        break;
      case R.id.add_sub_transaction_button:
        SubTransaction subTransaction = new SubTransaction();
        subTransaction.setProductType((ProductType) spinner.getSelectedItem());
        subTransaction.setStrain(strain.getText().toString());
        subTransaction.setGrams(Double.parseDouble(grams.getText().toString()));

        double grams = 0;
        double units = 0;

        transactionListModel.add(subTransaction);
        for(int i = 0; i < transactionListModel.size(); i++) {
          grams += transactionListModel.get(i).getGrams();
          units += transactionListModel.get(i).getGrams() * transactionListModel.get(i).getProductType().getMultiplier();
        }
        totalGrams.setText("" + grams);
        totalUnits.setText("" + units);
        ArrayAdapter arrayAdapter = new ArrayAdapter<SubTransaction>(getContext(), android.R.layout.simple_list_item_1, transactionListModel);
        transactionList.setAdapter(arrayAdapter);
        break;

    }

  }

  /**
   * refreshes the transaction history once activity is added.
   */
  public interface Callback {
    void refreshList();
  }
}

