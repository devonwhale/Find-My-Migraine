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
        values.put(MySQLiteHelper.COLUMN_ID, sleep.getID());
        values.put(MySQLiteHelper.COLUMN_TIME_TO_BED, sleep.getTimeToBed());
        values.put(MySQLiteHelper.COLUMN_TIME_UP, sleep.getTimeUp());
        values.put(MySQLiteHelper.COLUMN_SLEEP_RATING, sleep.getSleepRating());

        //Inserting row
        db.insert(MySQLiteHelper.TABLE_SLEEP, null, values);
        db.close();

    }

    //Get Single Sleep Record
    public Sleep getSleepingRecord(int id) {
        db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(MySQLiteHelper.TABLE_SLEEP, MySQLiteHelper.COLUMNS_SlEEP, " KEY_ID = ?",
                new String[] { String.valueOf(id) }, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Sleep sleeping = new Sleep();
        sleeping = cursorToSleeping(cursor);
        return sleeping;

    }

    //Get All Sleep Table Records
    public List<Sleep> getAllSleepingRecords() {
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
    }


    protected Sleep cursorToSleeping(Cursor cursor) {
        Sleep sleeping  = new Sleep();
        sleeping.setID(Long.parseLong(cursor.getString(0)));
        sleeping.setTimeToBed(Long.parseLong(cursor.getString(1)));
        sleeping.setTimeUp(Long.parseLong(cursor.getString(2)));
        sleeping.setSleepRating(Integer.parseInt(cursor.getString(3)));
        //log
        Log.d("getSleepingRecord("+sleeping.getID()+")", sleeping.toString());
        return sleeping;
    }


}
