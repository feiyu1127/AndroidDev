package com.yucheng.byxf.mini.message;

import java.util.List;

public class AdvanceRepaymentApplyResultSubmitYe {
	//提前还款-------返回code
	protected Integer code;
	protected String msg;
	protected String flag;
//	protected AdvanceRepaymentApplyResponse result;
//	public AdvanceRepaymentApplyResponse getResult() {
//		return result;
//	}
//
//	public void setResult(AdvanceRepaymentApplyResponse result) {
//		this.result = result;
//	}

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

}
