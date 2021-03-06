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
 * Created by Imtanan on 05/03/2015.
 */
//The Causes Table's Data Access Object
public class CausesDAO {

    public final static String TAG = "CausesDAO"; //tag is written to identify any exceptions

    // Database fields
    private SQLiteDatabase db;
    private MySQLiteHelper dbHelper;
    private Context mContext;


    public CausesDAO(Context context) {
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
    public void createCausesRecord(Causes causes) {
        Log.d("addCausesRecord", causes.toString());
        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(MySQLiteHelper.COLUMN_CAUSES_DATE, causes.getDate());
        values.put(MySQLiteHelper.COLUMN_CAUSES_START, causes.getStartTime());
        values.put(MySQLiteHelper.COLUMN_CAUSES_STRESS, causes.getStress());
        values.put(MySQLiteHelper.COLUMN_CAUSES_LACK_OF_SLEEP, causes.getLack_of_sleep());
        values.put(MySQLiteHelper.COLUMN_CAUSES_LACK_OF_FOOD, causes.getLack_of_food());
        values.put(MySQLiteHelper.COLUMN_CAUSES_LACK_OF_WATER, causes.getLack_of_water());
        values.put(MySQLiteHelper.COLUMN_CAUSES_DEPRESSION, causes.getDepression());
        values.put(MySQLiteHelper.COLUMN_CAUSES_OTHER, causes.getOther());
        values.put(MySQLiteHelper.COLUMN_CAUSES_SYNCFLAG, causes.getSyncFlag());

        //Inserting row
        db.insert(MySQLiteHelper.TABLE_CAUSES, null, values);
        db.close();

    }

    //Get Single Causes Record
    public Causes getCausesRecord(Long startTime) {
        db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(MySQLiteHelper.TABLE_CAUSES, MySQLiteHelper.COLUMNS_CAUSES,
                MySQLiteHelper.COLUMN_CAUSES_START+"="+startTime, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Causes causes = new Causes();
        causes = cursorToCauses(cursor);    //calls method below
        return causes;

    }

    //Get All Causes Table Records
    public Causes[] getAllCausesRecords() {

        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(MySQLiteHelper.TABLE_CAUSES, MySQLiteHelper.COLUMNS_CAUSES,
                null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            int noOfRows = cursor.getCount();
            Causes[] causes = new Causes[noOfRows];
            if(!cursor.isAfterLast()) {
                for(int i=0; i<causes.length; i++){
                    causes[i] = cursorToCauses(cursor);
                    cursor.moveToNext();
                }
                cursor.close();
                db.close();
                return causes;
            }}
        Causes[] causes = new Causes[0];
        return causes;
    }


    protected Causes cursorToCauses(Cursor cursor) {
        Causes causes  = new Causes();
        causes.setId(Long.parseLong(cursor.getString(0)));
        causes.setDate(Long.parseLong(cursor.getString(1)));
        causes.setStartTime(Long.parseLong(cursor.getString(2)));
        causes.setStress(Integer.parseInt(cursor.getString(4)));
        causes.setLack_of_sleep(Integer.parseInt(cursor.getString(5)));
        causes.setLack_of_food(Integer.parseInt(cursor.getString(6)));
        causes.setLack_of_water(Integer.parseInt(cursor.getString(7)));
        causes.setDepression(Integer.parseInt(cursor.getString(8)));
        causes.setOther(cursor.getString(9)); //Not to sure about this
        causes.setSyncFlag(Integer.parseInt(cursor.getString(3)));

        //log
        Log.d("getCausesRecord("+causes.getId()+")", causes.toString());
        return causes;
    }

}
