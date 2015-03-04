package uk.ac.bradford.findmymigraine;

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
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.lang.Override;
import java.util.Calendar;
import java.util.Date;
//just trying if push works --- imtanan
public class SleepActivity extends ActionBarActivity {

    //TextView dateWaking;                //added 4/3/15
    TextView tvTimeToBed;
    TextView tvTimeUp;
    Button btnNext;
    RatingBar ratingBar;

    //int year, month, day;
    int startHour, startMinute, endHour,endMinute;
    //Calendar wakeDate;
    Calendar start, end;
    boolean time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);
        initialise();
        setOnClickListeners();
    }

    /** Initialise components in XML layout*/
    private void initialise(){
        //dateWaking = (TextView)findViewById(R.id.et_sleep_date);    //added 4/3/15
        tvTimeToBed = (TextView) findViewById(R.id.etTTB);
        tvTimeUp = (TextView) findViewById(R.id.etTU);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        btnNext = (Button) findViewById(R.id.btnNext);

        start = Calendar.getInstance();
        end = Calendar.getInstance();
    }

    /** Set onClickListeners for components in XML layout*/
    private void setOnClickListeners() {
        /*dateWaking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Listener to create fragment.
                DialogFragment newFragment = new SleepDatePicker();
                newFragment.show(getFragmentManager(), "datePicker");
            }
        }); */


        tvTimeToBed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time = false;
                //Listener to create fragment.
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "timePicker");
            }
        });

        tvTimeUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time = true;
                //Listener to create fragment.
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "timePicker");
            }
        });



        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tvTimeToBed.getText().toString().equalsIgnoreCase("Click here") || tvTimeUp.getText().toString().equalsIgnoreCase("Click here")
                        //|| dateWaking.getText().toString().equalsIgnoreCase("Click here")
                        ) {
                    Toast t = Toast.makeText(getApplicationContext(), "Please set a date, start and end time", Toast.LENGTH_LONG);
                    t.show();
                }
                else {
                    Long a, b;
                    //Long c;
                    start.setTime(new Date(start.get(start.YEAR) - 1900, start.get(start.MONTH), start.get(start.DAY_OF_WEEK), startHour, startMinute));
                    end.setTime(new Date(end.get(end.YEAR) - 1900, end.get(end.MONTH), end.get(end.DAY_OF_WEEK), endHour, endMinute));
                    a = start.getTimeInMillis();
                    b = end.getTimeInMillis();

                    //Set 'Date' in Long format for passing to constructor
                    /*wakeDate.set(year, month, day);
                    c = wakeDate.getTimeInMillis(); */

                    //retrieve number of stars specifies by user in rating bar
                    int numStars = (int) ratingBar.getRating();

                    //Enter sleep details into database
                    Sleep s = new Sleep(
                            //c,
                            a, b, numStars);

                    //Create Sleep Data Access Object Instance
                    SleepDAO dao = new SleepDAO(SleepActivity.this);
                    //Enter sleep record into database
                    dao.createSleepingRecord(s);
                    Log.d("Sleep ", "Sleep Record Added");
                /*Toast added by Steve to give feedback on submit.
                    The next three lines bring up a small 'toast' with the feedback text in the code.
                    The following two lines then return the user to the Daily Activity screen.
                 */
                    Toast feedback = Toast.makeText(getApplicationContext(), "Details Added to Sleep Records", Toast.LENGTH_LONG);
                    feedback.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
                    feedback.show();
                    Intent mv = new Intent(getApplicationContext(), ExerciseActivity.class);
                    startActivity(mv);
                }
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

    public void setTime(int hour, int min) {
        System.err.println("Time received");
        if(!time) {
            startMinute = min;
            startHour = hour;
            tvTimeToBed.setText(hour + ": " + min);
            }
        else if(time) {
            endMinute = min;
            endHour = hour;
            tvTimeUp.setText(hour + ": " + min);
            }
        Toast.makeText(getBaseContext(), "Time set: "+hour+":"+min, Toast.LENGTH_LONG).show();
        }

    /*
    public void setDate(int year, int month, int day){
        //method called by SleepDatePicker
        this.year = year;
        this.month = month;
        this.day = day;
        int displayMonthFigure = month + 1;
        dateWaking.setText(day+"/"+displayMonthFigure+"/"+year);           //month integer increased by 1.
        Toast.makeText(getBaseContext(), "Date set to "+day+"/"+displayMonthFigure+"/"+year, Toast.LENGTH_LONG).show();

    } */
}

