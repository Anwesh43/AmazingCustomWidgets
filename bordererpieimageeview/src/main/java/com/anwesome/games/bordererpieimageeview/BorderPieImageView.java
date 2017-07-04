package com.anwesome.games.bordererpieimageeview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.anwesome.ui.dimensionsutil.DimensionsUtil;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by anweshmishra on 04/07/17.
 */

public class BorderPieImageView extends View {
    private int time = 0,w,h,size;
    private Bitmap bitmap;
    private AnimationHandler animationHandler;
    private ConcurrentLinkedQueue<PieBitmap> pieBitmaps = new ConcurrentLinkedQueue<>();
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
            for(int i=0;i<4;i++) {
                pieBitmaps.add(new PieBitmap(i));
            }
            animationHandler = new AnimationHandler();
        }
        canvas.save();
        canvas.translate(w/2,h/2);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(w/90);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(new RectF(-size/2,size/2,size/2,size/2),paint);
        for(PieBitmap pieBitmap:pieBitmaps) {
            pieBitmap.draw(canvas);
        }
        canvas.restore();
        time++;
        animationHandler.animate();
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN && animationHandler != null) {
            animationHandler.handleTap(event.getX()-w/2,event.getY()-h/2);
        }
        return true;
    }
    private class PieBitmap {
        private int index;
        private float x,y,r,dir=0,scale = 0;
        public PieBitmap(int index) {
            this.index = index;
            r = size/5;
            x = (float)((size/2+2*r)*Math.cos(((index*90)+45)*Math.PI/180));
            y = (float)((size/2+2*r)*Math.sin(((index*90)+45)*Math.PI/180));
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
            float tx = size/4*bitmapPivots[index][0],ty = size/4*bitmapPivots[index][1];
            Path path = new Path();
            path.addRect(new RectF(tx+(-size/4)*scale,ty+(-size/4)*scale,tx+(size/4)*scale,ty+(size/4)*scale), Path.Direction.CCW);
            canvas.clipPath(path);
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
                if(onExpandCloseListener != null) {
                   onExpandCloseListener.onExpand(index);
                }
            }
            if(scale < 0) {
                dir = 0;
                scale = 0;
                if(onExpandCloseListener != null) {
                    onExpandCloseListener.onClose(index);
                }
            }
        }
        public boolean stopped() {
            return dir == 0;
        }
        private void startUpdating() {
            if(scale >= 1) {
                dir = -1;
            }
            if(scale <= 0) {
                dir = 1;
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
            for(PieBitmap pieBitmap:pieBitmaps) {
                if(pieBitmap.handleTap(x,y)) {
                    tappedBitmaps.add(pieBitmap);
                    if(tappedBitmaps.size() == 1) {
                        animated = true;
                        postInvalidate();
                    }
                    break;
                }
            }
        }
    }
    private OnExpandCloseListener onExpandCloseListener;
    public void setOnExpandCloseListener(OnExpandCloseListener onExpandCloseListener) {
        this.onExpandCloseListener = onExpandCloseListener;
    }
    public interface OnExpandCloseListener {
        void onExpand(int index);
        void onClose(int index);
    }
    public static void create(Activity activity,Bitmap bitmap,OnExpandCloseListener onExpandCloseListener) {
        BorderPieImageView borderPieImageView = new BorderPieImageView(activity,bitmap);
        borderPieImageView.setOnExpandCloseListener(onExpandCloseListener);
        Point size = DimensionsUtil.getDeviceDimension(activity);
        activity.addContentView(borderPieImageView,new ViewGroup.LayoutParams(size.x,size.x));
    }
}
