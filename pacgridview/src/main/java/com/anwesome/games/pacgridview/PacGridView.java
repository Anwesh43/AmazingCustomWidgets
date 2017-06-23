package com.anwesome.games.pacgridview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by anweshmishra on 24/06/17.
 */

public class PacGridView extends View {
    private int time = 0,w,h,n=3;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap bitmap;
    public PacGridView(Context context,Bitmap bitmap) {
        super(context);
        this.bitmap = bitmap;
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            int minSize = Math.min(w,h);
            bitmap = Bitmap.createScaledBitmap(bitmap,minSize,minSize,true);
        }
        canvas.save();
        canvas.translate(w/2,h/2);
        paint.setColor(Color.BLACK);
        canvas.drawBitmap(bitmap,-bitmap.getWidth()/2,-bitmap.getHeight()/2,paint);
        canvas.restore();
        time++;
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {

        }
        return true;
    }
    private class PacGrid {
        private float x,y,size,deg=0,dir = 0,index;
        public PacGrid(int i) {
            this.index = i;
            size = Math.min(w,h)/3;
            x = ((i%n)*size)+size/2;
            y = ((i/n)*size)+size/2;
        }
        public void draw(Canvas canvas) {
            canvas.save();
            canvas.translate(x,y);
            int color = Color.parseColor("#FF5722");
            int r = Color.red(color),g = Color.green(color),b = Color.blue(color);
            paint.setColor(Color.argb(150,r,g,b));
            canvas.drawArc(new RectF(-size/2,-size/2,size/2,size/2),deg,360-2*deg,true,paint);
            canvas.restore();
        }
        private void startUpdating() {
            dir = 1;
        }
        public void update() {
            deg += 5*dir;
            if(deg>30) {
                dir = -1;
            }
            if(deg<0) {
                deg = 0;
                dir = 0;
            }
        }
        public boolean stopped(){
            return dir == 0;
        }
        public int hashCode() {
            return (int)(x+y+index);
        }
        public boolean handleTap(float x,float y) {
            boolean condition =  x>=this.x-size/2 && x<=this.x+size/2 && y>=this.y-size/2 && y<=this.y+size/2 && dir == 0;
            if(condition) {
                startUpdating();
            }
            return condition;
        }
    }
    private class AnimationHandler {
        private boolean isAnimated = false;
        private ConcurrentLinkedQueue<PacGrid> tappedPacs = new ConcurrentLinkedQueue<>();
        public void animate() {
            if(isAnimated) {
                for(PacGrid tappedPac:tappedPacs) {
                    tappedPac.update();
                    if(tappedPac.stopped()) {
                        tappedPacs.remove(tappedPac);
                        if(tappedPacs.size() == 0) {
                            isAnimated = false;
                        }
                    }
                }
            }
        }
        public void handleTap(float x,float y) {
            
        }
    }
}
