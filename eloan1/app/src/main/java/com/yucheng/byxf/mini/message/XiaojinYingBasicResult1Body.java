package com.yucheng.byxf.mini.message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class XiaojinYingBasicResult1Body implements Serializable {

	// 性别
	private String indivSex;// 1 男2 女3 未知的性别4 未说明的性别
	// 国籍
	private String indivCtry;
	// 婚姻状况
	private String indivMarital; // 10 未婚20 已婚90 其他
	// 教育程度
	private String indivEdu; // 08 博士及以上09 硕士10 本科20 大专30 高中或中专40 初中及以下50 无
	// 家庭电话区号
	private String indivFmyZome;
	// 家庭电话
	private String indivFmyTel;
	// 手机号码
	private String indivMobile;
	// 传真机号码
	private String fixNo;
	// E-mail
	private String indivEmail;
	// 公司电话区号
	private String indivEmpZone;
	// 公司电话
	private String indivEmpTelNo;
	// 公司电话分机号
	private String indivEmpTelSub;
	// 单位名称
	private String indivEmp;
	// 部门
	private String indivBranch;
	// 1 高级管理人员11 厅局级以上12 处级13 科级14 一般干部2 一般管理人员3 一般正式员工4 非正式员工5
	// 无6 企业负责人7 中层管理人员9 其他
	private String indivPosition;

	public String getIndivSex() {
		return indivSex;
	}

	public String getIndivCtry() {
		return indivCtry;
	}

	public String getIndivMarital() {
		return indivMarital;
	}

	public String getIndivEdu() {
		return indivEdu;
	}

	public String getIndivFmyZome() {
		return indivFmyZome;
	}

	public String getIndivFmyTel() {
		return indivFmyTel;
	}

	public String getIndivMobile() {
		return indivMobile;
	}

	public String getFixNo() {
		return fixNo;
	}

	public String getIndivEmail() {
		return indivEmail;
	}

	public String getIndivEmpZone() {
		return indivEmpZone;
	}

	public String getIndivEmpTelNo() {
		return indivEmpTelNo;
	}

	public String getIndivEmpTelSub() {
		return indivEmpTelSub;
	}

	public String getIndivEmp() {
		return indivEmp;
	}

	public String getIndivBranch() {
		return indivBranch;
	}

	public String getIndivPosition() {
		return indivPosition;
	}

	public void setIndivSex(String indivSex) {
		this.indivSex = indivSex;
	}

	public void setIndivCtry(String indivCtry) {
		this.indivCtry = indivCtry;
	}

	public void setIndivMarital(String indivMarital) {
		this.indivMarital = indivMarital;
	}

	public void setIndivEdu(String indivEdu) {
		this.indivEdu = indivEdu;
	}

	public void setIndivFmyZome(String indivFmyZome) {
		this.indivFmyZome = indivFmyZome;
	}

	public void setIndivFmyTel(String indivFmyTel) {
		this.indivFmyTel = indivFmyTel;
	}

	public void setIndivMobile(String indivMobile) {
		this.indivMobile = indivMobile;
	}

	public void setFixNo(String fixNo) {
		this.fixNo = fixNo;
	}

	public void setIndivEmail(String indivEmail) {
		this.indivEmail = indivEmail;
	}

	public void setIndivEmpZone(String indivEmpZone) {
		this.indivEmpZone = indivEmpZone;
	}

	public void setIndivEmpTelNo(String indivEmpTelNo) {
		this.indivEmpTelNo = indivEmpTelNo;
	}

	public void setIndivEmpTelSub(String indivEmpTelSub) {
		this.indivEmpTelSub = indivEmpTelSub;
	}

	public void setIndivEmp(String indivEmp) {
		this.indivEmp = indivEmp;
	}

	public void setIndivBranch(String indivBranch) {
		this.indivBranch = indivBranch;
	}

	public void setIndivPosition(String indivPosition) {
		this.indivPosition = indivPosition;
	}

}
