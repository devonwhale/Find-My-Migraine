package uk.ac.bradford.findmymigraine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by George on 16/04/2015.
 */
public class IntensityDAO {

    public static final String TAG = "IntensityDAO";

    private SQLiteDatabase db;
    private MySQLiteHelper dbHelper;
    private Context mContext;

    public IntensityDAO(Context context) {
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


    //Add new Intensity Record
    public void createIntensityRecord(Intensity intensity) {
        Log.d("addIntensityRecord", intensity.toString());
        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_COPING_DATE, intensity.getDate());
        values.put(MySQLiteHelper.COLUMN_INTENSITY_INTENSITY, intensity.getIntensity());
        values.put(MySQLiteHelper.COLUMN_COPING_SYNCFLAG, intensity.getSyncFlag());


        //Inserting row
        db.insert(MySQLiteHelper.TABLE_INTENSITY, null, values);
        db.close();

    }

    public Intensity getIntensityRecord(Long id) {
        db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(MySQLiteHelper.TABLE_WHEN,
                MySQLiteHelper.COLUMNS_WHEN,
                MySQLiteHelper.COLUMN_WHEN_ID + "=" + id,
                null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Intensity intensity = new Intensity();
        intensity = cursorToIntensity(cursor);    //calls method below
        return intensity;

    }

    public Intensity[] getIntensityRecordsForDate(Long dateRequired){
        Log.d("Date required received by getIntensityRecordForDate():",dateRequired.toString()); //On test, correct Long coming through
        Long minDate = dateRequired-1000;
        Long maxDate = dateRequired+1000;
        db = dbHelper.getReadableDatabase();
        Intensity[] intensity;// = new Exercise[array initialised in try block];                                              //Looks to be returning this EMPTY sleep record - Steve. 5/3/15 21:38
        Cursor cursor;
        try {
            cursor = db.query(MySQLiteHelper.TABLE_INTENSITY,
                    MySQLiteHelper.COLUMNS_INTENSITY,
                    MySQLiteHelper.COLUMN_INTENSITY_DATE + ">" + minDate + " AND " + MySQLiteHelper.COLUMN_EXERCISE_DATE + "<" + maxDate,
                    null, null, null, null);

            cursor.moveToFirst();
            //How many records?
            int noOfRows = cursor.getCount();
            intensity = new Intensity[noOfRows];
            for (int i=0; i<noOfRows; i++){
                if(!cursor.isAfterLast()) {

                    intensity[i] = cursorToIntensity(cursor);
                    cursor.moveToNext();
                }
            }
            cursor.close();
            db.close();
            return intensity;
        }
        catch (SQLException e){
            Log.e("Get row error", e.toString());
            e.printStackTrace();
        }
        //if no records found:
        intensity = new Intensity[0];
        return intensity;
    }

    protected Intensity cursorToIntensity(Cursor cursor) {
        Intensity intensity  = new Intensity();
        intensity.setId(Long.parseLong(cursor.getString(0)));
        intensity.setIntensity(cursor.getString(1));

        //log
        Log.d("When Record extracted from cursor("+intensity.getId()+")", intensity.toString());
        return intensity;
    }































    public Intensity[] getAllIntensityRecords(){

        db = dbHelper.getReadableDatabase();
        Intensity[] intensity;// = new Exercise[array initialised in try block];                                              //Looks to be returning this EMPTY sleep record - Steve. 5/3/15 21:38
        Cursor cursor;
        try {
            cursor = db.query(MySQLiteHelper.TABLE_INTENSITY,
                    MySQLiteHelper.COLUMNS_INTENSITY,
                    null, null, null, null, null);

            cursor.moveToFirst();
            //How many records?
            int noOfRows = cursor.getCount();
            intensity = new Intensity[noOfRows];
            for (int i=0; i<noOfRows; i++){
                if(!cursor.isAfterLast()) {

                    intensity[i] = cursorToIntensity(cursor);
                    cursor.moveToNext();
                }
            }
            cursor.close();
            db.close();
            return intensity;
        }
        catch (SQLException e){
            Log.e("Get row error", e.toString());
            e.printStackTrace();
        }
        //if no records found:
        intensity = new Intensity[0];
        return intensity;
    }

}
