package com.yucheng.byxf.mini.message;

import java.io.Serializable;

public class XiaojinYingRelationInfoResultBodyList implements Serializable {
	private static final long serialVersionUID = 1L;
	// 联系人类型 contTyp 01 直系联系人02 其他联系人03 第三联系人04 第四联系人
	private String contTyp;
	// 联系人姓名 contName
	private String contName;
	// 联系人关系 contRel 01 配偶02 父母03 子女04 其他血亲05 其他姻亲06 同事07 合伙人08 其他关系09 兄弟姐妹10
	// 同学11 朋友
	private String contRel;
	// 联系人手机 contPhone
	private String contPhone;
	// 联系人电话区号 contZone
	private String contZone;
	// 联系人电话 contTel
	private String contTel;

	public String getContTyp() {
		return contTyp;
	}

	public String getContName() {
		return contName;
	}

	public String getContRel() {
		return contRel;
	}

	public String getContPhone() {
		return contPhone;
	}

	public String getContZone() {
		return contZone;
	}

	public String getContTel() {
		return contTel;
	}

	public void setContTyp(String contTyp) {
		this.contTyp = contTyp;
	}

	public void setContName(String contName) {
		this.contName = contName;
	}

	public void setContRel(String contRel) {
		this.contRel = contRel;
	}

	public void setContPhone(String contPhone) {
		this.contPhone = contPhone;
	}

	public void setContZone(String contZone) {
		this.contZone = contZone;
	}

	public void setContTel(String contTel) {
		this.contTel = contTel;
	}
}
