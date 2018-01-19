
package com.yucheng.byxf.mini.rapidly;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.byxf.comm.ScreenManager;
import com.yucheng.byxf.http.BobHttpClient;
import com.yucheng.byxf.mini.domain.MiniPersonInfo;
import com.yucheng.byxf.mini.easyloan.ui.SharedPreferencesUtils;
import com.yucheng.byxf.mini.eloan.domain.AssertInfo;
import com.yucheng.byxf.mini.map.DemoApplication;
import com.yucheng.byxf.mini.message.PhotoInfo;
import com.yucheng.byxf.mini.message.Response;
import com.yucheng.byxf.mini.message.Response1;
import com.yucheng.byxf.mini.ui.HomeActivity;
import com.yucheng.byxf.mini.ui.MyLoginActivity;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.ui.ShareActivity;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.CustomMultiPartEntity;
import com.yucheng.byxf.mini.utils.DialogUtil;
import com.yucheng.byxf.mini.utils.FileUtils;
import com.yucheng.byxf.mini.utils.CustomMultiPartEntity.ProgressListener;
import com.yucheng.byxf.util.SharedPreferUtil;

public class RapidlyPost extends Activity{

	long totalSize;
	Context context;
	public static List<PhotoInfo> list;
	public static List<ProgressBar> progressBars = new ArrayList<ProgressBar>();
	public static List<ImageView> imageButtons = new ArrayList<ImageView>();
	public static List<ImageView> flagViews = new ArrayList<ImageView>();
	public static int buttonId[];
	List<Map<String, Object>> listInfo;
	public static String infoUrl;
	Gson gson;
	DemoApplication dal;

	int uploadNum = 0;// 正在上传的文件编号
	int num = 0;

	public static boolean isFlag[];

	public static final int PHOTO_FAIL = 11;
	public static final int PHOTO_SUCCEED = 10;
	public static final int PHOTO_SUCCEED1 = 110;
	public static final int PHOTO_SUCCEEDDATA = 111;

	public static final int VIDEO_FAIL = 1;

	// public static final int IMAGEBUTTON_ONCLICK = 20;
	// public static final int IMAGEBUTTON_ONCLICK0 = 21;
	// public static final int IMAGEBUTTON_ONCLICKDATA = 22;

	int i;
	int j;
	int photo;
	Button button;

	private boolean isCan = true;
	DemoApplication application = null;

	public void showDialogProgress() {
		final Dialog dialog = new Dialog(context, R.style.myDialog);
		dialog.setContentView(R.layout.rapidly_upload_add_dialog);

		ImageView imageButton1 = (ImageView) dialog.getWindow().getDecorView()
				.findViewById(R.id.upload_imagebutton1);
		ImageView imageButton2 = (ImageView) dialog.getWindow().getDecorView()
				.findViewById(R.id.upload_imagebutton2);
		ImageView imageButton3 = (ImageView) dialog.getWindow().getDecorView()
				.findViewById(R.id.upload_imagebutton3);
		ImageView imageButton4 = (ImageView) dialog.getWindow().getDecorView()
				.findViewById(R.id.upload_imagebutton4);

		ImageView imageView1 = (ImageView) dialog.getWindow().getDecorView()
				.findViewById(R.id.upload_flag1);
		ImageView imageView2 = (ImageView) dialog.getWindow().getDecorView()
				.findViewById(R.id.upload_flag2);
		ImageView imageView3 = (ImageView) dialog.getWindow().getDecorView()
				.findViewById(R.id.upload_flag3);
		ImageView imageView4 = (ImageView) dialog.getWindow().getDecorView()
				.findViewById(R.id.upload_flag4);

		ProgressBar progressBar1 = (ProgressBar) dialog.getWindow()
				.getDecorView().findViewById(R.id.progress1);
		ProgressBar progressBar2 = (ProgressBar) dialog.getWindow()
				.getDecorView().findViewById(R.id.progress2);
		ProgressBar progressBar3 = (ProgressBar) dialog.getWindow()
				.getDecorView().findViewById(R.id.progress3);
		ProgressBar progressBar4 = (ProgressBar) dialog.getWindow()
				.getDecorView().findViewById(R.id.progress4);

		imageButtons.add(imageButton1);
		imageButtons.add(imageButton2);
		imageButtons.add(imageButton3);
		imageButtons.add(imageButton4);

		flagViews.add(imageView1);
		flagViews.add(imageView2);
		flagViews.add(imageView3);
		flagViews.add(imageView4);

		progressBars.add(progressBar1);
		progressBars.add(progressBar2);
		progressBars.add(progressBar3);
		progressBars.add(progressBar4);
		isFlag = new boolean[] { false, false, false, false };
//		button = (Button) dialog.getWindow().getDecorView()
//				.findViewById(R.id.sureButton);
//		button.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				if (isFlag[3]) {
//					
//					progressBars.clear();
//					imageButtons.clear();
//					flagViews.clear();
//					
//					ScreenManager screenManager = ScreenManager
//							.getScreenManager();
//					screenManager.popAllActivityExceptOne();
//					Intent intent = new Intent(context, HomeActivity.class);
//					context.startActivity(intent);
//				}
//
//			}
//		});

		for (j = 0; j < list.size() + 1; j++) {
			imageButtons.get(j).setOnClickListener(
					new SetButtonOnClickListener());
		}

		dialog.show();
		dialog.setCanceledOnTouchOutside(false);
		dialog.setCancelable(false);
	}

