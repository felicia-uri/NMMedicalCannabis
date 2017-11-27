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

/**
 * Creates the login page fragment.
 */
public class LoginPage extends Fragment implements OnClickListener {

  private OnFragmentInteractionListener mListener;

  private Button loginButton;

  /**
   * Login page constructor
   */
  public LoginPage() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for login fragment
    View inflate = inflater.inflate(R.layout.login_page, container, false);

    //Inflates loginButton and adds setonSetClickListener
    loginButton = inflate.findViewById(R.id.buttonLogin);
    loginButton.setOnClickListener(this);

    //Creates login page
    return inflate;
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

  /**
   * Creates a listener for the fragment to interact with activity.
   */
  public interface OnFragmentInteractionListener {

    // TODO: Update argument type and name
    void onFragmentInteraction(Uri uri);
  }
}
