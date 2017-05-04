package com.anwesome.ui.dotbarswitch;

import android.view.MotionEvent;
import android.view.View;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by anweshmishra on 04/05/17.
 */
public class AnimationHandler {
    private View view;
    private List<DotbarSwitchShape> dotbarSwitchShapes = new ArrayList<>();
    private ConcurrentLinkedQueue<DotbarSwitchShape> shapes = new ConcurrentLinkedQueue<>();
    private boolean isAnimated = false;
    public AnimationHandler(View view,List<DotbarSwitchShape> dotbarSwitchShapes) {
        this.view = view;
        this.dotbarSwitchShapes = dotbarSwitchShapes;
    }
    public void animate() {
        if (isAnimated) {
            for (DotbarSwitchShape shape : shapes) {
                shape.update();
                if(shape.stopUpdating()) {
                    shapes.remove(shape);
                    if(shapes.size() == 0) {
                        isAnimated = false;
                    }
                }
            }
            try {
                Thread.sleep(50);
                view.invalidate();
            } catch (Exception ex) {

            }
        }
    }
    public void handleTouch(MotionEvent event,float offsetX) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            float x = event.getX()-offsetX,y = event.getY();
            for(DotbarSwitchShape dotbarSwitchShape:dotbarSwitchShapes) {
                if(dotbarSwitchShape.handleTap(x,y)) {
                    boolean tappedFirst = shapes.size() == 0;
                    dotbarSwitchShape.startUpdating();
                    shapes.add(dotbarSwitchShape);
                    if (tappedFirst) {
                        isAnimated = true;
                        view.postInvalidate();
                    }
                }
            }
        }
    }
}
