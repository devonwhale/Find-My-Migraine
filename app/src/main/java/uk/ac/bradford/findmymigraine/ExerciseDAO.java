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
 * Created by Sumaia on 23/02/2015.
 */

//The Exercise Table's Data Access Object
public class ExerciseDAO {

    public static final String TAG = "ExerciseDAO";

    // Database fields
    private SQLiteDatabase db;
    private MySQLiteHelper dbHelper;
    private Context mContext;


    public ExerciseDAO(Context context) {
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


    //Add new Exercise Record
    public void createExerciseRecord(Exercise exercise) {
        Log.d("addExerciseRecord", exercise.toString());
        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(MySQLiteHelper.COLUMN_HOURS, exercise.getHours());
        values.put(MySQLiteHelper.COLUMN_INTENSITY, exercise.getIntensity());

        //Inserting row
        db.insert(MySQLiteHelper.TABLE_EXERCISE, null, values);
        db.close();

    }

    //Get Single Exercise Record
    public Exercise getSleepingRecord(int id) {
        db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(MySQLiteHelper.TABLE_EXERCISE, MySQLiteHelper.COLUMNS_SlEEP, " KEY_ID = ?",
                new String[] { String.valueOf(id) }, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Exercise exercise = new Exercise();
        exercise = cursorToExercise(cursor);
        return exercise;

    }

    //Get All Exercise Table Records
    public List<Exercise> getAllExerciseRecords() {
        List<Exercise> listExercise = new ArrayList<Exercise>();

        Cursor cursor = db.query(MySQLiteHelper.TABLE_EXERCISE, MySQLiteHelper.COLUMNS_EXERCISE,
                null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Exercise exercise = cursorToExercise(cursor);
                listExercise.add(exercise);
                cursor.moveToNext();
            }


            cursor.close();
        }
        return listExercise;
    }


    protected Exercise cursorToExercise(Cursor cursor) {
        Exercise exercise  = new Exercise();
        exercise.setID(Long.parseLong(cursor.getString(0)));
        exercise.setHours(Double.parseDouble(cursor.getString(1)));
        exercise.setIntensity(Integer.parseInt(cursor.getString(2)));

        //log
        Log.d("getExerciseRecord("+exercise.getID()+")", exercise.toString());
        return exercise;
    }


}
