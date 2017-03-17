package com.anwesome.games.tripathbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.anwesome.ui.dimensionsutil.DimensionsUtil;

/**
 * Created by anweshmishra on 17/03/17.
 */
public class TriPathButton {
    private Activity activity;
    private TriPath triPath;
    private PathFollowingBall pathFollowingBall;
    private TriPathButtonView triPathButtonView;
    public TriPathButton(Activity activity) {
        this.activity = activity;
    }
    public void show(int x,int y) {
        if(triPathButtonView==null) {
            triPathButtonView = new TriPathButtonView(activity);
            Point size = DimensionsUtil.getDeviceDimension(activity);
            triPath = new TriPath(size.x/4,size.x/4,size.x/5);
            pathFollowingBall = new PathFollowingBall(triPath);
            activity.addContentView(triPathButtonView,new ViewGroup.LayoutParams(size.x/2,size.x/2));
        }
        triPathButtonView.setX(x);
        triPathButtonView.setY(y);
    }
    private class TriPathButtonView extends View {
        private boolean isAnimated = false;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public TriPathButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            triPath.draw(canvas,paint);
            pathFollowingBall.draw(canvas,paint);
            if(isAnimated) {
                if(pathFollowingBall!=null) {
                    pathFollowingBall.update();
                    if(pathFollowingBall.stopped()) {
                        isAnimated = false;
                    }
                }
                try {
                    Thread.sleep(50);
                    invalidate();
                }
                catch(Exception ex) {

                }
            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated) {
                pathFollowingBall.startMoving();
                isAnimated = true;
                postInvalidate();
            }
            return true;
        }

    }
}
