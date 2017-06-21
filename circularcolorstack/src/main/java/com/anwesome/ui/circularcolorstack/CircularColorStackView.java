package com.anwesome.ui.circularcolorstack;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 21/06/17.
 */

public class CircularColorStackView extends View {
    private int time = 0,w,h;
    private int colors[];
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public CircularColorStackView(Context context,int colors[]) {
        super(context);
        this.colors = colors;
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
        }
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
    private class ColorStackContainer {
        private int index = 0,dir = 1;
        public ColorStackContainer(int colors[]) {

        }
        public void startUpdating() {

        }
        public void adjustParametersOnStop() {
            index+=dir;
            if(index < 0) {
                index = 0;
                dir = -1;
            }
        }
    }
    private class ColorPie {
        private float deg = 0,maxDeg = 0,rot;
        private int color;
        public ColorPie(int color,float maxDeg,float rot) {
            this.maxDeg = maxDeg;
            this.color = color;
            this.rot = rot;
        }
        public void update(float factor) {
            deg = maxDeg*factor;
        }
        public void draw(Canvas canvas) {
            paint.setColor(color);
            canvas.save();
            canvas.translate(w/2,h/2);
            canvas.rotate(rot);
            canvas.drawArc(new RectF(-w/3,-w/3,w/3,w/3),0,deg,true,paint);
            canvas.restore();
        }
        public int hashCode() {
            return (int)(deg+rot)+color;
        }
    }
}
