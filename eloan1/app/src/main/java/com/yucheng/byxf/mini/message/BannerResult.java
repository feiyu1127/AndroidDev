package com.yucheng.byxf.mini.message;

import java.util.List;

public class BannerResult extends Response{
	private List<BannerResponse> result;

	public List<BannerResponse> getResult() {
		return result;
	}

	public void setResult(List<BannerResponse> result) {
		this.result = result;
	}

	
}
