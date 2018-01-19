package com.yucheng.byxf.mini.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import com.yucheng.apfx.net.HttpHelper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class DownLoaderBitmap {

	private OnImageLoadDownListener listener;
	private Context context;
	private Bitmap retBmp = null;

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				// 错误
				listener.OnFinished(retBmp, msg.getData().getInt("index"));
				break;
			case 1:
				// // 关闭
				// if (retBmp == null) {
				// Toast.makeText(context, "后台数据有问题，请联系服务器客服！",
				// Toast.LENGTH_SHORT).show();
				// }
				Toast.makeText(context, msg.getData().getString("error"),
						Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
			}

			// // retBmp.recycle();
			// retBmp = null;
			// context = null;
		};
	};

	public void setOnIamgeLoadDownListener(
			OnImageLoadDownListener loadDownistener) {
		listener = loadDownistener;
	}

	public Bitmap downloadStart(final Context cxt, final String urlStr,
			final Integer id, final String fileName) {
		context = cxt;

		// 启动一个后台线程
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Integer index = id;
					// URL url = new URL(urlStr);
					// HttpURLConnection con = (HttpURLConnection) url
					// .openConnection();
					// con.setDoInput(true);// 设置是否从httpUrlConnection读入
					// con.setConnectTimeout(30000);
					// con.setReadTimeout(60000);
					// con.setDoOutput(true);
					// con.connect();

					HttpClient client = HttpHelper.getInstance(context);
					HttpGet httpget = new HttpGet(urlStr);
					HttpResponse resp = client.execute(httpget);
					InputStream is = null;
					if (HttpStatus.SC_OK == resp.getStatusLine()
							.getStatusCode()) {
						// 将返回内容转换为bitmap
						HttpEntity entity = resp.getEntity();
						is = entity.getContent();
						retBmp = BitmapFactory.decodeStream(is);
						saveData(context, fileName);
						Message msg = new Message();
						msg.what = 0;

						Bundle data = new Bundle();
						data.putInt("index", index);
						msg.setData(data);
						handler.sendMessage(msg);
					}

					// 关闭输入流
					is.close();
				} catch (MalformedURLException e) {
					e.printStackTrace();
					Message msg = new Message();
					msg.what = 1;
					Bundle data = new Bundle();
					data.putString("error", e.getMessage());
					msg.setData(data);
					handler.sendMessage(msg);
					System.out.println("1");
				} catch (IOException e) {
					e.printStackTrace();
					Message msg = new Message();
					msg.what = 1;
					Bundle data = new Bundle();
					data.putString("error", e.getMessage());
					msg.setData(data);
					handler.sendMessage(msg);
					System.out.println("2");
				} catch (Exception e) {
					e.printStackTrace();
					Message msg = new Message();
					msg.what = 1;
					Bundle data = new Bundle();
					data.putString("error", "未知错误");
					msg.setData(data);
					handler.sendMessage(msg);
				}
			}
		}).start();

		return retBmp;
	}

	public Bitmap downloadStart1(final Context cxt, final String urlStr,
			final Integer id, final String fileName) {
		context = cxt;

		// 启动一个后台线程
		new Thread(new Runnable() {
			@Override
			public void run() {
        HttpURLConnection urlConn = null;
				try {
          Integer index = id;
          urlConn = HttpHelper.download(urlStr);
          InputStream is = urlConn.getInputStream();
					retBmp = BitmapFactory.decodeStream(is);
					saveData(context, fileName);
					Message msg = new Message();
					msg.what = 0;

					Bundle data = new Bundle();
					data.putInt("index", index);
					msg.setData(data);
					handler.sendMessage(msg);
					// }

					// 关闭输入流
					is.close();
				} catch (MalformedURLException e) {
					e.printStackTrace();
					Message msg = new Message();
					msg.what = 1;
					Bundle data = new Bundle();
					data.putString("error", e.getMessage());
					msg.setData(data);
					handler.sendMessage(msg);
					System.out.println("1");
				} catch (IOException e) {
					e.printStackTrace();
					Message msg = new Message();
					msg.what = 1;
					Bundle data = new Bundle();
					data.putString("error", e.getMessage());
					msg.setData(data);
					handler.sendMessage(msg);
					System.out.println("2");
				} catch (Exception e) {
					e.printStackTrace();
					Message msg = new Message();
					msg.what = 1;
					Bundle data = new Bundle();
					data.putString("error", "未知错误");
					msg.setData(data);
					handler.sendMessage(msg);
				}
        finally {
          if (null != urlConn) {
            urlConn.disconnect();
          }
        }
			}
		}).start();

		return retBmp;
	}

	private Boolean saveData(Context context, String name) {
		String dirs = "/data/data/" + context.getPackageName()
				+ "/file/";
		File file = new File(dirs);
		if (!file.exists()) {
			file.mkdirs();// 创建文件夹
		}

		String fileName = dirs + name;
		File fileq1 = new File(fileName);
		try {
			fileq1.createNewFile();
			FileOutputStream fos = new FileOutputStream(fileq1);
			retBmp.compress(CompressFormat.JPEG, 100, fos);
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

}
