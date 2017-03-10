package com.anwesome.games.spinnybutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 11/03/17.
 */
public class SpinnyButton {
    private Activity activity;

    private SpinnyButton(Activity activity) {
        this.activity = activity;
    }
    public static SpinnyButton getInstance(Activity activity) {
        return new SpinnyButton(activity);
    }
    public void show() {

    }
    private class SpinnyButtonView extends View {
        private boolean isAnimated = false;
        private Spinny spinny;
        private int time = 0;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public SpinnyButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            if(time == 0) {
                spinny = Spinny.getInstance(canvas.getWidth(),canvas.getHeight());
            }
            spinny.draw(canvas,paint);
            time++;
            if(isAnimated) {
                spinny.update();
                if(spinny.stopped()) {
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
            if(event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated && spinny!=null) {
                spinny.startMoving();
                isAnimated = true;
                postInvalidate();
            }
            return true;
        }
    }
}
