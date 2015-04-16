package uk.ac.bradford.findmymigraine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Sumaia on 15/03/2015.
 */
public class FoodDAO {
    public static final String TAG = "FoodDAO";

    // Database fields
    private SQLiteDatabase db;
    private MySQLiteHelper dbHelper;
    private Context mContext;


    public FoodDAO(Context context) {
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


    //Add new Food Record
    public void createFoodRecord(Food food) {
        Log.d("addFoodRecord", food.toString());
        //db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_FOOD_DATE, food.getDate());
        values.put(MySQLiteHelper.COLUMN_FOOD_SYNCFLAG, food.getSyncFlag());
        values.put(MySQLiteHelper.COLUMN_FOOD_CHOCOLATE, food.getChocolate());
        values.put(MySQLiteHelper.COLUMN_FOOD_CHEESE, food.getCheese());
        values.put(MySQLiteHelper.COLUMN_FOOD_NUTS, food.getNuts());
        values.put(MySQLiteHelper.COLUMN_FOOD_CITRUSFRUITS, food.getCitrusFruits());
        //Inserting row
        db.insertWithOnConflict(MySQLiteHelper.TABLE_FOOD, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        db.close();

    }


    //Get Single Food Record, for a given date
    public Food[] getFoodRecordsForDate(Long dateRequired){

        Long minDate = dateRequired-1000;
        Long maxDate = dateRequired+1000;
        db = dbHelper.getReadableDatabase();
        Food[] food;// = new Food();
        Cursor cursor;
        try {
            cursor = db.query(MySQLiteHelper.TABLE_FOOD,
                    MySQLiteHelper.COLUMNS_FOOD,
                    MySQLiteHelper.COLUMN_FOOD_DATE + ">" + minDate + " AND " + MySQLiteHelper.COLUMN_FOOD_DATE + "<" + maxDate,
                    null, null, null, null);

            cursor.moveToFirst();
            //How many records?
            int noOfRows = cursor.getCount();
            food = new Food[noOfRows];
            for (int i=0; i<noOfRows; i++){
            if(!cursor.isAfterLast()) {
                food[i] = cursorToFood(cursor);
                cursor.moveToNext();
            }else
                Log.d("getFoodRecordForDate","No Food record found with this date" );}

            cursor.close();
            db.close();
            return food;
        }
        catch (SQLException e){
            Log.e("Get row error", e.toString());
            e.printStackTrace();
        }
        //db.close();
        food = new Food[0];
        return food;
    }

    public Food[] getAllFoodRecords(){

        db = dbHelper.getReadableDatabase();
        Food[] food;// = new Food();
        Cursor cursor;
        try {
            cursor = db.query(MySQLiteHelper.TABLE_FOOD,
                    MySQLiteHelper.COLUMNS_FOOD,
                    null,
                    null, null, null, null);

            cursor.moveToFirst();
            //How many records?
            int noOfRows = cursor.getCount();
            food = new Food[noOfRows];
            for (int i=0; i<noOfRows; i++){
                if(!cursor.isAfterLast()) {
                    food[i] = cursorToFood(cursor);
                    cursor.moveToNext();
                }else
                    Log.d("getFoodRecordForDate","No Food record found with this date" );}

            cursor.close();
            db.close();
            return food;
        }
        catch (SQLException e){
            Log.e("Get row error", e.toString());
            e.printStackTrace();
        }
        //db.close();
        food = new Food[0];
        return food;
    }


    //Method to return Food object from cursor in database
    protected Food cursorToFood(Cursor cursor) {
        Food food  = new Food();
        food.setId(cursor.getLong(0));
        food.setDate(Long.parseLong(cursor.getString(1)));
        food.setChocolate(Integer.parseInt(cursor.getString(3)));       //numbers altered 6/4/15 by Steve (SyncFlag is 2)
        food.setCheese(Integer.parseInt(cursor.getString(4)));
        food.setNuts(Integer.parseInt(cursor.getString(5)));
        food.setCitrusFruits(Integer.parseInt(cursor.getString(6)));

        //log
        Log.d("getFoodRecord("+food.getId()+")", food.toString());
        return food;
    }

}
