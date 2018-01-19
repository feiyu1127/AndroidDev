package com.yucheng.byxf.mini.message;

import java.io.Serializable;

public class SuggestionMsgEntity implements Serializable{
    private static final String TAG = SuggestionMsgEntity.class.getSimpleName();
    private static final long serialVersionUID = -7993913871024563986L;

   /* "id": 161, "launchName": "test258", "replyName": null, 
    "phone": "13111111111", "content": "123321",
    "createTime": "2015-01-05 09:43:10", "existReply": "0", "type": "0" */
	
	private Long id;
	
	/**
	 * 发起人
	 */
	private String launchName;
	/**
	 * 回复人
	 */
	private String replyName;
	/**
	 * 电话
	 */
	private String phone;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 是否回复   0未回复   1已回复
	 */
	private String existReply="0";
	/**
	 * 用户类型  0 client使用用户   1 server 系统用户
	 */
	private String type="0";
	
//	   public boolean getMsgType() {
//		   if(type.equals("0")){
//			   return true;
//		   }else{
//			   return false; 
//		   }
//	    }
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public String getLaunchName() {
		return launchName;
	}
	public void setLaunchName(String launchName) {
		this.launchName = launchName;
	}
	public String getReplyName() {
		return replyName;
	}
	public void setReplyName(String replyName) {
		this.replyName = replyName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	public String getExistReply() {
		return existReply;
	}
	public void setExistReply(String existReply) {
		this.existReply = existReply;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
	

}
