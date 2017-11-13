package edu.cnm.deepdive.nmmedicalcannabis.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import edu.cnm.deepdive.nmmedicalcannabis.R;
import edu.cnm.deepdive.nmmedicalcannabis.activites.TransactionActivity;


public class PatientCardInfoPage extends Fragment implements OnClickListener{

  private OnFragmentInteractionListener mListener;



  public PatientCardInfoPage() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View inflate = inflater.inflate(R.layout.fragment_patient_card_info_page, container, false);

    inflate.findViewById(R.id.button_save).setOnClickListener(this);



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
  Intent intent = new Intent(getActivity(), TransactionActivity.class);
  startActivity(intent);
  }

  public interface OnFragmentInteractionListener {

    // TODO: Update argument type and name
    void onFragmentInteraction(Uri uri);
  }
}
