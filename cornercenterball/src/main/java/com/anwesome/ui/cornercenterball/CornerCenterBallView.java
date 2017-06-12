package com.anwesome.ui.cornercenterball;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
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
 * Created by anweshmishra on 13/06/17.
 */

public class CornerCenterBallView extends View {
    private int time = 0,w,h;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private CornerCenterBall cornerCenterBall;
    private AnimationHandler animationHandler;
    public CornerCenterBallView(Context context) {
        super(context);
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            cornerCenterBall = new CornerCenterBall();
            animationHandler = new AnimationHandler();
        }
        paint.setStrokeWidth(w/60);
        cornerCenterBall.draw(canvas);
        time++;
    }
    public void update(float factor) {
        cornerCenterBall.update(factor);
        postInvalidate();
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN && animationHandler!=null) {
            animationHandler.start();
        }
        return true;
    }
    private class CornerCenterBall {
        private int index = 0;
        private float r = 0;
        private CornerCenterPath cornerCenterPath;
        private float diagSize = 0;
        public CornerCenterBall() {
            diagSize = -(float)(Math.sqrt(Math.pow(h/3,2)+Math.pow(w/3,2)));
            cornerCenterPath = new CornerCenterPath();
        }
        public void draw(Canvas canvas) {
            cornerCenterPath.draw(canvas,diagSize);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.BLUE);
            canvas.save();
            canvas.translate(w/2,h/2);
            canvas.drawRect(new RectF(-w/3,-h/3,w/3,h/3),paint);
            for(int i=0;i<4;i++) {
                canvas.save();
                canvas.rotate(i*90-45);
                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(Color.BLUE);
                canvas.drawCircle(0,diagSize,w/15,paint);
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(Color.WHITE);
                canvas.drawCircle(0,diagSize,w/15,paint);
                canvas.restore();
            }
            canvas.restore();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.BLUE);
            canvas.save();
            canvas.translate(w/2,h/2);
            canvas.rotate(index*90-45);
            canvas.drawCircle(0,r,w/15,paint);
            canvas.restore();
        }
        public void update(float factor) {
            r = diagSize*factor;
        }
        public void incrementIndex() {
            index++;
            if(index%4 == 0) {
                index = 0;
            }
        }
    }
    private class AnimationHandler extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener{
        private ValueAnimator startAnim = ValueAnimator.ofFloat(0,1),endAnim = ValueAnimator.ofFloat(1,0);
        private int dir = 0;
        private boolean isAnimating = false;
        public AnimationHandler() {
            startAnim.setDuration(500);
            endAnim.setDuration(500);
            startAnim.addListener(this);
            endAnim.addListener(this);
            startAnim.addUpdateListener(this);
            endAnim.addUpdateListener(this);
        }
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if(isAnimating) {
                update((float)valueAnimator.getAnimatedValue());
            }
        }
        public void onAnimationEnd(Animator animator) {
            if(isAnimating) {
                dir = dir == 0?1:0;
                isAnimating = false;
                if(dir == 0 && cornerCenterBall != null) {
                    cornerCenterBall.incrementIndex();
                }
            }
        }
        public void start() {
            if(!isAnimating) {
                if(dir == 0) {
                    startAnim.start();
                }
                else {
                    endAnim.start();
                }
                isAnimating = true;
            }
        }
    }
    public static void create(Activity activity) {
        CornerCenterBallView cornerCenterBallView = new CornerCenterBallView(activity);
        Point size = DimensionsUtil.getDeviceDimension(activity);
        activity.addContentView(cornerCenterBallView,new ViewGroup.LayoutParams(size.x,size.x));
    }
    private class CornerCenterPath {
        public void draw(Canvas canvas,float y) {
            paint.setColor(Color.BLUE);
            paint.setStyle(Paint.Style.STROKE);
            for(int i=0;i<4;i++) {
                canvas.save();
                canvas.rotate(i*90-45);
                canvas.drawLine(0,0,0,y,paint);
                canvas.restore();
            }
            canvas.drawCircle(0,0,w/15,paint);
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(0,0,w/15,paint);
        }
    }
}
