package com.yucheng.byxf.mini.message;

import java.io.Serializable;

public class XiaoJinYingZhangDanXiangQing2 implements Serializable {
	// 交易时间
	private String hostTxDt;
	// 记账日
	private String acctDt;
	// 交易描述
	private String txDesc;
	// 交易金额
	private String txAmt;
	// 交易类型 CASHOUT 取现CONSUME 消费STAGES 分期REPAYMENT 还款REVSE 冲正NORM_INT 正常利息OD_INT
	// 罚息YEAR_FEE 年费TAKE_FEE 提现费LATE_FEE滞纳金AFFIRM 存款确认
	private String txTyp="";
	
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
	public String getTxTyp() {
		return txTyp;
	}
	public void setTxTyp(String txTyp) {
		this.txTyp = txTyp;
	}
	
	

}
