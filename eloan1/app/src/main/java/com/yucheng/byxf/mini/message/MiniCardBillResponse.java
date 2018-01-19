package com.yucheng.byxf.mini.message;

import java.util.List;

/**
 * Mini已出账单
 */
public class MiniCardBillResponse extends Response {

	private String flag;
	
	private List<MiniCardBillDetail> result;

	public MiniCardBillResponse() {
		super();
	}

	public MiniCardBillResponse(String flag, List<MiniCardBillDetail> result) {
		super();
		this.flag = flag;
		this.result = result;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public List<MiniCardBillDetail> getResult() {
		return result;
	}

	public void setResult(List<MiniCardBillDetail> result) {
		this.result = result;
	}
}
