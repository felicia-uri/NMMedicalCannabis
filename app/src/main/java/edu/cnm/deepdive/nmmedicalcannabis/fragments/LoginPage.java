package edu.cnm.deepdive.nmmedicalcannabis.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import edu.cnm.deepdive.nmmedicalcannabis.activities.NavigationActivity;
import edu.cnm.deepdive.nmmedicalcannabis.R;


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
    View inflate = inflater.inflate(R.layout.login_page, container, false);

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
    Intent intent = new Intent(getActivity(), NavigationActivity.class);
    startActivity(intent);
  }

  public interface OnFragmentInteractionListener {

    // TODO: Update argument type and name
    void onFragmentInteraction(Uri uri);
  }
}
