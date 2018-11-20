package com.example.sexyguy.motivation.changeGoalOpt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.example.sexyguy.motivation.Goal.GoalView;
import com.example.sexyguy.motivation.R;

public class ChangeGoalView extends Activity implements ChangeGoalContract.view{

    private ChangeGoalPresenter presenter;
    private EditText changeGoalEditText;
    private String changedGoal;
    private Intent intent;

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


        insertChangeGoalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changedGoal = changeGoalEditText.getText().toString();
                presenter.updateChangedGoalString(changedGoal);
                Intent intent = new Intent();
                intent.putExtra("changedGoal", changedGoal);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

}
