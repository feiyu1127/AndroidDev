package com.yucheng.apfx.net;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yucheng.byxf.comm.ScreenManager;
import com.yucheng.byxf.mini.domain.MiniPersonInfo;
import com.yucheng.byxf.mini.ui.MyLoginActivity;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.ReflectionUtils;
import com.yucheng.byxf.msg.file.DocInfo;
import com.yucheng.byxf.msg.file.FileInfoResponse;

/**
 * 单例模式 HttpClient
 * 
 * @author neo
 */
public class HttpHelper extends DefaultHttpClient {
  private static DefaultHttpClient ins = null;
  private static Gson gson = null;
  private static Context ctx;
  public static long length = 0;
  private HttpResponse rsp = null;
  private Handler hdl = new Handler() {
    @Override
    public void handleMessage(Message msg) {
      super.handleMessage(msg);
      switch (msg.what) {
      case 0:
        Toast.makeText(ctx, msg.getData().getString("error"), Toast.LENGTH_SHORT).show();
        rsp = null;
        break;
      case 403:
        Toast.makeText(ctx, "会话超时，请重新登录", Toast.LENGTH_SHORT).show();
        Contents.IS_LOGIN = false;
        Contents.username = "";
        Contents.custInfoResponseResult = null;
        Contents.response = null;
        ScreenManager screenManager = ScreenManager.getScreenManager();
        screenManager.popAllActivityExceptOne();
        MiniPersonInfo.clearAll();
        Intent intent = new Intent();
        intent.putExtra("openHome", "openHome");
        intent.setClass(ctx, MyLoginActivity.class);
        ctx.startActivity(intent);
        break;
      case 300:
        Toast.makeText(ctx, "Json解析异常", Toast.LENGTH_SHORT).show();
        break;
      default:
        break;
      }
    }
  };

  /**
   * 私有构造函数，仅供内部调用
   */
  private HttpHelper() {
    // 支持Https连接
    SSLSocketFactory sf = null;
    try {
      KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
      trustStore.load(null, null);
      sf = new Ssl.SocketTlsFactory(trustStore);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
    BasicHttpParams params = new BasicHttpParams();
    // 从连接池中取连接的超时时间
    ConnManagerParams.setTimeout(params, 10000);
    // 连接超时
    HttpConnectionParams.setConnectionTimeout(params, 10000);
    // 请求超时
    HttpConnectionParams.setSoTimeout(params, 30000);
    HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
    HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
    SchemeRegistry registry = new SchemeRegistry();
    registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
    registry.register(new Scheme("https", sf, 443));
    ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);
    ins = new DefaultHttpClient(ccm, params);
    gson = new Gson();
  }

  /**
   * 获取BobcfcHttpClient实例
   * 
   * @return
   */
  public static synchronized HttpHelper getInstance(Context context) {
    ctx = context;
    if (null == ins) {
      ins = new HttpHelper();
    }
    return (HttpHelper)ins;
  }

  /**
   * 提交get方式http请求
   * 
   * @param api
   *          服务接口
   * @param arg
   *          请求参数
   * @return 响应结果
   */
  public String get(String api, List<NameValuePair> arg) {
    if (null != arg && !arg.isEmpty()) {
      api = api + "?" + URLEncodedUtils.format(arg, "UTF-8");
    }
    return executeForResult(new HttpGet(api));
  }

  /**
   * 提交get方式http请求
   * 
   * @param api
   *          服务接口
   * @param arg
   *          请求参数
   * @param rsp
   *          响应报文
   * @return 响应结果
   */
  public <T> T get(String api, List<NameValuePair> arg, Class<T> rsp) {
    T result = null;
    String ret = get(api, arg);
    if (null != ret && 0 != ret.trim().length()) {
      result = (T)gson.fromJson(ret, rsp);
    }
    return result;
  }

  /**
   * 提交post方式http请求
   * 
   * @param api
   *          服务接口
   * @param arg
   *          请求参数
   * @return 响应结果
   */
  public String post(String api, List<NameValuePair> arg) {
    String result = null;
    try {
      HttpPost request = new HttpPost(api);
      if (null != arg && !arg.isEmpty()) {
        request.setEntity(new UrlEncodedFormEntity(arg, HTTP.UTF_8));
      }
      result = executeForResult(request);
    }
    catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return result;
  }

  /**
   * 提交post方式http请求
   * 
   * @param api
   *          服务接口
   * @param arg
   *          请求参数
   * @param rsp
   *          响应报文
   * @return 响应结果
   */
  public <T> T post(String api, List<NameValuePair> arg, Class<T> rsp) {
    T result = null;
    String ret = post(api, arg);
    if (null != ret && 0 != ret.trim().length()) {
      try {
        result = (T)gson.fromJson(ret, rsp);
        result = exceptionCode(result);
      }
      catch (Exception e) {
        e.printStackTrace();
        hdl.sendEmptyMessage(300);
        result = null;
      }
    }
    return result;
  }

