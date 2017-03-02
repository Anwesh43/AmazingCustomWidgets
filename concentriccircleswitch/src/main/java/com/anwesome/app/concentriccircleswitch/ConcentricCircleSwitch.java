package com.anwesome.app.concentriccircleswitch;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anweshmishra on 03/03/17.
 */
public class ConcentricCircleSwitch {
    private List<ConcentricCircle> concentricCircles = new ArrayList<>();
    private Activity activity;
    private ConcentricCircleView concentricCircleView = null;
    public ConcentricCircleSwitch(Activity activity) {
        this.activity = activity;
    }
    public void addButton() {
        concentricCircles.add(ConcentricCircle.newInstance());
    }
    public void show() {
        if(concentricCircleView == null) {
            concentricCircleView = new ConcentricCircleView(activity);
            this.activity.setContentView(concentricCircleView);
        }
    }
    private class ConcentricCircleView extends View {
        private int time = 0;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private boolean isAnimated = false;
        private ConcentricCircle prevCircle = null,currCircle=null;
        public ConcentricCircleView(Context context) {
            super(context);
        }
        private void stopAnimation() {
            prevCircle = currCircle;
            currCircle = null;
            isAnimated = false;
        }
        public void onDraw(Canvas canvas) {
            if(time == 0) {
                int  w = canvas.getWidth(),h = canvas.getHeight();
                float gap = w/(3*concentricCircles.size()+2),x = gap,y = h/2;
                for(ConcentricCircle concentricCircle:concentricCircles) {
                    concentricCircle.setDimensions(x,y,gap/2);
                    x+=(3*gap)/2;
                }
            }
            for(ConcentricCircle concentricCircle:concentricCircles) {
                concentricCircle.draw(canvas,paint);
            }
            time++;
            if(isAnimated) {
                if(currCircle!=null) {
                    currCircle.update();
                    if(prevCircle!=null) {
                        prevCircle.update();
                        if(prevCircle.stopped() && currCircle.stopped()) {
                            stopAnimation();
                        }
                    }
                    else {
                        if (currCircle.stopped()) {
                            stopAnimation();
                        }
                    }
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
            if(event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated && currCircle!=null) {
                for(ConcentricCircle concentricCircle:concentricCircles) {
                    if(concentricCircle.handleTap(x,y)) {
                        currCircle = concentricCircle;
                    }
                }
                if(currCircle!=null) {
                    currCircle.startFilling();
                    if(prevCircle!=null) {
                        prevCircle.startRemovingFill();
                    }
                    isAnimated = true;
                    postInvalidate();
                }
            }
            return true;
        }
    }
}
