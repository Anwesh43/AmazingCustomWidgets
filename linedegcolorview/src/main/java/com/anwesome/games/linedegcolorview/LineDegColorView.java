package com.anwesome.games.linedegcolorview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.anwesome.ui.dimensionsutil.DimensionsUtil;

import java.util.*;

/**
 * Created by anweshmishra on 27/06/17.
 */

public class LineDegColorView extends View {
    private int[] colors;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int time=0,w,h,gapDeg=0,r=0;
    private List<LineDeg> lineDegList = new ArrayList<>();
    private AnimationHandler animationHandler;
    public LineDegColorView(Context context,int[] colors) {
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
                    lineDegList.add(new LineDeg(i));
                }
            }
            r = Math.min(w,h)/2;
            animationHandler = new AnimationHandler();
        }
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(r/30);
        paint.setColor(Color.GRAY);
        canvas.drawCircle(w/2,h/2,r,paint);
        time++;
        animationHandler.animate(canvas);
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN && animationHandler != null && lineDegList.size()>0) {
            animationHandler.startAnimating();
        }
        return true;
    }
    private class LineDeg {
        private int index,dir = 0,deg = 0,lx = 0;
        public LineDeg(int index) {
            this.index = index;
            deg = gapDeg*index;
        }
        public void draw(Canvas canvas) {
            int color = colors[index];
            paint.setColor(color);
            canvas.save();
            canvas.translate(w/2,h/2);
            canvas.rotate(deg);
            canvas.drawLine(0,0,lx,0,paint);
            canvas.restore();
        }
        public void update() {
            lx += (r/5)*dir;
            if(lx > r) {
                dir = 0;
                lx = r;
            }
            if(lx < 0) {
                dir = 0;
                lx = 0;
            }
        }
        public boolean stopUpdating() {
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
        private LineDeg curr,prev;
        private boolean animating = false;
        private int index = 0;
        public void animate(Canvas canvas) {
            if(prev != null) {
                prev.draw(canvas);
            }
            if(curr != null) {
                curr.draw(canvas);
            }
            if(animating) {
                if(prev != null) {
                    prev.update();
                }
                if(curr != null) {
                    curr.update();
                    if(curr.stopUpdating()) {
                        index++;
                        index%=colors.length;
                        prev = curr;
                        curr = null;
                        animating = false;
                    }
                }
                try {
                    Thread.sleep(100);
                    invalidate();
                }
                catch (Exception ex) {

                }
            }
        }
        public void startAnimating() {
            if(!animating) {
                if(prev != null) {
                    prev.startUpdating(-1);
                }
                if(curr == null) {
                    curr = lineDegList.get(index);
                    curr.startUpdating(1);
                }
                animating = true;
                postInvalidate();
            }
        }
    }
    public static void create(Activity activity,int[] colors) {
        LineDegColorView lineDegColorView = new LineDegColorView(activity,colors);
        Point size = DimensionsUtil.getDeviceDimension(activity);
        activity.addContentView(lineDegColorView,new ViewGroup.LayoutParams(size.x,size.x));
    }
}
