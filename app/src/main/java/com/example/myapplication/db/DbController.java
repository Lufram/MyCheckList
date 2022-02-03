package com.example.myapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbController extends SQLiteOpenHelper {

    public DbController(Context context) {
        super(context, "com.example.myapplication.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_kets=ON");
        db.execSQL("CREATE TABLE USERS (USER_ID PRIMARY KEY AUTOINCREMENT,  USER_NAME TEXT NOT NULL, PASSWORD INTEGER  NOT NULL);");
        db.execSQL("CREATE TABLE TASKS (TASK_ID INTEGER PRIMARY KEY AUTOINCREMENT, TASK_NAME TEXT NOT NULL, USER_ID INTEGER REFERENCES USERS(USER_ID));");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    // INSERTAR DATOS USUARIOS
    public Boolean insertData(String name, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("USER_NAME", name);
        values.put("PASSWORD", password);

        long res = db.insert("USERS", null, values);

        if(res == -1) {
            return false;         //si no es posible la insercion o ha fallado
        } else {
            return true;
        }
    }

    //COMPROBAR NOMBRE USUARIO
    public Boolean checkUserName (String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor;
        cursor = db.rawQuery("SELECT * FROM USERS WHERE USER_NAME =?", new String[] {name});
        if (cursor.getCount()>0) {
            return true;
        }else {
            return false;
        }
    }

    // COMPROBAR NOMBRE Y CONTRASEÑA USUARIO
    public Boolean checkUserNamePass (String name, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor;
        cursor = db.rawQuery("SELECT * FROM USERS WHERE USER_NAME =? AND PASSWORD =?", new String[]{name,password});
        if (cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }




    public void addTask (int userId , String task){

        ContentValues signIn = new ContentValues();
        signIn.put("USER_ID", userId);
        signIn.put("NAME", task);


        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO TASKS VALUES('' + userId + '', null, '' + task + '')");

        db.close();


    }

    public String[] getAllTask() {
        String[] tasks=null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM TAREAS WHERE USER_ID = '' + userId + '' ;", null);
        int regs = cursor.getCount();

        if (regs == 0){
            db.close();
            return null;
        } else {
            tasks = new String[regs];
            cursor.moveToFirst();
            for (int i= 0; i<regs; i++) {
                tasks[i] = cursor.getString(1);
                cursor.moveToNext();
            }
            db.close();
            return tasks;
        }
    }
    public void deleteTask (String task) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("TASKS", "NOMBRE=?", new String[]{task});
        db.close();

    }

}
