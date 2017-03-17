package com.anwesome.games.hamburgbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.anwesome.ui.dimensionsutil.DimensionsUtil;

/**
 * Created by anweshmishra on 18/03/17.
 */
public class HamburgerButton {
    private Activity activity;
    private HamburgIcon hamburgIcon;
    private HamburgerButtonView view;
    public HamburgerButton(Activity activity) {
        this.activity = activity;
    }
    public void show(int x,int y) {
        if(view == null) {
            view = new HamburgerButtonView(activity);
            Point size = DimensionsUtil.getDeviceDimension(activity);
            hamburgIcon = HamburgIcon.getInstance(size.x/4,size.x/4,size.x/8);
            activity.addContentView(view,new ViewGroup.LayoutParams(size.x/2,size.x/2));
        }
        view.setX(x);
        view.setY(y);
    }
    private class HamburgerButtonView extends View {
        private boolean isAnimated = false;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public HamburgerButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            hamburgIcon.draw(canvas,paint);
            if(isAnimated) {
                hamburgIcon.update();
                if(hamburgIcon.isStopped()) {
                    isAnimated = false;
                }
                try {
                    Thread.sleep(50);
                    invalidate();
                }
                catch (Exception ex) {

                }
            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated) {
                hamburgIcon.handleTap();
                isAnimated = true;
                postInvalidate();
            }
            return true;
        }
    }
}
