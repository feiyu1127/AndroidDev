package com.yucheng.byxf.mini.message;

public class Response1 {
	protected Integer code;
	protected String msg;

	public Response1() {
	}

	public Response1(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

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
}
