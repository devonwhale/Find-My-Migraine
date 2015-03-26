package uk.ac.bradford.findmymigraine;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.widget.DatePicker;
import java.util.Calendar;

public class WhenDatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {

     /**
     * Creates the date picker - standard code apart from last statement
     * */
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
     * Handles the time being entered
     * */
    public void onDateSet(DatePicker view, int year, int month, int day) {
        System.err.println("Time set");
        ((MainActivity)getActivity()).setAttackDate(year, month, day);               //calls method in Parent Activity
        //new code:
        //Intent mv_att = new Intent(getApplicationContext(), WhenActivity.class);
        //startActivity(mv_att);
    }

}