package com.yucheng.byxf.mini.utils;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.byxf.comm.ScreenManager;
import com.yucheng.byxf.mini.domain.MiniPersonInfo;
import com.yucheng.byxf.mini.easyloan.ui.SharedPreferencesUtils;
import com.yucheng.byxf.mini.map.DemoApplication;
import com.yucheng.byxf.mini.message.PhotoInfo;
import com.yucheng.byxf.mini.message.Response;
import com.yucheng.byxf.mini.message.Response1;
import com.yucheng.byxf.mini.ui.MyLoginActivity;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.ui.ShareActivity;
import com.yucheng.byxf.mini.utils.CustomMultiPartEntity.ProgressListener;

public class HttpMultipartPostAddMore {

	/**
	 * 轻松贷 上传 网络请求
	 */
	long totalSize;
	Context context;
	public static List<PhotoInfo> list;
	public static List<ProgressBar> progressBars = new ArrayList<ProgressBar>();
	public static List<ImageView> imageButtons = new ArrayList<ImageView>();
	public static List<ImageView> flagViews = new ArrayList<ImageView>();
	public static boolean isableClick[];
	public static int buttonId[];
	String photoUrl;
	List<Map<String, Object>> listInfo;
	public static String infoUrl;
	Gson gson;

	int uploadNum = 0;// 正在上传的文件编号

	public static boolean isFlag[];

	public static final int PHOTO_FAIL = 11;
	public static final int PHOTO_SUCCEED = 10;
	public static final int PHOTO_SUCCEED1 = 110;
	public static final int PHOTO_SUCCEEDDATA = 111;

	// public static final int IMAGEBUTTON_ONCLICK = 20;
	// public static final int IMAGEBUTTON_ONCLICK0 = 21;
	// public static final int IMAGEBUTTON_ONCLICKDATA = 22;

	int i;
	int j;
	int photo;
	Button button;

	private boolean isCan = true;
	DemoApplication application = null;
	
	private String cde;
	private Dialog dialog;

	public void showDialogProgress() {
		final Dialog dialog = new Dialog(context, R.style.myDialog);
		dialog.setContentView(R.layout.upload_add_dialog);
		LinearLayout layout = (LinearLayout) dialog.getWindow().getDecorView()
				.findViewById(R.id.upload_layout);
		final LayoutInflater inflater = LayoutInflater.from(context);
		for (i = 0; i < list.size() + 1; i++) {
			isFlag[i] = false;
			isableClick[i] = false;
			buttonId = new int[list.size() + 1];
			if (i != list.size()) {
				View view = inflater.inflate(R.layout.upload_view_layout, null);
				TextView name = (TextView) view.findViewById(R.id.photo_name);
				ImageView image = (ImageView) view
						.findViewById(R.id.upload_imagebutton);
				ProgressBar progress = (ProgressBar) view
						.findViewById(R.id.progress);
				ImageView upload_flag = (ImageView) view
						.findViewById(R.id.upload_flag);
				layout.addView(view);
				progressBars.add(progress);
				imageButtons.add(image);
				image.setId(2000 + i);
				buttonId[i] = 2000 + i;
				name.setText(list.get(i).getChinaName());
				flagViews.add(upload_flag);
			} else {
				View view = inflater.inflate(R.layout.upload_view_layout, null);
				TextView name = (TextView) view.findViewById(R.id.photo_name);
				ImageView image = (ImageView) view
						.findViewById(R.id.upload_imagebutton);
				ProgressBar progress = (ProgressBar) view
						.findViewById(R.id.progress);
				ImageView upload_flag = (ImageView) view
						.findViewById(R.id.upload_flag);
				layout.addView(view);
				progressBars.add(progress);
				imageButtons.add(image);
				image.setId(2000 + i);
				buttonId[i] = 2000 + i;
				name.setText("所有申请信息");
				flagViews.add(upload_flag);
				image.setBackgroundResource(R.drawable.progress_data);
			}
		}

		// button = (Button) dialog.getWindow().getDecorView()
		// .findViewById(R.id.sureButton);
		// button.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		// if (isFlag[photo]) {
		//
		// progressBars.clear();
		// imageButtons.clear();
		// flagViews.clear();
		//
		// ScreenManager screenManager = ScreenManager
		// .getScreenManager();
		// screenManager.popAllActivityExceptOne();
		// Intent intent = new Intent(context, HomeActivity.class);
		// context.startActivity(intent);
		// }
		//
		// }
		// });

		for (j = 0; j < list.size() + 1; j++) {
			imageButtons.get(j).setOnClickListener(
					new SetButtonOnClickListener());
		}

		dialog.show();
		dialog.setCanceledOnTouchOutside(false);
		dialog.setCancelable(false);
	}

