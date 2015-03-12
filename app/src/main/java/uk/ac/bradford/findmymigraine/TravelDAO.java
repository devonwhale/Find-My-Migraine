package uk.ac.bradford.findmymigraine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by George on 12/03/2015.
 */
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
        values.put(MySQLiteHelper.COLUMN_TRAVEL_HOURS, travel.getHours());
        values.put(MySQLiteHelper.COLUMN_TRAVEL_METHOD, travel.getMethod());
        values.put(MySQLiteHelper.COLUMN_TRAVEL_DESTINATION, travel.getDest());

        db.insert(MySQLiteHelper.TABLE_TRAVEL, null, values);
        db.close();
    }

    public Travel getTravelRecord(int id) {
        db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(MySQLiteHelper.TABLE_TRAVEL, MySQLiteHelper.COLUMNS_TRAVEL, " KEY_ID = ?",
                new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Travel travel = new Travel();

        return travel;
    }

}
