package com.anwesome.ui.minustopause;

import android.app.Activity;
import android.graphics.Point;
import android.view.ViewGroup;

import com.anwesome.ui.dimensionsutil.DimensionsUtil;

/**
 * Created by anweshmishra on 13/05/17.
 */
public class MinusToPause {
    private Activity activity;
    private MinusToPauseView minusToPauseView;
    public MinusToPause(Activity activity) {
        this.activity = activity;
    }
    public void show(int x,int y) {
        if(minusToPauseView == null) {
            minusToPauseView = new MinusToPauseView(activity);
            Point size = DimensionsUtil.getDeviceDimension(activity);
            int w = size.x;
            activity.addContentView(minusToPauseView,new ViewGroup.LayoutParams(w/2,w/2));
        }
        minusToPauseView.setX(x);
        minusToPauseView.setY(y);
    }
}
