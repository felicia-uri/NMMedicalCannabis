package edu.cnm.deepdive.nmmedicalcannabis.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import edu.cnm.deepdive.nmmedicalcannabis.R;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Provides widget for selecting date.
 */
public class DatePickerFragment extends DialogFragment
    implements DatePickerDialog.OnDateSetListener {

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    // Use the current date as the default date in the picker
    final Calendar c = Calendar.getInstance();
    int year = c.get(Calendar.YEAR);
    int month = c.get(Calendar.MONTH);
    int day = c.get(Calendar.DAY_OF_MONTH);

    // Create a new instance of DatePickerDialog and return it
    return new DatePickerDialog(getActivity(), this, year, month, day);
  }

  /**
   * The listener used to indicate the user has finished selecting a date.
   *
   * @param view the picker associated with the dialog
   * @param year the selected year
   * @param month the selected month(0-11 for compatibility with MONTH)
   * @param day the selected day of the mon ( 1-31, depending on month)
   */
  public void onDateSet(DatePicker view, int year, int month, int day) {
    EditText issueDate = getActivity().findViewById(R.id.editIssueDate);
    EditText expDate = getActivity().findViewById(R.id.editExpDate);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
    Calendar cal = Calendar.getInstance();
    cal.set(year+1, month, day);
    expDate.setText(simpleDateFormat.format(cal.getTime()));
    cal.set(year, month, day);
    issueDate.setText(simpleDateFormat.format(cal.getTime()));
  }
}
