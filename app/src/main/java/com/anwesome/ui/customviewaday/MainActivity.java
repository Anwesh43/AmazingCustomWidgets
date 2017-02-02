package com.anwesome.ui.customviewaday;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.anwesome.ui.polygonaltraverseview.PolygonalTraverseView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PolygonalTraverseView ppView = (PolygonalTraverseView)findViewById(R.id.ppview);
        ppView.setN(6);
        ppView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Hello", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
