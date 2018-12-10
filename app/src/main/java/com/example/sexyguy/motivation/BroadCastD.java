package com.example.sexyguy.motivation;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;

public class BroadCastD extends BroadcastReceiver {

    String INTENT_ACTION=Intent.ACTION_BOOT_COMPLETED;
    DBHelper dbHelper;
    SQLiteDatabase db;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent pendingIntent=PendingIntent.getActivity(context,0,new Intent(context, Alarm.class),PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Builder builder=new Notification.Builder(context);

        String startTime=intent.getStringExtra("startTime");
        String scheduleTitle = null;

        dbHelper=new DBHelper(context,"SCHEDULEBOOK.db",null,1);
        db=dbHelper.getReadableDatabase();
        @SuppressLint("Recycle") Cursor c=db.rawQuery("select * from SCHEDULEBOOK where startTime='"+startTime+"';",null);

        while(c.moveToNext()) {
            scheduleTitle = c.getString(c.getColumnIndex("itemName"));
        }

        builder.setSmallIcon(R.drawable.menu).setTicker("HETT").setWhen(System.currentTimeMillis())
                .setNumber(1).setContentTitle(scheduleTitle).setContentText(scheduleTitle)
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE).setContentIntent(pendingIntent).setAutoCancel(true);

        notificationManager.notify(1,builder.build());
    }


    private void setup(){

    }
}
