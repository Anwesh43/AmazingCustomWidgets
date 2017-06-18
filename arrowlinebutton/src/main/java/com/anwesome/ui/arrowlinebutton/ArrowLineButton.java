package com.anwesome.ui.arrowlinebutton;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 19/06/17.
 */

public class ArrowLineButton {
    public static class ArrowLineButtonView extends View {
        private int time = 0,w,h;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public ArrowLineButtonView(Context context) {
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
    }
    public static class ArrowLine {
        private float lx = 0;
        public void draw(Canvas canvas,Paint paint,float x,float y,float size) {
            for(int i=0;i<4;i++) {
                canvas.save();
                canvas.translate(x, y);
                canvas.rotate(i*90);
                Path path = new Path();
                path.moveTo(0,0);
                path.lineTo(lx,-size/3);
                path.lineTo(0,-size/3);
                canvas.drawPath(path,paint);
                canvas.restore();
            }
        }
        public void update(float maxLx,float factor) {
            lx = maxLx * factor;
        }
    }
}
