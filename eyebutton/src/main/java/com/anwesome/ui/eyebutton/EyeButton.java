package com.anwesome.ui.eyebutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 24/04/17.
 */
public class EyeButton {
    private Activity activity;
    private AnimationController animationController;
    private EyeButtonShape eyeButtonShape = new EyeButtonShape();
    public EyeButton(Activity activity) {
        this.activity = activity;
    }
    public void show() {
        
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
