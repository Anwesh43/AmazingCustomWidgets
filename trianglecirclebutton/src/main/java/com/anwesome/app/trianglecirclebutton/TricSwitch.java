package com.anwesome.app.trianglecirclebutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anweshmishra on 26/02/17.
 */
public class TricSwitch {
    private Activity activity;
    private List<TriCircButton> buttons = new ArrayList<>();
    public TricSwitch(Activity activity) {
        this.activity = activity;
    }
    public void addTricButton(TriCircButton button) {
        buttons.add(button);
    }
    public void show() {
    }
    private class TricSwitchView extends View {
        private boolean isAnimated = false;
        private TriCircButton currButton = null;
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
                    if(currButton!=null) {
                        isAnimated = true;
                        postInvalidate();
                    }
                }
            }
            return true;
        }
    }
}
