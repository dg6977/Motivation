package com.example.sexyguy.motivation.SchedulerInsert;

import android.content.Context;
import android.graphics.Bitmap;

public class SchedulerInsertPresenter implements SchedulerInsertContract.presenterForModel,SchedulerInsertContract.presenterForView{

    private SchedulerInsertContract.view view;
    private SchedulerInsertContract.model model;

    public SchedulerInsertPresenter(SchedulerInsertContract.view view){
        this.view=view;
    }

    public void setModel(SchedulerInsertContract.model model){
        this.model=model;
    }


    public Context getContextForDB() {
        return view.getContextForDB();
    }

    public void inputToDB(String itemName, String itemPicture, String startTime, String endTime){
        model.inputToDB(itemName,itemPicture,startTime,endTime);
    }

    public void setScheduleImg(){
        model.setScheduleImg();
    }

    public void tmpResult(String result){
        view.tmpResult(result);
    }

    public void updateScheduleToDB( int itemId, String itemName, String itemPicture,String startTime, String endTime){
        model.updateScheduleToDB(itemId,itemName,itemPicture,startTime,endTime);
    }

}
