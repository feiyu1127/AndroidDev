package com.yucheng.byxf.mini.adapter;

import imageUtil.SmartImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.byxf.mini.adapter.AdanceRepaymentAdapter.ViewHolder;
import com.yucheng.byxf.mini.message.ActivityNotice;
import com.yucheng.byxf.mini.message.SystemNotice;
import com.yucheng.byxf.mini.ui.HomeActivity;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.DownLoaderBitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SystemMsgAdapter extends BaseAdapter {

	private List<SystemNotice> list;
	private Context context;
	private Bitmap bitmap = null;
	private URL imageUrl = null;

	public SystemMsgAdapter(List<SystemNotice> list, Context context) {
		super();
		this.list = list;
		this.context = context;
	}

	@Override
	public int getCount() {
		return list!=null?list.size():0;
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.system_messange_item, null);
			holder.title = (TextView) convertView.findViewById(R.id.title);
			holder.createTime = (TextView) convertView
					.findViewById(R.id.createTime);
			holder.sysMsgDetial = (TextView) convertView
					.findViewById(R.id.sysMsgDetial);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.title.setText(IsNull(list.get(position).getTitle()));
		holder.createTime.setText(IsNull(list.get(position).getCreateTime()));
		String detail = list.get(position).getDetail();
		if(IsNull(detail).length() >= 33){
			holder.sysMsgDetial.setText(detail.subSequence(0, 33)+"...");
		}else{
			holder.sysMsgDetial.setText(IsNull(detail));
		}

//		try {
//			Date datenow = new Date();
//			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//			String timenow = format.format(datenow);
//			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//			Date dtnow = df.parse(timenow);
//			String dateget = list.get(position).getCreateTime();
//			Date dtget = df.parse(dateget);
//			if (dtnow.getTime() == dtget.getTime()) {
//				String dateshow0 = dateget.substring(11);
//				String dateshow = dateshow0.substring(0, 5);
//				holder.createTime.setText(dateshow);
//			} else {
//				String dateold = dateget.substring(5, 10);
//				holder.createTime.setText(dateold);
//			}
//
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		return convertView;
	}

	class ViewHolder {
		TextView title;
		TextView createTime;
		TextView sysMsgDetial;
	}

	/**
	 * 判断数据是否为null，为null转换成"",否则正常
	 * @param str
	 * @return
	 */
	private String IsNull(String str){
		return str != null?str : "";
	}
}
