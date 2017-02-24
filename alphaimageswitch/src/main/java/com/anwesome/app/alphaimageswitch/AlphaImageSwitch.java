package com.anwesome.app.alphaimageswitch;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.view.MotionEvent;
import android.view.View;

import java.util.*;

/**
 * Created by anweshmishra on 24/02/17.
 */
public class AlphaImageSwitch {
    private Activity activity;
    private List<AlphaImageSwitchButton> buttons = new ArrayList<>();
    private AlphaImageSwitchView imageSwitchView;
    public AlphaImageSwitch(Activity activity) {
        this.activity = activity;
    }
    public void addImageSwitchButton(AlphaImageSwitchButton imageSwitchButton) {
        this.buttons.add(imageSwitchButton);
    }
    public void show(int y) {
        if(imageSwitchView != null) {
            imageSwitchView = new AlphaImageSwitchView(activity);
        }
    }
    private class AlphaImageSwitchView extends View {
        private boolean isAnimated = false;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private AlphaImageSwitchButton currButton=null,prevButton = null;
        public AlphaImageSwitchView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            for(AlphaImageSwitchButton imageSwitchButton:buttons) {
                imageSwitchButton.draw(canvas,paint);
            }
            if(isAnimated) {
                if(currButton!=null) {
                    currButton.update();
                }
                if(prevButton!=null) {
                    prevButton.update();
                }
                boolean currButtonStopped = (currButton!=null && currButton.isStopped());
                if((prevButton == null && currButtonStopped) || (prevButton!=null && prevButton.isStopped() && currButtonStopped)) {
                    prevButton.unselect();
                    prevButton = currButton;
                    currButton = null;
                }
                try {
                    Thread.sleep(50);
                    invalidate();
                }
                catch(Exception ex) {

                }
            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX(),y = event.getY();
            if(event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated && currButton == null) {
                for(AlphaImageSwitchButton imageSwitchButton:buttons) {
                    if(imageSwitchButton.handleTap(x,y)) {
                        currButton = imageSwitchButton;
                        break;
                    }
                }
                if(currButton!=null) {
                    currButton.startActivating();
                    if(prevButton!=null) {
                        prevButton.startDeactivating();
                    }
                }
            }
            return true;
        }

    }
}
