package com.yucheng.byxf.mini.message;

import java.io.Serializable;

public class AdvanceRepaymentResponse implements Serializable {
	
	
	public String retCode;
	public String retInfo;
	public String applyDt;
	public String applyAmt;
	public String applyCde;//申请编号
	public String loanTyp;//品种
	public String loanTnr;//期限
	public String applyType;//0-1类型
	public String pcCode;
	public String loanMtd;
	public String inputSrc;
	public String state;
	public String contStatus;
	public String loanSts;
	public String appvDt;
	public String loanGrp;
	public String loanNo;
	public String idNo;
	public String idType;
	public String custName;
	public String intStartDt;
	public String lastDueDt;
	public String mdtDesc;
	public String loanIntRate;
	public String pomsDepart;
	public String loanOdInd  ;


	public String getApplyType() {
		return applyType;
	}
	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}
	public String getRetCode() {
		return retCode;
	}
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	public String getRetInfo() {
		return retInfo;
	}
	public void setRetInfo(String retInfo) {
		this.retInfo = retInfo;
	}
	public String getApplyCde() {
		return applyCde;
	}
	public void setApplyCde(String applyCde) {
		this.applyCde = applyCde;
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
	public String getLoanTyp() {
		return loanTyp;
	}
	public void setLoanTyp(String loanTyp) {
		this.loanTyp = loanTyp;
	}
	public String getPcCode() {
		return pcCode;
	}
	public void setPcCode(String pcCode) {
		this.pcCode = pcCode;
	}
	public String getLoanMtd() {
		return loanMtd;
	}
	public void setLoanMtd(String loanMtd) {
		this.loanMtd = loanMtd;
	}
	public String getInputSrc() {
		return inputSrc;
	}
	public void setInputSrc(String inputSrc) {
		this.inputSrc = inputSrc;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getContStatus() {
		return contStatus;
	}
	public void setContStatus(String contStatus) {
		this.contStatus = contStatus;
	}
	public String getLoanSts() {
		return loanSts;
	}
	public void setLoanSts(String loanSts) {
		this.loanSts = loanSts;
	}
	public String getAppvDt() {
		return appvDt;
	}
	public void setAppvDt(String appvDt) {
		this.appvDt = appvDt;
	}
	public String getLoanGrp() {
		return loanGrp;
	}
	public void setLoanGrp(String loanGrp) {
		this.loanGrp = loanGrp;
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
	public String getIntStartDt() {
		return intStartDt;
	}
	public void setIntStartDt(String intStartDt) {
		this.intStartDt = intStartDt;
	}
	public String getLastDueDt() {
		return lastDueDt;
	}
	public void setLastDueDt(String lastDueDt) {
		this.lastDueDt = lastDueDt;
	}
	public String getLoanTnr() {
		return loanTnr;
	}
	public void setLoanTnr(String loanTnr) {
		this.loanTnr = loanTnr;
	}
	public String getMdtDesc() {
		return mdtDesc;
	}
	public void setMdtDesc(String mdtDesc) {
		this.mdtDesc = mdtDesc;
	}
	public String getLoanIntRate() {
		return loanIntRate;
	}
	public void setLoanIntRate(String loanIntRate) {
		this.loanIntRate = loanIntRate;
	}
	public String getPomsDepart() {
		return pomsDepart;
	}
	public void setPomsDepart(String pomsDepart) {
		this.pomsDepart = pomsDepart;
	}
	public String getLoanOdInd() {
		return loanOdInd;
	}
	public void setLoanOdInd(String loanOdInd) {
		this.loanOdInd = loanOdInd;
	}
	
}
