package com.anwesome.app.menuexpander;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anweshmishra on 13/02/17.
 */
public class MenuContainer {
    private float x,y,w,h,scale =0.2f,boundW,boundH,deg = 0,dir = 0;
    private boolean expanded = false;
    private boolean isAnimated = false;
    private Menu currentMenu = null;
    private List<Menu> menus = new ArrayList<>();
    public MenuContainer(List<Menu> menus) {
        this.menus = menus;

    }
    public void setDimensions(float x,float y,float w,float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.boundW = w*scale;
        this.boundH = h*scale;
        float gap = w/7;
        int i = 0;
        float xm = gap/2-w/2,ym = h/10-h/2;
        for(Menu menu:menus) {
            menu.setDimensions(xm,ym,gap);
            xm+=2*gap;
            i++;
            if(i%3 == 0){
                xm = gap/2-w/2;
                ym+=2*gap;
            }
        }
    }
    public void draw(Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor("#BB000000"));
        float r = Math.max(w,h)/6;
        canvas.save();
        canvas.translate(x,y);
        canvas.rotate(deg);
        canvas.scale(scale,scale);
        canvas.drawRoundRect(new RectF(-w/2,-h/2,w/2,h/2),r,r,paint);
        for(Menu menu:menus) {
            menu.draw(canvas,paint);
        }
        canvas.restore();
    }
    public void render(View view) {
        if(isAnimated) {
            if(!expanded) {
                deg+=72*dir;
                scale+=0.2f*dir;
                if(deg>=360) {
                    deg = 360;
                    scale = 1;
                    dir =0;
                    isAnimated = false;
                    expanded = true;
                }
                if(deg<=0) {
                    deg = 0;
                    dir = 0;
                    scale = 0.2f;
                    isAnimated = false;
                }
            }
            else {
                if(currentMenu!=null) {
                    currentMenu.render();
                    if(currentMenu.isClicked()) {
                        isAnimated = false;
                        currentMenu.resetClick();
                    }
                }
            }
            try {
                Thread.sleep(50);
                view.invalidate();
            }catch (Exception ex) {

            }
        }
    }
    public void handleTap(View view,float x,float y) {
        if (!isAnimated) {
            if (!expanded) {
                if (x >= this.x - boundW / 2 && x <= this.x + this.boundW / 2 && y >= this.y - boundH / 2 && y <= this.y + boundH / 2) {
                    dir = 1;
                    isAnimated = true;
                    view.postInvalidate();
                }
            } else {
                x = x-w/2;
                y = y-h/2;
                for (Menu menu : menus) {
                    if (menu.handleTap(x, y)) {
                        currentMenu = menu;
                        isAnimated = true;
                        view.postInvalidate();
                        break;
                    }
                }
            }
        }
    }
    public boolean handleBackPressed() {
        final boolean condition = expanded;
        if(expanded) {
            isAnimated = true;
            dir = -1;
            expanded = false;
        }
        return condition;
    }

}