	public HttpMultipartPostAddMore(Context context, List<PhotoInfo> list,
			String photoUrl, List<Map<String, Object>> listInfo, String infoUrl) {
		super();
		this.context = context;
		this.list = list;
		this.photoUrl = photoUrl;
		this.listInfo = listInfo;
		this.infoUrl = infoUrl;
		photo = list.size();
		gson = new Gson();
		application = (DemoApplication) context.getApplicationContext();
		isFlag = new boolean[list.size() + 1];
		isableClick = new boolean[list.size() + 1];
		showDialogProgress();

		new ImageUpLoading().execute();
	}

	public HttpMultipartPostAddMore(Context context, List<PhotoInfo> list,
			String photoUrl,String applCde) {
		cde = applCde;
		this.context = context;
		this.list = list;
		this.photoUrl = photoUrl;
		photo = list.size();
		gson = new Gson();
		application = (DemoApplication) context.getApplicationContext();
		isFlag = new boolean[list.size() + 1];
		isableClick = new boolean[list.size() + 1];
		showDialogProgress();

		new ImageUpLoading2().execute();
	}

	class ImageUpLoading extends AsyncTask<HttpResponse, Integer, String> {

		@Override
		protected String doInBackground(HttpResponse... arg0) {
			HttpClient httpClient = HttpHelper.getInstance(context);
			HttpContext httpContext = new BasicHttpContext();
			HttpPost httpPost = new HttpPost(photoUrl);

			try {
				CustomMultiPartEntity multipartContent = new CustomMultiPartEntity(
						new ProgressListener() {
							@Override
							public void transferred(long num) {
								publishProgress((int) ((num / (float) totalSize) * 100));
							}
						});

				// We use FileBody to transfer an image
				if (uploadNum <= list.size()) {
					multipartContent.addPart("docNo",
							new StringBody(list.get(uploadNum).getDocNo()));// 文档编号
					multipartContent.addPart("bizNo",
							new StringBody(list.get(uploadNum).getBizNo()));// 申请编号
					multipartContent.addPart("docName",
							new StringBody(list.get(uploadNum).getDocName(),
									Charset.forName("utf-8")));// 文档名称
					multipartContent.addPart("docType",
							new StringBody(list.get(uploadNum).getDocType(),
									Charset.forName("utf-8")));// 文档类型
					multipartContent.addPart("fileName",
							new StringBody(list.get(uploadNum).getFileName(),
									Charset.forName("utf-8")));// 文件名称
					multipartContent.addPart("bizType",
							new StringBody(list.get(uploadNum).getBizType(),
									Charset.forName("utf-8")));// 业务类型
					multipartContent.addPart("picture", new FileBody(new File(
							list.get(uploadNum).getPicture())));// 图片文件
				}
				// "/storage/sdcard0/DCIM/Camera/IMG_20130107_200357.jpg"
				totalSize = multipartContent.getContentLength();
				System.out.println("====================>" + totalSize);

				// Send it
				httpPost.setEntity(multipartContent);
				HttpResponse response = httpClient.execute(httpPost,
						httpContext);
				String serverResponse = EntityUtils.toString(response
						.getEntity());

				// ResponseFactory rp = new ResponseFactory(serverResponse);
				// return (TypeImage) rp.getData();

				return serverResponse;
			}

			catch (Exception e) {
				System.out.println(e);
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... progress) {
			progressBars.get(uploadNum).setProgress((int) progress[0]);
		}

		@Override
		protected void onPostExecute(String ui) {
			System.out.println("UI------------------------->" + ui);
			if (ui != null) {
				Response response = gson.fromJson(ui, Response.class);
				Log.d("xxx", "响应码为：" + response.getCode());
				if (response.getCode() == 0) {
					Message msg = new Message();
					msg.what = PHOTO_SUCCEED;
					isFlag[uploadNum] = true;
					handler.sendMessage(msg);
				} else if (response.getCode() == 403) {
					handler.sendEmptyMessage(403);
				} else {
					Message msg = new Message();
					msg.what = PHOTO_FAIL;
					isFlag[uploadNum] = false;
					msg.obj = response.getMsg();
					handler.sendMessage(msg);
				}
			} else {
				Message msg = new Message();
				msg.what = PHOTO_FAIL;
				isFlag[uploadNum] = false;
				handler.sendMessage(msg);
			}
		}
	}

