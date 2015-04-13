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
    private static final int DATABASE_VERSION = 6;
    private static final String DATABASE_NAME = "migraine.db";

    //Sleep Table Details
    public static final String TABLE_SLEEP = "sleep";
    public static final String COLUMN_SLEEP_ID = "_id";
    public static final String COLUMN_SLEEP_DATE = "wakeDate";
    public static final String COLUMN_SLEEP_HOURS = "hours";
    public static final String COLUMN_TIME_TO_BED = "timeToBed";
    public static final String COLUMN_TIME_UP= "timeUp";
    public static final String COLUMN_SLEEP_RATING= "sleepRating";
    public static final String COLUMN_SLEEP_SYNCFLAG= "syncFlag";
    public static final String[] COLUMNS_SlEEP = {COLUMN_SLEEP_ID,
            COLUMN_SLEEP_DATE, COLUMN_SLEEP_HOURS,
            COLUMN_TIME_TO_BED,COLUMN_TIME_UP,COLUMN_SLEEP_RATING,COLUMN_SLEEP_SYNCFLAG};

    //String containing query for building sleep table
    private static final String CREATE_SLEEPING_TABLE = "CREATE TABLE " +
            TABLE_SLEEP + "("
            + COLUMN_SLEEP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_SLEEP_DATE + " INTEGER, "
            + COLUMN_SLEEP_HOURS + " INTEGER, "
            + COLUMN_TIME_TO_BED + " INTEGER, "
            + COLUMN_TIME_UP + " INTEGER, "
            + COLUMN_SLEEP_RATING + " INTEGER, "
            + COLUMN_SLEEP_SYNCFLAG + " INTEGER" + ")";

    //Exercise Table Details
    public static final String TABLE_EXERCISE = "exercise";
    public static final String COLUMN_EXERCISE_ID = "_id";
    public static final String COLUMN_EXERCISE_DATE = "exDate";
    public static final String COLUMN_HOURS = "hours";
    public static final String COLUMN_INTENSITY= "intensity";
    public static final String COLUMN_EXERCISE_SYNCFLAG= "syncFlag";
    public static final String[] COLUMNS_EXERCISE = {COLUMN_EXERCISE_ID,COLUMN_EXERCISE_DATE,COLUMN_HOURS,COLUMN_INTENSITY,COLUMN_EXERCISE_SYNCFLAG};

    //String containing query for building exercise table
    private static final String CREATE_EXERCISE_TABLE = "CREATE TABLE " +
            TABLE_EXERCISE + "("
            + COLUMN_EXERCISE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_EXERCISE_DATE + " INTEGER, "
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
    public static final String[] COLUMNS_WHEN = {COLUMN_WHEN_ID,COLUMN_WHEN_DATE,
            COLUMN_WHEN_START_TIME,COLUMN_WHEN_END_TIME,COLUMN_WHEN_SYNCFLAG};

    //String containing query for building When table
    private static final String CREATE_WHEN_TABLE = "CREATE TABLE " +
            TABLE_WHEN + "("
            + COLUMN_WHEN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_WHEN_DATE + " INTEGER, "
            + COLUMN_WHEN_START_TIME + " INTEGER, "
            + COLUMN_WHEN_END_TIME + " INTEGER, "
            + COLUMN_WHEN_SYNCFLAG + " INTEGER" + ")";

    //Coping Strategies Table Details
    public static final String TABLE_COPING = "coping";
    public static final String COLUMN_COPING_ID = "_id";
    public static final String COLUMN_COPING_DATE = "date";
    public static final String COLUMN_COPING_START = "start";
    public static final String COLUMN_COPING_SYNCFLAG = "syncFlag";
    public static final String COLUMN_COPING_YOGA = "yoga";
    public static final String COLUMN_COPING_MEDICATION = "medication";
    public static final String COLUMN_COPING_MEDITATION = "meditation";
    public static final String COLUMN_COPING_SLEEPING = "sleeping";
    public static final String COLUMN_COPING_OTHER = "other";

    public static final String[] COLUMNS_COPING = {COLUMN_COPING_ID,COLUMN_COPING_DATE,COLUMN_COPING_START,
            COLUMN_COPING_SYNCFLAG,COLUMN_COPING_YOGA,COLUMN_COPING_MEDICATION,
            COLUMN_COPING_MEDITATION, COLUMN_COPING_SLEEPING, COLUMN_COPING_OTHER};

    //String containing query for building COPING table
    private static final String CREATE_COPING_TABLE = "CREATE TABLE " +
            TABLE_COPING + "("
            + COLUMN_COPING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_COPING_DATE + " INTEGER, "
            + COLUMN_COPING_START + " INTEGER, "
            + COLUMN_COPING_SYNCFLAG + " INTEGER, "
            + COLUMN_COPING_YOGA + " INTEGER, "
            + COLUMN_COPING_MEDICATION + " INTEGER, "
            + COLUMN_COPING_MEDITATION + " INTEGER, "
            + COLUMN_COPING_SLEEPING + " INTEGER, "
            + COLUMN_COPING_OTHER + " TEXT" + ")";


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
    + COLUMN_TRAVEL_DESTINATION + " TEXT, "
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

    //CAUSES Strategies Table Details
    public static final String TABLE_CAUSES = "causes";
    public static final String COLUMN_CAUSES_ID = "_id";
    public static final String COLUMN_CAUSES_DATE = "date";
    public static final String COLUMN_CAUSES_START = "start";
    public static final String COLUMN_CAUSES_SYNCFLAG = "syncFlag";
    public static final String COLUMN_CAUSES_STRESS = "stress";
    public static final String COLUMN_CAUSES_LACK_OF_SLEEP = "lack_of_sleep";
    public static final String COLUMN_CAUSES_LACK_OF_FOOD = "lack_of_food";
    public static final String COLUMN_CAUSES_LACK_OF_WATER = "lack_of_water";
    public static final String COLUMN_CAUSES_DEPRESSION  = "depression";
    public static final String COLUMN_CAUSES_OTHER  = "other";


    public static final String[] COLUMNS_CAUSES = {COLUMN_CAUSES_ID,COLUMN_CAUSES_DATE,COLUMN_CAUSES_START,
            COLUMN_CAUSES_SYNCFLAG,COLUMN_CAUSES_STRESS,COLUMN_CAUSES_LACK_OF_SLEEP,
            COLUMN_CAUSES_LACK_OF_FOOD, COLUMN_CAUSES_LACK_OF_WATER, COLUMN_CAUSES_DEPRESSION, COLUMN_CAUSES_OTHER};

    //String containing query for building CAUSES table
    private static final String CREATE_CAUSES_TABLE = "CREATE TABLE " +
            TABLE_CAUSES + "("
            + COLUMN_CAUSES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_CAUSES_DATE + " INTEGER, "
            + COLUMN_CAUSES_START + " INTEGER, "
            + COLUMN_CAUSES_SYNCFLAG + " INTEGER, "
            + COLUMN_CAUSES_STRESS + " INTEGER, "
            + COLUMN_CAUSES_LACK_OF_SLEEP + " INTEGER, "
            + COLUMN_CAUSES_LACK_OF_FOOD + " INTEGER, "
            + COLUMN_CAUSES_LACK_OF_WATER+ " INTEGER, "
            + COLUMN_CAUSES_DEPRESSION + " INTEGER, "
            + COLUMN_CAUSES_OTHER + " TEXT" + ")";

    //Drink Table Details
    public static final String TABLE_DRINK = "drink";
    public static final String COLUMN_DRINK_ID = "_id";
    public static final String COLUMN_DRINK_DATE = "date";
    public static final String COLUMN_DRINK_SYNCFLAG = "syncFlag";
    public static final String COLUMN_DRINK_BEER = "beer";
    public static final String COLUMN_DRINK_REDWINE = "redWine";
    public static final String COLUMN_DRINK_WHITEWINE = "whiteWine";
    public static final String COLUMN_DRINK_SPIRIT = "spirit";
    public static final String COLUMN_DRINK_SODA = "soda";
    public static final String COLUMN_DRINK_COFFEE = "coffee";
    public static final String COLUMN_DRINK_TEA = "tea";
    public static final String[] COLUMNS_DRINK = {COLUMN_DRINK_ID, COLUMN_DRINK_DATE, COLUMN_DRINK_SYNCFLAG,
            COLUMN_DRINK_BEER, COLUMN_DRINK_REDWINE, COLUMN_DRINK_WHITEWINE, COLUMN_DRINK_SPIRIT,
            COLUMN_DRINK_SODA, COLUMN_DRINK_COFFEE, COLUMN_DRINK_TEA};

    //String containing query for building Drink Table
    private static final String CREATE_DRINK_TABLE = "CREATE TABLE " +
            TABLE_DRINK + "("
            + COLUMN_DRINK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_DRINK_DATE + " INTEGER, "
            + COLUMN_DRINK_SYNCFLAG + " INTEGER, "
            + COLUMN_DRINK_BEER + " INTEGER, "
            + COLUMN_DRINK_REDWINE+ " INTEGER, "
            + COLUMN_DRINK_WHITEWINE + " INTEGER, "
            + COLUMN_DRINK_SPIRIT + " INTEGER, "
            + COLUMN_DRINK_SODA + " INTEGER, "
            + COLUMN_DRINK_COFFEE + " INTEGER, "
            + COLUMN_DRINK_TEA + " INTEGER" + ")";

    //Food Table Details
    public static final String TABLE_FOOD = "food";
    public static final String COLUMN_FOOD_ID = "_id";
    public static final String COLUMN_FOOD_DATE = "date";
    public static final String COLUMN_FOOD_SYNCFLAG = "syncFlag";
    public static final String COLUMN_FOOD_CHOCOLATE = "chocolate";
    public static final String COLUMN_FOOD_CHEESE = "cheese";
    public static final String COLUMN_FOOD_NUTS = "nuts";
    public static final String COLUMN_FOOD_CITRUSFRUITS = "citrusFruits";
    public static final String[] COLUMNS_FOOD = {COLUMN_FOOD_ID, COLUMN_FOOD_DATE,
            COLUMN_FOOD_SYNCFLAG, COLUMN_FOOD_CHOCOLATE, COLUMN_FOOD_CHEESE, COLUMN_FOOD_NUTS, COLUMN_FOOD_CITRUSFRUITS};

    //String containing query for building Food Table
    private static final String CREATE_FOOD_TABLE = "CREATE TABLE " +
            TABLE_FOOD + "("
            + COLUMN_FOOD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_FOOD_DATE + " INTEGER, "
            + COLUMN_FOOD_SYNCFLAG + " INTEGER, "
            + COLUMN_FOOD_CHOCOLATE + " INTEGER, "
            + COLUMN_FOOD_CHEESE + " INTEGER, "
            + COLUMN_FOOD_NUTS+ " INTEGER, "
            + COLUMN_FOOD_CITRUSFRUITS + " INTEGER" + ")";

    //Menstrual Cycle Table Details
    public static final String TABLE_MENSTRUAL_CYCLE = "menstrualCycle";
    public static final String COLUMN_MENSTRUAL_CYCLE_ID = "_id";
    public static final String COLUMN_MENSTRUAL_CYCLE_DATE = "date";
    public static final String COLUMN_MENSTRUAL_CYCLE_SYNCFLAG = "syncFlag";
    public static final String COLUMN_MENSTRUAL_CYCLE_YES = "yes";
    public static final String COLUMN_MENSTRUAL_CYCLE_NO = "no";
    public static final String COLUMN_MENSTRUAL_CYCLE_COMINGSOON = "comingSoon";
    public static final String[] COLUMNS_MENSTRUAL_CYCLE = {COLUMN_MENSTRUAL_CYCLE_ID,
            COLUMN_MENSTRUAL_CYCLE_DATE,
            COLUMN_MENSTRUAL_CYCLE_SYNCFLAG, COLUMN_MENSTRUAL_CYCLE_YES, COLUMN_MENSTRUAL_CYCLE_NO,
            COLUMN_MENSTRUAL_CYCLE_COMINGSOON};

    //String containing query for building Menstrual Cycle Table
    private static final String CREATE_MENSTRUAL_CYCLE_TABLE = "CREATE TABLE " +
            TABLE_MENSTRUAL_CYCLE + "("
            + COLUMN_MENSTRUAL_CYCLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_MENSTRUAL_CYCLE_DATE + " INTEGER, "
            + COLUMN_MENSTRUAL_CYCLE_SYNCFLAG + " INTEGER, "
            + COLUMN_MENSTRUAL_CYCLE_YES + " INTEGER, "
            + COLUMN_MENSTRUAL_CYCLE_NO + " INTEGER, "
            + COLUMN_MENSTRUAL_CYCLE_COMINGSOON+ " INTEGER" + ")";

    //User Info table details
    public static final String TABLE_USER_INFO = "userInfo";
    public static final String COLUMN_USER_ID = "_id";
    public static final String COLUMN_USER_SYNCFLAG = "syncFlag";
    public static final String COLUMN_USER_FIRST_NAME = "first_name";
    public static final String COLUMN_USER_SURNAME = "surname";
    public static final String COLUMN_USER_EMAIL = "email";
    public static final String COLUMN_USER_GP_NAME = "gpname";
    public static final String COLUMN_USER_GP_EMAIL = "gp_email";
    public final static String[] COLUMNS_USER_INFO = {COLUMN_USER_ID, COLUMN_USER_SYNCFLAG,
            COLUMN_USER_FIRST_NAME, COLUMN_USER_SURNAME, COLUMN_USER_EMAIL,
            COLUMN_USER_GP_NAME, COLUMN_USER_GP_EMAIL};

    //String containing query for building User Info table
    private static final String CREATE_USER_INFO_TABLE = "CREATE TABLE " +
            TABLE_USER_INFO + "("
            + COLUMN_USER_ID + " INTEGER, "
            + COLUMN_USER_SYNCFLAG + " INTEGER, "
            + COLUMN_USER_FIRST_NAME + " TEXT, "
            + COLUMN_USER_SURNAME + " TEXT, "
            + COLUMN_USER_EMAIL + " TEXT, "
            + COLUMN_USER_GP_NAME + " TEXT, "
            + COLUMN_USER_GP_EMAIL + " TEXT)";

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
        db.execSQL(CREATE_TRAVEL_TABLE);
        db.execSQL(CREATE_MOOD_TABLE);
        db.execSQL(CREATE_WHEN_TABLE);
        db.execSQL(CREATE_DRINK_TABLE);
        db.execSQL(CREATE_FOOD_TABLE);
        db.execSQL(CREATE_MENSTRUAL_CYCLE_TABLE);
        db.execSQL(CREATE_COPING_TABLE);
        db.execSQL(CREATE_CAUSES_TABLE);
        db.execSQL(CREATE_USER_INFO_TABLE);
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
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_TRAVEL);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_MOOD);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_WHEN);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_DRINK);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_FOOD);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_MENSTRUAL_CYCLE);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_COPING);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_CAUSES);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_USER_INFO);
        onCreate(db);
    }
}
