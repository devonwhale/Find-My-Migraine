package uk.ac.bradford.findmymigraine;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


//This class connects this information with its counterpart in the XML and deals with information when a button is pressed
//Comment by Steve to test change/version control
public class MainActivity extends ActionBarActivity implements LoginPopup.LoginPopupListener {
    //
    int whenYear, whenMonth, whenDay, wakeYear, wakeMonth, wakeDay;
    Calendar attackDate, wakeDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        attackDate = Calendar.getInstance(); //added code 26/3/15 to initialise attackDate
        wakeDate = Calendar.getInstance(); //added code 26/3/15 to initialise wakeDate (otherwise null and null pointer exception thrown by setWaKeDate() method!

        Button but_attack = (Button) findViewById(R.id.recAttack);
        but_attack.setOnClickListener(new View.OnClickListener() {
            /**
             * Moves to the Attack Recorder
             * @param v The current view
             */
            @Override
            public void onClick(View v) {
                //added code:
                DialogFragment newFragment = new WhenDatePicker();
                newFragment.show(getFragmentManager(), "datePicker");

                //Intent mv_att = new Intent(getApplicationContext(), WhenActivity.class);
                //startActivity(mv_att);
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

                //added code (26/3/15)
                DialogFragment newFragment = new SleepDatePicker();
                newFragment.show(getFragmentManager(), "datePicker");

                //Intent mv_daily = new Intent(getApplicationContext(), DailyActivity.class);
                //startActivity(mv_daily);
            }
        });


        Button but_review = (Button) findViewById(R.id.reviewRecords);
        but_review.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent mv = new Intent(getApplicationContext(), ReviewActivity.class);
                startActivity(mv);
            }
        });

        Button but_reviewAttacks = (Button) findViewById(R.id.reviewAttacks);
        but_reviewAttacks.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent mv = new Intent(getApplicationContext(), ReviewAttacks.class);
                startActivity(mv);
            }
        });

        Button but_userInfo = (Button) findViewById(R.id.userInfo);
        but_userInfo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent mv = new Intent(getApplicationContext(), UserInfoActivity.class);
                startActivity(mv);
            }
        });
    }

    public void setWakeDate(int year, int month, int day){
        //code to add - method will be called by WhenDatePicker
        wakeYear = year;
        wakeMonth = month;
        wakeDay = day;
        int displayMonthFigure = month + 1;
        //attackDate.setText(day+"/"+displayMonthFigure+"/"+year);
        //attackDate.setTextColor(5);
        //nextButton.setTextColor(0);

        //new code:
        Long c;
        Log.d("About to set wakeDate: ", wakeYear+"/"+wakeMonth+"/"+wakeDay);

        try {
            wakeDate.set(wakeYear, wakeMonth, wakeDay,0,0,0);
        } catch(Exception e) {
            Log.d("Exception", e.toString());
            Log.d("Wake date set: ",  wakeDate.toString()); }
        c = wakeDate.getTimeInMillis();

        //check if record already exists for wakeDate
        Sleep sleep = new Sleep();
        SleepDAO sleepDAO = new SleepDAO(MainActivity.this);
        //get values for selected date as a sleep object
        sleep = sleepDAO.getSleepRecordForDate(c); //returns a sleep record

        //if yes: Toast & return to main activity
        if (sleep.getDate() != 946598400000L) //records exists on database
        {
            Toast feedback = Toast.makeText(getApplicationContext(), "Daily Diary Record already exists for this date!", Toast.LENGTH_LONG);
            feedback.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
            feedback.show();

            Intent mv_dd = new Intent(getApplicationContext(), MainActivity.class);
            mv_dd.putExtra("uk.ac.bradford.findmymigraine.date", c);
            startActivity(mv_dd);

        }
        //else: launch SleepActivity
        else {
            Toast.makeText(getBaseContext(), "Date set to " + day + "/" + displayMonthFigure + "/" + year, Toast.LENGTH_LONG).show();

            Intent mv_dd = new Intent(getApplicationContext(), SleepActivity.class);
            mv_dd.putExtra("uk.ac.bradford.findmymigraine.date", c);
            startActivity(mv_dd);
        }
    }

    //set date code required...
    public void setAttackDate(int year, int month, int day){
        //code to add - method will be called by WhenDatePicker
        whenYear = year;
        whenMonth = month;
        whenDay = day;
        int displayMonthFigure = month + 1;
        //attackDate.setText(day+"/"+displayMonthFigure+"/"+year);
        //attackDate.setTextColor(5);
        //nextButton.setTextColor(0);
        Toast.makeText(getBaseContext(), "Date set to " + day + "/" + displayMonthFigure + "/" + year, Toast.LENGTH_LONG).show();

        //new code:
        Long c;
        attackDate.set(whenYear, whenMonth, whenDay,0,0,0);
        c = attackDate.getTimeInMillis();


        Intent mv_att = new Intent(getApplicationContext(), WhenActivity.class);
        mv_att.putExtra("uk.ac.bradford.findmymigraine.date", c);
        startActivity(mv_att);
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
