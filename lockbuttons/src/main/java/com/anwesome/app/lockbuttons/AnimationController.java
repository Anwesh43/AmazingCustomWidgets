package com.anwesome.app.lockbuttons;

import android.view.View;

/**
 * Created by anweshmishra on 25/02/17.
 */
public class AnimationController {
    private boolean animated = false;
    private Lock lock;
    private boolean lockOpened = false;
    private LockKey lockKey;
    private View view;
    private AnimationStore animationStore = new AnimationStore();
    public AnimationController(Lock lock, LockKey lockKey, View view) {
        this.lock = lock;
        this.lockKey = lockKey;
        this.view = view;
    }
    public boolean isAnimated(){
        return animated;
    }
    public void setAnimated(boolean animated) {
        this.animated = animated;
    }
    public void startAnimating() {
        if(lockOpened) {
            startClosing();
        }
        else {
            startOpening();
        }
    }
    private void startOpening() {
        lockKey.open();
    }
    private void startClosing() {
        lockKey.close();
    }
    public void animate() {
        if(animated) {
            update();
            try {
                Thread.sleep(50);
                view.invalidate();
            }
            catch (Exception ex) {

            }
        }
    }
    public void update(){
        switch(animationStore.getMode()) {
            case 0:
                lockKey.update();
                if(lockKey.isStopped()) {
                    lock.open();
                    animationStore.setMode(1);
                }
                break;
            case 1:
                lock.update();
                if(lock.isStop()) {
                    animationStore.setMode(2);
                    animated = false;
                    lockOpened = true;
                }
                break;
            case 2:
                lockKey.update();
                if(lockKey.isStopped()) {
                    animationStore.setMode(3);
                    lock.close();
                }
                break;
            case 3:
                lock.update();
                if(lock.isStop()) {
                    animationStore.setMode(0);
                    animated = false;
                    lockOpened = false;
                }
                break;
        }
    }
}
