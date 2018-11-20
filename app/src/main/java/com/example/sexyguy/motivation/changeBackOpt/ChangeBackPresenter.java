package com.example.sexyguy.motivation.changeBackOpt;

public class ChangeBackPresenter implements ChangeBackContract.presenterForModel,ChangeBackContract.presenterForView{

    private ChangeBackContract.view view;
    private ChangeBackContract.model model;

    public ChangeBackPresenter(ChangeBackContract.view view){
        this.view=view;
    }

    public void setModel(ChangeBackContract.model model){
        this.model=model;
    }

}
