package com.yucheng.byxf.mini.map;

import java.io.Serializable;

public class BranchLocation implements Serializable{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private Long branId;
		private String name;
		private String addr;
		private String phone;
		private String longitude;
		private String latitude;
		
		public Long getBranId() {
			return branId;
		}
		public void setBranId(Long branId) {
			this.branId = branId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAddr() {
			return addr;
		}
		public void setAddr(String addr) {
			this.addr = addr;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getLongitude() {
			return longitude;
		}
		public void setLongitude(String longitude) {
			this.longitude = longitude;
		}
		public String getLatitude() {
			return latitude;
		}
		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}
	}