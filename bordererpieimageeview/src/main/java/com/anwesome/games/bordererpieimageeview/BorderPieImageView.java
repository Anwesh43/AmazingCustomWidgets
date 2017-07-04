package com.anwesome.games.bordererpieimageeview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by anweshmishra on 04/07/17.
 */

public class BorderPieImageView extends View {
    private int time = 0,w,h,size;
    private Bitmap bitmap;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int bitmapPivots[][] = {{1,1},{-1,1},{-1,-1},{1,-1}};
    public BorderPieImageView(Context context,Bitmap bitmap) {
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
        canvas.restore();
        time++;
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {

        }
        return true;
    }
    private class PieBitmap {
        private int index;
        private float x,y,r,dir=0,scale = 0;
        public PieBitmap(int index) {
            this.index = index;
            r = size/5;
            x = (float)((size+r)*Math.cos(((index*90)+45)*Math.PI/180));
            y = (float)((size+r)*Math.sin(((index*90)+45)*Math.PI/180));
        }
        public void draw(Canvas canvas) {
            canvas.save();
            canvas.translate(x,y);
            paint.setColor(Color.BLUE);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(r/30);
            canvas.drawCircle(0,0,r,paint);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawArc(new RectF(-r,-r,r,r),0,360*scale,true,paint);
            canvas.restore();
            canvas.save();
            canvas.translate(size/2*bitmapPivots[index][0],size/2*bitmapPivots[index][1]);
            Path path = new Path();
            path.addRect(new RectF((-size/4)*scale,(-size/4)*scale,(size/4)*scale,(size/4)*scale), Path.Direction.CCW);
            canvas.drawBitmap(bitmap,-size/2,-size/2,paint);
            canvas.restore();
        }
        public int hashCode() {
            return index;
        }
        public void update() {
            scale+=0.2f*dir;
            if(scale > 1) {
                dir = 0;
                scale = 1;
            }
            if(scale < 0) {
                dir = 0;
                scale = 0;
            }
        }
        public boolean stopped() {
            return dir == 0;
        }
        private void startUpdating() {
            if(scale >= 1) {
                dir = 1;
            }
            if(scale <= 0) {
                dir = 0;
            }
        }
        public boolean handleTap(float x,float y) {
            boolean condition =  x>=this.x-r && x<=this.x+r && y>=this.y-r && y<=this.y+r;
            if(condition) {
                startUpdating();
            }
            return condition;
        }
    }
    private class AnimationHandler {
        private boolean animated = false;
        private ConcurrentLinkedQueue<PieBitmap> tappedBitmaps = new ConcurrentLinkedQueue<>();
        public void animate() {
            if(animated) {
                for(PieBitmap tappedBitmap:tappedBitmaps) {
                    tappedBitmap.update();
                    if(tappedBitmap.stopped()) {
                        tappedBitmaps.remove(tappedBitmap);
                        if(tappedBitmaps.size() == 0) {
                            animated = false;
                        }
                    }
                }
                try {
                    Thread.sleep(50);
                    invalidate();
                }
                catch(Exception ex) {

                }
            }
        }
        public void handleTap(float x,float y) {

        }
    }
}
