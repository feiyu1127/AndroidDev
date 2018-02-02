package com.victtech.component;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;

/**
 * Created by Richard on 2018/1/23.
 */

public class CustomLinerLayoutManager extends LinearLayoutManager {
    private boolean isScrollEnable = true;
    public CustomLinerLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public CustomLinerLayoutManager(Context context) {
        super(context);
    }


    public void setScrollEnable(boolean scrollEnable) {
        isScrollEnable = scrollEnable;
    }

    @Override
    public boolean canScrollVertically() {
        return isScrollEnable && super.canScrollVertically();
    }
}
