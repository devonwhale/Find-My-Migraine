package uk.ac.bradford.findmymigraine;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;


public class FoodDrink extends ActionBarActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_drink);
        initialise();
        setOnClickListeners();
    }

    /** Initialise components in XML layout*/
    private void initialise(){
        RadioButton beerRB = (RadioButton) findViewById(R.id.beerRB);
        RadioButton redWineRB = (RadioButton) findViewById(R.id.redWineRB);
        RadioButton whiteWineRB = (RadioButton) findViewById(R.id.whiteWineRB);
        RadioButton spiritRB = (RadioButton) findViewById(R.id.spiritRB);
        RadioButton sodaRB = (RadioButton) findViewById(R.id.sodaRB);
        RadioButton coffeeRB = (RadioButton) findViewById(R.id.coffeeRB);
        RadioButton teaRB = (RadioButton) findViewById(R.id.teaRB);
        RadioButton chocolateRB = (RadioButton) findViewById(R.id.chocolateRB);
        RadioButton cheeseRB = (RadioButton) findViewById(R.id.cheeseRB);
        RadioButton nutsRB = (RadioButton) findViewById(R.id.nutsRB);
        RadioButton citrusFruitsRB = (RadioButton) findViewById(R.id.citrusFruitsRB);
        Button nextButton = (Button) findViewById(R.id.nextButton);
    }

    /** Set onClickListeners for components in XML layout*/
    private void setOnClickListeners() {
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