	public RapidlyPost(Context context, List<Map<String, Object>> listInfo,
			List<PhotoInfo> list) {
		super();
		this.context = context;
		this.listInfo = listInfo;
		this.list = list;
		gson = new Gson();
		application = (DemoApplication) context.getApplicationContext();
		showDialogProgress();
		new ImageUpLoading().execute();
	}

	class ImageUpLoading extends AsyncTask<HttpResponse, Integer, String> {

		@Override
		protected String doInBackground(HttpResponse... arg0) {
			HttpClient httpClient = HttpHelper.getInstance(context);
			HttpContext httpContext = new BasicHttpContext();
			HttpPost httpPost = new HttpPost(ContantsAddress.DOC_UPLOAD);

			try {
				CustomMultiPartEntity multipartContent = new CustomMultiPartEntity(
						new ProgressListener() {
							@Override
							public void transferred(long num) {
								publishProgress((int) ((num / (float) totalSize) * 100));
							}
						});

				// We use FileBody to transfer an image

				uploadNum = 0;
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
				multipartContent.addPart("picture", new FileBody(new File(list
						.get(uploadNum).getPicture())));//
				System.out.println("=list.get(uploadNum).getPicture())==>"+list
						.get(uploadNum).getPicture());
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
			progressBars.get(0).setProgress((int) progress[0]);
		}

		@Override
		protected void onPostExecute(String ui) {
			System.out.println("UI------------------------->" + ui);
			if (ui != null) {
				Response response = gson.fromJson(ui, Response.class);
				if (response.getCode() == 0) {
					isFlag[uploadNum] = true;
					Message msg = new Message();
					msg.what = PHOTO_SUCCEEDDATA;
					handler.sendMessage(msg);
					new ImageUpLoading1().execute();
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
			HttpPost httpPost = new HttpPost(ContantsAddress.DOC_UPLOAD);

			try {
				CustomMultiPartEntity multipartContent = new CustomMultiPartEntity(
						new ProgressListener() {
							@Override
							public void transferred(long num) {
								publishProgress((int) ((num / (float) totalSize) * 100));
							}
						});

				// We use FileBody to transfer an image
				uploadNum = 1;
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
				multipartContent.addPart("picture", new FileBody(new File(list
						.get(uploadNum).getPicture())));//

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
				if (response.getCode() == 0) {
					isFlag[uploadNum] = true;
					num = 1;
					Message msg = new Message();
					msg.what = PHOTO_SUCCEEDDATA;
					handler.sendMessage(msg);
					new VideoUpLoading().execute();
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

	class VideoUpLoading extends AsyncTask<HttpResponse, Integer, String> {

		@Override
		protected String doInBackground(HttpResponse... arg0) {
			HttpClient httpClient = HttpHelper.getInstance(context);
			HttpContext httpContext = new BasicHttpContext();
			HttpPost httpPost = new HttpPost(ContantsAddress.VIDEO_UPLOAD);

			try {
				CustomMultiPartEntity multipartContent = new CustomMultiPartEntity(
						new ProgressListener() {
							@Override
							public void transferred(long num) {
								publishProgress((int) ((num / (float) totalSize) * 100));
							}
						});

				// We use FileBody to transfer an image
				uploadNum = 2;
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
				
				multipartContent.addPart("videoInfo",
						new StringBody(list.get(uploadNum).getVideoInfo(),
								Charset.forName("utf-8")));// 诗句
				
				multipartContent.addPart("video", new FileBody(new File(list
						.get(uploadNum).getPicture())));//
				System.out.println("path==>"+list.get(uploadNum).getPicture());
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
				System.out.println("UI===>" + ui);
				Response1 response = gson.fromJson(ui, Response1.class);
				if (response.getCode() == 0) {
					num = 2;
					isFlag[uploadNum] = true;
					Message msg = new Message();
					msg.what = PHOTO_SUCCEEDDATA;
					handler.sendMessage(msg);
					new DataUpLoading().execute();
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

	class DataUpLoading extends AsyncTask<String, Integer, String> {

		@Override
		protected String doInBackground(String... arg0) {
			uploadNum = 3;
			HttpClient httpClient = HttpHelper.getInstance(context);

			HttpContext httpContext = new BasicHttpContext();
			HttpPost httpPost = new HttpPost(ContantsAddress.APPLY_QUICK_LOAN);
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
					num = 3;
					Message msg = new Message();
					msg.what = PHOTO_SUCCEED;
					handler.sendMessage(msg);
				} else if (response.getCode() == 403) {
					handler.sendEmptyMessage(403);
					Toast.makeText(context, response.getMsg(), Toast.LENGTH_LONG).show();
				} else {
					isFlag[uploadNum] = false;
					Message msg = new Message();
					msg.what = PHOTO_FAIL;
					msg.obj = response.getMsg();
					handler.sendMessage(msg);
					Toast.makeText(context, response.getMsg(), Toast.LENGTH_LONG).show();
				}
			} else {
				isFlag[uploadNum] = false;
				Message msg = new Message();
				msg.what = PHOTO_FAIL;
				handler.sendMessage(msg);
				Toast.makeText(context, "返回数据为空", Toast.LENGTH_LONG).show();
			}
		}
	}
	
    public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
    	if(keyCode == KeyEvent.KEYCODE_BACK) { //监控/拦截/屏蔽返回键
    		finish();
    		return false; 
		}
		return super.onKeyDown(keyCode, event);
    };
    
	Handler handler = new Handler() {

		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case PHOTO_SUCCEED:
				flagViews.get(uploadNum).setBackgroundResource(
						R.drawable.down_ok);
//				button.setBackgroundResource(R.drawable.dialog_neg2);
				
//	wz.9-29	视频不删除 缓存 
				FileUtils.deleteFile(new File(Environment
						.getExternalStorageDirectory(),
						RapidlyLoanInfoContents.videoPath));
			
				FileUtils.deleteFile(new File(RapidlyLoanInfoContents.comPhoto1Path));
				FileUtils.deleteFile(new File(RapidlyLoanInfoContents.comPhoto2Path));
				
				
				RapidlyLoanInfoContents.comPhoto1Path = null;
				RapidlyLoanInfoContents.comPhoto2Path = null;
				RapidlyLoanInfoContents.rapidlyLoanInfo = null;
				RapidlyLoanInfoContents.videoPath = null;
				System.out.println("+++>"+	RapidlyLoanInfoContents.videoPath);
				
				SharedPreferencesUtils preferUtil = new SharedPreferencesUtils(context);
				preferUtil.clearData();
				//------------存登陆标志信息
				preferUtil.setData("login_sign","login_sign" );
				
//				preferUtil.setData("loanFlag", Contents.response.getResult().getIdNo());
//				System.out.println("存flag,身份证号为："+Contents.response.getResult().getIdNo());
				//
//				DialogUtil.showDialogOneButton_tijiao(context, "  您的贷款申请已提交成功，我公司会查询您的人行征信用于贷款审核。如您逾期，我公司将会向中国人民银行上报。请珍视您的信用记录。", new OnClickListener() {
//					
//					@Override
//					public void onClick(View v) {
//						// TODO Auto-generated method stub
//						progressBars.clear();
//						imageButtons.clear();
//						flagViews.clear();
//						//wz10.29
//						RapidlyLoanInfoContents.videoPath=null;
//						ScreenManager screenManager = ScreenManager
//								.getScreenManager();
//						screenManager.popAllActivityExceptOne();
//						Intent intent = new Intent(context, HomeActivity.class);
//						context.startActivity(intent);
//					}
//				});
				
				progressBars.clear();
				imageButtons.clear();
				flagViews.clear();
				//wz10.29
				RapidlyLoanInfoContents.videoPath=null;
				Intent intent1 = new Intent(context, ShareActivity.class);
				intent1.putExtra("type_ch", "1");
				context.startActivity(intent1);
				break;
			case PHOTO_SUCCEED1:

				break;
			case PHOTO_SUCCEEDDATA:
				flagViews.get(num).setBackgroundResource(
						R.drawable.down_ok);

				break;
			case PHOTO_FAIL:
				System.out.println("======uploadNum====>" + uploadNum);
				progressBars.get(uploadNum).setProgress(0);
				flagViews.get(uploadNum).setBackgroundResource(
						R.drawable.down_flase);
				imageButtons.get(uploadNum).setBackgroundResource(
						R.drawable.uploadagain);
				isCan = false;
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

	class SetButtonOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (v == imageButtons.get(0)) {
				if (!isFlag[0]) {
					new ImageUpLoading().execute();
					imageButtons.get(uploadNum).setBackgroundResource(
							R.drawable.progress_photo);
				}
			} else if (v == imageButtons.get(1)) {
				if (!isFlag[uploadNum] && isFlag[uploadNum - 1]) {
					new ImageUpLoading1().execute();
					imageButtons.get(uploadNum).setBackgroundResource(
							R.drawable.progress_photo);
				}
			} else if (v == imageButtons.get(2)) {
				if (!isFlag[uploadNum] && isFlag[uploadNum - 1]) {
					new VideoUpLoading().execute();
					imageButtons.get(uploadNum).setBackgroundResource(
							R.drawable.video_photo);
				}
			} else if (v == imageButtons.get(3)) {
				if (!isFlag[uploadNum] && isFlag[uploadNum - 1]) {
					new DataUpLoading().execute();
					imageButtons.get(uploadNum).setBackgroundResource(
							R.drawable.progress_photo);
				}
			}
		}
	}
}
