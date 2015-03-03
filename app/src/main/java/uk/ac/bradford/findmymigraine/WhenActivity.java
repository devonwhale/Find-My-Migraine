package uk.ac.bradford.findmymigraine;

import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;


public class WhenActivity extends ActionBarActivity {

    TextView attackDate, attackStart, attackEnd;
    Button nextButton;
    int whenYear, whenMonth, whenDay, startHour, startMinute, endHour, endMinute;
    Calendar whenDate, start, end;
    boolean time;                               //used to differentiate between the two time pickers

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_when);
        initialise();
        setOnClickListeners();
    }

    // Assign xml objects to the variables specified at the start of this Class.
    private void initialise(){
        //code to insert
        attackDate = (TextView)findViewById(R.id.pickWhenDate);
        attackStart = (TextView)findViewById(R.id.pickWhenStart);
        attackEnd = (TextView)findViewById(R.id.pickWhenEnd);
        nextButton = (Button)findViewById(R.id.btnUpdateWhen);

        whenDate = Calendar.getInstance();
        start = Calendar.getInstance();
        end = Calendar.getInstance();

    }

    private void setOnClickListeners(){

        attackDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Listener to create fragment.

                DialogFragment newFragment = new WhenDatePicker();
                newFragment.show(getFragmentManager(), "datePicker");

            }
        });
        attackStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time = false;
                //Listener to create fragment.
                DialogFragment newFragment = new WhenTimePicker();
                newFragment.show(getFragmentManager(), "timePicker");
            }
        });
        attackEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time = true;
                //Listener to create fragment.
                DialogFragment newFragment = new WhenTimePicker();
                newFragment.show(getFragmentManager(), "timePicker");
            }
        });
        //more code to add for what happens when nextButton is pressed ...........
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(attackDate.getText().toString().equalsIgnoreCase("Click here") || attackStart.getText().toString().equalsIgnoreCase("Click here") || attackEnd.getText().toString().equalsIgnoreCase("Click here")) {
                    Toast t = Toast.makeText(getApplicationContext(), "Please set a date, start and end time", Toast.LENGTH_LONG);
                    t.show();
                }
                else {
                    // Establish variable types used by the constructor
                    Long a, b, c;

                    //Set 'Date' in Long format for passing to constructor
                    whenDate.set(whenYear, whenMonth, whenDay);
                    c = whenDate.getTimeInMillis();


                    //Set times in Long format for passing to constructor
                    start.setTime(new Date(start.get(start.YEAR) - 1900, start.get(start.MONTH), start.get(start.DAY_OF_WEEK), startHour, startMinute));
                    end.setTime(new Date(end.get(end.YEAR) - 1900, end.get(end.MONTH), end.get(end.DAY_OF_WEEK), endHour, endMinute));
                    a = start.getTimeInMillis();
                    b = end.getTimeInMillis();


                    //Call constructor - Create a new When object
                    When w = new When(c, a, b);

                    //Create 'When' Data Access Object Instance
                    WhenDAO dao = new WhenDAO(WhenActivity.this);

                    //Enter 'when' record into database
                    dao.createWhenRecord(w);
                    Log.d("When ", "When Record Added");

                    /*Toast added by Steve to give feedback on submit.
                    The next three lines bring up a small 'toast' with the feedback text in the code.
                    The following two lines then return the user to the Daily Activity screen.
                    */
                    Toast feedback = Toast.makeText(getApplicationContext(), "Details Added to Migraine Records", Toast.LENGTH_LONG);
                    feedback.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
                    feedback.show();
                    Intent mv = new Intent(getApplicationContext(), AttackActivity.class);
                    startActivity(mv);
                }
            }
        });

    }

    public void setDate(int year, int month, int day){
        //code to add - method will be called by WhenDatePicker
        whenYear = year;
        whenMonth = month;
        whenDay = day;
        attackDate.setText(day+"/"+month+"/"+year);
        Toast.makeText(getBaseContext(), "Date set to "+day+"/"+month+"/"+year, Toast.LENGTH_LONG).show();
    }

    public void setTime(int hour, int min) {
        System.err.println("Time received");
        if(!time) {
            startMinute = min;
            startHour = hour;
            attackStart.setText(hour + ": " + min);
        }
        else if(time) {
            endMinute = min;
            endHour = hour;
            attackEnd.setText(hour + ": " + min);
        }
        Toast.makeText(getBaseContext(), "Time set: " + hour + ":" + min, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_when, menu);
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
