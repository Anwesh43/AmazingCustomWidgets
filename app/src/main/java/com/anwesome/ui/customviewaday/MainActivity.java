package com.anwesome.ui.customviewaday;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
//import com.anwesome.app.alphaimageswitch.AlphaImageSwitch;
//import com.anwesome.app.alphaimageswitch.AlphaImageSwitchButton;
//import com.anwesome.app.alphaimageswitch.SelectedListner;
//import com.anwesome.app.bargraphbutton.BarGraphButton;
//import com.anwesome.app.beanbutton.BeanButton;
//import com.anwesome.app.buttonintriangle.ButtonsTriangle;
//import com.anwesome.app.circularcontainerswitch.CircularButtonContainer;
//import com.anwesome.app.circularcontainerswitch.SelectedListener;
//import com.anwesome.app.circularwindow.CircularWindow;
//import com.anwesome.app.concentriccircleswitch.ConcentricCircleSwitch;
//import com.anwesome.app.customactionsheet.ActionSheet;
//import com.anwesome.app.customfloatingactionbutton.ActionIcon;
//import com.anwesome.app.customfloatingactionbutton.CustomFloatingActionButton;
//import com.anwesome.app.groupimagebuttons.GroupImageButton;
//import com.anwesome.app.groupimagebuttons.GroupImageButtons;
//import com.anwesome.app.imageclippe.ImageClipper;
//import com.anwesome.app.imageclippe.ImageClipperShape;
//import com.anwesome.app.leaninputcontainer.LeanInputContainer;
//import com.anwesome.app.leaninputcontainer.OnSubmitListener;
//import com.anwesome.app.lockbuttons.LockButton;
//import com.anwesome.app.lockbuttons.LockButtonType;
//import com.anwesome.app.lockbuttons.LockListener;
//import com.anwesome.app.menuexpander.Menu;
//import com.anwesome.app.menuexpander.MenuContainer;
//import com.anwesome.app.menuexpander.MenuExpander;
//import com.anwesome.app.mirrorimagebutton.MirrorImageButton;
//import com.anwesome.app.modakbutton.ModakButton;
//import com.anwesome.app.pathtext.PathText;
//import com.anwesome.app.prokbutton.ProkButton;
//import com.anwesome.app.stickynotification.StickyNotification;
//import com.anwesome.app.tablikeviews.TabElement;
//import com.anwesome.app.tablikeviews.TabLikeLayout;
//import com.anwesome.app.trianglecirclebutton.OnSelectedListener;
//import com.anwesome.app.trianglecirclebutton.TriCircButton;
//import com.anwesome.app.trianglecirclebutton.TricSwitch;
//import com.anwesome.demos.notepadbutton.NotepadButton;
//import com.anwesome.demos.pinbar.PinBar;
//import com.anwesome.demos.pinbar.PinBarButton;
//import com.anwesome.demos.playbackbutton.PlaybackButton;
//import com.anwesome.demos.playbackbutton.PlaybackButtonType;
//import com.anwesome.demos.settingsbutton.SettingsButton;
//import com.anwesome.games.breakablebutton.BreakableButton;
//import com.anwesome.games.circledarrowbutton.CircledArrowButton;
//import com.anwesome.games.circledarrowbutton.ToggleSelectionListener;
//import com.anwesome.games.clockswitch.ClockSwitch;
//import com.anwesome.games.clockswitch.OnButtonSelected;
//import com.anwesome.games.dataindicationbutton.DataIndicationType;
//import com.anwesome.games.dataindicationbutton.DataIndicator;
//import com.anwesome.games.dataindicationbutton.DataIndicatorButton;
//import com.anwesome.games.dataindicationbutton.OnDataIndicatorSelectedListener;
//import com.anwesome.games.deletebutton.DeleteButton;
//import com.anwesome.games.deletebutton.DeleteButtonListener;
//import com.anwesome.games.dotspin.DotSpin;
//import com.anwesome.games.ellipticalswitch.EllipticalSwitch;
//import com.anwesome.games.fultonbutton.FultonButton;
//import com.anwesome.games.fultonbutton.OnOffListener;
//import com.anwesome.games.hamburgbutton.HamburgerButton;
//import com.anwesome.games.hamburgbutton.HamburgerListener;
//import com.anwesome.games.imagebar.ImageBar;
//import com.anwesome.games.imagebar.OnToggleListener;
//import com.anwesome.games.latchbutton.LatchButton;
//import com.anwesome.games.latchbutton.LatchSelectedListener;
//import com.anwesome.games.livestreamingbutton.LiveStreamButton;
//import com.anwesome.games.multiplecheckbox.MultipleCheckBox;
//import com.anwesome.games.playpause.PlayPause;
//import com.anwesome.games.playpause.PlayPauseStateListener;
//import com.anwesome.games.spinnybutton.SpinnyButton;
//import com.anwesome.games.spinnybutton.ToggleSpinnyListener;
//import com.anwesome.games.tripathbutton.TriPathButton;
//import com.anwesome.games.widholder.WidButton;
//import com.anwesome.games.widholder.WidHolder;
//import com.anwesome.games.widholder.WidOnClickListener;
//import com.anwesome.games.zoomshapebutton.OnOpenListener;
//import com.anwesome.games.zoomshapebutton.ZoomShapeButton;
//import com.anwesome.ui.androidbuttonloader.AndroidLoaderButton;
//import com.anwesome.ui.bluetoothbutton.BluetoothButton;
//import com.anwesome.ui.bluetoothbutton.BluetoothButtonShape;
//import com.anwesome.ui.bulletedlist.BulletedList;
//import com.anwesome.ui.buttongroup.ButtonGroup;
//import com.anwesome.ui.buttongroup.OnSelectionListener;
//import com.anwesome.ui.calbutton.CalButton;
//import com.anwesome.ui.circularbuttonchooser.CircularButtonChooser;
//import com.anwesome.ui.bridgebutton.BridgeButtonView;
//import com.anwesome.ui.circularystbuttonlist.CYSTList;
//import com.anwesome.ui.circularystbuttonlist.OnSelectionChangeListener;
//import com.anwesome.ui.lineanddot.LineAndDotView;
import com.anwesome.games.bicirccolorview.BiCircColorView;
import com.anwesome.games.circfourbitmap.CircFourBitmapView;
import com.anwesome.games.circgridcolorimage.CircGridColorImageView;
import com.anwesome.games.imagecolorgrid.ImageColorGridView;
import com.anwesome.games.linedegcolorview.LineDegColorView;
import com.anwesome.games.pacgridview.PacGridView;
import com.anwesome.games.sweepcolorbitmap.SweepColorBitmapView;
import com.anwesome.ui.arrowlinebutton.ArrowLineButton;
import com.anwesome.ui.circmover.CircMoverView;
import com.anwesome.ui.circularcolorstack.CircularColorStackView;
import com.anwesome.ui.colorbarstack.ColorBarStack;
import com.anwesome.ui.cornercenterball.CornerCenterBallView;
import com.anwesome.ui.holderview.HolderView;
import com.anwesome.ui.holdfiller.HoldFillerView;
import com.anwesome.ui.plusbuttonrect.PlusButtonRectView;
import com.anwesome.ui.plusminuslineview.PlusMinusLineView;
import com.anwesome.ui.polygonaltraverseview.PolygonalTraverseView;
import com.anwesome.ui.rectbutton.RectButtonView;
import com.anwesome.ui.sharerotbutton.ShareRotButton;
//import com.anwesome.ui.progressbutton.OnClickListener;
//import com.anwesome.ui.progressbutton.ProgressButtonView;
//import com.anwesome.ui.tvbutton.TVButtonList;
//import com.anwesome.ui.clockbutton.ClockButton;
//import com.anwesome.ui.clockbutton.ClockListener;
//import com.anwesome.ui.completeballbuttons.BallButton;
//import com.anwesome.ui.completeballbuttons.CompleteBallButton;
//
//import com.anwesome.ui.crukybutton.CrukyButton;
//import com.anwesome.ui.directionbutton.DirectionButton;
//import com.anwesome.ui.directionbutton.OnDirectionChangeListener;
//import com.anwesome.ui.dotbarswitch.DotbarSwitch;
//import com.anwesome.ui.dotbarswitch.OnSelectionChangeListener;
//import com.anwesome.ui.emergencybutton.EmergencyButton;
//import com.anwesome.ui.eyebutton.EyeButton;
//import com.anwesome.ui.fillringbutton.FillRingButton;
//import com.anwesome.ui.fillringbutton.OnFillChangeListener;
//import com.anwesome.ui.footiepitchbutton.FootiePitchButton;
//import com.anwesome.ui.fourbutton.FourButtons;
//import com.anwesome.ui.fsarrowbutton.FsArrowButton;
//import com.anwesome.ui.fsarrowbutton.FsArrowButtonShape;
//import com.anwesome.ui.fullscreenbutton.FullScreenButton;
//import com.anwesome.ui.fullscreenbutton.FullScreenButtonShape;
//import com.anwesome.ui.gpbutton.GpButton;
//import com.anwesome.ui.gpbutton.OnSizeChangeListener;
//import com.anwesome.ui.likebutton.LikeButton;
//import com.anwesome.ui.likebutton.OnLikeChangeListener;
//import com.anwesome.ui.messageui.MessageUIButton;
//import com.anwesome.ui.messengerbutton.MessengerButton;
//import com.anwesome.ui.minustopause.MinusToPause;
//import com.anwesome.ui.mutebutton.MuteButton;
//import com.anwesome.ui.mutebutton.MuteClickListener;
//import com.anwesome.ui.ninesqaure.NineSquare;
//import com.anwesome.ui.ninesqaure.OnOpenCloseListener;
//import com.anwesome.ui.notificationbutton.NotificationButton;
//import com.anwesome.ui.polygonaltraverseview.PolygonalTraverseView;
//import com.anwesome.ui.powerbutton.PowerButton;
//import com.anwesome.ui.powerbutton.PowerButtonListener;
//import com.anwesome.ui.recordbutton.OnRecordButtonClickListener;
//import com.anwesome.ui.recordbutton.RecordButton;
//import com.anwesome.ui.sharebutton.ShareButton;
//import com.anwesome.ui.syncbutton.SyncButton;
//import com.anwesome.ui.tictactoefilterview.TicTacTowFilterViewList;
//import com.anwesome.ui.trashbutton.TrashButton;
//import com.anwesome.ui.trashbutton.TrashButtonShape;
//import com.anwesome.ui.tricircledbutton.TriCircledButton;
//import com.anwesome.ui.uiwindow.UiWindow;
//import com.anwesome.ui.watchlikebutton.WatchClickListener;
//import com.anwesome.ui.watchlikebutton.WatchLikeButton;
//import com.anwesome.ui.ystbuttonlist.YstButtonList;
//
//import java.util.ArrayList;
//import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String actions[] = {"action1","action2","action3","action4"};
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
                showCircFourView();
                ppView.setVisibility(View.INVISIBLE);
            }
        });
    }
