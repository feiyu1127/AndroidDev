package com.yucheng.byxf.mini.message;

import java.util.List;

public class CardNoMessage {
	private String retCode;
	private String retInfo;
	private List<CardNoId> cardList;
	
	
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


	public List<CardNoId> getCardList() {
		return cardList;
	}


	public void setCardList(List<CardNoId> cardList) {
		this.cardList = cardList;
	}
}
