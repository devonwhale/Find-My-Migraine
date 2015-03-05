package uk.ac.bradford.findmymigraine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sumaia on 01/02/2015.
 * Date field added to methods by Steve 04/03/2015.
 */

//The Sleep Table's Data Access Object
public class SleepDAO {

    public static final String TAG = "SleepDAO";

    // Database fields
    private SQLiteDatabase db;
    private MySQLiteHelper dbHelper;
    private Context mContext;


    public SleepDAO(Context context) {
        this.mContext = context;
        dbHelper = new MySQLiteHelper(context);
        // open the database
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLException e) {
            Log.e(TAG, "SQLException on opening database " + e.getMessage());
            e.printStackTrace();
        }
    }


    //Add new Sleep Record
    public void createSleepingRecord(Sleep sleep) {
        Log.d("addSleepingRecord", sleep.toString());
        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
      //  values.put(MySQLiteHelper.COLUMN_ID, sleep.getID());
        values.put(dbHelper.COLUMN_SLEEP_DATE, sleep.getDate());
        values.put(dbHelper.COLUMN_TIME_TO_BED, sleep.getTimeToBed());
        values.put(dbHelper.COLUMN_TIME_UP, sleep.getTimeUp());
        values.put(dbHelper.COLUMN_SLEEP_RATING, sleep.getSleepRating());
        values.put(dbHelper.COLUMN_SLEEP_ID, sleep.getSyncFlag());

        //Inserting row
        db.insert(MySQLiteHelper.TABLE_SLEEP, null, values);
        db.close();

    }

    /*
    NOTE ON METHODOLOGY:
    Rather than return an Array of values for a row, the cursorToSleeping() method puts the
    cursor into a Sleep object to be returned.
     */

    //Get Single Sleep Record, given the id

    /*public Sleep getSleepingRecord(int id) {
        db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(MySQLiteHelper.TABLE_SLEEP, MySQLiteHelper.COLUMNS_SlEEP, " KEY_ID = ?",
                new String[] { String.valueOf(id) }, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Sleep sleeping = new Sleep();
        sleeping = cursorToSleeping(cursor);
        return sleeping;

    }*/

    //Get Single Sleep Record, for a given date  !!! Assumes we only have one per date...to be discussed

    public Sleep getSleepRecordForDate(Long dateRequired){

        db = dbHelper.getReadableDatabase();
        Sleep sleep = new Sleep();                                              //Looks to be returning this EMPTY sleep record - Steve. 5/3/15 21:38
        Cursor cursor;
        try {
            cursor = db.query(dbHelper.TABLE_SLEEP,
                    dbHelper.COLUMNS_SlEEP,
                    dbHelper.COLUMN_SLEEP_DATE + "=" + dateRequired,
                    null, null, null, null);
            if (cursor != null) cursor.moveToFirst();

            sleep = cursorToSleeping(cursor, dateRequired);
                                                                                //The app stops working on my phone with this code but is okay with Test code below.
            cursor.close();                                                     // NOW WORKING - but returning zero values

        }
        catch (SQLException e){
            Log.e("Get row error", e.toString());
            e.printStackTrace();
        }
        return sleep;
                                                                                            // Steve - 5/3/2015.
        //Test code Returning dummy sleep record
        //Sleep sleepTest = new Sleep(946598400000L, 946598460000L, 946684740000L, 9);        // (31/12/1999, 00:01, 23:59, 9)
        //return sleepTest;
    }


    //Get All Sleep Table Records
    /*public List<Sleep> getAllSleepingRecords() {
        List<Sleep> listSleeping = new ArrayList<Sleep>();

        Cursor cursor = db.query(MySQLiteHelper.TABLE_SLEEP, MySQLiteHelper.COLUMNS_SlEEP,
                null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Sleep sleeping = cursorToSleeping(cursor);
                listSleeping.add(sleeping);
                cursor.moveToNext();
            }


            cursor.close();
        }
        return listSleeping;
    }*/

    //Temp amend for test - pass dateRequired as second parameter
    protected Sleep cursorToSleeping(Cursor cursor, Long dateR) {
        Sleep sleeping  = new Sleep();
        sleeping.setID(Long.parseLong(cursor.getString(0)));
        //sleeping.setDate(Long.parseLong(cursor.getString(1)));
        //sleeping.setDate(dateR);                                        //for test - This is still returning 1/1/70, whatever is passed to getSleepRecordForDate()
        sleeping.setDate(1426377600000L);
        sleeping.setTimeToBed(Long.parseLong(cursor.getString(2)));     //These int no's change by 1 if date included - DONE
        sleeping.setTimeUp(Long.parseLong(cursor.getString(3)));
        sleeping.setSleepRating(Integer.parseInt(cursor.getString(4)));
        //log
        Log.d("getSleepingRecord("+sleeping.getID()+")", sleeping.toString());
        return sleeping;
    }


}
