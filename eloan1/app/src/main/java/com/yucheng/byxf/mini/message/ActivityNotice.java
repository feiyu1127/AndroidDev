package com.yucheng.byxf.mini.message;

import java.io.Serializable;

public class ActivityNotice implements Serializable {

	// public String totalCount;
	public String title;
	public String path;
	public String htmlName;
	public String createTime;
	public String activityDetial;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getHtmlName() {
		return htmlName;
	}

	public void setHtmlName(String htmlName) {
		this.htmlName = htmlName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getActivityDetial() {
		return activityDetial;
	}

	public void setActivityDetial(String activityDetial) {
		this.activityDetial = activityDetial;
	}

	public ActivityNotice(String title, String path, String htmlName,
			String createTime, String activityDetial) {
		super();
		this.title = title;
		this.path = path;
		this.htmlName = htmlName;
		this.createTime = createTime;
		this.activityDetial = activityDetial;
	}

	public ActivityNotice() {
		super();
	}

}
