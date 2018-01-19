package com.yucheng.byxf.mini.message;

import java.util.List;


public class GetCardNo extends Response {
	private String flag;
	private CardInfo result;

	public GetCardNo(Integer code, String msg) {
		super(code, msg);
		// TODO Auto-generated constructor stub
	}

	public GetCardNo(String flag, CardInfo result) {
		super();
		this.flag = flag;
		this.result = result;
	}

	public GetCardNo() {
		super();
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public CardInfo getResult() {
		return result;
	}

	public void setResult(CardInfo result) {
		this.result = result;
	}
}
