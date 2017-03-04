package com.anwesome.games.clockswitch;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.view.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anweshmishra on 05/03/17.
 */
public class ClockSwitch {
    private Activity activity;
    private ClockSwitchView view;
    private List<ClockSwitchButton> buttons = new ArrayList<>();
    public ClockSwitch(Activity activity) {
        this.activity = activity;
    }
    public void addButton() {
        buttons.add(ClockSwitchButton.newInstance());
    }
    public void show() {
        if(view == null) {
            view = new ClockSwitchView(activity);
            activity.addContentView(view,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
    }
    private class ClockSwitchView extends View{
        private boolean isAnimated = false;
        private int time = 0;
        private ClockSwitchButton currBtn,prevBtn;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public ClockSwitchView(Context context) {
            super(context);
        }
        private void initDimensions(int w,int h) {
            float gap = (2*w)/(3*buttons.size()+2),x = gap,y = h/2,r = gap/2;
            for(ClockSwitchButton button:buttons) {
                button.setDimensions(x,y,r);
                x+=(3*gap)/2;
            }
        }
        public void stopAnimating() {
            isAnimated = false;
            prevBtn = currBtn;
            currBtn = null;
            OnButtonSelected onButtonSelected = currBtn.getOnButtonSelected();
            if(onButtonSelected!=null) {
                onButtonSelected.onSelected();
            }
        }
        public void onDraw(Canvas canvas) {
            if(time == 0) {
                initDimensions(canvas.getWidth(),canvas.getHeight());
            }
            for(ClockSwitchButton button:buttons) {
                button.draw(canvas,paint);
            }
            time++;
            if(isAnimated) {
                if(currBtn!=null) {
                    currBtn.update();
                    if(prevBtn!=null) {
                        prevBtn.update();
                        if(prevBtn.stopped() && currBtn.stopped()) {
                            stopAnimating();
                        }
                    }
                    else if(currBtn.stopped()) {
                        stopAnimating();
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
            if(event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated) {
                ClockSwitchButton tappedBtn = null;
                for(ClockSwitchButton btn:buttons) {
                    if(btn.handleTap(x,y)) {
                        tappedBtn = btn;
                    }
                }
                if(tappedBtn!=null && tappedBtn!=prevBtn) {
                    currBtn = tappedBtn;
                    currBtn.select();
                    if(prevBtn!=null) {
                        prevBtn.unselect();
                    }
                    isAnimated = true;
                    postInvalidate();
                }
            }
            return true;
        }
    }
}
