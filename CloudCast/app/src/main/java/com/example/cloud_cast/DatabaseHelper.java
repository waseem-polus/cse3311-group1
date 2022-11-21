package com.example.cloud_cast;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    //Table Name
    public static final String TABLE_NAME = "FAVORITE_CITY";

    //Table columns
    public static final String _ID = "_id";
    public static final String CITYNAME = "cityname";
    public static final String LAT = "lat";
    public static final String LON = "lon";

    // Database Information
    static final String DB_NAME = "FAVORITE_CITY.DB";

    //Database Information
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE_IF_NOT_EXISTS = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CITYNAME + " TEXT NOT NULL, " + LAT + " TEXT NOT NULL, " + LON + " TEXT NOT NULL);";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_IF_NOT_EXISTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    //Functions for database
    public void close() {
        this.close();
    }

    public boolean insert(String cityName, String lat, String lon) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.CITYNAME, cityName);
        contentValue.put(DatabaseHelper.LAT, lat);
        contentValue.put(DatabaseHelper.LON, lon);
        long result = database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
        database.close();
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getData() {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME, null);
        return cursor;
    }

    public void delete(long _id) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }

    public void deleteAll() {
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

}
