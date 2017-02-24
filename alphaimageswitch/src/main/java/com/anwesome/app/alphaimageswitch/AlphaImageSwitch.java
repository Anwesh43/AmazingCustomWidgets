package com.anwesome.app.alphaimageswitch;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.anwesome.ui.dimensionsutil.DimensionsUtil;

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
    public void show(int viewY) {
        if(imageSwitchView == null) {
            imageSwitchView = new AlphaImageSwitchView(activity);
            Point dimensions = DimensionsUtil.getDeviceDimension(activity);
            int w = dimensions.x;
            float size = (8*w*1.0f)/((9*buttons.size()+1)),x = (5*size)/8,y = size;
            for(AlphaImageSwitchButton button:buttons) {
                button.setDimension(x,y,size);
                x+=(size*9)/8;
            }
            activity.addContentView(imageSwitchView,new ViewGroup.LayoutParams(w,2*(int)size));
        }
        imageSwitchView.setX(0);
        imageSwitchView.setY(viewY);
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
                    if(prevButton!=null) {
                        prevButton.unselect();
                    }
                    prevButton = currButton;
                    currButton = null;
                    isAnimated = false;
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
                    isAnimated = true;
                    postInvalidate();
                }
            }
            return true;
        }

    }
}
