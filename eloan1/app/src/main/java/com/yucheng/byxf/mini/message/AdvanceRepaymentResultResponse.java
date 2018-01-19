package com.yucheng.byxf.mini.message;

import java.io.Serializable;

import android.widget.TextView;

public class AdvanceRepaymentResultResponse implements Serializable {

	// 提前还款 结果页面查询 110225197002104010 互联网金融平台有

	private String idNo;// 证件号、身份证号
	private String custName;// 客户姓名
	private String intStartDt;// 申请日期
	private String origPrcp;// 申请金额 ？？？贷款金额？？？
	private String state;// 申请状态
	private String payState;// 还款状态；
	// private String custName;//借款人姓名 /是客户姓名
	private String loanContNo;// 合同编号？？？？？？？？？？？、是申请编号？？？
	// private String origPrcp;//贷款金额
	private String setlRemPrcpPaym;// 提前还款本金
	// private String ;//手续费？？？？？？？？？？？？？？？=====================
	private String znj;// 逾期滞纳金
	private String amt;// 提前还款金额总计????????????????
	// private String state;//提前还款审批状态??????????根申请状态 有什么不一样
	private String actTransAmt;// 实扣金额
	// private String ;//扣款状态 =======================================
	private String olDdaDt;// 清偿日期？？？？？？？？、、是扣款日期？
	private String applyTnr;// 借款期限 是 申请期mn限么？
	private String loanNo;// 申请编号
	private String payAppState;//还款申请状态
	private String ddaSts ;
	private String applyCde;//编号
	private String applyType;//0.-1 区分
	private String payAppDt;//還款申請日期
	
	private String purpose;//用途
	private String setlPsIncTaken;//當期利息
	private String falseAmt;//提前还款 违约金
	private String fx;//逾期罚息
	private String setlOdPrcpAmt;//逾期本金
	private String allPsFeeAmt;//逾期账户管理费
	private String allOdNormIntAmt;//逾期利息
	private String outlayAmt;//极速贷手续费

	
	
	public String getOutlayAmt() {
		return outlayAmt;
	}

	public void setOutlayAmt(String outlayAmt) {
		this.outlayAmt = outlayAmt;
	}

	

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getSetlPsIncTaken() {
		return setlPsIncTaken;
	}

	public void setSetlPsIncTaken(String setlPsIncTaken) {
		this.setlPsIncTaken = setlPsIncTaken;
	}

	public String getFalseAmt() {
		return falseAmt;
	}

	public void setFalseAmt(String falseAmt) {
		this.falseAmt = falseAmt;
	}

	public String getFx() {
		return fx;
	}

	public void setFx(String fx) {
		this.fx = fx;
	}

	public String getSetlOdPrcpAmt() {
		return setlOdPrcpAmt;
	}

	public void setSetlOdPrcpAmt(String setlOdPrcpAmt) {
		this.setlOdPrcpAmt = setlOdPrcpAmt;
	}

	public String getAllPsFeeAmt() {
		return allPsFeeAmt;
	}

	public void setAllPsFeeAmt(String allPsFeeAmt) {
		this.allPsFeeAmt = allPsFeeAmt;
	}

	public String getAllOdNormIntAmt() {
		return allOdNormIntAmt;
	}

	public void setAllOdNormIntAmt(String allOdNormIntAmt) {
		this.allOdNormIntAmt = allOdNormIntAmt;
	}


	
	
	
	public String getPayAppDt() {
		return payAppDt;
	}

	public void setPayAppDt(String payAppDt) {
		this.payAppDt = payAppDt;
	}

	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	public String getApplyCde() {
		return applyCde;
	}

	public void setApplyCde(String applyCde) {
		this.applyCde = applyCde;
	}

	public String getDdaSts() {
		return ddaSts;
	}

	public void setDdaSts(String ddaSts) {
		this.ddaSts = ddaSts;
	}

	public String getPayAppState() {
	return payAppState;
	
}

public void setPayAppState(String payAppState) {
	this.payAppState = payAppState;
}

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getIntStartDt() {
		return intStartDt;
	}

	public String getLoanContNo() {
		return loanContNo;
	}

	public void setLoanContNo(String loanContNo) {
		this.loanContNo = loanContNo;
	}

	public void setIntStartDt(String intStartDt) {
		this.intStartDt = intStartDt;
	}

	public String getOrigPrcp() {
		return origPrcp;
	}

	public void setOrigPrcp(String origPrcp) {
		this.origPrcp = origPrcp;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPayState() {
		return payState;
	}

	public void setPayState(String payState) {
		this.payState = payState;
	}

	public String getSetlRemPrcpPaym() {
		return setlRemPrcpPaym;
	}

	public void setSetlRemPrcpPaym(String setlRemPrcpPaym) {
		this.setlRemPrcpPaym = setlRemPrcpPaym;
	}

	public String getZnj() {
		return znj;
	}

	public void setZnj(String znj) {
		this.znj = znj;
	}

	public String getAmt() {
		return amt;
	}

	public void setAmt(String amt) {
		this.amt = amt;
	}

	public String getActTransAmt() {
		return actTransAmt;
	}

	public void setActTransAmt(String actTransAmt) {
		this.actTransAmt = actTransAmt;
	}

	public String getOlDdaDt() {
		return olDdaDt;
	}

	public void setOlDdaDt(String olDdaDt) {
		this.olDdaDt = olDdaDt;
	}

	public String getApplyTnr() {
		return applyTnr;
	}

	public void setApplyTnr(String applyTnr) {
		this.applyTnr = applyTnr;
	}

}
