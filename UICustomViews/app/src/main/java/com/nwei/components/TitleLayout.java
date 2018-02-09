package com.nwei.components;


import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

/**
 * Created by admin on 2018/2/8.
 */

public class TitleLayout extends LinearLayout {
    public TitleLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
//        LayoutInflater.from(context).inflate() //from() 方法可以构建出一个对象, inflate() 可以动态加载一个布局文件,第一个参数是布局文件的ID,第二个参数是给布局文件再添加一个父布局
    }
}
