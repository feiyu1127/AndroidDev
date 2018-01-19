package com.yucheng.byxf.mini.message;

import java.util.List;

/**
 * 未出账单详情
 */
public class MiniNoBill {
	
//	private String errorCode;
//	
//	private String errorMsg;
//	
	private List<MiniNoBillDetail> details;
	public List<MiniNoBillDetail> getDetails() {
		return details;
	}
	public void setDetails(List<MiniNoBillDetail> details) {
		this.details = details;
	}
	/**
	 * 总利息
	 */
	private String totalInt;
	
	/**
	 * 本期利息
	 */
	private String curNormInt;
	
	
	public String getCurNormInt() {
		return curNormInt;
	}
	public String getTotalInt() {
		return totalInt;
	}
	public void setCurNormInt(String curNormInt) {
		this.curNormInt = curNormInt;
	}
	public void setTotalInt(String totalInt) {
		this.totalInt = totalInt;
	}

	//private String[] details = {};



	
	
}
