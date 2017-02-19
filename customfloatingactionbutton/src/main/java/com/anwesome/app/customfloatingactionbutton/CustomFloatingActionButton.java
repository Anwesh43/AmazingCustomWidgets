package com.anwesome.app.customfloatingactionbutton;

import android.app.Activity;
import android.graphics.Point;

import com.anwesome.ui.dimensionsutil.DimensionsUtil;

import java.util.*;

/**
 * Created by anweshmishra on 19/02/17.
 */
public class CustomFloatingActionButton {
    private Activity activity;
    private float y = 0;
    private List<ActionIcon> actionIcons = new ArrayList<>();
    public CustomFloatingActionButton(Activity activity) {
        this.activity = activity;
    }
    public void addActionIcon(ActionIcon actionIcon) {
        actionIcons.add(actionIcon);
    }
    public void show() {
        Point size = DimensionsUtil.getDeviceDimension(activity);
        float w = size.x,h = size.y,y = h/20,y1 = h/10+h/40+h/30;
        for(ActionIcon actionIcon:actionIcons) {
            actionIcon.setDimensions(w/2,y1,h/15);
            y1+=h/40+h/30;
        }

    }

}
