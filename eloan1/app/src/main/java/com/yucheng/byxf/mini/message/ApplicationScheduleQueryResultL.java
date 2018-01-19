package com.yucheng.byxf.mini.message;

import java.util.List;

public class ApplicationScheduleQueryResultL {
	private List<ApplicationScheduleQueryResponse> list;
	
	private String retCode;
	
	private String retInfo;
	
	private String talCount;
	
	private String applCdeFlag;
	
	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public String getRetInfo() {
		return retInfo;
	}

	public void setRetInfo(String retInfo) {
		this.retInfo = retInfo;
	}

	public String getTalCount() {
		return talCount;
	}

	public void setTalCount(String talCount) {
		this.talCount = talCount;
	}

	public String getApplCdeFlag() {
		return applCdeFlag;
	}

	public void setApplCdeFlag(String applCdeFlag) {
		this.applCdeFlag = applCdeFlag;
	}

	public List<ApplicationScheduleQueryResponse> getResult() {
		return list;
	}

	public void setResult(List<ApplicationScheduleQueryResponse> list) {
		this.list = list;
	}
}
