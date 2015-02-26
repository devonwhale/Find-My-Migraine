package uk.ac.bradford.findmymigraine;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;


public class ExerciseActivity extends ActionBarActivity {
    EditText hoursEditText;
    RatingBar intensityRatingBar;
    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        initialise();
        setOnClickListeners();
    }

    /** Initialise components in XML layout*/
    private void initialise(){
        hoursEditText = (EditText) findViewById(R.id.hoursEditText);
        intensityRatingBar = (RatingBar) findViewById(R.id.intensityRatingBar);
        nextButton = (Button) findViewById(R.id.nextButton);
    }

    /** Set onClickListeners for components in XML layout*/
    private void setOnClickListeners(){

     nextButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             //retrieve number of stars specifies by user in rating bar
             int numStars = (int) intensityRatingBar.getRating();

            double hours = Double.parseDouble(hoursEditText.getText().toString());

             //Enter exercise details into database
             Exercise e = new Exercise(hours,numStars);

             //Create Exercise Data Access Object Instance
             ExerciseDAO dao = new ExerciseDAO(ExerciseActivity.this);
             //Enter exercise record into database
             dao.createExerciseRecord(e);
             Log.d("Exercise ", "Exercise Record Added");
             //Go back to Daily Activity
             Intent intent = new Intent(getApplicationContext(), DailyActivity.class);
             startActivity(intent);
         }
     });
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
//Submitting data to database not yet working
//Hours of exercise integer only