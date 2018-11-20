package com.example.sexyguy.motivation.Scheduler;

import android.content.Context;
import android.content.Intent;

import com.example.sexyguy.motivation.ScheduleListAdapter;

import java.util.ArrayList;

public interface ScheduleContract {

    interface view{
        Context getContext();
    }

    interface presenterForView{
        ScheduleListAdapter getScheduleListAdapter();
        Intent getIntentForScheduleItemClicked(int position);
        void deleteScheduleItemFromScheduleDB(int position);
    }

    interface presenterForModel{
        Context getContext();
    }

    interface model{
        ScheduleListAdapter getScheduleListAdapter();
        Intent getIntentForScheduleItemClicked(int position);
        void deleteScheduleItemFromScheduleDB(int position);
    }

}
