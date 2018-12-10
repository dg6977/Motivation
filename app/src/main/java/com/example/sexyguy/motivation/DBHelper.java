package com.example.sexyguy.motivation;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE SCHEDULEBOOK (itemId INTEGER PRIMARY KEY AUTOINCREMENT, itemName TEXT, itemPicture TEXT, startTime TEXT, endTime TEXT);");
        db.execSQL("CREATE TABLE BASICOPTIONS (itemId INTEGER PRIMARY KEY, goalMsg TEXT, goalImg TEXT, goalDate TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertIntoScheduleBookDB(String itemName, String itemPicuture, String startTime, String endTime) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO SCHEDULEBOOK VALUES(null, '" + itemName + "', '" + itemPicuture + "', '" + startTime + "', '" + endTime + "');");
        db.close();
    }

    public void insertIntoBasicOptionsDB(String goalMsg, String goalImg, String goalDate) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO BASICOPTIONS VALUES(1,'" + goalMsg + "', '" + goalImg + "', '" + goalDate + "');");
    }

    public void updateFromScheduleBookDB(int itemId, String itemName, String itemPicture, String startTime, String endTime) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE SCHEDULEBOOK " +
                "SET itemName='" + itemName + "' , itemPicture='" + itemPicture + "' , startTime= '" + startTime + "' , endTime= '" + endTime + "' " +
                "WHERE itemId=" + itemId + ";");
        db.close();
    }

    public void updateFromBasicOptionsDB(String goalMsg, String goalImg, String goalDate) {
        SQLiteDatabase db = getWritableDatabase();
        //TODO where itemid가 아닌 다른것으로 구별
        db.execSQL("UPDATE BASICOPTIONS SET goalMsg='" + goalMsg + "', goalImg='" + goalImg + "', goalDate='" + goalDate + "';");
        db.close();
    }

    public void deleteFromScheduleBookDB(int itemId) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM SCHEDULEBOOK WHERE itemId=" + itemId + ";");
        db.close();
    }

    public int getItemIdByItemName(String itemName){
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT itemId FROM SCHEDULEBOOK WHERE itemName='"+itemName+"';",null);
        int result=0;
        while (cursor.moveToNext()){
            result=Integer.valueOf(cursor.getString(0));
        }
        return result;
    }

    public String getResult() {
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        Cursor cursor = db.rawQuery("SELECT * FROM SCHEDULEBOOK", null);
        while (cursor.moveToNext()) {
            //TODO result output form

            result += cursor.getString(0)
                    + " : "
                    + cursor.getString(1)
                    + " | "
                    + cursor.getString(2)
                    + " | "
                    + cursor.getString(3)
                    + " | "
                    + cursor.getString(4)
                    + "\n";

        }

        return result;
    }
}


