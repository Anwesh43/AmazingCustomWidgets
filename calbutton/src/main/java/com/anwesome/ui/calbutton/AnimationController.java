package com.anwesome.ui.calbutton;

import com.anwesome.games.animationqueue.AnimationQueue;
import com.anwesome.games.animationqueue.ObjectAnimation;

/**
 * Created by anweshmishra on 05/04/17.
 */
public class AnimationController {
    private float deg=0,time = 0;
    private int mode = -1;
    private AnimationQueue animationQueue = new AnimationQueue();
    public float getDeg() {
        return deg;
    }
    public AnimationController() {
        animationQueue.addObjectAnimation(new ObjectAnimation() {
            @Override
            public void animate() {
                deg-=5;
            }
            @Override
            public boolean isDone() {
                return deg<=-15;
            }
        });
        animationQueue.addObjectAnimation(new ObjectAnimation() {
            @Override
            public void animate() {
                time++;
            }

            @Override
            public boolean isDone() {
                return time%10 == 0;
            }
        });
        animationQueue.addObjectAnimation(new ObjectAnimation() {
            @Override
            public void animate() {
                deg+=5;
            }

            @Override
            public boolean isDone() {
                return deg>=0;
            }
        });
    }
    public void startMoving() {
        animationQueue.setDir(1);
    }
    public void animate() {
        animationQueue.execute();
    }
    public boolean stop() {
        return animationQueue.stopped();
    }
}
