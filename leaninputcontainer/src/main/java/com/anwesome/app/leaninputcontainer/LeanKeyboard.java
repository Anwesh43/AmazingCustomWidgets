package com.anwesome.app.leaninputcontainer;

import android.graphics.*;
import java.util.*;

/**
 * Created by anweshmishra on 27/02/17.
 */
public class LeanKeyboard {

    private char currChar;
    private float x,y,size;
    private List<LeanKey> leanKeys = new ArrayList<>();
    private LeanKeyboard(float x,float y,float size) {
        this.x = x;
        this.y = y;
        this.size = size;
        initDimensions();
    }
    public static LeanKeyboard newInstance(float x,float y,float size) {
        return new LeanKeyboard(x,y,size);
    }
    public void initDimensions() {
        char letters[] = {'1','2','3','4','5','6','7','8','9','*','0','#'};
        float keySize = size/3,keyX = x-size/2+keySize/2,keyY = y-size/2+keySize/2;
        for(char letter:letters) {
            LeanKey leanKey = new LeanKey(letter,keyX,keyY,keySize);
            leanKeys.add(leanKey);
            if(leanKeys.size()%3 == 0){
                keyX = x-size/2+keySize/2;
                keyY += keySize;
            }
            else {
                keyX+=keySize;
            }
        }
    }
    public void draw(Canvas canvas,Paint paint) {
        paint.setColor(Color.parseColor("#EEEEEE"));
        canvas.drawRect(new RectF(x-size/2,y-2*size/3,x+size/2,y+2*size/3),paint);
        for(LeanKey leanKey:leanKeys) {
            leanKey.draw(canvas,paint);
        }
    }
    private class LeanKey {
        private char letter;
        private float x,y,size,scale = 0,scaleDir = 0;
        public LeanKey(char letter,float x,float y,float size) {
            this.letter = letter;
            this.x = x;
            this.y = y;
            this.size = size;
        }
        public void startScalingUp() {
            scaleDir = 0.1f;
        }
        public void startScalingDown() {
            scaleDir = -0.1f;
        }
        public void draw(Canvas canvas, Paint paint) {
            paint.setColor(Color.BLACK);
            paint.setTextSize(size/2);
            canvas.drawText(""+letter,x,y,paint);
            paint.setColor(Color.parseColor("#AABDBDBD"));
            canvas.save();
            canvas.translate(x,y);
            canvas.scale(scale,scale);
            canvas.drawCircle(0,0,size/2,paint);
            canvas.restore();
        }
        public void update() {
            scale+=scaleDir;
            if(scale>=1) {
                scaleDir*=1;
            }
            if(scale<=0) {
                scaleDir  = 0;
            }
        }
        public boolean handleTap(float x,float y) {
            return x>=this.x-size/2 && x<=this.x+size/2 && y>=this.y-size/2 && y<=this.y+size/2;
        }
        public boolean isStop() {
            return scaleDir == 0;
        }
        public int hashCode() {
            return (int)x+(int)y+(int)scaleDir+(int)scale+letter;
        }
        public char getLetter() {
            return letter;
        }
    }
}
