package uk.ac.bradford.findmymigraine;

import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.lang.Override;
import java.util.Calendar;
import java.util.Date;
//just trying if push works --- imtanan
/*
Although called "SleepActivity", this class is really now the "Daily Diary"
 Only one Sleep record is allowed for a given date, but multiple travel,
 exercise, food/drink and work records can be added.
 Sleep still appears as the main activity on this screen.
 */

//This class connects this information with its counterpart in the XML and deals with information when a button is pressed
public class SleepActivity extends ActionBarActivity {

    TextView dateWaking;                //added 4/3/15
    TextView tvTimeToBed;
    TextView tvTimeUp;
    Button btnNext, addTravel, addExercise, addFoodDrink, addMenstrual, addWork, addMood;
    RatingBar ratingBarSleep, ratingBarMood;
    RadioGroup hoursSlept;
    RadioButton chosenHours; //rb12, rb35, rb68, rbOver8;

    int wakeYear, wakeMonth, wakeDay;
    int startHour, startMinute, endHour,endMinute, numStars;
    double sleepHours;
    Calendar wakeDate;
    Calendar start, end;
    boolean time;
    Calendar c;
    Long c2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);
        initialise();
        setOnClickListeners();

        //NEW CODE 26/3/15
        Bundle extra = getIntent().getExtras();
        if(extra != null) {
            c = Calendar.getInstance();
            c2 = extra.getLong("uk.ac.bradford.findmymigraine.date");
            c.setTimeInMillis(c2);
            numStars = extra.getInt("uk.ac.bradford.findmymigraine.stars");
            ratingBarSleep = (RatingBar) findViewById(R.id.ratingBarSleep);
            ratingBarSleep.setRating(numStars);
            sleepHours = extra.getDouble("uk.ac.bradford.findmymigraine.sleepHours");
            hoursSlept = (RadioGroup)findViewById(R.id.hoursSlept);
                if (sleepHours == 1.5){hoursSlept.check(R.id.hours12);}
                if (sleepHours == 4.0){hoursSlept.check(R.id.hours35);}
                if (sleepHours == 7.0){hoursSlept.check(R.id.hours68);}
                if (sleepHours == 9.0){hoursSlept.check(R.id.hoursOver8);}
            dateWaking = (TextView) findViewById(R.id.wakeDate);
            int displayMonth = c.get(Calendar.MONTH) + 1;
            dateWaking.setText("Date waking : "+c.get(Calendar.DATE)+"/"+displayMonth+"/"+c.get(Calendar.YEAR));
        }
    }


    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.hours12:
                if (checked)
                {sleepHours = 1.5;
                    break;}
            case R.id.hours35:
                if (checked)
                {sleepHours = 4.0;
                    break;}
            case R.id.hours68:
                if (checked)
                {sleepHours = 7.0;
                    break;}
            case R.id.hoursOver8:
                if (checked)
                {sleepHours = 9.0;
                    break;}
        }
    }
    /** Initialise components in XML layout*/
    private void initialise(){
        //dateWaking = (TextView)findViewById(R.id.et_sleep_date);    //added 4/3/15  //text view removed from xml 26/3/15
        //tvTimeToBed = (TextView) findViewById(R.id.etTTB);                    //text view removed from xml 26/3/15
        //tvTimeUp = (TextView) findViewById(R.id.etTU);                        //text view removed from xml 26/3/15

        //radio buttons added 26/3/15:
        //hoursSlept = (RadioGroup)findViewById(R.id.hoursSlept); Amended 3/4/15 - now initialised with Bundle extras
        //rb12 = (RadioButton)findViewById(R.id.hours12);
        //rb35 = (RadioButton)findViewById(R.id.hours35);
        //rb68 = (RadioButton)findViewById(R.id.hours68);
        //rbOver8 = (RadioButton)findViewById(R.id.hoursOver8);

        //ratingBarSleep = (RatingBar) findViewById(R.id.ratingBarSleep); Amended 3/4/15 - now initialised with Bundle extras
        //ratingBarMood = (RatingBar)findViewById(R.id.ratingBarMood);
        btnNext = (Button) findViewById(R.id.btnNext);
        addTravel = (Button)findViewById(R.id.btnTravel);
        addExercise = (Button)findViewById(R.id.btnExercise);
        addFoodDrink = (Button)findViewById(R.id.btnFoodDrink);
        addMenstrual = (Button)findViewById(R.id.btnMenstrual);
        addWork = (Button)findViewById(R.id.btnWork);
        addMood = (Button)findViewById(R.id.btnMood);

        wakeDate = Calendar.getInstance();
        start = Calendar.getInstance();
        end = Calendar.getInstance();
    }

    /** Set onClickListeners for components in XML layout*/
    private void setOnClickListeners() {
        /*  Removed from xml 26/3/15
        dateWaking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Listener to create fragment. - Moved to Daily Diary button on MainActivity
                DialogFragment newFragment = new SleepDatePicker();
                newFragment.show(getFragmentManager(), "datePicker");
            }
        });
        */

        /* TEXT VIEWS FOR TIME TO BED AND TIME UP REMOVED 26/3/15
        tvTimeToBed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time = false;
                //Listener to create fragment.
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "timePicker");
            }
        });

        tvTimeUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time = true;
                //Listener to create fragment.
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "timePicker");
            }
        });
        */

        addTravel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent mv = new Intent(getApplicationContext(), TravelActivity.class);
                mv.putExtra("uk.ac.bradford.findmymigraine.date", c2);
                //extras for hours slept & sleep rating to be passed ... and then passed back/reset???
                numStars = (int) ratingBarSleep.getRating();
                mv.putExtra("uk.ac.bradford.findmymigraine.stars", numStars);
                mv.putExtra("uk.ac.bradford.findmymigraine.sleepHours", sleepHours);
                startActivity(mv);
            }
        });

        addExercise.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent mv = new Intent(getApplicationContext(), ExerciseActivity.class);
                mv.putExtra("uk.ac.bradford.findmymigraine.date", c2);
                //extras for hours slept & sleep rating to be passed ... and then passed back/reset???
                numStars = (int) ratingBarSleep.getRating();
                mv.putExtra("uk.ac.bradford.findmymigraine.stars", numStars);
                mv.putExtra("uk.ac.bradford.findmymigraine.sleepHours", sleepHours);
                startActivity(mv);
            }
        });

        addFoodDrink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent mv = new Intent(getApplicationContext(), FoodDrinkActivity.class);
                mv.putExtra("uk.ac.bradford.findmymigraine.date", c2);
                //extras for hours slept & sleep rating to be passed ... and then passed back/reset???
                numStars = (int) ratingBarSleep.getRating();
                mv.putExtra("uk.ac.bradford.findmymigraine.stars", numStars);
                mv.putExtra("uk.ac.bradford.findmymigraine.sleepHours", sleepHours);
                startActivity(mv);
            }
        });

        addMenstrual.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //check to see if record exists for date
                MenstrualCycle ms = new MenstrualCycle();
                MenstrualCycleDAO msDAO = new MenstrualCycleDAO(SleepActivity.this);
                ms = msDAO.getMenstrualCycleRecordForDate(c2);
                if(ms.getDate()>(c2-1000)){   //record already exists
                    Toast feedback = Toast.makeText(getApplicationContext(), "Menstrual Cycle record already added!", Toast.LENGTH_LONG);
                    feedback.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
                    feedback.show();
                }

                else {
                    Intent mv = new Intent(getApplicationContext(), MenstrualCycleActivity.class);
                    mv.putExtra("uk.ac.bradford.findmymigraine.date", c2);
                    //extras for hours slept & sleep rating to be passed ... and then passed back/reset???
                    numStars = (int) ratingBarSleep.getRating();
                    mv.putExtra("uk.ac.bradford.findmymigraine.stars", numStars);
                    mv.putExtra("uk.ac.bradford.findmymigraine.sleepHours", sleepHours);
                    startActivity(mv);
                }
            }
        });

        addWork.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //check to see if record exists for date
                /*
                Work work = new Work();
                WorkDAO workDAO = new WorkDAO(SleepActivity.this);
                work = workDAO.getWorkRecordsForDate(c2);
                if(work.getDate()>(c2-1000)){   //record already exists
                    Toast feedback = Toast.makeText(getApplicationContext(), "Work record already added!", Toast.LENGTH_LONG);
                    feedback.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
                    feedback.show();
                }

                else {*/
                    Intent mv = new Intent(getApplicationContext(), WorkActivity.class);
                    mv.putExtra("uk.ac.bradford.findmymigraine.date", c2);
                    //extras for hours slept & sleep rating to be passed ... and then passed back/reset???
                    numStars = (int) ratingBarSleep.getRating();
                    mv.putExtra("uk.ac.bradford.findmymigraine.stars", numStars);
                    mv.putExtra("uk.ac.bradford.findmymigraine.sleepHours", sleepHours);
                    startActivity(mv);
                //}
            }
        });

        addMood.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //check to see if record exists for date
                Mood mood = new Mood();
                MoodDAO moodDAO = new MoodDAO(SleepActivity.this);
                mood = moodDAO.getMoodRecordForDate(c2);
                if(mood.getDate()>(c2-1000)){   //record already exists
                    Toast feedback = Toast.makeText(getApplicationContext(), "Mood record already added!", Toast.LENGTH_LONG);
                    feedback.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
                    feedback.show();
                }
                else {
                    Intent mv = new Intent(getApplicationContext(), MoodActivity.class);
                    mv.putExtra("uk.ac.bradford.findmymigraine.date", c2);
                    //extras for hours slept & sleep rating to be passed ... and then passed back/reset???
                    numStars = (int) ratingBarSleep.getRating();
                    mv.putExtra("uk.ac.bradford.findmymigraine.stars", numStars);
                    mv.putExtra("uk.ac.bradford.findmymigraine.sleepHours", sleepHours);
                    startActivity(mv);
                }
            }
        });


        //when clicked, create new sleep object with required data
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //This all needs removing or changing !!!!!
                /* IF NOT REQUIRED
                if(tvTimeToBed.getText().toString().equalsIgnoreCase("Click here") || tvTimeUp.getText().toString().equalsIgnoreCase("Click here")
                        //|| dateWaking.getText().toString().equalsIgnoreCase("Click here")
                        ) {
                    Toast t = Toast.makeText(getApplicationContext(), "Please set a date, start and end time", Toast.LENGTH_LONG);
                    t.show();
                }
                else { */

                //code added 27/3/15 - but buttons are not acting correctly at moment...all selectable and none can be deselected.
                int selectedRadio = hoursSlept.getCheckedRadioButtonId();
                chosenHours = (RadioButton)findViewById(selectedRadio);


                    Long a, b;
                    //Long c;
                    double hours;

                    hours = sleepHours;          //TEMPORARY VALUE UNTIL RADIO BUTTONS WORKING
                    a = 0L; b = 0L;     //tttb and time up no longer used
                    /*
                    start.setTime(new Date(start.get(start.YEAR) - 1900, start.get(start.MONTH), start.get(start.DAY_OF_WEEK), startHour, startMinute));
                    end.setTime(new Date(end.get(end.YEAR) - 1900, end.get(end.MONTH), end.get(end.DAY_OF_WEEK), endHour, endMinute));
                    a = start.getTimeInMillis();
                    b = end.getTimeInMillis();
                    */
                    //Set 'Date' in Long format for passing to constructor
                    //wakeDate.set(wakeYear, wakeMonth, wakeDay,0,0,0);
                    //c = wakeDate.getTimeInMillis();

                    //retrieve number of stars specified by user in rating bar
                    numStars = (int) ratingBarSleep.getRating();

                if(sleepHours<1 || numStars<1){
                    Toast feedback = Toast.makeText(getApplicationContext(), "Enter hours slept and rating!", Toast.LENGTH_LONG);
                    feedback.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
                    feedback.show();
                }
                else{
                    //Enter sleep details into Sleep object
                    Sleep s = new Sleep(
                            c2, hours,
                            a, b, numStars);

                    //Create Sleep Data Access Object Instance
                    SleepDAO dao = new SleepDAO(SleepActivity.this);

                    //Enter sleep record into database
                    dao.createSleepingRecord(s);
                    Log.d("Sleep ", "Sleep Record Added");
                /*Toast added by Steve to give feedback on submit.
                    The next three lines bring up a small 'toast' with the feedback text in the code.
                    The following two lines then return the user to the Daily Activity screen.
                 */
                    Toast feedback = Toast.makeText(getApplicationContext(), "Details Added to Daily Diary Records", Toast.LENGTH_LONG);
                    feedback.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
                    feedback.show();

                    Intent mv = new Intent(getApplicationContext(), MainActivity.class);
                    //mv.putExtra("uk.ac.bradford.findmymigraine.date", c);

                    startActivity(mv);
                //}   //END ELSE
            }}
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sleep, menu);
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

    public void setTime(int hour, int min) {
        System.err.println("Time received");
        if(!time) {
            startMinute = min;
            startHour = hour;
            tvTimeToBed.setText(hour + ": " + min);
            }
        else if(time) {
            endMinute = min;
            endHour = hour;
            tvTimeUp.setText(hour + ": " + min);
            }
        Toast.makeText(getBaseContext(), "Time set: "+hour+":"+min, Toast.LENGTH_LONG).show();
        }


    public void setDate(int year, int month, int day){
        //method called by SleepDatePicker
        wakeYear = year;
        wakeMonth = month;
        wakeDay = day;
        int displayMonthFigure = month + 1;
        dateWaking.setText(day+"/"+displayMonthFigure+"/"+year);           //month integer increased by 1.
        Toast.makeText(getBaseContext(), "Date set to "+day+"/"+displayMonthFigure+"/"+year, Toast.LENGTH_LONG).show();

    }
}

