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
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class FoodDrinkActivity extends ActionBarActivity {
    CheckBox beerCB;
    CheckBox redWineCB;
    CheckBox whiteWineCB;
    CheckBox spiritCB;
    CheckBox sodaCB;
    CheckBox coffeeCB;
    CheckBox teaCB;
    CheckBox chocolateCB;
    CheckBox cheeseCB;
    CheckBox nutsCB;
    CheckBox citrusFruitsCB;
    Button nextButton, anotherButton;
    Calendar c;
    long c2; int sleepRating; double sleepHours; //pass throughs from sleep

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_drink);
        initialise();

        Bundle extra = getIntent().getExtras();
        if(extra != null) {
            c = Calendar.getInstance();
            c2 = extra.getLong("uk.ac.bradford.findmymigraine.date");
            c.setTimeInMillis(c2);
            sleepRating = extra.getInt("uk.ac.bradford.findmymigraine.stars"); //added 3/4/15 by Steve.
            sleepHours = extra.getDouble("uk.ac.bradford.findmymigraine.sleepHours");
           // tvTitle = (TextView) findViewById(R.id.foodDrinkTitle);
          //  int displayMonth = c.get(Calendar.MONTH) + 1;
          //  tvTitle.setText(tvTitle.getText().toString() + " for " + c.get(Calendar.DATE)+"/"+displayMonth+"/"+c.get(Calendar.YEAR));
        }

        setOnClickListeners();
    }

    /** Initialise components in XML layout*/
    private void initialise(){
         beerCB = (CheckBox) findViewById(R.id.beerCB);
         redWineCB = (CheckBox) findViewById(R.id.redWineCB);
         whiteWineCB = (CheckBox) findViewById(R.id.whiteWineCB);
         spiritCB = (CheckBox) findViewById(R.id.spiritCB);
         sodaCB = (CheckBox) findViewById(R.id.sodaCB);
         coffeeCB = (CheckBox) findViewById(R.id.coffeeCB);
         teaCB = (CheckBox) findViewById(R.id.teaCB);
         chocolateCB = (CheckBox) findViewById(R.id.chocolateCB);
         cheeseCB = (CheckBox) findViewById(R.id.cheeseCB);
         nutsCB = (CheckBox) findViewById(R.id.nutsCB);
         citrusFruitsCB = (CheckBox) findViewById(R.id.citrusFruitsCB);
         nextButton = (Button) findViewById(R.id.foodDrinkNextButton);
         anotherButton = (Button)findViewById(R.id.foodDrinkAnotherButton);
    }

    /** Set onClickListeners for components in XML layout*/
    private void setOnClickListeners() {

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addFoodDrinkRecord();

                Toast feedback = Toast.makeText(getApplicationContext(), "Food and Drink records added", Toast.LENGTH_LONG);
                feedback.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
                feedback.show();

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

                addFoodDrinkRecord();

                Toast feedback = Toast.makeText(getApplicationContext(), "Food and Drink records added", Toast.LENGTH_LONG);
                feedback.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
                feedback.show();

                Intent intent = new Intent(getApplicationContext(), FoodDrinkActivity.class);
                intent.putExtra("uk.ac.bradford.findmymigraine.date", c2);
                intent.putExtra("uk.ac.bradford.findmymigraine.stars", sleepRating);
                intent.putExtra("uk.ac.bradford.findmymigraine.sleepHours", sleepHours);
                startActivity(intent);
            }
        });
    }

    private void addFoodDrinkRecord(){
        //create array of integer for checkboxes, 0 if not checked, 1 if checked
        int[] intArray = new int[11];

        //initialise all elements of array to 0 (false)
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = 0;
        }

        // Check if checkbox was ticked

        if (beerCB.isChecked()) {
            intArray[0] = 1;
        }

        if (redWineCB.isChecked()) {
            intArray[1] = 1;
        }
        if (whiteWineCB.isChecked()) {
            intArray[2] = 1;
        }
        if (spiritCB.isChecked()) {
            intArray[3] = 1;
        }
        if (sodaCB.isChecked()) {
            intArray[4] = 1;
        }
        if (coffeeCB.isChecked()) {
            intArray[5] = 1;
        }
        if (teaCB.isChecked()) {
            intArray[6] = 1;
        }
        if (chocolateCB.isChecked()) {
            intArray[7] = 1;
        }
        if (cheeseCB.isChecked()) {
            intArray[8] = 1;
        }
        if (nutsCB.isChecked()) {
            intArray[9] = 1;
        }
        if (citrusFruitsCB.isChecked()) {
            intArray[10] = 1;
        }


        //Create Drink and Food instances
        Drink drink = new Drink(c2,intArray[0],intArray[1],intArray[2],intArray[3],intArray[4],intArray[5],intArray[6]);
        Food food = new Food(c2,intArray[7],intArray[8],intArray[9],intArray[10]);

        //Create Drink and Food data access objects
        DrinkDAO ddao = new DrinkDAO(FoodDrinkActivity.this);
        FoodDAO fdao = new FoodDAO(FoodDrinkActivity.this);

        //Enter Drink and Food objects into database
        ddao.createDrinkRecord(drink);
        Log.d("Drink ", "Drink Record Added");
        fdao.createFoodRecord(food);
        //TEST
        //  fdao.createFoodRecord(new Food(c2, 1,1,1,1));
        Log.d("Food ", "Food Record Added");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_food_drink, menu);
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
