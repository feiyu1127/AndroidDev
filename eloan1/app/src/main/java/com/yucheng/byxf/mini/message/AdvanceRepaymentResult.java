package com.yucheng.byxf.mini.message;

import java.util.List;

public class AdvanceRepaymentResult {

	protected Integer code;
	protected String msg;
	protected String flag;
	private List<AdvanceRepaymentResponse> result;

	public List<AdvanceRepaymentResponse> getResult() {
		return result;
	}

	public void setResult(List<AdvanceRepaymentResponse> list) {
		this.result = result;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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

	/*
	 * private AdvanceRepaymentResult2 result;
	 * 
	 * public AdvanceRepaymentResult2 getResult() { return result; }
	 * 
	 * public void setResult(AdvanceRepaymentResult2 result) { this.result =
	 * result; }
	 */

}
