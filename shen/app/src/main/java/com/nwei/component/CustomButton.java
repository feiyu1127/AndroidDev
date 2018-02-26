package com.nwei.component;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import com.nwei.R;

/**
 * Created by admin on 2018/2/26.
 */

public class CustomButton extends AppCompatButton{

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setBackground(getResources().getDrawable(R.drawable.button_shape));
        this.setTextColor(getResources().getColor(R.color.white)); //白色字体
    }
}
