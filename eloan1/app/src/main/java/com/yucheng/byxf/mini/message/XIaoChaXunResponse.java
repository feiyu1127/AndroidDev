package com.yucheng.byxf.mini.message;

import java.io.Serializable;
import java.util.List;

import android.widget.TextView;

public class XIaoChaXunResponse implements Serializable {

	public XiaoChaXunResult2 cardMsgBody;
	public String errorMsg;

	public XiaoChaXunResult2 getCardMsgBody() {
		return cardMsgBody;
	}

	public void setCardMsgBody(XiaoChaXunResult2 cardMsgBody) {
		this.cardMsgBody = cardMsgBody;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
