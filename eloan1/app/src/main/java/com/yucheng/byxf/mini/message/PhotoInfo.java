package com.yucheng.byxf.mini.message;

public class PhotoInfo {
	/**
	 * 文档编号
	 */
	private String docNo;
	/**
	 * 申请编号
	 */
	private String bizNo;
	/**
	 * 文档名称
	 */
	private String docName;
	/**
	 * 文档类型
	 */
	private String docType;
	/**
	 * 文件名称
	 */
	private String fileName;
	/**
	 * 业务类型
	 */
	private String bizType;
	/**
	 * 图片路径
	 */
	private String picture;

	private String chinaName;

	private String videoInfo;

	public String getVideoInfo() {
		return videoInfo;
	}

	public void setVideoInfo(String videoInfo) {
		this.videoInfo = videoInfo;
	}

	public String getChinaName() {
		return chinaName;
	}

	public void setChinaName(String chinaName) {
		this.chinaName = chinaName;
	}

	public PhotoInfo() {
		super();
	}

	// Mini卡申请
	public PhotoInfo(String docNo, String bizNo, String docName,
			String docType, String fileName, String bizType, String picture) {
		super();
		this.docNo = docNo;
		this.bizNo = bizNo;
		this.docName = docName;
		this.docType = docType;
		this.fileName = fileName;
		this.bizType = bizType;
		this.picture = picture;
	}

	// 极速贷 9
	public PhotoInfo(String docNo, String bizNo, String docName,
			String docType, String fileName, String bizType, String picture,
			String videoInfo, String s) {
		super();
		this.docNo = docNo;
		this.bizNo = bizNo;
		this.docName = docName;
		this.docType = docType;
		this.fileName = fileName;
		this.bizType = bizType;
		this.picture = picture;
		this.videoInfo = videoInfo;
	}

	// 轻松贷8
	public PhotoInfo(String docNo, String bizNo, String docName,
			String docType, String fileName, String bizType, String picture,
			String chinaName) {
		super();
		this.docNo = docNo;
		this.bizNo = bizNo;
		this.docName = docName;
		this.docType = docType;
		this.fileName = fileName;
		this.bizType = bizType;
		this.picture = picture;
		this.chinaName = chinaName;
	}

	public String getDocNo() {
		return docNo;
	}

	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}

	public String getBizNo() {
		return bizNo;
	}

	public void setBizNo(String bizNo) {
		this.bizNo = bizNo;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

}