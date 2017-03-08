package com.anwesome.games.latchbutton;

import android.graphics.*;

/**
 * Created by anweshmishra on 09/03/17.
 */
public class PointPath extends Path{
    public void moveTo(Point point) {
        super.moveTo(point.x,point.y);
    }
    public void lineTo(Point point) {
        super.lineTo(point.x,point.y);
    }
    public void moveTo(PointF point) {
        super.moveTo(point.x,point.y);
    }
    public void lineTo(PointF point) {
        super.lineTo(point.x,point.y);
    }
}
