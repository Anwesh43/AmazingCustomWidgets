package com.anwesome.ui.tictactoefilterview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 22/05/17.
 */
public class TTIFView extends View{
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int time = 0,w,h;
    private Bitmap bitmap;
    private int index = 0;
    private TicTacFilter ticTacFilter;
    private boolean shouldShowFilter = false;
    private AnimationHandler animationHandler;
    public TTIFView(Context context, Bitmap bitmap,int index) {
        super(context);
        this.bitmap = bitmap;
        this.index = index;
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            bitmap = Bitmap.createScaledBitmap(bitmap,w,h,true);
            ticTacFilter = new TicTacFilter();
            animationHandler = new AnimationHandler();
        }
        canvas.drawBitmap(bitmap,0,0,paint);
        if(shouldShowFilter) {
            ticTacFilter.draw(canvas);
        }
    }
    public void update(float factor) {
        ticTacFilter.update(factor);
        postInvalidate();
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            shouldShowFilter = !shouldShowFilter;
            animationHandler.start();
        }
        return true;
    }
    private class TicTacFilter {
        private float scale = 0;
        public void draw(Canvas canvas) {
            canvas.drawColor(Color.parseColor("#99000000"));
            paint.setStyle(Paint.Style.STROKE);
            canvas.save();
            canvas.translate(w/2,h/2);
            canvas.scale(scale,scale);
            if(index%2 == 0) {
                for(int i=0;i<2;i++) {
                    canvas.save();
                    canvas.translate(0, 0);
                    canvas.rotate(45*(2*i-1));
                    Path path = new Path();
                    path.moveTo(0, -h / 2);
                    path.lineTo(0, h / 2);
                    canvas.drawPath(path, paint);
                    canvas.restore();
                }
            }
            else {
                canvas.drawCircle(0,0,w/2,paint);
            }
            canvas.restore();
        }
        public void update(float factor) {
            scale = factor;
        }
    }
    private class AnimationHandler extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener{
        private boolean isAnimating = false;
        private float dir = 0;
        private ValueAnimator addAnim = ValueAnimator.ofFloat(0,1),removeAnim = ValueAnimator.ofFloat(1,0);
        public AnimationHandler() {
            addAnim.setDuration(500);
            removeAnim.setDuration(500);
            addAnim.addUpdateListener(this);
            removeAnim.addUpdateListener(this);
            addAnim.addListener(this);
            removeAnim.addListener(this);
        }
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            update((float)valueAnimator.getAnimatedValue());
        }
        public void onAnimationEnd(Animator animator) {
            if(!isAnimating) {
                isAnimating = true;
            }
        }
        public void start() {
            if(!isAnimating) {
                if(dir == 0) {
                    addAnim.start();
                }
                else {
                    removeAnim.start();
                }
                dir = dir==0?1:0;
            }
        }
    }
}
