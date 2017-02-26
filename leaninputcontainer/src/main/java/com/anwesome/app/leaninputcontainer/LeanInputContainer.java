package com.anwesome.app.leaninputcontainer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by anweshmishra on 27/02/17.
 */
public class LeanInputContainer {
    private Activity activity;
    public LeanInputContainer(Activity activity) {
        this.activity = activity;
    }
    public void show() {

    }
    private class LeanInputContainerView extends View {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public LeanInputContainerView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {

        }
    }
}
