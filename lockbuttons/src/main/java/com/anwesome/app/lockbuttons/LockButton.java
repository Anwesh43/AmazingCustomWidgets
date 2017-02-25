package com.anwesome.app.lockbuttons;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by anweshmishra on 25/02/17.
 */
public class LockButton {
    private Activity activity;
    private LockButtonType lockButtonType;
    private LockButtonView lockButtonView;
    private LockListener lockListener;
    private AnimationController animationController;
    public LockButton(Activity activity,LockButtonType lockButtonType){
        this.activity = activity;
        this.lockButtonType = lockButtonType;
    }
    public void setLockListener(LockListener lockListener) {
        this.lockListener = lockListener;
        if(animationController!=null) {
            animationController.setLockListener(lockListener);
        }
    }
    public void show(int x,int y) {
        if(lockButtonView == null) {
            lockButtonView = new LockButtonView(activity);
            activity.addContentView(lockButtonView,new ViewGroup.LayoutParams(300,300));
        }
        lockButtonView.setX(x);
        lockButtonView.setY(y);
    }
    private class LockButtonView extends View {
        private int time = 0;
        private boolean isAnimated = false;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private Lock lock = null;
        private LockKey lockKey = null;
        private int w,h;
        public LockButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            if(time == 0) {
                initLockAndKey(canvas.getWidth(),canvas.getHeight());
                animationController = new AnimationController(lock,lockKey,this);
                if(lockListener!=null) {
                    animationController.setLockListener(lockListener);
                }
            }
            drawObjects(canvas,paint);
            time++;
            animationController.animate();
        }
        private void drawObjects(Canvas canvas,Paint paint) {
            LockDrawUtil.drawObjects(canvas,paint,lock,lockKey,lockButtonType,w,h);
        }
        private void initLockAndKey(int w,int h) {
            this.w = w;
            this.h = h;
            lockKey = new LockKey(w/2,h/2+w/4);
            float x = w/2-w/4,y = h/2,lockR = w/4;
            lock = new Lock(x,y,lockR);
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN &&  (animationController!=null && !animationController.isAnimated())) {
                animationController.startAnimating();
                animationController.setAnimated(true);
                postInvalidate();
            }
            return true;
        }
    }
}
