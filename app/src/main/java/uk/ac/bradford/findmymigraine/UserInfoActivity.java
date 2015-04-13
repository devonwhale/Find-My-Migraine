package uk.ac.bradford.findmymigraine;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


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

    }

    private void writeToFile(){
        //code to get data

        //code to write to file

        //toast to report
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
