// Created by: Aino Räkköläinen
// Sources for this code:
// How to create a strikethrough text when checkBox is checked:
// https://stackoverflow.com/questions/9786544/creating-a-strikethrough-text
// How to display HashMap in ListView:
// https://stackoverflow.com/questions/19466757/hashmap-to-listview

package com.example.mymobileapplication;

import static java.lang.Integer.parseInt;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ItemHashMapAdapter extends BaseAdapter {
    LayoutInflater mInFlater;
    //String[] items;
    //String[] amounts;
    //ArrayList<GroceryItem> groceryItemArrayList;
    ArrayList myGroceries;
    HashMap<String, GroceryItem> groceryItemHashMap;
    Context context;

    public ItemHashMapAdapter(Context c, HashMap<String, GroceryItem> g) {
        context = c;
        myGroceries = new ArrayList();
        myGroceries.addAll(g.entrySet());
        //groceryItemHashMap = g;
        mInFlater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return myGroceries.size();
    }

    @Override
    public HashMap.Entry<String, GroceryItem> getItem(int i) {
        return (HashMap.Entry) myGroceries.get(i) ;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        if (v == null) {
            v = mInFlater.inflate(R.layout.grocery_list_items, null);
        }
        TextView nameTextView = (TextView) v.findViewById(R.id.itemNameTextView);
        HashMap.Entry<String, GroceryItem> item = getItem(i);
        nameTextView.setText(item.getValue().getNumber() + item.getKey());
        if (item.getValue().getNumber() > 1) {
            nameTextView.setText(item.getValue().getNumber() + " " + item.getKey() + "s");
        } else {
            nameTextView.setText(item.getValue().getNumber() + " " + item.getKey());
        }

        CheckBox checkBoxItem = (CheckBox) v.findViewById(R.id.checkBox);
        checkBoxItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    nameTextView.setPaintFlags(nameTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                } else {
                    nameTextView.setPaintFlags(nameTextView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                }
            }
        });
        if (checkBoxItem.isChecked() == true) {
        }
        return v;
    }
}

