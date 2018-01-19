package com.yucheng.byxf.mini.message;

import java.io.Serializable;

public class XiaogetAddressBody implements Serializable {

	// 账单地址类型 sendTyp 00 居住地址01 公司地址02 房产地址03 户籍地址

	private String sendTyp;
	// 家庭地址
	private String contAddrJt;
	// 家庭地址邮编
	private String addrJtZip;
	// 户籍地址
	private String contAddrHj;
	// 户籍地址邮编
	private String addrHjZip;
	// 房产地址
	private String contAddrFc;
	// 房产地址邮编
	private String addrFcZip;
	// 公司地址
	private String contAddrGs;
	// 公司地址邮编
	private String addrGsZip;

	public String getSendTyp() {
		return sendTyp;
	}

	public void setSendTyp(String sendTyp) {
		this.sendTyp = sendTyp;
	}

	public String getContAddrJt() {
		return contAddrJt;
	}

	public void setContAddrJt(String contAddrJt) {
		this.contAddrJt = contAddrJt;
	}

	public String getAddrJtZip() {
		return addrJtZip;
	}

	public void setAddrJtZip(String addrJtZip) {
		this.addrJtZip = addrJtZip;
	}

	public String getContAddrHj() {
		return contAddrHj;
	}

	public void setContAddrHj(String contAddrHj) {
		this.contAddrHj = contAddrHj;
	}

	public String getAddrHjZip() {
		return addrHjZip;
	}

	public void setAddrHjZip(String addrHjZip) {
		this.addrHjZip = addrHjZip;
	}

	public String getContAddrFc() {
		return contAddrFc;
	}

	public void setContAddrFc(String contAddrFc) {
		this.contAddrFc = contAddrFc;
	}

	public String getAddrFcZip() {
		return addrFcZip;
	}

	public void setAddrFcZip(String addrFcZip) {
		this.addrFcZip = addrFcZip;
	}

	public String getContAddrGs() {
		return contAddrGs;
	}

	public void setContAddrGs(String contAddrGs) {
		this.contAddrGs = contAddrGs;
	}

	public String getAddrGsZip() {
		return addrGsZip;
	}

	public void setAddrGsZip(String addrGsZip) {
		this.addrGsZip = addrGsZip;
	}

}
