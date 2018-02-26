package com.nwei.component;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

import com.nwei.R;

/**
 * Created by admin on 2018/2/26.
 */

public class CustomEditText extends AppCompatEditText {
    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setBackground(getResources().getDrawable(R.drawable.edit_text_bg));

    }
}
