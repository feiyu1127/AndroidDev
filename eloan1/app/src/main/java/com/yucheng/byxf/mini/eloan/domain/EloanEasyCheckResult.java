package com.yucheng.byxf.mini.eloan.domain;

import com.yucheng.byxf.mini.message.Response;

public class EloanEasyCheckResult extends Response{
	private EloanListCheckEntity result;

	public EloanListCheckEntity getResult() {
		return result;
	}

	public void setResult(EloanListCheckEntity result) {
		this.result = result;
	}
	
}
