package com.yucheng.byxf.mini.message;

import java.io.Serializable;

public class XiaojinYingBasicResult1 implements Serializable {

 
	private String errorCode;
	private String errorMsg;
	private XiaojinYingBasicResult1Body basicMsgBody;
	public String getErrorCode() {
		return errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public XiaojinYingBasicResult1Body getBasicMsgBody() {
		return basicMsgBody;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public void setBasicMsgBody(XiaojinYingBasicResult1Body basicMsgBody) {
		this.basicMsgBody = basicMsgBody;
	}

}
