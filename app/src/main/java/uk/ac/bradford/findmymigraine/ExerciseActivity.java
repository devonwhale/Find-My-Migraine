package uk.ac.bradford.findmymigraine;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

//This class connects this information with its counterpart in the XML and deals with information when a button is pressed
public class ExerciseActivity extends ActionBarActivity {
    EditText hoursEditText;
    RatingBar intensityRatingBar;
    Button nextButton, anotherButton;
    TextView tvTitle;
    Calendar c;
    int sleepRating; double sleepHours;
    long c2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        initialise();
        setOnClickListeners();

        Bundle extra = getIntent().getExtras();
        if(extra != null) {
            c = Calendar.getInstance();
            c2 = extra.getLong("uk.ac.bradford.findmymigraine.date");
            c.setTimeInMillis(c2);
            sleepRating = extra.getInt("uk.ac.bradford.findmymigraine.stars"); //added 3/4/15 by Steve.
            sleepHours = extra.getDouble("uk.ac.bradford.findmymigraine.sleepHours");
            tvTitle = (TextView) findViewById(R.id.exerciseTitle);
            int displayMonth = c.get(Calendar.MONTH) + 1;
            tvTitle.setText(tvTitle.getText().toString() + " for " + c.get(Calendar.DATE)+"/"+displayMonth+"/"+c.get(Calendar.YEAR));
        }

    }

    /** Initialise components in XML layout*/
    private void initialise(){
        hoursEditText = (EditText) findViewById(R.id.hoursEditText);
        intensityRatingBar = (RatingBar) findViewById(R.id.intensityRatingBar);
        nextButton = (Button) findViewById(R.id.nextButton);
        anotherButton = (Button)findViewById(R.id.anotherButton);
    }

    /** Set onClickListeners for components in XML layout*/
    private void setOnClickListeners(){

     nextButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

             updateExerciseRecords();
          //Go to Food and Drink Activity
          Intent intent = new Intent(getApplicationContext(), SleepActivity.class);
          intent.putExtra("uk.ac.bradford.findmymigraine.date", c2);
          intent.putExtra("uk.ac.bradford.findmymigraine.stars", sleepRating);
          intent.putExtra("uk.ac.bradford.findmymigraine.sleepHours", sleepHours);
          startActivity(intent);
         }
     });

        anotherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateExerciseRecords();
                //Go to Food and Drink Activity
                Intent intent = new Intent(getApplicationContext(), ExerciseActivity.class);
                intent.putExtra("uk.ac.bradford.findmymigraine.date", c2);
                intent.putExtra("uk.ac.bradford.findmymigraine.stars", sleepRating);
                intent.putExtra("uk.ac.bradford.findmymigraine.sleepHours", sleepHours);
                startActivity(intent);
            }
        });
    }

    private void updateExerciseRecords() {

        if (hoursEditText.getText().toString().length() <1 || intensityRatingBar.getRating() <1) {


        Toast feedback = Toast.makeText(getApplicationContext(), "Exercise details not completed. No record added.", Toast.LENGTH_LONG);
        feedback.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
        feedback.show();
        }
        else {
            //retrieve number of stars specifies by user in rating bar
            int numStars = (int) intensityRatingBar.getRating();

            double hours = Double.parseDouble(hoursEditText.getText().toString());
          /*if (c2 != null) {
          }*/
            //Enter exercise details into database
            Exercise e = new Exercise(c2, hours, numStars);

            //Create Exercise Data Access Object Instance
            ExerciseDAO dao = new ExerciseDAO(ExerciseActivity.this);

            //Enter exercise record into database
            dao.createExerciseRecord(e);

            Toast feedback = Toast.makeText(getApplicationContext(), "Exercise Record Added", Toast.LENGTH_LONG);
            feedback.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
            feedback.show();

            Log.d("Exercise ", "Exercise Record Added");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_exercise, menu);
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
