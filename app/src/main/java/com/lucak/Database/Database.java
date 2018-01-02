package com.lucak.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lucak.classes.Coin;
import com.lucak.classes.User;

import java.util.ArrayList;

/**
 * Created by lkaastra6885 on 11/30/2017.
 */

public class Database extends SQLiteOpenHelper {
    //DB INFO
    public static final String DB_NAME = "TrackerDB";
    public static final int VERSION = 1;

    //User Table Info
    public static final String USER_TABLE_NAME = "Users";
    public static final String USER_COL_ID = "user_ID";
    public static final String USER_COL_LOGIN = "user_Login";
    public static final String USER_COL_PASS = "user_Pass";
    public static final String USER_COL_EMAIL = "user_Email";
    public static final String USER_COL_FIRSTNAME = "first_Name";
    public static final String USER_COL_LASTNAME = "last_Name";
    public static final String[] USER_COLUMNS = {USER_COL_ID, USER_COL_LOGIN, USER_COL_PASS,
            USER_COL_EMAIL, USER_COL_FIRSTNAME, USER_COL_LASTNAME};


    //Coin Table Info
    public static final String COIN_TABLE_NAME = "Coins";
    public static final String COIN_COL_ID = "coin_ID";
    public static final String COIN_COL_USER_ID = "user_ID";
    public static final String COIN_COL_NAME = "coin_Name";
    public static final String COIN_COL_AMOUNT = "coinAmount";
    public static final String COIN_COL_BOUGHT = "boughtPrice";
    public static final String COIN_COL_CURRENT = "currentPrice";
    public static final String COIN_COL_SYMBOL = "symbol";
    public static final String COIN_COL_ONEDAYCHANGE = "oneDayChange";
    public static final String COIN_COL_SEVENDAYCHANGE = "sevenDayChange";
    public static final String[] COIN_COLUMNS = {COIN_COL_ID, COIN_COL_USER_ID, COIN_COL_NAME,
            COIN_COL_AMOUNT, COIN_COL_BOUGHT, COIN_COL_CURRENT, COIN_COL_SYMBOL, COIN_COL_ONEDAYCHANGE, COIN_COL_SEVENDAYCHANGE};

    public static final String ALLCOINS_TABLE_NAME = "AllCoins";
    public static final String ALLCOINS_COL_ID = "all_Coin_ID";
    public static final String ALLCOINS_COL_SYMBOL = "symbol";
    public static final String ALLCOINS_COL_NAME = "name";

    public Database(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTable = "CREATE TABLE " + USER_TABLE_NAME + " ( " +
                USER_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USER_COL_LOGIN + " VARCHAR, " +
                USER_COL_PASS + " VARCHAR, " +
                USER_COL_EMAIL + " VARCHAR, " +
                USER_COL_FIRSTNAME + " VARCHAR, " +
                USER_COL_LASTNAME + " VARCHAR " +
                ")";

        String createCoinTable = "CREATE TABLE " + COIN_TABLE_NAME + " ( " +
                COIN_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COIN_COL_USER_ID + " INTEGER, " +
                COIN_COL_NAME + " VARCHAR, " +
                COIN_COL_AMOUNT + " REAL, " +
                COIN_COL_BOUGHT + " REAL, " +
                COIN_COL_CURRENT + " REAL, " +
                COIN_COL_SYMBOL + " VARCHAR, " +
                COIN_COL_ONEDAYCHANGE + " VARCHAR, " +
                COIN_COL_SEVENDAYCHANGE + " VARCHAR " +
                ")";

        String createAllCoinsTable = "CREATE TABLE " + ALLCOINS_TABLE_NAME + " ( " +
                ALLCOINS_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ALLCOINS_COL_SYMBOL + " VARCHAR, " +
                ALLCOINS_COL_NAME + " VARCHAR " +
                ")";


        db.execSQL(createUserTable);
        db.execSQL(createCoinTable);
        db.execSQL(createAllCoinsTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String dropUserTable = "DROP TABLE IF EXISTS " + USER_TABLE_NAME;
        String dropCoinTable = "DROP TABLE IF EXISTS " + COIN_TABLE_NAME;
        String dropAllCoinTable = "DROP TABLE IF EXISTS " + ALLCOINS_TABLE_NAME;
        db.execSQL(dropUserTable);
        db.execSQL(dropCoinTable);
        db.execSQL(dropAllCoinTable);
        onCreate(db);
    }

    public boolean Login(User user){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(USER_TABLE_NAME,
                USER_COLUMNS,
                USER_COL_LOGIN + " = ? AND " + USER_COL_PASS +" = ?",
                new String[]{user.getUser_Name(), user.getPassword()},
                null,
                null,
                null);

        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            db.myDB.loggedin = new User(
                cursor.getInt(cursor.getColumnIndex(USER_COL_ID)),
                cursor.getString(cursor.getColumnIndex(USER_COL_LOGIN)),
                cursor.getString(cursor.getColumnIndex(USER_COL_PASS)),
                cursor.getString(cursor.getColumnIndex(USER_COL_EMAIL)),
                cursor.getString(cursor.getColumnIndex(USER_COL_FIRSTNAME)),
                cursor.getString(cursor.getColumnIndex(USER_COL_LASTNAME))
            );
            return true;
        }
        else{
            return false;
        }
    }

