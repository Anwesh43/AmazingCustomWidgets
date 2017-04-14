package com.anwesome.ui.bluetoothbutton.utils;

import android.graphics.Path;
import android.graphics.PointF;

import java.util.List;

/**
 * Created by anweshmishra on 14/04/17.
 */
public class LineMaker {
    public static Path drawLine(List<PointF> points) {
        Path path = new Path();
        int index = 0;
        for(PointF point:points) {
            if(index == 0) {
                path.moveTo(point.x,point.y);
            }
            else {
                path.lineTo(point.x,point.y);
            }
            index++;
        }
        return path;
    }
}
