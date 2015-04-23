package uk.ac.bradford.findmymigraine;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

//This class connects this information with its counterpart in the XML and deals with information when a button is pressed
public class IntensityActivity extends ActionBarActivity {

    private ImageView[] ivHead;
    private boolean[] locations;
    private Button butNext;
    private int whenYear, whenMonth, whenDay, startHour, startMinute, endHour, endMinute;
    private Calendar c;
    private long c2, a;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intensity);

        Bundle extra = getIntent().getExtras();
        if(extra != null) {
            c = Calendar.getInstance();
            c2 = extra.getLong("uk.ac.bradford.findmymigraine.date");
            c.setTimeInMillis(c2);
            a = extra.getLong("uk.ac.bradford.findmymigraine.start");
        }

        ivHead = new ImageView[16];
        ivHead[0] = (ImageView) findViewById(R.id.head00);
        ivHead[1] = (ImageView) findViewById(R.id.head01);
        ivHead[2] = (ImageView) findViewById(R.id.head02);
        ivHead[3] = (ImageView) findViewById(R.id.head03);
        ivHead[4] = (ImageView) findViewById(R.id.head04);
        ivHead[5] = (ImageView) findViewById(R.id.head05);
        ivHead[6] = (ImageView) findViewById(R.id.head06);
        ivHead[7] = (ImageView) findViewById(R.id.head07);
        ivHead[8] = (ImageView) findViewById(R.id.head08);
        ivHead[9] = (ImageView) findViewById(R.id.head09);
        ivHead[10] = (ImageView) findViewById(R.id.head10);
        ivHead[11] = (ImageView) findViewById(R.id.head11);
        ivHead[12] = (ImageView) findViewById(R.id.head12);
        ivHead[13] = (ImageView) findViewById(R.id.head13);
        ivHead[14] = (ImageView) findViewById(R.id.head14);
        ivHead[15] = (ImageView) findViewById(R.id.head15);


        locations = new boolean[16];
        locations[0] = false;
        locations[1] = false;
        locations[2] = false;
        locations[3] = false;
        locations[4] = false;
        locations[5] = false;
        locations[6] = false;
        locations[7] = false;
        locations[8] = false;
        locations[9] = false;
        locations[10] = false;
        locations[11] = false;
        locations[12] = false;
        locations[13] = false;
        locations[14] = false;
        locations[15] = false;

        butNext = (Button) findViewById(R.id.intensityNext);

        ivHead[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!locations[0]) {
                    ivHead[0].setImageResource(R.drawable.red_00);
                    locations[0] = true;
                } else {
                    ivHead[0].setImageResource(R.drawable.nor_00);
                    locations[0] = false;
                }
            }
        });

        ivHead[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!locations[1]) {
                    ivHead[1].setImageResource(R.drawable.red_01);
                    locations[1] = true;
                } else {
                    ivHead[1].setImageResource(R.drawable.nor_01);
                    locations[1] = false;
                }
            }
        });

        ivHead[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!locations[2]) {
                    ivHead[2].setImageResource(R.drawable.red_02);
                    locations[2] = true;
                } else {
                    ivHead[2].setImageResource(R.drawable.nor_02);
                    locations[2] = false;
                }
            }
        });

        ivHead[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!locations[3]) {
                    ivHead[3].setImageResource(R.drawable.red_03);
                    locations[3] = true;
                } else {
                    ivHead[3].setImageResource(R.drawable.nor_03);
                    locations[3] = false;
                }
            }
        });

        ivHead[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!locations[4]) {
                    ivHead[4].setImageResource(R.drawable.red_04);
                    locations[4] = true;
                } else {
                    ivHead[4].setImageResource(R.drawable.nor_04);
                    locations[4] = false;
                }
            }
        });

        ivHead[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!locations[5]) {
                    ivHead[5].setImageResource(R.drawable.red_05);
                    locations[5] = true;
                } else {
                    ivHead[5].setImageResource(R.drawable.nor_05);
                    locations[5] = false;
                }
            }
        });

        ivHead[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!locations[6]) {
                    ivHead[6].setImageResource(R.drawable.red_06);
                    locations[6] = true;
                } else {
                    ivHead[6].setImageResource(R.drawable.nor_06);
                    locations[6] = false;
                }
            }
        });

        ivHead[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!locations[7]) {
                    ivHead[7].setImageResource(R.drawable.red_07);
                    locations[7] = true;
                } else {
                    ivHead[7].setImageResource(R.drawable.nor_07);
                    locations[7] = false;
                }
            }
        });

        ivHead[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!locations[8]) {
                    ivHead[8].setImageResource(R.drawable.red_08);
                    locations[8] = true;
                } else {
                    ivHead[8].setImageResource(R.drawable.nor_08);
                    locations[8] = false;
                }
            }
        });

        ivHead[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!locations[9]) {
                    ivHead[9].setImageResource(R.drawable.red_09);
                    locations[9] = true;
                } else {
                    ivHead[9].setImageResource(R.drawable.nor_09);
                    locations[9] = false;
                }
            }
        });

        ivHead[10].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!locations[10]) {
                    ivHead[10].setImageResource(R.drawable.red_10);
                    locations[10] = true;
                } else {
                    ivHead[10].setImageResource(R.drawable.nor_10);
                    locations[10] = false;
                }
            }
        });

        ivHead[11].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!locations[11]) {
                    ivHead[11].setImageResource(R.drawable.red_11);
                    locations[11] = true;
                } else {
                    ivHead[11].setImageResource(R.drawable.nor_11);
                    locations[11] = false;
                }
            }
        });

        ivHead[12].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!locations[12]) {
                    ivHead[12].setImageResource(R.drawable.red_12);
                    locations[12] = true;
                } else {
                    ivHead[12].setImageResource(R.drawable.nor_12);
                    locations[12] = false;
                }
            }
        });

        ivHead[13].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!locations[13]) {
                    ivHead[13].setImageResource(R.drawable.red_13);
                    locations[13] = true;
                } else {
                    ivHead[13].setImageResource(R.drawable.nor_13);
                    locations[13] = false;
                }
            }
        });

        ivHead[14].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!locations[14]) {
                    ivHead[14].setImageResource(R.drawable.red_14);
                    locations[14] = true;
                } else {
                    ivHead[14].setImageResource(R.drawable.nor_14);
                    locations[14] = false;
                }
            }
        });

        ivHead[15].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!locations[15]) {
                    ivHead[15].setImageResource(R.drawable.red_15);
                    locations[15] = true;
                } else {
                    ivHead[15].setImageResource(R.drawable.nor_15);
                    locations[15] = false;
                }
            }
        });

        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String intensity;
                if(locations[0])
                    intensity = "1";
                else
                    intensity = "0";
                for(int i = 1; i < locations.length; i++) {
                    if(locations[i])
                        intensity = intensity + "1";
                    else
                        intensity = intensity + "0";
                }

                Intensity i = new Intensity(intensity, c2);
                IntensityDAO dao = new IntensityDAO(IntensityActivity.this);

                dao.createIntensityRecord(i);
                Log.d("Intensity ", "Intensity Object created:" + " dateLong: " + c2 + ", Intensity (String): " + i );

                Toast feedback = Toast.makeText(getApplicationContext(), "Details Added to Migraine Records", Toast.LENGTH_LONG);
                feedback.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
                feedback.show();

                Intent mv = new Intent(getApplicationContext(), ActivityCoping.class);
                mv.putExtra("uk.ac.bradford.findmymigraine.date", c2);
                mv.putExtra("uk.ac.bradford.findmymigraine.start", a);
                startActivity(mv);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_intensity, menu);
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
