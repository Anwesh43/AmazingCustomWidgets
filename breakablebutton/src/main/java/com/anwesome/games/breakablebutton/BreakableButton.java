package com.anwesome.games.breakablebutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.anwesome.ui.dimensionsutil.DimensionsUtil;

/**
 * Created by anweshmishra on 23/03/17.
 */
public class BreakableButton  {
    private Activity activity;
    private String text;
    private BreakableButtonView breakableButtonView;
    private BreakableButtonController breakableButtonController;
    public BreakableButton(Activity activity,String text) {
        this.activity = activity;
        this.text = text;
    }
    public void show(int x,int y) {
        if(breakableButtonView == null) {
            breakableButtonView = new BreakableButtonView(activity);
            Point size = DimensionsUtil.getDeviceDimension(activity);
            int w = size.x,h = size.y;
            breakableButtonController = new BreakableButtonController(text,w/2);
            activity.addContentView(breakableButtonView,new ViewGroup.LayoutParams(w/2,w/2));
        }
        breakableButtonView.setX(x);
        breakableButtonView.setY(y);
    }
    private class BreakableButtonView extends View {
        private boolean isAnimated = false;
        public BreakableButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            breakableButtonController.draw(canvas);
            if(isAnimated) {
                breakableButtonController.update();
                if(breakableButtonController.stopped()) {
                    isAnimated = false;
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
            if(event.getAction()==MotionEvent.ACTION_DOWN && !isAnimated) {
                breakableButtonController.startMoving();
                isAnimated = true;
                postInvalidate();
            }
            return true;
        }
    }
}
