package com.yucheng.byxf.mini.message;

import java.util.List;

public class XiaoJinYingZhangDanXiangQing {
	protected String flag;
	private List<XiaoJinYingZhangDanXiangQing2> result;
	private Integer code;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	private String msg;

	public String getFlag() {
		return flag;
	}

	public List<XiaoJinYingZhangDanXiangQing2> getResult() {
		return result;
	}

	public Integer getCode() {
		return code;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public void setResult(List<XiaoJinYingZhangDanXiangQing2> result) {
		this.result = result;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

}
