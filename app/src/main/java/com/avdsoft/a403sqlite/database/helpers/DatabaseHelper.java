package com.avdsoft.a403sqlite.database.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by AVDSOFT on 03.04.2017;
 * project - 403SQLite;
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private String nameDB;

    public DatabaseHelper(Context context, String nameDB, int versionDB) {
        super(context, nameDB, null, versionDB);
        this.nameDB = nameDB;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + nameDB + "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name VARCHAR(40), last_name VARCHAR(90), age VARCHAR(3), description TEXT, " +
                "address TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
