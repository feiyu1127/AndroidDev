package com.victtech.component;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.victtech.tools.LogUtil;

/**
 * Created by Richard on 2018/1/17.
 */

public class CustomViewPager extends ViewPager {
    private boolean noScroll = false;
    public CustomViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomViewPager(@NonNull Context context) {
        super(context);
    }

    public void setNoScroll(boolean noScroll) {
        this.noScroll = noScroll;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogUtil.d("===============", "dispatchTouchEvent: ===================");
        return super.dispatchTouchEvent(ev);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LogUtil.d("--------", String.valueOf(noScroll) );
        if(noScroll){
            return false;
        }else{
            return super.onInterceptTouchEvent(ev);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        LogUtil.d("++++++++++++", "+++++++++++");
        return super.onTouchEvent(ev);
//        if(noScroll){
//            return false;
//        }else{
//            return super.onTouchEvent(ev);
//        }
    }


}
