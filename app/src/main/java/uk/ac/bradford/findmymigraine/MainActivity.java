package uk.ac.bradford.findmymigraine;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

//Comment by Steve to test change/version control
public class MainActivity extends ActionBarActivity implements LoginPopup.LoginPopupListener {
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button but_attack = (Button) findViewById(R.id.recAttack);
        but_attack.setOnClickListener(new View.OnClickListener() {
            /**
             * Moves to the Attack Recorder
             * @param v The current view
             */
            @Override
            public void onClick(View v) {
                Intent mv_att = new Intent(getApplicationContext(), AttackActivity.class);
                startActivity(mv_att);
            }
        });

        Button but_daily = (Button) findViewById(R.id.recActivity);
        but_daily.setOnClickListener(new View.OnClickListener() {
            /**
             * Moves to the Daily Activity Recorder
             * @param v The current view
             */
            @Override
            public void onClick(View v) {
                Intent mv_daily = new Intent(getApplicationContext(), DailyActivity.class);
                startActivity(mv_daily);
            }
        });

        //Clicking button to review records - CODE COMMENTED OUT PENDING CREATION OF ReviewActivity class
        /*
        Button but_review = (Button) findViewById(R.id.reviewRecords);
        but_review.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent mv = new Intent(getApplicationContext(), ReviewActivity.class);
                startActivity(mv);
            }
        }); */
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Preforms the relevant action when an option is selected
     * @param item The item selected
     * @return If the operation is successful
     */
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

        if (id == R.id.action_login) {
            DialogFragment d = new LoginPopup();
            d.show(getFragmentManager(), "LoginPopup");
        }

        if (id == R.id.action_help) {
            //Display Help fragment
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Deals with the button being clicked on the Login page
     * @param d The Fragment this is coming from
     */
    @Override
    public void onDialogPositiveClick(DialogFragment d) {
        //Add login code
    }
}
