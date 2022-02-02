package com.example.myapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbController extends SQLiteOpenHelper {

    public DbController(Context context) {
        super(context, "com.example.myapplication.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE TASKS (USER_ID INTEGER NOT NULL, ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insert (int userId , String task){

        ContentValues signIn = new ContentValues();
        signIn.put("USER_ID", userId);
        signIn.put("NAME", task);


        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO TASKS VALUES(' + userId + ', null, ' + task + ')");

        db.close();


    }
}
