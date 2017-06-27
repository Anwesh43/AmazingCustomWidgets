package com.anwesome.games.linedegcolorview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 27/06/17.
 */

public class LineDegColorView extends View {
    private int[] colors;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int time=0,w,h,gapDeg=0,r=0;
    public LineDegColorView(Context context,int[] colors) {
        super(context);
        this.colors = colors;
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            if(colors.length > 0) {
                gapDeg = 360/colors.length;
            }
            r = Math.min(w,h)/2;
        }
        time++;
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {

        }
        return true;
    }
    private class LineDeg {
        private int index,dir = 0,deg = 0,color,lx = 0;
        public LineDeg(int index) {
            this.index = index;
            deg = gapDeg*index;
        }
        public void draw(Canvas canvas) {
            canvas.save();
            canvas.translate(w/2,h/2);
            canvas.rotate(deg);
            canvas.drawLine(0,0,lx,0,paint);
            canvas.restore();
        }
        public void update() {
            lx += (r/5)*dir;
            if(lx > r) {
                dir = 0;
                lx = r;
            }
            if(lx < 0) {
                dir = 0;
                lx = 0;
            }
        }
        public boolean stopUpdating() {
            return dir == 0;
        }
        public int hashCode() {
            return index;
        }
        public void startUpdating(int dir) {
            this.dir = dir;
        }
    }
}
