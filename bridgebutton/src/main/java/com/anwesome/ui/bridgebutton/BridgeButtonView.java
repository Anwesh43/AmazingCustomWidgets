package com.anwesome.ui.bridgebutton;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 04/06/17.
 */

public class BridgeButtonView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int time = 0,w,h;
    private int color = Color.parseColor("#3F51B5");
    public BridgeButtonView(Context context) {
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
        return true;
    }
    private class BridgePoint {
        private float scale = 0,x,y,r;
        public BridgePoint(int i) {
            x = w/4 + i*w/2;
            y = h/2;
            r = w/12;
        }
        public void draw(Canvas canvas) {
            paint.setColor(Color.GRAY);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(x,y,r,paint);
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(color);
            canvas.save();
            canvas.translate(x,y);
            canvas.scale(scale,scale);
            canvas.drawCircle(0,0,r,paint);
            canvas.restore();
        }
        public boolean handleTap(float x,float y) {
            return x>=this.x -r && x<=this.x+r && y>=this.y -r && y<=this.y +r;
        }
        public void update(float factor) {
            scale = factor;
        }
        public int hashCode() {
            return (int)(x+scale);
        }
    }
    private class BridgeLine {
        private float x,y,deg = 45;
        public BridgeLine() {
            x = w/4;
            y = h/2;
        }
        public void draw(Canvas canvas) {
            paint.setColor(color);
            canvas.save();
            canvas.translate(x,y);
            canvas.rotate(deg);
            canvas.drawLine(0,0,w/2,0,paint);
            canvas.restore();
        }
        public void update(float factor) {
            deg = 45*(1-factor);
        }
    }
}