	class ImageUpLoading1 extends AsyncTask<HttpResponse, Integer, String> {

		@Override
		protected String doInBackground(HttpResponse... arg0) {
			HttpClient httpClient = HttpHelper.getInstance(context);
			HttpContext httpContext = new BasicHttpContext();
			HttpPost httpPost = new HttpPost(photoUrl);

			try {
				CustomMultiPartEntity multipartContent = new CustomMultiPartEntity(
						new ProgressListener() {
							@Override
							public void transferred(long num) {
								publishProgress((int) ((num / (float) totalSize) * 100));
							}
						});

				// We use FileBody to transfer an image
				if (uploadNum <= list.size()) {
					multipartContent.addPart("docNo",
							new StringBody(list.get(uploadNum).getDocNo()));// 文档编号
					multipartContent.addPart("bizNo",
							new StringBody(list.get(uploadNum).getBizNo()));// 申请编号
					multipartContent.addPart("docName",
							new StringBody(list.get(uploadNum).getDocName(),
									Charset.forName("utf-8")));// 文档名称
					multipartContent.addPart("docType",
							new StringBody(list.get(uploadNum).getDocType(),
									Charset.forName("utf-8")));// 文档类型
					multipartContent.addPart("fileName",
							new StringBody(list.get(uploadNum).getFileName(),
									Charset.forName("utf-8")));// 文件名称
					multipartContent.addPart("bizType",
							new StringBody(list.get(uploadNum).getBizType(),
									Charset.forName("utf-8")));// 业务类型
					multipartContent.addPart("picture", new FileBody(new File(
							list.get(uploadNum).getPicture())));//
				}

				totalSize = multipartContent.getContentLength();
				System.out.println("====================>" + totalSize);

				// Send it
				httpPost.setEntity(multipartContent);
				HttpResponse response = httpClient.execute(httpPost,
						httpContext);
				String serverResponse = EntityUtils.toString(response
						.getEntity());
				return serverResponse;
			}

			catch (Exception e) {
				System.out.println(e);
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... progress) {
			progressBars.get(uploadNum).setProgress((int) progress[0]);
		}

		@Override
		protected void onPostExecute(String ui) {
			if (ui != null) {
				Response1 response = gson.fromJson(ui, Response1.class);
				Log.d("xxx", "response.getCode()" + response.getCode());
				if (response.getCode() == 0) {
					Message msg = new Message();
					msg.what = PHOTO_SUCCEED1;
					handler.sendMessage(msg);

				} else if (response.getCode() == 403) {
					handler.sendEmptyMessage(403);
				} else {
					Message msg = new Message();
					msg.what = PHOTO_FAIL;
					isFlag[uploadNum] = false;
					msg.obj = response.getMsg();
					handler.sendMessage(msg);
				}
			} else {
				Message msg = new Message();
				msg.what = PHOTO_FAIL;
				isFlag[uploadNum] = false;
				handler.sendMessage(msg);

			}
		}
	}

	class ImageUpLoading2 extends AsyncTask<HttpResponse, Integer, String> {

