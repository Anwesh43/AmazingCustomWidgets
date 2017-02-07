package com.anwesome.ui.crukybutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;

import com.anwesome.ui.dimensionsutil.DimensionsUtil;

/**
 * Created by anweshmishra on 08/02/17.
 */
public class CrukyButton {
    private Activity mActivity;
    private int w,h;
    private int color = Color.parseColor("#43A047");
    private View.OnClickListener onClickListener;
    public CrukyButton(Activity activity) {
        this.mActivity = activity;
        Point size = DimensionsUtil.getDeviceDimension(mActivity);
        if(size!=null) {
            w = size.x;
            h = size.y;
        }
    }
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
    public void show() {

    }
    private class CrukyButtonView extends View {
        private int time = 0;
        public CrukyButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {

        }
        public boolean onTouchEvent(MotionEvent event) {
            return true;
        }
    }
}
