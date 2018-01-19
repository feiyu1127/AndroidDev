package com.yucheng.byxf.mini.eloan.domain;
import java.io.Serializable;
/**
 * 贷款申请信息
 * @author danny
 */
public class LoanApplyPersonalInfo implements Serializable {
	// 申請人編號
	private String apply_person_id;
	// 申请贷款金额
	private String apply_amount_value;
	
	
	// 贷款用途
	private String tick_loan_pursose;
	private String tick_loan_pursoseCode;
	
	private String tick_loan_other_purpose;
	
	// 申请贷款方式
	private String tick_loan_method;
	private String tick_loan_methodCode;
	// 申请贷款期限
	private String applyLoanTime;
	private String applyLoanTimeCode;
	// 信件邮寄地址
	private String send_method;
	private String send_methodCode;
	// 还款账号所属银行
	private String repay_acount_type;
	private String repay_acount_typeCode;
	
	// 户名
	private String username_value;
	// 账户
	private String account_value;
	
	
//	// 身份证证件类型
//	private String adentify_type;
//	
	
	// 身份证证件号码
	private String adentify_num;
//	// 收费项目
//	private String pay_project;
//	// 收费标准
//	private String pay_standerd;
	// 推广员工id
	private String employee_id;
	// 推广员工姓名
	private String employee_name;
	
	public String getRepay_acount_typeCode() {
		return repay_acount_typeCode;
	}
	public void setRepay_acount_typeCode(String repay_acount_typeCode) {
		this.repay_acount_typeCode = repay_acount_typeCode;
	}
	public String getTick_loan_other_purpose() {
		return tick_loan_other_purpose;
	}
	public void setTick_loan_other_purpose(String tick_loan_other_purpose) {
		this.tick_loan_other_purpose = tick_loan_other_purpose;
	}
	public String getTick_loan_pursoseCode() {
		return tick_loan_pursoseCode;
	}
	public void setTick_loan_pursoseCode(String tick_loan_pursoseCode) {
		this.tick_loan_pursoseCode = tick_loan_pursoseCode;
	}
	public String getTick_loan_methodCode() {
		return tick_loan_methodCode;
	}
	public void setTick_loan_methodCode(String tick_loan_methodCode) {
		this.tick_loan_methodCode = tick_loan_methodCode;
	}
	public String getApplyLoanTimeCode() {
		return applyLoanTimeCode;
	}
	public void setApplyLoanTimeCode(String applyLoanTimeCode) {
		this.applyLoanTimeCode = applyLoanTimeCode;
	}
	public String getSend_methodCode() {
		return send_methodCode;
	}
	public void setSend_methodCode(String send_methodCode) {
		this.send_methodCode = send_methodCode;
	}
	public String getApply_person_id() {
		return apply_person_id;
	}
	public void setApply_person_id(String apply_person_id) {
		this.apply_person_id = apply_person_id;
	}
	public String getApply_amount_value() {
		return apply_amount_value;
	}
	public void setApply_amount_value(String apply_amount_value) {
		this.apply_amount_value = apply_amount_value;
	}
	public String getTick_loan_pursose() {
		return tick_loan_pursose;
	}
	public void setTick_loan_pursose(String tick_loan_pursose) {
		this.tick_loan_pursose = tick_loan_pursose;
	}
	public String getTick_loan_method() {
		return tick_loan_method;
	}
	public void setTick_loan_method(String tick_loan_method) {
		this.tick_loan_method = tick_loan_method;
	}
	public String getApplyLoanTime() {
		return applyLoanTime;
	}
	public void setApplyLoanTime(String applyLoanTime) {
		this.applyLoanTime = applyLoanTime;
	}
	public String getSend_method() {
		return send_method;
	}
	public void setSend_method(String send_method) {
		this.send_method = send_method;
	}
	public String getRepay_acount_type() {
		return repay_acount_type;
	}
	public void setRepay_acount_type(String repay_acount_type) {
		this.repay_acount_type = repay_acount_type;
	}
	public String getUsername_value() {
		return username_value;
	}
	public void setUsername_value(String username_value) {
		this.username_value = username_value;
	}
	public String getAccount_value() {
		return account_value;
	}
	public void setAccount_value(String account_value) {
		this.account_value = account_value;
	}
	
	public String getAdentify_num() {
		return adentify_num;
	}
	public void setAdentify_num(String adentify_num) {
		this.adentify_num = adentify_num;
	}
	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
}
