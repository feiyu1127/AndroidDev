package com.yucheng.byxf.mini.eloan.domain;

import java.io.Serializable;

/**
 * 资产信息
 * 
 * @author danny
 * 
 */
public class AssertInfo implements Serializable {

	private static AssertInfo assertInfo = null;
	// 个人资产房产金额
	private String houseMoney;
	// 个人资产汽车金额
	private String carMoney;
	// 个人资产其它金额
	private String otherMoney;

	// 购房贷款余额
	private String daikuanHouse;
	// 购车贷款余额
	private String daikuanCar;
	// 其它贷款余额
	private String daikuanOher;

	// 购房月还款金额
	private String payHouse;
	// 购车月还款金额
	private String payCar;
	// 其它月还款金额
	private String payOher;

	// 是否持有信用卡
	private String isHavingCreditCard;
	// 单张信用卡最高额度
	private String cardTopMoney;
	// 信用卡开卡银行
	private String cardBankName;

	private AssertInfo() {
		// TODO Auto-generated constructor stub
	}
	public static void setAssertInfo(AssertInfo assertInfo) {
		AssertInfo.assertInfo = assertInfo;
	}
	public static  AssertInfo getInstance() {
		if (assertInfo == null) {
			assertInfo = new AssertInfo();
		}
		return assertInfo;
	};

	public String getHouseMoney() {
		return houseMoney;
	}

	public void setHouseMoney(String houseMoney) {
		this.houseMoney = houseMoney;
	}

	public String getCarMoney() {
		return carMoney;
	}

	public void setCarMoney(String carMoney) {
		this.carMoney = carMoney;
	}

	public String getOtherMoney() {
		return otherMoney;
	}

	public void setOtherMoney(String otherMoney) {
		this.otherMoney = otherMoney;
	}

	public String getDaikuanHouse() {
		return daikuanHouse;
	}

	public void setDaikuanHouse(String daikuanHouse) {
		this.daikuanHouse = daikuanHouse;
	}

	public String getDaikuanCar() {
		return daikuanCar;
	}

	public void setDaikuanCar(String daikuanCar) {
		this.daikuanCar = daikuanCar;
	}

	public String getDaikuanOher() {
		return daikuanOher;
	}

	public void setDaikuanOher(String daikuanOher) {
		this.daikuanOher = daikuanOher;
	}

	public String getPayHouse() {
		return payHouse;
	}

	public void setPayHouse(String payHouse) {
		this.payHouse = payHouse;
	}

	public String getPayCar() {
		return payCar;
	}

	public void setPayCar(String payCar) {
		this.payCar = payCar;
	}

	public String getPayOher() {
		return payOher;
	}

	public void setPayOher(String payOher) {
		this.payOher = payOher;
	}

	public String getIsHavingCreditCard() {
		return isHavingCreditCard;
	}

	public void setIsHavingCreditCard(String isHavingCreditCard) {
		this.isHavingCreditCard = isHavingCreditCard;
	}

	public String getCardTopMoney() {
		return cardTopMoney;
	}

	public void setCardTopMoney(String cardTopMoney) {
		this.cardTopMoney = cardTopMoney;
	}

	public String getCardBankName() {
		return cardBankName;
	}

	public void setCardBankName(String cardBankName) {
		this.cardBankName = cardBankName;
	}
}