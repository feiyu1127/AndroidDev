package com.yucheng.byxf.mini.message;

/******************************************************************************
 * 
 * 北银消费金融公司移动业务终端
 *
 * Copyright © 2012 Yucheng Technologies Limited All Rights Reserved.
 * 
 * Created on 2012-9-26 17:33:53
 *
 *******************************************************************************/

import java.util.StringTokenizer;

/**
 * PERSONAL_APK程序
 * 
 * @version 1.0
 * 
 * @author mark
 */

public class PersonalApkResponse extends Response {

	private PersonalApk result ;

	public PersonalApk getResult() {
		return result;
	}

	public void setResult(PersonalApk result) {
		this.result = result;
	}


}