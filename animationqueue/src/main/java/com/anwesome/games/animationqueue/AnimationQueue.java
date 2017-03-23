package com.anwesome.games.animationqueue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anweshmishra on 24/03/17.
 */
public class AnimationQueue {
    private List<ObjectAnimation> objectAnimations = new ArrayList<>();
    public void addObjectAnimation(ObjectAnimation objectAnimation) {
        this.objectAnimations.add(objectAnimation);
    }
    public void execute() {
        if(objectAnimations.size()>0) {
            ObjectAnimation objectAnimation = objectAnimations.get(0);
            objectAnimation.animate();
            if(objectAnimation.isDone()) {
                objectAnimations.remove(0);
            }
        }
    }
}
