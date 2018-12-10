package com.example.sexyguy.motivation.changeGoalOpt;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sexyguy.motivation.Goal.GoalView;
import com.example.sexyguy.motivation.R;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ChangeGoalView extends Activity implements ChangeGoalContract.view{

    private ChangeGoalPresenter presenter;
    private EditText changeGoalEditText;
    private TextView changeGoalDdayEditText;
    private String changedGoal,changedDate;
    private Intent intent;
    private int year,month,date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.changegoal);
        setUpMVP();
        setUp();
    }

    private void setUpMVP(){
        this.presenter=new ChangeGoalPresenter(this);
        ChangeGoalModel model=new ChangeGoalModel(presenter);
        presenter.setModel(model);
    }

    private void setUp() {
        Button insertChangeGoalBtn = (Button) findViewById(R.id.changeGoalInsertBtn);
        changeGoalEditText = (EditText) findViewById(R.id.changeGoalEditText);
        changeGoalDdayEditText = (TextView) findViewById(R.id.changeGoalDdayEditText);
        GregorianCalendar calendar=new GregorianCalendar();
        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.DATE);
        date=calendar.get(Calendar.DAY_OF_MONTH);
        String currentDate=String.format("%d-%d-%d",year,month+2,date);
        changeGoalDdayEditText.setText(currentDate);

        insertChangeGoalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String changedImg=null;
                changedGoal = changeGoalEditText.getText().toString();
                changedDate=changeGoalDdayEditText.getText().toString();
                presenter.updateChangedGoalString(changedGoal,changedImg,changedDate);
                Intent intent = new Intent();
                intent.putExtra("changedGoal", changedGoal);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        changeGoalDdayEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(ChangeGoalView.this,dateSetListener,year,month,date).show();
            }
        });
    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        //@SuppressLint("DefaultLocale")
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            changedDate=String.format("%d-%d-%d",year,month+1,dayOfMonth);
            changeGoalDdayEditText.setText(changedDate);
        }
    };

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

}
