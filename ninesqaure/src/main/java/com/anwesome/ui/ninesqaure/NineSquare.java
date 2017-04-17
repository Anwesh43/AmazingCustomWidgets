package com.anwesome.ui.ninesqaure;

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
 * Created by anweshmishra on 17/04/17.
 */
public class NineSquare {
    private Activity activity;
    private AnimationController animationController;
    private NineSquareButton nineSquareButton;
    private NineSquareView nineSquareView;
    private OnOpenCloseListener onOpenCloseListener;
    public NineSquare(Activity activity) {
        this.activity = activity;
    }
    public void setOnOpenCloseListener(OnOpenCloseListener onOpenCloseListener) {
        this.onOpenCloseListener = onOpenCloseListener;
        if(nineSquareButton!=null) {
            nineSquareButton.setOnOpenCloseListener(onOpenCloseListener);
        }
    }
    public void show(float x,float y) {
        if(nineSquareView == null) {
            nineSquareView = new NineSquareView(activity);
            Point size = DimensionsUtil.getDeviceDimension(activity);
            int w = size.x;
            nineSquareButton = new NineSquareButton(w/4);
            activity.addContentView(nineSquareView,new ViewGroup.LayoutParams(w/2,w/2));
            animationController = new AnimationController(nineSquareView,nineSquareButton);
            if(onOpenCloseListener!=null) {
                nineSquareButton.setOnOpenCloseListener(onOpenCloseListener);
            }
        }
        nineSquareView.setX(x);
        nineSquareView.setY(y);
    }
    private class NineSquareView extends View {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public NineSquareView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            if(nineSquareButton!=null && animationController!=null) {
                nineSquareButton.draw(canvas,paint);
                animationController.animate();
            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN && animationController!=null) {
                animationController.handleTap();
            }
            return true;
        }
    }
}
