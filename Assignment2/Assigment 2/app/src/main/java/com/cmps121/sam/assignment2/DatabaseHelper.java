package com.cmps121.sam.assignment2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.util.Log;

import java.io.ByteArrayOutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    private static final String TABLE_NAME = "photo_table_three";
    private static final String COL1 = "ID";
    private static final String COL2 = "name";
    private static final String COL3 = "url";
    private static final String COL4 = "actualURL";

    public DatabaseHelper(Context context){
        super(context, TABLE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table photo_table_three("
                + "id integer primary key autoincrement,"
                + "name TEXT,"
                + "url BLOB,"
                + "actualURL TEXT"+ ");");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String title, byte[] image, String url){       //bitmap url
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();              //pushes title and photo to db
        contentValues.put(COL2, title);
        contentValues.put(COL3, image);
        contentValues.put(COL4, url);

        Log.d(TAG, "addData: Adding "+title+" to "+TABLE_NAME);
        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * From " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }





    public Cursor getItemID(String title){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT "+ COL1 + " FROM " + TABLE_NAME + " WHERE " + COL2 + " = '" + title + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }



    public Cursor getItemTitle(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT "+ COL2 + " FROM " + TABLE_NAME + " WHERE " + COL1 + " = '" + id + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }



    public void delete(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + COL1 + " = '" + id + "'" + " AND " + COL2 + " = '" + name + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }
}