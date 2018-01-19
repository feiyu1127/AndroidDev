package com.yucheng.byxf.mini.utils;

import android.text.format.Time;

public class GetSystemDate {
	/**
	 * 获取系统当前月份
	 * @return
	 */
	public int getSystemMonth() {
		// TODO Auto-generated method stub
		Time time = new Time();
		time.setToNow();
		
		int month = time.month;
		return month;
	}
	
	/**
	 * 获取系统当前日期
	 * @return
	 */
	public int getSystemDay() {
		// TODO Auto-generated method stub
		Time time = new Time();
		time.setToNow();
		
		int day = time.monthDay + 1;
		return day;
	}
	
	/**
	 * 获取系统当前年份
	 * @return
	 */
	public int getSystemYear() {
		// TODO Auto-generated method stub
		Time time = new Time();
		time.setToNow();
		
		int year = time.year + 1;
		return year;
	}
	
	/**
	 * 获取系统当前小时
	 * @return
	 */
	public int getSystemHour() {
		// TODO Auto-generated method stub
		Time time = new Time();
		time.setToNow();
		
		int hout = time.hour;
		return hout;
	}
	
	/**
	 * 获取系统当前分钟
	 * @return
	 */
	public int getSystemMinute() {
		// TODO Auto-generated method stub
		Time time = new Time();
		time.setToNow();
		
		int minute = time.minute;
		return minute;
	}
	
	/**
	 * 获取系统当前秒钟
	 * @return
	 */
	public int getSystemSecond() {
		// TODO Auto-generated method stub
		Time time = new Time();
		time.setToNow();
		
		int minute = time.second;
		return minute;
	}
}
