package com.yucheng.byxf.mini.message;

import java.util.List;

public class XiaoChaXunResult {
	// 提前还款-------返回code
	protected Integer code;
	protected String msg;
	protected String flag;
	protected XIaoChaXunResponse result;

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

	public XIaoChaXunResponse getResult() {
		return result;
	}

	public void setResult(XIaoChaXunResponse result) {
		this.result = result;
	}

}
