package com.anwesome.ui.fullscreenbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.anwesome.ui.dimensionsutil.DimensionsUtil;

/**
 * Created by anweshmishra on 12/04/17.
 */
public class FullScreenButton {
    private Activity activity;
    private FullScreenButtonShape fullScreenButtonShape;
    private FullScreenButtonView fullScreenButtonView;
    private AnimationController animationController;
    public FullScreenButton(Activity activity) {
        this.activity = activity;
    }
    public void show(int x,int y) {
        if(fullScreenButtonView == null) {
            fullScreenButtonView = new FullScreenButtonView(activity);
            Point size = DimensionsUtil.getDeviceDimension(activity);
            int w = size.x;
            activity.addContentView(fullScreenButtonView,new ViewGroup.LayoutParams(w/2,w/2));
            fullScreenButtonShape = new FullScreenButtonShape(w/4,w/4,w/4);
            animationController = new AnimationController(fullScreenButtonView, new AnimationController.ShapeAnimationListener() {
                @Override
                public void animateShape() {
                    fullScreenButtonShape.move();
                }

                @Override
                public void startShapeAnimation() {
                    fullScreenButtonShape.handleTap();
                }
                public boolean stop() {
                    return fullScreenButtonShape.stop();
                }
            });
        }
        fullScreenButtonView.setX(x);
        fullScreenButtonView.setY(y);
    }
    private class FullScreenButtonView extends View {
        private boolean isAnimated = false;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public FullScreenButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            if(fullScreenButtonShape!=null) {
                canvas.drawColor(Color.parseColor("#E0E0E0"));
                fullScreenButtonShape.draw(canvas,paint);
                animationController.animate();
            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            animationController.startAnimating(event);
            return true;
        }
    }
}
