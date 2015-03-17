package uk.ac.bradford.findmymigraine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


/*
Created by Steve
This is the "Migraine Attack Record" menu screen.
It only consists of 4 buttons which link to other screens.
Only the 'When' button is currently linked
 */

public class AttackActivity extends ActionBarActivity {
    //Variables of type button
    Button but_When, but_Severity, but_Coping, but_Causes;
    /**
    * Creates the view on the android device
    * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attack);

        //Link the variables created at the beginning of the Class to the xml objects
        but_When = (Button) findViewById(R.id.btnWhen);
        but_Severity = (Button) findViewById(R.id.btnSeverity);
        but_Coping = (Button) findViewById(R.id.btnCoping);
        but_Causes = (Button) findViewById(R.id.btnCauses);

        //Moves to the When Activity when pressed.
        but_When.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), WhenActivity.class);
                startActivity(i);
            }
        });

        /*code to be added for remaining three buttons below
        .................
        .................
         */

    }

    /**
     * Creates the options menu
     * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Deals with items  being selected in the options menu
     * */
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
