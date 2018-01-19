package com.yucheng.byxf.mini.message;

import java.io.Serializable;

public class SystemNotice implements Serializable {

	public String title;
	public String createTime;
	public String detail;

	public SystemNotice(String title, String createTime,
			String detail) {
		super();
		this.title = title;
		this.createTime = createTime;
		this.detail = detail;
	}

	public SystemNotice() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

}
