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
import android.widget.Toast;


public class TravelActivity extends ActionBarActivity {

    Button btnNext;
    EditText etHours, etTravelType, etDest;
    int time;
    String method, dest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);

        btnNext = (Button) findViewById(R.id.btnNext);
        etHours = (EditText) findViewById(R.id.etHours);
        etTravelType = (EditText) findViewById(R.id.etTravelType);
        etDest = (EditText) findViewById(R.id.etDest);


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                time = Integer.parseInt(etHours.getText().toString());
                method = etTravelType.toString();
                dest = etDest.toString();

                Travel t = new Travel(time, method, dest);
                TravelDAO dao = new TravelDAO(TravelActivity.this);
                dao.createTravelRecord(t);
                Log.d("Travel ", "Travel Record Added");

                Toast feedback = Toast.makeText(getApplicationContext(), "Details Added to Travel Records", Toast.LENGTH_LONG);
                feedback.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
                feedback.show();

                Intent i = new Intent(getApplicationContext(), MoodActivity.class);
                startActivity(i);
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
