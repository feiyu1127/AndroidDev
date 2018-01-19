package com.yucheng.byxf.mini.message;

import java.util.List;

public class CardInfo {
	
	private String errorMsg;
	
	private Integer errorCode;
	/**
	 * 出生日期
	 */
	private String apptStartDate;
	/**
	 * 姓名
	 */
	private String custNam;
	/**
	 * 英文名字
	 */
	private String custSpell;
	private List<DetailsCardInfo> details;
	/**
	 * 传真机号码
	 */
	private String fixNo;
	/**
	 * 证件号码
	 */
	private String idNo;
	/**
	 * 证件类型
	 */
	private String idTyp;
	/**
	 * 部门
	 */
	private String indivBranch;
	/**
	 * 国籍
	 */
	private String indivCtry;
	/**
	 * 教育程度
	 */
	private String indivEdu;
	/**
	 * E-mail地址
	 */
	private String indivEmail;
	/**
	 * 单位名称
	 */
	private String indivEmp;
	/**
	 * 公司电话(分机)
	 */
	private String indivEmpTelNo;
	/**
	 * 家庭电话
	 */
	private String indivFmyTel;
	/**
	 * 婚姻状况
	 */
	private String indivMarital;
	/**
	 * 性别
	 */
	private String indivSex;
	/**
	 * 手机号
	 */
	private String phoneNo;
	/**
	 * 是否设置客户级查询密码
	 */
	private String queryInd;

	public CardInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public CardInfo(String errorMsg, Integer errorCode, String apptStartDate,
			String custNam, String custSpell, List<DetailsCardInfo> details,
			String fixNo, String idNo, String idTyp, String indivBranch,
			String indivCtry, String indivEdu, String indivEmail,
			String indivEmp, String indivEmpTelNo, String indivFmyTel,
			String indivMarital, String indivSex, String phoneNo,
			String queryInd) {
		super();
		this.errorMsg = errorMsg;
		this.errorCode = errorCode;
		this.apptStartDate = apptStartDate;
		this.custNam = custNam;
		this.custSpell = custSpell;
		this.details = details;
		this.fixNo = fixNo;
		this.idNo = idNo;
		this.idTyp = idTyp;
		this.indivBranch = indivBranch;
		this.indivCtry = indivCtry;
		this.indivEdu = indivEdu;
		this.indivEmail = indivEmail;
		this.indivEmp = indivEmp;
		this.indivEmpTelNo = indivEmpTelNo;
		this.indivFmyTel = indivFmyTel;
		this.indivMarital = indivMarital;
		this.indivSex = indivSex;
		this.phoneNo = phoneNo;
		this.queryInd = queryInd;
	}



	public String getErrorMsg() {
		return errorMsg;
	}



	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}



	public Integer getErrorCode() {
		return errorCode;
	}



	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}



	public String getApptStartDate() {
		return apptStartDate;
	}



	public void setApptStartDate(String apptStartDate) {
		this.apptStartDate = apptStartDate;
	}



	public String getCustNam() {
		return custNam;
	}



	public void setCustNam(String custNam) {
		this.custNam = custNam;
	}



	public String getCustSpell() {
		return custSpell;
	}



	public void setCustSpell(String custSpell) {
		this.custSpell = custSpell;
	}



	public List<DetailsCardInfo> getDetails() {
		return details;
	}



	public void setDetails(List<DetailsCardInfo> details) {
		this.details = details;
	}



	public String getFixNo() {
		return fixNo;
	}



	public void setFixNo(String fixNo) {
		this.fixNo = fixNo;
	}



	public String getIdNo() {
		return idNo;
	}



	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}



	public String getIdTyp() {
		return idTyp;
	}



	public void setIdTyp(String idTyp) {
		this.idTyp = idTyp;
	}



	public String getIndivBranch() {
		return indivBranch;
	}



	public void setIndivBranch(String indivBranch) {
		this.indivBranch = indivBranch;
	}



	public String getIndivCtry() {
		return indivCtry;
	}



	public void setIndivCtry(String indivCtry) {
		this.indivCtry = indivCtry;
	}



	public String getIndivEdu() {
		return indivEdu;
	}



	public void setIndivEdu(String indivEdu) {
		this.indivEdu = indivEdu;
	}



	public String getIndivEmail() {
		return indivEmail;
	}



	public void setIndivEmail(String indivEmail) {
		this.indivEmail = indivEmail;
	}



	public String getIndivEmp() {
		return indivEmp;
	}



	public void setIndivEmp(String indivEmp) {
		this.indivEmp = indivEmp;
	}



	public String getIndivEmpTelNo() {
		return indivEmpTelNo;
	}



	public void setIndivEmpTelNo(String indivEmpTelNo) {
		this.indivEmpTelNo = indivEmpTelNo;
	}



	public String getIndivFmyTel() {
		return indivFmyTel;
	}



	public void setIndivFmyTel(String indivFmyTel) {
		this.indivFmyTel = indivFmyTel;
	}



	public String getIndivMarital() {
		return indivMarital;
	}



	public void setIndivMarital(String indivMarital) {
		this.indivMarital = indivMarital;
	}



	public String getIndivSex() {
		return indivSex;
	}



	public void setIndivSex(String indivSex) {
		this.indivSex = indivSex;
	}



	public String getPhoneNo() {
		return phoneNo;
	}



	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}



	public String getQueryInd() {
		return queryInd;
	}



	public void setQueryInd(String queryInd) {
		this.queryInd = queryInd;
	}



	
}

