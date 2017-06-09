package com.anwesome.ui.rectbutton;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 09/06/17.
 */

public class RectButtonView extends View {
    private int time = 0,w,h;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public RectButtonView(Context context) {
        super(context);
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
        }
        paint.setColor(Color.BLUE);
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
    private class RectBall {
        private float rot = 0,y = 0;
        public void draw(Canvas canvas) {
            float radius = w/15;
            paint.setStyle(Paint.Style.FILL);
            for(int i=0;i<4;i++) {
                canvas.save();
                canvas.translate(w / 2, h/2);
                canvas.rotate((-90+i*90)+45);
                canvas.save();
                canvas.translate(0,y);
                canvas.save();
                canvas.rotate(rot);
                paint.setColor(Color.WHITE);
                paint.setStrokeCap(Paint.Cap.ROUND);
                canvas.drawLine(0,-radius/2,0,radius/2,paint);
                for(int j=0;j<2;j++) {
                    canvas.save();
                    canvas.translate(0,-radius/2);
                    canvas.rotate((1-2*j)*45);
                    canvas.drawLine(0,0,0,radius/8,paint);
                    canvas.restore();
                }
                canvas.restore();
                canvas.drawCircle(0,0,radius,paint);
                canvas.restore();
                canvas.restore();
            }
        }
        public void update(float factor) {
            rot = 180*factor;
            y = (h/3-w/15)*factor;
        }
    }
}
