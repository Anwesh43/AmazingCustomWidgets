package com.anwesome.games.animationqueue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anweshmishra on 24/03/17.
 */
public class AnimationQueue {
    private float dir = 0;
    private int index = -1;
    private List<ObjectAnimation> objectAnimations = new ArrayList<>();
    public void addObjectAnimation(ObjectAnimation objectAnimation) {
        this.objectAnimations.add(objectAnimation);
    }
    public void setDir(float dir) {
        this.dir = dir;
        index+=dir;
    }
    public void execute() {
        if(index>0 && index<objectAnimations.size()) {
            ObjectAnimation objectAnimation = objectAnimations.get(index);
            objectAnimation.animate();
            if(objectAnimation.isDone()) {
                index+=dir;
            }
        }
        else {
            dir = 0;
        }
    }
    public boolean stopped() {
        return dir == 0;
    }
}
