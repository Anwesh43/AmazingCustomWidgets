package com.anwesome.app.trianglecirclebutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anweshmishra on 26/02/17.
 */
public class TricSwitch {
    private Activity activity;
    private List<TriCircButton> buttons = new ArrayList<>();
    public TricSwitch(Activity activity) {
        this.activity = activity;
    }
    public void addTricButton(TriCircButton button) {
        buttons.add(button);
    }
    public void show() {

    }
    private class TricSwitchView extends View {
        private boolean isAnimated = false;
        private TriCircButton prevButton=null,currButton = null;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public TricSwitchView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {

        }
    }
}
