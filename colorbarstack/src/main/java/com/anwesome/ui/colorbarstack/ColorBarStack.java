package com.anwesome.ui.colorbarstack;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 20/06/17.
 */

public class ColorBarStack {
    public class ColorBarStackView extends View {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private int w,h,time=0;
        private Bitmap bitmap;
        public ColorBarStackView(Context context,Bitmap bitmap,int[] colors) {
            super(context);
            this.bitmap = bitmap;
        }
        public void onDraw(Canvas canvas) {
            if(time == 0) {
                w = canvas.getWidth();
                h = canvas.getHeight();
                bitmap = Bitmap.createScaledBitmap(bitmap,w/2,w/2,true);
            }
            canvas.drawBitmap(bitmap,w/4,h/2-w/4,paint);
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
    }
    private class ColorBar {
        private int color;
        private float dir = 0,wBar = 0,y,w,h;
        public ColorBar(int color,float y,float w,float h) {
            this.color = color;
            this.y = y;
            this.w = w;
            this.h = h;
        }
        public void draw(Canvas canvas,Paint paint) {
            canvas.save();
            canvas.translate(0,y);
            int r = Color.red(color),g = Color.green(color),b = Color.blue(color);
            paint.setColor(Color.argb(150,r,g,b));
            canvas.drawRect(0,0,wBar,h,paint);
            canvas.restore();
        }
        public void update(float factor) {
            wBar = w/2*factor;
        }
    }
}
