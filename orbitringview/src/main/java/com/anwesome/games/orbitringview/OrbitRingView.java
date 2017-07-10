package com.anwesome.games.orbitringview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.anwesome.ui.dimensionsutil.DimensionsUtil;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by anweshmishra on 10/07/17.
 */

public class OrbitRingView extends View {
    private int n=3;
    private Renderer renderer = new Renderer();
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private ConcurrentLinkedQueue<OrbitRing> orbitRings = new ConcurrentLinkedQueue<>();
    public OrbitRingView(Context context) {
        super(context);
    }
    public void setN(int n) {
        this.n = Math.max(this.n,n);
    }
    public void onDraw(Canvas canvas) {
        renderer.render(canvas);
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            renderer.handleTap(event.getX(),event.getY());
        }
        return true;
    }
    private class Renderer {
        private int w,h,time = 0;
        private DrawingService drawingService;
        private AnimationService animationService = new AnimationService();
        public void render(Canvas canvas) {
            if(time == 0) {
                w = canvas.getWidth();
                h = canvas.getHeight();
                drawingService = new DrawingService(w,h);
            }
            drawingService.draw(canvas);
            time++;
            animationService.animate();
        }
        public void handleTap(float x,float y) {
            animationService.handleTap(x-w/2,y-h/2);
        }
    }
    private class DrawingService {
        public DrawingService(int w,int h) {
            float size = (5*(Math.min(w,h))/6)/n;
            for(int i=0;i<n;i++) {
                orbitRings.add(new OrbitRing(i,size));
            }
        }
        public void draw(Canvas canvas) {
            for(OrbitRing orbitRing:orbitRings) {
                orbitRing.draw(canvas);
            }
        }
    }
    private class AnimationService {
        private ConcurrentLinkedQueue<OrbitRing> tappedRings = new ConcurrentLinkedQueue<>();
        private boolean animated = false;
        public void animate() {
            if(animated) {
                for(OrbitRing orbitRing:tappedRings) {
                    orbitRing.update();
                    if(orbitRing.stopped()) {
                        tappedRings.remove(orbitRing);
                        if(tappedRings.size() == 0) {
                            animated = false;
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
        public void handleTap(float x,float y) {
            boolean tapped = false;
            for(OrbitRing orbitRing:orbitRings) {
                tapped = orbitRing.handleTap(x,y);
                if(tapped) {
                    tappedRings.add(orbitRing);
                    break;
                }
            }
            if(!animated && tapped) {
                animated = true;
                postInvalidate();
            }
        }
    }
    private class OrbitRing {
        private int index = 0;
        private float x,y,r,scale = 0,dir = 0;
        public OrbitRing(int index,float size) {
            this.index = index;
            this.r = (size*(index+1))/2;
            this.x = 0;
            this.y = (2*(index%2)-1)*this.r;
        }
        public void draw(Canvas canvas) {
            float deg = 360*scale;
            canvas.save();
            canvas.translate(canvas.getWidth()/2,canvas.getHeight()/2);
            canvas.save();
            canvas.rotate(deg);
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.BLUE);
            canvas.drawCircle(this.x,this.y,r/5,paint);
            canvas.restore();
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(r/60);
            paint.setColor(Color.GRAY);
            canvas.drawCircle(0,0,r,paint);
            paint.setColor(Color.BLUE);
            Path path = new Path();
            for(float i=0;i<=deg;i+=10) {
                float x= (float)(r*Math.cos(((i-90)+180*(index%2))*Math.PI/180)),y = (float)(r*Math.sin(((i-90)+180*(index%2))*Math.PI/180));
                if(i == 0) {
                    path.moveTo(x,y);
                }
                else {
                    path.lineTo(x,y);
                }
            }
            canvas.drawPath(path,paint);
            canvas.restore();
        }
        public void update() {
            scale += 0.1f*dir;
            if(scale > 1) {
                dir = 0;
                scale = 1;
            }
            if(scale < 0) {
                dir = 0;
                scale = 0;
            }
            if(dir == 0 && onSelectionListener != null) {
                if(scale == 1) {
                    onSelectionListener.onSelect(index);
                }
                else {
                    onSelectionListener.onUnSelect(index);
                }
            }
        }
        public boolean stopped() {
            return dir == 0;
        }
        public int hashCode() {
            return index;
        }
        public boolean handleTap(float x,float y) {
            boolean condition =  x>=this.x-r/4 && x<=this.x+r/4 && y>=this.y-r/4 && y<=this.y+r/4 && dir == 0;
            if(condition) {
                dir = scale <=0?1:-1;
            }
            return condition;
        }
    }
    public static void create(Activity activity,int n,OnSelectionListener...onSelectionListeners) {
        OrbitRingView orbitRingView = new OrbitRingView(activity);
        orbitRingView.setN(n);
        if(onSelectionListeners.length == 1) {
            orbitRingView.setOnSelectionListener(onSelectionListeners[0]);
        }
        Point size = DimensionsUtil.getDeviceDimension(activity);
        activity.addContentView(orbitRingView,new ViewGroup.LayoutParams(size.x,size.x));
    }
    private OnSelectionListener onSelectionListener;
    public void setOnSelectionListener(OnSelectionListener onSelectionListener) {
        this.onSelectionListener = onSelectionListener;
    }
    public interface OnSelectionListener {
        void onSelect(int index);
        void onUnSelect(int index);
    }
}
