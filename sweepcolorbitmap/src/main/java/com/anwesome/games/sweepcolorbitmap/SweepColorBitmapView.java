package com.anwesome.games.sweepcolorbitmap;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.anwesome.ui.dimensionsutil.DimensionsUtil;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by anweshmishra on 25/06/17.
 */

public class SweepColorBitmapView extends View {
    private int time =0,w,h,gapDeg=0;
    private Bitmap bitmap;
    private ConcurrentLinkedQueue<SweepColorArc> sweepColorArcs = new ConcurrentLinkedQueue<>();
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int colors[];
    private AnimationHandler animationHandler;
    private SweepColorBitmapView(Context context,Bitmap bitmap,int colors[]) {
        super(context);
        this.bitmap = bitmap;
        this.colors = colors;
        this.animationHandler = new AnimationHandler();
    }
    private SweepColorArc getArcAt(int index) {
        SweepColorArc currArc = null;
        int i=0;
        for(SweepColorArc sweepColorArc:sweepColorArcs) {
            if(i == index) {
                currArc = sweepColorArc;
                break;
            }
            i++;
        }
        return currArc;
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            bitmap = Bitmap.createScaledBitmap(bitmap,w,h,true);
            if(colors.length > 0) {
                this.gapDeg = 360/(colors.length);
            }
            for(int i=0;i<colors.length;i++) {
                sweepColorArcs.add(new SweepColorArc(i));
            }
        }
        paint.setColor(Color.BLACK);
        canvas.drawBitmap(bitmap,0,0,paint);
        time++;
        animationHandler.animate(canvas);
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN && animationHandler != null) {
            animationHandler.handleAnimation();
        }
        return true;
    }
    private class SweepColorArc {
        private int startDeg,index=0,deg=0,dir=0;
        public SweepColorArc(int index) {

            this.index = index;
            this.startDeg = index*gapDeg;
        }
        public void draw(Canvas canvas) {
            float radius = Math.min(w,h)/2;
            int color = colors[this.index];
            int r = Color.red(color),g = Color.green(color),b = Color.blue(color);
            paint.setColor(Color.argb(100,r,g,b));
            canvas.save();
            canvas.translate(w/2,h/2);
            if(index == animationHandler.i-1) {
                Log.d("deg",""+deg);
            }
            if(this.deg > 0) {
                canvas.drawArc(new RectF(-radius, -radius, radius, radius), startDeg, deg, true, paint);
            }
            canvas.restore();
        }
        public void startUpdating(int dir) {
            this.dir = dir;
        }
        public void update() {
            this.deg+=(dir)*(gapDeg)/5;
            if(this.deg>gapDeg || this.deg < 0) {
                this.dir = 0;
                if(this.deg > gapDeg) {
                    this.deg = gapDeg;
                }
                if(this.deg < 0) {
                    this.deg = 0;
                }
            }
        }
        public boolean stopped() {
            return dir == 0;
        }
        public int hashCode() {
            return startDeg;
        }
    }
    private class AnimationHandler {
        private int i=0;
        private SweepColorArc currArc,prevArc;
        private boolean isAnimated = false;
        public void animate(Canvas canvas) {
            if(prevArc != null) {
                prevArc.draw(canvas);
            }
            if(currArc != null) {
                currArc.draw(canvas);
            }
            if(isAnimated) {
                if(prevArc!=null) {
                    prevArc.update();
                }
                if(currArc != null) {
                    currArc.update();
                    if(currArc.stopped()) {
                        if(onOpenCloseListener != null) {
                            if (prevArc != null) {
                                onOpenCloseListener.onClose(prevArc.index);
                            }
                            if (currArc != null) {
                                onOpenCloseListener.onOpen(currArc.index);
                            }
                        }
                        isAnimated = false;
                        i++;
                        i%=sweepColorArcs.size();
                        prevArc = null;
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
        public void handleAnimation() {
            if(!isAnimated && prevArc == null) {
                if(currArc != null) {
                    prevArc = currArc;
                    prevArc.startUpdating(-1);
                }
                currArc = getArcAt(i);
                if(currArc != null) {
                    currArc.startUpdating(1);
                    isAnimated = true;
                    postInvalidate();
                }
            }
        }
    }
    private OnOpenCloseListener onOpenCloseListener;
    public void setOnOpenCloseListener(OnOpenCloseListener onOpenCloseListener) {
        this.onOpenCloseListener = onOpenCloseListener;
    }
    public static void create(Activity activity,Bitmap bitmap,int colors[],OnOpenCloseListener onOpenCloseListener) {
        SweepColorBitmapView sweepColorBitmapView = new SweepColorBitmapView(activity,bitmap,colors);
        sweepColorBitmapView.setOnOpenCloseListener(onOpenCloseListener);
        Point size = DimensionsUtil.getDeviceDimension(activity);
        activity.addContentView(sweepColorBitmapView,new ViewGroup.LayoutParams(size.x,size.x));
    }
    public interface OnOpenCloseListener {
        void onOpen(int index);
        void onClose(int index);
    }
}
