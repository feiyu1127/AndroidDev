package com.yucheng.byxf.mini.easyloan.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPreferencesUtils {
	private SharedPreferences sp = null;
	public SharedPreferencesUtils(Context context) {
		// TODO Auto-generated constructor stub
		this.sp = PreferenceManager.getDefaultSharedPreferences(context);
	}
	// 首选项存值。
	public void setData(String key, Object value) {
		SharedPreferences.Editor editor = this.sp.edit();
		if (value instanceof String) {
			editor.putString(key, value.toString());
		} else if (value instanceof Boolean) {
			editor.putBoolean(key, Boolean.getBoolean(value.toString()));
		} else if (value instanceof Float) {
			editor.putFloat(key, Float.parseFloat(value.toString()));
		} else if (value instanceof Integer) {
			editor.putInt(key, Integer.parseInt(value.toString()));
		} else if (value instanceof Long) {
			editor.putLong(key, Long.parseLong(value.toString()));
		} else {
			editor.putString(key, value.toString());
		}
		editor.commit();
	}

	public String getData(String key) {
		return this.sp.getString(key, "");
	}
	public String getData(String key, String defaultValue) {
		return this.sp.getString(key, defaultValue);
	}
	public boolean hasData(String key) {
		return this.sp.contains(key);
	}
	public void clearData() {
		this.sp.edit().clear().commit();
	}
	public void removeData(String key) {
		if (this.hasData(key))
			sp.edit().remove(key).commit();
	}
}