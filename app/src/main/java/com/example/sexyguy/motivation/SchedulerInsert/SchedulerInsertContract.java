package com.example.sexyguy.motivation.SchedulerInsert;

import android.content.Context;
import android.graphics.Bitmap;

public interface SchedulerInsertContract {

    interface view{
        Context getContextForDB();
        void tmpResult(String result);
    }

    interface presenterForView{
        void inputToDB(String itemName, String itemPicture, String startTime, String endTime);
        void setScheduleImg();
        void updateScheduleToDB( int itemId, String itemName, String itemPicture,String startTime, String endTime);
        int getItemIdFromDB(String itemName);

    }

    interface presenterForModel{
        Context getContextForDB();
        void tmpResult(String result);
    }

    interface model{
        void inputToDB(String itemName, String itemPicture, String startTime, String endTime);
        void setScheduleImg();
        void updateScheduleToDB( int itemId, String itemName, String itemPicture,String startTime, String endTime);
        int getItemIdFromDB(String itemName);
    }
}
