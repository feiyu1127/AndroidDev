package com.yucheng.byxf.mini.utils;

public class ButtonUtil {
	/**
	 * @author Administrator
	 * 防止暴力点击
	 */
	private static long lastClickTime;  
	   public static boolean isFastDoubleClick() {  
      long time = System.currentTimeMillis();     
        if ( time - lastClickTime < 500) {     
           return true;     
        	}     
	        lastClickTime = time;     
	      return false;     
	   }  

}
