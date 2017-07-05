package com.anwesome.games.midcornerimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 06/07/17.
 */

public class MidCornerImageView extends View {
    private int time = 0,w,h,size;
    private Bitmap bitmap;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public MidCornerImageView(Context context,Bitmap bitmap) {
        super(context);
        this.bitmap = bitmap;
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            size = Math.min(w,h)/2;
            bitmap = Bitmap.createScaledBitmap(bitmap,size,size,true);
        }
        canvas.save();
        canvas.translate(w/2,h/2);
        canvas.drawBitmap(bitmap,-size/2,-size/2,paint)
        canvas.restore();
        time++;
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            
        }
        return true;
    }
}
