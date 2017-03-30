package com.anwesome.ui.androidbuttonloader;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 30/03/17.
 */
public class LoaderView extends View {
    private int time = 0;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private boolean isAnimated = false;
    private AndroidButton androidButton;
    public LoaderView(Context context) {
        super(context);
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            int w = canvas.getWidth(),h = canvas.getHeight();
            androidButton = new AndroidButton(w/2,h/2,w/6);
        }
        androidButton.draw(canvas,paint);
        if(isAnimated) {
            androidButton.update();
            try {
                Thread.sleep(50);
                invalidate();
            }
            catch (Exception ex) {

            }
        }
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated && androidButton!=null && androidButton.handleTap(event.getX(),event.getY())) {
            isAnimated = true;
            postInvalidate();
        }
        return true;
    }
}
