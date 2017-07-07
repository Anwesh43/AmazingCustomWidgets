package com.anwesome.games.linedotgraphview;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 07/07/17.
 */

public class LineDotGraphView extends View {
    private int data[],time = 0,w,h,size,px,py;
    public LineDotGraphView(Context context,int[] data) {
        super(context);
        this.data = data;
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            size = w/(2*data.length+1);
            px = w/8;
            py = h/8;
        }
        time++;
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {

        }
        return true;
    }
    private class LineDot {
        private float x,h,dir = 0,scale = 0;
        public LineDot(float x,float h) {
            this.x = x;
            this.h = h;
        }
        public void draw(Canvas canvas) {

        }
        public void update() {
            scale += 0.2f*dir;
            if(scale > 1) {
                dir = 0;
                scale = 0;
            }
            if(scale < 0) {
                dir = 0;
                scale = 0;
            }
        }
        private void startUpdating() {
            dir = scale == 0?1:0;
        }
        public int hashCode() {
            return (int)(x+h+scale+dir);
        }
        public boolean handleTap(float x,float y) {
            boolean condition =  x>=px+this.x-size && x<=px+this.x+size && y>=py+this.h-size && y<=py+this.h+size;
            if(condition) {
                startUpdating();
            }
            return condition;
        }
        public boolean stopUpdating() {
            return dir == 0;
        }
    }
}
