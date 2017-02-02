package com.anwesome.ui.customviewaday;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.anwesome.ui.polygonaltraverseview.PolygonalTraverseView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PolygonalTraverseView ppView = (PolygonalTraverseView)findViewById(R.id.ppview);
        ppView.setN(6);
    }
}
