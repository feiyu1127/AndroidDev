package com.yucheng.byxf.mini.message;

public class OtherMessageResult {

	protected Integer code;
	protected String msg;
	protected String flag;
	private UserOtherMessage result;
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public UserOtherMessage getResult() {
		return result;
	}
	public void setResult(UserOtherMessage result) {
		this.result = result;
	}
	
}
