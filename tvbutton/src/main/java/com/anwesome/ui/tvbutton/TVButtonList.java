package com.anwesome.ui.tvbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

/**
 * Created by anweshmishra on 28/05/17.
 */

public class TVButtonList {
    private Activity activity;
    private ScrollView scrollView;
    private Listlayout listlayout;
    private boolean isShown = false;
    public TVButtonList(Activity activity) {
        this.activity = activity;
        this.scrollView = new ScrollView(activity);
        listlayout = new Listlayout(activity);
        scrollView.addView(listlayout,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }
    public void addButton() {
        if(!isShown) {
            listlayout.addButton();
        }
    }
    public void show() {
        if(!isShown) {
            activity.setContentView(scrollView);
            isShown = true;
        }
    }
    private class Listlayout extends ViewGroup{
        private int w,h;
        public Listlayout(Context context) {
            super(context);
            initDimensions(context);
        }
        public void initDimensions(Context context) {
            DisplayManager displayManager = (DisplayManager) context.getSystemService(Context.DISPLAY_SERVICE);
            Display display = displayManager.getDisplay(0);
            if(display != null) {
                Point size = new Point();
                display.getRealSize(size);
                w = size.x;
                h = size.y;
            }
        }
        public void addButton() {
            TVButtonView tvButtonView = new TVButtonView(getContext());
            addView(tvButtonView,new LayoutParams(Math.max(w,h)/5,Math.max(w,h)/5));
            requestLayout();
        }
        public void onMeasure(int wspec,int hspec) {
            int hmax = h/20;
            for(int i=0;i<getChildCount();i++) {
                View child = getChildAt(i);
                measureChild(child,wspec,hspec);
                hmax += (h/20+child.getMeasuredHeight());
            }
            setMeasuredDimension(w,Math.max(hmax,h));
        }
        public void onLayout(boolean reloaded,int a,int b,int w,int h) {
            int y = h/20;
            for(int i=0;i<getChildCount();i++) {
                View child = getChildAt(i);
                int x = w/10;
                if(i%2 == 1) {
                    x += w/2;
                }
                child.layout(x,y,x+child.getMeasuredWidth(),y+child.getMeasuredHeight());
                y += (child.getMeasuredHeight()+h/20);
            }
        }
    }
}
