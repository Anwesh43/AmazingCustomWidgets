package com.anwesome.ui.footiepitchbutton;

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
 * Created by anweshmishra on 06/04/17.
 */
public class FootiePitchButton {
    private Activity activity;
    private FootiePitch footiePitch;
    private FootiePitchView footiePitchView;
    public FootiePitchButton(Activity activity) {
        this.activity = activity;
    }
    public void show(int x,int y) {
        if(footiePitchView == null) {
            footiePitchView = new FootiePitchView(activity);
            Point dimension = DimensionsUtil.getDeviceDimension(activity);
            int w = dimension.x;
            activity.addContentView(footiePitchView,new ViewGroup.LayoutParams(w,w));
            footiePitch = new FootiePitch(w/4,w/4,w/4);
        }
        footiePitchView.setX(x);
        footiePitchView.setY(y);
    }
    private class FootiePitchView extends View {
        private boolean isAnimated = false;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public FootiePitchView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            footiePitch.draw(canvas,paint);
            if(isAnimated) {
                try {
                    footiePitch.update();
                    if(footiePitch.stop()) {
                        isAnimated = false;
                    }
                    Thread.sleep(50);
                    invalidate();
                }
                catch (Exception ex) {

                }
            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated) {
                footiePitch.startMoving();
                isAnimated = true;
                postInvalidate();
            }
            return true;
        }
    }
}
