package com.example.mytasks;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TaskHelper extends SQLiteOpenHelper {

    public TaskHelper(Context context){
        super(context, "dbtasks", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql1 = "CREATE TABLE tasks (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, date DATE , complete BOOLEAN)";
        db.execSQL(sql1);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
