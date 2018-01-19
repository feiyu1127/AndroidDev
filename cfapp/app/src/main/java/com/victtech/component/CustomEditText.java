package com.victtech.component;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

import com.victtech.cfapp.R;

/**
 * Created by Richard on 2018/1/17.
 */

public class CustomEditText extends AppCompatEditText {
    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setBackground(getResources().getDrawable(R.drawable.bg_edittext));

    }
}
