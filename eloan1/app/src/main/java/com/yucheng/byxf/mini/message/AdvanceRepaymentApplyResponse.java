package com.yucheng.byxf.mini.message;

import java.io.Serializable;

import android.widget.TextView;

public class AdvanceRepaymentApplyResponse implements Serializable {
	
	
	private String loanContNo;//合同号
	//private String ;//还款方式  还款模式还是还款类型？  带过来  
	private String setlRemPrcpPaym;//提前还款本金   提前还本金额？？？？
	//private String ;//手续费  ？？？？
	private String allLateFee;//逾期滞纳金 ？？？？？？？？ 拖欠滞纳金
	private String amt;//提前还款金额总计 ???????? amt
	private String acctNo ;//还款账号
	//private String;//借款期限 申请 
	private String idNo;//证件号
	private String setlMode;//还款模式
	private String paymInd;//是否到账
	private String fx;//逾期罚息
	private String znj;//逾期滞纳金
	private String loanNo;//借据号
	private String custName; //客户姓名
	private String origPrcp;//放款金额

	public String loanTnr;//期限
	public String loanTyp;//品种
	
	private String outlayAmt;//手续费
	//---轻松贷
	//private String tiqianhuankuanzhanghao;
	private String setlPsIncTaken;//dangqilixi;当期利息
	private String falseAmt;//tiqianhuankuanweiyuejin;提前还款违约金
//	private String fx;//yuqifaxi;逾期罚息
	private String allOdPrcpAmt;//yuqibenjin;逾期本金
	private String allPsFeeAmt;//yuqizhanghuguanlifei;与其账户管理费
	private String allOdNormIntAmt;//yuqilixi;逾期利息
	
	private String applyCde;//申请编号
	private String applyType;//申请类型
	
	private String pomsDepart;//部门
	private String appvDt; //终审日期
	
	public String getPomsDepart() {
		return pomsDepart;
	}
	public void setPomsDepart(String pomsDepart) {
		this.pomsDepart = pomsDepart;
	}
	public String getAppvDt() {
		return appvDt;
	}
	public void setAppvDt(String appvDt) {
		this.appvDt = appvDt;
	}

	public String getApplyCde() {
		return applyCde;
	}
	public void setApplyCde(String applyCde) {
		this.applyCde = applyCde;
	}
	public String getApplyType() {
		return applyType;
	}
	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}
	

	public String getLoanTnr() {
		return loanTnr;
	}
	public void setLoanTnr(String loanTnr) {
		this.loanTnr = loanTnr;
	}
	public String getLoanTyp() {
		return loanTyp;
	}
	public void setLoanTyp(String loanTyp) {
		this.loanTyp = loanTyp;
	}
	
	public String getAllOdPrcpAmt() {
		return allOdPrcpAmt;
	}
	public void setAllOdPrcpAmt(String allOdPrcpAmt) {
		this.allOdPrcpAmt = allOdPrcpAmt;
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
	
	public String getOrigPrcp() {
		return origPrcp;
	}
	public void setOrigPrcp(String origPrcp) {
		this.origPrcp = origPrcp;
	}
	public String getOutlayAmt() {
		return outlayAmt;
	}
	public void setOutlayAmt(String outlayAmt) {
		this.outlayAmt = outlayAmt;
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
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
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
	public String getSetlMode() {
		return setlMode;
	}
	public void setSetlMode(String setlMode) {
		this.setlMode = setlMode;
	}
	public String getPaymInd() {
		return paymInd;
	}
	public void setPaymInd(String paymInd) {
		this.paymInd = paymInd;
	}
	public String getFx() {
		return fx;
	}
	public void setFx(String fx) {
		this.fx = fx;
	}
	public String getZnj() {
		return znj;
	}
	public void setZnj(String znj) {
		this.znj = znj;
	}

	
	public String getLoanContNo() {
		return loanContNo;
	}
	public void setLoanContNo(String loanContNo) {
		this.loanContNo = loanContNo;
	}
	public String getSetlRemPrcpPaym() {
		return setlRemPrcpPaym;
	}
	public void setSetlRemPrcpPaym(String setlRemPrcpPaym) {
		this.setlRemPrcpPaym = setlRemPrcpPaym;
	}
	public String getAllLateFee() {
		return allLateFee;
	}
	public void setAllLateFee(String allLateFee) {
		this.allLateFee = allLateFee;
	}
	public String getAmt() {
		return amt;
	}
	public void setAmt(String amt) {
		this.amt = amt;
	}
	public String getAcctNo() {
		return acctNo;
	}
	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

	
}
