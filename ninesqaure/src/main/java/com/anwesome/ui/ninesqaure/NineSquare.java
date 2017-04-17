package com.anwesome.ui.ninesqaure;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 17/04/17.
 */
public class NineSquare {
    private Activity activity;
    private AnimationController animationController;
    private NineSquareButton nineSquareButton;
    private NineSquareView nineSquareView;
    public NineSquare(Activity activity) {
        this.activity = activity;
    }
    public void show() {
        if(nineSquareView == null) {
            nineSquareView = new NineSquareView(activity);
        }
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
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                animationController.handleTap();
            }
            return true;
        }
    }
}
