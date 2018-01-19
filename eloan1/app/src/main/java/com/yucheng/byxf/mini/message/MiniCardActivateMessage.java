package com.yucheng.byxf.mini.message;

import java.io.Serializable;
import java.util.List;

public class MiniCardActivateMessage implements Serializable{
	//mini apply 接收实体类
	private int code;
	private String msg;
	private List<MiniCardActivateList> result;
	public int getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
	public List<MiniCardActivateList> getResult() {
		return result;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public void setResult(List<MiniCardActivateList> result) {
		this.result = result;
	}
	
	
	
}