		@Override
		protected String doInBackground(HttpResponse... arg0) {
			HttpClient httpClient = HttpHelper.getInstance(context);
			HttpContext httpContext = new BasicHttpContext();
			HttpPost httpPost = new HttpPost(photoUrl);

			try {
				CustomMultiPartEntity multipartContent = new CustomMultiPartEntity(
						new ProgressListener() {
							@Override
							public void transferred(long num) {
								publishProgress((int) ((num / (float) totalSize) * 100));
							}
						});

				// We use FileBody to transfer an image
				if (uploadNum <= list.size()) {
					multipartContent.addPart("docNo",
							new StringBody(list.get(uploadNum).getDocNo()));// 文档编号
					multipartContent.addPart("bizNo",
							new StringBody(list.get(uploadNum).getBizNo()));// 申请编号
					multipartContent.addPart("docName",
							new StringBody(list.get(uploadNum).getDocName(),
									Charset.forName("utf-8")));// 文档名称
					multipartContent.addPart("docType",
							new StringBody(list.get(uploadNum).getDocType(),
									Charset.forName("utf-8")));// 文档类型
					multipartContent.addPart("fileName",
							new StringBody(list.get(uploadNum).getFileName(),
									Charset.forName("utf-8")));// 文件名称
					multipartContent.addPart("bizType",
							new StringBody(list.get(uploadNum).getBizType(),
									Charset.forName("utf-8")));// 业务类型
					multipartContent.addPart("picture", new FileBody(new File(
							list.get(uploadNum).getPicture())));// 图片文件
				}
				// "/storage/sdcard0/DCIM/Camera/IMG_20130107_200357.jpg"
				totalSize = multipartContent.getContentLength();
				System.out.println("====================>" + totalSize);

				// Send it
				httpPost.setEntity(multipartContent);
				HttpResponse response = httpClient.execute(httpPost,
						httpContext);
				String serverResponse = EntityUtils.toString(response
						.getEntity());
				return serverResponse;
			}
			catch (Exception e) {
				System.out.println(e);
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... progress) {
			progressBars.get(uploadNum).setProgress((int) progress[0]);
		}

		@Override
		protected void onPostExecute(String ui) {
			if (ui != null) {
				Response1 response = gson.fromJson(ui, Response1.class);
				Log.d("xxx", "response.getCode()" + response.getCode());
				if (response.getCode() == 0) {
					Message msg = new Message();
					msg.what = PHOTO_SUCCEED1;
					handler1.sendMessage(msg);

				} else if (response.getCode() == 403) {
					handler1.sendEmptyMessage(403);
				} else {
					Message msg = new Message();
					msg.what = PHOTO_FAIL;
					isFlag[uploadNum] = false;
					msg.obj = response.getMsg();
					handler1.sendMessage(msg);
				}
			} else {
				Message msg = new Message();
				msg.what = PHOTO_FAIL;
				isFlag[uploadNum] = false;
				handler1.sendMessage(msg);

			}
		}
	}
	
	class DataUpLoading1 extends AsyncTask<String, Integer, String> {

		@Override
		protected String doInBackground(String... arg0) {
			try {
				CustomMultiPartEntity multipartContent = new CustomMultiPartEntity(
						new ProgressListener() {
							@Override
							public void transferred(long num) {
								publishProgress((int) ((num / (float) totalSize) * 100));
							}
						});
				// 得到一个key的集合，通过key值来取出value
				for (String key : listInfo.get(0).keySet()) {
					String value = (String) listInfo.get(0).get(key);
					if (value == null) {
						value = "";
					}
					multipartContent.addPart(key,
							new StringBody(value, Charset.forName("utf-8")));
				}
				totalSize = multipartContent.getContentLength();
				System.out.println(totalSize + "=====================>");
				// Send it
				return "ok";
			}

			catch (Exception e) {
				System.out.println(e);
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... progress) {
			progressBars.get(uploadNum).setProgress((int) (progress[0]));
		}

		@Override
		protected void onPostExecute(String ui) {
			isFlag[uploadNum] = true;
			Message msg = new Message();
			msg.what = PHOTO_SUCCEEDDATA;
			handler1.sendMessage(msg);
			new uploadVideoFile().execute();
		}
	}
	
	// 上传文件到ftp
	class uploadVideoFile extends AsyncTask<String, Object, Object> {

		@Override
		protected Object doInBackground(String... params) {
			// TODO Auto-generated method stub
			HttpHelper httpHelper = HttpHelper.getInstance(context);
			List<NameValuePair> arg = new ArrayList<NameValuePair>();
			arg.add(new BasicNameValuePair("applCde", cde));
			arg.add(new BasicNameValuePair("type", "01"));
			Log.d("xxx", "接口的地址是：====》" + ContantsAddress.ACTIVITY_MENU);
			return httpHelper.post(ContantsAddress.UPLOAD_VIDEO_FILE, arg,
					Response.class);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			if (null != dialog && dialog.isShowing()) {
				dialog.dismiss();
			}
//			showProgressDialog();
		}

		@Override
		protected void onPostExecute(Object result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			Response response = (Response) result;
			if (response != null) {
				if (0 == response.getCode()) {
					Toast.makeText(context, response.getMsg(),
							Toast.LENGTH_LONG).show();
				} else {
					System.out.println(response.getMsg());
					Toast.makeText(context,
							"上传数据失败！" + response.getMsg(), Toast.LENGTH_LONG)
							.show();
				}
			} else {
				Toast.makeText(context,
						"返回数据为空",
						Toast.LENGTH_LONG).show();
			}
		}

	}
	
