package com.example.sexyguy.motivation.changeGoalOpt;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sexyguy.motivation.DBHelper;

public class ChangeGoalModel implements ChangeGoalContract.model{

    private ChangeGoalContract.presenterForModel presenter;
    private DBHelper dbHelper;
    private SQLiteDatabase db;
    private Context context;

    public ChangeGoalModel(ChangeGoalContract.presenterForModel presenter){
        this.presenter=presenter;
        setContext(presenter.getContext());
        dbHelper=new DBHelper(context,"BASICOPTIONS",null,1);
    }

    public void updateChangedGoalString(String changedGoal){
        String goalImg=null;
        db=dbHelper.getWritableDatabase();
        Cursor c=db.rawQuery("select * from BASICOPTIONS",null);
        while(c.moveToNext()){
            String temp=c.getString(c.getColumnIndex("goalMsg"));

            goalImg=c.getString(c.getColumnIndex("goalImg"));
        }
        dbHelper.updateFromBasicOptionsDB(changedGoal,goalImg);
    }

    private void setContext(Context context){
        this.context=context;
    }

}
