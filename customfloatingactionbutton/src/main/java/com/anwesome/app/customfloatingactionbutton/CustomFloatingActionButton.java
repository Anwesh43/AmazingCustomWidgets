package com.anwesome.app.customfloatingactionbutton;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.anwesome.ui.dimensionsutil.DimensionsUtil;

import java.util.*;

/**
 * Created by anweshmishra on 19/02/17.
 */
public class CustomFloatingActionButton {
    private Activity activity;
    private boolean isAnimated = false, expanded = false;
    private MainActionButton mainActionButton;
    private FloatingActionButtonView floatingActionButtonView;
    private int color = Color.parseColor("#303F9F");
    private List<ActionIcon> actionIcons = new ArrayList<>();
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public void setColor(int color) {
        this.color = color;
    }

    public CustomFloatingActionButton(Activity activity) {
        this.activity = activity;
    }

    public void addActionIcon(ActionIcon actionIcon) {
        actionIcons.add(actionIcon);
    }

    public void show() {
        if (floatingActionButtonView == null) {
            Point size = DimensionsUtil.getDeviceDimension(activity);
            float w = size.x, h = size.y, y1 = h / 10 + h / 40 + h / 30, x = w / 2, y = h / 20, r = h / 20;
            for (ActionIcon actionIcon : actionIcons) {
                actionIcon.setDimensions(w / 2, y1, h / 15, y);
                y1 += h/10;
            }
            mainActionButton = new MainActionButton(x, y, r);
            floatingActionButtonView = new FloatingActionButtonView(activity);
            activity.addContentView(floatingActionButtonView, new ViewGroup.LayoutParams((int) (w / 2 + h / 10), (int) (y1 + h / 30)));
        }

    }

    private class MainActionButton {
        private float deg = 0, x = 0, y = 0, r = 10, dir = 1;

        public MainActionButton(float x, float y, float r) {
            this.x = x;
            this.y = y;
            this.r = r;
        }

        public void draw(Canvas canvas, Paint paint) {
            for (ActionIcon actionIcon : actionIcons) {
                actionIcon.draw(canvas, paint);
            }
            paint.setColor(color);
            canvas.save();
            canvas.translate(x, y);
            canvas.rotate(deg);
            canvas.drawCircle(0, 0, r, paint);
            for (int i = 0; i < 2; i++) {
                canvas.save();
                canvas.rotate(90 * i);
                paint.setColor(Color.WHITE);
                canvas.drawLine(-(r * 3) / 4, 0, (r * 3) / 4, 0, paint);
                canvas.restore();
            }
            canvas.restore();
        }

        public void setDir(float dir) {
            this.dir = dir;
        }

        public void update() {
            deg += 9 * dir;
            for (ActionIcon actionIcon : actionIcons) {
                actionIcon.update();
            }
            if (deg >= 45) {
                deg = 45;
                boolean isDone = true;
                for(ActionIcon actionIcon:actionIcons) {
                    isDone = isDone && actionIcon.isTakenPosition();
                    if(!isDone) {
                        break;
                    }
                }
                if(isDone) {
                    dir = -1;
                    isAnimated = false;
                    expanded = true;
                    for(ActionIcon actionIcon:actionIcons) {
                        actionIcon.setTakenPosition(false);
                    }
                }
            }
            if (deg <= 0) {
                deg = 0;
                boolean isDone = true;
                for(ActionIcon actionIcon:actionIcons) {
                    isDone = isDone && actionIcon.isTakenPosition();
                    if(!isDone) {
                        break;
                    }
                }
                if(isDone) {
                    dir = 1;
                    isAnimated = false;
                    expanded = false;
                    for(ActionIcon actionIcon:actionIcons) {
                        actionIcon.setTakenPosition(false);
                    }
                }

            }
        }

        public boolean handleTap(float x, float y) {
            return x >= this.x - r && x <= this.x + r && y >= this.y - r && y <= this.y + r;
        }
    }

    private class FloatingActionButtonView extends View {
        public FloatingActionButtonView(Context context) {
            super(context);
        }

        public void onDraw(Canvas canvas) {
            if (mainActionButton != null) {
                mainActionButton.draw(canvas, paint);
            }
            if (isAnimated) {
                mainActionButton.update();
                try {
                    Thread.sleep(50);
                    invalidate();
                } catch (Exception ex) {

                }
            }
        }

        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX(), y = event.getY();
            if (event.getAction() == MotionEvent.ACTION_DOWN && !isAnimated) {
                if (expanded) {
                    ActionIcon clickedActionIcon = null;
                    for (ActionIcon actionIcon : actionIcons) {
                        if (actionIcon.handleTap(x, y)) {
                            clickedActionIcon = actionIcon;
                            clickedActionIcon.click(this);
                            break;
                        }
                    }
                }
                if (mainActionButton != null && mainActionButton.handleTap(x, y)) {
                    isAnimated = true;
                    postInvalidate();
                }
            }
            return true;
        }
    }
}
