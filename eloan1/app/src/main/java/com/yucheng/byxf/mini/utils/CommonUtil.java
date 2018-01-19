package com.yucheng.byxf.mini.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.service.LoginSaveInfo;

public class CommonUtil {
	private static Toast toast;

	/**
	 * 自定义toast 显示在中间
	 * 
	 * @param context
	 *            请求上下文
	 * @param string
	 *            显示内容
	 */
	public static void commonToast(Context context, String string) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View layout = inflater.inflate(R.layout.toast_define, null);
		TextView tishi_text = (TextView) layout.findViewById(R.id.tishi_text);
		tishi_text.setText(string);
		if (toast == null) {
			toast = new Toast(context);
		} else {
			cancelToast();
			toast = new Toast(context);
		}
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setView(layout);
		toast.show();

	}

	public static void cancelToast() {
		if (toast != null) {
			toast.cancel();
		}
	}

	/**
	 * 公共的spinner
	 * @param mContext
	 * @param array
	 * @param sp
	 */
	public static void setSpinner(Context mContext, String[] array, Spinner sp) {
		ArrayAdapter<String> commSpinnerAdapter = null;
		
		commSpinnerAdapter = new ArrayAdapter<String>(mContext,
				R.layout.common_simple_spinner_item, array);
		commSpinnerAdapter
				.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		sp.setAdapter(commSpinnerAdapter);
		// sp.setPrompt("请选择");
	}

	/**
	 * 是否是网银内部的人员，是为true,不是为false
	 * 
	 * @return
	 */
	public static boolean isBYInsideMen() {
		if ("999999999".equals(LoginSaveInfo.orgNo)
				|| LoginSaveInfo.orgNo == null) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 是否能够进行直接支付，是为true,不是为false
	 * 
	 * @return
	 */
	public static boolean isDirectPay() {
		if ("Y".equals(LoginSaveInfo.isDirectPay)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static String getVersion(Context context)//获取版本号
	{
		try {
			PackageInfo pi=context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			return pi.versionName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "0";
		}
	}

}
