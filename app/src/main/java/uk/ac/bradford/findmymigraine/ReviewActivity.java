package uk.ac.bradford.findmymigraine;


import android.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Calendar;


/*
Class for Displaying Information on Screen for a given date (chosen by the user)
 returned from the SQLite database.
 Written by Steve 4/3/15
 */
public class ReviewActivity extends ActionBarActivity {

    //Variables for fields being displayed on screen
    Button selectDate;
    TextView selectedDate, sleepTTB, sleepTU, hoursSlept, sleepRating, exDuration, exIntensity, travelDuration, travelMethod, travelDest, moodRating;
    int year, month, day;
    Calendar theDate;
    TableLayout exerciseTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        initialise();
        addButtonListener();
    }



    /* Code needs to:
     - Launch a date picker dialog when 'select date' button pressed
     - Save the selected date to a variable which is used for SQL statements
     - Call DAO SQL queries to get rows for date selected
     - display these somehow on the screen (draft up some sample layouts first)
     */
    //link variables to the xml form to display information retrieved from Database
    private void initialise(){
        selectDate = (Button)findViewById(R.id.a_r_button_selectDate);
        selectedDate = (TextView)findViewById(R.id.selected_date);
        //sleepTTB = (TextView)findViewById(R.id.ttb_details);
        //sleepTU = (TextView)findViewById(R.id.tu_details);
        hoursSlept = (TextView)findViewById(R.id.hoursSlept_details);
        sleepRating = (TextView)findViewById(R.id.sr_details);
        //exDuration = (TextView)findViewById(R.id.ex_hours);
        //exIntensity = (TextView)findViewById(R.id.ex_intensity);
        travelDuration = (TextView)findViewById(R.id.travel_duration);
        travelMethod = (TextView)findViewById(R.id.travel_method);
        travelDest = (TextView)findViewById(R.id.travel_destination);
        moodRating = (TextView)findViewById(R.id.mood_rating);
        exerciseTable = (TableLayout)findViewById(R.id.exercise_table);
    }

    private void addButtonListener(){
        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Listener to create fragment.

                DialogFragment newFragment = new ReviewDatePicker();
                newFragment.show(getFragmentManager(), "datePicker");

            }
        });
    }

    public void setDate(int year, int month, int day){
        //method called by ReviewDatePicker
        this.year = year;
        this.month = month;
        this.day = day;
        int displayMonth = month+1;
        selectedDate.setText("Records for: "+day+"/"+displayMonth+"/"+year);

    }

    //Method sets all the values on the screen to the selected date
    public void setValues(){
        //initialise Calendar
        theDate = Calendar.getInstance();
        //Convert theDate to Long for passing to Data Access Object(s)
        theDate.set(year, month, day,0,0,0);
        Long longDate = (theDate.getTimeInMillis());
        Log.d("Date queried", longDate.toString());
        //Long longDate = 1425897750596L;                 //TEMP IN PLACE OF ABOVE LINE FOR TEST

        //Sleep Values for selected date
            //new sleep and sleepDAO objects
            Sleep sleep = new Sleep();
            SleepDAO sleepDAO = new SleepDAO(ReviewActivity.this);
            //get values for selected date as a sleep object
            sleep = sleepDAO.getSleepRecordForDate(longDate); //returns a sleep record
            //sleep = sleepDAO.getSleepingRecord(1);      //TEST code INSTEAD OF LINE ABOVE
            //display returned values in Android text views (values in sleep are longs (in millisecs...need to convert to times)

                /*
                //Time to bed
                Calendar calTTB = Calendar.getInstance();
                calTTB.setTimeInMillis(sleep.getTimeToBed());
                sleepTTB.setText(calTTB.get(Calendar.HOUR_OF_DAY)+":"+calTTB.get(Calendar.MINUTE));
                //sleepTTB.setText(Long.toString(sleep.getTimeToBed()));  //test code
                //Time up
                Calendar calTU = Calendar.getInstance();
                calTU.setTimeInMillis(sleep.getTimeUp());
                sleepTU.setText(calTU.get(Calendar.HOUR_OF_DAY)+":"+calTU.get(Calendar.MINUTE));
                */

                //hours slept
                String displayHours = "not entered";
                if(sleep.getSleepHours() == 1.5){
                    displayHours = "1-2 hours";
                }
                if(sleep.getSleepHours() == 4.0){
                    displayHours = "3-5 hours";
                }
                if(sleep.getSleepHours() == 7.0){
                    displayHours = "6-8 hours";
                }
                if(sleep.getSleepHours() == 9.0){
                    displayHours = "over 8 hours";
                }
                hoursSlept.setText(displayHours);
                //Sleep rating
                sleepRating.setText(Integer.toString(sleep.getSleepRating()));

        //Exercise Values for selected date
            while (exerciseTable.getChildCount()>1){exerciseTable.removeViewAt(1);} //This line just clears the table down when the selected date is changed.
            ExerciseDAO exerciseDAO = new ExerciseDAO(ReviewActivity.this);
            Exercise[] exercise = new Exercise[exerciseDAO.getExerciseRecordsForDate(longDate).length];
            exercise = exerciseDAO.getExerciseRecordsForDate(longDate);

            for (int i=0; i<exercise.length; i++){
                TableRow tr = new TableRow(this);
                Exercise row = exercise[i];
                exDuration = new TextView(this);
                exDuration.setText(Double.toString(row.getHours()));
                tr.addView(exDuration);
                exIntensity = new TextView(this);
                exIntensity.setText(Integer.toString(row.getIntensity()));
                tr.addView(exIntensity);
                exerciseTable.addView(tr);
            }
                //Duration
                //exDuration.setText(Double.toString(exercise.getHours()));
                //Intensity
                //exIntensity.setText(Integer.toString(exercise.getIntensity()));

        //Travel Values for selected date
            Travel travel = new Travel();
            TravelDAO travelDAO = new TravelDAO(ReviewActivity.this);
            travel = travelDAO.getTravelRecordForDate(longDate);
                //Duration
                travelDuration.setText(Double.toString(travel.getHours()));
                //Method
                travelMethod.setText(travel.getMethod());
                //Destination
                travelDest.setText(travel.getDest());

        //Mood value for selected date
            Mood mood = new Mood();
            MoodDAO moodDAO = new MoodDAO(ReviewActivity.this);
            mood = moodDAO.getMoodRecordForDate(longDate);
                //Mood
                moodRating.setText(Integer.toString(mood.getMood()));

    }




    //STANDARD ANDROID METHODS - NOT USED

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_review, menu);
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
