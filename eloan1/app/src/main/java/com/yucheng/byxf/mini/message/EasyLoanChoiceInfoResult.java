package com.yucheng.byxf.mini.message;

import java.util.List;

public class EasyLoanChoiceInfoResult extends Response{
	
 

	public List<EasyLoanChoiceInfoResult2> getResult() {
		return result;
	}

	public void setResult(List<EasyLoanChoiceInfoResult2> result) {
		this.result = result;
	}

	private List<EasyLoanChoiceInfoResult2> result;

 
	
}
