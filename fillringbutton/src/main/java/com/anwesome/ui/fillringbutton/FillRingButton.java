package com.anwesome.ui.fillringbutton;

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
 * Created by anweshmishra on 23/04/17.
 */
public class FillRingButton {
    private FillRingShape fillRingShape = new FillRingShape();
    private Activity activity;
    private FillRingView fillRingView;
    private AnimationController animationController;
    public FillRingButton(Activity activity) {
        this.activity = activity;
    }
    public void show(int x,int y) {
        if(fillRingView == null) {
            fillRingView = new FillRingView(activity);
            Point dimension = DimensionsUtil.getDeviceDimension(activity);
            int w = dimension.x/2;
            animationController = new AnimationController(fillRingView,fillRingShape);
            activity.addContentView(fillRingView,new ViewGroup.LayoutParams(w/2,w/2));
        }
        fillRingView.setX(x);
        fillRingView.setY(y);
    }
    private class FillRingView extends View {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public FillRingView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            fillRingShape.draw(canvas,paint,canvas.getWidth()/2);
            if(animationController != null) {
                animationController.animate();
            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN && animationController != null) {
                animationController.startAnimating();
            }
            return true;
        }
    }
}
