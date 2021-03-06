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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

//This class connects this information with its counterpart in the XML and deals with information when a button is pressed

public class TravelActivity extends ActionBarActivity {

    Button btnNext, btnAnother;
    EditText etHours, etTravelType, etDest;
    TextView tvTitle;
    int time;
    String method, dest;
    Calendar c;
    long c2; int sleepRating; double sleepHours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);

        btnNext = (Button) findViewById(R.id.btnNext);
        btnAnother = (Button)findViewById(R.id.btnAnother);
        etHours = (EditText) findViewById(R.id.etHours);
        etTravelType = (EditText) findViewById(R.id.etTravelType);
        etDest = (EditText) findViewById(R.id.etDest);
        //tvTitle = (TextView) findViewById(R.id.travelTitle);

        Bundle extra = getIntent().getExtras();
        if(extra != null) {
            c = Calendar.getInstance();
            c2 = extra.getLong("uk.ac.bradford.findmymigraine.date");
            c.setTimeInMillis(c2);
            sleepRating = extra.getInt("uk.ac.bradford.findmymigraine.stars"); //added 3/4/15 by Steve.
            sleepHours = extra.getDouble("uk.ac.bradford.findmymigraine.sleepHours");
            tvTitle = (TextView) findViewById(R.id.travelTitle);
            int displayMonth = c.get(Calendar.MONTH) + 1;
            tvTitle.setText(tvTitle.getText().toString() + " for " + c.get(Calendar.DATE)+"/"+displayMonth+"/"+c.get(Calendar.YEAR));
        }

        btnAnother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTravelRecord();

                Intent i = new Intent(getApplicationContext(), TravelActivity.class);
                i.putExtra("uk.ac.bradford.findmymigraine.date", c2);
                i.putExtra("uk.ac.bradford.findmymigraine.stars", sleepRating);
                i.putExtra("uk.ac.bradford.findmymigraine.sleepHours", sleepHours);
                startActivity(i);
            }});

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTravelRecord();

                Intent i = new Intent(getApplicationContext(), SleepActivity.class);
                i.putExtra("uk.ac.bradford.findmymigraine.date", c2);
                i.putExtra("uk.ac.bradford.findmymigraine.stars", sleepRating);
                i.putExtra("uk.ac.bradford.findmymigraine.sleepHours", sleepHours);
                startActivity(i);
            }});
    }

    private void addTravelRecord(){
        try {
            if(etTravelType.getText().toString().length() < 1 || etDest.getText().toString().length() < 1) {
                Toast feedback = Toast.makeText(getApplicationContext(), "Please enter a method and destination", Toast.LENGTH_LONG);
                feedback.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
                feedback.show();
            } else {
                time = Integer.parseInt(etHours.getText().toString());
                method = etTravelType.getText().toString();
                dest = etDest.getText().toString();

                Travel t = new Travel(c2, time, method, dest);
                TravelDAO dao = new TravelDAO(TravelActivity.this);
                dao.createTravelRecord(t);
                Log.d("Travel ", "Travel Record Added");

                Toast feedback = Toast.makeText(getApplicationContext(), "Details Added to Travel Records", Toast.LENGTH_LONG);
                feedback.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
                feedback.show();


            }
        } catch (NumberFormatException e) {
            Toast feedback = Toast.makeText(getApplicationContext(), "Please enter a duration for the travel", Toast.LENGTH_LONG);
            feedback.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
            feedback.show();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_travel, menu);
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
