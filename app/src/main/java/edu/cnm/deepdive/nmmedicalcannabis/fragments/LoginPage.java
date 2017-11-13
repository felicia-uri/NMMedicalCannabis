package edu.cnm.deepdive.nmmedicalcannabis.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import edu.cnm.deepdive.nmmedicalcannabis.R;
import edu.cnm.deepdive.nmmedicalcannabis.fragments.PatientCardInfoPage.OnFragmentInteractionListener;


public class LoginPage extends Fragment implements OnClickListener {

  private OnFragmentInteractionListener mListener;

  private Button loginButton;

  public LoginPage() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View inflate = inflater.inflate(R.layout.fragment_login_page, container, false);

    loginButton = inflate.findViewById(R.id.buttonLogin);
    loginButton.setOnClickListener(this);

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
    getFragmentManager().beginTransaction().replace(R.id.fragment_container, new PatientCardInfoPage()).commit();
  }

  public interface OnFragmentInteractionListener {

    // TODO: Update argument type and name
    void onFragmentInteraction(Uri uri);
  }
}
