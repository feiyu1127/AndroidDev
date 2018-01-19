package com.yucheng.byxf.mini.easyloan.ui;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import com.google.gson.Gson;
import com.yucheng.byxf.http.APIService.NetExceptionCallBack;
import com.yucheng.byxf.http.BobHttpClient;
import com.yucheng.byxf.msg.file.DocInfo;
import com.yucheng.byxf.msg.file.FileInfoResponse;
import com.yucheng.byxf.msg.stat.TradeStreamNum;
import com.yucheng.byxf.util.LogManager;

/**
 * 图片处理
 * 
 * @author danny
 * 
 */
public class ImageDispose {
	private static final String _TAG = "ImageDispose";


    /**
     * 保存图片为JPEG
     * @param bitmap
     * @param appCode
     * @return
     */
	public static String saveJPGE_After(Bitmap bitmap, String appCode) {
		String path = getPath(appCode);
		// Log.i("ShootPictureActivity", path);
		File file = new File(path);
		try {
			FileOutputStream out = new FileOutputStream(file);
			if (bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)) {
				out.flush();
				out.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

    /**
     *  保存图片为JPEG
     * @param bitmap
     * @param appCode
     * @param flag
     * @return
     */
	public static String saveJPGE_After(Bitmap bitmap, String appCode,
			String flag) {
		String path = getPath(appCode, flag);
		// Log.i("ShootPictureActivity", path);
		File file = new File(path);
		try {
			FileOutputStream out = new FileOutputStream(file);
			if (bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)) {
				out.flush();
				out.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

	/**
	 * 获取文件保存的路径
	 * 
	 * @return
	 */
	private static String getPath(String appCode) {
		File DatalDir = Environment.getExternalStorageDirectory();
		File myDir = new File(DatalDir, "/DCIM/");
		myDir.mkdirs();
		String mDirectoryname = DatalDir.toString() + "/DCIM/";
		String fileName = appCode + TradeStreamNum.getStreamNum();
		return mDirectoryname + fileName + ".jpg";
	}

	/**
	 * 获取文件保存的路径
	 * @return
	 */
	public static String getPath(String appCode, String flag) {
		File DatalDir = Environment.getExternalStorageDirectory();
		File myDir = new File(DatalDir, "/DCIM/");
		myDir.mkdirs();
		String mDirectoryname = DatalDir.toString() + "/DCIM/";
		String fileName = appCode + flag;
		// LogManager.i(ImageDispose.class, fileName);
		return mDirectoryname + fileName + ".jpg";
	}

	/**
	 * 上传文件
	 * 
	 * @param url
	 * @param fileName
	 */
	public static FileInfoResponse uploadFile(String url, String fileName,
			Context context) {
		FileInfoResponse response = null;
		HttpPost httppost = new HttpPost(url);
		File aFile = new File(fileName);
		FileEntity fileEty = new FileEntity(aFile, "binary/octet-stream");
		httppost.setEntity(fileEty);
		httppost.addHeader("filename", aFile.getName());
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("filename", aFile.getName()));
		try {
			if (!BobHttpClient.checkHasNet(context)) {
				return null;
			}
			httppost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// String jsonString = BobHttpClient.getHttpResponse1(httppost, context,
		// new NetExceptionCallBack() {
		//
		// @Override
		// public void netExceptionCallback(int flag) {
		// // TODO Auto-generated method stub
		//
		// }
		// });
		// LogManager.i(ImageDispose.class, jsonString);
		// if (!"out".equals(jsonString) && !"error".equals(jsonString)
		// && !"not".equals(jsonString)) {
		// response = new Gson().fromJson(jsonString, FileInfoResponse.class);
		// }
		try {

			HttpResponse httpResponse = BobHttpClient.getHttpClientInstance()
					.execute(httppost);
			// 获取响应服务器的数据
			if (httpResponse != null) {
				if (httpResponse.getStatusLine().getStatusCode() == 200) {
					byte[] jsonByte = EntityUtils.toByteArray(httpResponse
							.getEntity());
					String jsonString = new String(jsonByte, 0,
							jsonByte.length, "UTF-8");
					// Log.i(_TAG, "jsongString:" + jsonString);
					LogManager.i(ImageDispose.class, jsonString);
					response = new Gson().fromJson(jsonString,
							FileInfoResponse.class);
					// Log.i(_TAG,
					// "code:" + response.getCode() + "msg:"
					// + response.getMsg());
				} else {
					// Log.i(_TAG, "returnCode:"
					// + httpResponse.getStatusLine().getStatusCode());
				}
			}

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			BobHttpClient.isTimeOut(context, new NetExceptionCallBack() {

				@Override
				public void netExceptionCallback(int flag) {
					// TODO Auto-generated method stub

				}
			});
		}
		return response;
	}

	/**
	 * 图片上传
	 * 
	 * @param url
	 * @param nameValuePairs
	 */
	public static FileInfoResponse post(String url,
			HashMap<String, Object> dataMaps) {
		FileInfoResponse response = null;
		// Log.i(_TAG, "url:"+url+"size:"+nameValuePairs.size());
		HttpClient httpClient = BobHttpClient.getHttpClientInstance();
		HttpContext localContext = new BasicHttpContext();
		HttpPost httpPost = new HttpPost(url);
		try {
			MultipartEntity entity = new MultipartEntity(
					HttpMultipartMode.BROWSER_COMPATIBLE);
			ArrayList<DocInfo> mArrayLists = (ArrayList<DocInfo>) dataMaps
					.get("list");
			LogManager.i(ImageDispose.class,
					"mArrayLists size==" + mArrayLists.size());
			for (int i = 0; i < mArrayLists.size(); i++) {
				DocInfo mDocInfo = mArrayLists.get(i);
				entity.addPart("docList[" + i + "].file", new FileBody(
						new File(mDocInfo.getFilePath())));
				entity.addPart("docList[" + i + "].docName", new StringBody(
						mDocInfo.getDocName()));
				entity.addPart("docList[" + i + "].filePath", new StringBody(
						mDocInfo.getFilePath()));
				entity.addPart("docList[" + i + "].bizNo", new StringBody(
						mDocInfo.getBizNo()));
			}
			entity.addPart("bizNo",
					new StringBody((String) dataMaps.get("bizNo")));
			// if (dataMaps.get("docList[0].file") != null) {
			// entity.addPart("docList[0].file", new FileBody(new File(
			// (String) dataMaps.get("docList[0].file"))));
			// entity.addPart("docList[0].docName", new StringBody(
			// (String) dataMaps.get("docList[0].docName")));
			// entity.addPart("docList[0].filePath", new StringBody(
			// (String) dataMaps.get("docList[0].filePath")));
			// entity.addPart("docList[0].bizNo", new StringBody(
			// (String) dataMaps.get("bizNo")));
			// }
			// if (dataMaps.get("docList[1].file") != null) {
			// entity.addPart("docList[1].file", new FileBody(new File(
			// (String) dataMaps.get("docList[1].file"))));
			// entity.addPart("docList[1].docName", new StringBody(
			// (String) dataMaps.get("docList[1].docName")));
			// entity.addPart("docList[1].filePath", new StringBody(
			// (String) dataMaps.get("docList[1].filePath")));
			// LogManager.i(
			// ImageDispose.class,
			// "dataMaps.getdocList[0].bizNo="
			// + dataMaps.get("docList[0].bizNo"));
			// entity.addPart("docList[1].bizNo", new StringBody(
			// (String) dataMaps.get("bizNo")));
			// }
			// if (dataMaps.get("docList[2].file") != null) {
			// entity.addPart("docList[2].file", new FileBody(new File(
			// (String) dataMaps.get("docList[2].file"))));
			// entity.addPart("docList[2].docName", new StringBody(
			// (String) dataMaps.get("docList[2].docName")));
			// entity.addPart("docList[2].filePath", new StringBody(
			// (String) dataMaps.get("docList[2].filePath")));
			// entity.addPart("docList[2].bizNo", new StringBody(
			// (String) dataMaps.get("bizNo")));
			// }
			// if (dataMaps.get("docList[3].file") != null) {
			// entity.addPart("docList[3].file", new FileBody(new File(
			// (String) dataMaps.get("docList[3].file"))));
			// entity.addPart("docList[3].docName", new StringBody(
			// (String) dataMaps.get("docList[3].docName")));
			// entity.addPart("docList[3].filePath", new StringBody(
			// (String) dataMaps.get("docList[3].filePath")));
			// entity.addPart("docList[3].bizNo", new StringBody(
			// (String) dataMaps.get("bizNo")));
			// }
			// if (dataMaps.get("docList[4].file") != null) {
			// entity.addPart("docList[4].file", new FileBody(new File(
			// (String) dataMaps.get("docList[4].file"))));
			// entity.addPart("docList[4].docName", new StringBody(
			// (String) dataMaps.get("docList[4].docName")));
			// entity.addPart("docList[4].filePath", new StringBody(
			// (String) dataMaps.get("docList[4].filePath")));
			// entity.addPart("docList[4].bizNo", new StringBody(
			// (String) dataMaps.get("bizNo")));
			// }

			httpPost.setEntity(entity);
			HttpResponse httpResponse = httpClient.execute(httpPost,
					localContext);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				byte[] jsonByte = EntityUtils.toByteArray(httpResponse
						.getEntity());
				String jsonString = new String(jsonByte, 0, jsonByte.length,
						"UTF-8");
				// Log.i(_TAG, jsonString);
				response = new Gson().fromJson(jsonString,
						FileInfoResponse.class);
			} else {
				// Log.i(_TAG,
				// "returnCode:"+httpResponse.getStatusLine().getStatusCode());
			}
		} catch (IOException e) {
//			System.out.println("异常。。。。。。。。。。。。。。。。。。。");
			LogManager.i(ImageDispose.class, "异常。。。。。。。。。。。。。。。。。。。");
			e.printStackTrace();
		}
		return response;
	}

	/**
	 * 将photo转换成可以用来存储的byte[]类型
	 * 
	 * @return
	 */
	public static byte[] getPicture(Bitmap bitmap) {
		if (bitmap == null) {
			return null;
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
		return baos.toByteArray();
	}
}
