package com.anwesome.ui.dimensionsutil;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.view.Display;

/**
 * Created by anweshmishra on 07/02/17.
 */
public class DimensionsUtil {
    public static Point getDeviceDimension(Activity activity) {
        DisplayManager displayManager = (DisplayManager)activity.getSystemService(Context.DISPLAY_SERVICE);
        Display display = displayManager.getDisplay(0);
        Point dimensions = new Point();
        if(display!=null) {
            display.getRealSize(dimensions);
        }
        return dimensions;
    }
}
