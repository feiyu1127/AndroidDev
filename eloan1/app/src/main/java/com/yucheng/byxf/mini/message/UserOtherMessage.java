package com.yucheng.byxf.mini.message;

import java.util.List;

public class UserOtherMessage {

	private int totalCount;
//	private List<UserOtherMessageInfo> userother;
	private List<SystemNotice> userother;
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<SystemNotice> getUserother() {
		return userother;
	}
	public void setUserother(List<SystemNotice> userother) {
		this.userother = userother;
	}
}
