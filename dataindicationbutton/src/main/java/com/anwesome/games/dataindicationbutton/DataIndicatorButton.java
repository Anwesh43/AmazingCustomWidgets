package com.anwesome.games.dataindicationbutton;

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
 * Created by anweshmishra on 21/03/17.
 */
public class DataIndicatorButton {
    private Activity activity;
    private DataIndicatorView dataIndicatorView;
    private DataIndicator dataIndicator;
    public DataIndicatorButton(Activity activity) {
        this.activity = activity;
    }
    public void show(DataIndicationType dataIndicationType,int x,int y) {
        if(dataIndicatorView == null) {
            dataIndicatorView = new DataIndicatorView(activity);
            Point size = DimensionsUtil.getDeviceDimension(activity);
            activity.addContentView(dataIndicatorView,new ViewGroup.LayoutParams(size.x/3,size.x/3));
            dataIndicator = new DataIndicator(dataIndicationType,size.x/6,size.x/6,size.x/8);
        }
        dataIndicatorView.setX(x);
        dataIndicatorView.setY(y);
    }
    private class DataIndicatorView extends View {
        private boolean isAnimated = false;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public DataIndicatorView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            dataIndicator.draw(canvas,paint);
            if(isAnimated) {
                dataIndicator.update();
                if(dataIndicator.stopped()) {
                    isAnimated = false;
                }
                try {
                    Thread.sleep(50);
                    invalidate();
                } catch (Exception ex) {

                }
            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated) {
                dataIndicator.startMoving();
                isAnimated = true;
                postInvalidate();
            }
            return true;
        }
    }
}
