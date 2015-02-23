package uk.ac.bradford.findmymigraine;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Sumaia on 28/01/2015.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "migraine.db";

    //Sleep Table Details
    public static final String TABLE_SLEEP = "sleeping";
    public static final String COLUMN_SLEEP_ID = "_id";
    public static final String COLUMN_TIME_TO_BED = "timeToBed";
    public static final String COLUMN_TIME_UP= "timeUp";
    public static final String COLUMN_SLEEP_RATING= "sleepRating";
    public static final String[] COLUMNS_SlEEP = {COLUMN_SLEEP_ID,COLUMN_TIME_TO_BED,COLUMN_TIME_UP,COLUMN_SLEEP_RATING};

    //String containing query for building sleep table
    private static final String CREATE_SLEEPING_TABLE = "CREATE TABLE " +
            TABLE_SLEEP + "("
            + COLUMN_SLEEP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_TIME_TO_BED + " INTEGER,"
            + COLUMN_TIME_UP + " INTEGER,"
            + COLUMN_SLEEP_RATING + " INTEGER" + ")";

    //Exercise Table Details
    public static final String TABLE_EXERCISE = "exercise";
    public static final String COLUMN_EXERCISE_ID = "_id";
    public static final String COLUMN_HOURS = "hours";
    public static final String COLUMN_INTENSITY= "intensity";
    public static final String[] COLUMNS_EXERCISE = {COLUMN_EXERCISE_ID,COLUMN_HOURS,COLUMN_INTENSITY};

    //String containing query for building exercise table
    private static final String CREATE_EXERCISE_TABLE = "CREATE TABLE " +
            TABLE_EXERCISE + "("
            + COLUMN_EXERCISE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_HOURS + " INTEGER,"
            + COLUMN_INTENSITY+ " INTEGER"+ ")";

    public MySQLiteHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SLEEPING_TABLE);
        db.execSQL(CREATE_EXERCISE_TABLE);
    }

    //Needs fixing
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(), "Upgrading from version" + oldVersion + " to "+ newVersion+", which will destroy old data");
        db.execSQL("DROP TABLE IF EXISTS"+ TABLE_SLEEP);
        db.execSQL("DROP TABLE IF EXISTS"+ TABLE_EXERCISE);
        onCreate(db);
    }
}
