package com.anwesome.ui.bluetoothbutton.utils;

import android.graphics.Path;
import android.graphics.Point;

import java.util.List;

/**
 * Created by anweshmishra on 14/04/17.
 */
public class LineMaker {
    public static Path drawLine(List<Point> points) {
        Path path = new Path();
        int index = 0;
        for(Point point:points) {
            if(index == 0) {
                path.moveTo(point.x,point.y);
            }
            else {
                path.lineTo(point.x,point.y);
            }
        }
        return path;
    }
}
