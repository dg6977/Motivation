package com.example.sexyguy.motivation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ScheduleListAdapter extends BaseAdapter {

    private ArrayList<ScheduleItem> scheduleItems;

    public ScheduleListAdapter(ArrayList<ScheduleItem> scheduleItems){
        this.scheduleItems=scheduleItems;
    }

    @Override
    public int getCount() {
        return scheduleItems.size();
    }

    @Override
    public Object getItem(int position) {
        return scheduleItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return scheduleItems.get(position).getScheduleItemId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context=parent.getContext();

        if(convertView==null){
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.scheduleitem,parent,false);
        }

        TextView scheduleNameTextView=(TextView)convertView.findViewById(R.id.scheduleNameTextView);
        TextView scheduleStartTimeTextView=(TextView)convertView.findViewById(R.id.scheduleStartTimeTextView);
        TextView scheduleEndTimeTextView=(TextView)convertView.findViewById(R.id.scheduleEndTimeTextView);

        ScheduleItem scheduleItem=scheduleItems.get(position);

        scheduleNameTextView.setText(scheduleItem.getScheduleName());
        scheduleStartTimeTextView.setText(scheduleItem.getScheduleStartTime());
        scheduleEndTimeTextView.setText(scheduleItem.getScheduleEndTime());

        return convertView;
    }

    public void addItem(String name, String picture, String startTime, String endTime){
        ScheduleItem scheduleItem=new ScheduleItem();
        scheduleItem.setScheduleName(name);
        scheduleItem.setScheduleImg(picture);
        scheduleItem.setScheduleStartTime(startTime);
        scheduleItem.setScheduleEndTime(endTime);

        scheduleItems.add(scheduleItem);
    }

}
