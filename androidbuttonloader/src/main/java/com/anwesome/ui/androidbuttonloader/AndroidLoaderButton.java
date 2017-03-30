package com.anwesome.ui.androidbuttonloader;

import android.app.Activity;
import android.graphics.Point;
import android.view.View;
import android.view.ViewGroup;

import com.anwesome.ui.dimensionsutil.DimensionsUtil;

/**
 * Created by anweshmishra on 30/03/17.
 */
public class AndroidLoaderButton {
    private LoaderView loaderView;
    private OverlayView overlayView;
    private Activity activity;
    public AndroidLoaderButton(Activity activity) {
        this.activity = activity;
    }
    public void show() {
        if(loaderView == null && overlayView == null) {
            loaderView = new LoaderView(activity);
            overlayView = new OverlayView(activity);
            activity.addContentView(overlayView,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            Point dimension = DimensionsUtil.getDeviceDimension(activity);
            activity.addContentView(loaderView,new ViewGroup.LayoutParams(dimension.x,dimension.y/2));
            loaderView.setY(dimension.y/6);
        }
        else {
            loaderView.setVisibility(View.VISIBLE);
            overlayView.setVisibility(View.VISIBLE);
        }
    }
    public void dismiss() {
        if(loaderView!=null && overlayView!=null) {
            loaderView.setVisibility(View.INVISIBLE);
            overlayView.setVisibility(View.INVISIBLE);
        }
    }
}
