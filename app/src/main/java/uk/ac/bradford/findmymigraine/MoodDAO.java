package uk.ac.bradford.findmymigraine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by George on 05/03/2015.
 * Additional methods added by Steve 12/3/15.
 */
public class MoodDAO {

    public static final String TAG = "MoodDAO";

    // Database fields
    private SQLiteDatabase db;
    private MySQLiteHelper dbHelper;
    private Context mContext;


    public MoodDAO(Context context) {
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

    public void createMoodRecord(Mood mood) {
        Log.d("addMoodRecord", mood.toString());
        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_MOOD_FEELING, mood.getMood());
        values.put(MySQLiteHelper.COLUMN_MOOD_DATE, mood.getDate());        //Added by Steve 15/3/15

        db.insert(MySQLiteHelper.TABLE_MOOD, null, values);
        db.close();
    }

    //Method added by Steve 12/3/15
    public Mood getMoodRecordForDate(Long dateRequired){

        Long minDate = dateRequired-1000;
        Long maxDate = dateRequired+1000;
        db = dbHelper.getReadableDatabase();
        Mood mood = new Mood();                                              //Looks to be returning this EMPTY sleep record - Steve. 5/3/15 21:38
        Cursor cursor;
        try {
            cursor = db.query(MySQLiteHelper.TABLE_MOOD,
                    MySQLiteHelper.COLUMNS_MOOD,
                    MySQLiteHelper.COLUMN_MOOD_DATE + ">" + minDate + " AND " + MySQLiteHelper.COLUMN_MOOD_DATE + "<" + maxDate,
                    null, null, null, null);

            cursor.moveToFirst();
            if(!cursor.isAfterLast()){
                mood = cursorToMood(cursor);

                cursor.close();}

            db.close();
        }
        catch (SQLException e){
            Log.e("Get row error", e.toString());
            e.printStackTrace();
        }
        //db.close();
        return mood;
    }

    //Method added by Steve 12/3/15
    protected Mood cursorToMood(Cursor cursor) {
        Mood mood  = new Mood();
        mood.setId(Long.parseLong(cursor.getString(0)));
        mood.setDate(Long.parseLong(cursor.getString(1)));
        mood.setMood(Integer.parseInt(cursor.getString(2)));

        //log
        Log.d("getTravelRecord("+mood.getId()+")", mood.toString());
        return mood;
    }

/* method not used
    public Mood getMoodRecord(int id) {
        db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(MySQLiteHelper.TABLE_MOOD, MySQLiteHelper.COLUMNS_MOOD, " KEY_ID = ?",
                new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Mood mood = new Mood();

        return mood;
    }
*/
}
