package com.lucak.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    //Coin Table Info
    public static final String COIN_TABLE_NAME = "Coins";
    public static final String COIN_COL_ID = "coin_ID";




    public Database(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTable = "CREATE TABLE" + USER_TABLE_NAME + " ( " +
                USER_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USER_COL_LOGIN + " VARCHAR, " +
                USER_COL_PASS + " VARCHAR, " +
                USER_COL_EMAIL + " VARCHAR " +
                ")";

        String createCoinTable = "CREATE TABLE " + COIN_TABLE_NAME + " ( " +
                COIN_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +


        db.execSQL(createUserTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
