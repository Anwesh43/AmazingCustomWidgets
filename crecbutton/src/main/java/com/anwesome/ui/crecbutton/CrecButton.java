package com.anwesome.ui.crecbutton;

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
 * Created by anweshmishra on 21/04/17.
 */
public class CrecButton {
    private Activity activity;
    private CrecButtonShape crecButtonShape = new CrecButtonShape();
    private CrecButtonView crecButtonView;
    private AnimationController animationController;
    private OnClickListener onClickListener;
    public void setOnClickListener(OnClickListener onClickListener) {
        crecButtonShape.setOnClickListener(onClickListener);
    }
    public CrecButton(Activity activity) {
        this.activity = activity;
    }
    public void show() {
        if(crecButtonView == null) {
            crecButtonView = new CrecButtonView(activity);
            Point dimension = DimensionsUtil.getDeviceDimension(activity);
            int w = dimension.x;
            activity.addContentView(crecButtonView,new ViewGroup.LayoutParams(w/2,w/2));
            animationController = new AnimationController(crecButtonView,crecButtonShape);
        }
    }
    private class CrecButtonView extends View {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public CrecButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            crecButtonShape.draw(canvas,paint,canvas.getWidth()/2);
            animationController.animate();
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                animationController.startAnimating();
            }
            return true;
        }
    }
}
