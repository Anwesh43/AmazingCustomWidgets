package com.anwesome.ui.colorbarstack;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
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
}
