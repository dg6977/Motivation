package com.example.sexyguy.motivation.changeBackOpt;

public class ChangeBackModel implements ChangeBackContract.model{

    private ChangeBackContract.presenterForModel presenter;

    public ChangeBackModel(ChangeBackContract.presenterForModel presenter){
        this.presenter=presenter;
    }

}
