package com.lucak.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lucak.classes.Coin;
import com.lucak.classes.Token;
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
    public static final String[] USER_COLUMNS = {USER_COL_ID, USER_COL_LOGIN, USER_COL_PASS, USER_COL_EMAIL};

    //Coin Table Info
    public static final String COIN_TABLE_NAME = "Coins";
    public static final String COIN_COL_ID = "coin_ID";
    public static final String COIN_COL_USER_ID = "user_ID";
    public static final String COIN_COL_AMOUNT = "coin_Amount";
    public static final String COIN_COL_PRICE = "price_Per_Coin";
    public static final String COIN_COL_TICKER = "coin_Ticker";
    public static final String COIN_COL_NAME = "coin_Name";
    public static final String[] COIN_COLUMNS = {COIN_COL_ID, COIN_COL_USER_ID ,COIN_COL_AMOUNT, COIN_COL_PRICE, COIN_COL_TICKER, COIN_COL_NAME};

    //Coin Table Info
    public static final String TOKEN_TABLE_NAME = "Tokens";
    public static final String TOKEN_COL_ID = "token_ID";
    public static final String TOKEN_COL_USER_ID = "user_ID";
    public static final String TOKEN_COL_COIN = "coin_ID";
    public static final String TOKEN_COL_AMOUNT = "token_Amount";
    public static final String TOKEN_COL_PRICE = "price_Per_token";
    public static final String TOKEN_COL_TICKER = "token_Ticker";
    public static final String TOKEN_COL_NAME = "token_Name";
    public static final String[] TOKEN_COLUMNS = {TOKEN_COL_ID, TOKEN_COL_USER_ID , TOKEN_COL_COIN, TOKEN_COL_AMOUNT, TOKEN_COL_AMOUNT, TOKEN_COL_PRICE, TOKEN_COL_TICKER, TOKEN_COL_NAME};

    public Database(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTable = "CREATE TABLE " + USER_TABLE_NAME + " ( " +
                USER_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USER_COL_LOGIN + " VARCHAR, " +
                USER_COL_PASS + " VARCHAR, " +
                USER_COL_EMAIL + " VARCHAR " +
                ")";

        String createCoinTable = "CREATE TABLE " + COIN_TABLE_NAME + " ( " +
                COIN_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COIN_COL_USER_ID + " INTEGER, " +
                COIN_COL_AMOUNT + " REAL, " +
                COIN_COL_PRICE + " REAL, " +
                COIN_COL_TICKER + " VARCHAR, " +
                COIN_COL_NAME + " VARCHAR " +
                ")";

        String tokenCoinTable = "CREATE TABLE " + TOKEN_TABLE_NAME + " ( " +
                TOKEN_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TOKEN_COL_USER_ID + " INTEGER, " +
                TOKEN_COL_COIN + " INTEGER, " +
                TOKEN_COL_AMOUNT + " REAL, " +
                TOKEN_COL_PRICE + " REAL, " +
                TOKEN_COL_TICKER + " VARCHAR, " +
                TOKEN_COL_NAME + " VARCHAR " +
                ")";

        db.execSQL(createUserTable);
        db.execSQL(createCoinTable);
        db.execSQL(tokenCoinTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String dropUserTable = "DROP TABLE IF EXISTS " + USER_TABLE_NAME;
        String dropCoinTable = "DROP TABLE IF EXISTS " + COIN_TABLE_NAME;
        String dropTokenTable = "DROP TABLE IF EXISTS " + TOKEN_TABLE_NAME;
        db.execSQL(dropUserTable);
        db.execSQL(dropCoinTable);
        db.execSQL(dropTokenTable);
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
                cursor.getString(cursor.getColumnIndex(USER_COL_EMAIL))
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
            cv.put(COIN_COL_PRICE, coin.getBought_Price());
            cv.put(COIN_COL_TICKER, coin.getCoin_Ticker());

            database.insert(COIN_TABLE_NAME, null, cv);
            database.close();

            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public boolean AddToken(Token token){
        try {
            SQLiteDatabase database = this.getWritableDatabase();

            ContentValues cv = new ContentValues();
            cv.put(TOKEN_COL_USER_ID, token.getUser_Id());
            cv.put(TOKEN_COL_NAME, token.getToken_Name());
            cv.put(TOKEN_COL_AMOUNT, token.getToken_Amount());
            cv.put(TOKEN_COL_PRICE, token.getBought_Price());
            cv.put(TOKEN_COL_TICKER, token.getToken_Ticker());
            cv.put(TOKEN_COL_COIN, token.getCoin_Id());

            database.insert(TOKEN_TABLE_NAME, null, cv);
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
                    cursor.getDouble(cursor.getColumnIndex(COIN_COL_AMOUNT)),
                    cursor.getDouble(cursor.getColumnIndex(COIN_COL_PRICE)),
                    cursor.getString(cursor.getColumnIndex(COIN_COL_TICKER)),
                    cursor.getString(cursor.getColumnIndex(COIN_COL_NAME))
            );

            coinList.add(coin);
        }

        return coinList;

    }
    public ArrayList<Token> GetAllTokens(){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(TOKEN_TABLE_NAME,
                TOKEN_COLUMNS,
                "",
                null,
                null,
                null,
                null);

        ArrayList<Token> tokenList = new ArrayList<Token>();

        while(cursor.moveToNext()){
            Token token = new Token(
                    cursor.getInt(cursor.getColumnIndex(TOKEN_COL_ID)),
                    cursor.getInt(cursor.getColumnIndex(TOKEN_COL_USER_ID)),
                    cursor.getInt(cursor.getColumnIndex(TOKEN_COL_COIN)),
                    cursor.getDouble(cursor.getColumnIndex(TOKEN_COL_AMOUNT)),
                    cursor.getDouble(cursor.getColumnIndex(TOKEN_COL_PRICE)),
                    cursor.getString(cursor.getColumnIndex(TOKEN_COL_TICKER)),
                    cursor.getString(cursor.getColumnIndex(TOKEN_COL_NAME))
            );

            tokenList.add(token);
        }

        return tokenList;

    }

}
