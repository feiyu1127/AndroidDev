package com.yucheng.byxf.mini.message;
/**
 * 卡信息
 */
public class DetailsCardInfo {

	/**
	 * 主附卡标志
	 */
	private String basicCardInd;
	/**
	 * 卡号
	 */
	private String cardNo;
	/**
	 * 卡片状态
	 */
	private String cardSts;
	/**
	 * 卡片产品
	 */
	private String cardTypDesc;
	/**
	 * 有效期(年/月)
	 */
	private String effectDt;
	/**
	 * 年费情况
	 */
	private String feCirse;
	/**
	 * 发卡日期
	 */
	private String makeDt;

	

	public DetailsCardInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DetailsCardInfo(String basicCardInd, String cardNo, String cardSts,
			String cardTypDesc, String effectDt, String feCirse, String makeDt) {
		super();
		this.basicCardInd = basicCardInd;
		this.cardNo = cardNo;
		this.cardSts = cardSts;
		this.cardTypDesc = cardTypDesc;
		this.effectDt = effectDt;
		this.feCirse = feCirse;
		this.makeDt = makeDt;
	}

	public String getBasicCardInd() {
		return basicCardInd;
	}

	public void setBasicCardInd(String basicCardInd) {
		this.basicCardInd = basicCardInd;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getCardSts() {
		return cardSts;
	}

	public void setCardSts(String cardSts) {
		this.cardSts = cardSts;
	}

	public String getCardTypDesc() {
		return cardTypDesc;
	}

	public void setCardTypDesc(String cardTypDesc) {
		this.cardTypDesc = cardTypDesc;
	}

	public String getEffectDt() {
		return effectDt;
	}

	public void setEffectDt(String effectDt) {
		this.effectDt = effectDt;
	}

	public String getFeCirse() {
		return feCirse;
	}

	public void setFeCirse(String feCirse) {
		this.feCirse = feCirse;
	}

	public String getMakeDt() {
		return makeDt;
	}

	public void setMakeDt(String makeDt) {
		this.makeDt = makeDt;
	}
}