//    public void showFourButtons() {
//        FourButtons fourButtons = new FourButtons(this);
//        for(int i=0;i<actions.length;i++) {
//            final int curIndex = i;
//            fourButtons.addButton(actions[i], BitmapFactory.decodeResource(getResources(), images[i]), new FourButtons.FourButtonListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(MainActivity.this,actions[curIndex],Toast.LENGTH_SHORT).show();
//                }
//            });
//            fourButtons.show();
//        }
//    }
//    public void showCircularButtonChooser() {
//        CircularButtonChooser circularButtonChooser = new CircularButtonChooser(this);
//        for(int i=0;i<actions.length;i++) {
//            final int curIndex = i;
//            circularButtonChooser.addCircularButton(BitmapFactory.decodeResource(getResources(), images[i]), new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(MainActivity.this, actions[curIndex], Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//        circularButtonChooser.show(400,400);
//    }
//    public void showBulletedList() {
//        BulletedList bulletedList = new BulletedList(this);
//        bulletedList.setItems(actions);
//        bulletedList.show(300,600);
//    }
//    public void showCompleteButtons() {
//        CompleteBallButton completeBallButton = new CompleteBallButton(this);
//        completeBallButton.addBallButton(new BallButton('A'));
//        completeBallButton.addBallButton(new BallButton('B'));
//        completeBallButton.addBallButton(new BallButton('C'));
//        completeBallButton.addBallButton(new BallButton('D'));
//        completeBallButton.addBallButton(new BallButton('E'));
//        completeBallButton.show(0,600);
//    }
//    public void showCrukyButton() {
//        CrukyButton crukyButton = new CrukyButton(this);
//        crukyButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "First CrukyButton", Toast.LENGTH_SHORT).show();
//            }
//        });
//        crukyButton.show();
//        CrukyButton crukyButton1 = new CrukyButton(this);
//        crukyButton1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "Second CrukyButton", Toast.LENGTH_SHORT).show();
//            }
//        });
//        crukyButton1.show(300,300);
//    }
//    public void showTriCircledButton() {
//        TriCircledButton triCircledButton = new TriCircledButton(this);
//        triCircledButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "clicked on triangle", Toast.LENGTH_SHORT).show();
//            }
//        });
//        triCircledButton.setTriangleColor(Color.parseColor("#0097A7"));
//        triCircledButton.show(300,300);
//    }
//    public void showCircularWindow() {
//        CircularWindow circularWindow = new CircularWindow(this);
//        circularWindow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this,"Showing text",Toast.LENGTH_SHORT).show();
//            }
//        });
//        circularWindow.show(400,400);
//    }
//    public void showProkButton() {
//        ProkButton prokButton = new ProkButton(this);
//        prokButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "Clicked On ProkButton", Toast.LENGTH_SHORT).show();
//            }
//        });
//        prokButton.show(500,500);
//    }
//    public void showExpanderMenu() {
//        List<Menu> menus = new ArrayList<>();
//        int i = 0;
//        for(int image:images) {
//            final int index = i;
//            Menu menu = new Menu(BitmapFactory.decodeResource(getResources(),image));
//            menu.setOnClickListener(new Menu.MenuClickListener() {
//                @Override
//                public void onClick() {
//                    Toast.makeText(MainActivity.this,actions[index],Toast.LENGTH_SHORT).show();
//                }
//            });
//            i++;
//            menus.add(menu);
//        }
//        menuExpander = new MenuExpander(this);
//        menuExpander.setMenuContainer(new MenuContainer(menus));
//        menuExpander.show();
//    }
//    public void onBackPressed() {
//        if(!(menuExpander!=null && menuExpander.handleBackPressed())) {
//            super.onBackPressed();
//        }
//    }
//    public void showButtonsInTriangle() {
//        ButtonsTriangle buttonsTriangle = new ButtonsTriangle(this);
//        buttonsTriangle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this,"clicked",Toast.LENGTH_SHORT).show();
//            }
//        });
//        buttonsTriangle.show(300,300);
//    }
//    public void showTabLikeViews() {
//        String colorStrings[] = {"#f44336","#F57F17","#009688","#311B92","#F50057","#FFEE58"};
//        TabLikeLayout tabLikeLayout = new TabLikeLayout(this);
//        for(int i=0;i<colorStrings.length;i++) {
//            View coloredView = new ColoredFragment(this,Color.parseColor(colorStrings[i]),(i+1));
//            tabLikeLayout.addTab(new TabElement(coloredView, "tab" + (i + 1)));
//        }
//        tabLikeLayout.show();
//    }
//    public void showGroupImageButtons() {
//        GroupImageButtons groupImageButtons = new GroupImageButtons(this);
//        for(int i=0;i<5*images.length;i++) {
//            groupImageButtons.addImageButton(new GroupImageButton(BitmapFactory.decodeResource(getResources(),tImages[i%4])));
//        }
//        groupImageButtons.show();
//    }
//    public void showActionSheet() {
//        if(actionSheet == null) {
//            actionSheet = new ActionSheet(this);
//        }
//        actionSheet.setTitle("Some Actions");
//        addActions(actionSheet,"Delete","Add","Make","More");
//        actionSheet.show();
//    }
//    private void addActions(ActionSheet actionSheet,String ...actions) {
//        for(String action:actions) {
//            final String text = action;
//            actionSheet.addAction(action, new ActionSheet.ActionListener() {
//                @Override
//                public void doAction() {
//                    Toast.makeText(MainActivity.this,text,Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//    }
//    public void showBeanButtons() {
//        BeanButton beanButton = new BeanButton(this);
//        beanButton.show(200,200);
//    }
//    public void showCustomFloatingActionButton() {
//        CustomFloatingActionButton customFloatingActionButton = new CustomFloatingActionButton(this);
//        for(int i=0;i<images.length;i++) {
//            final int index = i;
//            ActionIcon actionIcon = new ActionIcon(BitmapFactory.decodeResource(getResources(),images[i]));
//            actionIcon.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(MainActivity.this,actions[index],Toast.LENGTH_LONG).show();
//                }
//            });
//            customFloatingActionButton.addActionIcon(actionIcon);
//        }
//        customFloatingActionButton.show();
//    }
//    public void showPathText() {
//        PathText pathText = new PathText(this,'H');
//        pathText.show(200,200);
//        PathText pathText1 = new PathText(this,'E');
//        pathText1.show(500,500);
//    }
//    public void showModakButton() {
//        ModakButton modakButton = new ModakButton(this);
//        modakButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this,"loaded completely",Toast.LENGTH_LONG).show();
//            }
//        });
//        modakButton.show(100,100);
//    }
//    public void showImageClipper() {
//        ImageClipper imageClipper = new ImageClipper(this,BitmapFactory.decodeResource(getResources(),tImages[0]), ImageClipperShape.CIRCLE_SHAPE);
//        imageClipper.show(100,100);
//        ImageClipper imageClipper1 = new ImageClipper(this,BitmapFactory.decodeResource(getResources(),tImages[1]),ImageClipperShape.RECT_SHAPE);
//        imageClipper1.show(500,500);
//        ImageClipper imageClipper2 = new ImageClipper(this,BitmapFactory.decodeResource(getResources(),tImages[2]),ImageClipperShape.TRIANGLE_SHAPE);
//        imageClipper2.show(900,900);
//    }
//    public void showStickyNotification() {
//        String notifText = "hello there lets see how it wraps up. Here are some insights on jobs done.But Lucas is enjoying his time here since his move from Deportivo la Coruna";
//        StickyNotification stickyNotification = new StickyNotification(this,notifText, BitmapFactory.decodeResource(getResources(),R.drawable.penguin));
//        stickyNotification.show();
//    }
//    public void showAlphaImageSwitch() {
//        AlphaImageSwitch alphaImageSwitch = new AlphaImageSwitch(this);
//        int index = 0;
//        for(int tImage:tImages) {
//            final String text = actions[index];
//            AlphaImageSwitchButton alphaImageSwitchButton = new AlphaImageSwitchButton(BitmapFactory.decodeResource(getResources(),tImage));
//            alphaImageSwitchButton.setSelectedLisenter(new SelectedListner() {
//                @Override
//                public void onSelected() {
//                    Toast.makeText(MainActivity.this,text,Toast.LENGTH_SHORT).show();
//                }
//            });
//            alphaImageSwitch.addImageSwitchButton(alphaImageSwitchButton);
//            index++;
//        }
//        alphaImageSwitch.show(300);
//    }
//    public void showLockButtons() {
//        LockButton lockButton = new LockButton(this, LockButtonType.ROUNDRECTANGLE);
//        lockButton.show(200,200);
//        lockButton.setLockListener(new LockListener() {
//            @Override
//            public void onOpen() {
//                Toast.makeText(MainActivity.this, "OPENED", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onClose() {
//                Toast.makeText(MainActivity.this, "CLOSE", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//    public void showTricSwitch() {
//        TricSwitch tricSwitch = new TricSwitch(this);
//        for(int i=0;i<3;i++) {
//            final String text = actions[i];
//            TriCircButton triCircButton = TriCircButton.newInstance();
//            triCircButton.setOnSelectedListener(new OnSelectedListener() {
//                @Override
//                public void onSelected() {
//                    Toast.makeText(MainActivity.this,text,Toast.LENGTH_SHORT).show();
//                }
//            });
//            tricSwitch.addTricButton(triCircButton);
//        }
//        tricSwitch.show(200);
//    }
//    public void showLeanInputContainer() {
//        LeanInputContainer leanInputContainer = new LeanInputContainer(this);
//        leanInputContainer.setOnSubmitListener(new OnSubmitListener() {
//            @Override
//            public void onSubmit(String text) {
//                Intent intent = new Intent(MainActivity.this,TextResultActivity.class);
//                intent.putExtra("text_result",text);
//                startActivity(intent);
//            }
//        });
//        leanInputContainer.show();
//    }
//    public void showMirrorImageButton() {
//        final MirrorImageButton mirrorImageButton = new MirrorImageButton(this,BitmapFactory.decodeResource(getResources(),tImages[2]));
//        mirrorImageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this,"Mirror!",Toast.LENGTH_SHORT).show();
//            }
//        });
//        mirrorImageButton.show(200,200);
//    }
//    public void showCircularContainerSwitch() {
//        CircularButtonContainer container = new CircularButtonContainer(this);
//        char chars[] = {'A','B','C','D'};
//        for(int i=0;i<tImages.length;i++) {
//            final String text = actions[i];
//            container.addCircularButton(BitmapFactory.decodeResource(getResources(), tImages[i]), chars[i], new SelectedListener() {
//                @Override
//                public void onSelected() {
//                    Toast.makeText(MainActivity.this,text,Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//        container.show();
//    }
//    public void showBarGraphButtons() {
//        BarGraphButton barGraphButton = new BarGraphButton(this);
//        for(int i=0;i<actions.length;i++) {
//            final String text = actions[i];
//            barGraphButton.addBarGraph(new com.anwesome.app.bargraphbutton.OnSelectedListener() {
//                @Override
//                public void onSelected() {
//                    Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//        barGraphButton.show();
//    }
//    public void showConcentricCircleSwitch() {
//        ConcentricCircleSwitch concentricCircleSwitch = new ConcentricCircleSwitch(this);
//        for(int i=0;i<actions.length;i++) {
//            final String text = actions[i];
//            concentricCircleSwitch.addButton(new com.anwesome.app.concentriccircleswitch.OnSelectedListener() {
//                @Override
//                public void onSelected() {
//                    Toast.makeText(MainActivity.this,text,Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//        concentricCircleSwitch.show();
//    }
//    public void showWidHolder() {
//        if(widHolder == null) {
//            widHolder = new WidHolder(this);
//            int i=0;
//            for (int tImage : tImages) {
//                final String text = actions[i];
//                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), tImage);
//                WidButton widButton = WidButton.newInstance(bitmap);
//                widButton.setWidOnClickListener(new WidOnClickListener() {
//                    @Override
//                    public void onClick() {
//                        Toast.makeText(MainActivity.this,text,Toast.LENGTH_LONG).show();
//                    }
//                });
//                widHolder.addWidButton(widButton);
//                i++;
//            }
//        }
//        widHolder.show();
//    }
//    public void showClockSwitch() {
//        ClockSwitch clockSwitch = new ClockSwitch(this);
//        for(String action:actions) {
//            final String text = action;
//            clockSwitch.addButton(new OnButtonSelected() {
//                @Override
//                public void onSelected() {
//                    Toast.makeText(MainActivity.this,text,Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//        clockSwitch.show();
//    }
//    public void showEllipticalSwitch() {
//        EllipticalSwitch ellipticalSwitch = new EllipticalSwitch(this);
//        for(String action:actions) {
//            final String text = action;
//            ellipticalSwitch.addSwitchObject(new com.anwesome.games.basicswitch.OnSelectedListener() {
//                @Override
//                public void onSelected() {
//                    Toast.makeText(MainActivity.this,text,Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//        ellipticalSwitch.show();
//    }
//    public void showMultipleCheckBoxes() {
//        MultipleCheckBox multipleCheckBox = new MultipleCheckBox(this);
//        for(String action:actions) {
//            multipleCheckBox.addCheckBox(action);
//        }
//        multipleCheckBox.show();
//    }
//    public void showCircledArrowButton() {
//        CircledArrowButton circledArrowButton = new CircledArrowButton(this);
//        circledArrowButton.setToggleSelectionListener(new ToggleSelectionListener() {
//            @Override
//            public void onSelected() {
//                Toast.makeText(MainActivity.this,"Selected",Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onUnselected() {
//                Toast.makeText(MainActivity.this,"Unselected",Toast.LENGTH_SHORT).show();
//            }
//        });
//        circledArrowButton.show(100,100);
//    }
//    public void showLatchButton() {
//        LatchButton latchButton = LatchButton.newInstance(this);
//        latchButton.setLatchSelectedListener(new LatchSelectedListener() {
//            @Override
//            public void onSelected() {
//                Toast.makeText(MainActivity.this,"Selected",Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onUnSelected() {
//                Toast.makeText(MainActivity.this,"Unselected",Toast.LENGTH_SHORT).show();
//            }
//        });
//        latchButton.show(200,200);
//    }
//    public void showFultonButton() {
//        FultonButton fultonButton = FultonButton.getInstance(this);
//        fultonButton.setOnOffListener(new OnOffListener() {
//            @Override
//            public void on() {
//                Toast.makeText(MainActivity.this, "On", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void off() {
//                Toast.makeText(MainActivity.this,"Off",Toast.LENGTH_SHORT).show();
//            }
//        });
//        fultonButton.show(200,200);
//    }
//    public void showSpinnyButton() {
//        SpinnyButton spinnyButton = SpinnyButton.getInstance(this);
//        spinnyButton.show(100,100);
//        spinnyButton.setToggleSpinnyListener(new ToggleSpinnyListener() {
//            @Override
//            public void onOpened() {
//                Toast.makeText(MainActivity.this,"Opened",Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onClosed() {
//                Toast.makeText(MainActivity.this,"Closed",Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//    public void showZoomShapedButton() {
//        ZoomShapeButton zoomShapeButton = new ZoomShapeButton(this);
//        zoomShapeButton.show(200,200);
//        zoomShapeButton.setOnOpenListener(new OnOpenListener() {
//            @Override
//            public void open() {
//                Toast.makeText(MainActivity.this,"Opened",Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void close() {
//                Toast.makeText(MainActivity.this, "Closed", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//    public void showImageBar() {
//        ImageBar imageBar = new ImageBar(this,BitmapFactory.decodeResource(getResources(),tImages[1]));
//        imageBar.show(100,100);
//        imageBar.setOnToggleListener(new OnToggleListener() {
//            @Override
//            public void show() {
//                Toast.makeText(MainActivity.this,"Hi!!",Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void hide() {
//                Toast.makeText(MainActivity.this,"Bye!!",Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//    public void showPlayPause() {
//        PlayPause playPause = new PlayPause(this);
//        playPause.setPlayPauseStateListener(new PlayPauseStateListener() {
//            @Override
//            public void play() {
//                Toast.makeText(MainActivity.this,"Play",Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void pause() {
//                Toast.makeText(MainActivity.this,"Pause",Toast.LENGTH_SHORT).show();
//            }
//        });
//        playPause.show(200,200);
//    }
//    public void showDotSpin() {
//        DotSpin dotSpin = new DotSpin(this);
//        dotSpin.show(100,100);
//        dotSpin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this,"Loaded",Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//    public void showTriPathButton() {
//        TriPathButton triPathButton = new TriPathButton(this);
//        triPathButton.show(200,200);
//        triPathButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//    public void showHamburgButton() {
//        HamburgerButton hamburgerButton = new HamburgerButton(this);
//        hamburgerButton.show(300,300);
//        hamburgerButton.setHamburgerListener(new HamburgerListener() {
//            @Override
//            public void onOpen() {
//                Toast.makeText(MainActivity.this,"Opened",Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onClose() {
//                Toast.makeText(MainActivity.this,"Closed",Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//    public void showDeleteButton() {
//        DeleteButton deleteButton = DeleteButton.getInstance(this);
//        deleteButton.show(300);
//        deleteButton.setDeleteButtonListener(new DeleteButtonListener() {
//            @Override
//            public void onDelete() {
//                Toast.makeText(MainActivity.this,"Deleted",Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//    public void showDataIndicator() {
//        DataIndicatorButton dataIndicatorButton = new DataIndicatorButton(this);
//        dataIndicatorButton.show(DataIndicationType.NETWORK_DATA,200,200);
//        dataIndicatorButton.setOnDataIndicatorSelectedListener(new OnDataIndicatorSelectedListener() {
//            @Override
//            public void onSelected() {
//                Toast.makeText(MainActivity.this,"Selected Mobile Network",Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onUnSelected() {
//                Toast.makeText(MainActivity.this,"Unselected Mobile Network",Toast.LENGTH_SHORT).show();
//            }
//        });
//        DataIndicatorButton dataIndicatorButton1 = new DataIndicatorButton(this);
//        dataIndicatorButton1.setOnDataIndicatorSelectedListener(new OnDataIndicatorSelectedListener() {
//            @Override
//            public void onSelected() {
//                Toast.makeText(MainActivity.this,"Selected Wifi Network",Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onUnSelected() {
//                Toast.makeText(MainActivity.this,"Unselected Wifi Network",Toast.LENGTH_SHORT).show();
//            }
//        });
//        dataIndicatorButton1.show(DataIndicationType.WIFI,500,500);
//        DataIndicatorButton dataIndicatorButton3 = new DataIndicatorButton(this);
//        dataIndicatorButton3.setOnDataIndicatorSelectedListener(new OnDataIndicatorSelectedListener() {
//            @Override
//            public void onSelected() {
//                Toast.makeText(MainActivity.this,"Selected Wifi PC Network",Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onUnSelected() {
//                Toast.makeText(MainActivity.this,"Unselected Wifi PC Network",Toast.LENGTH_SHORT).show();
//            }
//        });
//        dataIndicatorButton3.show(DataIndicationType.WIFI_PC,0,700);
//    }
//    public void showBreakableButton() {
//        BreakableButton breakableButton = new BreakableButton(this,"Need Button");
//        breakableButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this,"Breaking it",Toast.LENGTH_SHORT).show();
//            }
//        });
//        breakableButton.show(300,300);
//    }
//    public void showLiveStreamButton() {
//        LiveStreamButton liveStreamButton = new LiveStreamButton(this);
//        liveStreamButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
//            }
//        });
//        liveStreamButton.show(200,200);
//    }
//    public void showSettingsButton() {
//        SettingsButton settingsButton = new SettingsButton(this);
//        settingsButton.show(200,200);
//        settingsButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//    public void showPlaybackButon() {
//        PlaybackButton replaybutton = new PlaybackButton(this, PlaybackButtonType.REPLAY);
//        replaybutton.show(200,200);
//        PlaybackButton playbackcontrolbutton = new PlaybackButton(this, PlaybackButtonType.PLAYBACK_CONTROL);
//        playbackcontrolbutton.show(500,500);
//    }
//    public void showNotepadButton() {
//        NotepadButton notepadButton = new NotepadButton(this,"Hello world");
//        try {
//            notepadButton.show();
//        }
//        catch (Exception ex) {
//
//        }
//    }
//    public void showPinBar() {
//        PinBarButton pinBarButton = new PinBarButton(this);
//        pinBarButton.show(200,200);
//    }
//    public void initAndroidLoaderButton() {
//        if(androidLoaderButton == null) {
//            androidLoaderButton = new AndroidLoaderButton(this);
//        }
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                int time = 0;
//                while(time<6) {
//                    try {
//                        Thread.sleep(1000);
//                    }
//                    catch (Exception ex) {
//
//                    }
//                    time++;
//                }
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        androidLoaderButton.dismiss();
//                    }
//                });
//            }
//        };
//        androidLoaderButton.show();
//        new Thread(runnable).start();
//    }
//    public void drawPowerButton() {
//        PowerButton powerButton = new PowerButton(this);
//        powerButton.show(300,300);
//        powerButton.setPowerButtonListener(new PowerButtonListener() {
//            @Override
//            public void on() {
//                Toast.makeText(MainActivity.this, "On", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void off() {
//                Toast.makeText(MainActivity.this, "Off", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//    public void showMessageUi() {
//        MessageUIButton messageUIButton = new MessageUIButton(this);
//        messageUIButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "New Message", Toast.LENGTH_SHORT).show();
//            }
//        });
//        messageUIButton.show(200,200);
//    }
//    public void showSyncButton() {
//        SyncButton syncButton = new SyncButton(this);
//        syncButton.show(300,300);
//    }
//    public void showShareButton() {
//        ShareButton shareButton = new ShareButton(this);
//        shareButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "Share", Toast.LENGTH_SHORT).show();
//            }
//        });
//        shareButton.show(200,200);
//    }
//    public void showEmergencyButton() {
//        EmergencyButton emergencyButton = new EmergencyButton(this);
//        emergencyButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "Emergency", Toast.LENGTH_SHORT).show();
//            }
//        });
//        emergencyButton.show();
//    }
//    public void showCalButton() {
//        CalButton calButton = new CalButton(this);
//        calButton.show(200,200);
//        calButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//    public void showFootiePitch() {
//        FootiePitchButton footiePitchButton = new FootiePitchButton(this);
//        footiePitchButton.show(200,200);
//    }
//    public void showLikeButton() {
//        LikeButton likeButton = new LikeButton(this);
//        likeButton.show(200,200);
//        likeButton.setOnLikeChangeListener(new OnLikeChangeListener() {
//            @Override
//            public void onLike() {
//                Toast.makeText(MainActivity.this, "Liked", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onUnLike() {
//                Toast.makeText(MainActivity.this, "UnLiked", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//    public void showNotificationButton() {
//        NotificationButton notificationButton = new NotificationButton(this,4);
//        notificationButton.show(500,500);
//        notificationButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "Creating Toast", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//    public void showRecordButton() {
//        RecordButton recordButton = new RecordButton(this);
//        recordButton.show(200,200);
//        recordButton.setOnRecordButtonClickListener(new OnRecordButtonClickListener() {
//            @Override
//            public void onSelect() {
//                Toast.makeText(MainActivity.this, "Selected", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onUnselect() {
//                Toast.makeText(MainActivity.this, "Unselected", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//    public void showMuteButton() {
//        MuteButton muteButton = new MuteButton(this);
//        muteButton.setMuteClickListener(new MuteClickListener() {
//            @Override
//            public void onMute() {
//                Toast.makeText(MainActivity.this, "Mute", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onUnmute() {
//                Toast.makeText(MainActivity.this, "OnUnmute", Toast.LENGTH_SHORT).show();
//            }
//        });
//        muteButton.show();
//    }
//    public void showFullScreenButton() {
//        FullScreenButton fullScreenButton = new FullScreenButton(this);
//        fullScreenButton.setOnFullButtonClickListener(new FullScreenButtonShape.OnFullButtonClickListener() {
//            @Override
//            public void onexpand() {
//                Toast.makeText(MainActivity.this, "Expanded", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onshrink() {
//                Toast.makeText(MainActivity.this, "Shrinked", Toast.LENGTH_SHORT).show();
//            }
//        });
//        fullScreenButton.show(300,300);
//    }
//    public void showMessengerButton() {
//        MessengerButton messengerButton = new MessengerButton(this);
//        messengerButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "Message", Toast.LENGTH_SHORT).show();
//            }
//        });
//        messengerButton.show(300,300);
//    }
//    public void showBluetoothButton() {
//        BluetoothButton bluetoothButton = new BluetoothButton(this);
//        bluetoothButton.setOnSelectionChangeListener(new BluetoothButtonShape.OnSelectionChangeListener() {
//            @Override
//            public void onSelect() {
//                Toast.makeText(MainActivity.this, "Selected", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onUnselect() {
//                Toast.makeText(MainActivity.this, "UnSelected", Toast.LENGTH_SHORT).show();
//            }
//        });
//        bluetoothButton.show(300,300);
//    }
//    public void showFsArrowButton() {
//        FsArrowButton fsArrowButton = new FsArrowButton(this);
//        fsArrowButton.setOnExpandListener(new FsArrowButtonShape.OnExpandListener() {
//            @Override
//            public void onExpand() {
//                Toast.makeText(MainActivity.this, "Expanded", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onShrink() {
//                Toast.makeText(MainActivity.this, "Shrink", Toast.LENGTH_SHORT).show();
//            }
//        });
//        fsArrowButton.show();
//    }
//    public void showTrashButton() {
//        TrashButton trashButton = new TrashButton(this);
//        trashButton.show(200,200);
//        trashButton.setTrashButtonOnClickListener(new TrashButtonShape.TrashButtonClickListener() {
//            @Override
//            public void onClick() {
//                Toast.makeText(MainActivity.this, "Collect Trash", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//    public void showNineSquare() {
//        NineSquare nineSquare = new NineSquare(this);
//        nineSquare.setOnOpenCloseListener(new OnOpenCloseListener() {
//            @Override
//            public void onOpen() {
//                Toast.makeText(MainActivity.this, "Opened", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onClose() {
//                Toast.makeText(MainActivity.this, "Closed", Toast.LENGTH_SHORT).show();
//            }
//        });
//        nineSquare.show(300,300);
//    }
//    public void showWatchLikeButton() {
//        WatchLikeButton watchLikeButton = new WatchLikeButton(this);
//        watchLikeButton.show();
//        watchLikeButton.setX(300);
//        watchLikeButton.setY(300);
//        watchLikeButton.setWatchClickListener(new WatchClickListener() {
//            @Override
//            public void onClick() {
//                Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }
//    public void showClockButton() {
//        final TextView textView = new TextView(this);
//        textView.setX(200);
//        textView.setTextSize(30);
//        addContentView(textView,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        final ClockButton clockButton = new ClockButton(this);
//        clockButton.show(300,300);
//        clockButton.setClockListener(new ClockListener() {
//            @Override
//            public void onProgress(int h, int m) {
//                textView.setText(clockButton.getTimeString());
//            }
//
//            @Override
//            public void onCompletion(int h, int m) {
//                Toast.makeText(MainActivity.this,clockButton.getTimeString(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//    public void showUiWindow() {
//        UiWindow uiWindow = new UiWindow(this);
//        uiWindow.setOnClickListener(new com.anwesome.ui.uiwindow.OnClickListener() {
//            @Override
//            public void onClick() {
//                Toast.makeText(MainActivity.this, "Showing UIWindow", Toast.LENGTH_SHORT).show();
//            }
//        });
//        uiWindow.show(300,300);
//    }
//    public void showFillRingButton() {
//        FillRingButton fillRingButton = new FillRingButton(this);
//        fillRingButton.setOnFillChangeListener(new OnFillChangeListener() {
//            @Override
//            public void onFill() {
//                Toast.makeText(MainActivity.this, "Filled", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onUnFill() {
//                Toast.makeText(MainActivity.this, "UnFilled", Toast.LENGTH_SHORT).show();
//            }
//        });
//        fillRingButton.show(300,300);
//    }
//    public void showEyeButton() {
//        EyeButton eyeButton = new EyeButton(this);
//        eyeButton.setOnClickListener(new com.anwesome.ui.eyebutton.OnClickListener() {
//            @Override
//            public void onClick() {
//                Toast.makeText(MainActivity.this, "Eye Clicked", Toast.LENGTH_SHORT).show();
//            }
//        });
//        eyeButton.show(400,400);
//    }
//    public void showDirectionButton() {
//        DirectionButton directionButton = new DirectionButton(this);
//        directionButton.setOnDirectionChangeListener(new OnDirectionChangeListener() {
//            @Override
//            public void onPointingToLeft() {
//                Toast.makeText(MainActivity.this, "Left", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onPointingToRight() {
//                Toast.makeText(MainActivity.this, "Right", Toast.LENGTH_SHORT).show();
//            }
//        });
//        directionButton.show(300,300);
//    }
//    public void showGpButton() {
//        GpButton gpButton = new GpButton(this);
//        gpButton.setOnSizeChangeListener(new OnSizeChangeListener() {
//            @Override
//            public void onIncrease() {
//                Toast.makeText(MainActivity.this, "Increased", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onDecrease() {
//                Toast.makeText(MainActivity.this, "Decreased", Toast.LENGTH_SHORT).show();
//            }
//        });
//        gpButton.show();
//    }
//    public void showButtonGroup() {
//        ButtonGroup buttonGroup = new ButtonGroup(this);
//        String titles[] = {"Hello","Hi","Ok","Show","Stick","Pick","Pluck"};
//        for(String title:titles) {
//            final String currTitle = title;
//            buttonGroup.addButton(title, new OnSelectionListener() {
//                @Override
//                public void onSelected() {
//                    Toast.makeText(MainActivity.this, currTitle+" is Selected", Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void onUnSelected() {
//                    Toast.makeText(MainActivity.this, currTitle+" is UnSelected", Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//        buttonGroup.show();
//    }
//    public void showDotBarSwitch() {
//        DotbarSwitch dotbarSwitch = new DotbarSwitch(this);
//        String[] titles = {"hello","hi","start","zero"};
//        for(int i=0;i<titles.length;i++) {
//            final String title = titles[i];
//            dotbarSwitch.addDotBar(new OnSelectionChangeListener() {
//                @Override
//                public void onSelected() {
//                    Toast.makeText(MainActivity.this, String.format("%s is selected",title), Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void onUnselected() {
//                    Toast.makeText(MainActivity.this, String.format("%s is unselected",title), Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//        dotbarSwitch.show();
//    }
//    public void showMinusToPause() {
//        MinusToPause minusToPause = new MinusToPause(this);
//        minusToPause.show(300,300);
//    }
//    public void showTicTacToeList() {
//        TicTacTowFilterViewList ticTacTowFilterViewList = new TicTacTowFilterViewList(this);
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.penguin);
//        for(int i=0;i<12;i++) {
//            final int index = (i+1);
//            ticTacTowFilterViewList.addImage(bitmap, new com.anwesome.ui.tictactoefilterview.OnSelectionChangeListener() {
//                @Override
//                public void onSelect() {
//                    Toast.makeText(MainActivity.this, String.format("%d is selected",index), Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void onUnSelect() {
//                    Toast.makeText(MainActivity.this, String.format("%d is unselected",index), Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//        ticTacTowFilterViewList.show();
//    }
//    public void showYstButtonList() {
//        YstButtonList ystButtonList = new YstButtonList(this);
//        for(int i=0;i<6;i++) {
//            final int index = i+1;
//            ystButtonList.addButton(new com.anwesome.ui.ystbuttonlist.OnSelectionChangeListener() {
//                @Override
//                public void onSelect() {
//                    Toast.makeText(MainActivity.this, String.format("%d selected",index), Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void onUnSelect() {
//                    Toast.makeText(MainActivity.this, String.format("%d unselected",index), Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//        ystButtonList.show();
//    }
//    public void showCYSTButtonList() {
//        CYSTList cystList = new CYSTList(this);
//        for(int i=0;i<6;i++) {
//            final int index = i+1;
//            cystList.addButton(new OnSelectionChangeListener() {
//                @Override
//                public void onSelect() {
//                    Toast.makeText(MainActivity.this, index+" Selected", Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void onUnselect() {
//                    Toast.makeText(MainActivity.this, index+" UnSelected", Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//        cystList.show();
//    }
//    public void showTVButton() {
//        TVButtonList tvButtonList = new TVButtonList(this);
//        for(int i=0;i<9;i++) {
//            final int index = i+1;
//            tvButtonList.addButton(new com.anwesome.ui.tvbutton.OnSelectionChangeListener() {
//                @Override
//                public void onSelect() {
//                    Toast.makeText(MainActivity.this, String.format("%d selected",index), Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void onUnSelect() {
//                    Toast.makeText(MainActivity.this, String.format("%d unselected",index), Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//        tvButtonList.show();
//    }
//    public void showProgressButton() {
//        ProgressButtonView.create(this, new OnClickListener() {
//            @Override
//            public void onFirstButtonSelected() {
//                Toast.makeText(MainActivity.this, "First Button Selected", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onSecondButtonSelected() {
//                Toast.makeText(MainActivity.this, "Second Button Selected", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFirstButtonUnSelected() {
//                Toast.makeText(MainActivity.this, "First Button UnSelected", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onSecondButtonUnSelected() {
//                Toast.makeText(MainActivity.this, "Second Button UnSelected", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//    public void showBridgeButton() {
//        BridgeButtonView.create(this, new BridgeButtonView.OnBridgeListener() {
//            @Override
//            public void onBridge() {
//                Toast.makeText(MainActivity.this, "On Bridge", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onUnBridge() {
//                Toast.makeText(MainActivity.this, "On Unbridge", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//    public void showLineAndDot() {
//        LineAndDotView.create(this, 8, new LineAndDotView.OnSelectedListener() {
//            @Override
//            public void onSelected(int index) {
//                Toast.makeText(MainActivity.this,String.format("%d selected",index),Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onUnSelected(int index) {
//                Toast.makeText(MainActivity.this, String.format("%d unselected",index), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }
    public void showHolderView() {
        HolderView.create(this, new HolderView.OnFillEmptyListener() {
            @Override
            public void onFill() {
                Toast.makeText(MainActivity.this, "OnFilled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onEmpty() {
                Toast.makeText(MainActivity.this, "OnEmoty", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void showPlusMinusView() {
        PlusMinusLineView.create(this, new PlusMinusLineView.OnOpenCloseListener() {
            @Override
            public void onOpen() {
                Toast.makeText(MainActivity.this, "On Opened", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClose() {
                Toast.makeText(MainActivity.this, "On Closed", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void showRectButton() {
        RectButtonView.create(this, new RectButtonView.OnSizeChangeListener() {
            @Override
            public void onExpand() {
                Toast.makeText(MainActivity.this, "OnExpanded", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onShrink() {
                Toast.makeText(MainActivity.this, "OnShrinked", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void showHoldFiller() {
        HoldFillerView.create(this, new HoldFillerView.OnFillCompleteListener() {
            @Override
            public void onFillComplete() {
                Toast.makeText(MainActivity.this, "Filled", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void showPlusButtonRect() {
        PlusButtonRectView.create(this, new PlusButtonRectView.OnExpandListener() {
            @Override
            public void onExpand() {
                Toast.makeText(MainActivity.this, "OnExpanded", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showCornerCenterBall() {
        CornerCenterBallView.create(this);
    }

    public void showCircMover() {
        CircMoverView.create(this, 5, new CircMoverView.OnMovementCompletionListener() {
            @Override
            public void onMoveToRight(int index) {
                Toast.makeText(MainActivity.this, String.format("%d moved to right",index), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMoveToLeft(int index) {
                Toast.makeText(MainActivity.this, String.format("%d moved to left",index), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void showShareRotButton() {
        ShareRotButton.create(this, new ShareRotButton.OnClickListener() {
            @Override
            public void onClick() {
                Toast.makeText(MainActivity.this, "Clicked Share Button", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void showArrowLineButton() {
        ArrowLineButton.create(this, new ArrowLineButton.OnOpenCloseListener() {
            @Override
            public void onOpen() {
                Toast.makeText(MainActivity.this, "Opened", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClose() {
                Toast.makeText(MainActivity.this, "Closed", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void showColorBarStack() {
        int[] colors = {Color.parseColor("#f44336"),Color.parseColor("#26A69A"),Color.parseColor("#1E88E5"),Color.parseColor("#C2185B")};
        ColorBarStack.create(this, BitmapFactory.decodeResource(getResources(), R.drawable.penguin), colors, new ColorBarStack.OnOpenCloseColorListener() {
            @Override
            public void onOpen(int index, int color) {
                Toast.makeText(MainActivity.this, String.format("Opened %d color",index), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClose(int index, int color) {
                Toast.makeText(MainActivity.this, String.format("Closed %d color",index), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void showCircularColorStack() {
        int[] colors = {Color.parseColor("#f44336"),Color.parseColor("#26A69A"),Color.parseColor("#1E88E5"),Color.parseColor("#C2185B")};
        CircularColorStackView.create(this, BitmapFactory.decodeResource(getResources(), R.drawable.penguin), colors);
    }
    public void showImageColorGrid() {
        ImageColorGridView.create(this,BitmapFactory.decodeResource(getResources(),R.drawable.penguin));
    }
    public void showCircGridColorImage() {
        CircGridColorImageView.create(this, BitmapFactory.decodeResource(getResources(), R.drawable.penguin), new CircGridColorImageView.OnSelectionChangeListener() {
            @Override
            public void onSelect(int index) {
                Toast.makeText(MainActivity.this, String.format("%d selected",index), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onUnSelect(int index) {
                Toast.makeText(MainActivity.this, String.format("%d unselected",index), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void showPacGridView() {
        PacGridView.create(this, BitmapFactory.decodeResource(getResources(), R.drawable.penguin), new PacGridView.OnClickListener() {
            @Override
            public void onClick(int index) {
                Toast.makeText(MainActivity.this, String.format("%d clicked",index), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void showSweepColorBitmap() {
        int colors[] = {Color.RED,Color.GREEN,Color.BLUE,Color.CYAN,Color.BLACK,Color.YELLOW};
        SweepColorBitmapView.create(this, BitmapFactory.decodeResource(getResources(), R.drawable.penguin), colors, new SweepColorBitmapView.OnOpenCloseListener() {
            @Override
            public void onOpen(int index) {
                Toast.makeText(MainActivity.this,String.format("color at %d opened",index),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClose(int index) {
                Toast.makeText(MainActivity.this,String.format("color at %d closed",index),Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void showBiCircColorView() {
        int colors[] = {Color.RED,Color.GREEN,Color.BLUE,Color.CYAN,Color.BLACK,Color.YELLOW};
        BiCircColorView.create(this, colors, new BiCircColorView.OnOpenCloseListener() {
            @Override
            public void onOpen(int index) {
                Toast.makeText(MainActivity.this, String.format("opened %d",index), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClose(int index) {
                Toast.makeText(MainActivity.this, String.format("closed %d",index), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void showLineDeg() {
        int colors[] = {Color.RED,Color.GREEN,Color.BLUE,Color.CYAN,Color.BLACK,Color.YELLOW};
        LineDegColorView.create(this, colors, new LineDegColorView.OnExpandShrinkListener() {
            @Override
            public void onExpand(int index) {
                Toast.makeText(MainActivity.this, String.format("expanded %d",(index+1)), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onShrink(int index) {
                Toast.makeText(MainActivity.this, String.format("shrinked %d",(index+1)), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void showCircFourView() {
        CircFourBitmapView.create(this,BitmapFactory.decodeResource(getResources(),R.drawable.t2));
    }
}

