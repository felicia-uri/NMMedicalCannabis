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
import edu.cnm.deepdive.nmmedicalcannabis.entities.PatientCard;
import edu.cnm.deepdive.nmmedicalcannabis.helpers.OrmHelper.OrmInteraction;
import edu.cnm.deepdive.nmmedicalcannabis.navigation.NavMenu;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class PatientCardInfoPage extends Fragment implements OnClickListener{

  private OnFragmentInteractionListener mListener;

  private EditText cardNumber;
  private EditText issueDate;
  private EditText expDate;
  private EditText unitsAvailable;



  public PatientCardInfoPage() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View inflate = inflater.inflate(R.layout.fragment_patient_card_info_page, container, false);

    inflate.findViewById(R.id.button_save_patient_id_page).setOnClickListener(this);
    cardNumber = inflate.findViewById(R.id.editCardNumber);
    issueDate = inflate.findViewById(R.id.editIssueDate);
    expDate = inflate.findViewById(R.id.editExpDate);
    unitsAvailable = inflate.findViewById(R.id.editUnitsAvailable);

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
      PatientCard patientcard = new PatientCard();
      patientcard.setCardID(parseLong(cardNumber.getText().toString()));
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
      patientcard.setIssueDate(simpleDateFormat.parse(issueDate.getText().toString()));
      patientcard.setExpDate(simpleDateFormat.parse(expDate.getText().toString()));
      patientcard.setUnitsAvailable(Integer.parseInt(unitsAvailable.getText().toString()));
      ((OrmInteraction) getActivity()).getHelper().getPatientCardDao().create(patientcard);
    } catch (ParseException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    Intent intent = new Intent(getActivity(), NavMenu.class);
  startActivity(intent);
  }

  public interface OnFragmentInteractionListener {

    // TODO: Update argument type and name
    void onFragmentInteraction(Uri uri);
  }
}
