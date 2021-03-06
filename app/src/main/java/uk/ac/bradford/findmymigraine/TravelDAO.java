package uk.ac.bradford.findmymigraine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by George on 12/03/2015.
 * Additional methods added by Steve 12/3/15.
 */
//The Travel Table's Data Access Object
public class TravelDAO {

    public static final String TAG = "TravelDAO";

    // Database fields
    private SQLiteDatabase db;
    private MySQLiteHelper dbHelper;
    private Context mContext;


    public TravelDAO(Context context) {
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

    public void createTravelRecord(Travel travel) {
        Log.d("addTravelRecord", travel.toString());
        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_TRAVEL_DATE, travel.getDate());
        values.put(MySQLiteHelper.COLUMN_TRAVEL_HOURS, travel.getHours());
        values.put(MySQLiteHelper.COLUMN_TRAVEL_METHOD, travel.getMethod());
        values.put(MySQLiteHelper.COLUMN_TRAVEL_DESTINATION, travel.getDest());

        db.insert(MySQLiteHelper.TABLE_TRAVEL, null, values);
        db.close();
    }

    //method added by Steve - 12/3/15
    //Amended 3/4/15 to return array of records
    public Travel[] getTravelRecordsForDate(Long dateRequired){

        Long minDate = dateRequired-1000;
        Long maxDate = dateRequired+1000;
        db = dbHelper.getReadableDatabase();
        Travel[] travel;// = new Travel();             initialise in try block
        Cursor cursor;
        try {
            cursor = db.query(MySQLiteHelper.TABLE_TRAVEL,
                    MySQLiteHelper.COLUMNS_TRAVEL,
                    MySQLiteHelper.COLUMN_TRAVEL_DATE + ">" + minDate + " AND " + MySQLiteHelper.COLUMN_TRAVEL_DATE + "<" + maxDate,
                    null, null, null, null);

            cursor.moveToFirst();
            //How many records?
            int noOfRows = cursor.getCount();
            travel = new Travel[noOfRows];
            for (int i=0; i<noOfRows; i++){
            if(!cursor.isAfterLast()){
                travel[i] = cursorToTravel(cursor);
                cursor.moveToNext();
            }}
            cursor.close();
            db.close();
            return travel;
        }
        catch (SQLException e){
            Log.e("Get row error", e.toString());
            e.printStackTrace();
        }
        //db.close();
        travel = new Travel[0]; //if try block fails, return empty array
        return travel;
    }

    public Travel[] getAllTravelRecords(){

        db = dbHelper.getReadableDatabase();
        Travel[] travel;// = new Travel();             initialise in try block
        Cursor cursor;
        try {
            cursor = db.query(MySQLiteHelper.TABLE_TRAVEL,
                    MySQLiteHelper.COLUMNS_TRAVEL,
                    null,
                    null, null, null, null);

            cursor.moveToFirst();
            //How many records?
            int noOfRows = cursor.getCount();
            travel = new Travel[noOfRows];
            for (int i=0; i<noOfRows; i++){
                if(!cursor.isAfterLast()){
                    travel[i] = cursorToTravel(cursor);
                    cursor.moveToNext();
                }}
            cursor.close();
            db.close();
            return travel;
        }
        catch (SQLException e){
            Log.e("Get row error", e.toString());
            e.printStackTrace();
        }
        //db.close();
        travel = new Travel[0]; //if try block fails, return empty array
        return travel;
    }

    //method added by Steve 12/3/15
    protected Travel cursorToTravel(Cursor cursor) {
        Travel travel  = new Travel();
        travel.setId(Long.parseLong(cursor.getString(0)));
        travel.setDate(Long.parseLong(cursor.getString(1)));
        travel.setHours(Double.parseDouble(cursor.getString(2)));
        travel.setMethod(cursor.getString(3));
        travel.setDest(cursor.getString(4));

        //log
        Log.d("getTravelRecord("+travel.getId()+")", travel.toString());
        return travel;
    }

/*  method not used
    public Travel getTravelRecord(int id) {
        db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(MySQLiteHelper.TABLE_TRAVEL, MySQLiteHelper.COLUMNS_TRAVEL, " KEY_ID = ?",
                new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Travel travel = new Travel();

        return travel;
    }
*/
}