  /**
   * 提交post方式http请求
   * 
   * @param api
   *          http服务URL
   * @param arg
   *          请求参数
   * @return 响应结果
   */
  public String post(String api, Map<String, ContentBody> arg) {
    HttpPost request = new HttpPost(api);
    MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
    for (Entry<String, ContentBody> entry : arg.entrySet()) {
      entity.addPart(entry.getKey(), entry.getValue());
    }
    length = entity.getContentLength();
    request.setEntity(entity);
    return executeForResult(request);
  }

  /**
   * 提交post方式http请求
   * 
   * @param api
   *          服务接口
   * @param arg
   *          请求参数
   * @param rsp
   *          响应报文
   * @return 响应结果
   */
  public <T> T post(String api, Map<String, ContentBody> arg, Class<T> rsp) {
    T result = null;
    String ret = post(api, arg);
    if (ret != null && 0 != ret.trim().length()) {
      try {
        result = (T)gson.fromJson(ret, rsp);
      }
      catch (Exception e) {
        System.out.println(e.getMessage());
        result = null;
      }
    }
    return result;
  }

  /**
   * 图片上传
   * 
   * @param api
   * @param arg
   */
  public FileInfoResponse post(String api, HashMap<String, Object> arg) {
    // FileInfoResponse response = null;
    ArrayList<DocInfo> mArrayLists = (ArrayList<DocInfo>)arg.get("list");
    Map<String, ContentBody> params = new HashMap<String, ContentBody>();
    try {
      for (int i = 0; i < mArrayLists.size(); i++) {
        DocInfo mDocInfo = mArrayLists.get(i);
        params.put("docList[" + i + "].file", new FileBody(new File(mDocInfo.getFilePath())));
        params.put("docList[" + i + "].docName", new StringBody(mDocInfo.getDocName()));
        params.put("docList[" + i + "].filePath", new StringBody(mDocInfo.getFilePath()));
        params.put("docList[" + i + "].bizNo", new StringBody(mDocInfo.getBizNo()));
      }
      params.put("bizNo", new StringBody((String)arg.get("bizNo")));
    }
    catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    // put.addPart("bizNo", new StringBody((String) dataMaps.get("bizNo")));
    // httpPost.setEntity(entity);
    FileInfoResponse response = post(api, params, FileInfoResponse.class);
    return response;
  }

  public <T> T exceptionCode(Object obj) throws Exception {
    Integer code = (Integer)ReflectionUtils.getFieldValue(obj, "code");
    if (code == 403) {
      hdl.sendEmptyMessage(403);
      return null;
    }
    return (T)obj;
  }

  /**
   * 执行http请求
   * 
   * @param request
   * @return 响应结果
   */
  private String executeForResult(HttpUriRequest request) {
    String result = null;
    try {
      // 接口参数
      HttpParams params = ins.getParams();
      // 连接超时
      HttpConnectionParams.setConnectionTimeout(params, 30000);
      // 请求超时
      HttpConnectionParams.setSoTimeout(params, 30000);
      rsp = ins.execute(request);
      int status = rsp.getStatusLine().getStatusCode();
      if (HttpStatus.SC_OK == status) {
        // 获取响应结果
        result = toString(rsp.getEntity());
      }
      else {
        // 进行异常处理
        System.out.println("响应状态：" + status);
        Bundle bdl = new Bundle();
        bdl.putString("error", String.valueOf(rsp.getStatusLine().getStatusCode()));
        Message msg = new Message();
        msg.setData(bdl);
        msg.what = 0;
        hdl.sendMessage(msg);
      }
      System.out.println("响应结果：" + result);
    }
    catch (NullPointerException e) {
      e.printStackTrace();
    }
    catch (ClientProtocolException e) {
      e.printStackTrace();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  /**
   * 将HttpEntity转化为字符串
   * 
   * @param entity
   * @return
   * @throws IOException
   */
  private String toString(HttpEntity entity) throws IOException {
    byte[] arr = EntityUtils.toByteArray(entity);
    return new String(arr, 0, arr.length, "UTF-8");
  }

  /**
   * @param uri
   * @return
   */
  public static HttpURLConnection download(String uri) {
    HttpURLConnection urlConn = null;
    try {
      if (uri.startsWith("https")) {
        SSLContext sc;
        sc = SSLContext.getInstance("TLS");
        sc.init(null, new TrustManager[] { new Ssl.MyTrustManager() }, new SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        HttpsURLConnection.setDefaultHostnameVerifier(new Ssl.MyHostnameVerifier());
        urlConn = (HttpsURLConnection)new URL(uri).openConnection();
      }
      else {
        urlConn = (HttpURLConnection)new URL(uri).openConnection();
      }
      urlConn.setConnectTimeout(1000 * 10);
      urlConn.setReadTimeout(1000 * 10);
      urlConn.setDoInput(true);
      urlConn.setDoOutput(true);
      urlConn.connect();
    }
    catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    catch (KeyManagementException e) {
      e.printStackTrace();
    }
    catch (MalformedURLException e) {
      e.printStackTrace();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    return urlConn;
  }
}
