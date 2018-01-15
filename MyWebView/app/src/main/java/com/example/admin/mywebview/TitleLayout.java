package com.example.admin.mywebview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by admin on 2018/1/15.
 */

public class TitleLayout extends LinearLayout implements View.OnClickListener{
    public TitleLayout(Context context, AttributeSet attrs){
        super(context,attrs);
        LayoutInflater.from(context).inflate(R.layout.title,this);

        Button exitButton = findViewById(R.id.exit_button);
        Button returnButton = findViewById(R.id.return_button);

        exitButton.setOnClickListener(this);
        returnButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.exit_button:
                ActivityCollector.finishAll();
                break;
            case R.id.return_button:

                break;
            default:
                break;
        }
    }
}
