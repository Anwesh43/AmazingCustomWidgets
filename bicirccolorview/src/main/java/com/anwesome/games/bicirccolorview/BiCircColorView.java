package com.anwesome.games.bicirccolorview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.anwesome.ui.dimensionsutil.DimensionsUtil;

import java.util.*;
/**
 * Created by anweshmishra on 26/06/17.
 */

public class BiCircColorView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int time = 0,w,h,gapDeg = 0;
    private int[] colors;
    private List<BiCircColor> biCircColors = new ArrayList<>();
    private AnimationHandler animationHandler;
    public BiCircColorView(Context context,int[] colors) {
        super(context);
        this.colors = colors;
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            if(colors.length > 0) {
                gapDeg = 360/colors.length;
                for(int i=0;i<colors.length;i++) {
                    biCircColors.add(new BiCircColor(i));
                }
            }
            animationHandler = new AnimationHandler();
        }
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(w/60);
        paint.setColor(Color.GRAY);
        int r = (2*Math.min(w,h))/5;
        canvas.drawCircle(w/2,h/2,r,paint);
        time++;
        if(animationHandler != null) {
            animationHandler.animate(canvas);
        }
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN && animationHandler!=null) {
            animationHandler.startAnimation();
        }
        return true;
    }
    private class BiCircColor {
        private int index,sweepDeg = 0,startDeg = 0,dir=0;
        public BiCircColor(int index) {
            this.index = index;
            startDeg = gapDeg*index;
        }
        public void draw(Canvas canvas) {
            if(sweepDeg != 0) {
                paint.setColor(colors[index]);
                int r = (2 * Math.min(w, h)) / 5;
                canvas.save();
                canvas.translate(w / 2, h / 2);
                canvas.drawArc(new RectF(-r, -r, r, r), startDeg, sweepDeg, false, paint);
                canvas.drawArc(new RectF(-r, -r, r, r), startDeg + gapDeg - sweepDeg, sweepDeg, false, paint);
                canvas.restore();
            }
        }
        public void update() {
            this.sweepDeg += (this.dir)*(gapDeg/10);
            if(this.sweepDeg> gapDeg/2) {
                this.dir = 0;
                this.sweepDeg = gapDeg/2;
            }
            if(this.sweepDeg < 0) {
                this.dir = 0;
                this.sweepDeg = 0;
            }
        }
        public boolean stopped() {
            return dir == 0;
        }
        public int hashCode() {
            return index;
        }
        public void startUpdating(int dir) {
            this.dir = dir;
        }
    }
    private class AnimationHandler {
        private BiCircColor curr,prev;
        private int i = 0;
        private boolean isAnimated = false;
        public void animate(Canvas canvas) {
            if(prev != null) {
                prev.draw(canvas);
            }
            if(curr != null) {
                curr.draw(canvas);
            }
            if(isAnimated) {
                if(prev!=null) {
                    prev.update();
                }
                if(curr != null) {
                    curr.update();
                    if(curr.stopped()) {
                        i++;
                        i%=colors.length;
                        if(onOpenCloseListener!=null) {
                            if(prev!=null) {
                                onOpenCloseListener.onClose(prev.index);
                            }
                            onOpenCloseListener.onOpen(curr.index);
                        }
                        prev = curr;
                        curr = null;
                        isAnimated = false;
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
        public void startAnimation() {
            if(!isAnimated && curr == null) {
                if(prev != null) {
                    prev.startUpdating(-1);
                }
                if(curr == null) {
                    curr = biCircColors.get(i);
                    curr.startUpdating(1);
                }
                isAnimated = true;
                postInvalidate();
            }
        }
    }
    private OnOpenCloseListener onOpenCloseListener;
    public void setOnOpenCloseListener(OnOpenCloseListener onOpenCloseListener) {
        this.onOpenCloseListener = onOpenCloseListener;
    }
    public static void create(Activity activity,int colors[],OnOpenCloseListener onOpenCloseListener) {
        BiCircColorView biCircColorView = new BiCircColorView(activity,colors);
        biCircColorView.setOnOpenCloseListener(onOpenCloseListener);
        Point size = DimensionsUtil.getDeviceDimension(activity);
        activity.addContentView(biCircColorView,new ViewGroup.LayoutParams(size.x,size.x));
    }
    public interface OnOpenCloseListener {
        void onOpen(int index);
        void onClose(int index);
    }
}
