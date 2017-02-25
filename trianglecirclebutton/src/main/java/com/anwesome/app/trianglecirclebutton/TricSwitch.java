package com.anwesome.app.trianglecirclebutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.anwesome.ui.dimensionsutil.DimensionsUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anweshmishra on 26/02/17.
 */
public class TricSwitch {
    private Activity activity;
    private List<TriCircButton> buttons = new ArrayList<>();
    private TricSwitchView tricSwitchView = null;
    public TricSwitch(Activity activity) {
        this.activity = activity;
    }
    public void addTricButton(TriCircButton button) {
        buttons.add(button);
    }
    public void show(int vertY) {
        if(tricSwitchView == null) {
            Point size = DimensionsUtil.getDeviceDimension(activity);
            int w = size.x,h = size.y;
            float gap = (w*1.0f)/((2*buttons.size()));
            float x = gap,y = 2*gap;
            for(TriCircButton button:buttons) {
                button.setDimensions(x,y,gap);
                x+=(gap*2);
            }
            tricSwitchView = new TricSwitchView(activity);
            activity.addContentView(tricSwitchView,new ViewGroup.LayoutParams(w,3*(int)gap));
        }
        tricSwitchView.setY(vertY);
    }
    private class TricSwitchView extends View {
        private boolean isAnimated = false;
        private TriCircButton currButton = null,prevButton = null;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public TricSwitchView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            for(TriCircButton triCircButton:buttons) {
                triCircButton.draw(canvas,paint);
            }
            if(isAnimated) {
                if(currButton!=null) {
                    currButton.update();
                    if (currButton.stopped()) {
                        isAnimated = false;
                        prevButton = currButton;
                        currButton = null;

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
            if(event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated && currButton == null) {
                for(TriCircButton triCircButton:buttons) {
                    if(triCircButton.handleTap(event.getX(),event.getY())) {
                        currButton = triCircButton;
                        break;
                    }
                }
                if(currButton!=null) {
                    if(prevButton!=null) {
                        prevButton.unselect();
                    }
                    isAnimated = true;
                    postInvalidate();
                }
            }
            return true;
        }
    }
}
