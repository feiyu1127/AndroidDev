package com.yucheng.byxf.mini.message;

import java.util.List;

public class ApplicationScheduleQueryResult{
	protected Integer code;
	protected String msg;
	protected String flag;
	
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

	private ApplicationScheduleQueryResultL result;

	public ApplicationScheduleQueryResultL getResult() {
		return result;
	}

	public void setResult(ApplicationScheduleQueryResultL result) {
		this.result = result;
	}

//	public List<ApplicationScheduleQueryResponse> getResult() {
//		return result;
//	}
//
//	public void setResult(List<ApplicationScheduleQueryResponse> result) {
//		this.result = result;
//	}
	
	
}
