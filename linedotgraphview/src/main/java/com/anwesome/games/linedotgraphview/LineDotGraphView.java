package com.anwesome.games.linedotgraphview;

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

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by anweshmishra on 07/07/17.
 */

public class LineDotGraphView extends View {
    private int data[],time = 0,w,h,size,px,py;
    private AnimationHandler animationHandler = new AnimationHandler();
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private ConcurrentLinkedQueue<LineDot> lineDots = new ConcurrentLinkedQueue<>();
    public LineDotGraphView(Context context,int[] data) {
        super(context);
        this.data = data;
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            size = (4*w/5)/(2*data.length+1);
            px = w/10;
            py = 9*h/10;
            int maxData = data[0];
            for(int i=1;i<data.length;i++) {
                if(data[i] > maxData) {
                    maxData = data[i];
                }
            }
            float currX = 3*size/2;
            for(int i=0;i<data.length;i++) {
                float currH = (4*h/5)*((data[i]*1.0f)/maxData);
                lineDots.add(new LineDot(currX,currH,i));
                currX += 2*size;
            }
        }
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(size/8);
        canvas.save();
        canvas.translate(px,py);
        canvas.drawLine(0,0,4*w/5,0,paint);
        canvas.drawLine(0,0,0,-4*h/5,paint);
        for(LineDot lineDot:lineDots) {
            lineDot.draw(canvas);
        }
        canvas.restore();
        time++;
        animationHandler.animate();
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            animationHandler.handlTap(event.getX(),event.getY());
        }
        return true;
    }
    private class LineDot {
        private float x,h,dir = 0,scale = 0;
        private int index;
        public LineDot(float x,float h,int index) {
            this.x = x;
            this.h = h;
            this.index = index;
        }
        public void draw(Canvas canvas) {
            canvas.save();
            canvas.translate(x,-h);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(0,0,size/2,paint);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawArc(new RectF(-size/2,-size/2,size/2,size/2),0,360*scale,true,paint);
            canvas.drawLine(0,h,0,h*(1-scale),paint);
            canvas.restore();
        }
        public void update() {
            scale += 0.2f*dir;
            if(scale > 1) {
                dir = 0;
                scale = 1;
            }
            if(scale < 0) {
                dir = 0;
                scale = 0;
            }
            if(dir == 0 && onSelectionListener != null) {
                if(scale >= 1) {
                    onSelectionListener.onSelect(data[index]);
                }
                else {
                    onSelectionListener.onUnSelect(data[index]);
                }
            }
        }
        private void startUpdating() {
            dir = scale == 0?1:-1;
        }
        public int hashCode() {
            return (int)(x+h+scale+dir);
        }
        public boolean handleTap(float x,float y) {
            boolean condition =  x>=px+this.x-size && x<=px+this.x+size && y>=py-this.h-size && y<=py-this.h+size;
            if(condition) {
                startUpdating();
            }
            return condition;
        }
        public boolean stopUpdating() {
            return dir == 0;
        }
    }
    private class AnimationHandler {
        private boolean animating = false;
        private ConcurrentLinkedQueue<LineDot> tappedLineDots = new ConcurrentLinkedQueue<>();
        public void animate() {
            if(animating) {
                for(LineDot lineDot:tappedLineDots) {
                    lineDot.update();
                    if(lineDot.stopUpdating()) {
                        tappedLineDots.remove(lineDot);
                        if(tappedLineDots.size() == 0) {
                            animating = false;
                        }
                    }
                }
                try {
                    Thread.sleep(75);
                    invalidate();
                }
                catch (Exception ex) {

                }
            }
        }
        public void handlTap(float x,float y) {
            for(LineDot lineDot:lineDots) {
                if(lineDot.handleTap(x,y)) {
                    tappedLineDots.add(lineDot);
                    if(!animating) {
                        animating = true;
                        postInvalidate();
                    }
                }
            }
        }
    }
    private OnSelectionListener onSelectionListener;
    public void setOnSelectionListener(OnSelectionListener onSelectionListener) {
        this.onSelectionListener = onSelectionListener;
    }
    public static void create(Activity activity,int[] data,OnSelectionListener...listeners) {
        LineDotGraphView lineDotGraphView = new LineDotGraphView(activity,data);
        if(listeners.length == 1) {
            lineDotGraphView.setOnSelectionListener(listeners[0]);
        }
        Point size = DimensionsUtil.getDeviceDimension(activity);
        activity.addContentView(lineDotGraphView,new ViewGroup.LayoutParams(size.x,size.x));
    }
    public interface OnSelectionListener {
        void onSelect(int data);
        void onUnSelect(int data);
    }
}
