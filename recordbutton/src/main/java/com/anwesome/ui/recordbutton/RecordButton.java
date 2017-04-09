package com.anwesome.ui.recordbutton;

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
 * Created by anweshmishra on 10/04/17.
 */
public class RecordButton {
    private Activity activity;
    private RecordButtonView recordButtonView;
    private RecordShape recordShape;
    public RecordButton(Activity activity) {
        this.activity = activity;
    }
    public void show(int x,int y) {
        if(recordButtonView  == null) {
            recordButtonView = new RecordButtonView(activity);
            Point dimension = DimensionsUtil.getDeviceDimension(activity);
            int w = dimension.x;
            activity.addContentView(recordButtonView,new ViewGroup.LayoutParams(w/2,w/2));
            recordShape = new RecordShape(w/4,w/4,w/4);
        }
        recordButtonView.setX(x);
        recordButtonView.setY(y);
    }
    private class RecordButtonView extends View {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private boolean isAnimated = false;
        public RecordButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            if(recordShape!=null) {
                recordShape.draw(canvas, paint);
                if (isAnimated) {
                    recordShape.update();
                    if (recordShape.stop()) {
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
            if(event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated && recordShape!=null) {
                recordShape.startMoving();
                isAnimated = true;
                postInvalidate();
            }
            return true;
        }
    }
}
