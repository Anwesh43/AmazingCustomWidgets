package com.anwesome.ui.circmover;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by anweshmishra on 14/06/17.
 */

public class CircMoverView extends View {
    private int n=3,time = 0,w,h;
    private ConcurrentLinkedQueue<CircMover> circMovers = new ConcurrentLinkedQueue<>();
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public CircMoverView(Context context,int n) {
        super(context);
        this.n = Math.max(n,this.n);
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            int gap = h/(n+1),y = gap;
            for(int i=0;i<n;i++) {
                circMovers.add(new CircMover(y,gap));
                y+=gap;
            }
        }
        for(CircMover circMover:circMovers) {
            circMover.draw(canvas);
        }
        time++;
    }
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
    private class CircMover {
        private float x,y,h,dir=0;
        public CircMover(float y,float size) {
            this.y = y;
            this.x = 0;
            this.h = size;
        }
        public void draw(Canvas canvas) {
            paint.setStrokeWidth(h/15);
            canvas.save();
            canvas.translate(0,y);
            canvas.drawLine(0,0,w,0,paint);
            canvas.drawCircle(x+h/10,0,h/10,paint);
            canvas.restore();
        }
        public boolean handleTap(float y) {
            return y>=this.y-h/2 && y<=this.y+h/2 && dir == 0;
        }
        public void setDir() {
            if(y <= x+h/10) {
                dir = 1;
            }
            if(x >= 9*h/10) {
                dir = -1;
            }
        }
        public int hashCode() {
            return (int)(y+x+dir);
        }
    }
}
