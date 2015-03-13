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
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class MoodActivity extends ActionBarActivity {

    Button btnNext;
    RatingBar rbMood;
    TextView tvTitle;
    int mood;
    Calendar c;
    long c2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);

        btnNext = (Button) findViewById(R.id.btnNext);
        rbMood = (RatingBar) findViewById(R.id.rbMood);
        tvTitle = (TextView) findViewById(R.id.travelTitle);

        Bundle extra = getIntent().getExtras();
        if(extra != null) {
            c = Calendar.getInstance();
            c2 = extra.getLong("uk.ac.bradford.findmymigraine.date");
            c.setTimeInMillis(c2);
            tvTitle = (TextView) findViewById(R.id.moodTitle);
            tvTitle.setText(tvTitle.getText().toString() + " for " + c.DATE);
        }


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((int)rbMood.getRating() == 0) {
                    Toast feedback = Toast.makeText(getApplicationContext(), "Please enter your mood", Toast.LENGTH_LONG);
                    feedback.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
                    feedback.show();
                } else {
                    mood = (int) rbMood.getRating();
                    Mood m = new Mood(c2, mood);
                    MoodDAO dao = new MoodDAO(MoodActivity.this);
                    dao.createMoodRecord(m);
                    Log.d("Mood ", "Mood Record Added");

                    Toast feedback = Toast.makeText(getApplicationContext(), "Details Added to Mood Records", Toast.LENGTH_LONG);
                    feedback.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
                    feedback.show();

                    Intent i = new Intent(getApplicationContext(), DailyActivity.class);
                    startActivity(i);
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mood, menu);
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
