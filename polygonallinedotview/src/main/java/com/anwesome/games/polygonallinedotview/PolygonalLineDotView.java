package com.anwesome.games.polygonallinedotview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by anweshmishra on 09/07/17.
 */

public class PolygonalLineDotView extends View{
    private int n = 3,time = 0,w,h,size,deg;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private ConcurrentLinkedQueue<PolygonalLineDot> lineDots = new ConcurrentLinkedQueue<>();
    public PolygonalLineDotView(Context context,int n) {
        super(context);
        this.n = Math.max(n,this.n);
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            size = Math.min(w,h)/2;
            deg = 360/n;
            for(int i=0;i<n;i++) {
                lineDots.add(new PolygonalLineDot(i));
            }
        }
        paint.setColor(Color.CYAN);
        paint.setStrokeWidth(w/120);
        canvas.save();
        canvas.translate(w/2,h/2);
        for(PolygonalLineDot lineDot:lineDots) {
            lineDot.draw(canvas);
        }
        canvas.restore();
        time++;
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {

        }
        return true;
    }
    private class PolygonalLineDot {
        private int index,dir=0;
        private float x,y,scale = 0;
        public PolygonalLineDot(int index) {
            this.index = index;
            this.x = (float)((size/2)*Math.cos(index*deg*Math.PI/180));
            this.y = (float)((size/2)*Math.sin(index*deg*Math.PI/180));
        }
        public void draw(Canvas canvas) {
            canvas.save();
            canvas.translate(x,y);
            canvas.drawLine(0,0,x*(1-scale),y*(1-scale),paint);
            canvas.drawArc(new RectF(-size/8,-size/8,size/8,size/8),0,360*scale,true,paint);
            canvas.restore();
        }
        public boolean stopped() {
            return dir == 0;
        }
        public void update() {
            scale+=0.2f*dir;
            if(scale > 1) {
                scale = 1;
                dir = 0;
            }
            if(scale < 0) {
                scale = 0;
                dir = 0;
            }
        }
        public int hashCode() {
            return index;
        }
        public boolean handleTap(float x,float y) {
            boolean condition =  x>=this.x-size/8 && x<=this.x+size/8 && y>=this.y-size/8 && y<=this.y+size/8 && dir == 0;
            if(condition) {
                dir = scale == 0?1:-1;
            }
            return condition;
        }
    }
    private class AnimationHandler {
        private ConcurrentLinkedQueue<PolygonalLineDot> tappedLineDots = new ConcurrentLinkedQueue<>();
        private boolean animated = false;
        public void update() {
            if(animated) {
                for(PolygonalLineDot polygonalLineDot:tappedLineDots) {
                    polygonalLineDot.update();
                    if(polygonalLineDot.stopped()) {
                        tappedLineDots.remove(polygonalLineDot);
                        if(tappedLineDots.size() == 0) {
                            animated = false;
                        }
                    }
                }
                try {
                    Thread.sleep(75);
                    invalidate();
                }
                catch (Exception ex) {

                }
            }
        }
        public void handleTap(float x,float y) {

        }
    }
}
