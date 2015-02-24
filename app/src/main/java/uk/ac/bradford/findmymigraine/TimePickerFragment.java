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

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

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
        ((SleepActivity)getActivity()).setTime(hourOfDay, minute);
        /*Intent i = new Intent("time_set");
        i.setAction("UK.AC.BRADFORD.FINDMYMIGRAINE.TIME_SET");
        i.putExtra("start", true);
        i.putExtra("hourOfDay", hourOfDay);
        i.putExtra("minute", minute);
        LocalBroadcastManager.getInstance(this.getActivity()).sendBroadcast(i);*/
    }

}
