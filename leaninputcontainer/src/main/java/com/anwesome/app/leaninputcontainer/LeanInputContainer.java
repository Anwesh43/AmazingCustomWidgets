package com.anwesome.app.leaninputcontainer;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.view.*;
import android.view.ViewGroup;

/**
 * Created by anweshmishra on 27/02/17.
 */
public class LeanInputContainer {
    private Activity activity;
    private LeanInputContainerView leanInputContainerView;
    public LeanInputContainer(Activity activity) {
        this.activity = activity;
    }
    public void show() {
        if(leanInputContainerView == null) {
            leanInputContainerView = new LeanInputContainerView(activity);
            this.activity.addContentView(leanInputContainerView,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
    }
    private class LeanInputContainerView extends View {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private LeanKeyboard leanKeyboard;
        private int time = 0;
        private boolean isAnimated = false;
        public LeanInputContainerView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            int w = canvas.getWidth(),h = canvas.getHeight();
            if(time == 0) {
                leanKeyboard =  LeanKeyboard.newInstance(w/8,h/4,3*w/4);
            }
            if(leanKeyboard!=null) {
                leanKeyboard.draw(canvas,paint);
            }
            time++;
            if(isAnimated) {
                leanKeyboard.updatePressedKey();
                if(leanKeyboard.isStop()) {
                    isAnimated = false;
                }
                try {
                    Thread.sleep(50);
                    invalidate();
                }
                catch (Exception ex) {

                }
            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX(),y = event.getY();
            if(event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated && leanKeyboard!=null) {
                if(leanKeyboard.handleTap(x,y)) {
                    isAnimated = true;
                    postInvalidate();
                }
            }
            return true;
        }
    }
}
