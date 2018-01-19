package com.yucheng.byxf.mini.message;
/**
 * Mini未出账单
 */
public class MiniNoBillResponse extends Response {

	private String flag;
	private MiniNoBill result;

	public MiniNoBillResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MiniNoBillResponse(String flag, MiniNoBill result) {
		super();
		this.flag = flag;
		this.result = result;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public MiniNoBill getResult() {
		return result;
	}
	public void setResult(MiniNoBill result) {
		this.result = result;
	}
}
