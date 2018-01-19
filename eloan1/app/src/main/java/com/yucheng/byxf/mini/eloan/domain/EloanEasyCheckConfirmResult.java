package com.yucheng.byxf.mini.eloan.domain;

import com.yucheng.byxf.mini.message.Response;

public class EloanEasyCheckConfirmResult extends Response{
	private EloanListCheckConfirmEntity result;

	public EloanListCheckConfirmEntity getResult() {
		return result;
	}

	public void setResult(EloanListCheckConfirmEntity result) {
		this.result = result;
	}
	
}
