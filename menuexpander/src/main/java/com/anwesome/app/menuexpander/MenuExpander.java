package com.anwesome.app.menuexpander;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 13/02/17.
 */
public class MenuExpander {
    private Activity activity;
    public MenuExpander(Activity activity) {
        this.activity = activity;
    }
    public void show() {

    }
    private class MenuExpanderView extends View {
        private float scale = 0.2f,deg = 0;
        private boolean expanded = false,isAnimated = false;
        private float boundW,boundH,x,y;
        private int time = 0;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public MenuExpanderView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            int w = canvas.getWidth(),h = canvas.getHeight();
            if(time == 0) {
                boundW = 0.2f* w;
                boundH = 0.2f*h;
                x = w/2 -boundW;
                y = h/2-boundH;
            }
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.parseColor("#BB000000"));

            int r = Math.max(w,h)/6;
            canvas.save();
            canvas.translate(w/2,h/2);
            canvas.rotate(deg);
            canvas.scale(scale,scale);
            canvas.drawRoundRect(new RectF(-w/2,-h/2,w/2,h/2),r,r,paint);
            canvas.restore();
            time++;
            if(isAnimated) {
                try {
                    Thread.sleep(50);
                }catch (Exception ex) {

                }
            }

        }
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX(),y = event.getY();
            if(event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated) {

            }
            return true;
        }
    }
}
