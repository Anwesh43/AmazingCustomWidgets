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
import android.graphics.RectF;
import android.util.Log;
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
    private OnSelectionChangeListener onSelectionChangeListener;
    public void setOnSelectionChangeListener(OnSelectionChangeListener onSelectionChangeListener) {
        this.onSelectionChangeListener = onSelectionChangeListener;
    }
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
        time++;
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
            if(!shouldShowFilter) {
                shouldShowFilter = true;
            }
            animationHandler.start();
        }
        return true;
    }
    private class TicTacFilter {
        private float r=0;
        public void draw(Canvas canvas) {
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.parseColor("#99000000"));
            canvas.drawRect(new RectF(0,0,w,h),paint);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.WHITE);
            paint.setStrokeWidth(canvas.getWidth()/10);
            canvas.save();
            canvas.translate(w/2,h/2);
            //canvas.scale(scale,scale);
            if(index%2 == 0) {
                for(int i=0;i<2;i++) {
                    canvas.save();
                    canvas.translate(0, 0);
                    canvas.rotate(45*(i*2-1));
                    canvas.drawLine(0,-r,0,r,paint);
                    Log.d("r",""+r);
                    //canvas.drawPath(path, paint);
                    canvas.restore();
                }
            }
            else {
                canvas.drawCircle(0,0,r,paint);
            }
            canvas.restore();
        }
        public void update(float factor) {
            Log.d("factor",""+factor);
            this.r = factor;
            Log.d("r",""+this.r);
        }
    }
    private class AnimationHandler extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener{
        private boolean isAnimating = false;
        private float dir = 0;
        private ValueAnimator addAnim,removeAnim;
        public AnimationHandler() {
            addAnim = ValueAnimator.ofFloat(0,w/3);
            removeAnim = ValueAnimator.ofFloat(w/3,0);
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
            if(isAnimating) {
                isAnimating = false;
                if(shouldShowFilter) {
                    if(dir == 0) {
                        if(onSelectionChangeListener!=null) {
                            onSelectionChangeListener.onUnSelect();;
                        }
                        shouldShowFilter = false;
                        postInvalidate();
                    }
                    else {
                        if(onSelectionChangeListener!=null) {
                            onSelectionChangeListener.onSelect();;
                        }
                    }
                }
            }
        }
        public void start() {
            if(!isAnimating) {
                if(dir == 0) {
                    //addAnim.addListener(this);
                    addAnim.start();
                }
                else {
                    //removeAnim.addListener(this);
                    removeAnim.start();
                }
                dir = dir==0?1:0;
                isAnimating = true;
            }
        }
    }
}
