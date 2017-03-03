package com.anwesome.games.widholder;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.view.*;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by anweshmishra on 04/03/17.
 */
public class WidHolder {
    private Activity activity;
    private WidHolderView view;
    private ConcurrentLinkedQueue<WidButton> widButtons = new ConcurrentLinkedQueue<>();
    public WidHolder(Activity activity) {
        this.activity = activity;
    }
    public void addWidButton(WidButton widButton) {
        widButtons.add(widButton);
    }
    public void show() {
        if(view == null) {
            view = new WidHolderView(activity);
            activity.addContentView(view,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
    }
    public WidButton getWidButtonAt(int index) {
        WidButton widButton = null;
        int i = 0;
        for(WidButton btn:widButtons) {
            if(i == index) {
                widButton = btn;
                break;
            }
            i++;
        }
        return widButton;
    }
    private class WidHolderView extends View {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private boolean isAnimated = false;
        private int time = 0,currentIndex = 0;
        public WidHolderView(Context context) {
            super(context);
        }
        public void initDimensions(int w,int h) {
            float gap = (2*w)/(3*widButtons.size()+2),x = gap,y = (7*h)/10;
            for(WidButton widButton:widButtons) {
                widButton.setDimension(x,y,gap);
                x+=(3*gap)/2;
            }
        }
        public void onDraw(Canvas canvas) {
            if(time == 0) {
                initDimensions(canvas.getWidth(),canvas.getHeight());
            }
            for(WidButton widButton:widButtons) {
                widButton.draw(canvas,paint);
            }
            time++;
            if(isAnimated) {
                if(currentIndex>=0 && currentIndex<widButtons.size()) {
                    WidButton widButton = getWidButtonAt(currentIndex);
                    widButton.update();
                    if(widButton.isStop() && currentIndex<= widButtons.size()-1) {
                        currentIndex++;
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
            if(event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated) {
                isAnimated = true;
                postInvalidate();
            }
            return true;
        }
    }
}
