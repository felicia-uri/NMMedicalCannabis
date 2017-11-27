package edu.cnm.deepdive.nmmedicalcannabis.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import edu.cnm.deepdive.nmmedicalcannabis.R;
import edu.cnm.deepdive.nmmedicalcannabis.fragments.PatientCardInfoPage;
import edu.cnm.deepdive.nmmedicalcannabis.fragments.TransactionsPage;
import edu.cnm.deepdive.nmmedicalcannabis.helpers.OrmHelper;
import edu.cnm.deepdive.nmmedicalcannabis.helpers.OrmHelper.OrmInteraction;

/**
 * Creates a container fragment for each view in the navigation menu.
 */
public class NavigationActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener, OrmInteraction {

  private OrmHelper helper;

  @Override
  protected void onCreate(Bundle savedInstanceState) { //Preforms initialization of all fragments and loaders

    super.onCreate(savedInstanceState); //onCreate constructor

    setContentView(R.layout.navigation_drawer); //calls navigation drawer view

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);//calls toolbar
    setSupportActionBar(toolbar); //Set toolbar to act as ActionBar for this Activity (NavagationAvtivity)


     // Creates floating action button instance
     // Uses add_transaction_button ID
    FloatingActionButton addTransactionButton = (FloatingActionButton) findViewById(R.id.add_transaction_button);

    /**
     * Sets on set click listener for the add button on the transaction page.
     * Add button will open an alert dialog which allows users to add each transaction.
     */
    addTransactionButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
//        AlertDialog.Builder builder =new AlertDialog(R.id.NavigationActivity.this))
//        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//            .setAction("Action", null).show();
      }
    });

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.setDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
    navigationView.setNavigationItemSelectedListener(this);

    PatientCardInfoPage patientCardInfoPage = new PatientCardInfoPage();
    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_nav_container, patientCardInfoPage).commit();

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

  /**
   * Allows Navigation Activity to use OrmHelper class
   */
  public synchronized void releaseHelper() {
    if (helper != null) {
      OpenHelperManager.releaseHelper();
      helper = null;
    }
  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

//  @Override
//  public boolean onCreateOptionsMenu(Menu menu) {
//    // Inflate the menu; this adds items to the action bar if it is present.
//    getMenuInflater().inflate(R.menu.menu_main, menu);
//    return true;
//  }

//  @Override
//  public boolean onOptionsItemSelected(MenuItem item) {
//    // Handle action bar item clicks here. The action bar will
//    // automatically handle clicks on the Home/Up button, so long
//    // as you specify a parent activity in AndroidManifest.xml.
//    int id = item.getItemId();
//
//
//    //noinspection SimplifiableIfStatement
//    if (id == R.id.action_settings) {
//      return true;
//    }
//
//    return super.onOptionsItemSelected(item);
//  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {

    // Handle navigation view item clicks here.
    int id = item.getItemId();



    switch (id) {
      case R.id.menu_patient_card_info_page:
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_nav_container, new PatientCardInfoPage()).commit();
        break;
      case R.id.menu_transactions_page :
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_nav_container, new TransactionsPage()).commit();
        break;
    }



    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }
}
