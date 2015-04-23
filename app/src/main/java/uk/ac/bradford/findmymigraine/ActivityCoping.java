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
public class ActivityCoping extends ActionBarActivity {

    CheckBox CMeditation;
    CheckBox CMedication;
    CheckBox CYoga;
    CheckBox CSleep;
    EditText CoOther;
    String other;
    Button btnNext;
    Calendar c; //calendar is used to get the date
    long c2, a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_coping);
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
        CMeditation = (CheckBox) findViewById(R.id.CopingMeditation);
        CMedication = (CheckBox) findViewById(R.id.CopingMedication);
        CYoga = (CheckBox) findViewById(R.id.CopingYoga);
        CSleep = (CheckBox) findViewById(R.id.CopingSleep);
        CoOther = (EditText) findViewById(R.id.CopingOther);
        btnNext = (Button) findViewById(R.id.BtnNext);
    }

    private void setOnClickListeners() {

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //create array of integer for radio button, 0 if not checked, 1 if checked
                int[] intArray = new int[4];

                //initialise all elements of array to 0 (false)
                for (int i = 0; i < intArray.length; i++) {
                    intArray[i] = 0;
                }

                // Check if Checkbox was ticked

                if (CMedication.isChecked()) {
                    intArray[0] = 1;
                }

                if (CMeditation.isChecked()) {
                    intArray[1] = 1;
                }
                if (CSleep.isChecked()) {
                    intArray[2] = 1;
                }
                if (CYoga.isChecked()) {
                    intArray[3] = 1;
                }


                //Create Coping objects
                Coping coping = new Coping(c2,a,intArray[0],intArray[1],intArray[2], intArray[3], CoOther.getText().toString());


                //Create Coping data access objects
                CopingDAO Cdao = new CopingDAO(ActivityCoping.this);


                //Enter Drink and Food objects into database
                Cdao.createCopingRecord(coping);
                Log.d("Coping ", "Coping Record Added");
                Toast feedback = Toast.makeText(getApplicationContext(), "Details Added to Migraine Records", Toast.LENGTH_LONG);
                feedback.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
                feedback.show();


                Intent intent = new Intent(getApplicationContext(), CausesActivity.class);
                intent.putExtra("uk.ac.bradford.findmymigraine.date", c2);
                intent.putExtra("uk.ac.bradford.findmymigraine.start", a);
                startActivity(intent);
            }
        });
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_coping, menu);
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
