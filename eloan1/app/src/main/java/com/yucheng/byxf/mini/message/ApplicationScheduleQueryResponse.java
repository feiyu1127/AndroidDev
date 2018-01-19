package com.yucheng.byxf.mini.message;

import java.io.Serializable;

public class ApplicationScheduleQueryResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String applSeq;
	private String applCde;
	private String idNo;
	private String idType;
	private String custName;
	private String applyDt;
	private String applyAmt;
	private String applyTnr;
	private String state;
	private String stateShow;
	private String inputSrc;
	private String contStatus;
	private String actvStr;
	private String loanNo;
	private String loanDebtSts;
	private String psSignInd;
	private String dnState;
	private String dnWfFlag;
	public String getDnWfFlag() {
		return dnWfFlag;
	}
	public void setDnWfFlag(String dnWfFlag) {
		this.dnWfFlag = dnWfFlag;
	}
	public String getDnState() {
		return dnState;
	}
	public void setDnState(String dnState) {
		this.dnState = dnState;
	}
	public String getPsSignInd() {
		return psSignInd;
	}
	public void setPsSignInd(String psSignInd) {
		this.psSignInd = psSignInd;
	}
	public String getApplSeq() {
		return applSeq;
	}
	public void setApplSeq(String applSeq) {
		this.applSeq = applSeq;
	}
	public String getApplCde() {
		return applCde;
	}
	public void setApplCde(String applCde) {
		this.applCde = applCde;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getApplyDt() {
		return applyDt;
	}
	public void setApplyDt(String applyDt) {
		this.applyDt = applyDt;
	}
	public String getApplyAmt() {
		return applyAmt;
	}
	public void setApplyAmt(String applyAmt) {
		this.applyAmt = applyAmt;
	}
	public String getApplyTnr() {
		return applyTnr;
	}
	public void setApplyTnr(String applyTnr) {
		this.applyTnr = applyTnr;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getStateShow() {
		return stateShow;
	}
	public void setStateShow(String stateShow) {
		this.stateShow = stateShow;
	}
	public String getInputSrc() {
		return inputSrc;
	}
	public void setInputSrc(String inputSrc) {
		this.inputSrc = inputSrc;
	}
	public String getContStatus() {
		return contStatus;
	}
	public void setContStatus(String contStatus) {
		this.contStatus = contStatus;
	}
	public String getActvStr() {
		return actvStr;
	}
	public void setActvStr(String actvStr) {
		this.actvStr = actvStr;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getLoanDebtSts() {
		return loanDebtSts;
	}
	public void setLoanDebtSts(String loanDebtSts) {
		this.loanDebtSts = loanDebtSts;
	}
	
	

}
