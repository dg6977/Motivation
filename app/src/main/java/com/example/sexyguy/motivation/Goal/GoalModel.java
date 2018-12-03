package com.example.sexyguy.motivation.Goal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sexyguy.motivation.DBHelper;
import com.example.sexyguy.motivation.MenuListItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GoalModel implements GoalContract.model {

    private GoalContract.presenterForModel presenter;
    private Context context;
    private DBHelper dbHelper;
    private SQLiteDatabase db;
    private String goalDate = "0000-00-00";

    private ArrayList<MenuListItem> menuList = new ArrayList<MenuListItem>();

    public GoalModel(GoalContract.presenterForModel presenter) {
        this.presenter = presenter;
        setContext(presenter.getContextForDB());
        dbHelper = new DBHelper(context, "BASICOPTIONS", null, 1);

    }

    private void setContext(Context context) {
        this.context = context;
    }

    public String getGoalStringFromDB() {
        String goalMsg = null;
        String goalImg = null;
        db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("select * from BASICOPTIONS", null);
        int i = c.getCount();
        if (c.getCount() == 0) {
            goalMsg = "Be a great Man";
            goalImg = "goalImg";
            dbHelper.insertIntoBasicOptionsDB(goalMsg, goalImg, goalDate);
        } else {
            while (c.moveToNext()) {
                goalImg = c.getString(c.getColumnIndex("goalImg"));
                goalMsg = c.getString(c.getColumnIndex("goalMsg"));
                goalDate = c.getString(c.getColumnIndex("goalDate"));
            }
        }
        return goalMsg;
    }

    @Override
    public ArrayList getList() {

        MenuListItem tmp;
        tmp = new MenuListItem();
        tmp.setMenuName("Change Goal");
        menuList.add(tmp);
        tmp = new MenuListItem();
        tmp.setMenuName("Background Image");
        menuList.add(tmp);
        tmp = new MenuListItem();
        tmp.setMenuName("Background Music");
        menuList.add(tmp);

        return menuList;
    }

    public int doDiffOfDate()  {
        long now=System.currentTimeMillis();
        Date date=new Date(now);
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
        String tmpDate=formatter.format(date);

        Date currentDate= null;
        Date goalDate2=null;
        try {
            currentDate = formatter.parse(tmpDate);
            goalDate2=formatter.parse(goalDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long diff=goalDate2.getTime()-currentDate.getTime();
        long diffDays=diff/(24*60*60*1000);

        return (int)diffDays;
    }

}
