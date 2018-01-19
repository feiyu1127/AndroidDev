package com.yucheng.byxf.mini.message;


 
import java.util.List;

/**
 * 还款计划详情-响应报文
 * 
 * @author neo
 *
 */
public class PaymentDetailResponse {
	//交易码
	private String txCde="";
	//证件号码
	private String idNo = "";
	//证件类型
	private String idType="";
	//本金
	private String bj="";
	//利息
	private String lx="";
	//罚息
	private String fx="";
	//管理费
	private String glf="";
	//管理费
	private String znj="";
	//******************************************
	private String applyDt="";
	//******************************************
	//返回码
	private String retCode="";
	//返回信息
	private String retInfo="";
	//地址
	private String contAddr="";
	//邮政编号
	private String contZip="";
	//地址类型
	private String contType="";
	//现单位名称
	private String indivEmp="";
	//任职部门
	private String indivBranch="";
	//借据号
	private String loanNo="";
	//合同号
	private String loanContNo="";
	//用户名
	private String custName="";
	//放款金额
	private String origPrcp="";
	//还款账号
	private String acctNo="";
	//起息日
	private String intStartDt="";
	//到期日
	private String lastDueDt="";
	//期限
	private String loanTnr="";
	//还款方式
	private String mtdDesc="";
	//执行年利率
	private String loanIntRate="";
	
	private List<PaymentDetail> list=null;
	
	public String getRetCode() {
		return retCode;
	}
	public String getRetInfo() {
		return retInfo;
	}
	
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	public void setRetInfo(String retInfo) {
		this.retInfo = retInfo;
	}
	
	public String getContAddr() {
		return contAddr;
	}
	public void setContAddr(String contAddr) {
		this.contAddr = contAddr;
	}
	public String getContZip() {
		return contZip;
	}
	public void setContZip(String contZip) {
		this.contZip = contZip;
	}
	public String getContType() {
		return contType;
	}
	public void setContType(String contType) {
		this.contType = contType;
	}
	public String getIndivEmp() {
		return indivEmp;
	}
	public void setIndivEmp(String indivEmp) {
		this.indivEmp = indivEmp;
	}
	public String getIndivBranch() {
		return indivBranch;
	}
	
	public void setIndivBranch(String indivBranch) {
		this.indivBranch = indivBranch;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getLoanContNo() {
		return loanContNo;
	}
	public void setLoanContNo(String loanContNo) {
		this.loanContNo = loanContNo;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getOrigPrcp() {
		return origPrcp;
	}
	public void setOrigPrcp(String origPrcp) {
		this.origPrcp = origPrcp;
	}
	public String getAcctNo() {
		return acctNo;
	}
	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
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
	public String getMtdDesc() {
		return mtdDesc;
	}
	public void setMtdDesc(String mtdDesc) {
		this.mtdDesc = mtdDesc;
	}
	public String getLoanIntRate() {
		return loanIntRate;
	}
	public void setLoanIntRate(String loanIntRate) {
		this.loanIntRate = loanIntRate;
	}
	
	public List<PaymentDetail> getList() {
		return list;
	}
	public void setList(List<PaymentDetail> list) {
		this.list = list;
	}
	/****************************************************/
	public String getTxCde() {
		return txCde;
	}
	public void setTxCde(String txCde) {
		this.txCde = txCde;
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
	public String getBj() {
		return bj;
	}
	public void setBj(String bj) {
		this.bj = bj;
	}
	public String getLx() {
		return lx;
	}
	public void setLx(String lx) {
		this.lx = lx;
	}
	public String getFx() {
		return fx;
	}
	public void setFx(String fx) {
		this.fx = fx;
	}
	public String getGlf() {
		return glf;
	}
	public void setGlf(String glf) {
		this.glf = glf;
	}
	public String getZnj() {
		return znj;
	}
	public void setZnj(String znj) {
		this.znj = znj;
	}
	public String getApplyDt() {
		return applyDt;
	}
	public void setApplyDt(String applyDt) {
		this.applyDt = applyDt;
	}
	
}
