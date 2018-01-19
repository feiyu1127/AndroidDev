package com.yucheng.byxf.mini.message;

import java.util.List;

public class AdvanceRepaymentResultAllResult {
	//提前还款---结果页面 ----返回code
	protected Integer code;
	protected String msg;
	protected String flag;
	private List<AdvanceRepaymentResultResponse> result;

	public List<AdvanceRepaymentResultResponse> getResult() {
		return result;
	}

	public void setResult(List<AdvanceRepaymentResultResponse> list) {
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

}
