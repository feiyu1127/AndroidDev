package com.example.richard.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Richard on 2018/1/5.
 */

public class InitDB extends SQLiteOpenHelper {
    private  Context context;
    private final static String DBNAME = "DBStorage.db";
    private static InitDB initDB = null;
    private static final String CREATE_USER = "create table user(" +
            "id integer primary key autoincrement," +
            "name text," +
            "sex text," +
            "age text)";
    private InitDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    public static InitDB getInitDb(Context context, SQLiteDatabase.CursorFactory factory, int version){
        if(initDB == null){
            initDB = new InitDB(context, DBNAME, factory, version);
        }
        return initDB;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
