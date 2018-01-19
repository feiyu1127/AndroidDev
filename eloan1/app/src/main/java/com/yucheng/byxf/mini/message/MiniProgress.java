/******************************************************************************
 * 
 * 北银消费金融公司-移动业务终端三期-轻松e贷
 *
 * Copyright © 2014 YuchengTech Technologies Limited All Rights Reserved.
 * 
 * Created on 2014-2-22 18:12:44
 *
 *******************************************************************************/
package com.yucheng.byxf.mini.message;

/**
 * 审批状态查询实体类
 * 
 * @version 1.0
 * 
 * @author neo
 */
public class MiniProgress implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2413435271480631225L;
	/**
	 * 交易码
	 */
	private String txCde;
	/**
	 * 客户姓名
	 */
	private String custName;
	/**
	 * 证件号
	 */
	private String idNo;
	/**
	 * 申请编号
	 */
	private String applCde;
	/**
	 * 审批金额
	 */
	private String apprvAmt;
	/**
	 * 审批状态
	 */
	private String state;

	public MiniProgress() {
	}

	public MiniProgress(String txCde) {
		this.txCde = txCde;
	}

	public MiniProgress(String txCde, String custName, String idNo,
			String applCde, String apprvAmt, String state) {
		this.txCde = txCde;
		this.custName = custName;
		this.idNo = idNo;
		this.applCde = applCde;
		this.apprvAmt = apprvAmt;
		this.state = state;
	}

	/**
	 * 访问<交易码>属性
	 */
	public String getTxCde() {
		return this.txCde;
	}

	/**
	 * 设置<交易码>属性
	 */
	public void setTxCde(String txCde) {
		this.txCde = txCde;
	}

	/**
	 * 访问<客户姓名>属性
	 */
	public String getCustName() {
		return this.custName;
	}

	/**
	 * 设置<客户姓名>属性
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}

	/**
	 * 访问<证件号>属性
	 */
	public String getIdNo() {
		return this.idNo;
	}

	/**
	 * 设置<证件号>属性
	 */
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	/**
	 * 访问<申请编号>属性
	 */
	public String getApplCde() {
		return this.applCde;
	}

	/**
	 * 设置<申请编号>属性
	 */
	public void setApplCde(String applCde) {
		this.applCde = applCde;
	}

	/**
	 * 访问<审批金额>属性
	 */
	public String getApprvAmt() {
		return this.apprvAmt;
	}

	/**
	 * 设置<审批金额>属性
	 */
	public void setApprvAmt(String apprvAmt) {
		this.apprvAmt = apprvAmt;
	}

	/**
	 * 访问<审批状态>属性
	 */
	public String getState() {
		return this.state;
	}

	/**
	 * 设置<审批状态>属性
	 */
	public void setState(String state) {
		this.state = state;
	}

}
