package com.anwesome.games.boundarylineswitch;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.anwesome.ui.dimensionsutil.DimensionsUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anweshmishra on 29/06/17.
 */

public class BoundaryLineSwitchView extends View {
    private int w,h,time = 0,size;
    private Bitmap bitmap;
    private int color = Color.parseColor("#00BCD4");
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private List<BoundaryLine> boundaryLineList = new ArrayList<>();
    private AnimationHandler animationHandler;
    public BoundaryLineSwitchView(Context context, Bitmap bitmap) {
        super(context);
        this.bitmap = bitmap;
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            size = Math.min(w,h)/2;
            bitmap = Bitmap.createScaledBitmap(bitmap,size,size,true);
            animationHandler = new AnimationHandler();
            for(int i=0;i<4;i++) {
                boundaryLineList.add(new BoundaryLine(i));
            }
        }
        canvas.save();
        canvas.translate(w/2,h/2);
        canvas.drawBitmap(bitmap,-size/2,-size/2,paint);
        canvas.restore();
        time++;
        paint.setColor(color);
        paint.setStrokeWidth(w/60);
        animationHandler.animate(canvas);
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN && animationHandler != null) {
            animationHandler.startAnimating();
        }
        return true;
    }
    private class BoundaryLine {
        private float wx = 0;
        private int index = 0,dir = 0;
        public BoundaryLine(int index) {
            this.index = index;
        }
        public void draw(Canvas canvas) {
            canvas.save();
            canvas.translate(w/2,h/2);
            canvas.rotate(index*90);
            canvas.drawLine(size/2,-wx,size/2,wx,paint);
            canvas.restore();
        }
        public void update() {
            wx += (size/10)*dir;
            if(wx>size/2) {
                dir = 0;
                wx = size/2;
            }
            if(wx<0) {
                dir = 0;
                wx = 0;
            }
        }
        public int hashCode() {
            return index;
        }
        public void startUpdating(int dir) {
            this.dir = dir;
        }
        public boolean stopUpdating() {
            return dir == 0;
        }
    }
    private class AnimationHandler {
        private boolean animated = false;
        private BoundaryLine curr,prev;
        private int index = 0;
        public void animate(Canvas canvas) {
            if(curr != null) {
                curr.draw(canvas);
            }
            if(prev != null) {
                prev.draw(canvas);
            }
            if(animated) {
                if(prev!=null) {
                    prev.update();
                }
                if(curr != null) {
                    curr.update();
                }
                if(curr.stopUpdating()) {
                    prev = curr;
                    animated = false;
                    curr = null;
                    if(onSelectionListener != null) {
                        onSelectionListener.onSelected(index);
                    }
                    index++;
                    index %= boundaryLineList.size();
                }
                try  {
                    Thread.sleep(50);
                    invalidate();
                }
                catch (Exception ex) {

                }
            }
        }
        public void startAnimating() {
            if(!animated && curr == null) {
                if(prev != null) {
                    prev.startUpdating(-1);
                }
                if(index < boundaryLineList.size()) {
                    curr = boundaryLineList.get(index);
                    curr.startUpdating(1);
                    animated = true;
                    postInvalidate();
                }

            }
        }
    }
    public void setColor(int color) {
        if(color != 0) {
            this.color = color;
        }
    }
    private OnSelectionListener onSelectionListener;
    public void setOnSelectionListener(OnSelectionListener onSelectionListener) {
        this.onSelectionListener = onSelectionListener;
    }
    public static void create(Activity activity,Bitmap bitmap,OnSelectionListener onSelectionListener,int...colors) {
        BoundaryLineSwitchView boundaryLineSwitchView = new BoundaryLineSwitchView(activity,bitmap);
        boundaryLineSwitchView.setOnSelectionListener(onSelectionListener);
        if(colors.length == 1) {
            boundaryLineSwitchView.setColor(colors[0]);
        }
        Point size = DimensionsUtil.getDeviceDimension(activity);
        activity.addContentView(boundaryLineSwitchView,new ViewGroup.LayoutParams(size.x,size.x));
    }
    public interface OnSelectionListener{
        void onSelected(int index);
    }
}
