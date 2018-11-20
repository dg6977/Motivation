package com.example.sexyguy.motivation.SchedulerInsert;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.sexyguy.motivation.Alarm;
import com.example.sexyguy.motivation.R;

import java.util.Calendar;

public class SchedulerInsertView extends Activity implements SchedulerInsertContract.view{


    private SchedulerInsertPresenter presenter;

    private TimePickerDialog picker;
    private int startHour,startMinute;
    private int endHour,endMinute;
    private String itemName, itemPicture, startTime, endTime;
    private ImageButton scheduleImgBtn;
    private Intent checkIntent;
    private boolean isStartForUpdate=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scheduleinsert);
        setUpMVP();
        setUp();
    }

    private void setUpMVP(){
        presenter=new SchedulerInsertPresenter(this);
        SchedulerInsertModel model=new SchedulerInsertModel(presenter);
        presenter.setModel(model);
    }

    @SuppressLint("SetTextI18n")
    private void setUp(){
        final EditText titleEdit=(EditText)findViewById(R.id.titleEdit);
        Button insertScheduleBtn=(Button) findViewById(R.id.insertScheduleBtn);
        final EditText startTimeEditText=(EditText) findViewById(R.id.startTimeEdit);
        final EditText endTimeEditText=(EditText) findViewById(R.id.editTimeEdit);

        startTimeEditText.setInputType(InputType.TYPE_NULL);
        endTimeEditText.setInputType(InputType.TYPE_NULL);

        startTimeEditText.setText("00:00");
        endTimeEditText.setText("00:00");

        itemName =titleEdit.getText().toString();

        insertScheduleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemName = titleEdit.getText().toString();
                //
                new Alarm(getApplicationContext()).setAlarm(startTimeEditText.getText().toString());
                //
                if(!isStartForUpdate) {
                    presenter.inputToDB(itemName, itemPicture, startTimeEditText.getText().toString(), endTimeEditText.getText().toString());
                }
                else {
                    presenter.updateScheduleToDB(checkIntent.getIntExtra("scheduleItemId",-1),
                            itemName,itemPicture,startTimeEditText.getText().toString(),endTimeEditText.getText().toString());
                }
                Intent intent = new Intent();
                intent.putExtra("changedSchedule", "changedSchedule");
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        startTimeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr=Calendar.getInstance();
                int hour=cldr.get(Calendar.HOUR_OF_DAY);
                int minutes=cldr.get(Calendar.MINUTE);

                picker=new TimePickerDialog(SchedulerInsertView.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int Hour, int Minute) {
                        startHour=Hour;
                        startMinute=Minute;
                        startTimeEditText.setText(startHour+":"+startMinute);
                        startTime =Integer.toString(startHour)+" : "+Integer.toString(startMinute);
                    }
                },hour,minutes,true);
                picker.show();
            }
        });

        endTimeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr=Calendar.getInstance();
                int hour=cldr.get(Calendar.HOUR_OF_DAY);
                int minutes=cldr.get(Calendar.MINUTE);

                picker=new TimePickerDialog(SchedulerInsertView.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hour, int minute) {
                        endHour=hour;
                        endMinute=minute;
                        endTimeEditText.setText(hour+":"+minute);
                        endTime =Integer.toString(hour)+" : "+Integer.toString(minute);
                    }
                },hour,minutes,true);
                picker.show();
            }
        });

        scheduleImgBtn=(ImageButton) findViewById(R.id.scheduleImgButton);
        scheduleImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //presenter.setScheduleImg();
                itemPicture="img1";
            }
        });

        checkIntent = getIntent();
        if (checkIntent.getStringExtra("scheduleName") != null) {
            isStartForUpdate=true;
            titleEdit.setText(checkIntent.getStringExtra("scheduleName"));
            startTimeEditText.setText(checkIntent.getStringExtra("scheduleStartTime"));
            endTimeEditText.setText(checkIntent.getStringExtra("scheduleEndTime"));
        }
    }

    public Context getContextForDB() {
        return getApplicationContext();
    }

    public void tmpResult(String result){
        //TODO temporary result
        TextView temporaryResult=(TextView) findViewById(R.id.tmpResult);
        temporaryResult.setText(result);
    }
}
