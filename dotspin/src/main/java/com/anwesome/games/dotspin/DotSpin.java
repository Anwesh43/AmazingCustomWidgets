package com.anwesome.games.dotspin;

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
 * Created by anweshmishra on 16/03/17.
 */
public class DotSpin {
    private Activity activity;
    private DotSpinButton dotSpinButton = DotSpinButton.getInstance();
    private DotSpinView dotSpinView;
    public DotSpin(Activity activity) {
        this.activity = activity;
    }
    public void show(int x,int y) {
        if(dotSpinButton == null) {
            Point size = DimensionsUtil.getDeviceDimension(activity);
            int w = size.x/3;
            dotSpinButton.setDimensions(w/2,w/2,w/3);
            dotSpinView = new DotSpinView(activity);
            activity.addContentView(dotSpinView,new ViewGroup.LayoutParams(w,w));
        }
        dotSpinView.setX(x);
        dotSpinView.setY(y);
    }
    private class DotSpinView extends View {
        private boolean isAnimated = false;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public DotSpinView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            dotSpinButton.draw(canvas,paint);
            if(isAnimated) {
                dotSpinButton.update();
                if(dotSpinButton.stopped()) {
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
                dotSpinButton.startMoving();
                isAnimated = true;
                postInvalidate();
            }
            return true;
        }
    }
}
