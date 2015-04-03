package uk.ac.bradford.findmymigraine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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
        //values.put(dbHelper.COLUMN_SLEEP_ID, sleep.getID());
        values.put(MySQLiteHelper.COLUMN_SLEEP_DATE, sleep.getDate());
        values.put(MySQLiteHelper.COLUMN_TIME_TO_BED, sleep.getTimeToBed());
        values.put(MySQLiteHelper.COLUMN_TIME_UP, sleep.getTimeUp());
        values.put(MySQLiteHelper.COLUMN_SLEEP_HOURS, sleep.getSleepHours());
        values.put(MySQLiteHelper.COLUMN_SLEEP_RATING, sleep.getSleepRating());
        values.put(MySQLiteHelper.COLUMN_SLEEP_SYNCFLAG, sleep.getSyncFlag());

        //Inserting row
        db.insertWithOnConflict(MySQLiteHelper.TABLE_SLEEP, null, values, SQLiteDatabase.CONFLICT_IGNORE);
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

        Long minDate = dateRequired-1000;
        Long maxDate = dateRequired+1000;
        db = dbHelper.getReadableDatabase();
        Sleep sleep = new Sleep();                                              //Looks to be returning this EMPTY sleep record - Steve. 5/3/15 21:38
        Cursor cursor;
        try {
            cursor = db.query(MySQLiteHelper.TABLE_SLEEP,
                    MySQLiteHelper.COLUMNS_SlEEP,
                    MySQLiteHelper.COLUMN_SLEEP_DATE + ">" + minDate + " AND " + MySQLiteHelper.COLUMN_SLEEP_DATE + "<" + maxDate,
                    null, null, null, null);

            cursor.moveToFirst();
            if(!cursor.isAfterLast()){
            sleep = cursorToSleeping(cursor);

            cursor.close();}
            else{
                //Test code Returning dummy sleep record if no record found
                Sleep sleepTest = new Sleep(946598400000L,0, 946598460000L, 946684740000L, 9);        // (31/12/1999, 00:01, 23:59, 9)
                return sleepTest;

            }
            db.close();
        }
        catch (SQLException e){
            Log.e("Get row error", e.toString());
            e.printStackTrace();
        }
        //db.close();
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
    protected Sleep cursorToSleeping(Cursor cursor) {
        Sleep sleeping  = new Sleep();
        sleeping.setID(cursor.getLong(0));                                 //amended to get long
        sleeping.setDate(Long.parseLong(cursor.getString(1)));
        //sleeping.setDate(dateR);                                        //for test - This is still returning 1/1/70, whatever is passed to getSleepRecordForDate()
        //sleeping.setDate(1426377600000L);                                   //more testing...
        sleeping.setTimeToBed(cursor.getLong(2));     //These int no's change by 1 if date included - DONE
        sleeping.setTimeUp(Long.parseLong(cursor.getString(3)));
        sleeping.setSleepRating(Integer.parseInt(cursor.getString(4)));
        //log
        Log.d("getSleepingRecord("+sleeping.getID()+")", sleeping.toString());
        return sleeping;
    }


}
