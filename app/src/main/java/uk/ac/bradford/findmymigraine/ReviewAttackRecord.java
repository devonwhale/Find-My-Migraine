package uk.ac.bradford.findmymigraine;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class ReviewAttackRecord extends ActionBarActivity {

    Long id, attackDate, attackStart, attackEnd;
    TextView dateString, startString, endString, copingString, causesString;
    String attackDateString, attackStartString, attackEndString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_attack_record);
        Bundle extra = getIntent().getExtras();
        if(extra != null) {
            id = extra.getLong("uk.ac.bradford.findmymigraine.id");
        }
        initialise();
        setValues();
    }

    private void initialise(){
        dateString = (TextView)findViewById(R.id.attack_rec_date);
        startString = (TextView)findViewById(R.id.attack_rec_start);
        endString = (TextView)findViewById(R.id.attack_rec_end);
        copingString = (TextView)findViewById(R.id.attack_rec_coping);
        causesString = (TextView)findViewById(R.id.attack_rec_causes);
    }

    private void setValues(){
        /*
        get when record for id
        get coping record for startTime
        get causes record for startTime
         */
        When when = new When();
        WhenDAO whenDAO = new WhenDAO(ReviewAttackRecord.this);
        when = whenDAO.getWhenRecord(id);

        attackDateString = when.getDisplayDate();
        dateString.setText(attackDateString);

        attackStartString = when.getDisplayStartTime();
        startString.setText(attackStartString);

        attackEndString = when.getDisplayEndTime();
        endString.setText(attackEndString);

        Coping coping = new Coping();
        CopingDAO copingDAO = new CopingDAO(ReviewAttackRecord.this);
        coping = copingDAO.getCopingRecord(when.getStart_time());

        copingString.setText(coping.copingList());

        Causes causes = new Causes();
        CausesDAO causesDAO = new CausesDAO(ReviewAttackRecord.this);
        causes = causesDAO.getCausesRecord(when.getStart_time());

        causesString.setText(causes.causesList());
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_review_attack_record, menu);
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
