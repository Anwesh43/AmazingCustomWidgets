package com.anwesome.ui.plusminuslineview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 08/06/17.
 */

public class PlusMinusLineView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int time = 0,w,h;
    public PlusMinusLineView(Context context) {
        super(context);
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
        }
        time++;
    }
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
    public void update(float factor) {
        postInvalidate();
    }
    private class PlusMinusButton {
        private float x,y,r,deg = 90;
        public PlusMinusButton() {
            x = w/2;
            y = h/2;
            r = w/10;
        }
        public void draw(Canvas canvas) {

            paint.setColor(Color.BLUE);
            canvas.save();
            canvas.translate(x, y);
            canvas.drawCircle(0,0,r,paint);
            for(int i=0;i<2;i++) {
                canvas.rotate(deg*i);
                paint.setColor(Color.WHITE);
                paint.setStrokeWidth(r/50);
                canvas.drawLine(-r/2,0,r/2,0,paint);
                canvas.restore();
            }
            canvas.restore();
        }
        public void update(float factor) {
            deg = 90*(1-factor);
        }
    }
}

