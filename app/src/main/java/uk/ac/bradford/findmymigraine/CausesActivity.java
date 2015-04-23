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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

//This class connects this information with its counterpart in the XML and deals with information when a button is pressed
public class CausesActivity extends ActionBarActivity {

    CheckBox  stress;
    CheckBox lack_of_sleep;
    CheckBox lack_of_food;
    CheckBox lack_of_water;
    CheckBox depression;
    //String other;
    EditText causeOther;
    Button btnNext;
    Calendar c;
    long c2, a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_causes);
        initialise();

        Bundle extra = getIntent().getExtras();
        if(extra != null) {
            c = Calendar.getInstance();
            c2 = extra.getLong("uk.ac.bradford.findmymigraine.date");
            c.setTimeInMillis(c2);
            a = extra.getLong("uk.ac.bradford.findmymigraine.start");

        }

        setOnClickListeners();
    }

    private void initialise(){
        stress= (CheckBox) findViewById(R.id.CauseStress);
        lack_of_food= (CheckBox) findViewById(R.id.CauseLackFood);
        lack_of_sleep= (CheckBox) findViewById(R.id.CauseLackSleep);
        lack_of_water= (CheckBox) findViewById(R.id.CauseLackWater);
        depression= (CheckBox) findViewById(R.id.CauseDepression);
        causeOther = (EditText)findViewById(R.id.CausesOther);
        btnNext = (Button) findViewById(R.id.BtnNext);
    }

    private void setOnClickListeners() {

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //create array of integer for radio button, 0 if not checked, 1 if checked
                int[] intArray = new int[5];                                                //changed to length 5 by Steve 26/3/15

                //initialise all elements of array to 0 (false)
                for (int i = 0; i < intArray.length; i++) {
                    intArray[i] = 0;
                }

                // Check if Checkbox was ticked

                if (stress.isChecked()) {
                    intArray[0] = 1;
                }

                if (lack_of_food.isChecked()) {
                    intArray[2] = 1;
                }
                if (lack_of_water.isChecked()) {
                    intArray[3] = 1;
                }
                if (lack_of_sleep.isChecked()) {
                    intArray[1] = 1;
                }
                if (depression.isChecked()){
                    intArray[4]= 1;
                }


                //Create Coping objects
                Causes causes = new Causes(c2,a,intArray[0],intArray[1],intArray[2], intArray[3], intArray[4], causeOther.getText().toString());


                //Create Coping data access objects
                CausesDAO causesDAO = new CausesDAO(CausesActivity.this);


                //Enter Drink and Food objects into database
                causesDAO.createCausesRecord(causes);
                Log.d("causes ", "Causes Record Added");
                Toast feedback = Toast.makeText(getApplicationContext(), "Details Added to Migraine Records", Toast.LENGTH_LONG);
                feedback.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
                feedback.show();


                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("uk.ac.bradford.findmymigraine.date", c2);
                startActivity(intent);
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_causes, menu);
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
