package com.example.richard.webview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

/**
 * Created by Richard on 2018/1/2.
 */

public class BaseLayout extends LinearLayout {
    Header header;

    public BaseLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.base_layout,this);
        header = findViewById(R.id.base_header);
    }

    public void setHeader(String header_txt){
        header.setheaderTxt(header_txt);
    }
}
