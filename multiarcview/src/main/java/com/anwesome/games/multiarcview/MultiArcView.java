package com.anwesome.games.multiarcview;

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

/**
 * Created by anweshmishra on 18/07/17.
 */

public class MultiArcView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int time = 0,w,h,n=3;
    private AnimationHandler animationHandler = new AnimationHandler();
    public void setN(int n) {
        this.n = Math.max(this.n,n);
    }
    public MultiArcView(Context context) {
        super(context);
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
        }
        time++;
        animationHandler.render(canvas);
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            animationHandler.startAnimation();
        }
        return true;
    }
    private class MultiArc {
        public void draw(Canvas canvas, float scale) {
            for(int i=0;i<n;i++) {
                paint.setColor(Color.GRAY);
                drawArc(canvas,paint,1,i);
                paint.setColor(Color.CYAN);
                drawArc(canvas,paint,scale,i);
            }
        }
        private void drawArc(Canvas canvas,Paint paint,float scale,int i) {
            canvas.save();
            canvas.translate(w/2,h/2);
            float size = ((w/2*i)*1.0f)/n;
            canvas.drawArc(new RectF(-size/2,-size/2,size/2,size/2),270,90*scale,false,paint);
            canvas.restore();
        }
    }
    private class StateContainer {
        private float scale = 0,dir = 0;
        public void update() {
            scale += dir*0.2f;
            if(scale > 1) {
                dir = 0;
                scale = 1;
            }
            if(scale < 0) {
                dir = 0;
                scale = 0;
            }
        }
        public void startUpdating() {
            if(dir == 0) {
                dir = scale <= 0 ?1:-1;
            }
        }
        public boolean stopped() {
            return dir == 0;
        }
    }
    private class AnimationHandler {
        private boolean animated = false;
        private StateContainer stateContainer = new StateContainer();
        private MultiArc multiArc = new MultiArc();
        public void render(Canvas canvas) {
            multiArc.draw(canvas,stateContainer.scale);
            if(animated) {
                stateContainer.update();
                if(stateContainer.stopped()) {
                    animated = false;
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
            if(!animated) {
                animated = true;
                stateContainer.startUpdating();
                postInvalidate();
            }
        }
    }
    public static void create(Activity activity) {
        MultiArcView multiArcView = new MultiArcView(activity);
        Point size = DimensionsUtil.getDeviceDimension(activity);
        activity.addContentView(multiArcView,new ViewGroup.LayoutParams(size.x,size.y));
    }
}
