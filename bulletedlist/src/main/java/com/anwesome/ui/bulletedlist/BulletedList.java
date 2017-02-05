package com.anwesome.ui.bulletedlist;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.view.MotionEvent;
import android.view.View;
import java.util.*;

/**
 * Created by anweshmishra on 06/02/17.
 */
public class BulletedList {
    private Activity activity;
    private List<String> items = new ArrayList<>();
    public BulletedList(Activity activity) {
        this.activity = activity;
    }
    public void show() {

    }
    public void addItem(String item) {
        this.items.add(item);
    }
    public void setItems(String items[]) {
        for(String item:items) {
            this.items.add(item);
        }
    }
    public void setItems(List<String> items) {
        this.items = items;
    }
    public class BulletedListView extends View {
        private int time = 0;
        private boolean isAnimated = false;
        private List<BulletedElement> elements = new ArrayList<>();
        private Triangle triangle;
        public BulletedListView(Context context) {
            super(context);
        }

        public void onDraw(Canvas canvas) {
            int w = canvas.getWidth(),h = canvas.getHeight();
            if(time == 0) {
                triangle = new Triangle();
            }
            time++;
            if(isAnimated) {
                try {
                    Thread.sleep(50);
                    invalidate();
                }
                catch(Exception ex) {

                }
            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated) {

            }
            return true;
        }
    }
    private class Triangle  {
        private float deg = 180,x,y,rot=0;
        public Triangle(float x,float y) {
            this.x = x;
            this.y = y;
        }
        public void render(Canvas canvas,Paint paint) {
            canvas.save();
            canvas.translate(x,y);
            canvas.rotate(deg);
            canvas.restore();
            deg+=rot;
            rot = deg%180 == 0?0:rot;
        }
        public void startAnimating() {
            if(rot == 0 && deg%180 == 0) {
                rot = deg == 180?-30:30;
            }
        }
    }
    private class BulletedElement {
        private String item;
        private float y,h;
        public BulletedElement(String item,float y,float h) {
            this.item = item;
            this.y = y;
            this.h = h;
        }
        public boolean containsTap(float y) {
            return y>this.y && y<this.y+this.h;
        }
        public int hashCode() {
            return item.hashCode()+(int)y+(int)h;
        }
    }
}
