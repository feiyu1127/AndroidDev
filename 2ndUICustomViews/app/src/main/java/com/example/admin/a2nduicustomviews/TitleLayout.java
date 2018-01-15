package com.example.admin.a2nduicustomviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

/**
 * Created by admin on 2018/1/15.
 */

public class TitleLayout extends LinearLayout {
    public  TitleLayout(Context context, AttributeSet attrs){
        super(context,attrs);
        LayoutInflater.from(context).inflate(R.layout.title,this);
    }
}
