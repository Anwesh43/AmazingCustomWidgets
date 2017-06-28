package com.anwesome.games.circfourbitmap;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
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

/**
 * Created by anweshmishra on 28/06/17.
 */

public class CircFourBitmapView extends View{
    private int time = 0,w,h,r;
    private Bitmap bitmap;
    private CircFour circFour;
    private AnimationHandler animationHandler;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public CircFourBitmapView(Context context, Bitmap bitmap) {
        super(context);
        this.bitmap = bitmap;
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            int size = 2*Math.min(w,h)/3;
            r = size/2;
            bitmap = Bitmap.createScaledBitmap(bitmap,size,size,true);
            circFour = new CircFour();
            animationHandler = new AnimationHandler();
        }
        canvas.save();
        canvas.translate(w/2,h/2);
        Path path = new Path();
        path.addCircle(0,0,r, Path.Direction.CCW);
        canvas.clipPath(path);
        canvas.drawBitmap(bitmap,-r,-r,paint);
        canvas.restore();
        paint.setStrokeWidth(r/20);
        paint.setColor(Color.parseColor("#0097A7"));
        if(circFour != null) {
            circFour.draw(canvas);
        }
        time++;
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN && animationHandler != null) {
            animationHandler.start();
        }
        return true;
    }
    public void update(float factor) {
        if(circFour != null) {
            circFour.update(factor);
        }
        postInvalidate();
    }
    private class CircFour {
        private float deg = 0;
        public void draw(Canvas canvas) {
            int scales[][] = {{1,1},{-1,1},{-1,-1},{1,-1}};
            paint.setStyle(Paint.Style.STROKE);
            for(int i=0;i<scales.length;i++) {
                canvas.save();
                canvas.translate(w / 2, h / 2);
                canvas.scale(scales[i][0],scales[i][1]);
                canvas.drawArc(new RectF(-r,-r,r,r),0,deg,false,paint);
                canvas.restore();
            }
        }
        public void update(float factor) {
            this.deg = 90*factor;
        }
    }
    private class AnimationHandler extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener {
        private ValueAnimator startAnim = ValueAnimator.ofFloat(0,1),endAnim = ValueAnimator.ofFloat(1,0);
        private boolean isAnimated = false;
        private int state = 0;
        public AnimationHandler() {
            startAnim.setDuration(500);
            endAnim.setDuration(500);
            startAnim.addListener(this);
            endAnim.addListener(this);
            startAnim.addUpdateListener(this);
            endAnim.addUpdateListener(this);
        }
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float factor = (float)valueAnimator.getAnimatedValue();
            update(factor);
        }
        public void onAnimationEnd(Animator animator) {
            if(isAnimated) {
                state = state == 0?1:0;
                isAnimated = false;
            }
        }
        public void start() {
            if(!isAnimated) {
                if(state == 0) {
                    startAnim.start();
                }
                else {
                    endAnim.start();
                }
                isAnimated = true;
            }
        }
    }
    public static void create(Activity activity,Bitmap bitmap) {
        CircFourBitmapView circFourBitmapView = new CircFourBitmapView(activity,bitmap);
        Point size = DimensionsUtil.getDeviceDimension(activity);
        activity.addContentView(circFourBitmapView,new ViewGroup.LayoutParams(size.x,size.x));
    }
}
