package com.yucheng.byxf.mini.message;

import java.util.List;
import java.util.Map;



public class ResponseforProving {
	protected Integer code;
	protected String msg;
	protected Map<String,String> result;

	public Map<String, String> getResult() {
		return result;
	}
	public void setResult(Map<String, String> result) {
		this.result = result;
	}


	
	public ResponseforProving() {
	}
	public ResponseforProving(Integer code,String msg) {
		this.code = code;
		this.msg = msg;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
