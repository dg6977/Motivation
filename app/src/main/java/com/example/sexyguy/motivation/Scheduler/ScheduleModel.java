package com.example.sexyguy.motivation.Scheduler;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sexyguy.motivation.DBHelper;
import com.example.sexyguy.motivation.ScheduleItem;
import com.example.sexyguy.motivation.ScheduleListAdapter;
import com.example.sexyguy.motivation.SchedulerInsert.SchedulerInsertView;

import java.util.ArrayList;

public class ScheduleModel implements ScheduleContract.model{

    private ScheduleContract.presenterForModel presenter;
    private Context context;
    private DBHelper dbHelper;
    private SQLiteDatabase db;
    public ArrayList<ScheduleItem> scheduleItems=new ArrayList<ScheduleItem>();
    public ScheduleListAdapter scheduleListAdapter;



    public ScheduleModel(ScheduleContract.presenterForModel presenter){
        this.presenter=presenter;
        setContext(presenter.getContext());
    }

    private void setContext(Context context){
        this.context=context;
    }

    private ArrayList getScheduleItemFromDB(){
        dbHelper=new DBHelper(context,"SCHEDULEBOOK.db",null,1);
        db=dbHelper.getReadableDatabase();
        Cursor c=db.rawQuery("select * from SCHEDULEBOOK",null);
        scheduleItems.clear();
        while(c.moveToNext()){
            int scheduleItemId=c.getInt(c.getColumnIndex("itemId"));
            String scheduleName=c.getString(c.getColumnIndex("itemName"));
            String scheduleImg=c.getString(c.getColumnIndex("itemPicture"));
            String startTime=c.getString(c.getColumnIndex("startTime"));
            String endTime=c.getString(c.getColumnIndex("endTime"));

            ScheduleItem item=new ScheduleItem();
            item.setScheduleItemId(scheduleItemId);
            item.setScheduleName(scheduleName);
            item.setScheduleImg(scheduleImg);
            item.setScheduleStartTime(startTime);
            item.setScheduleEndTime(endTime);
            scheduleItems.add(item);
        }
        return scheduleItems;
    }

    @Override
    public ScheduleListAdapter getScheduleListAdapter() {
        getScheduleItemFromDB();
        scheduleListAdapter=new ScheduleListAdapter(scheduleItems);
        return scheduleListAdapter;
    }

    public void deleteScheduleItemFromScheduleDB(int position){
        dbHelper.deleteFromScheduleBookDB((int)(long)scheduleListAdapter.getItemId(position));
    }

    @Override
    public Intent getIntentForScheduleItemClicked(int position) {
        Intent intent=new Intent(context,SchedulerInsertView.class);
        intent.putExtra("isComeForUpdate",true);
        intent.putExtra("scheduleItemId",(int)(long)scheduleListAdapter.getItemId(position));
        intent.putExtra("scheduleName",scheduleItems.get(position).getScheduleName());
        intent.putExtra("scheduleStartTime",scheduleItems.get(position).getScheduleStartTime());
        intent.putExtra("scheduleEndTime",scheduleItems.get(position).getScheduleEndTime());
        intent.putExtra("scheduleImg",scheduleItems.get(position).getScheduleImg());
        return intent;
    }
}
