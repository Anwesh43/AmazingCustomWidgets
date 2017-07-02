package com.anwesome.games.barclipimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 02/07/17.
 */

public class BarClipImageView extends View {
    private Bitmap bitmap;
    private int time = 0,w,h,size,n=3,hSize = 0;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
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
            hSize = size/n;
        }
        time++;
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {

        }
        return true;
    }
    private class BarClipImage {
        private int index,dir = 0;
        private float scale = 0,y=0;
        public BarClipImage(int index) {
            this.index = index;
            y = index*hSize;
        }
        public void draw(Canvas canvas) {
            canvas.save();
            Path path = new Path();
            path.addRect(new RectF(-(size/2)*scale,-hSize/2,(size/2)*scale,hSize/2), Path.Direction.CCW);
            canvas.clipPath(path);
            canvas.drawBitmap(bitmap,-size/2,-size/2,paint);
            canvas.restore();
        }
        public void update() {
            scale += 0.2f*dir;
            if(scale > 1) {
                scale = 1;
                dir = 0;
            }
            if(scale < 0) {
                scale = 0;
                dir = 0;
            }
        }
        public void startUpdating(int dir) {
            this.dir = dir;
        }
        public int hashCode() {
            return index;
        }
    }
}
