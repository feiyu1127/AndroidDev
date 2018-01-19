package com.yucheng.byxf.mini.ui;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;

import com.yucheng.apfx.soc.OnekeyShare;
import com.yucheng.apfx.soc.ShareRes;
import com.yucheng.byxf.mini.soc.SocShare;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.utils.UIHandler;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.tencent.weibo.TencentWeibo;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;
import android.R.color;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ShareActivity2 extends BaseAllActivity implements PlatformActionListener,
		Callback {
	private boolean shareFromQQLogin = false;
	private PlatformActionListener platformActionListener;
	private ShareParams shareParams;
	private ImageView back;
	private GridView share_list;
	private MyAdapter adapter;
	private static String[] shareNames = { "微信朋友圈", "微信好友", "QQ空间", "QQ好友",
			"新浪微博", "腾讯微博" };
	private static int[] shareIcons = { R.drawable.logo_wechatmoments,
			R.drawable.logo_wechat, R.drawable.logo_qzone, R.drawable.logo_qq,
			R.drawable.logo_sinaweibo, R.drawable.logo_tencentweibo };

	// public ShareActivity2(Context ctx) {
	// this.ctx = ctx;
	// new Thread() {
	// public void run() {
	// Platform[] list = ShareSDK.getPlatformList();
	// if (list != null) {
	// Message msg = new Message();
	// msg.obj = list;
	// UIHandler.sendMessage(msg, new Callback() {
	// public boolean handleMessage(Message msg) {
	// afterPlatformsGot((Platform[]) msg.obj);
	// return false;
	// }
	// });
	// }
	// }
	// }.start();
	// }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.share_layout);
		ShareSDK.initSDK(this);
		share_list = (GridView) findViewById(R.id.gv_share_gridview);
		share_list.setSelector(color.transparent);
		back = (ImageView) findViewById(R.id.back);
		if (adapter == null) {
			adapter = new MyAdapter(ShareActivity2.this);
		}
		share_list.setAdapter(adapter);
		// model = new ShareInfo();
		initShareImage();
		initShareText();

		share_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				switch (position) {
				case 0:
					showShare("WechatMoments");
					break;
				case 1:
					// showShare("Wechat");
					ShareParams sp = new ShareParams();
					sp.setTitle(ShareActivity2.this
							.getString(R.string.evenote_title));
					if (ShareActivity2.SHARE_TEXT != null
							&& ShareActivity2.SHARE_TEXT.containsKey(0)) {
						sp.setText(ShareActivity2.SHARE_TEXT.get(0));
					} else {
						sp.setText(ShareActivity2.this
								.getString(R.string.share_content));
					}
					sp.setShareType(Platform.SHARE_TEXT);
					sp.setShareType(Platform.SHARE_WEBPAGE);
					sp.setImagePath(ShareActivity2.SHARE_IMAGE_LFN);
					sp.setUrl("https://etp.bobcfc.com:7788/eloan-services/v1/anon/app");
					Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
					if (platformActionListener != null) {
						wechat.setPlatformActionListener(platformActionListener);
					}
					wechat.share(sp);
					break;
				case 2:
					showShare("Qzone");
					break;
				case 3:
					showShare("QQ");
					break;
				case 4:
					showShare("SinaWeibo");
					break;
				case 5:
					showShare("TencentWeibo");
					// ShareParams sp = new ShareParams();
					// if (ShareActivity2.SHARE_TEXT != null
					// && ShareActivity2.SHARE_TEXT.containsKey(0)) {
					// sp.setText(ShareActivity2.SHARE_TEXT.get(0));
					// } else {
					// sp.setText(ShareActivity2.this
					// .getString(R.string.share_content));
					// }
					// sp.setImagePath(ShareActivity2.SHARE_IMAGE_LFN);
					// Platform weibo =
					// ShareSDK.getPlatform(ShareActivity2.this,TencentWeibo.NAME);
					// if(TencentWeibo.NAME!=null){
					// Log.e("TAG","jar包不等于空");
					// }
					// weibo.setPlatformActionListener(platformActionListener);
					// // 设置分享事件回调
					// // 执行图文分享
					// weibo.share(sp);
					break;
				}
			}
		});

		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	public void showShare(String platform) {

		ShareParams sp = new ShareParams();
		if (platform.equals("WechatMoments")) {
			// sp.setTitle(ShareActivity2.this.getString(R.string.evenote_title));
			// sp.setTitleUrl("https://etp.bobcfc.com:7788/eloan-services/v1/anon/app");
			// sp.setImageUrl(ShareActivity2.SHARE_IMAGE_URL);
			if (ShareActivity2.SHARE_TEXT != null
					&& ShareActivity2.SHARE_TEXT.containsKey(0)) {
				sp.setText(ShareActivity2.SHARE_TEXT.get(0));
			} else {
				sp.setText(ShareActivity2.this
						.getString(R.string.share_content));
			}
			sp.setImagePath(ShareActivity2.SHARE_IMAGE_LFN);
			// sp.setUrl("https://etp.bobcfc.com:7788/eloan-services/v1/anon/app");
			// sp.setFilePath(ShareActivity2.SHARE_IMAGE_LFN);
			Platform wechatMoments = ShareSDK.getPlatform(WechatMoments.NAME);
			if (platformActionListener != null) {
				wechatMoments.setPlatformActionListener(platformActionListener);
			}
			wechatMoments.share(sp);
		} else if ("Wechat".equals(platform)) {
			sp = new ShareParams();
			sp.setTitle(ShareActivity2.this.getString(R.string.evenote_title));
			// sp.setTitleUrl("https://etp.bobcfc.com:7788/eloan-services/v1/anon/app");
			sp.setImageUrl(ShareActivity2.SHARE_IMAGE_URL);
			if (ShareActivity2.SHARE_TEXT != null
					&& ShareActivity2.SHARE_TEXT.containsKey(0)) {
				sp.setText(ShareActivity2.SHARE_TEXT.get(0));
			} else {
				sp.setText(ShareActivity2.this
						.getString(R.string.share_content));
			}
			sp.setImagePath(ShareActivity2.SHARE_IMAGE_LFN);
			// sp.setUrl("https://etp.bobcfc.com:7788/eloan-services/v1/anon/app");
			// sp.setFilePath(ShareActivity2.SHARE_IMAGE_LFN);
			sp.setShareType(Platform.SHARE_WEBPAGE);
			Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
			if (platformActionListener != null) {
				wechat.setPlatformActionListener(platformActionListener);
			}
			wechat.share(sp);
		} else if ("Qzone".equals(platform)) {
			sp = new ShareParams();
			sp.setTitle(ShareActivity2.this.getString(R.string.evenote_title));
			sp.setTitleUrl("https://etp.bobcfc.com:7788/eloan-services/v1/anon/app"); // 标题的超链接
			if (ShareActivity2.SHARE_TEXT != null
					&& ShareActivity2.SHARE_TEXT.containsKey(0)) {
				sp.setText(ShareActivity2.SHARE_TEXT.get(0));
			} else {
				sp.setText(ShareActivity2.this
						.getString(R.string.share_content));
			}
			sp.setImagePath(ShareActivity2.SHARE_IMAGE_LFN);
			sp.setSite(ShareActivity2.this.getString(R.string.app_name));
			sp.setSiteUrl("https://etp.bobcfc.com:7788/eloan-services/v1/anon/app");

			Platform qzone = ShareSDK.getPlatform(QZone.NAME);
			qzone.setPlatformActionListener(platformActionListener); // 设置分享事件回调
			// 执行图文分享
			qzone.share(sp);
		} else if ("QQ".equals(platform)) {
			sp = new ShareParams();
			sp.setTitle(ShareActivity2.this.getString(R.string.evenote_title));
			sp.setTitleUrl("https://etp.bobcfc.com:7788/eloan-services/v1/anon/app");

			sp.setImagePath(ShareActivity2.SHARE_IMAGE_LFN);
			sp.setImageUrl(ShareActivity2.SHARE_IMAGE_URL);
			if (ShareActivity2.SHARE_TEXT != null
					&& ShareActivity2.SHARE_TEXT.containsKey(0)) {
				sp.setText(ShareActivity2.SHARE_TEXT.get(0));
			} else {
				sp.setText(ShareActivity2.this
						.getString(R.string.share_content));
			}
			Platform qq = ShareSDK.getPlatform(QQ.NAME);
			if (platformActionListener != null) {
				qq.setPlatformActionListener(platformActionListener);
			}
			qq.share(sp);
		} else if ("SinaWeibo".equals(platform)) {
			sp = new ShareParams();
			if (ShareActivity2.SHARE_TEXT != null
					&& ShareActivity2.SHARE_TEXT.containsKey(0)) {
				sp.setText(ShareActivity2.SHARE_TEXT.get(0));
			} else {
				sp.setText(ShareActivity2.this
						.getString(R.string.share_content));
			}
			sp.setImagePath(ShareActivity2.SHARE_IMAGE_LFN);
			sp.setLatitude(23.056081f);
			sp.setLongitude(113.385708f);
			Platform sinaWeibo = ShareSDK.getPlatform(SinaWeibo.NAME);
			if (platformActionListener != null) {
				sinaWeibo.setPlatformActionListener(platformActionListener);
			}
			sinaWeibo.share(sp);
		} else if ("TencentWeibo".equals(platform)) {
			sp = new ShareParams();
			if (ShareActivity2.SHARE_TEXT != null
					&& ShareActivity2.SHARE_TEXT.containsKey(0)) {
				sp.setText(ShareActivity2.SHARE_TEXT.get(0));
			} else {
				sp.setText(ShareActivity2.this
						.getString(R.string.share_content));
			}
			sp.setImagePath(ShareActivity2.SHARE_IMAGE_LFN);
			sp.setLatitude(23.056081f);
			sp.setLongitude(113.385708f);
			Platform tencentWeibo = ShareSDK.getPlatform(ShareActivity2.this,
					TencentWeibo.NAME);
			if (TencentWeibo.NAME != null) {
				Log.e("TAG", "jar包不等于空");
			}
			tencentWeibo.setPlatformActionListener(platformActionListener); // 设置分享事件回调
			// 执行图文分享
			tencentWeibo.share(sp);
		}
		// sp.setNotification(R.drawable.ic_launcher,
		// getContext().getString(R.string.app_name));
		// sp.setTitle(getContext().getString(R.string.evenote_title));
		// sp.setTitleUrl("https://etp.bobcfc.com:7788/eloan-services/v1/anon/app");
		//
		// sp.setImagePath(ShareActivity2.SHARE_IMAGE_LFN);
		// sp.setImageUrl(ShareActivity2.SHARE_IMAGE_URL);
		//
		// if (ShareActivity2.SHARE_TEXT != null
		// && ShareActivity2.SHARE_TEXT.containsKey(0)) {
		// sp.setText(ShareActivity2.SHARE_TEXT.get(0));
		// } else {
		// sp.setText(getContext().getString(R.string.share_content));
		// }
		// // oks.setUrl("https://etp.bobcfc.com/share");
		// sp.setUrl("https://etp.bobcfc.com:7788/eloan-services/v1/anon/app");
		// sp.setFilePath(ShareActivity2.SHARE_IMAGE_LFN);
		// sp.setComment(getContext().getString(R.string.share));
		// sp.setSite(getContext().getString(R.string.app_name));
		// // oks.setSiteUrl("https://etp.bobcfc.com/");
		// sp.setSiteUrl("https://etp.bobcfc.com:7788/eloan-services/v1/anon/app");
		// sp.setVenueName("轻松e贷");
		// sp.setVenueDescription("轻松e贷");
		// sp.setLatitude(23.056081f);
		// sp.setLongitude(113.385708f);
		// sp.setShareFromQQAuthSupport(false);
		// 指定分享平台，和slient一起使用可以直接分享到指定的平台
		// if (null != platform) {
		// oks.setPlatform(platform);
		// }
		// // 是否直接分享（true则直接分享）
		// oks.setSilent(silent);
		// oks.setDialogMode();
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub

		if (ev.getAction() == MotionEvent.ACTION_MOVE) {
			return true;
		}
		return super.dispatchTouchEvent(ev);
	}

	public void initShareImage() {
		try {

			SHARE_IMAGE_LFN = ShareRes.getCachePath(ShareActivity2.this, null)
					+ SHARE_IMAGE_SFN;
			File file = new File(SHARE_IMAGE_LFN);
			file.delete();
			if (!file.exists()) {
				file.createNewFile();
				FileOutputStream fos = new FileOutputStream(file);
				// my_ic_launcher ic的图片！icon-75x75.pngicshare2
				BitmapFactory
						.decodeResource(ShareActivity2.this.getResources(),
								R.drawable.icshare2).compress(
								Bitmap.CompressFormat.JPEG, 100, fos);
				fos.flush();
				fos.close();
			}
		} catch (Throwable t) {
			t.printStackTrace();
			SHARE_IMAGE_LFN = null;
		}
	}

	public void initShareImage2() {
		try {

			SHARE_IMAGE_LFN2 = ShareRes.getCachePath(getContext(), null)
					+ SHARE_IMAGE_SFN2;
			File file = new File(SHARE_IMAGE_LFN2);
			file.delete();
			if (!file.exists()) {
				file.createNewFile();
				FileOutputStream fos = new FileOutputStream(file);
				// my_ic_launcher ic的图片！icon-75x75.png
				BitmapFactory.decodeResource(getContext().getResources(),
						R.drawable.gift_icon).compress(
						Bitmap.CompressFormat.JPEG, 100, fos);
				fos.flush();
				fos.close();
			}
		} catch (Throwable t) {
			t.printStackTrace();
			SHARE_IMAGE_LFN2 = null;
		}
	}

	public void initShareText() {
		try {
			SHARE_TEXT = new HashMap<Integer, String>();
			SHARE_TEXT.put(0,
					ShareActivity2.this.getString(R.string.share_content));
		} catch (Throwable t) {
			t.printStackTrace();
		}
		Log.i("SHARE_TEXT=>>>", SHARE_TEXT.toString());
	}

	public void initShareText2() {
		try {
			SHARE_TEXT = new HashMap<Integer, String>();
			SHARE_TEXT.put(0, "北银消费轻松e贷");
		} catch (Throwable t) {
			t.printStackTrace();
		}
		Log.i("SHARE_TEXT=>>>", SHARE_TEXT.toString());
	}

	class MyAdapter extends BaseAdapter {

		private LayoutInflater inflater;

		public MyAdapter(Context context) {
			inflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return shareNames.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.share_item, null);
			}
			ImageView shareIcon = (ImageView) convertView
					.findViewById(R.id.share_icon);
			TextView shareTitle = (TextView) convertView
					.findViewById(R.id.share_title);
			shareIcon.setImageResource(shareIcons[position]);
			shareTitle.setText(shareNames[position]);
			return convertView;
		}

	}

	/**
	 * 获取平台
	 * 
	 * @param position
	 * @return
	 */
	/*
	 * private String getPlatform(int position) { String platform = ""; switch
	 * (position) { case 0: platform = "WechatMoments"; break; case 1: platform
	 * = "Wechat"; break; case 2: platform = "QZone"; break; case 3: platform =
	 * "QQ"; break; case 4: platform = "SinaWeibo"; break; case 5: platform =
	 * "TencentSinaWeibo"; break; } return platform; }
	 */

	public void onComplete(Platform plat, int action,
			HashMap<String, Object> res) {

		Message msg = new Message();
		msg.arg1 = 1;
		msg.arg2 = action;
		msg.obj = plat;
		UIHandler.sendMessage(msg, this);
	}

	public void onCancel(Platform palt, int action) {
		Message msg = new Message();
		msg.arg1 = 3;
		msg.arg2 = action;
		msg.obj = palt;
		UIHandler.sendMessage(msg, this);
	}

	public void onError(Platform palt, int action, Throwable t) {
		t.printStackTrace();

		Message msg = new Message();
		msg.arg1 = 2;
		msg.arg2 = action;
		msg.obj = palt;
		UIHandler.sendMessage(msg, this);
	}

	/*
	 * private void qzone() { ShareParams sp = new ShareParams();
	 * sp.setTitle(getContext().getString(R.string.evenote_title));
	 * sp.setTitleUrl(shareParams.getUrl()); // 标题的超链接
	 * sp.setText(shareParams.getText());
	 * sp.setImageUrl(shareParams.getImageUrl());
	 * sp.setComment(getContext().getString(R.string.share));
	 * sp.setSite(getContext().getString(R.string.app_name));
	 * sp.setSiteUrl("https://etp.bobcfc.com:7788/eloan-services/v1/anon/app");
	 * 
	 * Platform qzone = ShareSDK.getPlatform(ShareActivity2.this, "QZone");
	 * 
	 * qzone.setPlatformActionListener(platformActionListener); // 设置分享事件回调 //
	 * // 执行图文分享 qzone.share(sp); }
	 */

	public boolean handleMessage(Message msg) {
		Platform plat = (Platform) msg.obj;
		String text = ShareActivity2.actionToString(msg.arg2);
		switch (msg.arg1) {
		case 1: {
			// 成功
			text = plat.getName() + " completed at " + text;
		}
			break;
		case 2: {
			// 失败
			text = plat.getName() + " caught error at " + text;
		}
			break;
		case 3: {
			// 取消
			text = plat.getName() + " canceled at " + text;
		}
			break;
		}

		Toast.makeText(ShareActivity2.this, text, Toast.LENGTH_SHORT).show();
		return false;
	}

	public static String actionToString(int action) {
		switch (action) {
		case Platform.ACTION_AUTHORIZING:
			return "ACTION_AUTHORIZING";
		case Platform.ACTION_GETTING_FRIEND_LIST:
			return "ACTION_GETTING_FRIEND_LIST";
		case Platform.ACTION_FOLLOWING_USER:
			return "ACTION_FOLLOWING_USER";
		case Platform.ACTION_SENDING_DIRECT_MESSAGE:
			return "ACTION_SENDING_DIRECT_MESSAGE";
		case Platform.ACTION_TIMELINE:
			return "ACTION_TIMELINE";
		case Platform.ACTION_USER_INFOR:
			return "ACTION_USER_INFOR";
		case Platform.ACTION_SHARE:
			return "ACTION_SHARE";
		default: {
			return "UNKNOWN";
		}
		}
	}

	private Context getContext() {
		return ctx;
	}

	private static final String SHARE_IMAGE_SFN = "icshare2.jpg";
	private static final String SHARE_IMAGE_SFN2 = "gift_icon.jpg";
	private static String SHARE_IMAGE_LFN;
	private static String SHARE_IMAGE_LFN2;
	private static String SHARE_IMAGE_URL;
	private static HashMap<Integer, String> SHARE_TEXT;
	private Context ctx;

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ShareSDK.stopSDK(this);
	}
}
