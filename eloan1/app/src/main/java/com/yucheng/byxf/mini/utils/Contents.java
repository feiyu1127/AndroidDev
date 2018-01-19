package com.yucheng.byxf.mini.utils;

import com.yucheng.byxf.mini.message.BannerResponse;
import com.yucheng.byxf.mini.message.CustInfoResponseResult;
import com.yucheng.byxf.mini.message.LoginResponse;
import com.yucheng.byxf.mini.message.XiaoChaXunResult2;
 

import android.content.Intent;

public class Contents {
	/**控制所有校验的显示**/
	public static final boolean VERIFT  = true;
	/** 标示图片是否上传**/
	public static boolean ISUPLOAD  = false;
	/** 异常标示 **/
	public static final int ISEXCEPTION = 11111;
	/** 图片1返回标示 **/
	public static final int IMAGEVIEW_1 = 101;
	/** 图片2返回标示 **/
	public static final int IMAGEVIEW_2 = 102;
	/** 图片3返回标示 **/
	public static final int IMAGEVIEW_3 = 103;
	/** 图片4返回标示 **/
	public static final int IMAGEVIEW_4 = 104;
	/** 图片5返回标示 **/
	public static final int IMAGEVIEW_5 = 105;
	/** 已填数据保存标示 **/
	public static boolean IS_SAVE = false;
	/** 是否登录标识 **/
	public static boolean IS_LOGIN = false;
	/** 存储客户信息 **/
	public static LoginResponse response = null;
	/** 存储用户名信息 **/
	public static String username = "";
	/** 存储老用户信息 **/
	public static CustInfoResponseResult custInfoResponseResult = null;
	
	public static int width;
	public static int height;
	
	public static String locationInfo = null;
	
	public static String city = null;
	public static String area = null;
	public static String province = null;
	
	public static String latitude = null;
	public static String longitude = null;
	public static String poetry_str = null;
	/** 设备唯一标示 **/
	public static String iMei = null;
	
	/** 是否是打回操作 **/
	public static boolean isChoice = false;
	/** 是否需要验证操作操作 **/
	public static boolean isChoiceMsgKey = true;
	/** 流水号相关 **/
	public static String applCde;
	public static String applSeq;
	public static String applDisbSeq;
	public static String applRpymSeq;
	public static String apptSeq;
	/** 是否标识 **/
	public static int IS_Loc = -1;
	/** 是否开启锁屏 **/
	public static int IS_LocStart = -1;
	/** 存储xiaojinying信息 **/
	public static XiaoChaXunResult2 xiaoChaXunResult2 = null;
	/** 存储卡号信息 **/
	public static String xiaoChaXunCardNo = "";
}
