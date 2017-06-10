package com.anwesome.ui.plusbuttonrect;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 11/06/17.
 */

public class PlusButtonRectView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int time = 0,w,h;
    public PlusButtonRectView(Context context) {
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
        if(event.getAction() == MotionEvent.ACTION_DOWN) {

        }
        return true;
    }
    public void update(float factor) {
        postInvalidate();
    }
    private class PlusButton {
        private float rot = 0;
        public void draw(Canvas canvas) {
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.FILL);
            canvas.save();
            canvas.translate(w/2,h/2);
            canvas.rotate(rot);
            canvas.drawCircle(0,0,w/20,paint);
            paint.setColor(Color.WHITE);
            paint.setStrokeWidth(w/60);
            for(int i=0;i<2;i++) {
                canvas.save();
                canvas.rotate(i*90);
                canvas.drawLine(-w/30,0,w/30,0,paint);
                canvas.restore();
            }
            canvas.restore();
        }
        public void update(float factor) {
            rot = 45*factor;
        }
    }
}
