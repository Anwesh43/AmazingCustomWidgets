package com.anwesome.games.zoomshapebutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by anweshmishra on 13/03/17.
 */
public class ZoomShapeButton {
    private Activity activity;
    private ZoomShapeButtonView zoomShapeButtonView;
    public ZoomShapeButton(Activity activity) {
       this.activity = activity;
    }
    public void show() {
        if(zoomShapeButtonView == null){
            zoomShapeButtonView = new ZoomShapeButtonView(activity);
            activity.addContentView(zoomShapeButtonView,new ViewGroup.LayoutParams(300,300));
        }
    }
    private class ZoomShapeButtonView extends View {
        private ZoomShape zoomShape = null;
        private int time = 0;
        private boolean isAnimated = false;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public ZoomShapeButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            if(time == 0) {
                float w = canvas.getWidth(),h = canvas.getHeight();
                zoomShape = new ZoomShape(w,h);
            }
            zoomShape.draw(canvas,paint);
            time++;
            if(isAnimated) {
                zoomShape.draw(canvas,paint);
                try {
                    Thread.sleep(100);
                    invalidate();
                }
                catch (Exception ex) {

                }
            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN && isAnimated) {
                zoomShape.handleTap();
                isAnimated = true;
                postInvalidate();
            }
            return true;
        }
    }
}
