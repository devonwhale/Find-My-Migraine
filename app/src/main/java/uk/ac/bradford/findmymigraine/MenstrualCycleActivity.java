package uk.ac.bradford.findmymigraine;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Calendar;


public class MenstrualCycleActivity extends ActionBarActivity {
    RadioGroup menstrualCycleRadioGroup;
    RadioButton menstrualCycleRadioButton;
    RadioButton yesRB;
    RadioButton noRB;
    RadioButton comingSoonRB;
    Button saveButton;
    TextView header; //added by Steve 19/4
    Calendar c;
    long c2; int sleepRating; double sleepHours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menstrual_cycle);
        initialise();

        Bundle extra = getIntent().getExtras();
        if(extra != null) {
            c = Calendar.getInstance();
            c2 = extra.getLong("uk.ac.bradford.findmymigraine.date");
            c.setTimeInMillis(c2);
            sleepRating = extra.getInt("uk.ac.bradford.findmymigraine.stars"); //added 3/4/15 by Steve.
            sleepHours = extra.getDouble("uk.ac.bradford.findmymigraine.sleepHours");
            header.setText(header.getText().toString()+" for "+Converter.getDisplayDate(c2));
            // tvTitle = (TextView) findViewById(R.id.foodDrinkTitle);
            //  int displayMonth = c.get(Calendar.MONTH) + 1;
            //  tvTitle.setText(tvTitle.getText().toString() + " for " + c.get(Calendar.DATE)+"/"+displayMonth+"/"+c.get(Calendar.YEAR));
        }

        setOnClickListeners();
    }

    /** Initialise components in XML layout*/
    private void initialise(){
        header = (TextView)findViewById(R.id.textView2);
        menstrualCycleRadioGroup=(RadioGroup)findViewById(R.id.menstrualCycleRadioGroup);
        yesRB=(RadioButton)findViewById(R.id.yesRB);
        noRB=(RadioButton)findViewById(R.id.noRB);
        comingSoonRB=(RadioButton)findViewById(R.id.comingSoonRB);
        saveButton=(Button)findViewById(R.id.saveButton);
    }
    /** Set onClickListeners for components in XML layout*/
    private void setOnClickListeners() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //create array of integer for radiobuttons, 0 if not checked, 1 if checked
                int[] intArray = new int[3];

                //initialise all elements of array to 0 (false)
                for (int i = 0; i < intArray.length; i++) {
                    intArray[i] = 0;
                }

                // get selected radio button from radioGroup
                int selectedId = 0;
                selectedId = menstrualCycleRadioGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                menstrualCycleRadioButton = (RadioButton) findViewById(selectedId);

                //Check if id of selected radiobutton matches id of yesRB, and if so, turn corresponding array value to 1
                if (menstrualCycleRadioButton.getId()== yesRB.getId()){
                    intArray[0]=1;
                }
                //Check if id of selected radiobutton matches id of noRB, and if so, turn corresponding array value to 1
                else if (menstrualCycleRadioButton.getId()== noRB.getId()){    //was yesRB 13/4 - changed to noRB by Steve
                    intArray[1]=1;
                }
                //Check if id of selected radiobutton matches id of comingSoonRB, and if so, turn corresponding array value to 1
                else{
                    intArray[2]=1;
                }

                //Create MenstrualCycle instance
                MenstrualCycle mc = new MenstrualCycle(c2,intArray[0],intArray[1],intArray[2]);
                //TEST
               // MenstrualCycle mc = new MenstrualCycle(1,1,1,1);

                //Create MenstrualCycle data access object
                MenstrualCycleDAO mcdao = new MenstrualCycleDAO(MenstrualCycleActivity.this);

                //Enter MenstrualCycle instance into database
                mcdao.createMenstrualCycleRecord(mc);
                Log.d("MenstrualCycle ", "MenstrualCycle Record Added");

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
        getMenuInflater().inflate(R.menu.menu_menstrual_cycle, menu);
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
