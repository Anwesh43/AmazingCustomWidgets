package com.anwesome.ui.customviewaday;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.anwesome.app.alphaimageswitch.AlphaImageSwitch;
import com.anwesome.app.alphaimageswitch.AlphaImageSwitchButton;
import com.anwesome.app.alphaimageswitch.SelectedListner;
import com.anwesome.app.beanbutton.BeanButton;
import com.anwesome.app.buttonintriangle.ButtonsTriangle;
import com.anwesome.app.circularwindow.CircularWindow;
import com.anwesome.app.customactionsheet.ActionSheet;
import com.anwesome.app.customfloatingactionbutton.ActionIcon;
import com.anwesome.app.customfloatingactionbutton.CustomFloatingActionButton;
import com.anwesome.app.groupimagebuttons.GroupImageButton;
import com.anwesome.app.groupimagebuttons.GroupImageButtons;
import com.anwesome.app.imageclippe.ImageClipper;
import com.anwesome.app.imageclippe.ImageClipperShape;
import com.anwesome.app.lockbuttons.LockButton;
import com.anwesome.app.lockbuttons.LockButtonType;
import com.anwesome.app.menuexpander.Menu;
import com.anwesome.app.menuexpander.MenuContainer;
import com.anwesome.app.menuexpander.MenuExpander;
import com.anwesome.app.modakbutton.ModakButton;
import com.anwesome.app.pathtext.PathText;
import com.anwesome.app.prokbutton.ProkButton;
import com.anwesome.app.stickynotification.StickyNotification;
import com.anwesome.app.tablikeviews.TabElement;
import com.anwesome.app.tablikeviews.TabLikeLayout;
import com.anwesome.ui.bulletedlist.BulletedList;
import com.anwesome.ui.circularbuttonchooser.CircularButtonChooser;
import com.anwesome.ui.compassbutton.CompassButton;
import com.anwesome.ui.completeballbuttons.BallButton;
import com.anwesome.ui.completeballbuttons.CompleteBallButton;
import com.anwesome.ui.crukybutton.CrukyButton;
import com.anwesome.ui.fourbutton.FourButtons;
import com.anwesome.ui.polygonaltraverseview.PolygonalTraverseView;
import com.anwesome.ui.tricircledbutton.TriCircledButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String actions[] = {"action1","action2","action3","action4"};
    private MenuExpander menuExpander;
    private ActionSheet actionSheet;
    private int tImages[] = {R.drawable.t1,R.drawable.t2,R.drawable.t3,R.drawable.t4};
    private int images[] = {R.drawable.delivered,R.drawable.restart,R.drawable.onway,R.drawable.order};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final PolygonalTraverseView ppView = (PolygonalTraverseView)findViewById(R.id.ppview);
        ppView.setN(6);
        ppView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLockButtons();
                ppView.setVisibility(View.INVISIBLE);
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
    public void showExpanderMenu() {
        List<Menu> menus = new ArrayList<>();
        int i = 0;
        for(int image:images) {
            final int index = i;
            Menu menu = new Menu(BitmapFactory.decodeResource(getResources(),image));
            menu.setOnClickListener(new Menu.MenuClickListener() {
                @Override
                public void onClick() {
                    Toast.makeText(MainActivity.this,actions[index],Toast.LENGTH_SHORT).show();
                }
            });
            i++;
            menus.add(menu);
        }
        menuExpander = new MenuExpander(this);
        menuExpander.setMenuContainer(new MenuContainer(menus));
        menuExpander.show();
    }
    public void onBackPressed() {
        if(!(menuExpander!=null && menuExpander.handleBackPressed())) {
            super.onBackPressed();
        }
    }
    public void showButtonsInTriangle() {
        ButtonsTriangle buttonsTriangle = new ButtonsTriangle(this);
        buttonsTriangle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"clicked",Toast.LENGTH_SHORT).show();
            }
        });
        buttonsTriangle.show(300,300);
    }
    public void showTabLikeViews() {
        String colorStrings[] = {"#f44336","#F57F17","#009688","#311B92","#F50057","#FFEE58"};
        TabLikeLayout tabLikeLayout = new TabLikeLayout(this);
        for(int i=0;i<colorStrings.length;i++) {
            View coloredView = new ColoredFragment(this,Color.parseColor(colorStrings[i]),(i+1));
            tabLikeLayout.addTab(new TabElement(coloredView, "tab" + (i + 1)));
        }
        tabLikeLayout.show();
    }
    public void showGroupImageButtons() {
        GroupImageButtons groupImageButtons = new GroupImageButtons(this);
        for(int i=0;i<5*images.length;i++) {
            groupImageButtons.addImageButton(new GroupImageButton(BitmapFactory.decodeResource(getResources(),tImages[i%4])));
        }
        groupImageButtons.show();
    }
    public void showActionSheet() {
        if(actionSheet == null) {
            actionSheet = new ActionSheet(this);
        }
        actionSheet.setTitle("Some Actions");
        addActions(actionSheet,"Delete","Add","Make","More");
        actionSheet.show();
    }
    private void addActions(ActionSheet actionSheet,String ...actions) {
        for(String action:actions) {
            final String text = action;
            actionSheet.addAction(action, new ActionSheet.ActionListener() {
                @Override
                public void doAction() {
                    Toast.makeText(MainActivity.this,text,Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    public void showBeanButtons() {
        BeanButton beanButton = new BeanButton(this);
        beanButton.show(200,200);
    }
    public void showCustomFloatingActionButton() {
        CustomFloatingActionButton customFloatingActionButton = new CustomFloatingActionButton(this);
        for(int i=0;i<images.length;i++) {
            final int index = i;
            ActionIcon actionIcon = new ActionIcon(BitmapFactory.decodeResource(getResources(),images[i]));
            actionIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this,actions[index],Toast.LENGTH_LONG).show();
                }
            });
            customFloatingActionButton.addActionIcon(actionIcon);
        }
        customFloatingActionButton.show();
    }
    public void showPathText() {
        PathText pathText = new PathText(this,'H');
        pathText.show(200,200);
        PathText pathText1 = new PathText(this,'E');
        pathText1.show(500,500);
    }
    public void showModakButton() {
        ModakButton modakButton = new ModakButton(this);
        modakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"loaded completely",Toast.LENGTH_LONG).show();
            }
        });
        modakButton.show(100,100);
    }
    public void showImageClipper() {
        ImageClipper imageClipper = new ImageClipper(this,BitmapFactory.decodeResource(getResources(),tImages[0]), ImageClipperShape.CIRCLE_SHAPE);
        imageClipper.show(100,100);
        ImageClipper imageClipper1 = new ImageClipper(this,BitmapFactory.decodeResource(getResources(),tImages[1]),ImageClipperShape.RECT_SHAPE);
        imageClipper1.show(500,500);
        ImageClipper imageClipper2 = new ImageClipper(this,BitmapFactory.decodeResource(getResources(),tImages[2]),ImageClipperShape.TRIANGLE_SHAPE);
        imageClipper2.show(900,900);
    }
    public void showStickyNotification() {
        String notifText = "hello there lets see how it wraps up. Here are some insights on jobs done.But Lucas is enjoying his time here since his move from Deportivo la Coruna";
        StickyNotification stickyNotification = new StickyNotification(this,notifText, BitmapFactory.decodeResource(getResources(),R.drawable.penguin));
        stickyNotification.show();
    }
    public void showAlphaImageSwitch() {
        AlphaImageSwitch alphaImageSwitch = new AlphaImageSwitch(this);
        int index = 0;
        for(int tImage:tImages) {
            final String text = actions[index];
            AlphaImageSwitchButton alphaImageSwitchButton = new AlphaImageSwitchButton(BitmapFactory.decodeResource(getResources(),tImage));
            alphaImageSwitchButton.setSelectedLisenter(new SelectedListner() {
                @Override
                public void onSelected() {
                    Toast.makeText(MainActivity.this,text,Toast.LENGTH_SHORT).show();
                }
            });
            alphaImageSwitch.addImageSwitchButton(alphaImageSwitchButton);
            index++;
        }
        alphaImageSwitch.show(300);
    }
    public void showLockButtons() {
        LockButton lockButton = new LockButton(this, LockButtonType.ROUNDRECTANGLE);
        lockButton.show(200,200);
    }
}
