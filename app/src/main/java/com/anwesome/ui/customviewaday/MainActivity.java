package com.anwesome.ui.customviewaday;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.anwesome.ui.circularbuttonchooser.CircularButtonChooser;
import com.anwesome.ui.fourbutton.FourButtons;
import com.anwesome.ui.polygonaltraverseview.PolygonalTraverseView;

public class MainActivity extends AppCompatActivity {
    private String actions[] = {"action1","action2","action3","action4"};
    private int images[] = {R.drawable.delivered,R.drawable.restart,R.drawable.onway,R.drawable.order};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PolygonalTraverseView ppView = (PolygonalTraverseView)findViewById(R.id.ppview);
        ppView.setN(6);
        ppView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCircularButtonChooser();
            }
        });
    }
    public void showFourButtons() {
        FourButtons fourButtons = new FourButtons(this);
        for(int i=0;i<actions.length;i++) {
            final int curIndex = i;
            fourButtons.addButton(actions[i], BitmapFactory.decodeResource(getResources(), images[i]), new FourButtons.FourButtonListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this,actions[curIndex],Toast.LENGTH_SHORT).show();
                }
            });
            fourButtons.show();
        }
    }
    public void showCircularButtonChooser() {
        CircularButtonChooser circularButtonChooser = new CircularButtonChooser(this);
        for(int i=0;i<actions.length;i++) {
            final int curIndex = i;
            circularButtonChooser.addCircularButton(BitmapFactory.decodeResource(getResources(), images[i]), new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, actions[curIndex], Toast.LENGTH_SHORT).show();
                }
            });
        }
        circularButtonChooser.show(400,400);
    }
}
