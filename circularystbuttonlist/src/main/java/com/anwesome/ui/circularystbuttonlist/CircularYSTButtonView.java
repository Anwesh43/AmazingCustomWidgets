package com.anwesome.ui.circularystbuttonlist;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 25/05/17.
 */

public class CircularYSTButtonView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int time = 0,w,h;
    public CircularYSTButtonView(Context context) {
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
    private class ClipBoard {
        private float scale = 0,x,y,size;
        public ClipBoard() {
            x = w/2;
            y = h/2;
            size = Math.min(w,h)*0.8f;
        }
        public void draw(Canvas canvas) {
            canvas.save();
            canvas.translate(x,y);
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawRoundRect(new RectF(-size/2,-size/2,size/2,size/2),size/20,size/20,paint);
            int n = 5;
            float circleX = w/10,gap = size/11,y_gap = size/(2*n+1);
            for(int i=0;i<n;i++) {
                canvas.drawCircle(circleX,y_gap,size/30,paint);
                canvas.drawLine(circleX+size/5,y_gap,circleX+size*0.9f,y_gap,paint);
                y_gap += gap;
            }
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.argb(150,0,0,0));
            canvas.drawRoundRect(new RectF(-size/2,-size/2,size/2,size/2),size/20,size/20,paint);
            canvas.restore();
        }
        public void update(float factor) {
            scale = factor;
        }
    }
}
