package com.anwesome.ui.gpbutton;

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
 * Created by anweshmishra on 26/04/17.
 */
public class GpButton {
    private Activity activity;
    private GpButtonShape gpButtonShape = new GpButtonShape();
    private AnimationController animationController;
    private GpButtonView gpButtonView;
    public GpButton(Activity activity) {
        this.activity = activity;
    }
    public void setOnSizeChangeListener(OnSizeChangeListener onSizeChangeListener) {
        gpButtonShape.setOnSizeChangeListener(onSizeChangeListener);
    }
    public void show(float...coordinates) {
        if(gpButtonView == null) {
            gpButtonView = new GpButtonView(activity);
            animationController = new AnimationController(gpButtonView,gpButtonShape);
            Point dimension = DimensionsUtil.getDeviceDimension(activity);
            int w = dimension.x;
            activity.addContentView(gpButtonView,new ViewGroup.LayoutParams(w/2,w/2));
            gpButtonView.setX(w/4);
            gpButtonView.setY(w/4);
        }
        if(coordinates.length == 2) {
            gpButtonView.setX(coordinates[0]);
            gpButtonView.setY(coordinates[1]);
        }
    }
    private class GpButtonView extends View {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public GpButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            gpButtonShape.draw(canvas,paint,canvas.getWidth()/2);
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
