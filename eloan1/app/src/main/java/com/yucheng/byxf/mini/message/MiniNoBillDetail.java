package com.yucheng.byxf.mini.message;

public class MiniNoBillDetail {

	/**
	 * 交易时间
	 */
	private String hostTxDt;
	/**
	 * 到期还款日
	 */
	private String acctDt;
	/**
	 * 交易金额
	 */
	private String txAmt;
	/**
	 * 交易描述
	 */
	private String txDesc;
	/**
	 * 交易类型
	 */
	private String txTyp;

	public String getHostTxDt() {
		return hostTxDt;
	}

	public void setHostTxDt(String hostTxDt) {
		this.hostTxDt = hostTxDt;
	}

	public String getAcctDt() {
		return acctDt;
	}

	public void setAcctDt(String acctDt) {
		this.acctDt = acctDt;
	}

	public String getTxAmt() {
		return txAmt;
	}

	public void setTxAmt(String txAmt) {
		this.txAmt = txAmt;
	}

	public String getTxDesc() {
		return txDesc;
	}

	public void setTxDesc(String txDesc) {
		this.txDesc = txDesc;
	}

	public String getTxTyp() {
		return txTyp;
	}

	public void setTxTyp(String txTyp) {
		this.txTyp = txTyp;
	}

	public MiniNoBillDetail(String hostTxDt, String acctDt, String txAmt,
			String txDesc, String txTyp) {
		super();
		this.hostTxDt = hostTxDt;
		this.acctDt = acctDt;
		this.txAmt = txAmt;
		this.txDesc = txDesc;
		this.txTyp = txTyp;
	}
}
