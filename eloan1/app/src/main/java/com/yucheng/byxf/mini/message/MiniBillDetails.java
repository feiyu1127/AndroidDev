package com.yucheng.byxf.mini.message;

/**
 * 已出账单交易详情
 */
public class MiniBillDetails extends Response implements java.io.Serializable{

	private String errorCode;
	
	private String errorMsg;
	/**
	 * 交易时间
	 */
	private String hostTxDt;
	/**
	 * 记账日
	 */
	private String acctDt;
	/**
	 * 交易描述
	 */
	private String txDesc;
	/**
	 * 交易金额
	 */
	private String txAmt;
	/**
	 * 交易类型
	 */
	private String txTyp;
	
	public MiniBillDetails(String errorCode, String errorMsg, String hostTxDt,
			String acctDt, String txDesc, String txAmt, String txTyp) {
		super();
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		this.hostTxDt = hostTxDt;
		this.acctDt = acctDt;
		this.txDesc = txDesc;
		this.txAmt = txAmt;
		this.txTyp = txTyp;
	}
	public String getTxTyp() {
		return txTyp;
	}
	public void setTxTyp(String txTyp) {
		this.txTyp = txTyp;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
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
	public String getTxDesc() {
		return txDesc;
	}
	public void setTxDesc(String txDesc) {
		this.txDesc = txDesc;
	}
	public String getTxAmt() {
		return txAmt;
	}
	public void setTxAmt(String txAmt) {
		this.txAmt = txAmt;
	}
	
}
