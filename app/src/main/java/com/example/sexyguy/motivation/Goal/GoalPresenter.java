package com.example.sexyguy.motivation.Goal;

import android.content.Context;

import com.example.sexyguy.motivation.Goal.GoalContract;

import java.util.ArrayList;

public class GoalPresenter implements GoalContract.presenterForModel,GoalContract.presneterForView {

    private GoalContract.view view;
    private GoalContract.model model;

    public GoalPresenter(GoalContract.view view){
        this.view=view;
    }

    public void setModel(GoalContract.model model) {
        this.model = model;
    }

    public String getGoalStringFromDB(){
        return model.getGoalStringFromDB();
    }

    @Override
    public int doDiffOfDate() {
        return model.doDiffOfDate();
    }

    @Override
    public ArrayList getList() {
        return model.getList();
    }

    @Override
    public Context getContextForDB() {
        return view.getContextForDB();
    }
}
