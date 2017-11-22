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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import edu.cnm.deepdive.nmmedicalcannabis.R;
import edu.cnm.deepdive.nmmedicalcannabis.entities.ProductType;
import edu.cnm.deepdive.nmmedicalcannabis.entities.SubTransaction;
import edu.cnm.deepdive.nmmedicalcannabis.entities.TransactionDatabase;
import edu.cnm.deepdive.nmmedicalcannabis.helpers.OrmHelper.OrmInteraction;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class NewTransactionDialog extends DialogFragment implements View.OnClickListener {

  public static final String PATIENT_CARD_ID = "patientCardId";
  private Callback callback;
  private ListView transactionList;
  private List<SubTransaction> transactionListModel = new ArrayList<>();
  private Spinner spinner;
  private AutoCompleteTextView strain;
  private EditText grams;
  private EditText date;

  public NewTransactionDialog() {

  }

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
    inflate.findViewById(R.id.add_subtransaction_button);

    List<ProductType> types = new ArrayList<>();

    try {
      types = ((OrmInteraction)getActivity()).getHelper().getProductTypeDao().queryForAll();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    spinner = inflate.findViewById(R.id.typeSpinner);
    this.strain = inflate.findViewById(R.id.autoCompleteStrain);
    this.grams = inflate.findViewById(R.id.editGramsNumber);
    date = inflate.findViewById(R.id.calendarEdit);

    inflate.findViewById(R.id.add_subtransaction_button).setOnClickListener(this);

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
        }

        //Uses Orm helper to add user input to database
        try {
          ((OrmInteraction)getActivity()).getHelper().getTransactionsDao().create(
              transactionDatabase);
          for (int i = 0; i < transactionListModel.size(); i++) {
            transactionListModel.get(i).setTransactionDatabase(transactionDatabase);
            ((OrmInteraction)getActivity()).getHelper().getSubTransactionsDao().create(transactionListModel.get(i));
          }
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
    SubTransaction subTransaction = new SubTransaction();
    subTransaction.setProductType((ProductType) spinner.getSelectedItem());
    subTransaction.setStrain(strain.getText().toString());
    subTransaction.setGrams(Double.parseDouble(grams.getText().toString()));

    transactionListModel.add(subTransaction);
    ArrayAdapter arrayAdapter = new ArrayAdapter<SubTransaction>(getContext(), android.R.layout.simple_list_item_1, transactionListModel);
    transactionList.setAdapter(arrayAdapter);

  }

  public interface Callback {
    void refreshList();
  }
}

