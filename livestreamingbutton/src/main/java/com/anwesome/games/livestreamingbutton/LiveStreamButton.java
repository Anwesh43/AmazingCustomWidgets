package com.anwesome.games.livestreamingbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.*;

import com.anwesome.ui.dimensionsutil.DimensionsUtil;

/**
 * Created by anweshmishra on 24/03/17.
 */
public class LiveStreamButton {
    private Activity activity;
    private LiveStreamButtonView liveStreamButtonView;
    private LiveStreamingButtonController liveStreamingButtonController;
    public LiveStreamButton(Activity activity) {
        this.activity = activity;
    }
    public void show(int x,int y) {
        if(liveStreamButtonView == null) {
            liveStreamButtonView = new LiveStreamButtonView(activity);
            Point size = DimensionsUtil.getDeviceDimension(activity);
            int w = size.x,h = size.y;
            liveStreamingButtonController = new LiveStreamingButtonController(w/2);
            activity.addContentView(liveStreamButtonView,new ViewGroup.LayoutParams(w/2,w/2));
        }
        liveStreamButtonView.setX(x);
        liveStreamButtonView.setY(y);
    }
    private class LiveStreamButtonView extends View {
        private boolean isAnimated = false;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public LiveStreamButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            liveStreamingButtonController.draw(canvas,paint);
            if(isAnimated) {
                liveStreamingButtonController.update();
                if(liveStreamingButtonController.stopped()) {
                    isAnimated = false;
                }
                try {
                    Thread.sleep(50);
                    invalidate();
                }
                catch (Exception ex) {

                }
            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated) {
                liveStreamingButtonController.startMoving();
                isAnimated = true;
                postInvalidate();
            }
            return  true;
        }
    }
}
