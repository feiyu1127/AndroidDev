package com.yucheng.byxf.mini.message;

import java.util.List;

public class XiaoJinYingMingXiresult extends Response {
	private List<XiaoJinYingMingXiresult2> result;
	private String flag;

	public XiaoJinYingMingXiresult() {
		super();
	}

	public XiaoJinYingMingXiresult(List<XiaoJinYingMingXiresult2> result,
			String flag) {
		super();
		this.result = result;
		this.flag = flag;
	}

	public List<XiaoJinYingMingXiresult2> getResult() {
		return result;
	}

	public void setResult(List<XiaoJinYingMingXiresult2> result) {
		this.result = result;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
