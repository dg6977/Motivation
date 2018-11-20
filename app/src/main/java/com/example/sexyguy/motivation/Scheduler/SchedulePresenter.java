package com.example.sexyguy.motivation.Scheduler;

import android.content.Context;
import android.content.Intent;

import com.example.sexyguy.motivation.ScheduleListAdapter;
import com.example.sexyguy.motivation.Scheduler.ScheduleContract;

import java.util.ArrayList;

public class SchedulePresenter implements ScheduleContract.presenterForModel,ScheduleContract.presenterForView{

    private ScheduleContract.view view;
    private ScheduleContract.model model;

    public SchedulePresenter(ScheduleContract.view view){
        this.view=view;
    }

    public void setModel(ScheduleContract.model model){
        this.model=model;
    }

    public void deleteScheduleItemFromScheduleDB(int position){
        model.deleteScheduleItemFromScheduleDB(position);
    }

    @Override
    public Intent getIntentForScheduleItemClicked(int position) {
        return model.getIntentForScheduleItemClicked(position);
    }

    @Override
    public ScheduleListAdapter getScheduleListAdapter() {
        return model.getScheduleListAdapter();
    }


    @Override
    public Context getContext(){
        return view.getContext();
    }
}
