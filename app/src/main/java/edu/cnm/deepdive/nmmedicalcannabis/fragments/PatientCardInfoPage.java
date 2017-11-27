package edu.cnm.deepdive.nmmedicalcannabis.fragments;

import static java.lang.Long.parseLong;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import edu.cnm.deepdive.nmmedicalcannabis.R;
import edu.cnm.deepdive.nmmedicalcannabis.entities.CardDatabase;
import edu.cnm.deepdive.nmmedicalcannabis.helpers.OrmHelper.OrmInteraction;
import edu.cnm.deepdive.nmmedicalcannabis.activities.NavigationActivity;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class PatientCardInfoPage extends Fragment implements OnClickListener {

  private OnFragmentInteractionListener mListener;

  private EditText cardNumber;
  private EditText issueDate;
  private EditText expDate;
  private EditText unitsAvailable;
  private CardDatabase mCardDatabase = null;


  public PatientCardInfoPage() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {


    try {
      mCardDatabase = ((OrmInteraction) getActivity()).getHelper().getPatientCardDao().queryForAll().get(0);
    } catch (SQLException e) {
      e.printStackTrace();
    } catch(IndexOutOfBoundsException e) {
      e.printStackTrace();
    }

    // Inflate the layout for this fragment
    View inflate = inflater.inflate(R.layout.id_card_info_page, container, false);

    inflate.findViewById(R.id.button_save_patient_id_page).setOnClickListener(this);
    inflate.findViewById(R.id.editIssueDate).setOnClickListener(this);

    cardNumber = inflate.findViewById(R.id.editCardNumber);
    issueDate = inflate.findViewById(R.id.editIssueDate);
    issueDate.setOnClickListener(this);
    expDate = inflate.findViewById(R.id.editExpDate);
    unitsAvailable = inflate.findViewById(R.id.editUnitsAvailable);

    if(mCardDatabase != null) {
      inflate.findViewById(R.id.button_save_patient_id_page).setEnabled(false);
      cardNumber.setText(Long.toString(mCardDatabase.getCardID()));
      issueDate.setEnabled(false);
      cardNumber.setEnabled(false);
      expDate.setEnabled(false);
      unitsAvailable.setEnabled(false);
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
      issueDate.setText(simpleDateFormat.format(mCardDatabase.getIssueDate()));
      expDate.setText(simpleDateFormat.format(mCardDatabase.getExpDate()));
      unitsAvailable.setText(Double.toString(mCardDatabase.getUnitsAvailable()));
    }

    return inflate;
  }


  @Override
  public void onDetach() {
    super.onDetach();
    mListener = null;
  }



  @Override
  public void onClick(View view) {

    if (view.getId() == R.id.button_save_patient_id_page) {



    try {
      if (mCardDatabase == null) {
        mCardDatabase = new CardDatabase();
      }

      mCardDatabase.setCardID(parseLong(cardNumber.getText().toString()));
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
      mCardDatabase.setIssueDate(simpleDateFormat.parse(issueDate.getText().toString()));
      mCardDatabase.setExpDate(simpleDateFormat.parse(expDate.getText().toString()));
      mCardDatabase.setUnitsAvailable(Integer.parseInt(unitsAvailable.getText().toString()));
      ((OrmInteraction) getActivity()).getHelper().getPatientCardDao().createOrUpdate(
          mCardDatabase);
    } catch (ParseException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    Intent intent = new Intent(getActivity(), NavigationActivity.class);
    startActivity(intent);
    }

    if (view.getId() == R.id.editIssueDate) {
      DialogFragment newFragment = new DatePickerFragment();
      newFragment.show(getFragmentManager(), "datePicker");
    }

  }



  public interface OnFragmentInteractionListener {

    // TODO: Update argument type and name
    void onFragmentInteraction(Uri uri);
  }
}
