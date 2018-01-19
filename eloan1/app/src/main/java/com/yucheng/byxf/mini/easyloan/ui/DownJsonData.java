package com.yucheng.byxf.mini.easyloan.ui;

import java.util.List;
import org.apache.http.NameValuePair;

import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.byxf.comm.ScreenManager;
import com.yucheng.byxf.mini.ui.MyLoginActivity;
import com.yucheng.byxf.mini.ui.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.Toast;

public class DownJsonData {
	private Activity context = null;
	private String retJson = null;
	private OnLoadJsonDataListener dataListener;
	private Boolean canShow = true;
	private static View view;
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (canShow) {
				dismissProgressDialog();
			}
			switch (msg.what) {
			case 1:
				if (retJson == null && checkNetwork()) {
					Toast.makeText(context, "后台数据有问题，请联系服务器客服！",
							Toast.LENGTH_SHORT).show();
					return;
				}
				if (retJson == null) {
					Toast.makeText(context, "后台数据有问题，请联系服务器客服！",
							Toast.LENGTH_SHORT).show();
					return;
				}
				String subRetJson = retJson
						.substring(retJson.indexOf("code") + 6);
				subRetJson = subRetJson.substring(0, subRetJson.indexOf(","));
				if (subRetJson.contains("403")) {
					Toast.makeText(context, "登录超时，请重新登录！", Toast.LENGTH_SHORT)
							.show();
					ScreenManager.getScreenManager()
							.clearHistorynavigationFrom(
									MyLoginActivity.class.getSimpleName(),
									false);
					Intent loginIntent = new Intent(context,
							MyLoginActivity.class);
					context.startActivity(loginIntent);
					return;
				}
				dataListener.onFinished(retJson);
				break;
			default:
				break;
			}
		}
	};

	private boolean createView() {
		boolean flag = false;
		WindowManager wm = (WindowManager) context.getSystemService("window");
		WindowManager.LayoutParams wmParams = new LayoutParams();
		view = LayoutInflater.from(context).inflate(
				R.layout.common_progress_layout, null);
		ImageView imageView = (ImageView) view
				.findViewById(R.id.comm_progress_iv_anim);// 加载布局
		// // 加载动画
		AnimationDrawable animation = (AnimationDrawable) imageView
				.getBackground();
		// 使用ImageView显示动画
		animation.start();
		/**
		 * 设置WindowManager.LayoutParams的相关属性，具体的用途参考SDK文档
		 */
		wmParams.type = 2002;
		wmParams.format = 1;
		wmParams.flags = 40;
		DisplayMetrics metrics = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		int wight = metrics.widthPixels;
		int height = metrics.heightPixels;
		float density = metrics.density;
		// 屏幕的分辨率等于（360,640）与density的乘积
		wmParams.width = (int) (wight + 0.5f);
		wmParams.height = (int) (height + 0.5f);
		wm.addView(view, wmParams);
		flag = true;
		return flag;
	}

	protected boolean showProgressDialog() {
		boolean isSuccess = false;
		isSuccess = createView();
		return isSuccess;
	}

	protected void dismissProgressDialog() {
		closeView();
	}

	private boolean closeView() {
		if (view != null) {
			view.setVisibility(View.GONE);
			view = null;
		}
		return true;
	}

	public void showDownloadStatus(Boolean isShow) {
		canShow = isShow;
	}

	public void cancelDownload() {
		if (canShow) {
			dismissProgressDialog();
		}
	}

	public OnLoadJsonDataListener getDataListener() {
		return dataListener;
	}

	public void setDataListener(OnLoadJsonDataListener dataListener) {
		this.dataListener = dataListener;
	}

	public void jsonDataDownStart(Activity cxt, final String url,
			final List<NameValuePair> params) {
		if (cxt == null) {
			return;
		}
		this.context = null;
		this.context = cxt;
		if (url == null || "".equals(url)) {
			Toast.makeText(context, "URL为空！", Toast.LENGTH_SHORT).show();
			return;
		}
		if (!checkNetwork()) {
			Toast.makeText(context, "没有网络，请检查网络设置！", Toast.LENGTH_SHORT).show();
		}
		if (canShow) {
			showProgressDialog();
		}
    new Thread(new Runnable() {
      @Override
      public void run() {
        // TODO Auto-generated method stub
        Looper.prepare();
        HttpHelper httpHelper = HttpHelper.getInstance(context);
        retJson = httpHelper.post(url, params);
        Message msg = new Message();
        msg.what = 1;
        handler.sendMessage(msg);
        Looper.loop();
      }
    }).start();
	}

	private boolean checkNetwork() {
		ConnectivityManager mConnectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = mConnectivity.getActiveNetworkInfo();
		if (info != null) {
			return info.isAvailable();
		}
		return false;
	}
}