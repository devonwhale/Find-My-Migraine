package uk.ac.bradford.findmymigraine;


import android.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
    TextView selectedDate, sleepTTB, sleepTU, sleepRating;
    int year, month, day;
    Calendar theDate;

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
        sleepTTB = (TextView)findViewById(R.id.ttb_details);
        sleepTU = (TextView)findViewById(R.id.tu_details);
        sleepRating = (TextView)findViewById(R.id.sr_details);
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
        this.month = month+1;                       // ADDED A FUDGE HERE!!!!!!
        this.day = day;
        selectedDate.setText("Records for: "+day+"/"+this.month+"/"+year);  // AND HERE !!!!

    }

    public void setValues(){
        //initialise Calendar
        theDate = Calendar.getInstance();
        //Convert theDate to Long for passing to Data Access Object(s)
        theDate.set(year, month, day);
        Long longDate = theDate.getTimeInMillis();

        //Sleep Values for selected date
            //new sleep and sleepDAO objects
            Sleep sleep = new Sleep();
            SleepDAO sleepDAO = new SleepDAO(ReviewActivity.this);
            //get values for selected date as a sleep object
            sleep = sleepDAO.getSleepRecordForDate(longDate); //returns a sleep record
            //display returned values in Android text views (values in sleep are longs (in millisecs...need to convert to times)
            Calendar calTTB = Calendar.getInstance();
            calTTB.setTimeInMillis(sleep.getTimeToBed());
            sleepTTB.setText(+calTTB.HOUR+":"+calTTB.MINUTE);



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