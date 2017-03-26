package com.anwesome.demos.playbackbutton;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 27/03/17.
 */
public class PlaybackStateController {
    private boolean isAnimated = false;
    private View view;
    private PlaybackButtonShape playbackButtonShape;
    public void init(View view,int w, int h) {
        this.playbackButtonShape = PlaybackButtonShape.getInstance(w/2,h/2,w/3);
        this.view = view;
    }
    public void render(Canvas canvas, Paint paint) {
        if(this.playbackButtonShape!=null && view!=null) {
            playbackButtonShape.draw(canvas,paint);
        }
        if(isAnimated) {
            playbackButtonShape.update();
            if(playbackButtonShape.stopped()) {
                isAnimated = false;
            }
            try {
                Thread.sleep(50);
                view.invalidate();
            }
            catch (Exception ex) {

            }
        }
    }
    public void handleTap(MotionEvent event) {
        if(!isAnimated && event.getAction() == MotionEvent.ACTION_DOWN && view!=null && playbackButtonShape!=null) {
            playbackButtonShape.start();
            isAnimated = true;
            view.postInvalidate();
        }
    }

}
