package com.anwesome.games.verticalclipimageview;

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
        canvas.translate(w/2-size/2,h/2-size/2);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        for(int i=0;i<n;i++) {
            float x = i*wSize;
            canvas.drawRect(new RectF(x,0,x+wSize,size),paint);
        }
        paint.setStyle(Paint.Style.FILL);
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
    private class VerticalClipImage {
        private int index;
        private float x,y,scale = 0,dir = 0;
        public VerticalClipImage(int index) {
            this.index = index;
            this.x = wSize*index;
            this.y = 0;
        }
        public void draw(Canvas canvas) {
            canvas.save();
            Path path = new Path();
            path.addRect(new RectF(0,0,wSize,size*scale), Path.Direction.CCW);
            canvas.clipPath(path);
            canvas.drawBitmap(bitmap,0,0,paint);
            canvas.restore();
        }
        private void startUpdating() {
            if(scale >= 1) {
                dir = -1;
            }
            else {
                dir = 1;
            }
        }
        public void update() {
            scale+=dir*0.2f;
            if(scale > 1) {
                scale = 1;
                dir = 0;
            }
            if(scale < 0) {
                scale = 0;
                dir = 0;
            }
        }
        public int hashCode() {
            return index;
        }
        public boolean handleTap(float x,float y) {
            boolean condition = x>=this.x && x<=this.x+wSize && y>=this.y && y<=this.y+size;
            if(condition) {
                startUpdating();
            }
            return condition;
        }
        public boolean stopped() {
            return dir == 0;
        }
    }
    private class AnimationHandler {
        private boolean animated = false;
        private ConcurrentLinkedQueue<VerticalClipImage> tappedImages = new ConcurrentLinkedQueue<>();
        public void animate() {
            if(animated) {
                for(VerticalClipImage tappedImage:tappedImages) {
                    tappedImage.update();
                    if(tappedImage.stopped()) {
                        tappedImages.remove(tappedImage);
                        if(tappedImages.size() == 0) {
                            animated = false;
                        }
                    }
                }
                try {
                    Thread.sleep(50);
                    invalidate();
                }
                catch (Exception ex) {

                }
            }
        }
        public void handleTap(float x,float y) {
        }
    }
}
