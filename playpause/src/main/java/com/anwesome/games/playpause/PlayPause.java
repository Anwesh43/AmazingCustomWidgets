package com.anwesome.games.playpause;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

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
    public void show() {
        if(playPauseView == null) {
            playPauseView = new PlayPauseView(activity);
        }
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
