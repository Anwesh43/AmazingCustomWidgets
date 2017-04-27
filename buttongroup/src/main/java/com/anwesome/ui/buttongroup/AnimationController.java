package com.anwesome.ui.buttongroup;

import android.view.View;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by anweshmishra on 27/04/17.
 */
public class AnimationController {
    private View view;
    private List<ButtonElement> buttonElements = new ArrayList<>();
    private ConcurrentLinkedQueue<ButtonElement> tappedButtons = new ConcurrentLinkedQueue<>();
    private boolean isAnimated = false;
    public AnimationController(View view,List<ButtonElement> buttonElements) {
        this.view = view;
        this.buttonElements = buttonElements;
    }
    public void animate() {
        if(isAnimated) {
            for(ButtonElement tappedButton:tappedButtons) {
                tappedButton.update();
                if(tappedButton.stopped()) {
                    tappedButtons.remove(tappedButton);
                    if(tappedButtons.size() == 0) {
                        isAnimated = false;
                    }
                }
            }
            try {
                Thread.sleep(50);
                view.invalidate();
            }
            catch (Exception ex) {

            }
        }
    }
    public void handleTapForAnimation(float x,float y) {
        for(ButtonElement buttonElement:buttonElements) {
            if(buttonElement.handleTap(x,y)) {
                boolean firstTappedButton = tappedButtons.size()  == 0;
                tappedButtons.add(buttonElement);
                if(firstTappedButton) {
                    isAnimated = true;
                    view.postInvalidate();
                }
            }
        }
    }
}
