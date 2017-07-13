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
            private float deg = 0;
            private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            public void draw(Canvas canvas) {
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
            public void update(float scale) {
                deg = 360*scale;
            }
        }
    }

}
