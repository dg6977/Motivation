package com.example.sexyguy.motivation.Goal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sexyguy.motivation.MenuListAdapter;
import com.example.sexyguy.motivation.R;
import com.example.sexyguy.motivation.Scheduler.ScheduleView;
import com.example.sexyguy.motivation.changeBackOpt.ChangeBackView;
import com.example.sexyguy.motivation.changeGoalOpt.ChangeGoalView;

import java.util.ArrayList;

public class GoalView extends AppCompatActivity implements GoalContract.view{

    private GoalPresenter presenter;

    private TextView goalText;
    private ImageButton menuBtn;
    private ArrayList<MenuItem> data;
    private MenuListAdapter menuAdapter;

    boolean isPageOpen=false;
    Animation translateLeftAnim;
    Animation translateRightAnim;
    LinearLayout slidingPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goal);
        setUpMVP();
        setUp();
    }

    @Override
    public void onBackPressed(){
        if(isPageOpen){
            slidingPage.startAnimation(translateRightAnim);
            slidingPage.setVisibility(View.GONE);
            isPageOpen=false;
        }
        else {
            super.onBackPressed();
        }
    }

    private void setUpMVP(){
        this.presenter=new GoalPresenter(this);
        GoalModel model=new GoalModel(presenter);
        presenter.setModel(model);
    }

    private void setUp(){

        ListView listView=(ListView) findViewById(R.id.menuListView);
        goalText=(TextView)findViewById(R.id.goalText);
        menuBtn=(ImageButton)findViewById(R.id.menuBtn);
        ImageButton scheduleBtn=(ImageButton) findViewById(R.id.scheduleBtn);

        //
        slidingPage=(LinearLayout)findViewById(R.id.menuListLayout);

        translateLeftAnim= AnimationUtils.loadAnimation(this,R.anim.translate_left);
        translateRightAnim=AnimationUtils.loadAnimation(this,R.anim.translate_right);

        SlidingPageAnimationListener animationListener=new SlidingPageAnimationListener();
        translateLeftAnim.setAnimationListener(animationListener);
        translateRightAnim.setAnimationListener(animationListener);

        //
        menuAdapter=new MenuListAdapter(presenter.getList());
        listView.setAdapter(menuAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    Intent intent=new Intent(getApplicationContext(), ChangeGoalView.class);
                    startActivityForResult(intent,1000);
                }
                else if(position==1 || position==2){
                    Intent intent = new Intent(getApplicationContext(), ChangeBackView.class);
                    intent.putExtra("WhatOpt",String.valueOf(position));
                    startActivityForResult(intent,2000);
                }

            }
        });

        goalText.setText(presenter.getGoalStringFromDB());

        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPageOpen){
                    slidingPage.startAnimation(translateRightAnim);
                    isPageOpen=false;
                }
                else {
                    slidingPage.setVisibility(View.VISIBLE);
                    slidingPage.startAnimation(translateLeftAnim);
                    isPageOpen=true;
                }
            }
        });

        scheduleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), ScheduleView.class);
                startActivity(intent);
            }
        });

    }

    public Context getContextForDB(){
        return getApplicationContext();
    }

    private class SlidingPageAnimationListener implements Animation.AnimationListener{

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if(isPageOpen){
                slidingPage.clearFocus();
                isPageOpen=true;
            }
            else {
                isPageOpen=false;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1000:
                    goalText.setText(data.getStringExtra("changedGoal"));
                    break;
            }
        }
    }
}
