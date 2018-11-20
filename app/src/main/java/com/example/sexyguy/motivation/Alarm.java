package com.example.sexyguy.motivation;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

public class Alarm {
    private Context context;
    private String startTime;
    private int i=0;
    private AlarmManager am;

    public Alarm(Context context){
        this.context=context;
    }

    public void setAlarm(String startTime){
        this.startTime=startTime;

        am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        Intent intent=new Intent(context,BroadCastD.class);

        intent.putExtra("startTime",startTime);

        PendingIntent sender=PendingIntent.getBroadcast(context,0,intent,0);

        Calendar calendar=Calendar.getInstance();

        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DATE), getStartHour(startTime),getStartMinute(startTime),0);

        am.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),sender);
    }

    //TODO cancel Alarm Service
    public void cancelAlarm(){

    }

    private int getStartHour(String startTime){
        String []arr=startTime.split("");
        StringBuilder tmp = new StringBuilder();
        while(true){
            if(arr[i].equals(":")) break;
            tmp.append(arr[i]);
            i++;
        }
        return Integer.valueOf(tmp.toString());
    }

    private int getStartMinute(String startTime){
        String []arr=startTime.split("");
        i++;
        StringBuilder tmp=new StringBuilder();
        for(int j=i;j<arr.length;j++){
            tmp.append(arr[j]);
        }
        return Integer.valueOf(tmp.toString());
    }

}
