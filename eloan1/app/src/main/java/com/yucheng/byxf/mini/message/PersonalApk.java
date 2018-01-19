package com.yucheng.byxf.mini.message;

/******************************************************************************
 * 
 * 北银消费金融公司移动业务终端
 *
 * Copyright © 2012 Yucheng Technologies Limited All Rights Reserved.
 * 
 * Created on 2012-9-26 17:33:53
 *
 *******************************************************************************/

import java.util.StringTokenizer;

/**
 * PERSONAL_APK程序
 * 
 * @version 1.0
 * 
 * @author mark
 */

public class PersonalApk implements java.io.Serializable {

	private static final long serialVersionUID = 4384628785677544746L;

	/**
	 * ID
	 */
	private Long id;

	/**
	 * 版本
	 */
	private String ver;

	/**
	 * 说明
	 */
	private String msg;
	/**
	 * 说明
	 */
	private String name;

	/**
	 * 程序路径
	 */
	private String url;

	/**
	 * Apk存储路径
	 */
	private String path;

	/**
	 * 更新日期
	 */
	private String dt;

	/**
	 * 升级包类型
	 */
	private String type;

	private String isForceUpdate;
	
	public PersonalApk() {
	}

	public PersonalApk(Long id, String ver, String url) {
		this.id = id;
		this.ver = ver;
		this.url = url;
	}

	public PersonalApk(Long id, String ver, String msg, String url) {
		this.id = id;
		this.ver = ver;
		this.msg = msg;
		this.url = url;
	}

	public PersonalApk(Long id, String ver, String name, String msg,
			String url, String isForceUpdate) {
		this.id = id;
		this.ver = ver;
		this.name = name;
		this.msg = msg;
		this.url = url;
		this.isForceUpdate = isForceUpdate;
	}

	/**
	 * 访问<ID>属性
	 */

	public Long getId() {
		return this.id;
	}

	/**
	 * 设置<ID>属性
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 访问<版本>属性
	 */
	public String getVer() {
		return this.ver;
	}

	/**
	 * 设置<版本>属性
	 */
	public void setVer(String ver) {
		this.ver = ver;
	}

	/**
	 * 访问<说明>属性
	 */
	public String getMsg() {
		return this.msg;
	}

	/**
	 * 设置<说明>属性
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 访问<程序路径>属性
	 */
	public String getUrl() {
		return this.url;
	}

	/**
	 * 设置<程序路径>属性
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 访问<Apk存储路径>属性
	 */
	public String getPath() {
		return this.path;
	}

	/**
	 * 设置<Apk存储路径>属性
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * 访问<更新日期>属性
	 */
	public String getDt() {
		return this.dt;
	}

	/**
	 * 设置<更新日期>属性
	 */
	public void setDt(String dt) {
		this.dt = dt;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIsForceUpdate() {
		return isForceUpdate;
	}

	public void setIsForceUpdate(String isForceUpdate) {
		this.isForceUpdate = isForceUpdate;
	}

	public static void main(String[] args) {
		String str1 = "1.1.06.6.7";
		String str2 = "1.1.07";
		System.out.println(isGreater(str1, str2));
	}

	public static boolean isGreater(String first, String second) {
		if (first == null || first.trim().equals(""))
			return false;
		if (second == null || second.trim().equals(""))
			return true;
		StringTokenizer firstToken = new StringTokenizer(first, ".");
		StringTokenizer secondToken = new StringTokenizer(second, ".");
		while (firstToken.hasMoreElements()) {
			String firstStr = firstToken.nextToken();
			String secondStr = null;
			if (secondToken.hasMoreElements())
				secondStr = secondToken.nextToken();
			if (secondStr == null)
				return true;
			int firstValue = Integer.parseInt(firstStr);
			int secondValue = Integer.parseInt(secondStr);
			if (firstValue > secondValue)
				return true;
			else if (firstValue < secondValue)
				return false;
		}
		return false;
	}

}