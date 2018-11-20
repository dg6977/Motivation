package com.example.sexyguy.motivation.Msg;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sexyguy.motivation.Goal.GoalView;
import com.example.sexyguy.motivation.Msg.MsgContract;
import com.example.sexyguy.motivation.Msg.MsgModel;
import com.example.sexyguy.motivation.Msg.MsgPresenter;
import com.example.sexyguy.motivation.R;

public class MsgView extends AppCompatActivity implements MsgContract.view{

    private MsgPresenter presenter;
    private MsgModel model;
    private GoalView goalView;
    private Intent intent;

    private TextView cheerUpText;
    private ImageView cheerUpBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message);

        setUpMVP();
        setUp();
    }

    private void setUpMVP(){
        presenter=new MsgPresenter(this);
        MsgModel model=new MsgModel(presenter);
        presenter.setModel(model);
    }

    private void setUp(){
        goalView =new GoalView();

        intent=new Intent(getApplicationContext(),GoalView.class);

        cheerUpText =(TextView)findViewById(R.id.cheerUp);
        cheerUpBack = (ImageView)findViewById(R.id.cheerUpBack);

        cheerUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("goto","next");
                startActivity(intent);
            }
        });
    }


    public void setCheerUp(String msg, Bitmap back){
        cheerUpText.setText(msg);
        cheerUpBack.setImageBitmap(back);

    }
    public Context getContextForModel(){
        return getApplicationContext();
    }

}
