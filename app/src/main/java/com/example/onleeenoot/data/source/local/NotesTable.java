package com.example.onleeenoot.data.source.local;

import android.database.sqlite.SQLiteDatabase;

public class NotesTable {

    public static final String TABLE_NAME = "notes";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TEXT = "content";

    private static final String DATABASE_CREATE = "create table "
            + TABLE_NAME
            + "("
            + COLUMN_ID + " integer primary key autoincrement"
            + COLUMN_TEXT + " text"
            + ")";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database) {
        // Put upgrade statements here
    }
}
