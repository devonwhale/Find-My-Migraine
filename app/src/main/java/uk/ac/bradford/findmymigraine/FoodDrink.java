package uk.ac.bradford.findmymigraine;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;


public class FoodDrink extends ActionBarActivity {
    RadioButton beerRadioButton;
    RadioButton redWineRadioButton;
    RadioButton whiteWineRadioButton;
    RadioButton spiritRadioButton;
    RadioButton sodaRadioButton;
    RadioButton coffeeRadioButton;
    RadioButton TeaRadioButton;
    EditText foodEditText;
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
        RadioButton beerRadioButton = (RadioButton) findViewById(R.id.beerRadioButton);
        RadioButton redWineRadioButton = (RadioButton) findViewById(R.id.redWineRadioButton);
        RadioButton whiteWineRadioButton = (RadioButton) findViewById(R.id.whiteWineRadioButton);
        RadioButton spiritRadioButton = (RadioButton) findViewById(R.id.spiritRadioButton);
        RadioButton sodaRadioButton = (RadioButton) findViewById(R.id.sodaRadioButton);
        RadioButton coffeeRadioButton = (RadioButton) findViewById(R.id.coffeeRadioButton);
        RadioButton TeaRadioButton = (RadioButton) findViewById(R.id.TeaRadioButton);
        EditText foodEditText = (EditText) findViewById(R.id.foodEditText);
        Button nextButton = (Button) findViewById(R.id.nextButton);
    }

    /** Set onClickListeners for components in XML layout*/
    private void setOnClickListeners(){
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //check which radio buttons have been checked and input values into database.
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
