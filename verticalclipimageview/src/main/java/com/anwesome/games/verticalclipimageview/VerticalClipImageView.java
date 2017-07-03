package com.anwesome.games.verticalclipimageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 03/07/17.
 */

public class VerticalClipImageView extends View {
    private int n = 3,time = 0,w,h,wSize,size;
    private Bitmap bitmap;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public VerticalClipImageView(Context context, Bitmap bitmap) {
        super(context);
        this.bitmap = bitmap;
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            size = Math.min(w,h)/2;
            bitmap = Bitmap.createScaledBitmap(bitmap,size,size,true);
            wSize = size/n;
        }
        canvas.save();
        canvas.translate(w/2-size/2,h/2);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        for(int i=0;i<n;i++) {
            float x = i*wSize;
            canvas.drawRect(new RectF(x,-size/2,x+wSize,size/2),paint);
        }
        canvas.restore();
        time++;
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {

        }
        return true;
    }
    public void setN(int n) {
        this.n = Math.max(n,this.n);
    }
}
