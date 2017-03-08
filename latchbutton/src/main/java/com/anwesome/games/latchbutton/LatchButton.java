package com.anwesome.games.latchbutton;

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
 * Created by anweshmishra on 09/03/17.
 */
public class LatchButton {
    private Activity activity;
    private LatchButtonView latchButtonView;
    private Latch latch;
    private LatchSelectedListener latchSelectedListener;
    private LatchButton(Activity activity) {
        this.activity = activity;
    }
    public static LatchButton newInstance(Activity activity) {
        return new LatchButton(activity);
    }
    public void setLatchSelectedListener(LatchSelectedListener latchSelectedListener) {
        this.latchSelectedListener = latchSelectedListener;
        if(latch!=null) {
            latch.setLatchSeleectedListener(latchSelectedListener);
        }
    }
    public void show(int x,int y) {
        if(latchButtonView == null) {
            latchButtonView = new LatchButtonView(activity);
            Point size = DimensionsUtil.getDeviceDimension(activity);
            latch = new Latch(2*size.x/3,2*size.x/3);
            if(latchSelectedListener!=null) {
                latch.setLatchSeleectedListener(latchSelectedListener);
            }
            activity.addContentView(latchButtonView,new ViewGroup.LayoutParams(2*size.x/3,2*size.x/3));
        }
        latchButtonView.setX(x);
        latchButtonView.setY(y);
    }
    private class LatchButtonView extends View {
        private boolean isAnimated = false;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public LatchButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            if(latch!=null) {
                latch.draw(canvas,paint);
            }
            if(isAnimated) {
                latch.update();
                if(latch.stopped()) {
                    isAnimated = false;
                }
                try {
                    Thread.sleep(50);
                    invalidate();
                }
                catch(Exception ex) {

                }
            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated) {
                latch.handleTap();
                isAnimated = true;
                postInvalidate();
            }
            return true;
        }
    }
}
