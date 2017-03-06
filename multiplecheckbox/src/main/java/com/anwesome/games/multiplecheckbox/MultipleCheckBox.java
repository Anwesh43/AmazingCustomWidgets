package com.anwesome.games.multiplecheckbox;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.view.*;
import java.util.*;

/**
 * Created by anweshmishra on 07/03/17.
 */
public class MultipleCheckBox {
    private Activity activity;
    private List<CheckBox> checkBoxList = new ArrayList<>();
    private MultipleCheckBoxView multipleCheckBoxView;
    public MultipleCheckBox(Activity activity) {
        this.activity = activity;
    }
    public void show() {
        if(multipleCheckBoxView == null) {
            multipleCheckBoxView = new MultipleCheckBoxView(activity);
            activity.setContentView(multipleCheckBoxView);
        }
    }
    public void addCheckBox(String text) {
        CheckBox checkBox = CheckBox.newInstance(text);
        checkBoxList.add(checkBox);
    }
    private class MultipleCheckBoxView extends View {
        private boolean isAnimated  = false;
        private int time = 0;
        private CheckBox tappedCheckbox = null;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public MultipleCheckBoxView(Context context) {
            super(context);
        }
        public void initCheckBoxes(int w,int h) {
            float gap = (2*w)/(3*checkBoxList.size()+2),x = gap,y = h/2;
            for(CheckBox checkBox:checkBoxList) {
                checkBox.setDimensions(x,y,gap);
                x+=(3*gap)/2;
            }
        }
        public void onDraw(Canvas canvas) {
            if(time == 0) {
                initCheckBoxes(canvas.getWidth(),canvas.getHeight());
            }
            for(CheckBox checkBox:checkBoxList) {
                checkBox.draw(canvas,paint);
            }
            time++;
            if(isAnimated) {
                if(tappedCheckbox!=null) {
                    tappedCheckbox.update();
                    if(tappedCheckbox.animationStopped()) {
                        isAnimated = false;
                        tappedCheckbox = null;
                    }
                }
                try {
                    Thread.sleep(50);
                    invalidate();
                } catch (Exception ex) {

                }
            }
        }
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated && tappedCheckbox == null) {
                for(CheckBox checkBox:checkBoxList) {
                    if(checkBox.handleTap(event.getX(),event.getY())) {
                        tappedCheckbox = checkBox;
                        break;
                    }
                }
                if(tappedCheckbox != null){
                    if(tappedCheckbox.isSelected()) {
                        tappedCheckbox.unselect();
                    }
                    else {
                        tappedCheckbox.select();
                    }
                    isAnimated = true;
                    postInvalidate();
                }
            }
            return true;
        }
    }
}
