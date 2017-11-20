package edu.cnm.deepdive.nmmedicalcannabis.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import edu.cnm.deepdive.nmmedicalcannabis.R;
import edu.cnm.deepdive.nmmedicalcannabis.fragments.LoginPage;
import edu.cnm.deepdive.nmmedicalcannabis.helpers.OrmHelper;
import edu.cnm.deepdive.nmmedicalcannabis.helpers.OrmHelper.OrmInteraction;

public class MainActivity extends AppCompatActivity implements OrmInteraction{

  private OrmHelper helper;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getHelper().getWritableDatabase().close();
    setContentView(R.layout.activity_main_fragment_container);

    getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new LoginPage()).commit();
  }

  @Override
  protected void onStart() {
    super.onStart();
    getHelper();
  }

  @Override
  protected void onStop() {
    releaseHelper();
    super.onStop();
  }

  @Override
  public synchronized OrmHelper getHelper() {
    if (helper == null) {
      helper = OpenHelperManager.getHelper(this, OrmHelper.class);
    }
    return helper;
  }

  public synchronized void releaseHelper() {
    if (helper != null) {
      OpenHelperManager.releaseHelper();
      helper = null;
    }
  }

}
