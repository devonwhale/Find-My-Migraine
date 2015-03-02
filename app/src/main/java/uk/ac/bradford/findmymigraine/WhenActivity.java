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


public class WhenActivity extends ActionBarActivity {

    TextView attackDate, attackStart, attackEnd;
    Button nextButton;
    int startHour, startMinute, endHour, endMinute;
    Calendar whenDate, start, end;
    boolean time;

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
                time = false;
                //Listener to create fragment.
                /* CODE NEEDS DatePickerFragment TO BE RESOLVED FIRST           NOT WORKING YET...NEED TO CHECK HOW TO CODE THIS!
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), "datePicker");
                */
            }
        });
        attackStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time = false;
                //Listener to create fragment.
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "timePicker");
            }
        });
        attackEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time = false;
                //Listener to create fragment.
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "timePicker");
            }
        });
        //more code to add
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
