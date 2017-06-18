package com.anwesome.ui.sharerotbutton;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 18/06/17.
 */

public class ShareRotButton  {
    private int color = Color.parseColor("#00838F");
    private class ShareRotButtonView extends View {
        public ShareRotButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {

        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN) {

            }
            return true;
        }
        public void update(float factor) {
            postInvalidate();
        }
    }
    private class ShareRotShape {
        private float initDeg = 0,deg = 0;
        public void incrementDeg() {
            this.initDeg += 90;
            this.initDeg %= 90;
        }
        public void draw(Canvas canvas, Paint paint,float x,float y,float size) {
            canvas.save();
            canvas.translate(x,y);
            Path path = new Path();
            path.moveTo(size/3,1);
            drawArrow(path,size/2,-size/5,size/12);
            drawArc(path,size/4,-1);
            paint.setColor(color);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawPath(path,paint);
            canvas.restore();
        }
        private void drawArrow(Path path,float uy,float mx,float ly) {
            path.lineTo(0,uy);
            path.lineTo(mx,0);
            path.lineTo(0,ly);
        }
        private void drawArc(Path path,float r,int dir) {
            int a = 0,b = 90;
            if(dir == -1) {
               a = 90;
                b = 0;
            }
            for(int i=a;;i+=dir) {
                float x = (float)(r*Math.cos(i*Math.PI/180)),y = (float)(r*Math.sin(i*Math.PI/180));
                path.lineTo(x,y);
                if(i == b){
                    break;
                }
            }
        }
        public void update(float factor) {
            deg = initDeg + 90*factor;
        }
    }
}
