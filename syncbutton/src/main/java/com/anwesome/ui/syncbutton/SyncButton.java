package com.anwesome.ui.syncbutton;

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
 * Created by anweshmishra on 02/04/17.
 */
public class SyncButton {
    private Activity activity;
    private SyncShape syncShape;
    private SyncButtonView syncButtonView;
    public SyncButton(Activity activity) {
        this.activity = activity;
    }
    public void show(int x,int y) {
        if(syncButtonView == null) {
            syncButtonView = new SyncButtonView(activity);
            Point dimension = DimensionsUtil.getDeviceDimension(activity);
            int w = dimension.x;
            activity.addContentView(syncButtonView,new ViewGroup.LayoutParams(w/3,w/3));
            syncShape = new SyncShape(w/6,w/6,w/12);
        }
        syncButtonView.setX(x);
        syncButtonView.setY(y);
    }
    private class SyncButtonView extends View {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private boolean isAnimated = false;
        public SyncButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            if(syncShape!=null) {
                syncShape.draw(canvas,paint);
                if(isAnimated) {
                    try {
                        syncShape.update();
                        Thread.sleep(50);
                        invalidate();
                    }
                    catch (Exception ex) {

                    }
                }
            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(!isAnimated && event.getAction() == MotionEvent.ACTION_DOWN && syncShape!=null) {
                syncShape.startMoving();
                isAnimated = true;
                postInvalidate();
            }
            return true;
        }
    }
}
