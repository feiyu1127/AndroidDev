package com.victtech.component;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by Richard on 2018/1/18.
 */

public class CustomRecyclerView extends RecyclerView {
    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("************", "dispatchTouchEvent: ");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        Log.d(")))))))))))))))", "onTouchEvent: ");
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        Log.d("((((((((((((((((((((", "onInterceptTouchEvent: ");
        return super.onInterceptTouchEvent(e);
    }
}
