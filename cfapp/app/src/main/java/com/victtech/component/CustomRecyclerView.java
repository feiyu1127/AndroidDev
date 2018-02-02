package com.victtech.component;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.victtech.tools.LogUtil;

/**
 * Created by Richard on 2018/1/18.
 */

public class CustomRecyclerView extends RecyclerView {
    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogUtil.d("************", "dispatchTouchEvent: ");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        LogUtil.d(")))))))))))))))", "onTouchEvent: ");
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        LogUtil.d("((((((((((((((((((((", "onInterceptTouchEvent: ");
        return super.onInterceptTouchEvent(e);
    }
}
