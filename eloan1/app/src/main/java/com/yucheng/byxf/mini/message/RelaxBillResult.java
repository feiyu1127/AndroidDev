package com.yucheng.byxf.mini.message;

import java.util.List;

public class RelaxBillResult extends Response{
	private List<RelaxBillResponse> result;

	public List<RelaxBillResponse> getResult() {
		return result;
	}

	public void setResult(List<RelaxBillResponse> result) {
		this.result = result;
	}

}
