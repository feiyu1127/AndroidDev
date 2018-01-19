package com.yucheng.byxf.mini.eloan.domain;

import java.io.Serializable;
/**
 * 职业信息
 * @author danny
 */
public class ProfessionalInfo implements Serializable{
	// 现单位名称
	private String companyName;
	// 任职部门
	private String rzDepartment;
	// 本单位工作年数
	private String workYear;
	// 总工作年限
	private String totalWorkYear;
	// 单位地址
	private String companyAddress;
	// 单位邮编
	private String companyMail;
	// 单位电话区号
	private String companyAreaNum;
	// 单位电话号码
	private String companytelNum;
	// 分机号
	private String companyExtensionNum;
	
	// 企业规模
	private String dpScale;
	private String dpScaleCode;
	// 单位性质
	private String dpNature;
	private String dpNatureCode;
	// 行业性质
	private String tradeNature;
	private String tradeNatureCode;
	// 现任职位
	private String nowRole;
	private String nowRoleCode;
	// 个人工资月收入
	private String moneyMonth;
	private boolean getOldCilent3 = true;
	
	public String getDpScaleCode() {
		return dpScaleCode;
	}
	public void setDpScaleCode(String dpScaleCode) {
		this.dpScaleCode = dpScaleCode;
	}
	public String getDpNatureCode() {
		return dpNatureCode;
	}
	public void setDpNatureCode(String dpNatureCode) {
		this.dpNatureCode = dpNatureCode;
	}
	public String getTradeNatureCode() {
		return tradeNatureCode;
	}
	public void setTradeNatureCode(String tradeNatureCode) {
		this.tradeNatureCode = tradeNatureCode;
	}
	public String getNowRoleCode() {
		return nowRoleCode;
	}
	public void setNowRoleCode(String nowRoleCode) {
		this.nowRoleCode = nowRoleCode;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getRzDepartment() {
		return rzDepartment;
	}
	public void setRzDepartment(String rzDepartment) {
		this.rzDepartment = rzDepartment;
	}
	public String getWorkYear() {
		return workYear;
	}
	public void setWorkYear(String workYear) {
		this.workYear = workYear;
	}
	public String getTotalWorkYear() {
		return totalWorkYear;
	}
	public void setTotalWorkYear(String totalWorkYear) {
		this.totalWorkYear = totalWorkYear;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getCompanyMail() {
		return companyMail;
	}
	public void setCompanyMail(String companyMail) {
		this.companyMail = companyMail;
	}
	public String getCompanyAreaNum() {
		return companyAreaNum;
	}
	public void setCompanyAreaNum(String companyAreaNum) {
		this.companyAreaNum = companyAreaNum;
	}
	public String getCompanytelNum() {
		return companytelNum;
	}
	public void setCompanytelNum(String companytelNum) {
		this.companytelNum = companytelNum;
	}
	public String getCompanyExtensionNum() {
		return companyExtensionNum;
	}
	public void setCompanyExtensionNum(String companyExtensionNum) {
		this.companyExtensionNum = companyExtensionNum;
	}
	public String getDpScale() {
		return dpScale;
	}
	public void setDpScale(String dpScale) {
		this.dpScale = dpScale;
	}
	public String getDpNature() {
		return dpNature;
	}
	public void setDpNature(String dpNature) {
		this.dpNature = dpNature;
	}
	public String getTradeNature() {
		return tradeNature;
	}
	public void setTradeNature(String tradeNature) {
		this.tradeNature = tradeNature;
	}
	public String getNowRole() {
		return nowRole;
	}
	public void setNowRole(String nowRole) {
		this.nowRole = nowRole;
	}
	public String getMoneyMonth() {
		return moneyMonth;
	}
	public void setMoneyMonth(String moneyMonth) {
		this.moneyMonth = moneyMonth;
	}
}