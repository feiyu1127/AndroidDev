package com.victtech.component;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import com.victtech.cfapp.R;

/**
 * Created by Richard on 2018/1/17.
 */

public class CustomButton extends AppCompatButton {
    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setBackground(getResources().getDrawable(R.drawable.bg_button));
    }
}
