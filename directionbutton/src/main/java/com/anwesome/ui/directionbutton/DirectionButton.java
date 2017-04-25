package com.anwesome.ui.directionbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import com.anwesome.ui.directionbutton.controller.AnimationController;

/**
 * Created by anweshmishra on 25/04/17.
 */
public class DirectionButton {
    private Activity activity;
    private AnimationController animationController;
    private DirectionButtonShape directionButtonShape = new DirectionButtonShape();
    public DirectionButton(Activity activity) {
        this.activity = activity;
    }
    public void show() {

    }
    private class DirectionButtonView extends View {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public DirectionButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            directionButtonShape.draw(canvas,paint,canvas.getWidth()/2);
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
