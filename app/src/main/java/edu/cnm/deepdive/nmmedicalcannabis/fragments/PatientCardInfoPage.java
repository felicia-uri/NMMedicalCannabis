package edu.cnm.deepdive.nmmedicalcannabis.fragments;

import static java.lang.Long.parseLong;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import edu.cnm.deepdive.nmmedicalcannabis.R;
import edu.cnm.deepdive.nmmedicalcannabis.entities.PatientCardDatabaseTable;
import edu.cnm.deepdive.nmmedicalcannabis.helpers.OrmHelper.OrmInteraction;
import edu.cnm.deepdive.nmmedicalcannabis.activities.NavigationActivity;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class PatientCardInfoPage extends Fragment implements OnClickListener{

  private OnFragmentInteractionListener mListener;

  private EditText cardNumber;
  private EditText issueDate;
  private EditText expDate;
  private EditText unitsAvailable;
  private PatientCardDatabaseTable mPatientCardDatabaseTable = null;


  public PatientCardInfoPage() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {


    try {
      mPatientCardDatabaseTable = ((OrmInteraction) getActivity()).getHelper().getPatientCardDao().queryForAll().get(0);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    // Inflate the layout for this fragment
    View inflate = inflater.inflate(R.layout.id_card_info_page, container, false);

    inflate.findViewById(R.id.button_save_patient_id_page).setOnClickListener(this);

    cardNumber = inflate.findViewById(R.id.editCardNumber);
    issueDate = inflate.findViewById(R.id.editIssueDate);
    expDate = inflate.findViewById(R.id.editExpDate);
    unitsAvailable = inflate.findViewById(R.id.editUnitsAvailable);

    if(mPatientCardDatabaseTable != null) {
      inflate.findViewById(R.id.button_save_patient_id_page).setEnabled(false);
      cardNumber.setText(Long.toString(mPatientCardDatabaseTable.getCardID()));
      issueDate.setEnabled(false);
      cardNumber.setEnabled(false);
      expDate.setEnabled(false);
      unitsAvailable.setEnabled(false);
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
      issueDate.setText(simpleDateFormat.format(mPatientCardDatabaseTable.getIssueDate()));
      expDate.setText(simpleDateFormat.format(mPatientCardDatabaseTable.getExpDate()));
      unitsAvailable.setText(Integer.toString(mPatientCardDatabaseTable.getUnitsAvailable()));
    }

    return inflate;
  }

  // TODO: Rename method, update argument and hook method into UI event
  public void onButtonPressed(Uri uri) {
    if (mListener != null) {
      mListener.onFragmentInteraction(uri);
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mListener = null;
  }



  @Override
  public void onClick(View view) {
    try {
      if(mPatientCardDatabaseTable == null) {
        mPatientCardDatabaseTable = new PatientCardDatabaseTable();
      }

      mPatientCardDatabaseTable.setCardID(parseLong(cardNumber.getText().toString()));
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
      mPatientCardDatabaseTable.setIssueDate(simpleDateFormat.parse(issueDate.getText().toString()));
      mPatientCardDatabaseTable.setExpDate(simpleDateFormat.parse(expDate.getText().toString()));
      mPatientCardDatabaseTable.setUnitsAvailable(Integer.parseInt(unitsAvailable.getText().toString()));
      ((OrmInteraction) getActivity()).getHelper().getPatientCardDao().createOrUpdate(
          mPatientCardDatabaseTable);
    } catch (ParseException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    Intent intent = new Intent(getActivity(), NavigationActivity.class);
  startActivity(intent);
  }

  public interface OnFragmentInteractionListener {

    // TODO: Update argument type and name
    void onFragmentInteraction(Uri uri);
  }
}
