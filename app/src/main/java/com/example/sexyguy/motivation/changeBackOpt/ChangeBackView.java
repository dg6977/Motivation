package com.example.sexyguy.motivation.changeBackOpt;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.sexyguy.motivation.R;


public class ChangeBackView extends Activity implements ChangeBackContract.view{

    private ChangeBackPresenter presenter;
    private Intent intent;
    private String whatOpt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.changebackopt);

        intent=getIntent();
        whatOpt=intent.getStringExtra("WhatOpt");

        setUpMVP();
        setUp();
    }

    private void setUpMVP(){
        this.presenter=new ChangeBackPresenter(this);
        ChangeBackModel model=new ChangeBackModel(presenter);
        presenter.setModel(model);
    }

    @SuppressLint("SetTextI18n")
    private void setUp(){

        ImageButton fileDirectoryBtn=(ImageButton) findViewById(R.id.fileDirectoryBtn);
        TextView fileDirectoryTxtView=(TextView) findViewById(R.id.fileDirectoryTextView);

        if(whatOpt.equals("1")){
            fileDirectoryTxtView.setText("Change Image");
        }
        else{
            fileDirectoryTxtView.setText("Change Music");
        }


        fileDirectoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


}
