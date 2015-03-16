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
import android.widget.TextView;

import java.util.Calendar;


public class FoodDrinkActivity extends ActionBarActivity {
    RadioButton beerRB;
    RadioButton redWineRB;
    RadioButton whiteWineRB;
    RadioButton spiritRB;
    RadioButton sodaRB;
    RadioButton coffeeRB;
    RadioButton teaRB;
    RadioButton chocolateRB;
    RadioButton cheeseRB;
    RadioButton nutsRB;
    RadioButton citrusFruitsRB;
    Button nextButton;
    Calendar c;
    long c2;

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
           // tvTitle = (TextView) findViewById(R.id.foodDrinkTitle);
          //  int displayMonth = c.get(Calendar.MONTH) + 1;
          //  tvTitle.setText(tvTitle.getText().toString() + " for " + c.get(Calendar.DATE)+"/"+displayMonth+"/"+c.get(Calendar.YEAR));
        }

        setOnClickListeners();
    }

    /** Initialise components in XML layout*/
    private void initialise(){
         beerRB = (RadioButton) findViewById(R.id.beerRB);
         redWineRB = (RadioButton) findViewById(R.id.redWineRB);
         whiteWineRB = (RadioButton) findViewById(R.id.whiteWineRB);
         spiritRB = (RadioButton) findViewById(R.id.spiritRB);
         sodaRB = (RadioButton) findViewById(R.id.sodaRB);
         coffeeRB = (RadioButton) findViewById(R.id.coffeeRB);
         teaRB = (RadioButton) findViewById(R.id.teaRB);
         chocolateRB = (RadioButton) findViewById(R.id.chocolateRB);
         cheeseRB = (RadioButton) findViewById(R.id.cheeseRB);
         nutsRB = (RadioButton) findViewById(R.id.nutsRB);
         citrusFruitsRB = (RadioButton) findViewById(R.id.citrusFruitsRB);
         nextButton = (Button) findViewById(R.id.foodDrinkNextButton);
    }

    /** Set onClickListeners for components in XML layout*/
    private void setOnClickListeners() {

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create array of integer for radio button, 0 if not checked, 1 if checked
                int[] intArray = new int[11];

                //initialise all elements of array to 0 (false)
                for (int i=0; i<intArray.length;i++ ){
                    intArray[i]= 0;
                }

                // Is the button now checked?
              //  boolean checked = ((RadioButton) v).isChecked();
              //java.lang.ClassCastException: android.widget.Button cannot be cast to android.widget.RadioButton??

                //isSelected() or isChecked() ??
                // Check which radio button was clicked
                switch(v.getId()) {
                    case R.id.beerRB:
                        if (beerRB.isSelected()){
                            intArray[0]= 1;
                        }
                    case R.id.redWineRB:
                        if (redWineRB.isSelected()){
                            intArray[1]= 1;
                        }
                    case R.id.whiteWineRB:
                        if (whiteWineRB.isSelected()){
                            intArray[2]= 1;
                        }
                    case R.id.spiritRB:
                        if (spiritRB.isSelected()){

                            intArray[3]= 1;
                        }
                    case R.id.sodaRB:
                        if (sodaRB.isSelected()){
                            intArray[4]= 1;
                        }
                    case R.id.coffeeRB:
                        if (coffeeRB.isSelected()){
                            intArray[5]= 1;
                        }
                    case R.id.teaRB:
                        if (teaRB.isSelected()){
                            intArray[6]= 1;
                        }
                    case R.id.chocolateRB:
                        if (chocolateRB.isSelected()){
                            intArray[7]= 1;
                        }
                    case R.id.cheeseRB:
                        if (cheeseRB.isSelected()){
                            intArray[8]= 1;
                        }
                    case R.id.nutsRB:
                        if (nutsRB.isSelected()){
                            intArray[9]= 1;
                        }
                    case R.id.citrusFruitsRB:
                        if (citrusFruitsRB.isSelected()){
                            intArray[10]= 1;
                        }
                }

                //Create Drink and Food objects
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

                Intent intent = new Intent(getApplicationContext(), TravelActivity.class);
                intent.putExtra("uk.ac.bradford.findmymigraine.date", c2);
                startActivity(intent);
            }
        });
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
