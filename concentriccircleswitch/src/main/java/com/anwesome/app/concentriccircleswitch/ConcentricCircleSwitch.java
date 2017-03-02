package com.anwesome.app.concentriccircleswitch;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
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
        private boolean isAnimated = false;
        public ConcentricCircleView(Context context) {
            super(context);
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
            time++;
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
            return true;
        }
    }
}
