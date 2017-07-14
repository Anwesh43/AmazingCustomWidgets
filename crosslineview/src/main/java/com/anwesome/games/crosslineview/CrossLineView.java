package com.anwesome.games.crosslineview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 14/07/17.
 */

public class CrossLineView extends View {
    private int w,h,time = 0;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private CrossLineView(Context context) {
        super(context);
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            paint.setColor(Color.parseColor("#673ab7"));
            paint.setStrokeWidth(5);
        }
        time++;
    }
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
    private class CrossLine {
        public void draw(Canvas canvas,float scale) {
            canvas.save();
            canvas.translate(w/2,h/2);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawArc(new RectF(-w/10,-w/10,w/10,w/10),0,360*scale,true,paint);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(0,0,w/10,paint);
            for(int i=0;i<4;i++) {
                canvas.save();
                canvas.rotate(i*90+45);
                canvas.drawLine(0,0,w/4*scale,0,paint);
                canvas.restore();
            }
            canvas.restore();
        }
        public boolean handleTap(float x,float y) {
            return x>=w/2 -w/10 && x<=w/2+w/10 && y>=h/2-w/10 && y<=h/2+w/10;
        }
    }
    public class StateContainer {
        private float scale = 0,dir = 0;
        public void update() {
            scale+=0.15f*dir;
            if(scale >= 1) {
                dir = 0;
                scale = 1;
            }
            if(scale <= 0) {
                dir = 0;
                scale = 0;
            }
        }
        public void startUpdating() {
            dir = scale <= 0?1:-1;
        }
        public boolean stopped() {
            return dir == 0;
        }
    }
}
