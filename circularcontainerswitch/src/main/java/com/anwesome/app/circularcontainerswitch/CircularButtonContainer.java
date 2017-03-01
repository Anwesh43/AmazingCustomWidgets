package com.anwesome.app.circularcontainerswitch;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.view.*;
import java.util.*;

/**
 * Created by anweshmishra on 01/03/17.
 */
public class CircularButtonContainer {
    private Activity activity;
    private List<CircularButton> circularButtons = new ArrayList<>();
    public CircularButtonContainer(Activity activity) {
        this.activity = activity;
    }
    public void addCircularButton(Bitmap bitmap,char tag) {
        CircularButton circularButton = CircularButton.newInstance(bitmap,tag);
        circularButtons.add(circularButton);
    }
    public void show() {

    }
    private class CircularButtonContainerView extends View {
        public CircularButtonContainerView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {

        }
        public boolean onTouchEvent(MotionEvent event) {
            return true;
        }
    }
}
