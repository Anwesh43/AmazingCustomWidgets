package com.anwesome.games.basicswitch;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by anweshmishra on 06/03/17.
 */
public class BasicSwitch {
    private Activity activity;
    private ConcurrentLinkedQueue<SwitchObject> switchObjects = new ConcurrentLinkedQueue<>();
    private BasicSwitchView basicSwitchView;
    public BasicSwitch(Activity activity) {
        this.activity = activity;
    }
    public void addSwitchObject(OnSelectedListener onSelectedListener) {
        this.switchObjects.add(getSwitchObject(onSelectedListener));
    }
    public SwitchObject getSwitchObject(OnSelectedListener onSelectedListener) {
        return new SwitchObject(onSelectedListener);
    }
    public void show() {
        if(basicSwitchView == null) {
            basicSwitchView = new BasicSwitchView(activity);
            activity.setContentView(basicSwitchView);
        }
    }
    private class BasicSwitchView extends View{
        private boolean isAnimated = false;
        private int time = 0;
        private SwitchObject currObject=null,prevObject=null;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public BasicSwitchView(Context context) {
            super(context);
        }
        private void stopAnimating() {
            if(currObject.getOnSelectedListener()!=null) {
                currObject.getOnSelectedListener().onSelected();
            }
            prevObject = currObject;
            currObject = null;
            isAnimated = false;
        }
        public void initSwitchObjects(int w,int h) {
            float gap =(2*w)/(3*switchObjects.size()+2) ,y = h/2,x = gap;
            for(SwitchObject switchObject:switchObjects) {
                switchObject.setDimensions(x,y,gap);
                x+=(3*gap)/2;
            }
        }
        public void onDraw(Canvas canvas) {
            if(time == 0){
                initSwitchObjects(canvas.getWidth(),canvas.getHeight());
            }
            for(SwitchObject switchObject:switchObjects) {
                switchObject.draw(canvas,paint);
            }
            time++;
            if(isAnimated) {
                if(currObject!=null) {
                    currObject.update();
                    if(prevObject!=null) {
                        prevObject.update();
                        if(prevObject.stoppedAnimating() && currObject.stoppedAnimating()) {
                            stopAnimating();
                        }
                    }
                    else {
                        if(currObject.stoppedAnimating()) {
                            stopAnimating();
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
            if(event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated && currObject==null) {
                for(SwitchObject switchObject:switchObjects) {
                    if(switchObject.handleTap(event.getX(),event.getY())) {
                        currObject = switchObject;
                        break;
                    }
                }
                if(currObject!=null) {
                    currObject.select();
                    if(prevObject!=null) {
                        prevObject.unselect();
                    }
                    isAnimated = true;
                    postInvalidate();
                }
            }
            return true;
        }

    }
}
