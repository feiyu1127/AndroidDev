package com.yucheng.byxf.mini.message;

public class RelaxBillDetailResult extends Response{
	PaymentDetailResponse result;

	public PaymentDetailResponse getResult() {
		return result;
	}

	public void setResult(PaymentDetailResponse result) {
		this.result = result;
	}
	
}
