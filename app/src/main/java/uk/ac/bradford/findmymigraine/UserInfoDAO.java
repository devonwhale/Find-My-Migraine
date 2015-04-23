package uk.ac.bradford.findmymigraine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Steve on 13/04/2015.
 * Methods to access the USER table in the database to get record and update record
 * n.b. Only 1 user record is allowed. Therefore an empty record is created id=1 and this
 *      is the record which is retrieved or updated.
 */
public class UserInfoDAO {
    private SQLiteDatabase db;
    private MySQLiteHelper dbHelper;
    private Context mContext;


    public UserInfoDAO(Context context) {
        this.mContext = context;
        dbHelper = new MySQLiteHelper(context);
        // open the database
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLException e) {
            Log.e("UserInfo", "SQLException on opening database " + e.getMessage());
            e.printStackTrace();
        }
    }

    //Method to Create empty user record
    private void createEmptyUserRecord(){
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_USER_ID, 1);
        values.put(MySQLiteHelper.COLUMN_USER_SYNCFLAG, 0);
        values.put(MySQLiteHelper.COLUMN_USER_FIRST_NAME, "");
        values.put(MySQLiteHelper.COLUMN_USER_SURNAME, "");
        values.put(MySQLiteHelper.COLUMN_USER_EMAIL, "");
        values.put(MySQLiteHelper.COLUMN_USER_GP_NAME, "");
        values.put(MySQLiteHelper.COLUMN_USER_GP_EMAIL, "");

        db = dbHelper.getWritableDatabase();
        db.insert(MySQLiteHelper.TABLE_USER_INFO, null, values);
        db.close();
    }

    //method to get existing record
    public String[] getUserInfo(){
        String[] userInfo = new String[7];
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(MySQLiteHelper.TABLE_USER_INFO,
                MySQLiteHelper.COLUMNS_USER_INFO,
                MySQLiteHelper.COLUMN_USER_ID + "=1",
                null, null, null, null);

        cursor.moveToFirst();
        if(cursor.isAfterLast() || cursor == null){
            createEmptyUserRecord();
        }
        else{
            do {
                userInfo[0] = Integer.toString(cursor.getInt(0));
                userInfo[1] = Integer.toString(cursor.getInt(1));
                userInfo[2] = cursor.getString(2);
                userInfo[3] = cursor.getString(3);
                userInfo[4] = cursor.getString(4);
                userInfo[5] = cursor.getString(5);
                userInfo[6] = cursor.getString(6);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return userInfo;
    }

    //method to update existing record
    public void updateUserInfo(String[] updateDetails){
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_USER_SYNCFLAG, updateDetails[1]);
        values.put(MySQLiteHelper.COLUMN_USER_FIRST_NAME, updateDetails[2]);
        values.put(MySQLiteHelper.COLUMN_USER_SURNAME, updateDetails[3]);
        values.put(MySQLiteHelper.COLUMN_USER_EMAIL, updateDetails[4]);
        values.put(MySQLiteHelper.COLUMN_USER_GP_NAME, updateDetails[5]);
        values.put(MySQLiteHelper.COLUMN_USER_GP_EMAIL, updateDetails[6]);
        db.update(MySQLiteHelper.TABLE_USER_INFO, values, MySQLiteHelper.COLUMN_USER_ID + "= 1", null);
        db.close();
    }
}
