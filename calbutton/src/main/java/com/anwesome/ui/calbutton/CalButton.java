package com.anwesome.ui.calbutton;

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
 * Created by anweshmishra on 05/04/17.
 */
public class CalButton {
    private Activity activity;
    private CalButtonView view;
    private CalButtonShape calButtonShape;
    private View.OnClickListener onClickListener;
    public CalButton(Activity activity) {
        this.activity = activity;
    }
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
    public void show(int x,int y) {
        if(view == null) {
            view = new CalButtonView(activity);
            Point dimension = DimensionsUtil.getDeviceDimension(activity);
            int w = dimension.x;
            activity.addContentView(view,new ViewGroup.LayoutParams(w/2,w/2));
            calButtonShape = CalButtonShape.getInstance(w/4,w/4,w/4);
        }
        view.setX(x);
        view.setY(y);
    }
    private class CalButtonView extends View {
        private boolean isAnimated = false;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public CalButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            calButtonShape.draw(canvas,paint);
            if(isAnimated) {
                calButtonShape.update();
                if(calButtonShape.stop()) {
                    isAnimated = false;
                    if(onClickListener != null) {
                        onClickListener.onClick(this);
                    }
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
                calButtonShape.handleTap();
                isAnimated = true;
                postInvalidate();
            }
            return true;
        }
    }
}
