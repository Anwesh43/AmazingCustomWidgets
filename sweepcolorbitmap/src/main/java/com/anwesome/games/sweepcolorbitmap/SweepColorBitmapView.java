package com.anwesome.games.sweepcolorbitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 25/06/17.
 */

public class SweepColorBitmapView extends View {
    private int time =0,w,h;
    private Bitmap bitmap;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int colors[];
    private SweepColorBitmapView(Context context,Bitmap bitmap,int colors[]) {
        super(context);
        this.bitmap = bitmap;
        this.colors = colors;
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            bitmap = Bitmap.createScaledBitmap(bitmap,w,h,true);
        }
        paint.setColor(Color.BLACK);
        canvas.drawBitmap(bitmap,0,0,paint);
        time++;
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {

        }
        return true;
    }
    private class SweepColorArc {
        private int startDeg,gapDeg,index=0,deg=0,dir=0;
        public SweepColorArc(int index) {
            this.gapDeg = 360/(colors.length);
            this.index = index;
            this.startDeg = index*gapDeg;
        }
        public void draw(Canvas canvas) {
            float radius = Math.min(w,h)/2;
            int color = colors[this.index];
            int r = Color.red(color),g = Color.green(color),b = Color.blue(color);
            paint.setColor(Color.argb(150,r,g,b));
            canvas.save();
            canvas.translate(w/2,h/2);
            canvas.drawArc(new RectF(-radius,-radius,radius,radius),startDeg,deg,true,paint);
            canvas.restore();
        }
        public void startUpdating(int dir) {
            this.dir = dir;
        }
        public void update() {
            this.deg+=(dir)*(gapDeg)/5;
            if(this.deg>this.gapDeg || this.deg < 0) {
                this.dir = 0;
            }
        }
        public int hashCode() {
            return startDeg;
        }
    }
}
