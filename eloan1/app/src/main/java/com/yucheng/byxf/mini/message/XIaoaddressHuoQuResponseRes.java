package com.yucheng.byxf.mini.message;

import java.io.Serializable;

public class XIaoaddressHuoQuResponseRes implements Serializable {

			public XiaogetAddressBody getAddressBody;
			public String errorMsg;

			public XiaogetAddressBody getGetAddressBody() {
				return getAddressBody;
			}

			public void setGetAddressBody(XiaogetAddressBody getAddressBody) {
				this.getAddressBody = getAddressBody;
			}

			public String getErrorMsg() {
				return errorMsg;
			}

			public void setErrorMsg(String errorMsg) {
				this.errorMsg = errorMsg;
			}


}
