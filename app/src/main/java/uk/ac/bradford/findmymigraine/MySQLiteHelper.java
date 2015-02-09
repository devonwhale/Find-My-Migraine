package uk.ac.bradford.findmymigraine;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Sumaia on 28/01/2015.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "migraine.db";

    //Sleep Table Details
    public static final String TABLE_SLEEP = "sleeping";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TIME_TO_BED = "time to bed";
    public static final String COLUMN_TIME_UP= "time up";
    public static final String COLUMN_SLEEP_RATING= "sleep rating";
    public static final String[] COLUMNS_SlEEP = {COLUMN_ID,COLUMN_TIME_TO_BED,COLUMN_TIME_UP,COLUMN_SLEEP_RATING};

    private static final String CREATE_SLEEPING_TABLE = "CREATE TABLE " +
            TABLE_SLEEP + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_TIME_TO_BED + " INTEGER,"
            + COLUMN_TIME_UP + " INTEGER,"
            + COLUMN_SLEEP_RATING + " INTEGER" + ")";

    public MySQLiteHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SLEEPING_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(), "Upgrading from version" + oldVersion + " to "+ newVersion+", which will destroy old data");
        db.execSQL("DROP TABLE IF EXISTS"+ TABLE_SLEEP);
        onCreate(db);
    }
}
