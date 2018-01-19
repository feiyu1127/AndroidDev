package com.yucheng.byxf.mini.utils;

import android.graphics.Bitmap;

public interface OnImageLoadDownListener {

    /**
     * 下载结束
     * @param retBmp
     * @param index
     */
	public void OnFinished(Bitmap retBmp,int index);
}
