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
    private static final int DATABASE_VERSION = 2;              //Date fields added to Sleeping and Exercise
    private static final String DATABASE_NAME = "migraine.db";

    //Sleep Table Details
    public static final String TABLE_SLEEP = "sleep_new";
    public static final String COLUMN_SLEEP_ID = "_id";
    public static final String COLUMN_SLEEP_DATE = "wakeDate";
    public static final String COLUMN_TIME_TO_BED = "timeToBed";
    public static final String COLUMN_TIME_UP= "timeUp";
    public static final String COLUMN_SLEEP_RATING= "sleepRating";
    public static final String COLUMN_SLEEP_SYNCFLAG= "syncFlag";
    public static final String[] COLUMNS_SlEEP = {COLUMN_SLEEP_ID,
            COLUMN_SLEEP_DATE,
            COLUMN_TIME_TO_BED,COLUMN_TIME_UP,COLUMN_SLEEP_RATING,COLUMN_SLEEP_SYNCFLAG};

    //String containing query for building sleep table
    private static final String CREATE_SLEEPING_TABLE = "CREATE TABLE " +
            TABLE_SLEEP + "("
            + COLUMN_SLEEP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_SLEEP_DATE + " INTEGER, "
            + COLUMN_TIME_TO_BED + " INTEGER, "
            + COLUMN_TIME_UP + " INTEGER, "
            + COLUMN_SLEEP_RATING + " INTEGER, "
            + COLUMN_SLEEP_SYNCFLAG + " INTEGER" + ")";

    //Exercise Table Details
    public static final String TABLE_EXERCISE = "exercise";
    public static final String COLUMN_EXERCISE_ID = "_id";
    public static final String COLUMN_EXERCISE_DATE = "date";
    public static final String COLUMN_HOURS = "hours";
    public static final String COLUMN_INTENSITY= "intensity";
    public static final String COLUMN_EXERCISE_SYNCFLAG= "syncFlag";
    public static final String[] COLUMNS_EXERCISE = {COLUMN_EXERCISE_ID,
            COLUMN_EXERCISE_DATE,
            COLUMN_HOURS,COLUMN_INTENSITY,COLUMN_EXERCISE_SYNCFLAG};

    //String containing query for building exercise table
    private static final String CREATE_EXERCISE_TABLE = "CREATE TABLE " +
            TABLE_EXERCISE + "("
            + COLUMN_EXERCISE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_EXERCISE_DATE + " INTEGER,"
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
    /*

    //Guys I made the table for coping strategies but before I uncommented and adn actually pushed it I wanted you guys to see if this is done properly
    //and if it is just uncomment the parts which are necessary.

    //Coping Strategies Table Details
    public static final String TABLE_COPING = "coping";
    public static final String COLUMN_COPING_ID = "_id";
    public static final String COLUMN_COPING_DATE = "date";
    public static final String COLUMN_COPING_SYNCFLAG = "syncFlag";
    public static final String COLUMN_COPING_YOGA = "yoga";
    public static final String COLUMN_COPING_MEDICATION = "medication";
    public static final String COLUMN_COPING_MEDITATION = "meditation";
    public static final String COLUMN_COPING_SLEEPING = "sleeping";
    public static final String COLUMN_COPING_OTHER = "other";

    public static final String[] COLUMNS_COPING = {COLUMN_COPING_ID,COLUMN_COPING_DATE,COLUMN_COPING_SYNCFLAG,COLUMN_COPING_YOGA,COLUMN_COPING_MEDICATION, COLUMN_COPING_MEDITATION, COLUMN_COPING_SLEEPING, COLUMN_COPING_OTHER};

    //String containing query for building COPING table
    private static final String CREATE_COPING_TABLE = "CREATE TABLE " +
            TABLE_COPING + "("
            + COLUMN_COPING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_COPING_DATE + " INTEGER, "
            + COLUMN_COPING_SYNCFLAG + " INTEGER, "
            + COLUMN_COPING_YOGA + " INTEGER, "
            + COLUMN_COPING_MEDICATION + " INTEGER, "
            + COLUMN_COPING_MEDITATION + " INTEGER, "
            + COLUMN_COPING_SLEEPING + " INTEGER, "
            + COLUMN_COPING_OTHER + " TEXT" + ")";

     */
        /*

        //CAUSES Strategies Table Details
    public static final String TABLE_CAUSES = "causes";
    public static final String COLUMN_CAUSES_ID = "_id";
    public static final String COLUMN_CAUSES_DATE = "date";
 public static final String COLUMN_CAUSES_SYNCFLAG = "syncFlag";
    public static final String COLUMN_CAUSES_STRESS = "stress";
    public static final String COLUMN_CAUSES_LACK_OF_SLEEP = "lack of sleep";
public static final String COLUMN_CAUSES_LACK_OF_FOOD = "lack of food";
public static final String COLUMN_CAUSES_LACK_OF_WATER = "lack of water";
public static final String COLUMN_CAUSES_DEPRESSION  = "depression";
public static final String COLUMN_CAUSES_OTHER  = "other";


  public static final String[] COLUMNS_CAUSES = {COLUMN_CAUSES_ID,COLUMN_CAUSES_DATE,COLUMN_CAUSES_SYNCFLAG,COLUMN_CAUSES_STRESS,COLUMN_CAUSES_LACK_OF_SLEEP, COLUMN_CAUSES_LACK_OF_FOOD, COLUMN_CAUSES_LACK_OF_WATER, COLUMN_CAUSES_DEPRESSION, COLUMN_CAUSES_OTHER};

    //String containing query for building CAUSES table
    private static final String CREATE_CAUSES_TABLE = "CREATE TABLE " +
            TABLE_CAUSES + "("
            + COLUMN_CAUSES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_CAUSES_DATE + " INTEGER, "
            + COLUMN_CAUSES_SYNCFLAG + " INTEGER, "
            + COLUMN_CAUSES_STRESS + " INTEGER, "
            + COLUMN_CAUSES_LACK_OF_SLEEP + " INTEGER, "
            + COLUMN_CAUSES_LACK_OF_FOOD + " INTEGER, "
            + COLUMN_CAUSES_LACK_OF_WATER+ " INTEGER, "
            + COLUMN_CAUSES_DEPRESSION + " INTEGER, "
            + COLUMN_CAUSES_OTHER + " TEXT" + ")";

     */

    //Severity Table Details
        //Steve to code

    //String containing query for building Severity table
        //Steve to code


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
    //  db.excelSQL(CREATE_COPING_TABLE);
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
    //  db.execSQL("DROP TABLE IF EXISTS "+ TABLE_COPING);
        onCreate(db);
    }
}
