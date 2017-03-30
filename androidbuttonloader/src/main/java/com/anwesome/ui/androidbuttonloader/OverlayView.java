package com.anwesome.ui.androidbuttonloader;

import android.content.Context;
import android.graphics.*;
import android.view.View;

/**
 * Created by anweshmishra on 30/03/17.
 */
public class OverlayView extends View {
    public OverlayView(Context context) {
        super(context);
    }
    public void onDraw(Canvas canvas) {
        canvas.drawColor(Color.parseColor("#99000000"));
    }
}
