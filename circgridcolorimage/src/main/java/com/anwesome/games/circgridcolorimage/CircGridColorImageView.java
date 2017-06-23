package com.anwesome.games.circgridcolorimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by anweshmishra on 23/06/17.
 */

public class CircGridColorImageView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int w,h,time=0,n=3;
    private ConcurrentLinkedQueue<CircGridImage> circGridImages = new ConcurrentLinkedQueue<>();
    private Bitmap bitmap;
    private AnimationHandler animationHandler;
    public CircGridColorImageView(Context context, Bitmap bitmap) {
        super(context);
        this.bitmap = bitmap;
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            bitmap = Bitmap.createScaledBitmap(bitmap,Math.max(w,h)/3,Math.max(w,h)/3,true);
            for(int i=0;i<n*n;i++) {
                circGridImages.add(new CircGridImage(i));
            }
            animationHandler = new AnimationHandler();
        }
        for(CircGridImage circGridImage:circGridImages) {
            circGridImage.draw(canvas);
        }
        time++;
        if(animationHandler != null) {
            animationHandler.animate();
        }
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN && animationHandler != null) {
            animationHandler.handleTap(event.getX(),event.getY());
        }
        return true;
    }
    private class CircGridImage {
        private float x,y,size,dir = 0,prevDir = -1,scale = 0;
        public CircGridImage(int i) {
            size = Math.max(w,h)/3;
            x = ((i%n)*size)+size/2;
            y = ((i/n)*size)+size/2;
        }
        public int hashCode() {
            return (int)(x+y);
        }
        public void draw(Canvas canvas) {
            canvas.save();
            canvas.translate(x,y);
            Path path = new Path();
            path.addCircle(0,0,size/2, Path.Direction.CCW);
            canvas.clipPath(path);
            canvas.drawBitmap(bitmap,-size/2,-size/2,paint);
            canvas.save();
            canvas.scale(scale,scale);
            paint.setColor(Color.argb(150,0,0,255));
            canvas.drawPath(path,paint);
            canvas.restore();
            canvas.restore();
        }
        private void startUpdating() {
            dir = prevDir*-1;
        }
        public void update() {
            scale += 0.2f*dir;
            if(scale>1) {
                scale = 1;
                dir = 0;
                prevDir = 1;
            }
            if(scale < 0) {
                dir = 0;
                prevDir = -1;
                scale = 0;
            }
        }
        public boolean stopped() {
            return dir == 0;
        }
        public boolean handleTap(float x,float y) {
            boolean condition = x>=this.x-size/2 && x<=this.x+size/2 && y>=this.y-size/2 && y<=this.y+size/2 && dir == 0;
            if(condition) {
                startUpdating();
            }
            return condition;
        }
    }
    private class AnimationHandler {
        private ConcurrentLinkedQueue<CircGridImage> tappedImages = new ConcurrentLinkedQueue<>();
        private boolean isAnimated = false;
        public void animate() {
            if(isAnimated) {
                for(CircGridImage tappedImage:tappedImages) {
                    tappedImage.update();
                    if(tappedImage.stopped()) {
                        tappedImages.remove(tappedImage);
                        if(tappedImages.size() == 0) {
                            isAnimated = false;
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
            for(CircGridImage circGridImage:circGridImages) {
                if(circGridImage.handleTap(x,y)) {
                    tappedImages.add(circGridImage);
                    if(circGridImages.size() == 1) {
                        isAnimated = true;
                        postInvalidate();
                    }
                }
            }
        }
    }
}
