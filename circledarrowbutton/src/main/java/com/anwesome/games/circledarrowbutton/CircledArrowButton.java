package com.anwesome.games.circledarrowbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.anwesome.ui.dimensionsutil.DimensionsUtil;

/**
 * Created by anweshmishra on 08/03/17.
 */
public class CircledArrowButton {
    private Activity activity;
    private CircledArrowView circledArrowView;
    private CircledArrow circledArrow;
    public CircledArrowButton(Activity activity) {
        this.activity = activity;
    }
    public void show(int x,int y) {
        if(circledArrowView == null) {
            Point size = DimensionsUtil.getDeviceDimension(activity);
            int w = size.x;
            circledArrowView = new CircledArrowView(activity);
            circledArrow = new CircledArrow(w/4);
            activity.addContentView(circledArrowView,new ViewGroup.LayoutParams(w/4,w/4));
        }
        circledArrowView.setX(x);
        circledArrowView.setY(y);
    }
    private class CircledArrowView extends View {
        private boolean isAnimated = false;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public CircledArrowView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            if(circledArrow!=null) {
                circledArrow.draw(canvas,paint);
            }
            if(isAnimated) {
                try {
                    Thread.sleep(50);
                    invalidate();
                }
                catch (Exception ex) {

                }
            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated) {
                circledArrow.startAnimating();
            }
            return true;
        }
    }
}
