package com.anwesome.games.hamburgbutton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anweshmishra on 18/03/17.
 */
public class IconAnimationQueue {
    private boolean stopped = true;
    private int dir = 1,index = 0;
    private List<IconAnimation> iconAnimations = new ArrayList<>();
    public void addIconAnimation(IconAnimation iconAnimation) {
        iconAnimations.add(iconAnimation);
    }
    public void animate() {
        if(!stopped) {
            IconAnimation iconAnimation = iconAnimations.get(index);
            iconAnimation.execute(dir);
            if (iconAnimation.isDone()) {
                if ((index < iconAnimations.size() - 1 && dir == 1) || (index > 0 || dir == -1)) {
                    index += dir;
                } else {
                    stopped = true;
                    dir *= -1;
                }
                iconAnimation.setEdgeConditionOnComplete();
            }
        }

    }
    public void startAnimating() {
        stopped = false;
    }
    public boolean isStopped() {
        return stopped;
    }
}
