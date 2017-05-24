package com.anwesome.ui.circularystbuttonlist;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

/**
 * Created by anweshmishra on 25/05/17.
 */

public class CYSTList {
    private Activity activity;
    private boolean isShown = false;
    private ScrollView scrollView;
    public CYSTList(Activity activity) {
        this.activity = activity;
    }
    public void show() {
        if(!isShown) {
            isShown = true;
        }
    }
    public void addButton() {
        if(!isShown) {

        }
    }
    private class ListLayout extends ViewGroup {
        private int w,h,viewSize;
        public ListLayout(Context context) {
            super(context);
        }
        public void addButton() {
            addView(new CircularYSTButtonView(getContext()),new LayoutParams(w,viewSize));
            requestLayout();
        }
        public void initDimension(Context context) {
            DisplayManager displayManager = (DisplayManager)context.getSystemService(Context.DISPLAY_SERVICE);
            Display display = displayManager.getDisplay(0);
            if(display != null) {
                Point size = new Point();
                display.getRealSize(size);
                w = size.x;
                h = size.y;
                viewSize = h/3;
            }
        }
        public void onMeasure(int wspec,int hspec) {
            int hmax = h/25;
            for(int i=0;i<getChildCount();i++) {
                View child = getChildAt(i);
                measureChild(child,wspec,hspec);
                hmax += child.getMeasuredHeight()+h/25;
            }
            setMeasuredDimension(w,Math.max(h,hmax+viewSize+h/25));
        }
        public void onLayout(boolean reloaded,int a,int b,int wa,int ha) {
            int y = h/25;
            for(int i=0;i<getChildCount();i++) {
                View child = getChildAt(i);
                child.layout(0,y,w,y+h/25);
                y += child.getMeasuredHeight()+h/25;
            }
        }
    }
}
