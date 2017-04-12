package com.anwesome.ui.fullscreenbutton;

import android.graphics.*;

/**
 * Created by anweshmishra on 12/04/17.
 */
public class DrawingController  {
    private float x = 0,y = 0,size;
    private StateHandler stateHandler;
    public DrawingController(StateHandler stateHandler,float x,float y,float size) {
        this.stateHandler = stateHandler;
        this.x = x;
        this.y = y;
        this.size = size;
    }
    public void draw(Canvas canvas, Paint paint) {
        canvas.save();
        canvas.translate(x,y);
        canvas.scale(stateHandler.getScale(),stateHandler.getScale());
        int dirs[][] = {{1,1},{-1,1},{-1,-1},{1,-1}};
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        for(int i=0;i<4;i++) {
            canvas.save();
            canvas.translate(size/2*dirs[i][0],size/2*dirs[i][1]);
            canvas.rotate(stateHandler.getDeg()+90*i);
            drawAEdge(canvas,paint);
            canvas.restore();
        }
        canvas.restore();
    }
    private void drawAEdge(Canvas canvas,Paint paint) {
        paint.setStrokeWidth(size/20);
        paint.setColor(Color.WHITE);
        for(int j=0;j<2;j++) {
            canvas.save();
            canvas.rotate(90*j);
            canvas.drawLine(0,0,-size/8,0,paint);
            canvas.restore();
        }
    }
}
