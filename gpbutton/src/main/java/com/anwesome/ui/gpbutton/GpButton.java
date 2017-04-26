package com.anwesome.ui.gpbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 26/04/17.
 */
public class GpButton {
    private Activity activity;
    private GpButtonShape gpButtonShape = new GpButtonShape();
    private AnimationController animationController;
    public GpButton(Activity activity) {
        this.activity = activity;
    }
    public void show() {

    }
    private class GpButtonView extends View {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public GpButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            gpButtonShape.draw(canvas,paint,canvas.getWidth()/2);
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