	class DataUpLoading extends AsyncTask<String, Integer, String> {

		@Override
		protected String doInBackground(String... arg0) {
			HttpClient httpClient = HttpHelper.getInstance(context);

			HttpContext httpContext = new BasicHttpContext();
			HttpPost httpPost = new HttpPost(infoUrl);
			System.out.println("infoUrl=======>" + infoUrl);
			try {
				CustomMultiPartEntity multipartContent = new CustomMultiPartEntity(
						new ProgressListener() {
							@Override
							public void transferred(long num) {
								publishProgress((int) ((num / (float) totalSize) * 100));
							}
						});
				// 得到一个key的集合，通过key值来取出value
				for (String key : listInfo.get(0).keySet()) {
					String value = (String) listInfo.get(0).get(key);
					if (value == null) {
						value = "";
					}
					multipartContent.addPart(key,
							new StringBody(value, Charset.forName("utf-8")));
				}
				totalSize = multipartContent.getContentLength();
				System.out.println(totalSize + "=====================>");
				// Send it
				httpPost.setEntity(multipartContent);
				HttpResponse response = httpClient.execute(httpPost,
						httpContext);
				String serverResponse = EntityUtils.toString(response
						.getEntity());
				return serverResponse;
			}

			catch (Exception e) {
				System.out.println(e);
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... progress) {
			progressBars.get(uploadNum).setProgress((int) (progress[0]));
		}

		@Override
		protected void onPostExecute(String ui) {
			System.out.println("ui======================>" + ui);
			if (ui != null) {
				Response response = gson.fromJson(ui, Response.class);
				if (response.getCode() == 0) {
					isFlag[uploadNum] = true;
					Message msg = new Message();
					msg.what = PHOTO_SUCCEEDDATA;
					handler.sendMessage(msg);
				} else if (response.getCode() == 403) {
					handler.sendEmptyMessage(403);
				} else {
					isFlag[uploadNum] = false;
					Message msg = new Message();
					msg.what = PHOTO_FAIL;
					msg.obj = response.getMsg();
					handler.sendMessage(msg);
				}
			} else {
				isFlag[uploadNum] = false;
				Message msg = new Message();
				msg.what = PHOTO_FAIL;
				handler.sendMessage(msg);
			}
		}
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case PHOTO_SUCCEED:
				System.out.println("uploadNum==========>" + uploadNum);
				flagViews.get(uploadNum).setBackgroundResource(
						R.drawable.down_ok);
				// progressBars.get(uploadNum).setProgress(100);
				// if (isFlag[list.size()]) {
				// button.setBackgroundResource(R.drawable.upload_sure_selector);
				// }
				uploadNum++;
				if (uploadNum < list.size()) {
					new ImageUpLoading().execute();
				} else {
					new DataUpLoading().execute();
				}
				break;
			case PHOTO_SUCCEED1:
				System.out.println("uploadNum==========>" + uploadNum);
				flagViews.get(uploadNum).setBackgroundResource(
						R.drawable.down_ok);
				// progressBars.get(uploadNum).setProgress(100);
				// if (isFlag[list.size()]) {
				// button.setBackgroundResource(R.drawable.upload_sure_selector);
				// }
				isFlag[uploadNum] = true;
				uploadNum++;
				if (uploadNum < list.size()) {
					new ImageUpLoading().execute();
				} else {
					new DataUpLoading().execute();
				}
				break;
			case PHOTO_SUCCEEDDATA:
				flagViews.get(uploadNum).setBackgroundResource(
						R.drawable.down_ok);
				// button.setBackgroundResource(R.drawable.dialog_neg2);

				application.applyPersonalInfo = null;
				application.basicInfo = null;
				application.linkManInfo = null;
				application.professionInfo = null;
				application.assertInfo = null;
				application.clientInfo = null;

				MiniPersonInfo.photoPath1 = null;// 照片存储路径1
				MiniPersonInfo.photoPath2 = null;// 照片存储路径2
				MiniPersonInfo.photoPath3 = null;// 照片存储路径3
				MiniPersonInfo.photoPath4 = null;// 照片存储路径4
				MiniPersonInfo.photoPath5 = null;// 照片存储路径5

				MiniPersonInfo.photoPath51 = null;// 照片存储路径1
				MiniPersonInfo.photoPath52 = null;// 照片存储路径2
				MiniPersonInfo.photoPath53 = null;// 照片存储路径3
				MiniPersonInfo.photoPath54 = null;// 照片存储路径4
				MiniPersonInfo.photoPath55 = null;// 照片存储路径5

				// 压缩后的图片
				MiniPersonInfo.compress_photoPath1 = null;// 照片存储路径1
				MiniPersonInfo.compress_photoPath2 = null;// 照片存储路径2
				MiniPersonInfo.compress_photoPath3 = null;// 照片存储路径3
				MiniPersonInfo.compress_photoPath4 = null;// 照片存储路径4
				MiniPersonInfo.compress_photoPath5 = null;// 照片存储路径5

				MiniPersonInfo.compress_photoPath51 = null;// 照片存储路径1
				MiniPersonInfo.compress_photoPath52 = null;// 照片存储路径2
				MiniPersonInfo.compress_photoPath53 = null;// 照片存储路径3
				MiniPersonInfo.compress_photoPath54 = null;// 照片存储路径4
				MiniPersonInfo.compress_photoPath55 = null;// 照片存储路径5

				SharedPreferencesUtils preferUtil = new SharedPreferencesUtils(
						context);
				preferUtil.clearData();
				// ------------存登陆标志信息
				preferUtil.setData("login_sign", "login_sign");

				progressBars.clear();
				imageButtons.clear();
				flagViews.clear();
				Intent intent2 = new Intent(context, ShareActivity.class);
				intent2.putExtra("type_ch", "0");
				context.startActivity(intent2);
				//
				//
				// DialogUtil.showDialogOneButton_tijiao(context, "提交成功！", new
				// OnClickListener() {
				//
				// @Override
				// public void onClick(View v) {
				// // TODO Auto-generated method stub
				// progressBars.clear();
				// imageButtons.clear();
				// flagViews.clear();
				//
				// ScreenManager screenManager = ScreenManager
				// .getScreenManager();
				// screenManager.popAllActivityExceptOne();
				// Intent intent = new Intent(context, HomeActivity.class);
				// context.startActivity(intent);
				// }
				// });

				break;
			case PHOTO_FAIL:
				System.out.println("======uploadNum====>" + uploadNum);
				progressBars.get(uploadNum).setProgress(0);
				flagViews.get(uploadNum).setBackgroundResource(
						R.drawable.down_flase);
				imageButtons.get(uploadNum).setBackgroundResource(
						R.drawable.uploadagain);
				isableClick[uploadNum] = true;
				// if (uploadNum == list.size()+1) {
				System.out.println(msg.obj + "===>");
				Toast.makeText(context, msg.obj + "", Toast.LENGTH_LONG).show();
				// }
				break;

			case 403:
				Toast.makeText(context, "会话超时，请重新登录", Toast.LENGTH_LONG).show();
				Contents.IS_LOGIN = false;
				Contents.username = "";
				Contents.custInfoResponseResult = null;
				Contents.response = null;
				ScreenManager screenManager = ScreenManager.getScreenManager();
				screenManager.popAllActivityExceptOne();
				MiniPersonInfo.clearAll();
				Intent intent = new Intent();
				intent.putExtra("openHome", "openHome");
				intent.setClass(context, MyLoginActivity.class);
				context.startActivity(intent);
				break;
			}
		};
	};

