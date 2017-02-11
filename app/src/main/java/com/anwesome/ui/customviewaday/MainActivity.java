package com.anwesome.ui.customviewaday;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.anwesome.app.circularwindow.CircularWindow;
import com.anwesome.app.prokbutton.ProkButton;
import com.anwesome.ui.bulletedlist.BulletedList;
import com.anwesome.ui.circularbuttonchooser.CircularButtonChooser;
import com.anwesome.ui.compassbutton.CompassButton;
import com.anwesome.ui.completeballbuttons.BallButton;
import com.anwesome.ui.completeballbuttons.CompleteBallButton;
import com.anwesome.ui.crukybutton.CrukyButton;
import com.anwesome.ui.fourbutton.FourButtons;
import com.anwesome.ui.polygonaltraverseview.PolygonalTraverseView;
import com.anwesome.ui.tricircledbutton.TriCircledButton;

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
                showProkButton();
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
    public void showBulletedList() {
        BulletedList bulletedList = new BulletedList(this);
        bulletedList.setItems(actions);
        bulletedList.show(300,600);
    }
    public void showCompleteButtons() {
        CompleteBallButton completeBallButton = new CompleteBallButton(this);
        completeBallButton.addBallButton(new BallButton('A'));
        completeBallButton.addBallButton(new BallButton('B'));
        completeBallButton.addBallButton(new BallButton('C'));
        completeBallButton.addBallButton(new BallButton('D'));
        completeBallButton.addBallButton(new BallButton('E'));
        completeBallButton.show(0,600);
    }
    public void showCrukyButton() {
        CrukyButton crukyButton = new CrukyButton(this);
        crukyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "First CrukyButton", Toast.LENGTH_SHORT).show();
            }
        });
        crukyButton.show();
        CrukyButton crukyButton1 = new CrukyButton(this);
        crukyButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Second CrukyButton", Toast.LENGTH_SHORT).show();
            }
        });
        crukyButton1.show(300,300);
    }
    public void showCompassButton() {
        CompassButton compassButton = new CompassButton(this);
        compassButton.show(400,400);
        compassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Clicked On Compass",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void showTriCircledButton() {
        TriCircledButton triCircledButton = new TriCircledButton(this);
        triCircledButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "clicked on triangle", Toast.LENGTH_SHORT).show();
            }
        });
        triCircledButton.setTriangleColor(Color.parseColor("#0097A7"));
        triCircledButton.show(300,300);
    }
    public void showCircularWindow() {
        CircularWindow circularWindow = new CircularWindow(this);
        circularWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Showing text",Toast.LENGTH_SHORT).show();
            }
        });
        circularWindow.show(400,400);
    }
    public void showProkButton() {
        ProkButton prokButton = new ProkButton(this);
        prokButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Clicked On ProkButton", Toast.LENGTH_SHORT).show();
            }
        });
        prokButton.show(500,500);
    }
}
