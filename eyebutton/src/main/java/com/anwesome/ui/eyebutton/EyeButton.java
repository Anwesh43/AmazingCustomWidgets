package com.anwesome.ui.eyebutton;

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
 * Created by anweshmishra on 24/04/17.
 */
public class EyeButton {
    private Activity activity;
    private AnimationController animationController;
    private EyeButtonShape eyeButtonShape = new EyeButtonShape();
    private EyeButtonView eyeButtonView;
    public EyeButton(Activity activity) {
        this.activity = activity;
    }
    public void show(int x,int y) {
        if(eyeButtonView == null) {
            eyeButtonView = new EyeButtonView(activity);
            animationController = new AnimationController(eyeButtonView,eyeButtonShape);
            Point dimension = DimensionsUtil.getDeviceDimension(activity);
            int w = dimension.x/2;
            activity.addContentView(eyeButtonView,new ViewGroup.LayoutParams(w,w));
        }
        eyeButtonView.setX(x);
        eyeButtonView.setY(y);
    }
    private class EyeButtonView extends View {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public EyeButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            eyeButtonShape.draw(canvas,paint,canvas.getWidth()/2);
            if(animationController != null) {
                animationController.animate();
            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN && animationController != null) {
                animationController.startAnimating();
            }
            return true;
        }
    }
}
