package uk.ac.bradford.findmymigraine;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.lang.Override;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class SleepActivity extends ActionBarActivity {

    TextView tvTimeToBed;
    TextView tvTimeUp;
    Button btnSubmit;
    RatingBar ratingBar;

    int startHour, startMinute, endHour,endMinute;
    Calendar start, end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);
        initialise();
        setOnClickListeners();
    }

    /** Initialise components in XML layout*/
    private void initialise(){
        tvTimeToBed = (TextView) findViewById(R.id.etTTB);
        tvTimeUp = (TextView) findViewById(R.id.etTU);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        btnSubmit = (Button) findViewById(R.id.btnSub);

        start = Calendar.getInstance();
        end = Calendar.getInstance();
    }

    /** Set onClickListeners for components in XML layout*/
    private void setOnClickListeners(){
        tvTimeToBed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Listener to create fragment and watch it. Captures the time in variables startHour and startMinute
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "timePicker");
                TimePickerDialog.OnTimeSetListener startTimeSetListener =
                        new TimePickerDialog.OnTimeSetListener() {
                            public void onTimeSet(TimePicker view, int hourOfDay, int hour_minute) {
                                startHour = hourOfDay;
                                startMinute = hour_minute;
                                Toast.makeText(getBaseContext(), "Time set: " + startHour + ":" + startMinute, Toast.LENGTH_LONG).show();
                                tvTimeToBed.setText(startHour + ": " + startMinute);
                            }
                        };
            }
        });

        tvTimeUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Listener to create fragment and watch it. Captures the time in variables endHour and endMinute
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "timePicker");
                TimePickerDialog.OnTimeSetListener endTimeSetListener =
                        new TimePickerDialog.OnTimeSetListener() {
                            public void onTimeSet(TimePicker view, int hourOfDay, int hour_minute) {
                                endHour = hourOfDay;
                                endMinute = hour_minute;
                                Toast.makeText(getBaseContext(), "Time set: "+endHour+":"+endMinute, Toast.LENGTH_LONG).show(); //Not working !!!
                                tvTimeUp.setText(endHour + ": " + endMinute); //Not working !!!
                            }
                        };
            }
        });



        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long a, b;
                start.setTime(new Date(start.get(start.YEAR) - 1900, start.get(start.MONTH), start.get(start.DAY_OF_WEEK), startHour, startMinute));
                end.setTime(new Date(end.get(end.YEAR) - 1900, end.get(end.MONTH), end.get(end.DAY_OF_WEEK), endHour, endMinute));
                a = start.getTimeInMillis();
                b = end.getTimeInMillis();

                //retrieve number of stars specifies bu user in rating bar
                int numStars=(int)ratingBar.getRating();

                //Enter sleep details into database
                Sleep s = new Sleep(a, b, numStars);

                //Create Sleep Data Access Object Instance
                SleepDAO dao = new SleepDAO(SleepActivity.this);
                //Enter sleep record into database
                dao.createSleepingRecord(s);
                Log.d("Sleep ", "Sleep Record Added");
                /*Toast added by Steve to give feedback on submit.
                    The next three lines bring up a small 'toast' with the feedback text in the code.
                    The following two lines then return the user to the Daily Activity screen.
                 */
                Toast feedback = Toast.makeText(getApplicationContext(),"Details Added to Sleep Records", Toast.LENGTH_LONG);
                feedback.setGravity(Gravity.CENTER| Gravity.CENTER, 0,0);
                feedback.show();
                Intent mv = new Intent(getApplicationContext(), DailyActivity.class);
                startActivity(mv);
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
}

