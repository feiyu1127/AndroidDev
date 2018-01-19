package com.yucheng.byxf.mini.message;

import java.util.List;

public class ActivityNoticeResult {

	private Integer totalCount;
	private List<ActivityNotice> activitives;

	public ActivityNoticeResult(Integer totalCount,
			List<ActivityNotice> activitives) {
		super();
		this.totalCount = totalCount;
		this.activitives = activitives;
	}

	public ActivityNoticeResult() {
		super();
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public List<ActivityNotice> getActivitives() {
		return activitives;
	}

	public void setActivitives(List<ActivityNotice> activitives) {
		this.activitives = activitives;
	}

}
