package com.example.sexyguy.motivation.Scheduler;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import com.example.sexyguy.motivation.ScheduleItem;
import com.example.sexyguy.motivation.ScheduleListAdapter;
import com.example.sexyguy.motivation.SchedulerInsert.SchedulerInsertView;


import com.example.sexyguy.motivation.R;

public class ScheduleView extends AppCompatActivity implements ScheduleContract.view{

    private SchedulePresenter presenter;
    public ListView scheduleListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule);
        setUpMVP();
        setUp();
    }

    private void setUpMVP(){
        presenter=new SchedulePresenter(this);
        ScheduleModel model=new ScheduleModel(presenter);
        presenter.setModel(model);
    }

    private void setUp(){

        ImageButton insertScheduleBtn=(ImageButton)findViewById(R.id.insertScheduleBtn);
        TextView percentOfSuccess=(TextView)findViewById(R.id.percentOfSuccess);

        scheduleListView=(ListView) findViewById(R.id.scheduleListView) ;
        setAdapterForScheduleListView();

        insertScheduleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(getApplicationContext(),SchedulerInsertView.class);
               startActivityForResult(intent,1000);
            }
        });

        scheduleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),String.valueOf(presenter.getScheduleListAdapter().getItemId(position)),Toast.LENGTH_LONG).show();
                startActivityForResult(presenter.getIntentForScheduleItemClicked(position),2000);
            }
        });

       scheduleListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.deleteScheduleItemFromScheduleDB(position);
                setAdapterForScheduleListView();
                return true;
           }
       });

    }

    private void setAdapterForScheduleListView(){
        scheduleListView.setAdapter(presenter.getScheduleListAdapter());
    }

    public Context getContext(){
        return getApplicationContext();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1000:
                    setAdapterForScheduleListView();
                    break;

                case 2000:
                    setAdapterForScheduleListView();
                    break;
            }
        }
    }


}
