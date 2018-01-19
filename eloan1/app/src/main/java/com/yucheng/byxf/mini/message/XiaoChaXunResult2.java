package com.yucheng.byxf.mini.message;

import java.io.Serializable;
import java.util.List;

public class XiaoChaXunResult2 implements Serializable {
	// 卡号
	private String cardNo;
	// 证件号码
	private String idNo;
	// 姓名
	private String custNam;
	// 制卡日期
	private String makeDt;
	// 人民币信用额度
	private String crAmt;

	// 人民币可用额度
	private String availAmt;
	// 分期付款信用额度
	private String stagesAmt;
	// 分期付款可用额度
	private String stagesAvailAmt;
	// 附卡额度设置方式
	private String creditCde; // SE 与主卡共享额度 PT 附属卡额度百分比 FX 附属卡额度固定
	// 挂失日期
	private String lossDt;
	// 卡片种类描述
	private String cardTypDesc;
	// 卡片专案代码
	private String cardProm;
	// 下次年费收取日期
	private String nextFeeDt;
	// 自动还款方式
	private String isMinPay;// Y:最低额还款 N:全额还款
	// 卡片状态
	private String cardSts;// A: 有效 I: 无效 C: 销卡 H: 挂失
	// 凸字姓名
	private String custSpellName;
	// 激活日期
	private String actvDt;
	// 年费类型
	private String feeCde;
	// 卡片有效期(年/月)
	private String effectDt;
	// 卡片版面
	private String cardDesc;
	// 提现利率
	private String loanIntRate;
	// 还款卡号
	private String acctNo;
	// 账单日
	private String dueDt;
	// //预借现金信用额度
	private String cashAmt;
	// 预借现金可用额度
	private String cashAvailAmt;
	// 上次续卡日期
	private String extendDt;
	// 消费是否使用密码
	private String xfPwdInd;
	// 业务类型
	private String bussTyp; // CASHOUT Mini卡, PROFIT 循环时贷
	// 溢缴款
	private String osAmt; // 已用额度=信用额度+溢缴款-可用额度
	// 上期账单金额
	private String lastBillAmt;
	// 当期未出账单金额
	private String curBillAmt;

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getCustNam() {
		return custNam;
	}

	public void setCustNam(String custNam) {
		this.custNam = custNam;
	}

	public String getMakeDt() {
		return makeDt;
	}

	public void setMakeDt(String makeDt) {
		this.makeDt = makeDt;
	}

	public String getCrAmt() {
		return crAmt;
	}

	public void setCrAmt(String crAmt) {
		this.crAmt = crAmt;
	}

	public String getAvailAmt() {
		return availAmt;
	}

	public void setAvailAmt(String availAmt) {
		this.availAmt = availAmt;
	}

	public String getStagesAmt() {
		return stagesAmt;
	}

	public void setStagesAmt(String stagesAmt) {
		this.stagesAmt = stagesAmt;
	}

	public String getStagesAvailAmt() {
		return stagesAvailAmt;
	}

	public void setStagesAvailAmt(String stagesAvailAmt) {
		this.stagesAvailAmt = stagesAvailAmt;
	}

	public String getCreditCde() {
		return creditCde;
	}

	public void setCreditCde(String creditCde) {
		this.creditCde = creditCde;
	}

	public String getLossDt() {
		return lossDt;
	}

	public void setLossDt(String lossDt) {
		this.lossDt = lossDt;
	}

	public String getCardTypDesc() {
		return cardTypDesc;
	}

	public void setCardTypDesc(String cardTypDesc) {
		this.cardTypDesc = cardTypDesc;
	}

	public String getCardProm() {
		return cardProm;
	}

	public void setCardProm(String cardProm) {
		this.cardProm = cardProm;
	}

	public String getNextFeeDt() {
		return nextFeeDt;
	}

	public void setNextFeeDt(String nextFeeDt) {
		this.nextFeeDt = nextFeeDt;
	}

	public String getIsMinPay() {
		return isMinPay;
	}

	public void setIsMinPay(String isMinPay) {
		this.isMinPay = isMinPay;
	}

	public String getCardSts() {
		return cardSts;
	}

	public void setCardSts(String cardSts) {
		this.cardSts = cardSts;
	}

	public String getCustSpellName() {
		return custSpellName;
	}

	public void setCustSpellName(String custSpellName) {
		this.custSpellName = custSpellName;
	}

	public String getActvDt() {
		return actvDt;
	}

	public void setActvDt(String actvDt) {
		this.actvDt = actvDt;
	}

	public String getFeeCde() {
		return feeCde;
	}

	public void setFeeCde(String feeCde) {
		this.feeCde = feeCde;
	}

	public String getEffectDt() {
		return effectDt;
	}

	public void setEffectDt(String effectDt) {
		this.effectDt = effectDt;
	}

	public String getCardDesc() {
		return cardDesc;
	}

	public void setCardDesc(String cardDesc) {
		this.cardDesc = cardDesc;
	}

	public String getLoanIntRate() {
		return loanIntRate;
	}

	public void setLoanIntRate(String loanIntRate) {
		this.loanIntRate = loanIntRate;
	}

	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

	public String getDueDt() {
		return dueDt;
	}

	public void setDueDt(String dueDt) {
		this.dueDt = dueDt;
	}

	public String getCashAmt() {
		return cashAmt;
	}

	public void setCashAmt(String cashAmt) {
		this.cashAmt = cashAmt;
	}

	public String getCashAvailAmt() {
		return cashAvailAmt;
	}

	public void setCashAvailAmt(String cashAvailAmt) {
		this.cashAvailAmt = cashAvailAmt;
	}

	public String getExtendDt() {
		return extendDt;
	}

	public void setExtendDt(String extendDt) {
		this.extendDt = extendDt;
	}

	public String getXfPwdInd() {
		return xfPwdInd;
	}

	public void setXfPwdInd(String xfPwdInd) {
		this.xfPwdInd = xfPwdInd;
	}

	public String getBussTyp() {
		return bussTyp;
	}

	public void setBussTyp(String bussTyp) {
		this.bussTyp = bussTyp;
	}

	public String getOsAmt() {
		return osAmt;
	}

	public void setOsAmt(String osAmt) {
		this.osAmt = osAmt;
	}

	public String getLastBillAmt() {
		return lastBillAmt;
	}

	public void setLastBillAmt(String lastBillAmt) {
		this.lastBillAmt = lastBillAmt;
	}

	public String getCurBillAmt() {
		return curBillAmt;
	}

	public void setCurBillAmt(String curBillAmt) {
		this.curBillAmt = curBillAmt;
	}

}
