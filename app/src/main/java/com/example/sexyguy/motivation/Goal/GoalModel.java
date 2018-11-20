package com.example.sexyguy.motivation.Goal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sexyguy.motivation.DBHelper;
import com.example.sexyguy.motivation.MenuListItem;

import java.util.ArrayList;

public class GoalModel implements GoalContract.model {

    private GoalContract.presenterForModel presenter;
    private Context context;
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    private ArrayList<MenuListItem> menuList=new ArrayList<MenuListItem>();

    public GoalModel(GoalContract.presenterForModel presenter){
        this.presenter=presenter;
        setContext(presenter.getContextForDB());
        dbHelper=new DBHelper(context,"BASICOPTIONS",null,1);

    }

    private void setContext(Context context){
        this.context=context;
    }

    public String getGoalStringFromDB(){
        String goalMsg=null;
        String goalImg=null;
        db=dbHelper.getReadableDatabase();
        Cursor c=db.rawQuery("select * from BASICOPTIONS",null);
        int i=c.getCount();
        if (c.getCount()==0) {
            goalMsg = "Be a great Man";
            goalImg = "goalImg";
            dbHelper.insertIntoBasicOptionsDB(goalMsg, goalImg);
        } else {
            while(c.moveToNext()) {
                goalImg = c.getString(c.getColumnIndex("goalImg"));
                goalMsg = c.getString(c.getColumnIndex("goalMsg"));
            }
        }

        return goalMsg;
    }

    @Override
    public ArrayList getList() {

        MenuListItem tmp;
        tmp=new MenuListItem();
        tmp.setMenuName("Change Goal");
        menuList.add(tmp);
        tmp=new MenuListItem();
        tmp.setMenuName("Background Image");
        menuList.add(tmp);
        tmp=new MenuListItem();
        tmp.setMenuName("Background Music");
        menuList.add(tmp);

        return menuList;
    }
}
