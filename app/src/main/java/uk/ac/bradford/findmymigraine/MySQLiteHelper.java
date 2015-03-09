package uk.ac.bradford.findmymigraine;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Sumaia on 28/01/2015.
 * N.B. ALL TABLES MUST HAVE THE FOLLOWING THREE COLUMNS (IN ADDITION TO ANY OTHERS):
 *  - _ID which must be set to auto increment
 *  - DATE for the date of the details being recorded - used for searching and graphing comparable events
 *  - SYNCFLAG for synchronisation status with external database
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
    public static final String COLUMN_SLEEP_SYNCFLAG= "syncFlag";
    public static final String[] COLUMNS_SlEEP = {COLUMN_SLEEP_ID,COLUMN_TIME_TO_BED,COLUMN_TIME_UP,COLUMN_SLEEP_RATING,COLUMN_SLEEP_SYNCFLAG};

    //String containing query for building sleep table
    private static final String CREATE_SLEEPING_TABLE = "CREATE TABLE " +
            TABLE_SLEEP + "("
            + COLUMN_SLEEP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_TIME_TO_BED + " INTEGER,"
            + COLUMN_TIME_UP + " INTEGER,"
            + COLUMN_SLEEP_RATING + " INTEGER,"
            + COLUMN_SLEEP_SYNCFLAG + " INTEGER"+ ")";

    //Exercise Table Details
    public static final String TABLE_EXERCISE = "exercise";
    public static final String COLUMN_EXERCISE_ID = "_id";
    public static final String COLUMN_HOURS = "hours";
    public static final String COLUMN_INTENSITY= "intensity";
    public static final String COLUMN_EXERCISE_SYNCFLAG= "syncFlag";
    public static final String[] COLUMNS_EXERCISE = {COLUMN_EXERCISE_ID,COLUMN_HOURS,COLUMN_INTENSITY,COLUMN_EXERCISE_SYNCFLAG};

    //String containing query for building exercise table
    private static final String CREATE_EXERCISE_TABLE = "CREATE TABLE " +
            TABLE_EXERCISE + "("
            + COLUMN_EXERCISE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_HOURS + " INTEGER,"
            + COLUMN_INTENSITY+ " INTEGER,"
            + COLUMN_EXERCISE_SYNCFLAG + " INTEGER"+ ")";



    //When Table Details
    public static final String TABLE_WHEN = "attackWhen";
    public static final String COLUMN_WHEN_ID = "_id";
    public static final String COLUMN_WHEN_DATE = "date";
    public static final String COLUMN_WHEN_START_TIME = "start";
    public static final String COLUMN_WHEN_END_TIME = "end";
    public static final String COLUMN_WHEN_SYNCFLAG = "syncFlag";
    public static final String[] COLUMNS_WHEN = {COLUMN_WHEN_ID,COLUMN_WHEN_DATE,COLUMN_WHEN_START_TIME,COLUMN_WHEN_END_TIME,COLUMN_WHEN_SYNCFLAG};

    //String containing query for building When table
    private static final String CREATE_WHEN_TABLE = "CREATE TABLE " +
            TABLE_WHEN + "("
            + COLUMN_WHEN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_WHEN_DATE + " INTEGER, "
            + COLUMN_WHEN_START_TIME + " INTEGER, "
            + COLUMN_WHEN_END_TIME + " INTEGER, "
            + COLUMN_WHEN_SYNCFLAG + " INTEGER" + ")";

    //Severity Table Details
        //Steve to code

    //String containing query for building Severity table
        //Steve to code

    //Travel Table Details
    public static final String TABLE_TRAVEL = "travel";
    public static final String COLUMN_TRAVEL_ID = "_id";
    public static final String COLUMN_TRAVEL_DATE = "date";
    public static final String COLUMN_TRAVEL_HOURS = "hours";
    public static final String COLUMN_TRAVEL_METHOD = "method";
    public static final String COLUMN_TRAVEL_DESTINATION = "destination";
    public static final String COLUMN_TRAVEL_SYNCFLAG = "syncFlag";
    public static final String[] COLUMNS_TRAVEL = {COLUMN_TRAVEL_ID, COLUMN_TRAVEL_DATE, COLUMN_TRAVEL_HOURS, COLUMN_TRAVEL_METHOD, COLUMN_TRAVEL_DESTINATION, COLUMN_TRAVEL_SYNCFLAG};

    //String containing query for building Travel Table
    private static final String CREATE_TRAVEL_TABLE = "CREATE TABLE " +
            TABLE_TRAVEL +"("
            + COLUMN_TRAVEL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_TRAVEL_DATE + " INTEGER, "
            + COLUMN_TRAVEL_HOURS + " INTEGER, "
            + COLUMN_TRAVEL_METHOD + " VARCHAR, "
            + COLUMN_TRAVEL_DESTINATION + " VARCHAR, "
            + COLUMN_TRAVEL_SYNCFLAG + " INTEGER" + ")";

    //Mood Table Details
    public static final String TABLE_MOOD = "mood";
    public static final String COLUMN_MOOD_ID = "_id";
    public static final String COLUMN_MOOD_DATE = "date";
    public static final String COLUMN_MOOD_FEELING = "feeling";
    public static final String COLUMN_MOOD_SYNCFLAG = "syncFlag";
    public static final String[] COLUMNS_MOOD = {COLUMN_MOOD_ID, COLUMN_MOOD_DATE, COLUMN_MOOD_FEELING, COLUMN_MOOD_SYNCFLAG};

    //String containing query for building Mood Table
    private static final String CREATE_MOOD_TABLE = "CREATE TABLE " +
            TABLE_MOOD + "("
            + COLUMN_MOOD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_MOOD_DATE + " INTEGER, "
            + COLUMN_MOOD_FEELING + " INTEGER, "
            + COLUMN_MOOD_SYNCFLAG + " INTEGER" + ")";

    /*
    MySQLiteHelper constructor creates the database if it does not exist.
        e.g. on first installation.
     */

    public MySQLiteHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SLEEPING_TABLE);
        db.execSQL(CREATE_EXERCISE_TABLE);
        db.execSQL(CREATE_WHEN_TABLE);
        db.execSQL(CREATE_TRAVEL_TABLE);
        db.execSQL(CREATE_MOOD_TABLE);
    }

    /*
    onUpgrade method currently set to drop all tables and then create tables based upon the code above.
    This will require amendment when data is to be saved following upgrade.
        Steve to do separate document.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(), "Upgrading from version" + oldVersion + " to "+ newVersion+", which will destroy old data");
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_SLEEP);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_EXERCISE);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_WHEN);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_TRAVEL);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_MOOD);
        onCreate(db);
    }
}
