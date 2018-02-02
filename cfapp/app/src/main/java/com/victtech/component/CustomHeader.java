package com.victtech.component;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.victtech.cfapp.R;

/**
 * Created by Richard on 2018/1/23.
 */

public class CustomHeader extends LinearLayout {
    private Toolbar toolbar;
    public CustomHeader(final Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.normal_header,this);
        toolbar = findViewById(R.id.normal_header);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark,null));
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorText,null));
        toolbar.setTitle("处方系统");
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_keyboard_arrow_left_white_24dp,null));
        toolbar.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)context).finish();
            }
        });
    }

    public void setTitle(String title){
        toolbar.setTitle(title);
    }

}
