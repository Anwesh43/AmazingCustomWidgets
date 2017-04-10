package com.anwesome.ui.mutebutton;

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
 * Created by anweshmishra on 11/04/17.
 */
public class MuteButton {
    private Activity activity;
    private MuteButtonView muteButtonView;
    private MuteButtonShape muteButtonShape;
    public MuteButton(Activity activity) {
        this.activity = activity;
    }
    public void show() {
        if(muteButtonView == null) {
            muteButtonView = new MuteButtonView(activity);
            Point size = DimensionsUtil.getDeviceDimension(activity);
            int w = size.x;
            activity.addContentView(muteButtonView,new ViewGroup.LayoutParams(w/2,w/2));
            muteButtonShape = new MuteButtonShape(w/4,w/4,w/4);
        }
    }
    private class MuteButtonView extends View {
        private boolean isAnimated = false;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public MuteButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            muteButtonShape.draw(canvas,paint);
            if(isAnimated) {
                muteButtonShape.update();
                if(muteButtonShape.stop()) {
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
                muteButtonShape.handleTap();
                isAnimated = true;
                postInvalidate();
            }
            return true;
        }
    }
}