    public boolean AddUser(User user){
        try {
            SQLiteDatabase database = this.getWritableDatabase();

            ContentValues cv = new ContentValues();
            cv.put(USER_COL_LOGIN, user.getUser_Name());
            cv.put(USER_COL_PASS, user.getPassword());
            cv.put(USER_COL_EMAIL, user.getEmail());
            cv.put(USER_COL_FIRSTNAME, "");
            cv.put(USER_COL_LASTNAME, "");

            database.insert(USER_TABLE_NAME, null, cv);
            database.close();

            return true;
        }
        catch(Exception e){
            return false;
        }

    }

    public boolean AddCoin(Coin coin){
        try {
            SQLiteDatabase database = this.getWritableDatabase();

            ContentValues cv = new ContentValues();
            cv.put(COIN_COL_USER_ID, coin.getUser_id());
            cv.put(COIN_COL_NAME, coin.getCoin_Name());
            cv.put(COIN_COL_AMOUNT, coin.getCoin_Amount());
            cv.put(COIN_COL_BOUGHT, coin.getPriceBought());
            cv.put(COIN_COL_CURRENT, 0);
            cv.put(COIN_COL_SYMBOL, coin.getSymbol());
            cv.put(COIN_COL_ONEDAYCHANGE, "");
            cv.put(COIN_COL_SEVENDAYCHANGE, "");

            database.insert(COIN_TABLE_NAME, null, cv);
            database.close();

            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public ArrayList<Coin> GetAllCoins(){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(COIN_TABLE_NAME,
                COIN_COLUMNS,
                "",
                null,
                null,
                null,
                null);

        ArrayList<Coin> coinList = new ArrayList<Coin>();

        while(cursor.moveToNext()){
            Coin coin = new Coin(
                    cursor.getInt(cursor.getColumnIndex(COIN_COL_ID)),
                    cursor.getInt(cursor.getColumnIndex(COIN_COL_USER_ID)),
                    cursor.getString(cursor.getColumnIndex(COIN_COL_NAME)),
                    cursor.getDouble(cursor.getColumnIndex(COIN_COL_AMOUNT)),
                    cursor.getDouble(cursor.getColumnIndex(COIN_COL_BOUGHT)),
                    cursor.getDouble(cursor.getColumnIndex(COIN_COL_CURRENT)),
                    cursor.getString(cursor.getColumnIndex(COIN_COL_SYMBOL)),
                    cursor.getString(cursor.getColumnIndex(COIN_COL_ONEDAYCHANGE)),
                    cursor.getString(cursor.getColumnIndex(COIN_COL_SEVENDAYCHANGE))
            );

            coinList.add(coin);
        }

        return coinList;

    }

    public boolean RefreshAllCoins(ArrayList<Coin> list){

        try {
            SQLiteDatabase database = this.getWritableDatabase();
            database.delete(ALLCOINS_TABLE_NAME, "", null);

            for (Coin coin: list) {
                ContentValues cv = new ContentValues();
                cv.put(ALLCOINS_COL_NAME, coin.getCoin_Name());
                cv.put(ALLCOINS_COL_SYMBOL, coin.getSymbol());
                database.insert(ALLCOINS_TABLE_NAME, null, cv);
            }

            database.close();

            return true;
        }
        catch(Exception e){
            return false;
        }
    }

}
