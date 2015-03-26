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


public class TravelActivity extends ActionBarActivity {

    Button btnNext;
    EditText etHours, etTravelType, etDest;
    TextView tvTitle;
    int time, numStars;
    String method, dest;
    Calendar c;
    long c2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);

        btnNext = (Button) findViewById(R.id.btnNext);
        etHours = (EditText) findViewById(R.id.etHours);
        etTravelType = (EditText) findViewById(R.id.etTravelType);
        etDest = (EditText) findViewById(R.id.etDest);
        //tvTitle = (TextView) findViewById(R.id.travelTitle);

        Bundle extra = getIntent().getExtras();
        if(extra != null) {
            c = Calendar.getInstance();
            c2 = extra.getLong("uk.ac.bradford.findmymigraine.date");
            c.setTimeInMillis(c2);
            numStars = extra.getInt("uk.ac.bradford.findmymigraine.stars");
            tvTitle = (TextView) findViewById(R.id.travelTitle);
            int displayMonth = c.get(Calendar.MONTH) + 1;
            tvTitle.setText(tvTitle.getText().toString() + " for " + c.get(Calendar.DATE)+"/"+displayMonth+"/"+c.get(Calendar.YEAR));
        }


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                        Intent i = new Intent(getApplicationContext(), SleepActivity.class);
                        i.putExtra("uk.ac.bradford.findmymigraine.date", c2);
                        i.putExtra("uk.ac.bradford.findmymigraine.stars", numStars);
                        startActivity(i);
                    }
                } catch (NumberFormatException e) {
                    Toast feedback = Toast.makeText(getApplicationContext(), "Please enter a duration for the travel", Toast.LENGTH_LONG);
                    feedback.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
                    feedback.show();
                }
            }
        });
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
