package com.yucheng.byxf.mini.eloan.domain;

import java.io.Serializable;

/**
 * 基本信息
 * @author danny
 *
 */
public class BasicInfo implements Serializable{
	/**
	 * 身份证信息一； 身份证,性别，出生日期。
	 */
	private String idcard;
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	private String birthday;// 生日
	private String sex;// 性别
	private String expiryStartDate;// 证件有效期起始日期
	private String expiryEndDate;// 证件有效期终止日期
	private String birthPlace;// 籍贯
	private String household_email;// 户籍邮编；
	public boolean getOldCilent1 = true;
	
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getExpiryStartDate() {
		return expiryStartDate;
	}
	public void setExpiryStartDate(String expiryStartDate) {
		this.expiryStartDate = expiryStartDate;
	}
	public String getExpiryEndDate() {
		return expiryEndDate;
	}
	public void setExpiryEndDate(String expiryEndDate) {
		this.expiryEndDate = expiryEndDate;
	}
	public String getBirthPlace() {
		return birthPlace;
	}
	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}
	public String getHousehold_email() {
		return household_email;
	}
	public void setHousehold_email(String household_email) {
		this.household_email = household_email;
	}
	public boolean isGetOldCilent1() {
		return getOldCilent1;
	}
	public void setGetOldCilent1(boolean getOldCilent1) {
		this.getOldCilent1 = getOldCilent1;
	}
	/**
	 * 身份证信息二；
	 */
	// 婚姻
	public String marriageStatue;
	// 婚姻代码
	public String marriageStatueCode;
	// 供养人数
	public String providePerson;
	public String liveAddress;
	// 邮编
	public String livePostcode;
	// 住在性质
	public String liveProperties;
	// 住在性质
	public String livePropertiesCode;
	// 文化程度
	public String cultureDegree;
	// 文化程度
	public String cultureDegreeCode;
	// 区号
	public String zoneCode;
	// 电话
	public String telPhone;
	// 电子邮箱
	public String elecTronEmail;
	// 手机号
	public String mobilePhone;
	public boolean getOldCilent2 = true;
	// 租金
	public String indivRentAmt;

	public String getIndivRentAmt() {
		return indivRentAmt;
	}
	public void setIndivRentAmt(String indivRentAmt) {
		this.indivRentAmt = indivRentAmt;
	}
	public String getLivePropertiesCode() {
		return livePropertiesCode;
	}
	public void setLivePropertiesCode(String livePropertiesCode) {
		this.livePropertiesCode = livePropertiesCode;
	}
	public String getCultureDegreeCode() {
		return cultureDegreeCode;
	}
	public void setCultureDegreeCode(String cultureDegreeCode) {
		this.cultureDegreeCode = cultureDegreeCode;
	}
	public String getMarriageStatueCode() {
		return marriageStatueCode;
	}
	public void setMarriageStatueCode(String marriageStatueCode) {
		this.marriageStatueCode = marriageStatueCode;
	}
	public String getMarriageStatue() {
		return marriageStatue;
	}
	public void setMarriageStatue(String marriageStatue) {
		this.marriageStatue = marriageStatue;
	}
	public String getProvidePerson() {
		return providePerson;
	}
	public void setProvidePerson(String providePerson) {
		this.providePerson = providePerson;
	}
	public String getLiveAddress() {
		return liveAddress;
	}
	public void setLiveAddress(String liveAddress) {
		this.liveAddress = liveAddress;
	}
	public String getLivePostcode() {
		return livePostcode;
	}
	public void setLivePostcode(String livePostcode) {
		this.livePostcode = livePostcode;
	}
	public String getLiveProperties() {
		return liveProperties;
	}
	public void setLiveProperties(String liveProperties) {
		this.liveProperties = liveProperties;
	}
	public String getCultureDegree() {
		return cultureDegree;
	}
	public void setCultureDegree(String cultureDegree) {
		this.cultureDegree = cultureDegree;
	}
	public String getZoneCode() {
		return zoneCode;
	}
	public void setZoneCode(String zoneCode) {
		this.zoneCode = zoneCode;
	}
	public String getTelPhone() {
		return telPhone;
	}
	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}
	public String getElecTronEmail() {
		return elecTronEmail;
	}
	public void setElecTronEmail(String elecTronEmail) {
		this.elecTronEmail = elecTronEmail;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public boolean isGetOldCilent2() {
		return getOldCilent2;
	}
	public void setGetOldCilent2(boolean getOldCilent2) {
		this.getOldCilent2 = getOldCilent2;
	}
	
}

