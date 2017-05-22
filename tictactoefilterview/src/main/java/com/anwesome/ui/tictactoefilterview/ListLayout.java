package com.anwesome.ui.tictactoefilterview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by anweshmishra on 22/05/17.
 */
public class ListLayout extends ViewGroup{
    private int w,h,viewSize;
    public ListLayout(Context context) {
        super(context);
        initDimension(context);
    }
    public void initDimension(Context context) {
        DisplayManager displayManager = (DisplayManager) context.getSystemService(Context.DISPLAY_SERVICE);
        Display display = displayManager.getDisplay(0);
        if(display != null) {
            Point size = new Point();
            display.getRealSize(size);
            w = size.x;
            h = size.y;
            viewSize = w/7;
        }
    }
    public void addImage(Bitmap bitmap,OnSelectionChangeListener onSelectionChangeListener) {
        TTIFView ttifView = new TTIFView(getContext(),bitmap,getChildCount());
        ttifView.setOnSelectionChangeListener(onSelectionChangeListener);
        addView(ttifView,new LayoutParams(viewSize,viewSize));
        requestLayout();
    }
    public void onMeasure(int wspec,int hspec) {
        int hMax = h/20;
        for(int i=0;i<getChildCount();i++) {
            View child = getChildAt(i);
            measureChild(child,wspec,hspec);
            if(i%3 == 2) {
                hMax += 2*viewSize;
            }
        }
        if(getChildCount()%3 != 0) {
            hMax += 2*viewSize;
        }
        else {
            hMax += viewSize;
        }
        setMeasuredDimension(w,Math.max(hMax,h));
    }
    public void onLayout(boolean reloaded,int a,int b,int w,int h) {
        int x = viewSize,y = h/20;
        for(int i=0;i<getChildCount();i++) {
            View child = getChildAt(i);
            child.layout(x,y,x+viewSize,y+viewSize);
            if(i%3 == 2) {
                y+=2*viewSize;
                x = viewSize;
            }
            else {
                x += 2*viewSize;
            }
        }
    }
}
