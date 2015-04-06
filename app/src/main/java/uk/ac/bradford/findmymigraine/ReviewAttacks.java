package uk.ac.bradford.findmymigraine;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class ReviewAttacks extends ActionBarActivity {
    TableLayout attackList;
    TextView id, date, time, details;
    //Button click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_attacks);
        attackList = (TableLayout)findViewById(R.id.review_attacks_table);
        id = (TextView)findViewById(R.id.attack_id);
        date = (TextView)findViewById(R.id.attack_date);
        time = (TextView)findViewById(R.id.attack_time);
        //click = (TextView)findViewById(R.id.view_details);
        buildTable();

    }

    public void buildTable(){
        while(attackList.getChildCount()>1){attackList.removeViewAt(1);} //clear previous table
        WhenDAO whenDAO = new WhenDAO(ReviewAttacks.this);
        When[] records;// = new When[whenDAO.getAllWhenRecords().length];
        records = whenDAO.getAllWhenRecords();

        for (int i=records.length-1; i>=0; i--){ //set to show most recent first
            TableRow tr = new TableRow(this);
            When when = records[i];
            final long whenID = when.getId();
            id = new TextView(this);
            id.setTextColor(Color.WHITE);
            id.setText(Long.toString(when.getId()));
            tr.addView(id);
            date = new TextView(this);
            date.setTextColor(Color.WHITE);
            date.setText(when.getDisplayDate());
            tr.addView(date);
            time = new TextView(this);
            time.setTextColor(Color.WHITE);
            time.setText(when.getDisplayStartTime());
            tr.addView(time);
            /*
            click = new TextView(this);
            click.setTextColor(Color.WHITE);
            click.setText("Click");
            tr.addView(click);*/

            Button click = new Button(this);
            click.setText("Click");
            click.setTextColor(Color.WHITE);

            tr.addView(click);
            //need to set on click listener for button.....Button but_review = (Button) findViewById(R.id.reviewRecords);
            click.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent mv = new Intent(getApplicationContext(), ReviewAttackRecord.class);
                    mv.putExtra("uk.ac.bradford.findmymigraine.id", whenID);
                    startActivity(mv);
                }
            });


            attackList.addView(tr);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_review_attacks, menu);
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
