package com.anwesome.games.livestreamingbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.*;

import com.anwesome.ui.dimensionsutil.DimensionsUtil;

/**
 * Created by anweshmishra on 24/03/17.
 */
public class LiveStreamButton {
    private Activity activity;
    private LiveStreamButtonView liveStreamButtonView;
    public LiveStreamButton(Activity activity) {
        this.activity = activity;
    }
    public void show(int x,int y) {
        if(liveStreamButtonView == null) {
            liveStreamButtonView = new LiveStreamButtonView(activity);
            Point size = DimensionsUtil.getDeviceDimension(activity);
            int w = size.x,h = size.y;
            activity.addContentView(liveStreamButtonView,new ViewGroup.LayoutParams(w/2,w/2));
        }
        liveStreamButtonView.setX(x);
        liveStreamButtonView.setY(y);
    }
    private class LiveStreamButtonView extends View {
        private boolean isAnimated = false;
        public LiveStreamButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {

        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated) {
                isAnimated = true;
                postInvalidate();
            }
            return  true;
        }
    }
}
