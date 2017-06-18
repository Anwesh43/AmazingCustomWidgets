package com.anwesome.ui.sharerotbutton;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
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

/**
 * Created by anweshmishra on 18/06/17.
 */

public class ShareRotButton  {
    private static int color = Color.parseColor("#00838F");
    public static void create(Activity activity) {
        ShareRotButtonView shareRotButtonView = new ShareRotButtonView(activity);
        Point size = DimensionsUtil.getDeviceDimension(activity);
        int w = size.x;
        activity.addContentView(shareRotButtonView,new ViewGroup.LayoutParams(w,w));
    }
    private static class ShareRotButtonView extends View {
        private ShareRotShape shareRotShape;
        private AnimationHandler animationHandler;
        private int time = 0,w,h;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public ShareRotButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            if(time == 0) {
                w = canvas.getWidth();
                h = canvas.getHeight();
                shareRotShape = new ShareRotShape();
                animationHandler = new AnimationHandler(this);
            }
            shareRotShape.draw(canvas,paint,w/2,h/2,2*w/5);
            time++;
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN && animationHandler!=null) {
                animationHandler.start();
            }
            return true;
        }
        public void update(float factor) {
            if(shareRotShape != null) {
                shareRotShape.update(factor);
            }
            postInvalidate();
        }
        public void doOnAnimEnd() {
            if(shareRotShape != null) {
                shareRotShape.incrementDeg();
            }
        }
    }
    private static class ShareRotShape {
        private float initDeg = 0,deg = 0;
        public void incrementDeg() {
            this.initDeg += 90;
            this.initDeg %= 360;
        }
        public void draw(Canvas canvas, Paint paint,float x,float y,float size) {
            canvas.save();
            canvas.translate(x,y);
            canvas.rotate(deg);
            Path path = new Path();
            path.moveTo(size/2,0);
            drawArc(path,size/2,2*size/5,1);
            drawArrow(path,size/2,-size/5,11*size/30,7*size/30);
            drawArc(path,size/2,size/3,-1);
            paint.setColor(color);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawPath(path,paint);
            canvas.restore();
        }
        private void drawArrow(Path path,float uy,float mx,float my,float ly) {
            path.lineTo(0,uy);
            path.lineTo(mx,my);
            path.lineTo(0,ly);
        }
        private void drawArc(Path path,float a,float b,int dir) {
            int s = 0,e = 90;
            if(dir == -1) {
                s = 90;
                e = 0;
            }
            for(int i=s;;i+=dir) {
                float x = (float)(a*Math.cos(i*Math.PI/180)),y = (float)(b*Math.sin(i*Math.PI/180));
                path.lineTo(x,y);
                if(i == e){
                    break;
                }
            }
        }
        public void update(float factor) {
            deg = initDeg + 90*factor;
        }
    }
    private static class AnimationHandler extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener {
        private ValueAnimator startAnim = ValueAnimator.ofFloat(0,1);
        private ShareRotButtonView shareRotButtonView;
        private boolean isAnimated = false;
        public void onAnimationEnd(Animator animator) {
            if(isAnimated && shareRotButtonView != null) {
                shareRotButtonView.doOnAnimEnd();
                isAnimated = false;
            }
        }
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if(shareRotButtonView != null && isAnimated) {
                shareRotButtonView.update((float)valueAnimator.getAnimatedValue());
            }
        }
        public AnimationHandler(ShareRotButtonView shareRotButtonView) {
            this.shareRotButtonView = shareRotButtonView;
            startAnim.setDuration(500);
            startAnim.addListener(this);
            startAnim.addUpdateListener(this);
        }
        public void start() {
            if(!isAnimated) {
                startAnim.start();
                isAnimated = true;
            }
        }
    }
}
