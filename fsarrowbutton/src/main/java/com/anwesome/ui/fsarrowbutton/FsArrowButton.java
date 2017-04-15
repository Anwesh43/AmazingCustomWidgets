package com.anwesome.ui.fsarrowbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.anwesome.ui.dimensionsutil.DimensionsUtil;
import com.anwesome.ui.fsarrowbutton.controllers.AnimationController;

/**
 * Created by anweshmishra on 15/04/17.
 */
public class FsArrowButton {
    private Activity activity;
    private FsArrowButtonShape fsArrowButtonShape;
    private FsArrowButtonView view;
    private AnimationController animationController;
    private FsArrowButtonShape.OnExpandListener onExpandListener;
    public FsArrowButton(Activity activity) {
        this.activity = activity;
    }
    public void setOnExpandListener(FsArrowButtonShape.OnExpandListener onExpandListener) {
        this.onExpandListener = onExpandListener;
        if(fsArrowButtonShape != null) {
            fsArrowButtonShape.setOnExpandListener(onExpandListener);
        }
    }
    public void show() {
        if(view == null) {
            view = new FsArrowButtonView(activity);
            Point dimension = DimensionsUtil.getDeviceDimension(activity);
            int w = dimension.x;
            activity.addContentView(view,new ViewGroup.LayoutParams(w/2,w/2));
            fsArrowButtonShape = new FsArrowButtonShape(w/4);
            animationController = new AnimationController(view,fsArrowButtonShape);
            if(onExpandListener != null) {
                fsArrowButtonShape.setOnExpandListener(onExpandListener);
            }
        }
    }
    private class FsArrowButtonView extends View {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public FsArrowButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            fsArrowButtonShape.draw(canvas,paint);
            animationController.animate();
        }
        public boolean onTouchEvent(MotionEvent event) {
            animationController.handleTap(event);
            return true;
        }
    }
}
