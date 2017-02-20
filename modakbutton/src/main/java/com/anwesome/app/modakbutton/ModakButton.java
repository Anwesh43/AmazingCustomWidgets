package com.anwesome.app.modakbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by anweshmishra on 21/02/17.
 */
public class ModakButton {
    private Activity activity;
    private LineMover lineMover;
    public ModakButton(Activity activity) {
        this.activity = activity;
    }
    public void show() {

    }
    private class ModakButtonView extends View {
        private int time = 0;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public ModakButtonView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            int w = canvas.getWidth(),h = canvas.getHeight();
            if(time == 0) {
                lineMover = new LineMover(w/2,h/2,w/3);
            }
            paint.setColor(Color.GRAY);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(canvas.getWidth()/40);
            canvas.drawCircle(w/2,h/2,w/3,paint);
            lineMover.draw(canvas,paint);
            time++;
            try {
                Thread.sleep(50);
                invalidate();
            }
            catch (Exception ex) {

            }
        }
    }
}
