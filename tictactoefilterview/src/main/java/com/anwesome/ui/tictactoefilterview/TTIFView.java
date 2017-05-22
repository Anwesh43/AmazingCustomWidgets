package com.anwesome.ui.tictactoefilterview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 22/05/17.
 */
public class TTIFView extends View{
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int time = 0,w,h;
    private Bitmap bitmap;
    private int index = 0;
    public TTIFView(Context context, Bitmap bitmap,int index) {
        super(context);
        this.bitmap = bitmap;
        this.index = index;
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            bitmap = Bitmap.createScaledBitmap(bitmap,w,h,true);
        }
        canvas.drawBitmap(bitmap,0,0,paint);
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){

        }
        return true;
    }
    private class TicTacFilter {
        private float scale = 0;
        public void draw(Canvas canvas) {
            canvas.drawColor(Color.parseColor("#99000000"));
            paint.setStyle(Paint.Style.STROKE);
            canvas.save();
            canvas.translate(w/2,h/2);
            canvas.scale(scale,scale);
            if(index%2 == 0) {
                for(int i=0;i<2;i++) {
                    canvas.save();
                    canvas.translate(0, 0);
                    canvas.rotate(45*(2*i-1));
                    Path path = new Path();
                    path.moveTo(0, -h / 2);
                    path.lineTo(0, h / 2);
                    canvas.drawPath(path, paint);
                    canvas.restore();
                }
            }
            else {
                canvas.drawCircle(0,0,w/2,paint);
            }
            canvas.restore();
        }
    }
}
