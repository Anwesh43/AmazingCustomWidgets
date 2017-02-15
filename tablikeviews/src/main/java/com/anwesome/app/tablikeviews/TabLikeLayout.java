package com.anwesome.app.tablikeviews;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by anweshmishra on 15/02/17.
 */
public class TabLikeLayout {
    private Activity activity;
    private TabLikeView tabLikeView;
    private int w,h,tabW,tabH;
    public TabLikeLayout(Activity activity) {
        this.activity = activity;
        initWH();
    }
    public void initWH() {
        Point size = new Point();
        w = size.x;
        h = size.y;
    }

    public void show() {
        if(tabLikeView == null) {
            tabLikeView = new TabLikeView(activity);
            activity.addContentView(tabLikeView,new ViewGroup.LayoutParams(tabW,tabH));
        }
    }
    private class TabLikeView extends View {
        public TabLikeView(Context context){
            super(context);
        }
        public void onDraw(Canvas canvas) {

        }
        public boolean onTouchEvent(MotionEvent event) {
            return true;
        }
    }
}
