package uk.ac.bradford.findmymigraine;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class SleepActivity extends ActionBarActivity {

    TextView tvTimeToBed;
    TextView tvTimeUp;
    EditText etSleepRating;
    Button btnSubmit;

    static final int time_dialog_id_1 = 0;
    static final int time_dialog_id_2 = 1;
    int hour,minute,startHour, startMinute, endHour,endMinute;
    int year, month,day, currentYear, currentMonth, currentDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);

        tvTimeToBed = (TextView) findViewById(R.id.etTTB);
        tvTimeUp = (TextView) findViewById(R.id.etTU);
        etSleepRating = (EditText) findViewById(R.id.etSR);
        btnSubmit = (Button) findViewById(R.id.btnSub);

        tvTimeToBed.setText(Calendar.HOUR_OF_DAY + ": " + Calendar.MINUTE);
        tvTimeUp.setText(Calendar.HOUR_OF_DAY + ": " + Calendar.MINUTE);

        tvTimeToBed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "timePicker");
            }
        });

        tvTimeUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "timePicker");
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sleep s = new Sleep(Long.parseLong(tvTimeToBed.getText().toString()), Long.parseLong(tvTimeUp.getText().toString()), Integer.parseInt(etSleepRating.getText().toString()));
                //Create Sleep Data Access Object Instance
                SleepDAO dao = new SleepDAO(SleepActivity.this);
                //Enter sleep record into database
                dao.createSleepingRecord(s);
                Log.d("Sleep ", "Sleep Record Added");
                //Toast added by Steve to give feedback on submit
                Toast feedback = Toast.makeText(getApplicationContext(),"Details Added to Sleep Records", Toast.LENGTH_SHORT);
                feedback.setGravity(Gravity.TOP| Gravity.CENTER, 0,0);
                feedback.show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sleep, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Below are all the methods that have to do with Time Picker Dialogs
    private TimePickerDialog.OnTimeSetListener startTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int hourOfDay, int hour_minute) {
                    startHour = hourOfDay;
                    startMinute = hour_minute;
                    Toast.makeText(getBaseContext(), "Time set: " + startHour + ":" + startMinute, Toast.LENGTH_LONG).show();
                }
            };

    private TimePickerDialog.OnTimeSetListener endTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int hourOfDay, int hour_minute) {
                    endHour = hourOfDay;
                    endMinute = hour_minute;
                    Toast.makeText(getBaseContext(), "Time set: "+endHour+":"+endMinute, Toast.LENGTH_LONG).show();
                }
            };

    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case time_dialog_id_1:
                return new TimePickerDialog(this, startTimeSetListener,
                        hour, minute, true);

            case time_dialog_id_2:
                return new TimePickerDialog(this, endTimeSetListener,
                        hour, minute, true);
        }
            return null;
        }

}

