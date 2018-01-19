package com.yucheng.byxf.mini.message;

import java.util.List;

public class BillNewResult extends Response{
	private List<BillNewResponse> result;

	public List<BillNewResponse> getResult() {
		return result;
	}

	public void setResult(List<BillNewResponse> result) {
		this.result = result;
	}
	
}
