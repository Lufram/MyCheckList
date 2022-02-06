package com.example.mychecklist.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbController extends SQLiteOpenHelper {


    private int sessionId;

    public DbController(Context context, int sessionId) {
        super(context, "com.example.myapplication.db", null, 1);
        this.sessionId = sessionId;
    }


    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    // Creamos 2 BBBDD usuarios, tareas.
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_kets=ON");
        db.execSQL("CREATE TABLE USERS (USER_ID INTEGER PRIMARY KEY AUTOINCREMENT,  USER_NAME TEXT NOT NULL, PASSWORD INTEGER  NOT NULL);");
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

    // DEVUELVE ID USUARIO
    public int getIdUserByName (String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor;
        cursor = db.rawQuery("SELECT USER_ID FROM USERS WHERE USER_NAME =?",  new String[]{name});
        if (cursor.getCount()>0) {
            cursor.moveToFirst();
            int id = cursor.getInt(0);
            return id;
        }else {
            return 0;
        }
    }

    // devuelve numero de registros que tiene la tabla
    public int regLength() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor;
        cursor = db.rawQuery("SELECT * FROM TASKS WHERE USER_ID = " + sessionId + ";", null);

        return cursor.getCount();
    }


    // AÑADIR TAREA A LA TABLA
    public void addTask (int userId , String task){

        ContentValues signIn = new ContentValues();
        signIn.put("TASK_NAME", task);
        signIn.put("USER_ID", userId);


        SQLiteDatabase db = this.getWritableDatabase();
        db.insert("TASKS",null, signIn);

        db.close();


    }

    // DEVUELVE TODAS LAS TAREAS DE LA TABLA DE EL USUARIO COMPARANDO POR ID
    public String[] getAllTask() {
        String[] tasks;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM TASKS WHERE USER_ID = " + sessionId + ";", null);
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

    //BORRA TAREA
    public void deleteTask (String task) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("TASKS", "TASK_NAME=?", new String[]{task});
        db.close();

    }

}
