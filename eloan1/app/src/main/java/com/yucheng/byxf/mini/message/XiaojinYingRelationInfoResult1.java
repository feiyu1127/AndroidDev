package com.yucheng.byxf.mini.message;

import java.io.Serializable;

public class XiaojinYingRelationInfoResult1 implements Serializable {

	private String errorCode;
	private String errorMsg;
	private XiaojinYingRelationInfoResultBody body;

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public XiaojinYingRelationInfoResultBody getBody() {
		return body;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public void setBody(XiaojinYingRelationInfoResultBody body) {
		this.body = body;
	}

}
