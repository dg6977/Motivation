package com.example.sexyguy.motivation.Goal;

import android.content.Context;

import java.util.ArrayList;

public interface GoalContract {

    interface view{

        Context getContextForDB();
    }

    interface presneterForView{
        ArrayList getList();
        String getGoalStringFromDB();
    }

    interface presenterForModel{

        Context getContextForDB();
    }

    interface model{
        ArrayList getList();
        String getGoalStringFromDB();
    }
}

