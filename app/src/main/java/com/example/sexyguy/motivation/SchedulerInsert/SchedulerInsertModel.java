package com.example.sexyguy.motivation.SchedulerInsert;

import android.content.Context;
import android.widget.Toast;

import com.example.sexyguy.motivation.DBHelper;

public class SchedulerInsertModel implements SchedulerInsertContract.model{

    private SchedulerInsertContract.presenterForModel presenter;
    private Context context;
    public DBHelper dbHelper;

    public SchedulerInsertModel(SchedulerInsertContract.presenterForModel presenter){
        this.presenter=presenter;
        setContext(presenter.getContextForDB());
        DBHelper dbHelper=new DBHelper(context,"SCHEDULEBOOK.db",null,1);
        this.dbHelper=dbHelper;
    }

    public void inputToDB(String itemName, String itemPicture, String startTime, String endTime){
        dbHelper.insertIntoScheduleBookDB(itemName, itemPicture, startTime, endTime);
        Toast.makeText(context,"inserted",Toast.LENGTH_LONG).show();
        presenter.tmpResult(dbHelper.getResult());
    }

    public void updateScheduleToDB(int itemId, String itemName, String itemPicture,String startTime, String endTime){
        dbHelper.updateFromScheduleBookDB(itemId, itemName, itemPicture, startTime, endTime);
    }

    public void setScheduleImg(){

    }


    private void setContext(Context context){
        this.context=context;
    }



}
