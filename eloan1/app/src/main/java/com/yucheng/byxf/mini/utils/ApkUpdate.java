package com.yucheng.byxf.mini.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.NameValuePair;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.apfx.net.Ssl;
import com.yucheng.byxf.comm.ScreenManager;
import com.yucheng.byxf.mini.message.PersonalApk;
import com.yucheng.byxf.mini.message.PersonalApkResponse;
import com.yucheng.byxf.mini.message.Response;
import com.yucheng.byxf.mini.service.UpdateService;
import com.yucheng.byxf.mini.ui.MyLoginActivity;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.msg.apk.Apk;
import com.yucheng.byxf.util.LogManager;

public class ApkUpdate {
	// 显示当前下载进度
	private final int DOWN_UPDATE = 0;
	// 下载完成
	private final int DOWNLOAD_FINISH = 1;
	// 下载缓冲
	private ProgressBar pBar;
	// private Dialog mDownloadDialog;
	// 下载的缓冲值
	private boolean interceptFlag = true;
	private int progressNum = 0;
	// private String apkUrl;
	// private String savePath;
	private String apkName;
	private Context mContext;// 上下文对象
	private String apkUrl;// 下载的url
	private String savePath = "/mnt/sdcard/bobcfc/";// 文件保存的路径
	private String saveApkName = "byxf_phone.apk";// 文件保存的名称
	private Dialog dialog;
	private PersonalApkResponse apk;
	private String isForceUpdate;