	Handler handler1 = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case PHOTO_SUCCEED:
				System.out.println("uploadNum==========>" + uploadNum);
				flagViews.get(uploadNum).setBackgroundResource(
						R.drawable.down_ok);
				uploadNum++;
				if (uploadNum < list.size()) {
					new ImageUpLoading2().execute();
				} else {
					new DataUpLoading1().execute();
				}
				break;
			case PHOTO_SUCCEED1:
				System.out.println("uploadNum==========>" + uploadNum);
				flagViews.get(uploadNum).setBackgroundResource(
						R.drawable.down_ok);
				isFlag[uploadNum] = true;
				uploadNum++;
				if (uploadNum < list.size()) {
					new ImageUpLoading2().execute();
				} else {
					new DataUpLoading1().execute();
				}
				break;
			case PHOTO_SUCCEEDDATA:
				flagViews.get(uploadNum).setBackgroundResource(
						R.drawable.down_ok);

				application.applyPersonalInfo = null;
				application.basicInfo = null;
				application.linkManInfo = null;
				application.professionInfo = null;
				application.assertInfo = null;
				application.clientInfo = null;

				MiniPersonInfo.photoPath1 = null;// 照片存储路径1
				MiniPersonInfo.photoPath2 = null;// 照片存储路径2
				MiniPersonInfo.photoPath3 = null;// 照片存储路径3
				MiniPersonInfo.photoPath4 = null;// 照片存储路径4
				MiniPersonInfo.photoPath5 = null;// 照片存储路径5

