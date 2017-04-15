package com.anwesome.ui.trashbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import com.anwesome.ui.trashbutton.controller.AnimationController;

/**
 * Created by anweshmishra on 16/04/17.
 */
public class TrashButton {
    private Activity activity;
    private TrashButtonShape trashButtonShape;
    private TrashButtonView trashButtonView;
    private AnimationController animationController;
    public TrashButton(Activity activity) {
        this.activity = activity;
    }
    public void show(int x,int y) {
        if(trashButtonView == null) {
            trashButtonView = new TrashButtonView(activity);
            trashButtonShape = new TrashButtonShape();
            animationController = new AnimationController(trashButtonView,trashButtonShape);
        }
    }
    private class TrashButtonView extends View {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public TrashButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            trashButtonShape.draw(canvas,paint,canvas.getWidth()/2);
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
