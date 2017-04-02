package com.anwesome.ui.sharebutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.anwesome.ui.dimensionsutil.DimensionsUtil;

/**
 * Created by anweshmishra on 03/04/17.
 */
public class ShareButton {
    private Activity activity;
    private ShareShape shareShape;
    private ShareButtonView shareButtonView;
    public ShareButton(Activity activity) {
        this.activity = activity;
    }
    public void show(int x,int y) {
        if(shareButtonView == null) {
            shareButtonView = new ShareButtonView(activity);
            Point dimension = DimensionsUtil.getDeviceDimension(activity);
            int w = dimension.x/2;
            activity.addContentView(shareButtonView,new ViewGroup.LayoutParams(w,w));
            shareShape = new ShareShape(w/4,w/4,w/8);
        }
        shareButtonView.setX(x);
        shareButtonView.setY(y);
    }
    private class ShareButtonView extends View {
        private boolean isAnimated = false;
        public ShareButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {

        }
        public boolean onTouchEvent(MotionEvent event) {
            return true;
        }
    }
}
