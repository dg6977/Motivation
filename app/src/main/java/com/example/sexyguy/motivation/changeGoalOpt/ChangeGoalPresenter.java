package com.example.sexyguy.motivation.changeGoalOpt;


import android.content.Context;

public class ChangeGoalPresenter implements ChangeGoalContract.presenterForModel,ChangeGoalContract.presenterForView{

    private ChangeGoalContract.view view;
    private ChangeGoalContract.model model;

    public ChangeGoalPresenter(ChangeGoalContract.view view){
        this.view=view;
    }

    public void setModel(ChangeGoalContract.model model){
        this.model=model;
    }

    public void updateChangedGoalString(String changedGoalString){
        model.updateChangedGoalString(changedGoalString);
    }

    @Override
    public Context getContext() {
        return view.getContext();
    }
}
