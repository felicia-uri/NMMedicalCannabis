package edu.cnm.deepdive.nmmedicalcannabis.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import edu.cnm.deepdive.nmmedicalcannabis.R;

public class NewTransactionDialog extends DialogFragment {


  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    LayoutInflater inflater = getActivity().getLayoutInflater();

    builder.setView(inflater.inflate(R.layout.new_transaction, null));
    builder.setPositiveButton(R.string.button_save, new OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        //save information
      }
    })
        .setNegativeButton(R.string.cancel_button, new DialogInterface.OnClickListener() {

          @Override
          public void onClick(DialogInterface dialog, int which) {
            NewTransactionDialog.this.getDialog().cancel();
          }
        });
    return builder.create();
  }
}

