package com.anwesome.app.beanbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.*;
/**
 * Created by anweshmishra on 18/02/17.
 */
public class BeanButton {
    private Activity activity;
    private BeanButtonView beanButtonView;
    public BeanButton(Activity activity) {
        this.activity = activity;
    }
    public void show(int x,int y) {
        if(beanButtonView == null) {
            beanButtonView = new BeanButtonView(activity);
            activity.addContentView(beanButtonView,new ViewGroup.LayoutParams(200,200));
        }
        beanButtonView.setX(x);
        beanButtonView.setY(y);
    }
    private class BeanButtonView extends View {
        private boolean isAnimated = false;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private int mode = 0;
        private float deg = 0,rot = 0,fx=0,dir = 0;
        private int l[] = {1,-1};
        public BeanButtonView(Context context) {
            super(context);
            paint.setColor(Color.parseColor("#b71c1c"));
        }
        public void onDraw(Canvas canvas) {
            int w = canvas.getWidth(),h = canvas.getHeight();
            int r = Math.min(w,h)/4;
            for(int i=0;i<2;i++) {
                canvas.save();
                canvas.translate(w/2,h/2);
                canvas.rotate(deg);
                canvas.scale(1,l[i]);
                canvas.drawArc(new RectF(-r,fx-r,r,fx+r),0,180,true,paint);
                canvas.restore();
            }
            if(isAnimated) {
                update();
                try {
                    Thread.sleep(50);
                    invalidate();
                } catch (Exception ex) {

                }
            }
        }
        private void update() {
            switch(mode) {
                case 0:
                    fx+=5;
                    if(fx>=20) {
                        mode = 1;
                    }
                    break;
                case 1:
                    deg+=20;
                    if(deg%180 == 0) {
                        mode = 0;
                        isAnimated = false;
                    }
                    break;
                default:
                    break;
            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated) {
                isAnimated = true;
                postInvalidate();
            }
            return true;
        }
    }
}
