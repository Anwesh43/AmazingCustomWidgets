package com.anwesome.app.lockbuttons;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 25/02/17.
 */
public class LockButton {
    private Activity activity;
    private LockButtonType lockButtonType;
    public LockButton(Activity activity,LockButtonType lockButtonType){
        this.activity = activity;
        this.lockButtonType = lockButtonType;
    }
    public void show() {

    }
    private class LockButtonView extends View {
        private int time = 0;
        private boolean isAnimated = false;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private Lock lock = null;
        private LockKey lockKey = null;
        private int w,h;
        private AnimationController animationController;
        public LockButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            if(time == 0) {
                initLockAndKey(canvas.getWidth(),canvas.getHeight());
                animationController = new AnimationController(lock,lockKey,this);
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
            lockKey = new LockKey(3*w/4,h/2);
            float x = 3*w/4+(w/4)*(float)Math.cos(240*Math.PI/180),y = h/2+(w/4)*(float)Math.sin(240*Math.PI/180),lockR = Math.abs(x-3*w/4);
            lock = new Lock(x,y,lockR);
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN &&  (animationController!=null && !animationController.isAnimated())) {
                animationController.startAnimating();
            }
            return true;
        }
    }
}
