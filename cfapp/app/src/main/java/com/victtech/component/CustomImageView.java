package com.victtech.component;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.victtech.cfapp.R;

/**
 * Created by Richard on 2018/1/23.
 */

public class CustomImageView extends AppCompatImageView {
    public CustomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Glide.with(context).load(R.drawable.loading).into(this);
//        this.setImageDrawable(getResources().getDrawable(R.drawable.loading));
    }
}
