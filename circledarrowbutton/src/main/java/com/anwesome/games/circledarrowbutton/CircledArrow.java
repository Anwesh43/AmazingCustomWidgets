package com.anwesome.games.circledarrowbutton;

import android.graphics.*;

/**
 * Created by anweshmishra on 08/03/17.
 */
public class CircledArrow {
    private float x,y,radius,lineLength,triSize,deg=0,dir=0,circScale = 0,triScale=0;
    public CircledArrow(float w) {
        this.x = w/8;
        this.y = 7*w/8;
        this.radius = w/8;
        this.lineLength = w/2;
        this.triSize = w/4;
    }
    public void draw(Canvas canvas,Paint paint) {
        canvas.save();
        canvas.translate(x,y);
        canvas.rotate(deg);
        canvas.save();
        canvas.restore();
        canvas.restore();
    }
    public void startAnimating() {
        if(dir == 0) {
            dir = dir == 0?1:-1;
        }
    }
    public void update() {
        deg-=dir*9;
        circScale+=dir*0.1f;
        triScale+=dir*0.1f;
        if(circScale>=1 && triScale>=1 && deg<=-90) {
            dir = 0;
            circScale = 1;
            triScale = 1;
            deg = -90;
        }
        if(circScale<=0 && triScale<=0 && deg>=0) {
            deg = 0;
            circScale = 0;
            dir = 0;
            triScale = 0;
        }
    }
    public boolean handleTap(float x,float y) {
        return (x>=this.x-radius && x<=this.x+radius && y>=this.y-radius && y<=this.y+radius);
    }
}
