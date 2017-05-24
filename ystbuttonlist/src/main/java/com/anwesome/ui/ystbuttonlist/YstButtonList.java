package com.anwesome.ui.ystbuttonlist;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

/**
 * Created by anweshmishra on 24/05/17.
 */
public class YstButtonList {
    private Activity activity;
    private boolean isShown = false;
    private ScrollView scrollView;
    private ListLayout listLayout;
    public YstButtonList(Activity activity) {
        this.activity = activity;
        listLayout = new ListLayout(activity);
        scrollView = new ScrollView(activity);
        scrollView.addView(listLayout,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }
    public void addButton(OnSelectionChangeListener onSelectionChangeListener) {
        if(!isShown) {
            listLayout.addButton(onSelectionChangeListener);
        }
    }
    public void show() {
        if(!isShown) {
            activity.setContentView(scrollView);
            isShown = true;
        }
    }
    private class ListLayout extends ViewGroup{
        private int w,h;
        public void initDimension(Context context) {
            DisplayManager displayManager = (DisplayManager)context.getSystemService(Context.DISPLAY_SERVICE);
            Display display = displayManager.getDisplay(0);
            Point size = new Point();
            display.getRealSize(size);
            w = size.x;
            h = size.y;
        }
        public ListLayout(Context context) {
            super(context);
            initDimension(context);
        }
        public void addButton(OnSelectionChangeListener onSelectionChangeListener) {
            YSTButtonView ystButtonView = new YSTButtonView(getContext());
            addView(ystButtonView,new LayoutParams(w/2,w/2));
            ystButtonView.setOnSelectionChangeListener(onSelectionChangeListener);
            requestLayout();
        }
        public void onMeasure(int wspec,int hspec) {
            int hMax = h/25;
            for(int i=0;i<getChildCount();i++) {
                View child = getChildAt(i);
                measureChild(child,wspec,hspec);
                hMax += w/2 +h/25;
            }
            setMeasuredDimension(w,Math.max(hMax,h));
        }
        public void onLayout(boolean reloaded,int a,int b,int w,int h) {
            int x = w/4,y = h/25;
            for(int i=0;i<getChildCount();i++) {
                View child = getChildAt(i);
                child.layout(x,y,x+w/2,y+w/2);
                y += w/2+h/25;
            }
        }
    }
}
