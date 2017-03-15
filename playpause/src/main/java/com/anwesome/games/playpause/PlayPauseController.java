package com.anwesome.games.playpause;

import android.graphics.*;

/**
 * Created by anweshmishra on 15/03/17.
 */
public class PlayPauseController {
    private float dir = -1;
    private int mode = 0;
    private PlayButton playButton = new PlayButton();
    private PauseButton pauseButton = new PauseButton();
    private boolean stopped = false;
    public void setDimensions(float x,float y,float w,float h) {
        playButton.setDimensions(x,y,w,h);
        pauseButton.setDimensions(x,y,w,h);
    }
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.parseColor("#424242"));
        playButton.draw(canvas,paint);
        pauseButton.draw(canvas,paint);
    }
    public void update() {
       switch (mode) {
           case 0:
               playButton.update();
               if(playButton.stopped()) {
                   if(dir == 1) {
                       pauseButton.startMoving(1);
                       mode = 1;
                   }
                   else {
                       stopped = true;
                   }
               }
               break;
           case 1:
               pauseButton.update();
               if(pauseButton.stopped()) {
                   if(dir == 1) {
                       stopped = true;
                   }
                   else {
                       mode = 0;
                       playButton.startMoving(1);
                   }
               }
               break;

           default:
               break;
       }
    }
    public void startMoving() {
        stopped = false;
        dir*=-1;
        if(dir == 1) {
            playButton.startMoving(-1);
        }
        else {
            pauseButton.startMoving(-1);
        }
    }
    public boolean isStopped() {
        return stopped;
    }
}
