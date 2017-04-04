package com.anwesome.ui.emergencybutton;

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
 * Created by anweshmishra on 04/04/17.
 */
public class EmergencyButton {
    private Activity activity;
    private EmergencyButtonShape emergencyButtonShape;
    private View.OnClickListener onClickListener;
    private EmergencyButtonShapeView emergencyButtonShapeView;
    public EmergencyButton(Activity activity) {
        this.activity = activity;
    }
    public void show() {
        if(emergencyButtonShapeView == null) {
            emergencyButtonShapeView = new EmergencyButtonShapeView(activity);
            Point size = DimensionsUtil.getDeviceDimension(activity);
            int w = size.x;
            activity.addContentView(emergencyButtonShapeView,new ViewGroup.LayoutParams(w/2,w/2));
            emergencyButtonShape = new EmergencyButtonShape(w/4,w/4,w/4);
        }
    }
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
    private class EmergencyButtonShapeView extends View {
        private boolean isAnimated = false;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public EmergencyButtonShapeView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            emergencyButtonShape.draw(canvas,paint);
            if(isAnimated) {
                try {
                    emergencyButtonShape.update();
                    if(emergencyButtonShape.stop()) {
                        isAnimated = false;
                        if(onClickListener!=null) {
                            onClickListener.onClick(this);
                        }
                    }
                    Thread.sleep(50);
                    invalidate();
                }
                catch (Exception ex) {

                }
            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated && emergencyButtonShape!=null) {
                emergencyButtonShape.startMoving();
                isAnimated = true;
                postInvalidate();
            }
            return true;
        }
    }
}
