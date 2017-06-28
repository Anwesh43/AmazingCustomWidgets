package com.anwesome.games.circfourbitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 28/06/17.
 */

public class CircFourBitmapView extends View{
    private int time = 0,w,h,r;
    private Bitmap bitmap;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public CircFourBitmapView(Context context, Bitmap bitmap) {
        super(context);
        this.bitmap = bitmap;
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            int size = 2*Math.min(w,h)/3;
            r = size/2;
            bitmap = Bitmap.createScaledBitmap(bitmap,size,size,true);
        }
        canvas.save();
        canvas.translate(w/2,h/2);
        Path path = new Path();
        path.addCircle(0,0,r, Path.Direction.CCW);
        canvas.clipPath(path);
        canvas.drawBitmap(bitmap,-r,-r,paint);
        canvas.restore();
        time++;
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {

        }
        return true;
    }
    private class CircFour {
        private float deg = 0;
        public void draw(Canvas canvas) {
            int scales[][] = {{1,1},{-1,1},{-1,-1},{1,-1}};
            for(int i=0;i<scales.length;i++) {
                canvas.save();
                canvas.translate(w / 2, h / 2);
                canvas.scale(scales[i][0],scales[i][1]);
                canvas.drawArc(new RectF(-r,-r,r,r),0,deg,false,paint);
                canvas.restore();
            }
        }
        public void update(float factor) {
            this.deg = 90*factor;
        }
    }
}