	private Handler handler = new Handler(new Handler.Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			switch (msg.what) {
			case DOWN_UPDATE:
				pBar.setProgress(progressNum);
				break;
			case DOWNLOAD_FINISH:
				dialog.dismiss();
				installMethod(mContext, apkName);
				ScreenManager screenManager = ScreenManager
						.getScreenManager();
				screenManager.popAllActivityExceptOne();
				break;

			default:
				break;
			}
			return false;
		}

	});

	public ApkUpdate(Context mContext) {
		super();
		this.mContext = mContext;
	}

	/**
	 * 检查设备版本
	 * 
	 * @param context
	 */
	@SuppressLint("NewApi")
	public void checkNewVersion(Context context,PersonalApkResponse apk) {
		String oldVersionName = getVerName(context);
//		apk = getVersionOnServer();
		System.out.println(apk.getResult().getUrl());
		ApkData.appName = apk.getResult().getName();
		ApkData.appDt = apk.getResult().getDt();
		ApkData.appMsg = apk.getMsg();
		if (apk != null && apk.getCode() == 0) {
			isForceUpdate = apk.getResult().getIsForceUpdate();
			System.out.println("Apk=" + apk.toString());
			apkUrl = apk.getResult().getUrl();
			if (!Apk.isGreater(apk.getResult().getVer(), oldVersionName)) {
				StringBuffer sb = new StringBuffer();
				sb.append("已是最新版本");
				dialog = DialogUtil.showDialogOneButton2(mContext, sb.toString());
			} else if (Apk.isGreater(apk.getResult().getVer(), oldVersionName)
					&& !"Y".equals(apk.getResult().getIsForceUpdate())) {
				StringBuffer sb = new StringBuffer();
				sb.append("发现新版本，是否更新？");
				OnClickListener posListener = new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						dialog.cancel();
//						download(); 新后台线程下载
						updateClient(mContext,apkUrl);
					}
				};
				dialog = DialogUtil.showDialog_apk(mContext, sb.toString(),
						posListener);
			} else if (Apk.isGreater(apk.getResult().getVer(), oldVersionName)
					&& !"Y".equals(apk.getResult().getIsForceUpdate())) {
				StringBuffer sb = new StringBuffer();
				sb.append("发现新版本，是否更新？");
				OnClickListener posListener = new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						dialog.cancel();
//						download();
						updateClient(mContext,apkUrl);
					}
				};
				dialog = DialogUtil.showDialog_apk(mContext, sb.toString(),
						posListener);
			} else if (Apk.isGreater(apk.getResult().getVer(), oldVersionName)
					&& "Y".equals(apk.getResult().getIsForceUpdate())) {
				StringBuffer sb = new StringBuffer();
				sb.append("发现新版本，请更新!");

				OnClickListener posListener = new OnClickListener() {
					@Override
					public void onClick(View arg0) {
//						dialog.cancel();
//						download();
						Toast.makeText(mContext, "已开始后台下载，请耐心等待。。。", 4000).show();
						updateClient(mContext,apkUrl);
					}
				};
//				dialog = DialogUtil.showDialogOneButton(mContext, sb.toString(),
//						posListener);
				dialog = DialogUtil.showDialogOneButton_shengjiapk(mContext, sb.toString(),
					posListener);
			}
		} else {
			// DialogUtil.createDialog(context, 1, "网络连接异常，请检查网络");
		}
	}
	
	public void downLoadApk(Context context){
		String oldVersionName = getVerName(context);
		if (Apk.isGreater(apk.getResult().getVer(), oldVersionName)
				&& !"Y".equals(apk.getResult().getIsForceUpdate())) {
			StringBuffer sb = new StringBuffer();
			sb.append("发现新版本，是否更新？");
			OnClickListener posListener = new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					dialog.cancel();
					download();
				}
			};
			dialog = DialogUtil.showDialog_apk(mContext, sb.toString(),
					posListener);
		} else if (Apk.isGreater(apk.getResult().getVer(), oldVersionName)
				&& "Y".equals(apk.getResult().getIsForceUpdate())) {
			StringBuffer sb = new StringBuffer();
			sb.append("发现新版本，请更新？");

			OnClickListener posListener = new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					dialog.cancel();
					download();
				}
			};
			dialog = DialogUtil.showDialog_apk(mContext, sb.toString(),
					posListener);
		}
	}
	
	

	/**
	 * 检查设备版本
	 * 
	 * @param context
	 */
	@SuppressLint("NewApi")
	public int checkNewVersionAndTap(Context context) {
		int isUpdata = 0;
		String oldVersionName = getVerName(context);
		apk = getVersionOnServer();
		
		if (apk != null && apk.getCode() == 0) {
			ApkData.appName = apk.getResult().getName();
			ApkData.appDt = apk.getResult().getDt();
			ApkData.appMsg = apk.getMsg();
			isForceUpdate = apk.getResult().getIsForceUpdate();
			System.out.println("Apk=" + apk.toString());
			apkUrl = apk.getResult().getUrl();
			
			if (!Apk.isGreater(apk.getResult().getVer(), oldVersionName)) {
//				StringBuffer sb = new StringBuffer();
//				sb.append("已是最新版本");
//				dialog = DialogUtil.showDialogOneButton2(mContext, sb.toString());
				return 0;
			} else if (Apk.isGreater(apk.getResult().getVer(), oldVersionName)
					&& !"Y".equals(apk.getResult().getIsForceUpdate())) {
//				StringBuffer sb = new StringBuffer();
//				sb.append("发现新版本，是否更新？");
//				OnClickListener posListener = new OnClickListener() {
//					@Override
//					public void onClick(View arg0) {
//						dialog.cancel();
//						download();
//					}
//				};
//				dialog = DialogUtil.showDialog(mContext, sb.toString(),
//						posListener);
				return 1;
			} else if (Apk.isGreater(apk.getResult().getVer(), oldVersionName)
					&& !"Y".equals(apk.getResult().getIsForceUpdate())) {
//				StringBuffer sb = new StringBuffer();
//				sb.append("发现新版本，是否更新？");
//				OnClickListener posListener = new OnClickListener() {
//					@Override
//					public void onClick(View arg0) {
//						dialog.cancel();
//						download();
//					}
//				};
//				dialog = DialogUtil.showDialog(mContext, sb.toString(),
//						posListener);
				return 1;
			} else if (Apk.isGreater(apk.getResult().getVer(), oldVersionName)
					&& "Y".equals(apk.getResult().getIsForceUpdate())) {
//				StringBuffer sb = new StringBuffer();
//				sb.append("发现新版本，请更新");
//
//				OnClickListener posListener = new OnClickListener() {
//					@Override
//					public void onClick(View arg0) {
//						dialog.cancel();
//						download();
//					}
//				};
//				dialog = DialogUtil.showDialogOneButton(mContext, sb.toString(),
//						posListener);
				return 2;
			}
			
//			if (!Apk.isGreater(apk.getResult().getVer(), oldVersionName)) {
//				return false;
//			} else {
//				return true;
//			}
//			
		} else {
			// DialogUtil.createDialog(context, 1, "网络连接异常，请检查网络");
		}
		return 0;
	}

	// 获取版本名
	public static String getVerName(Context context) {
		String verName = "";
		try {
			verName = context.getPackageManager().getPackageInfo(
					"com.yucheng.byxf.mini.ui", 0).versionName;
		} catch (NameNotFoundException e) {
			LogManager.i(MyLoginActivity.class, e.getMessage());
		}
		return verName;
	}

	/**
	 * 获取版本信息
	 * 
	 * @author danny
	 * 
	 */
  public PersonalApkResponse getVersionOnServer() {
    HttpHelper httpHelper = HttpHelper.getInstance(mContext);
    List<NameValuePair> arg = new ArrayList<NameValuePair>();
    return httpHelper.post(ContantsAddress.PERSONAL_APK, arg, PersonalApkResponse.class);
  }
  
  public void updateClient(Context context,String up_url) {
	  
		if (up_url == null || "".equals(up_url)) {
			Toast.makeText(context, "下载地址为空，请联系客服！", Toast.LENGTH_SHORT).show();
		} else {
			Intent updateIntent = new Intent(context, UpdateService.class);
			updateIntent.putExtra("url", up_url);
			isServiceRunning(context,
					"com.yucheng.byxf.mini.service.UpdateService.class",
					updateIntent);
			context.startService(updateIntent);
		}
	} 
  
  /**
	 * 用来判断服务是否运行.
	 * 
	 * @param context
	 * @param className
	 *            判断的服务名字
	 * @return true 在运行 false 不在运行
	 */
	public static boolean isServiceRunning(Context mContext, String className,
			Intent intent) {
		boolean isRunning = false;
		ActivityManager activityManager = (ActivityManager) mContext
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningServiceInfo> serviceList = activityManager
				.getRunningServices(30);
		if (!(serviceList.size() > 0)) {
			return false;
		}
		for (int i = 0; i < serviceList.size(); i++) {
			if (serviceList.get(i).service.getClassName().equals(className) == true) {
				isRunning = true;
				mContext.stopService(intent);
				break;
			}
		}
		return isRunning;
	}

	/**
	 * 显示软件下载对话框
	 */
	public void download() {
		apkName = savePath + "/" + saveApkName;
		// 构造软件下载对话框
		Builder builder = new AlertDialog.Builder(mContext);
		builder.setTitle("软件版本更新");
		// 给下载对话框增加进度条
		final LayoutInflater inflater = LayoutInflater.from(mContext);
		View v = inflater.inflate(R.layout.progress, null);
		pBar = (ProgressBar) v.findViewById(R.id.progress);
		builder.setView(v);
		// builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
		// {
		//
		// @Override
		// public void onClick(DialogInterface dialog, int which) {
		// // 现在文件
		// // downFile();
		// dialog.dismiss();
		//
		// }
		// });
		// 取消更新
		if (!"Y".equals(isForceUpdate)) {
			builder.setNegativeButton("取消",
					new android.content.DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
							// 设置取消状态
							interceptFlag = false;
						}
					});
		}
		dialog = builder.create();
		dialog.show();
		dialog.setCancelable(false);
		downFile();

	}

	/**
	 * apk下载
	 * 
	 * @param apkUrl
	 * 
	 */
	private void downFile() {
		new Thread() {
			public void run() {
			  HttpURLConnection urlConn = null;
				int length;
				try {
					Log.i("info", "apkUrl=" + apkUrl);
	        urlConn = HttpHelper.download(apkUrl);					
					length = urlConn.getContentLength();
					InputStream is = urlConn.getInputStream();
					Log.i("info", "length=" + length);
					File file = new File(savePath);
					if (!file.exists()) {
						file.mkdirs();
					}
					File apkFile = new File(savePath, saveApkName);
					if (apkFile.exists()) {
						apkFile.delete();
					}
					try {
						apkFile.createNewFile();
						RandomAccessFile threadfile = new RandomAccessFile(apkFile, "rwd");
					} catch (IOException e) {
						e.printStackTrace();
					}
					FileOutputStream fos = new FileOutputStream(apkFile);
					int count = 0;
					byte buf[] = new byte[1024];
					if (null != is) {
						do {
							int numread = is.read(buf);
							count += numread;
							LogManager.i(ApkUpdate.class, "count=" + count);
							progressNum = (int) (((float) count / length) * 100);
							Log.i("info", "progressNum=" + progressNum);
							// 更新进度
							Message msg1 = new Message();
							msg1.what = DOWN_UPDATE;
							handler.sendMessage(msg1);
							if (numread <= 0) {
								Log.i("info", "下载完成=");
								// 下载完成通知安装
								Message msg2 = new Message();
								msg2.what = DOWNLOAD_FINISH;
								handler.sendMessage(msg2);
								break;
							}
							fos.write(buf, 0, numread);
						} while (interceptFlag);// 点击取消就停止下载.
						fos.flush();
						fos.close();
						is.close();
					}

					// Log.i(_Tag, "文件长度:" + length);

				} catch (MalformedURLException e1) {
					e1.printStackTrace();
					System.out.println("MalformedURLException==");
				} catch (IOException e1) {
					// 网络连接异常是执行此方法
					e1.printStackTrace();
					System.out.println("IOException==");
				}
        finally {
          if (null != urlConn) {
            urlConn.disconnect();
          }
        }
			};
		}.start();
	}

	/**
	 * 安装app功能方法
	 * */
	public static void installMethod(Context context, String apkName) {

		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(new File(apkName)),
				"application/vnd.android.package-archive");

		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			intent.setDataAndType(Uri.fromFile(new File(apkName)),
					"application/vnd.android.package-archive");
		} else {

			intent.setDataAndType(Uri.fromFile(new File(context
					.getFileStreamPath(apkName).getPath())),
					"application/vnd.android.package-archive");
		}
		context.startActivity(intent);
		
	}
}
