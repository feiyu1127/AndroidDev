package com.yucheng.byxf.mini.map;

import java.util.ArrayList;
import java.util.List;

import com.yucheng.byxf.mini.message.Response;

public class BranchLocationResponse extends Response{

	private List<BranchLocation> result;
	
	public List<BranchLocation> getResult() {
		return result;
	}
	public void setResult(ArrayList<BranchLocation> result) {
		this.result = result;
	}
	
	
	
}
