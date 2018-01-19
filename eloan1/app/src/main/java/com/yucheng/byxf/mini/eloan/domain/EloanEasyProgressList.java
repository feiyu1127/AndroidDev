package com.yucheng.byxf.mini.eloan.domain;

import java.io.Serializable;
import java.util.List;

public class EloanEasyProgressList implements Serializable{
	private String retCode;
	private String retInfo;
	private List<EloanListEntity> progressList;
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
	public List<EloanListEntity> getProgressList() {
		return progressList;
	}
	public void setProgressList(List<EloanListEntity> progressList) {
		this.progressList = progressList;
	}
	
}
