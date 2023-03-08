package com.example.footballplayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper
{
    private Context ctx;
    private static final String DATABASE_NAME = "db_football";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "tbl_player";
    private static final String FIELD_ID = "id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_NUMBER = "number";
    private static final String FIELD_CLUB = "club";
    public MyDatabaseHelper(@Nullable Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.ctx = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String query = "CREATE TABLE " + TABLE_NAME + "( " +
                FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FIELD_NAME + " VARCHAR(50), " +
                FIELD_NUMBER + " VARCHAR(2), " +
                FIELD_CLUB + " VARCHAR(50)" +
                ");";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV)
    {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        onCreate(db);
    }
    public long AddPlayer(String name, String number, String club)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(FIELD_NAME, name);
        cv.put(FIELD_NUMBER, number);
        cv.put(FIELD_CLUB, club);
        long exec = db.insert(TABLE_NAME, null, cv);
        return exec;
    }
}