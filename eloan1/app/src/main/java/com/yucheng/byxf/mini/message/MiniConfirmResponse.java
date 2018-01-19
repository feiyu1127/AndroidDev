package com.yucheng.byxf.mini.message;

public class MiniConfirmResponse {
	protected Integer code;
	protected String msg;
	private MiniConfirmMessage result;
	public MiniConfirmResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MiniConfirmResponse(Integer code, String msg,
			MiniConfirmMessage result) {
		super();
		this.code = code;
		this.msg = msg;
		this.result = result;
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

	public MiniConfirmMessage getResult() {
		return result;
	}

	public void setResult(MiniConfirmMessage result) {
		this.result = result;
	}
	
	
}
