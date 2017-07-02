package com.anwesome.games.barclipimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 02/07/17.
 */

public class BarClipImageView extends View {
    private Bitmap bitmap;
    private int time = 0,w,h,size,n=3;
    private BarClipImageView(Context context, Bitmap bitmap,int n) {
        super(context);
        this.bitmap = bitmap;
        this.n = Math.max(n,this.n);
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            size = Math.min(w,h)/2;
            bitmap = Bitmap.createScaledBitmap(bitmap,size,size,true);
        }
        time++;
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {

        }
        return true;
    }
}
