package com.anwesome.ui.tricircledbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.view.*;
/**
 * Created by anweshmishra on 10/02/17.
 */
public class TriCircledButton {
    private Activity activity;
    public TriCircledButton(Activity activity) {
    }
    private class TriCircledButtonView extends View {
        private float deg = 0,dir = 0;
        private boolean shouldAnimate = false;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public  TriCircledButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            int w = canvas.getWidth(),h = canvas.getHeight();
            canvas.save();
            canvas.translate(w/2,h/2);
            canvas.rotate(deg);
            Path path = new Path();
            path.lineTo(w/2,h/2);
            path.lineTo(0,-h/2);
            path.lineTo(-w/2,h/2);
            path.lineTo(w/2,h/2);
            
            canvas.drawPath(path,paint);
            canvas.restore();
        }
    }
}
