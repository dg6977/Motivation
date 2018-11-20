package com.example.sexyguy.motivation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MenuListAdapter extends BaseAdapter {

    private ArrayList<MenuListItem> menuListItems;

    public MenuListAdapter(ArrayList<MenuListItem> menuListItems){
        this.menuListItems=menuListItems;
    }

    @Override
    public int getCount() {
        return menuListItems.size();
    }

    @Override
    public Object getItem(int position) {
        return menuListItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context=parent.getContext();

        if(convertView==null){
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.menuitem,parent,false);
        }

        TextView menuNameTextView=(TextView) convertView.findViewById(R.id.menuItemNameTextView);

        MenuListItem menuListItem=menuListItems.get(position);

        menuNameTextView.setText(menuListItem.getMenuName());

        return convertView;
    }

    public void addItem(String menuName){
        MenuListItem menuListItem=new MenuListItem();
        menuListItem.setMenuName(menuName);
    }

}
