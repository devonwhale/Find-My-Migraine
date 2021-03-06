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
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;


/*
This Class creates the 'When did it happen?' record entry screen,
to record date, start time and end time. Then create a new database record.
 */
//This class connects this information with its counterpart in the XML and deals with information when a button is pressed
public class WhenActivity extends ActionBarActivity {

    TextView attackDate, attackStart, attackEnd;
    RatingBar howBad;
    Button nextButton;
    int whenYear, whenMonth, whenDay, startHour, startMinute, endHour, endMinute, intensity;
    Calendar whenDate, start, end;
    boolean time;                               //used to differentiate between the two time pickers
    //new variables for date extra -- added 26/3/15
    Calendar c;
    long c2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_when);
        initialise();
        setOnClickListeners();

        Bundle extra = getIntent().getExtras();
        if(extra != null) {
            c = Calendar.getInstance();
            c2 = extra.getLong("uk.ac.bradford.findmymigraine.date");
            c.setTimeInMillis(c2);
            attackDate = (TextView) findViewById(R.id.pickWhenDate);
            int displayMonth = c.get(Calendar.MONTH) + 1;
            attackDate.setText(c.get(Calendar.DATE)+"/"+displayMonth+"/"+c.get(Calendar.YEAR));
        }

    }

    // Assign xml objects to the variables specified at the start of this Class.
    private void initialise(){
        //code to insert
        attackDate = (TextView)findViewById(R.id.pickWhenDate);
        attackStart = (TextView)findViewById(R.id.pickWhenStart);
        attackEnd = (TextView)findViewById(R.id.pickWhenEnd);
        howBad = (RatingBar)findViewById(R.id.ratingBarIntensity);
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
                    Long a, b;
                    //, c;

                    //Set 'Date' in Long format for passing to constructor
                    //whenDate.set(whenYear, whenMonth, whenDay);
                    //c = whenDate.getTimeInMillis();


                    //Set times in Long format for passing to constructor
                    start.setTime(new Date(start.get(start.YEAR) - 1900, start.get(start.MONTH), start.get(start.DAY_OF_WEEK), startHour, startMinute));
                    end.setTime(new Date(end.get(end.YEAR) - 1900, end.get(end.MONTH), end.get(end.DAY_OF_WEEK), endHour, endMinute));
                    a = start.getTimeInMillis();
                    b = end.getTimeInMillis();
                    intensity = (int)howBad.getRating();


                    //Call constructor - Create a new When object
                    When w = new When(c2, a, b, intensity);

                    //Create 'When' Data Access Object Instance
                    WhenDAO dao = new WhenDAO(WhenActivity.this);

                    //Enter 'when' record into database
                    dao.createWhenRecord(w);
                    Log.d("When ", "When Object created:"+" dateLong: "+c2+", Start time (log): "+a+", End Time (long): "+b+", Intensity: "+intensity);

                    /*Toast added by Steve to give feedback on submit.
                    The next three lines bring up a small 'toast' with the feedback text in the code.
                    The following two lines then return the user to the Daily Activity screen.
                    */
                    Toast feedback = Toast.makeText(getApplicationContext(), "Details Added to Migraine Records", Toast.LENGTH_LONG);
                    feedback.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
                    feedback.show();
                    //old code to return to menu screen
                    /*
                    Intent mv = new Intent(getApplicationContext(), AttackActivity.class);
                    startActivity(mv); */

                    //new code added 26/3/15 to move to [ Coping ] screen (needs changing to Intensity when ready!
                    Intent mv = new Intent(getApplicationContext(), IntensityActivity.class);
                    mv.putExtra("uk.ac.bradford.findmymigraine.date", c2);
                    mv.putExtra("uk.ac.bradford.findmymigraine.start", a);
                    startActivity(mv);
                }
            }
        });

    }

    /*
    The following two methods are called by the WhenDatePicker and WhenTimePicker
      (fragment) classes. They set the variables above to the selected date/time(s).
     */
    public void setDate(int year, int month, int day){
        //code to add - method will be called by WhenDatePicker
        whenYear = year;
        whenMonth = month;
        whenDay = day;
        int displayMonthFigure = month + 1;
        attackDate.setText(day+"/"+displayMonthFigure+"/"+year);
        //attackDate.setTextColor(5);
        //nextButton.setTextColor(0);
        Toast.makeText(getBaseContext(), "Date set to "+day+"/"+displayMonthFigure+"/"+year, Toast.LENGTH_LONG).show();
    }

    public void setTime(int hour, int min) {
        System.err.println("Time received");
        String displayMinutes = Integer.toString(min);
        switch(min){
            case 0: displayMinutes = "00";break;
            case 1: displayMinutes = "01";break;
            case 2: displayMinutes = "02";break;
            case 3: displayMinutes = "03";break;
            case 4: displayMinutes = "04";break;
            case 5: displayMinutes = "05";break;
            case 6: displayMinutes = "06";break;
            case 7: displayMinutes = "07";break;
            case 8: displayMinutes = "08";break;
            case 9: displayMinutes = "09";break;
        }
        if(!time) {
            startMinute = min;
            startHour = hour;
            attackStart.setText(hour + ": " + displayMinutes);
        }
        else if(time) {
            endMinute = min;
            endHour = hour;
            attackEnd.setText(hour + ": " + displayMinutes);
        }
        Toast.makeText(getBaseContext(), "Time set: " + hour + ":" + displayMinutes, Toast.LENGTH_LONG).show();
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
