package com.yucheng.byxf.mini.message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class XiaojinYingRelationInfoResultBody implements Serializable {
 

	private List<XiaojinYingRelationInfoResultBodyList> details;

	public List<XiaojinYingRelationInfoResultBodyList> getDetails() {
		return details;
	}

	public void setDetails(ArrayList<XiaojinYingRelationInfoResultBodyList> details) {
		this.details = details;
	}
 

}
