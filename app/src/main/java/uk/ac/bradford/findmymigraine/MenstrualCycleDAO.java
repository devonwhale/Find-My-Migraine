package uk.ac.bradford.findmymigraine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Sumaia on 02/04/2015.
 */
public class MenstrualCycleDAO {
    public static final String TAG = "MenstrualCycleDAO";

    // Database fields
    private SQLiteDatabase db;
    private MySQLiteHelper dbHelper;
    private Context mContext;


    public MenstrualCycleDAO(Context context) {
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


    //Add new MenstrualCycle Record
    public void createMenstrualCycleRecord(MenstrualCycle mc) {
        Log.d("addMenstrualCycleRecord", mc.toString());
        //db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_MENSTRUAL_CYCLE_DATE, mc.getDate());
        values.put(MySQLiteHelper.COLUMN_MENSTRUAL_CYCLE_SYNCFLAG, mc.getSyncFlag());
        values.put(MySQLiteHelper.COLUMN_MENSTRUAL_CYCLE_YES, mc.getYes());
        values.put(MySQLiteHelper.COLUMN_MENSTRUAL_CYCLE_NO, mc.getNo());
        values.put(MySQLiteHelper.COLUMN_MENSTRUAL_CYCLE_COMINGSOON, mc.getComingSoon());

        //Inserting row
        db.insertWithOnConflict(MySQLiteHelper.TABLE_MENSTRUAL_CYCLE, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        db.close();

    }


    //Get Single Menstrual Cycle Record, for a given date
    public MenstrualCycle getMenstrualCycleRecordForDate(Long dateRequired){

        Long minDate = dateRequired-1000;
        Long maxDate = dateRequired+1000;
        db = dbHelper.getReadableDatabase();
        MenstrualCycle mc = new MenstrualCycle();
        Cursor cursor;
        try {
            cursor = db.query(MySQLiteHelper.TABLE_MENSTRUAL_CYCLE,
                    MySQLiteHelper.COLUMNS_MENSTRUAL_CYCLE,
                    MySQLiteHelper.COLUMN_MENSTRUAL_CYCLE_DATE + ">" + minDate + " AND " + MySQLiteHelper.COLUMN_MENSTRUAL_CYCLE_DATE + "<" + maxDate,
                    null, null, null, null);

            cursor.moveToFirst();
            if(!cursor.isAfterLast()){
                mc = cursorToMenstrualCycle(cursor);

                cursor.close();}
            else
                Log.d("getMenstrualCycleRecordForDate","No MenstrualCycle record found for this date");

            db.close();
        }
        catch (SQLException e){
            Log.e("Get row error", e.toString());
            e.printStackTrace();
        }
        //db.close();
        return mc;
    }

    public MenstrualCycle[] getAllMenstrualCycleRecords(){

        db = dbHelper.getReadableDatabase();
        MenstrualCycle[] mc;
        Cursor cursor;
        try {
            cursor = db.query(MySQLiteHelper.TABLE_MENSTRUAL_CYCLE,
                    MySQLiteHelper.COLUMNS_MENSTRUAL_CYCLE,
                    null,null, null, null, null);

            cursor.moveToFirst();
            int noOfRows = cursor.getCount();
            mc = new MenstrualCycle[noOfRows];
            if(!cursor.isAfterLast()){
                for(int i=0; i<mc.length; i++){
                    mc[i] = cursorToMenstrualCycle(cursor);
                    cursor.moveToNext();
                }

                cursor.close();}
            else
                Log.d("getMenstrualCycleRecordForDate","No MenstrualCycle record found for this date");

            db.close();
        }
        catch (SQLException e){
            Log.e("Get row error", e.toString());
            e.printStackTrace();
        }
        //db.close();
        mc = new MenstrualCycle[0];
        return mc;
    }


    //Method to return Menstrual Cycle object from cursor in database
    protected MenstrualCycle cursorToMenstrualCycle(Cursor cursor) {
        MenstrualCycle mc  = new MenstrualCycle();
        mc.setId(cursor.getLong(0));
        mc.setDate(Long.parseLong(cursor.getString(1)));
        mc.setYes(Integer.parseInt(cursor.getString(3)));
        mc.setNo(Integer.parseInt(cursor.getString(4)));
        mc.setComingSoon(Integer.parseInt(cursor.getString(5)));

        //log
        Log.d("getMenstrualCycleRecord("+mc.getId()+")", mc.toString());
        return mc;
    }

}
