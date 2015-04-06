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
 * This class is the Data Access Object for the 'Coping' table.
 * The 'Coping' table records details of when a migraine occurs.
 */
public class CopingDAO {

    public static final String TAG = "CopingDAO"; //This TAG is written

    //Database fields
    private SQLiteDatabase db;
    private MySQLiteHelper dbHelper;
    private Context mContext;

    public CopingDAO(Context context) {
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

    //Add new Coping Record
    public void createCopingRecord(Coping coping) {
        Log.d("addCopingRecord", coping.toString());
        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(MySQLiteHelper.COLUMN_COPING_DATE, coping.getDate());
        values.put(MySQLiteHelper.COLUMN_COPING_START, coping.getStartTime());
        values.put(MySQLiteHelper.COLUMN_COPING_YOGA, coping.getYoga());
        values.put(MySQLiteHelper.COLUMN_COPING_SLEEPING, coping.getSleep());
        values.put(MySQLiteHelper.COLUMN_COPING_MEDICATION, coping.getMedication());
        values.put(MySQLiteHelper.COLUMN_COPING_MEDITATION, coping.getMeditation());
        values.put(MySQLiteHelper.COLUMN_COPING_OTHER, coping.getOther());
        values.put(MySQLiteHelper.COLUMN_COPING_SYNCFLAG, coping.getSyncFlag());

        //Inserting row
        db.insert(MySQLiteHelper.TABLE_COPING, null, values);
        db.close();

    }
    //Get Single Coping Record
    public Coping getCopingRecord(Long startTime) {
        db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(MySQLiteHelper.TABLE_COPING,
                MySQLiteHelper.COLUMNS_COPING,
                MySQLiteHelper.COLUMN_COPING_START+"="+startTime,
                null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Coping coping = new Coping();
        coping = cursorToCoping(cursor);
        return coping;

    }

    //Get All Coping Table Records
    public List<Coping> getAllCopingRecords() {
        List<Coping> listCoping = new ArrayList<Coping>();

        Cursor cursor = db.query(MySQLiteHelper.TABLE_COPING, MySQLiteHelper.COLUMNS_COPING,
                null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Coping coping = cursorToCoping(cursor);
                listCoping.add(coping);
                cursor.moveToNext();
            }


            cursor.close();
        }
        return listCoping;
    }
    //The order is the way the columns have been inserted in the SQLite database
    protected Coping cursorToCoping(Cursor cursor) {
        Coping coping  = new Coping();
        coping.setId(Long.parseLong(cursor.getString(0)));
        coping.setDate(Long.parseLong(cursor.getString(1)));
        coping.setStartTime(Long.parseLong(cursor.getString(2)));
        coping.setYoga(Integer.parseInt(cursor.getString(4)));
        coping.setMedication(Integer.parseInt(cursor.getString(5)));
        coping.setMeditation(Integer.parseInt(cursor.getString(6)));
        coping.setSleep(Integer.parseInt(cursor.getString(7)));
        coping.setOther(cursor.getString(8)); //Not to sure about this
        coping.setSyncFlag(Integer.parseInt(cursor.getString(3)));


        //log
        Log.d("getCopingRecord("+coping.getId()+")", coping.toString());
        return coping;
    }


}
