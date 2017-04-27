package com.anwesome.ui.buttongroup;

import android.app.Activity;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anweshmishra on 27/04/17.
 */
public class ButtonGroup {
    private Activity activity;
    private ButtonView buttonView;
    private List<ButtonElement> buttonElements = new ArrayList<>();
    private int color = Color.parseColor("#990277BD");
    public ButtonGroup(Activity activity) {
        this.activity = activity;
    }
    public void setColor(int color) {
        this.color = color;
    }
    public void addButton(String title,OnSelectionListener onSelectionListener) {
        if(buttonView == null) {
            ButtonElement buttonElement = new ButtonElement(title);
            buttonElement.setOnSelectionListener(onSelectionListener);
            buttonElements.add(buttonElement);
        }
    }
    public void show() {
        if(buttonView == null) {
            buttonView = new ButtonView(activity,buttonElements,color);
            activity.setContentView(buttonView);
        }
    }
}
