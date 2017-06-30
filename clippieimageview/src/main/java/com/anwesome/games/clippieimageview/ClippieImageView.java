package com.anwesome.games.clippieimageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 01/07/17.
 */

public class ClippieImageView extends View {
    private Bitmap bitmap;
    private int time = 0,w,h,r;
    public ClippieImageView(Context context,Bitmap bitmap) {
        super(context);
        this.bitmap = bitmap;
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            r = Math.min(w,h)/4;
            bitmap = Bitmap.createScaledBitmap(bitmap,w/2,w/2,true);
        }
        time++;
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            
        }
        return true;
    }
}
