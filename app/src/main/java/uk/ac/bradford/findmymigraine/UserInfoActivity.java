package uk.ac.bradford.findmymigraine;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;


public class UserInfoActivity extends ActionBarActivity {

    //Variables for xml components
    EditText firstName, surname, email, gpName, gpEmail;
    Button update, writeData;
    //Variables for info to be saved to database
    String userFirstName, userSurname, userEmail, userGPName, userGPEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        initialise();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUserInfo();
            }
        });
        writeData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeToFile();
            }
        });
    }

    protected void initialise(){
        firstName = (EditText)findViewById(R.id.first_name);
        surname = (EditText)findViewById(R.id.surname);
        email = (EditText)findViewById(R.id.email);
        gpName = (EditText)findViewById(R.id.gp_name);
        gpEmail = (EditText)findViewById(R.id.gp_email);
        update = (Button)findViewById(R.id.user_update);
        writeData = (Button)findViewById(R.id.data_to_file);

        //get existing values for user info
        setValues();
    }

    private void setValues(){
        UserInfoDAO userInfoDAO = new UserInfoDAO(UserInfoActivity.this);
        String[] userInfo = userInfoDAO.getUserInfo();
        firstName.setText(userInfo[2]);
        surname.setText(userInfo[3]);
        email.setText(userInfo[4]);
        gpName.setText(userInfo[5]);
        gpEmail.setText(userInfo[6]);
    }

    private void updateUserInfo(){
        String[] newInfo = new String[7];
        newInfo[0] = "1"; //id of row in database
        newInfo[1] = "2"; //syncFlag for updated record
        newInfo[2] = firstName.getText().toString();
        newInfo[3] = surname.getText().toString();
        newInfo[4] = email.getText().toString();
        newInfo[5] = gpName.getText().toString();
        newInfo[6] = gpEmail.getText().toString();
        //code to update user tables
        UserInfoDAO newInfoDAO = new UserInfoDAO(UserInfoActivity.this);
        newInfoDAO.updateUserInfo(newInfo);
        //Toast to report
        Toast feedback = Toast.makeText(getApplicationContext(), "User record updated", Toast.LENGTH_LONG);
        feedback.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
        feedback.show();
        //Return to main activity screen
        Intent mv = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(mv);
    }

    private void writeToFile(){
        //code to get data

        /*Data required for
         UserInfo;
         Sleep, Travel, Exercise, Food, Drink, Work, Menstrual Cycle, Mood;
         When, Coping, Intensity, Causes;                                       13 TABLES TO DO - 1 DONE - TIME EST FOR REMAINING 12 = 6 HOURS (i.e 10 mins per method)
                                                                                ATTACHING FILES TO EMAIL NEEDS SOME INVESTIGATION. (External Storage, Manifest alteration, special methods for mail services????)
         */

        //Sleep records ..... !!!Depends upon methods setting up: Sleep.toStringArray() and SleepDAO.getAllSleepRecords()
        SleepDAO sleepRecords = new SleepDAO(UserInfoActivity.this);
        Sleep[] allSleepRecords = sleepRecords.getAllSleepRecords();
        String sleepRecordsCSV = "id, syncFlag, wakeDate, hours, rating\n";
        for (int i=0; i<allSleepRecords.length; i++){
            //String[] singleRecordStringArray = new String[5];
            for (int j=0; j<4; j++){
                sleepRecordsCSV += allSleepRecords[i].toStringArray()[j];
                sleepRecordsCSV += ",";
            }
            sleepRecordsCSV += allSleepRecords[i].toStringArray()[4];
            sleepRecordsCSV += "\n";
        }

        String fileString = sleepRecordsCSV;   //to be replaced by all records
        String filename = "SleepRecords.csv";       //BUT WHERE IS THIS WRITTEN TO???????
        FileOutputStream outputStream;
        //code to write to file
        try{
            outputStream = openFileOutput(filename, Context.MODE_WORLD_READABLE);      //was Context.MODE_PRIVATE
            outputStream.write(fileString.getBytes());
            outputStream.close();
        }
        catch(Exception e){e.printStackTrace();}

        //Code to create email
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, email.getText());
        intent.putExtra(Intent.EXTRA_SUBJECT, "Migraine file");
        intent.putExtra(Intent.EXTRA_TEXT, "Attached is a csv file with information from the migraine records");
        //attach file code to add

        //start email
        this.startActivity(Intent.createChooser(intent, "Sending email...."));

        //toast to report
        Toast feedback = Toast.makeText(getApplicationContext(), "Data written to "+filename, Toast.LENGTH_LONG);
        feedback.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
        feedback.show();
        Log.d("CSV File text: ",sleepRecordsCSV);
        //return to main menu
        Intent mv = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(mv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_info, menu);
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
