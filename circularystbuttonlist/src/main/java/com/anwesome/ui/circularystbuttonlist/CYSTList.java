package com.anwesome.ui.circularystbuttonlist;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import java.util.List;

/**
 * Created by anweshmishra on 25/05/17.
 */

public class CYSTList {
    private Activity activity;
    private boolean isShown = false;
    private ListLayout listLayout;
    private ScrollView scrollView;
    public CYSTList(Activity activity) {
        this.activity = activity;
        scrollView = new ScrollView(activity);
        listLayout = new ListLayout(activity);
        scrollView.addView(listLayout,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }
    public void show() {
        if(!isShown) {
            activity.setContentView(scrollView);
            isShown = true;
        }
    }
    public void addButton(OnSelectionChangeListener onSelectionChangeListener) {
        if(!isShown) {
            listLayout.addButton(onSelectionChangeListener);
        }
    }
    private class ListLayout extends ViewGroup {
        private int w,h,viewSize;
        public ListLayout(Context context) {
            super(context);
            initDimension(context);
        }
        public void addButton(OnSelectionChangeListener onSelectionChangeListener) {
            CircularYSTButtonView view = new CircularYSTButtonView(getContext());
            addView(view,new LayoutParams(viewSize,viewSize));
            view.setOnSelectionChangeListener(onSelectionChangeListener);
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
                viewSize = w/2;
            }
        }
        public void onMeasure(int wspec,int hspec) {
            int hmax = h/25;
            for(int i=0;i<getChildCount();i++) {
                View child = getChildAt(i);
                measureChild(child,wspec,hspec);
                hmax += child.getMeasuredHeight()+h/25;
            }
            setMeasuredDimension(w,Math.max(h,hmax));
        }
        public void onLayout(boolean reloaded,int a,int b,int wa,int ha) {
            int y = h/25;
            for(int i=0;i<getChildCount();i++) {
                View child = getChildAt(i);
                child.layout(w/4,y,3*w/4,y+w/4);
                y += child.getMeasuredHeight()+h/25;
            }
        }
    }
}
