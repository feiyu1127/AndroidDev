/******************************************************************************
 * 
 * 北银消费金融公司-移动业务终端三期-轻松e贷
 *
 * Copyright © 2014 YuchengTech Technologies Limited All Rights Reserved.
 * 
 * Created on 2014-3-12 16:30:36
 *
 *******************************************************************************/
package com.yucheng.byxf.mini.message;

/**
 * 文档信息实体类
 *
 * @version 1.0
 *
 * @author neo
 */
public class MiniDocInfo  implements java.io.Serializable {

	/**
 	  *  文档编号
      */
 	private String docNo;
	/**
 	  *  申请编号
      */
 	private String bizNo;
	/**
 	  *  业务类型
      */
 	private String bizType = "999";
	/**
 	  *  文档名称
      */
 	private String docName;
	/**
 	  *  文档类型
      */
 	private String docType="LCDOC";
	/**
 	  *  文件名称
      */
 	private String fileName;
	/**
 	  *  文档路径
      */
 	private String filePath;
	/**
 	  *  ftp文档路径
      */
 	private String ftpFilePath = "LCDOC/";
	/**
	  *  上传状态
     */
	private Integer status = 0;

    public MiniDocInfo() {
    }
	
    public MiniDocInfo(String docNo) {
        this.docNo = docNo;
    }
    public MiniDocInfo(String docNo, String bizNo, String bizType, String docName, String docType, String fileName, String filePath, String ftpFilePath) {
       this.docNo = docNo;
       this.bizNo = bizNo;
       this.bizType = bizType;
       this.docName = docName;
       this.docType = docType;
       this.fileName = fileName;
       this.filePath = filePath;
       this.ftpFilePath = ftpFilePath;
    }
   
   /**     
     *访问<文档编号>属性
     */
    public String getDocNo() {
        return this.docNo;
    }	    
    /**     
     *设置<文档编号>属性
     */
    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }
   /**     
     *访问<申请编号>属性
     */
    public String getBizNo() {
        return this.bizNo;
    }	    
    /**     
     *设置<申请编号>属性
     */
    public void setBizNo(String bizNo) {
        this.bizNo = bizNo;
    }
   /**     
     *访问<业务类型>属性
     */
    public String getBizType() {
        return this.bizType;
    }	    
    /**     
     *设置<业务类型>属性
     */
    public void setBizType(String bizType) {
        this.bizType = bizType;
    }
   /**     
     *访问<文档名称>属性
     */
    public String getDocName() {
        return this.docName;
    }	    
    /**     
     *设置<文档名称>属性
     */
    public void setDocName(String docName) {
        this.docName = docName;
    }
   /**     
     *访问<文档类型>属性
     */
    public String getDocType() {
        return this.docType;
    }	    
    /**     
     *设置<文档类型>属性
     */
    public void setDocType(String docType) {
        this.docType = docType;
    }
   /**     
     *访问<文件名称>属性
     */
    public String getFileName() {
        return this.fileName;
    }	    
    /**     
     *设置<文件名称>属性
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
   /**     
     *访问<文档路径>属性
     */
    public String getFilePath() {
        return this.filePath;
    }	    
    /**     
     *设置<文档路径>属性
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
   /**     
     *访问<ftp文档路径>属性
     */
    public String getFtpFilePath() {
        return this.ftpFilePath;
    }	    
    /**     
     *设置<ftp文档路径>属性
     */
    public void setFtpFilePath(String ftpFilePath) {
        this.ftpFilePath = ftpFilePath;
    }
    
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
