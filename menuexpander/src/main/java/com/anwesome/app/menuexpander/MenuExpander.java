package com.anwesome.app.menuexpander;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.ExpandedMenuView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by anweshmishra on 13/02/17.
 */
public class MenuExpander {
    private Activity activity;
    private MenuContainer menuContainer;
    public MenuExpander(Activity activity) {
        this.activity = activity;
    }
    public void setMenuContainer(MenuContainer menuContainer) {
        this.menuContainer = menuContainer;
    }
    public void show() {
        MenuExpanderView view = new MenuExpanderView(activity);
        activity.addContentView(view,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

    }
    private class MenuExpanderView extends View {

        private int time = 0;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public MenuExpanderView(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas) {
            int w = canvas.getWidth(),h = canvas.getHeight();
            if(time == 0) {
                menuContainer.setDimensions(w/2,h/2,w,h);
            }
            menuContainer.draw(canvas,paint);
            menuContainer.render(this);
            time++;

        }
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX(),y = event.getY();
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                menuContainer.handleTap(this,x,y);
            }
            return true;
        }
    }
}
