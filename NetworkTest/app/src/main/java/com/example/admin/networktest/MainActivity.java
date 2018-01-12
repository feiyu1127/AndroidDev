package com.example.admin.networktest;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView responseTest;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendResponse = findViewById(R.id.send_request);
        responseTest = findViewById(R.id.response_text);
        sendResponse.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send_request:
//                sendRequestWithHttpUrlConnection();
                sendRequestWithOkHttp();
                break;
            default:
                break;
        }
    }


    private void sendRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("https://chufang-api.victtech.com/api/v1/androidApiTest")
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();

                    parseJSONWithGson(responseData);
//                    parseJSONWithJSONObject(responseData);
//                    parseXMLWithPull(responseData);
//                    showResponse(responseData); //showResponse 将服务器放回的数据显示到界面上

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseJSONWithGson(String jsonData) {
        Gson gson = new Gson();
        List<App> appList = gson.fromJson(jsonData,new TypeToken<List<App>>(){}.getType());
        for (App app:appList){
            Log.d(TAG, "id is " + app.getId());
            Log.d(TAG, "name is " + app.getName());
            Log.d(TAG, "version is " + app.getVersion());
        }
    }


    private void parseJSONWithJSONObject(String jsonData){
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for(int i = 0;i < jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String version = jsonObject.getString("version");
                Log.d(TAG, "id is " + id);
                Log.d(TAG, "name is " + name);
                Log.d(TAG, "version is " + version);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    
    private void parseXMLWithPull(String xmlData){
        try{
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));
            int eventType = xmlPullParser.getEventType();
            String id = "";
            String name = "";
            String version = "";
            while (eventType != xmlPullParser.END_DOCUMENT){
                String nodeName = xmlPullParser.getName();
                switch (eventType){
                    //开始解析某个节点
                    case XmlPullParser.START_TAG:
                        if ("id".equals(nodeName)) {
                            id = xmlPullParser.nextText();
                        } else if ("name".equals(nodeName)) {
                            name = xmlPullParser.nextText();
                        } else if ("version".equals(nodeName)) {
                            version = xmlPullParser.nextText();
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if("app".equals(nodeName)){
                            Log.d(TAG, "id is " + id);
                            Log.d(TAG, "name is " + name);
                            Log.d(TAG, "version is " + version);
                        }
                        break;
                    default:
                        break;
                }

                eventType = xmlPullParser.next();
            }
            
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    private void sendRequestWithHttpUrlConnection(){
        //开启线程发送网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("https://www.baidu.com");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();

                    //对获取到的流入进行读取
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuffer response = new StringBuffer();
                    String line;
                    while ((line = reader.readLine()) != null){ //对服务器返回的流进行读取
                        response.append(line);
                    }
                    showResponse(response.toString());
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if(reader != null){
                        try {
                            reader.close();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                    if(connection != null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }


    private void showResponse(final String response){
        runOnUiThread(new Runnable() { // android 不允许在子线程中进行 UI 操作,利用 runOnUiThread 切换到主线程
            @Override
            public void run() {
                //进行 Ui 操作,将结果显示在页面
                responseTest.setText(response);
            }
        });
    }


}
