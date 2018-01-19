package com.yucheng.byxf.mini.message;

import java.util.List;

public class SuggestionMsgResult {

	protected Integer code;
	protected String msg;
	private List<SuggestionMsgEntity> result;

	public List<SuggestionMsgEntity> getResult() {
		return result;
	}

	public void setResult(List<SuggestionMsgEntity> list) {
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



}
