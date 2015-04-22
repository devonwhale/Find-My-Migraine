package uk.ac.bradford.findmymigraine;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.Calendar;


public class WorkActivity extends ActionBarActivity {
    RadioGroup hoursWorkedRadioGroup;
    RadioButton radioButton12;
    RadioButton radioButton35;
    RadioButton radioButton68;
    RadioButton radioButtonOver8;
    RadioButton chosenHours;
    RatingBar ratingBarStress;
    Button saveButton;
    Calendar c;
    long c2; int sleepRating; double sleepHours; //pass throughs from sleep

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);
        initialise();
        setOnClickListeners();

        Bundle extra = getIntent().getExtras();
        if(extra != null) {
            c = Calendar.getInstance();
            c2 = extra.getLong("uk.ac.bradford.findmymigraine.date");
            c.setTimeInMillis(c2);
            sleepRating = extra.getInt("uk.ac.bradford.findmymigraine.stars");
            sleepHours = extra.getDouble("uk.ac.bradford.findmymigraine.sleepHours");
        }
    }

    /** Initialise components in XML layout*/
    private void initialise(){
        hoursWorkedRadioGroup = (RadioGroup)findViewById(R.id.hoursWorkedRadioGroup);
        radioButton12 = (RadioButton)findViewById(R.id.radioButton12);
        radioButton35= (RadioButton)findViewById(R.id.radioButton35);
        radioButton68= (RadioButton)findViewById(R.id.radioButton68);
        radioButtonOver8= (RadioButton)findViewById(R.id.radioButtonOver8);
        saveButton = (Button)findViewById(R.id.saveButton);
        ratingBarStress = (RatingBar)findViewById(R.id.ratingBarStress);
    }

    /** Set onClickListeners for components in XML layout*/
    private void setOnClickListeners() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedId = 0;
                selectedId = hoursWorkedRadioGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                chosenHours = (RadioButton) findViewById(selectedId);

                double hoursWorked=0;
                try {
                    //Check if id of selected radiobutton matches id of yesRB, and if so, turn corresponding array value to 1
                    if (chosenHours.getId() == radioButton12.getId()) {
                        hoursWorked = 1.5;
                    } else if (chosenHours.getId() == radioButton35.getId()) {
                        hoursWorked = 4;
                    } else if (chosenHours.getId() == radioButton68.getId()) {
                        hoursWorked = 7;
                    } else // over 8
                    {
                        hoursWorked = 9;
                    }
                }
                //no radio button was chosen
                catch(NullPointerException e){

                }
                //retrieve number of stars specified by user in rating bar
                int numStars = (int) ratingBarStress.getRating();


                //Enter work details into Sleep object
                Work work = new Work(c2, hoursWorked, numStars);

                //Create Work Data Access Object Instance
                WorkDAO workdao = new WorkDAO(WorkActivity.this);

                //Enter sleep record into database
                workdao.createWorkRecord(work);
                Log.d("Work ", "Work Record Added");

                Toast feedback = Toast.makeText(getApplicationContext(), "Details Added to Daily Diary Records", Toast.LENGTH_LONG);
                feedback.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
                feedback.show();

                //Return to Daily Diary
                Intent intent = new Intent(getApplicationContext(), SleepActivity.class);
                intent.putExtra("uk.ac.bradford.findmymigraine.date", c2);
                intent.putExtra("uk.ac.bradford.findmymigraine.stars", sleepRating);
                intent.putExtra("uk.ac.bradford.findmymigraine.sleepHours", sleepHours);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_work, menu);
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
