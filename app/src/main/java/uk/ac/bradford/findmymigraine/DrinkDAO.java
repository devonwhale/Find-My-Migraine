package uk.ac.bradford.findmymigraine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Sumaia on 12/03/2015.
 */

//The Drink Table's Data Access Object
public class DrinkDAO {
    public static final String TAG = "DrinkDAO";

    // Database fields
    private SQLiteDatabase db;
    private MySQLiteHelper dbHelper;
    private Context mContext;


    public DrinkDAO(Context context) {
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


    //Add new Drink Record
    public void createDrinkRecord(Drink drink) {
        Log.d("addDrinkRecord", drink.toString());
        //db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_DRINK_DATE, drink.getDate());
        values.put(MySQLiteHelper.COLUMN_DRINK_SYNCFLAG, drink.getSyncFlag());
        values.put(MySQLiteHelper.COLUMN_DRINK_BEER, drink.getBeer());
        values.put(MySQLiteHelper.COLUMN_DRINK_WHITEWINE, drink.getWhiteWine());
        values.put(MySQLiteHelper.COLUMN_DRINK_REDWINE, drink.getRedWine());
        values.put(MySQLiteHelper.COLUMN_DRINK_SPIRIT, drink.getSpirit());
        values.put(MySQLiteHelper.COLUMN_DRINK_SODA, drink.getSoda());
        values.put(MySQLiteHelper.COLUMN_DRINK_COFFEE, drink.getCoffee());
        values.put(MySQLiteHelper.COLUMN_DRINK_TEA, drink.getTea());
            //Inserting row
        db.insertWithOnConflict(MySQLiteHelper.TABLE_DRINK, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        db.close();

    }


    //Get Single Drink Record, for a given date

    public Drink[] getDrinkRecordsForDate(Long dateRequired){

        Long minDate = dateRequired-1000;
        Long maxDate = dateRequired+1000;
        db = dbHelper.getReadableDatabase();
        Drink[] drink;// = new Drink();
        Cursor cursor;
        try {
            cursor = db.query(MySQLiteHelper.TABLE_DRINK,
                    MySQLiteHelper.COLUMNS_DRINK,
                    MySQLiteHelper.COLUMN_DRINK_DATE + ">" + minDate + " AND " + MySQLiteHelper.COLUMN_DRINK_DATE + "<" + maxDate,
                    null, null, null, null);

            cursor.moveToFirst();
            //How many records?
            int noOfRows = cursor.getCount();
            drink = new Drink[noOfRows];

            if(!cursor.isAfterLast()) {
                for(int i=0; i<drink.length; i++){
                drink[i] = cursorToDrink(cursor);
                cursor.moveToNext();
            }

                cursor.close();
                db.close();
                return drink;
            }

            else{
                Log.d("getDrinkRecordForDate","No Drink record found with this date" );}

        }
        catch (SQLException e){
            Log.e("Get row error", e.toString());
            e.printStackTrace();
        }
        //db.close();
        drink = new Drink[0];
        return drink;
    }

    public Drink[] getAllDrinkRecords(){

        db = dbHelper.getReadableDatabase();
        Drink[] drink;// = new Drink();
        Cursor cursor;
        try {
            cursor = db.query(MySQLiteHelper.TABLE_DRINK,
                    MySQLiteHelper.COLUMNS_DRINK,
                    null,
                    null, null, null, null);

            cursor.moveToFirst();
            //How many records?
            int noOfRows = cursor.getCount();
            drink = new Drink[noOfRows];

            if(!cursor.isAfterLast()) {
                for(int i=0; i<drink.length; i++){
                    drink[i] = cursorToDrink(cursor);
                    cursor.moveToNext();
                }

                cursor.close();
                db.close();
                return drink;
            }

            else{
                Log.d("getDrinkRecordForDate","No Drink record found with this date" );}

        }
        catch (SQLException e){
            Log.e("Get row error", e.toString());
            e.printStackTrace();
        }
        //db.close();
        drink = new Drink[0];
        return drink;
    }


    //Method to return Drink object from cursor in database
    protected Drink cursorToDrink(Cursor cursor) {
        Drink drink  = new Drink();
        drink.setId(cursor.getLong(0));
        drink.setDate(Long.parseLong(cursor.getString(1)));
        drink.setBeer(Integer.parseInt(cursor.getString(3)));       //changed to match COLUMNS_DRINK by Steve 6/4/15
        drink.setWhiteWine(Integer.parseInt(cursor.getString(5)));
        drink.setRedWine(Integer.parseInt(cursor.getString(4)));
        drink.setSpirit(Integer.parseInt(cursor.getString(6)));
        drink.setSoda(Integer.parseInt(cursor.getString(7)));
        drink.setCoffee(Integer.parseInt(cursor.getString(8)));
        drink.setTea(Integer.parseInt(cursor.getString(9)));

        //log
        Log.d("getDrinkRecord("+drink.getId()+")", drink.toString());
        return drink;
    }

}
