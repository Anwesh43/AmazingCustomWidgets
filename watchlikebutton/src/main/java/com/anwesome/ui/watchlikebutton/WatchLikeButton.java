package com.anwesome.ui.watchlikebutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by anweshmishra on 19/04/17.
 */
public class WatchLikeButton {
    private Activity activity;
    private WatchLikeView watchLikeView;
    private AnimationController animationController;
    private WatchLikeShape watchLikeShape = new WatchLikeShape();
    public WatchLikeButton(Activity activity) {
        this.activity = activity;
    }
    public void setX(float x) {
        if(watchLikeView != null) {
            watchLikeView.setX(x);
        }
        else {
            Toast.makeText(activity, "Please use setX after show method", Toast.LENGTH_SHORT).show();
        }
    }
    public void setY(float y) {
        if(watchLikeView != null) {
            watchLikeView.setY(y);
        }
        else {
            Toast.makeText(activity, "Please use setY after show method", Toast.LENGTH_SHORT).show();
        }
    }
    public void show() {
        if(watchLikeView == null) {
            watchLikeView = new WatchLikeView(activity);
            activity.addContentView(watchLikeView,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            animationController = new AnimationController(watchLikeView,watchLikeShape);
        }
    }
    private class WatchLikeView extends View {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public WatchLikeView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            watchLikeShape.draw(canvas,paint,canvas.getWidth()/2);
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
