package com.yucheng.byxf.mini.xiaojinying;

import java.io.Serializable;
import java.util.List;

import com.baidu.mapapi.map.Text;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

public class XiaoJinYingZhangDanBodyList implements Serializable{
	

	
	private static final long serialVersionUID = 1L;
	// 明细名称
			private TextView mingxiname;
			// 交易时间
			private TextView shijian;
			// 明细金额
			private TextView renminbinum;
			// 详情按钮
			private TextView xiangqing;
			// 明细名称——
			private TextView xiangqing_name;
			// 交易时间——
			private TextView jiaoyiriqidata;
			// 交易类型
			private TextView jiaoyileixingtype;
			// 记账日期
			private TextView jizhangriqidata;
			// 交易金额——
			private TextView jiaoyijinenum;
			public TextView getMingxiname() {
				return mingxiname;
			}
			public void setMingxiname(TextView mingxiname) {
				this.mingxiname = mingxiname;
			}
			public TextView getShijian() {
				return shijian;
			}
			public void setShijian(TextView shijian) {
				this.shijian = shijian;
			}
			public TextView getRenminbinum() {
				return renminbinum;
			}
			public void setRenminbinum(TextView renminbinum) {
				this.renminbinum = renminbinum;
			}
			public TextView getXiangqing() {
				return xiangqing;
			}
			public void setXiangqing(TextView xiangqing) {
				this.xiangqing = xiangqing;
			}
			public TextView getXiangqing_name() {
				return xiangqing_name;
			}
			public void setXiangqing_name(TextView xiangqing_name) {
				this.xiangqing_name = xiangqing_name;
			}
			public TextView getJiaoyiriqidata() {
				return jiaoyiriqidata;
			}
			public void setJiaoyiriqidata(TextView jiaoyiriqidata) {
				this.jiaoyiriqidata = jiaoyiriqidata;
			}
			public TextView getJiaoyileixingtype() {
				return jiaoyileixingtype;
			}
			public void setJiaoyileixingtype(TextView jiaoyileixingtype) {
				this.jiaoyileixingtype = jiaoyileixingtype;
			}
			public TextView getJizhangriqidata() {
				return jizhangriqidata;
			}
			public void setJizhangriqidata(TextView jizhangriqidata) {
				this.jizhangriqidata = jizhangriqidata;
			}
			public TextView getJiaoyijinenum() {
				return jiaoyijinenum;
			}
			public void setJiaoyijinenum(TextView jiaoyijinenum) {
				this.jiaoyijinenum = jiaoyijinenum;
			}
			public static long getSerialversionuid() {
				return serialVersionUID;
			}

	
}
