package com.yucheng.apfx.utl;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtil {
  public NetworkUtil() {
  }

  /**
   * 检测WiFi网是否连接
   * 
   * @param ctx
   *          上下文
   * @return 是否连接
   */
  public static boolean isWifiConnected(Context ctx) {
    ConnectivityManager cm = (ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
    if (null != cm) {
      NetworkInfo ni = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
      if (null != ni) {
        return ni.isConnected();
      }
    }
    return false;
  }

  /**
   * 检测移动网是否连接
   * 
   * @param ctx
   *          上下文
   * @return 是否连接
   */
  public static boolean isMobiConnected(Context ctx) {
    ConnectivityManager cm = (ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
    if (null != cm) {
      NetworkInfo ni = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
      if (null != ni) {
        return ni.isConnected();
      }
    }
    return false;
  }

  /**
   * 检测网络是否连接
   * 
   * @param ctx
   *          上下文
   * @return 是否连接
   */
  public static boolean isNetConnected(Context ctx) {
    ConnectivityManager cm = (ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
    if (null != cm) {
      NetworkInfo[] nis = cm.getAllNetworkInfo();
      if (null != nis) {
        for (NetworkInfo ni : nis) {
          if (ni.isConnected()) {
            return true;
          }
        }
      }
    }
    return false;
  }
}
