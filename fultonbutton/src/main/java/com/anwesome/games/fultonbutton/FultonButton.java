package com.anwesome.games.fultonbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 10/03/17.
 */
public class FultonButton {
    private Activity activity;
    private FultonShape fultonShape;
    private FultonButtonView fultonButtonView;
    private FultonButton(Activity activity) {
        this.activity = activity;
    }
    public void show() {
        if(fultonButtonView == null) {

        }
    }
    private class FultonButtonView extends View {
        private boolean isAnimated = false;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public FultonButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            if(fultonShape!=null) {
                fultonShape.draw(canvas,paint);
                if(isAnimated) {
                    fultonShape.update();
                    if(fultonShape.stopped()) {
                        isAnimated = false;
                    }
                    try {
                        Thread.sleep(100);
                        invalidate();
                    }
                    catch (Exception ex) {

                    }
                }
            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated && fultonShape!=null) {
                fultonShape.handleTap();
            }
            return true;
        }
    }
}
