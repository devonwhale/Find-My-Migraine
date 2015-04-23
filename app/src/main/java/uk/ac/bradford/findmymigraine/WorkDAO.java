package uk.ac.bradford.findmymigraine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Sumaia on 17/04/2015.
 */

//The Work Table's Data Access Object
public class WorkDAO {
    public static final String TAG = "WorkDAO";

    // Database fields
    private SQLiteDatabase db;
    private MySQLiteHelper dbHelper;
    private Context mContext;


    public WorkDAO(Context context) {
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

    public void createWorkRecord(Work work) {
        Log.d("addWorkRecord", work.toString());
        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_WORK_DATE, work.getDate());
        values.put(MySQLiteHelper.COLUMN_WORK_SYNCFLAG, work.getSyncFlag());
        values.put(MySQLiteHelper.COLUMN_WORK_HOURS, work.getHours());
        values.put(MySQLiteHelper.COLUMN_WORK_STRESS, work.getStress());


        db.insert(MySQLiteHelper.TABLE_WORK, null, values);
        db.close();
    }

    //Method to return all work records for particular date  - amended by Steve 19/4 from method to return a single work record.
    public Work[] getWorkRecordsForDate(Long dateRequired){

        Long minDate = dateRequired-1000;
        Long maxDate = dateRequired+1000;
        db = dbHelper.getReadableDatabase();
        Work[] work;// = new Work();                                              //Looks to be returning this EMPTY sleep record - Steve. 5/3/15 21:38
        Cursor cursor;
        try {
            cursor = db.query(MySQLiteHelper.TABLE_WORK,
                    MySQLiteHelper.COLUMNS_WORK,
                    MySQLiteHelper.COLUMN_WORK_DATE + ">" + minDate + " AND " + MySQLiteHelper.COLUMN_WORK_DATE + "<" + maxDate,
                    null, null, null, null);

            cursor.moveToFirst();
            //How many records?
            int noOfRows = cursor.getCount();
            work = new Work[noOfRows];
            for (int i=0; i<noOfRows; i++){
                if(!cursor.isAfterLast()) {

                    work[i] = cursorToWork(cursor);
                    cursor.moveToNext();
                }
            }
            cursor.close();
            db.close();
            return work;
        }
        catch (SQLException e){
            Log.e("Get row error", e.toString());
            e.printStackTrace();
        }
        //db.close();
        work = new Work[0];
        return work;
    }

    public Work[] getAllWorkRecords(){

        db = dbHelper.getReadableDatabase();
        Work[] work;// = new Work();             initialise in try block
        Cursor cursor;
        try {
            cursor = db.query(MySQLiteHelper.TABLE_WORK,
                    MySQLiteHelper.COLUMNS_WORK,
                    null,
                    null, null, null, null);

            cursor.moveToFirst();
            //How many records?
            int noOfRows = cursor.getCount();
            work = new Work[noOfRows];
            for (int i=0; i<noOfRows; i++){
                if(!cursor.isAfterLast()){
                    work[i] = cursorToWork(cursor);
                    cursor.moveToNext();
                }}
            cursor.close();
            db.close();
            return work;
        }
        catch (SQLException e){
            Log.e("Get row error", e.toString());
            e.printStackTrace();
        }
        //db.close();
        work = new Work[0]; //if try block fails, return empty array
        return work;
    }


    protected Work cursorToWork(Cursor cursor) {
        Work work  = new Work();
        work.setId(Long.parseLong(cursor.getString(0)));
        work.setDate(Long.parseLong(cursor.getString(1)));
        work.setSyncFlag(Integer.parseInt(cursor.getString(2))); //added by Steve 19/4 - int increased by 1 on next two
        work.setHours(Double.parseDouble(cursor.getString(3)));
        work.setStress(Integer.parseInt(cursor.getString(4)));

        //log
        Log.d("getWorkRecord("+work.getId()+")", work.toString());
        return work;
    }
}
