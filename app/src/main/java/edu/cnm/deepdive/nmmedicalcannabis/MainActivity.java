package edu.cnm.deepdive.nmmedicalcannabis;

import android.app.FragmentManager;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import edu.cnm.deepdive.nmmedicalcannabis.fragments.LoginPage;
import edu.cnm.deepdive.nmmedicalcannabis.fragments.LoginPage.OnFragmentInteractionListener;
import edu.cnm.deepdive.nmmedicalcannabis.fragments.PatientCardInfoPage;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new LoginPage()).commit();
  }

}
