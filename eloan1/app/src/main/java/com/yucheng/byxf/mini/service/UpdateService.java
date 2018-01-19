package com.yucheng.byxf.mini.service;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.yucheng.byxf.mini.ui.HomeActivity;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.ApkData;
import com.yucheng.byxf.mini.utils.Util;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.RemoteViews;

/** 
 * @author HYX
 */
public class UpdateService extends Service {
	/** 超时时间 */
	private static final int TIMEOUT = 10 * 1000;
	/** 下载的连接 */
	private static String down_url ;
	/** 下载成功 */
	private static final int DOWN_OK = 1;
	/** 下载失败 */
	private static final int DOWN_ERROR = 0;
	/** 当前文件的大小 */
	private static long size;
	/** 应用名称 */
	private String app_name = "轻松e贷";
	/** 通知栏管理器 */
	private NotificationManager notificationManager;
	/** 通知栏 */
	private Notification notification;
	/** 更新版本的的intent，无论下载成功失败都会停止这个service */
	private Intent updateIntent;
	/** 启动一个pendingIntent对通知栏的点击时进行设置跳转 */
	private PendingIntent pendingIntent;
	/** 给通知栏设置一个标志id，方便判断和操作 */
	private int notification_id = 0;
	/** 联网方式 */
	private HttpURLConnection httpURLConnection;
	/** 通知栏的builder */
	private Builder builder = null;

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@SuppressLint("HandlerLeak")
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if (intent != null) {

			// 从调用该service的地方获取传递的内容
			app_name = ApkData.appName;
			down_url = intent.getStringExtra("url");
			// 创建文件
			Util.createFile(app_name);
			// 创建通知栏
			createNotification();
			// 创建现成进行下载,并且对下载结果进行处理
			createThread();
		}
		return super.onStartCommand(intent, flags, startId);// 调用父类的方法
	}

	/*** 开线程下载 */
	public void createThread() {
		/*** 更新UI */
		final Handler handler = new Handler() {
			@SuppressWarnings("deprecation")
			@Override
			public void handleMessage(Message msg) {
				updateIntent = new Intent(UpdateService.this,
						UpdateService.class);// 创建service自身对象的intent
				switch (msg.what) {
				case DOWN_OK: // 下载完成，点击安装
					openFile();// 打开下载好的文件
					Uri uri = Uri.fromFile(Util.updateFile);// 找到事先设置好的文件路径
					Intent intent = new Intent(Intent.ACTION_VIEW);
					intent.setDataAndType(uri,
							"application/vnd.android.package-archive");// 设置接收数据和接收类型
					pendingIntent = PendingIntent.getActivity(
							UpdateService.this, 0, intent, 0);// 初始化延迟接收数据的内容
					notification.setLatestEventInfo(
							UpdateService.this,
							app_name,
							getResources().getString(
									R.string.upversion_noti_compelet),// 下载完成，请点击安装
							pendingIntent);// 在此处设置在notify里的该notifycation得显示情况。
					notificationManager.notify(notification_id, notification);// 激活notifycation通知该id的notify
					stopService(updateIntent);// 停止该service
					break;
				case DOWN_ERROR:// 如果下载失败的话
					notification.flags = Notification.FLAG_AUTO_CANCEL;
					notification.setLatestEventInfo(
							UpdateService.this,
							app_name,
							getResources().getString(
									R.string.upversion_noti_fail),// 下载失败
							pendingIntent);// 在此处设置在notify里的该notifycation得显示情况。
					notificationManager.notify(notification_id, notification);// 激活notifycation通知该id的notify
					stopService(updateIntent);// 停止该service
					break;
				default:// 其他的任何情况都会停止这个service
					stopService(updateIntent);// 停止该service
					break;
				}
			}
		};

		final Message message = new Message();// 新建message对象
		new Thread(new Runnable() {// 开启现成下载APK
					@Override
					public void run() {
						try {
							long downloadSize = downloadUpdateFile(down_url,
									Util.updateFile.toString());// 获得文件已下载的总大小
							if (downloadSize > 0) {
								// 下载成功的标志，发送回主线程进行处理
								message.what = DOWN_OK;
								handler.sendMessage(message);
							}
						} catch (Exception e) {
							// 下载失败的标志，发送回主线程进行处理
							e.printStackTrace();
							message.what = DOWN_ERROR;
							handler.sendMessage(message);
						}
					}
				}).start();
	}
	RemoteViews contentView;
	/*** 创建通知栏 */
	public void createNotification() {
		if (notificationManager == null) {// 非空验证 初始化--notificationManager
			notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		}
		if (notification == null) {// 非空验证 初始化--notification
			notification = new Notification();
		}
		notification.icon = R.drawable.icon75;// 通知栏弹出时候的图标
		notification.flags = Notification.FLAG_ONGOING_EVENT
				| Notification.FLAG_AUTO_CANCEL;// 一直保持在通知栏
		// 这个参数是通知提示闪出来的值.
		notification.tickerText = getResources().getString(
				R.string.upversion_noti_tickerText);
		/**在这里我们用自定的view来显示Notification*/
		contentView = new RemoteViews(getPackageName(),R.layout.notification_item);
		contentView.setTextViewText(R.id.notificationTitle, "轻松e贷正在下载");
		contentView.setTextViewText(R.id.notificationPercent, "0%");
		contentView.setProgressBar(R.id.notificationProgress, 100, 0, false);
		notification.contentView = contentView;
		updateIntent = new Intent(this, HomeActivity.class);
		updateIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		pendingIntent = PendingIntent.getActivity(this, 0, updateIntent, 0);
		notification.contentIntent = pendingIntent;
		notificationManager.notify(notification_id, notification);
	}

	/***
	 * 下载文件
	 * 
	 * @return
	 * @throws MalformedURLException
	 */
	public long downloadUpdateFile(String down_url, String file)
			throws Exception {

		int down_step = 5;// 提示step
		long totalSize;// 文件总大小
		int downloadCount = 0;// 已经下载好的大小
		int updateCount = 0;// 已经上传的文件大小
		InputStream inputStream;
		OutputStream outputStream;
		URL url = new URL(down_url);
		httpURLConnection = (HttpURLConnection) url.openConnection();
		httpURLConnection.setConnectTimeout(TIMEOUT);
		httpURLConnection.setReadTimeout(TIMEOUT);
		// 获取下载文件的size
		totalSize = httpURLConnection.getContentLength();
		if (httpURLConnection.getResponseCode() == 404) {
			throw new Exception("fail!");
		}
		inputStream = httpURLConnection.getInputStream();
		outputStream = new FileOutputStream(file, false);// 文件存在则覆盖掉
		byte buffer[] = new byte[1024];
		int readsize = 0;
		while ((readsize = inputStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, readsize);
			downloadCount += readsize;// 时时获取下载到的大小
			/** 每次增张5%*/
			if (updateCount == 0 || (downloadCount * 100 / totalSize - down_step) >= updateCount) {
				updateCount += down_step;
				contentView.setTextViewText(R.id.notificationPercent,updateCount + "%");
				contentView.setProgressBar(R.id.notificationProgress, 100,updateCount, false);
				notificationManager.notify(notification_id, notification);
			}
		}
		if (httpURLConnection != null) {
			httpURLConnection.disconnect();
		}
		inputStream.close();
		outputStream.close();
		return downloadCount;
	}

	/**
	 * 安装apk openFile UpdateService
	 */
	private void openFile() {
		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// 添加标记
		intent.setAction(android.content.Intent.ACTION_VIEW);// 设置执行的活动
		intent.setDataAndType(Uri.fromFile(Util.updateFile),
				"application/vnd.android.package-archive");// 设置启动系统安装apk的程序
		startActivity(intent);
	}

	@Override
	public void onDestroy() {
		if (httpURLConnection != null) {
			httpURLConnection.disconnect();// 断开连接
		}
		super.onDestroy();
	}
}
