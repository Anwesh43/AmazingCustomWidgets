package com.anwesome.ui.progressbutton;

import android.content.Context;
import android.content.pm.ProviderInfo;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 03/06/17.
 */

public class ProgressButtonView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int time = 0,w,h;
    public ProgressButtonView(Context context) {
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
    private class CircularProgressBar {
        private float x,y,r,deg = 0;
        public CircularProgressBar(int i) {
            x = w/4*(i+1);
            y = h/2;
            r = w/10;
        }
        public void draw(Canvas canvas) {
            Path path = new Path();
            for(int i=0;i<=deg;i++) {
                float x = (float)(r*Math.cos(i*Math.PI/180)),y = (float)(r*Math.sin(i*Math.PI/180));
                if(i == 0) {
                    path.moveTo(x,y);
                }
                else {
                    path.lineTo(x,y);
                }
            }
            canvas.drawPath(path,paint);
        }
        public void update(float factor) {
            deg = 360*factor;
        }

    }
    private class LinearBar {
        private float x,y,wx = 0;
        public LinearBar(int i) {
            x = 0;
            y = h/3*(i+1);
        }
        public void update(float factor) {
            wx = w*factor;
        }
        public void draw(Canvas canvas) {
            paint.setStrokeWidth(w/30);
            canvas.drawLine(x,y,wx,y,paint);
        }
    }
}
