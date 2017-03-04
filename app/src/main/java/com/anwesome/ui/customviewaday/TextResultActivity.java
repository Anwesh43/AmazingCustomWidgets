package com.anwesome.ui.customviewaday;

import android.support.v7.app.AppCompatActivity;
import android.os.*;
import android.widget.TextView;

/**
 * Created by anweshmishra on 04/03/17.
 */
public class TextResultActivity extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_text_layout);
        TextView textView = (TextView)findViewById(R.id.text_result);
        if(getIntent()!=null && getIntent().getExtras()!=null) {
            String text = getIntent().getExtras().getString("text_result");
            textView.setText(text);
        }
    }
}
