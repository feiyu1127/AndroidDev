package com.yucheng.byxf.mini.message;

import java.util.List;

public class SysMsgResult {

	private Integer totalCount;
	private List<SystemNotice> activitives;

	public SysMsgResult(Integer totalCount,
			List<SystemNotice> activitives) {
		super();
		this.totalCount = totalCount;
		this.activitives = activitives;
	}

	public SysMsgResult() {
		super();
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public List<SystemNotice> getActivitives() {
		return activitives;
	}

	public void setActivitives(List<SystemNotice> activitives) {
		this.activitives = activitives;
	}

}
