package com.anwesome.demos.pinbar;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.view.*;

import com.anwesome.ui.dimensionsutil.DimensionsUtil;

/**
 * Created by anweshmishra on 29/03/17.
 */
public class PinBarButton  {
    private Activity activity;
    private PinBar pinBar;
    private PinBarButtonView pinBarButtonView;
    public PinBarButton(Activity activity) {
        this.activity = activity;
    }
    public void show(int x,int y) {
        if(pinBarButtonView == null) {
            pinBarButtonView = new PinBarButtonView(activity);
            Point size = DimensionsUtil.getDeviceDimension(activity);
            int w = size.x;
            activity.addContentView(pinBarButtonView,new ViewGroup.LayoutParams(w/2,w/2));
            pinBar = PinBar.getInstance(w/4,w/4,w/4,w/4,5);
        }
        pinBarButtonView.setX(x);
        pinBarButtonView.setY(y);
    }
    private class PinBarButtonView extends View {
        private boolean isAnimated = false;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public PinBarButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            if(pinBar!=null) {
                pinBar.draw(canvas,paint);
                if (isAnimated) {
                    pinBar.update();
                    if(pinBar.stopped()) {
                        isAnimated = false;
                    }
                    try {
                        Thread.sleep(50);
                        invalidate();
                    } catch (Exception ex) {

                    }
                }
            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated && pinBar!=null) {
                pinBar.startMoving();
                isAnimated = true;
                postInvalidate();
            }
            return true;
        }
    }
}
