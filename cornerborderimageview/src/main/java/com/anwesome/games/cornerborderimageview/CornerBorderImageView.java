package com.anwesome.games.cornerborderimageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 05/07/17.
 */

public class CornerBorderImageView extends View {
    private Bitmap bitmap;
    private int time = 0,w,h,size;
    private CornerBorder cornerBorder = new CornerBorder();
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public CornerBorderImageView(Context context,Bitmap bitmap) {
        super(context);
        this.bitmap = bitmap;
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            size = 2*Math.min(w,h)/3;
            bitmap = Bitmap.createScaledBitmap(bitmap,size,size,true);
        }
        canvas.save();
        canvas.translate(w/2,h/2);
        canvas.drawBitmap(bitmap,-size/2,-size/2,paint);
        cornerBorder.draw(canvas);
        canvas.restore();
        time++;
    }
    public void update(float factor) {
        cornerBorder.update(factor);
        postInvalidate();
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {

        }
        return true;
    }
    private class CornerBorder {
        private float scale = 0;
        public void draw(Canvas canvas) {
            float x = -size/2,y=-size/2;
            for(int i=0;i<2;i++) {
                canvas.save();
                canvas.rotate(i*180);
                canvas.drawArc(new RectF(x-size/10,y-size/10,x+size/10,y+size/10),0,360*scale,true,paint);
                for(int j=0;j<2;j++) {
                    canvas.save();
                    canvas.translate(x,y);
                    canvas.rotate(j*90);
                    canvas.drawLine(0,0,size,0,paint);
                    canvas.restore();
                }
                canvas.restore();
            }
        }
        public void update(float factor) {
            scale = factor;
        }
    }
}
