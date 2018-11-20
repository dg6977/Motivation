package com.example.sexyguy.motivation.Msg;

import android.content.Context;

import com.example.sexyguy.motivation.Msg.MsgContract;

public class MsgModel implements MsgContract.model {

    private MsgContract.presenterForModel presenter;
    private Context context;

    public MsgModel(MsgContract.presenterForModel presenter){
        this.presenter=presenter;
        setContext(presenter.getContextForModel());
    }

    private void setContext(Context context){
        this.context=context;
    }
}
