package com.anwesome.ui.cornercenterball;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 13/06/17.
 */

public class CornerCenterBallView extends View {
    private int time = 0,w,h;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public CornerCenterBallView(Context context) {
        super(context);
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
        }
        time++;
    }
    public void update(float factor) {
        postInvalidate();
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {

        }
        return true;
    }
    private class CornerCenterBall {
        private int index = 0;
        private float r = 0;
        public void draw(Canvas canvas) {
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.BLUE);
            canvas.drawRect(new RectF(-w/3,-h/3,w/3,h/3),paint);
            for(int i=0;i<4;i++) {
                canvas.save();
                canvas.rotate(i*90-45);
                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(Color.BLUE);
                canvas.drawCircle(0,-h/3,w/15,paint);
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(Color.WHITE);
                canvas.drawCircle(0,-h/3,w/15,paint);
                canvas.restore();
            }
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.BLUE);
            canvas.save();
            canvas.translate(w/2,h/2);
            canvas.rotate(index*90-45);
            canvas.drawCircle(0,r,w/15,paint);
            canvas.restore();
        }
        public void update(float factor) {
            r = -h/3*factor;
        }
        public void incrementIndex() {
            index++;
            if(index%4 == 0) {
                index = 0;
            }
        }
    }
}
