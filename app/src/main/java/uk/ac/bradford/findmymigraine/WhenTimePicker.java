package uk.ac.bradford.findmymigraine;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.format.DateFormat;
import android.widget.TimePicker;
import java.util.Calendar;

public class WhenTimePicker extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    boolean start;

    /**
     * Creates the time picker
     * */
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
    }

    /**
     * Handles the time being entered
     * */
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        System.err.println("Time set");
        ((WhenActivity)getActivity()).setTime(hourOfDay, minute);               //calls method in Parent Activity

    }

}