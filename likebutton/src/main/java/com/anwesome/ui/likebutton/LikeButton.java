package com.anwesome.ui.likebutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 07/04/17.
 */
public class LikeButton  {
    private Activity activity;
    private LikeShape likeShape;
    private LikeButtonView likeButtonView;
    public LikeButton(Activity activity) {
        this.activity = activity;
    }
    public void show() {
        if(likeButtonView == null) {
            likeButtonView = new LikeButtonView(activity);
        }
    }
    private class LikeButtonView extends View {
        private boolean isAnimated = false;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public LikeButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            if(likeShape!=null) {
                likeShape.draw(canvas,paint);
                if (isAnimated) {
                    likeShape.update();
                    if(likeShape.stop()) {
                        isAnimated = false;
                    }
                    try {
                        Thread.sleep(50);
                        invalidate();
                    } catch (Exception ex) {

                    }
                }
            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(!isAnimated && likeShape!=null && event.getAction() == MotionEvent.ACTION_DOWN) {
                likeShape.startMoving();
                isAnimated = true;
                postInvalidate();
            }
            return  true;
        }
    }
}
