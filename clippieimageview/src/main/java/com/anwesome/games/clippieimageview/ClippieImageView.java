package com.anwesome.games.clippieimageview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.anwesome.ui.dimensionsutil.DimensionsUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anweshmishra on 01/07/17.
 */

public class ClippieImageView extends View {
    private Bitmap bitmap;
    private int time = 0,w,h,r;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private List<ClipImage> clipImages = new ArrayList<>();
    private AnimationHandler animationHandler;
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
            animationHandler = new AnimationHandler();
        }
        animationHandler.animate(canvas);
        time++;
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN && animationHandler != null) {
            animationHandler.startAnimating();
        }
        return true;
    }
    private class ClipImage{
        private int index,deg=0,dir = 0;
        public ClipImage(int index) {
            this.index = index;

        }
        public void draw(Canvas canvas) {
            canvas.save();
            Path path = new Path();
            path.addArc(new RectF(-r,-r,r,r),index*60,deg);
            canvas.clipPath(path);
            canvas.drawBitmap(bitmap,-r,-r,paint);
            canvas.restore();
        }
        public void update() {
            deg += 12*dir;
            if(deg > 60) {
                deg = 60;
                dir = 0;
            }
            if(deg < 0) {
                deg = 0;
                dir = 0;
            }
        }
        public int hashCode() {
            return index;
        }
        public void startUpdating(int dir) {
            this.dir = dir;
        }
        public boolean stopped() {
            return dir == 0;
        }
    }
    private class AnimationHandler {
        private int index = 0;
        private ClipImage curr,prev;
        private boolean animated = false;
        public void animate(Canvas canvas) {
            if(prev != null) {
                prev.draw(canvas);
            }
            if(curr != null) {
                curr.draw(canvas);
            }
            if(animated) {
                if(curr != null) {
                    if (prev != null) {
                        prev.update();
                    }
                    curr.update();
                    try {
                        Thread.sleep(50);
                        invalidate();
                    }
                    catch (Exception ex) {

                    }
                    if(curr.stopped()) {
                        prev = curr;
                        animated = false;
                        curr = null;
                        index++;
                        index %= clipImages.size();
                    }
                }
            }
        }
        public void startAnimating() {
            if(!animated && index < clipImages.size()) {
                animated = true;
                if(prev != null) {
                    prev.startUpdating(-1);
                }
                curr = clipImages.get(index);
                curr.startUpdating(1);
                postInvalidate();

            }
        }
    }
    public static void create(Activity activity,Bitmap bitmap) {
        ClippieImageView clippieImageView = new ClippieImageView(activity,bitmap);
        Point size = DimensionsUtil.getDeviceDimension(activity);
        activity.addContentView(clippieImageView,new ViewGroup.LayoutParams(size.x,size.x));
    }
}
