package uk.ac.bradford.findmymigraine;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.InputMismatchException;


public class ReviewAttackRecord extends ActionBarActivity {

    Long id, attackDate, attackStart, attackEnd;
    TextView dateString, startString, endString, copingString, causesString, intenString;
    String attackDateString, attackStartString, attackEndString;
    ImageView[] ivHead;



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
        intenString = (TextView)findViewById(R.id.attack_rec_inten);
        copingString = (TextView)findViewById(R.id.attack_rec_coping);
        causesString = (TextView)findViewById(R.id.attack_rec_causes);

        ivHead = new ImageView[16];
        ivHead[0] = (ImageView) findViewById(R.id.head00r);
        ivHead[1] = (ImageView) findViewById(R.id.head01r);
        ivHead[2] = (ImageView) findViewById(R.id.head02r);
        ivHead[3] = (ImageView) findViewById(R.id.head03r);
        ivHead[4] = (ImageView) findViewById(R.id.head04r);
        ivHead[5] = (ImageView) findViewById(R.id.head05r);
        ivHead[6] = (ImageView) findViewById(R.id.head06r);
        ivHead[7] = (ImageView) findViewById(R.id.head07r);
        ivHead[8] = (ImageView) findViewById(R.id.head08r);
        ivHead[9] = (ImageView) findViewById(R.id.head09r);
        ivHead[10] = (ImageView) findViewById(R.id.head10r);
        ivHead[11] = (ImageView) findViewById(R.id.head11r);
        ivHead[12] = (ImageView) findViewById(R.id.head12r);
        ivHead[13] = (ImageView) findViewById(R.id.head13r);
        ivHead[14] = (ImageView) findViewById(R.id.head14r);
        ivHead[15] = (ImageView) findViewById(R.id.head15r);
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

        intenString.setText(when.getIntensity() + "/5");

        Coping coping = new Coping();
        CopingDAO copingDAO = new CopingDAO(ReviewAttackRecord.this);
        coping = copingDAO.getCopingRecord(when.getStart_time());

        copingString.setText(coping.copingList());

        Causes causes = new Causes();
        CausesDAO causesDAO = new CausesDAO(ReviewAttackRecord.this);
        causes = causesDAO.getCausesRecord(when.getStart_time());

        causesString.setText(causes.causesList());

        Intensity i;
        IntensityDAO idoa = new IntensityDAO(ReviewAttackRecord.this);
        i = idoa.getIntensityRecord(id);

        char[] locations = i.getIntensity().toCharArray();
        for(int j = 0; j < 16; j++) {
            if(locations[j] == '1')
                makeImageChange(j);
        }
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

    private void makeImageChange(int place) {
        if(place == 0)
            ivHead[0].setImageResource(R.drawable.red_00);
        if(place == 1)
            ivHead[1].setImageResource(R.drawable.red_01);
        if(place == 2)
            ivHead[2].setImageResource(R.drawable.red_02);
        if(place == 3)
            ivHead[3].setImageResource(R.drawable.red_03);
        if(place == 4)
            ivHead[4].setImageResource(R.drawable.red_04);
        if(place == 5)
            ivHead[5].setImageResource(R.drawable.red_05);
        if(place == 6)
            ivHead[6].setImageResource(R.drawable.red_06);
        if(place == 7)
            ivHead[7].setImageResource(R.drawable.red_07);
        if(place == 8)
            ivHead[8].setImageResource(R.drawable.red_08);
        if(place == 9)
            ivHead[9].setImageResource(R.drawable.red_09);
        if(place == 10)
            ivHead[10].setImageResource(R.drawable.red_10);
        if(place == 11)
            ivHead[11].setImageResource(R.drawable.red_11);
        if(place == 12)
            ivHead[12].setImageResource(R.drawable.red_12);
        if(place == 13)
            ivHead[13].setImageResource(R.drawable.red_13);
        if(place == 14)
            ivHead[14].setImageResource(R.drawable.red_14);
        if(place == 15)
            ivHead[15].setImageResource(R.drawable.red_15);
    }
}
