package com.yucheng.byxf.mini.message;

public class LoginResponse extends Response{
	private LoginMessage result;
	public LoginMessage getResult() {
		return result;
	}
	public void setResult(LoginMessage result) {
		this.result = result;
	}
}	