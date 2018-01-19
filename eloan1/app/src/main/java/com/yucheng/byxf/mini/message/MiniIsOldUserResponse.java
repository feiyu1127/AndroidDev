package com.yucheng.byxf.mini.message;

import android.database.CursorJoiner.Result;

public class MiniIsOldUserResponse extends Response {
	private String msg;
	private String flag;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
}
