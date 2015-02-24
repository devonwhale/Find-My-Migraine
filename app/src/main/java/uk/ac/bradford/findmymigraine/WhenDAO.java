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

        //Inserting row
        db.insert(MySQLiteHelper.TABLE_WHEN, null, values);
        db.close();

    }

    //Get Single When Record
    public When getWhenRecord(int id) {
        db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(MySQLiteHelper.TABLE_WHEN, MySQLiteHelper.COLUMNS_WHEN, " KEY_ID = ?",
                new String[] { String.valueOf(id) }, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        When when = new When();
        when = cursorToWhen(cursor);    //calls method below
        return when;

    }

    //Get All Exercise Table Records
    public List<When> getAllWhenRecords() {
        List<When> listWhen = new ArrayList<When>();

        Cursor cursor = db.query(MySQLiteHelper.TABLE_WHEN, MySQLiteHelper.COLUMNS_WHEN,
                null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                When when = cursorToWhen(cursor);
                listWhen.add(when);
                cursor.moveToNext();
            }


            cursor.close();
        }
        return listWhen;
    }


    protected When cursorToWhen(Cursor cursor) {
        When when  = new When();
        when.setId(Long.parseLong(cursor.getString(0)));
        when.setDate(Long.parseLong(cursor.getString(1)));
        when.setStart_time(Long.parseLong(cursor.getString(2)));
        when.setEnd_time(Long.parseLong(cursor.getString(3)));
        when.setSyncFlag(Integer.parseInt(cursor.getString(4)));

        //log
        Log.d("getExerciseRecord("+when.getId()+")", when.toString());
        return when;
    }

}
