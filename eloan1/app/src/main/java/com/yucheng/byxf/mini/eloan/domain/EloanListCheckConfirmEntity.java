package com.yucheng.byxf.mini.eloan.domain;

import java.io.Serializable;

public class EloanListCheckConfirmEntity implements Serializable {

	private String retCode;
	private String retInfo;

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

}