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
 * Created by Steve on 24/02/2015.
 * This class is the Data Access Object for the 'When' table.
 * The 'When' table records details of when a migraine occurs.
 */

//The When Table's Data Access Object
public class WhenDAO {
    public final static String TAG = "WhenDAO"; //tag is written to identify any exceptions

    // Database fields
    private SQLiteDatabase db;
    private MySQLiteHelper dbHelper;
    private Context mContext;


    public WhenDAO(Context context) {
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

    //Add new When Record
    public void createWhenRecord(When when) {
        Log.d("addWhenRecord", when.toString());
        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(MySQLiteHelper.COLUMN_WHEN_DATE, when.getDate());
        values.put(MySQLiteHelper.COLUMN_WHEN_START_TIME, when.getStart_time());
        values.put(MySQLiteHelper.COLUMN_WHEN_END_TIME, when.getEnd_time());
        values.put(MySQLiteHelper.COLUMN_WHEN_SYNCFLAG, when.getSyncFlag());
        values.put(MySQLiteHelper.COLUMN_WHEN_INTENSITY, when.getIntensity());

        //Inserting row
        db.insert(MySQLiteHelper.TABLE_WHEN, null, values);
        db.close();

    }

    //Get Single When Record
    public When getWhenRecord(Long id) {
        db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(MySQLiteHelper.TABLE_WHEN,
                MySQLiteHelper.COLUMNS_WHEN,
                MySQLiteHelper.COLUMN_WHEN_ID + "=" + id,
                null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        When when = new When();
        when = cursorToWhen(cursor);    //calls method below
        return when;

    }

    //Get All When Table Records
    public When[] getAllWhenRecords() {
        db = dbHelper.getReadableDatabase();
        When[] records;

        Cursor cursor = db.query(MySQLiteHelper.TABLE_WHEN, MySQLiteHelper.COLUMNS_WHEN,
                null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            //How many records?
            int noOfRows = cursor.getCount();
            records = new When[noOfRows];
            for (int i=0; i<noOfRows; i++){
                if(!cursor.isAfterLast()){
                    records[i] = cursorToWhen(cursor);
                    cursor.moveToNext();
                }}
            cursor.close();
            db.close();
            return records;

        }
        records = new When[0];
        return records;
    }

    //public When[] getAllWhenRecords


    protected When cursorToWhen(Cursor cursor) {
        When when  = new When();
        when.setId(Long.parseLong(cursor.getString(0)));
        when.setDate(Long.parseLong(cursor.getString(1)));
        when.setStart_time(Long.parseLong(cursor.getString(2)));
        when.setEnd_time(Long.parseLong(cursor.getString(3)));
        when.setSyncFlag(Integer.parseInt(cursor.getString(4)));
        when.setIntensity(Integer.parseInt(cursor.getString(5)));

        //log
        Log.d("When Record extracted from cursor("+when.getId()+")", when.toString());
        return when;
    }

}
