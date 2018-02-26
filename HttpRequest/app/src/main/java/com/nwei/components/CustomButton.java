package com.nwei.components;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import com.nwei.httprequest.R;

/**
 * Created by admin on 2018/2/26.
 */

public class CustomButton extends AppCompatButton {

    public CustomButton(Context context, AttributeSet attrs) {
        super(context,attrs);
        this.setBackground(getResources().getDrawable(R.drawable.custom_bg_button));
        this.setTextColor(getResources().getColor(R.color.white)); //白色字体
    }
}
