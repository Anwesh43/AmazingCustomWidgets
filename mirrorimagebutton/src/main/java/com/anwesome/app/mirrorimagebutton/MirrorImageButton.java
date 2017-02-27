package com.anwesome.app.mirrorimagebutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

/**
 * Created by anweshmishra on 28/02/17.
 */
public class MirrorImageButton {
    private Activity activity;
    public MirrorImageButton(Activity activity) {
        this.activity = activity;
    }
    public void show() {

    }
    private class MirrorImageButtonView extends View {
        public MirrorImageButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
        }
    }
}
