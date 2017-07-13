package com.anwesome.games.circularplatecollapser;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 13/07/17.
 */

public class CircularPlateCollapser {
    private static class CircularPlateCollapserView  extends View{
        private int time = 0,w,h;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public CircularPlateCollapserView(Context context) {
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
            if(event.getAction() == MotionEvent.ACTION_DOWN) {

            }
            return true;
        }
        private class CircularPlate {

            public void draw(Canvas canvas,float deg) {
                canvas.save();
                canvas.translate(w/2,w/10+w/4);
                paint.setColor(Color.BLUE);
                paint.setStrokeWidth(w/60);
                paint.setStyle(Paint.Style.STROKE);
                canvas.drawArc(new RectF(-w/4,-w/4,w/4,w/4),0,360,true,paint);
                paint.setStyle(Paint.Style.FILL);
                canvas.drawArc(new RectF(-w/4,-w/4,w/4,w/4),0,deg,true,paint);
                canvas.restore();
            }
        }
        public class Collapser {
            public void draw(Canvas canvas,float deg) {
                canvas.save();
                canvas.translate(w/2,w/10);
                paint.setColor(Color.parseColor("#9E9E9E"));
                canvas.drawCircle(0,0,w/10,paint);
                paint.setColor(Color.BLACK);
                paint.setStrokeWidth(w/50);
                canvas.save();
                canvas.rotate(deg);
                for(int i=0;i<2;i++) {
                    canvas.save();
                    canvas.rotate(i*90);
                    canvas.drawLine(0,-w/15,0,w/15,paint);
                    canvas.restore();
                }
                canvas.restore();
                canvas.restore();
            }
            public boolean handleTap(float x,float y) {
                return x>=w/2-w/10 && x<=w/2+w/10 && y>=0 && y<=w/5;
            }
        }
        private class StateContainer {
            private float scale = 0,dir = 0;
            public void update() {
                scale += dir*0.1f;
                if(scale > 1) {
                    scale = 1;
                    dir = 0;
                }
                if(scale < 0) {
                    scale = 0;
                    dir = 0;
                }
            }
            public boolean stopped() {
                return dir == 0;
            }
            public void startUpdating() {
                dir = scale <= 0?1:-1;
            }
        }
    }
}
