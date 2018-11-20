package com.example.sexyguy.motivation.Msg;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.sexyguy.motivation.Msg.MsgContract;

public class MsgPresenter implements MsgContract.presenterForModel, MsgContract.presenterForView{

    private MsgContract.view view;
    private MsgContract.model model;


    public MsgPresenter(MsgContract.view view){
        this.view=view;
    }

    public void setModel(MsgContract.model model) {
        this.model = model;
    }

    public void setCheerUp(String msg, Bitmap img){
        view.setCheerUp(msg, img);
    }

    public Context getContextForModel(){
        return view.getContextForModel();
    }
}
