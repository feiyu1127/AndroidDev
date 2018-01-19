package com.yucheng.byxf.mini.utils;

import java.util.HashMap;
import java.util.Map;

import com.yucheng.byxf.mini.message.BannerResponse;
import com.yucheng.byxf.mini.message.CustInfoResponseResult;
import com.yucheng.byxf.mini.message.LoginResponse;

import android.content.Intent;

public class ContentsMoney {

	public static Map<String,String> MapMoney=new HashMap<String, String>();

	public static Map<String, String> getMapMoney() {
		return MapMoney;
	}

	public static void setMapMoney(Map<String, String> mapMoney) {
		MapMoney = mapMoney;
	}
}
