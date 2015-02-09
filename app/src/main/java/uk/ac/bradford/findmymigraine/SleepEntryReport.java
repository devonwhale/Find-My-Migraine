package uk.ac.bradford.findmymigraine;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

/*
 When user clicks button to update SleepActivity,
 the data written to the database is reported back
 in this screen.
 */
public class SleepEntryReport extends ActionBarActivity {

    TextView ttbField, tuField, rField;
    //need database object declaring here (after database class added to app e.g. - DBClass db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_entry_report);

        setupGUI();
        retrieveRow();
    }

    private void setupGUI(){
        ttbField = (TextView)findViewById(R.id.updated_sleep_tt);
        tuField = (TextView)findViewById(R.id.updated_sleep_tu);
        rField = (TextView)findViewById(R.id.updated_sleep_r);
    }

    private void retrieveRow(){
        ArrayList<Object> row;
        //row = db. get row method in DB class ... parameters much match entered data.
        /*
        ttbField.setText((String)row.get(?));
        tuField.setText((String)row.get(?));
        rField.setText((String)row.get(?));
         */
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sleep_entry_report, menu);
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
