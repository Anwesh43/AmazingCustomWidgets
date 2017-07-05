package com.anwesome.games.midcornerimage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
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
 * Created by anweshmishra on 06/07/17.
 */

public class MidCornerImageView extends View {
    private int time = 0,w,h,size;
    private Bitmap bitmap;
    private MidCornerImage midCornerImage;
    private AnimationHandler animationHandler = new AnimationHandler();
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public MidCornerImageView(Context context,Bitmap bitmap) {
        super(context);
        this.bitmap = bitmap;
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            size = Math.min(w,h)/2;
            bitmap = Bitmap.createScaledBitmap(bitmap,size,size,true);
            midCornerImage = new MidCornerImage();
            paint.setStrokeWidth(size/60);
            paint.setColor(Color.parseColor("#0277BD"));
        }
        canvas.save();
        canvas.translate(w/2,h/2);
        canvas.drawBitmap(bitmap,-size/2,-size/2,paint);
        midCornerImage.draw(canvas);
        canvas.restore();
        time++;
    }
    public void update(float factor) {
        if(midCornerImage != null) {
            midCornerImage.update(factor);
            postInvalidate();
        }
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            animationHandler.start();
        }
        return true;
    }
    private class MidCornerImage {
        private float scale = 0;
        public void draw(Canvas canvas) {
            for(int i=0;i<4;i++) {
                canvas.save();
                canvas.rotate(i*90);
                float x = size/2,y = size/2;
                canvas.save();
                canvas.translate(x,y);
                canvas.drawLine((-size/2)*scale,(-size/2)*scale,0,0,paint);
                canvas.drawArc(new RectF(-size/10,-size/10,size/10,size/10),0,360*scale,true,paint);
                canvas.restore();
                canvas.restore();
            }
        }
        public void update(float factor) {
            scale = factor;
        }
    }
    class AnimationHandler extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener{
        private ValueAnimator openAnim = ValueAnimator.ofFloat(0,1),closeAnim = ValueAnimator.ofFloat(1,0);
        private boolean animated = false;
        private int state = 0;
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if(animated) {
                update((float)valueAnimator.getAnimatedValue());
            }
        }
        public void onAnimationEnd(Animator animator) {
            if(animated) {
                animated = false;
                state = state == 0?1:0;
            }
        }
        public void start() {
            if(!animated) {
                if(state == 0) {
                    openAnim.start();
                }
                else {
                    closeAnim.start();
                }
                animated = true;
            }
        }
        public AnimationHandler() {
            openAnim.setDuration(500);
            closeAnim.setDuration(500);
            openAnim.addUpdateListener(this);
            closeAnim.addUpdateListener(this);
            openAnim.addListener(this);
            closeAnim.addListener(this);
        }
    }
    public static void create(Activity activity,Bitmap bitmap) {
        MidCornerImageView midCornerImageView = new MidCornerImageView(activity,bitmap);
        Point size = DimensionsUtil.getDeviceDimension(activity);
        activity.addContentView(midCornerImageView,new ViewGroup.LayoutParams(size.x,size.x));
    }
}