				MiniPersonInfo.photoPath51 = null;// 照片存储路径1
				MiniPersonInfo.photoPath52 = null;// 照片存储路径2
				MiniPersonInfo.photoPath53 = null;// 照片存储路径3
				MiniPersonInfo.photoPath54 = null;// 照片存储路径4
				MiniPersonInfo.photoPath55 = null;// 照片存储路径5

				// 压缩后的图片
				MiniPersonInfo.compress_photoPath1 = null;// 照片存储路径1
				MiniPersonInfo.compress_photoPath2 = null;// 照片存储路径2
				MiniPersonInfo.compress_photoPath3 = null;// 照片存储路径3
				MiniPersonInfo.compress_photoPath4 = null;// 照片存储路径4
				MiniPersonInfo.compress_photoPath5 = null;// 照片存储路径5

				MiniPersonInfo.compress_photoPath51 = null;// 照片存储路径1
				MiniPersonInfo.compress_photoPath52 = null;// 照片存储路径2
				MiniPersonInfo.compress_photoPath53 = null;// 照片存储路径3
				MiniPersonInfo.compress_photoPath54 = null;// 照片存储路径4
				MiniPersonInfo.compress_photoPath55 = null;// 照片存储路径5

				SharedPreferencesUtils preferUtil = new SharedPreferencesUtils(
						context);
				preferUtil.clearData();
				// ------------存登陆标志信息
				preferUtil.setData("login_sign", "login_sign");

				progressBars.clear();
				imageButtons.clear();
				flagViews.clear();
				Intent intent2 = new Intent(context, ShareActivity.class);
				intent2.putExtra("type_ch", "0");
				context.startActivity(intent2);

				break;
			case PHOTO_FAIL:
				System.out.println("======uploadNum====>" + uploadNum);
				progressBars.get(uploadNum).setProgress(0);
				flagViews.get(uploadNum).setBackgroundResource(
						R.drawable.down_flase);
				imageButtons.get(uploadNum).setBackgroundResource(
						R.drawable.uploadagain);
				isableClick[uploadNum] = true;
				System.out.println(msg.obj + "===>");
				Toast.makeText(context, msg.obj + "", Toast.LENGTH_LONG).show();
				break;

			case 403:
				Toast.makeText(context, "会话超时，请重新登录", Toast.LENGTH_LONG).show();
				Contents.IS_LOGIN = false;
				Contents.username = "";
				Contents.custInfoResponseResult = null;
				Contents.response = null;
				ScreenManager screenManager = ScreenManager.getScreenManager();
				screenManager.popAllActivityExceptOne();
				MiniPersonInfo.clearAll();
				Intent intent = new Intent();
				intent.putExtra("openHome", "openHome");
				intent.setClass(context, MyLoginActivity.class);
				context.startActivity(intent);
				break;
			}
		};
	};
	
	class SetButtonOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (v == imageButtons.get(0)) {
				if (!isFlag[0] && isableClick[0]) {
					new ImageUpLoading().execute();
					imageButtons.get(uploadNum).setBackgroundResource(
							R.drawable.progress_photo);
					isableClick[0] = false;
				}
			} else if (v == imageButtons.get(list.size())) {
				if (!isFlag[list.size() - 1] && isFlag[list.size() - 2]
						&& isableClick[list.size() - 1]) {
					new DataUpLoading().execute();
					imageButtons.get(uploadNum).setBackgroundResource(
							R.drawable.progress_data);
					isableClick[list.size() - 1] = false;
				}
			} else {
				if (!isFlag[uploadNum] && isFlag[uploadNum - 1]
						&& isableClick[uploadNum]) {
					new ImageUpLoading().execute();
					imageButtons.get(uploadNum).setBackgroundResource(
							R.drawable.progress_photo);
					isableClick[uploadNum] = false;
				}
			}
		}

	}
}
