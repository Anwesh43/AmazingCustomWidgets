package com.anwesome.games.playpause;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.view.*;
import com.anwesome.ui.dimensionsutil.DimensionsUtil;

/**
 * Created by anweshmishra on 15/03/17.
 */
public class PlayPause {
    private Activity activity;
    private PlayPauseView playPauseView;
    private PlayPauseController playPauseController = new PlayPauseController();
    public PlayPause(Activity activity) {
        this.activity = activity;
    }
    public void show(int x,int y) {
        if(playPauseView == null) {
            playPauseView = new PlayPauseView(activity);
            Point size = DimensionsUtil.getDeviceDimension(activity);
            playPauseController.setDimensions(size.x/4,size.x/4,size.x/4,size.x/4);
            activity.addContentView(playPauseView,new ViewGroup.LayoutParams(size.x/2,size.x/2));
        }
        playPauseView.setX(x);
        playPauseView.setY(y);
    }
    private class PlayPauseView extends View {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private boolean isAnimated = false;
        public PlayPauseView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            playPauseController.draw(canvas,paint);
            if(isAnimated) {
                try {
                    playPauseController.update();
                    if(playPauseController.isStopped()) {
                        isAnimated = false;
                    }
                    Thread.sleep(50);
                    invalidate();
                }
                catch (Exception ex) {

                }
            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated) {
                playPauseController.startMoving();
                isAnimated = true;
                postInvalidate();
            }
            return true;
        }
    }
}
