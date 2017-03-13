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
    private OnOpenListener onOpenListener;
    private ZoomShapeButtonView zoomShapeButtonView;
    public ZoomShapeButton(Activity activity) {
       this.activity = activity;
    }
    public void show(int x,int y) {
        if(zoomShapeButtonView == null){
            zoomShapeButtonView = new ZoomShapeButtonView(activity);
            if(onOpenListener!=null) {
                zoomShapeButtonView.setOnOpenListener();
            }
            activity.addContentView(zoomShapeButtonView,new ViewGroup.LayoutParams(300,300));
        }
        zoomShapeButtonView.setX(x);
        zoomShapeButtonView.setY(y);
    }
    public void setOnOpenListener(OnOpenListener onOpenListener) {
        this.onOpenListener = onOpenListener;
        if(zoomShapeButtonView!=null) {
            zoomShapeButtonView.setOnOpenListener();
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
        public void setOnOpenListener() {
            if(zoomShape!=null) {
                zoomShape.setOnOpenListener(onOpenListener);
            }
        }
        public void onDraw(Canvas canvas) {
            if(time == 0) {
                float w = canvas.getWidth(),h = canvas.getHeight();
                zoomShape = new ZoomShape(w,h);
                if(onOpenListener!=null) {
                    zoomShape.setOnOpenListener(onOpenListener);
                }
            }
            zoomShape.draw(canvas,paint);
            time++;
            if(isAnimated) {
                zoomShape.update();
                if(zoomShape.stopped()) {
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
                zoomShape.handleTap();
                isAnimated = true;
                postInvalidate();
            }
            return true;
        }
    }
}
