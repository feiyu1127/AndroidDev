package com.yucheng.byxf.mini.message;

import java.io.Serializable;

public class XiaoJinYingIsJihuoRespinse2 implements Serializable{
	private String respCode;
	private String replyMsg;
	private String isPwdCreate;
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	public String getReplyMsg() {
		return replyMsg;
	}
	public void setReplyMsg(String replyMsg) {
		this.replyMsg = replyMsg;
	}
	public String getIsPwdCreate() {
		return isPwdCreate;
	}
	public void setIsPwdCreate(String isPwdCreate) {
		this.isPwdCreate = isPwdCreate;
	}
	
}
