package edu.cnm.deepdive.nmmedicalcannabis.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.telecom.Call;
import android.widget.DatePicker;
import android.widget.EditText;
import edu.cnm.deepdive.nmmedicalcannabis.R;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DatePickerGeneral extends DialogFragment
    implements DatePickerDialog.OnDateSetListener{

  private Callback callback = null;

  public void setCallback(Callback callback) {
    this.callback = callback;
  }

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


  @Override
  public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//    EditText newPurchaseDate = getActivity().findViewById(R.id.newPurchaseDate);
//    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
    if (callback != null) {
      Calendar cal = Calendar.getInstance();
      cal.set(year, month, dayOfMonth);
      callback.update(cal.getTime());
    }

//    newPurchaseDate.setText(simpleDateFormat.format(cal.getTime()));
  }

  public interface Callback {
    void update(Date date);
  }
}
