package com.anwesome.ui.bulletedlist;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anweshmishra on 06/02/17.
 */
public class BulletedList {
    private Activity activity;
    private List<String> items = new ArrayList<>();
    public BulletedList(Activity activity) {
        this.activity = activity;
    }
    public void show() {

    }
    public void addItem(String item) {
        this.items.add(item);
    }
    public void setItems(String items[]) {
        for(String item:items) {
            this.items.add(item);
        }
    }
    public void setItems(List<String> items) {
        this.items = items;
    }
}
