package com.yucheng.byxf.mini.message;

import java.util.List;

public class ActivityNoticeResponse {

	protected Integer code;
	protected String msg;
	private ActivityNoticeResult result;
	private String flag;

	public ActivityNoticeResponse(Integer code, String msg,
			ActivityNoticeResult result) {
		super();
		this.code = code;
		this.msg = msg;
		this.result = result;
	}

	public ActivityNoticeResponse() {
		super();
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

	public ActivityNoticeResult getResult() {
		return result;
	}

	public void setResult(ActivityNoticeResult result) {
		this.result = result;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
