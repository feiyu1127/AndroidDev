package com.yucheng.byxf.mini.message;



public class Response {
	protected Integer code;
	protected String msg;
	
	public Response() {
		super();
	}
	public Response(Integer code,String msg) {
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
