package com.example.richard.webview;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.richard.entity.BaseModel;
import com.example.richard.entity.ErrorModel;
import com.example.richard.entity.LoginEntity;
import com.example.richard.entity.LoginModel;
import com.example.richard.http.HttpCallBackLisioner;
import com.example.richard.http.HttpUtil;
import com.example.richard.http.ParseJson;
import com.example.richard.tools.RememberPassword;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetworkConnect extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "NetworkConnect";
    Button sendButton;
    TextView textView;
    CheckBox checkBox;
    EditText telText;
    EditText psdText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_connect);
        setHeaderContent("网络请求测试页面");

        sendButton = findViewById(R.id.send_request);
        sendButton.setOnClickListener(this);
        textView = findViewById(R.id.response_text);
        checkBox = findViewById(R.id.remember_checkbox);

        telText = findViewById(R.id.telephone);
        psdText = findViewById(R.id.password);

        RememberPassword rememberPassword = new RememberPassword(NetworkConnect.this);
        Map<String,String> login_info = rememberPassword.load();
        if(!login_info.get("name").equals("") && !login_info.get("psd").equals("")){
            telText.setText(login_info.get("name"));
            psdText.setText(login_info.get("psd"));
            checkBox.setChecked(true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send_request:
//                sendRequestWithHttpURLConnection();
//                sendOkHttpRequest();
                postOkHttpRequest();

                break;
            default:
        }
    }



    private void sendRequestWithHttpURLConnection(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try{

                    URL url = new URL("http://www.163.com");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while((line = reader.readLine()) != null){
                        response.append(line);
                    }
                    showResponse(response.toString());
                }catch (Exception e){
                    e.printStackTrace();
                } finally {
                    if(reader != null){
                        try{
                            reader.close();
                        } catch (IOException ex){
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

    private void showResponse(final String response){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(response);
                RememberPassword rememberPassword = new RememberPassword(NetworkConnect.this);
                if(checkBox.isChecked()){
                    rememberPassword.remember(telText.getText().toString(),psdText.getText().toString());
                }else{
                    rememberPassword.forgot();
                }
            }
        });
    }

    private void showToast(final String message){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(NetworkConnect.this,message,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showJsonResponse(final String jsonResponse){

        try {
            Object baseModel = ParseJson.parseJson(jsonResponse,LoginModel.class);
            Log.d(TAG, "showJsonResponse: ++++++"+(baseModel instanceof ErrorModel));
            if(baseModel instanceof ErrorModel){
                ErrorModel errorModel = (ErrorModel)baseModel;
                showToast(errorModel.getMessage());

            }else{
                LoginModel loginModel = (LoginModel)baseModel;
                LoginEntity loginEntity = (LoginEntity) loginModel.getData();
                showResponse("name:"+String.valueOf(loginEntity.getName()) );
                showToast("登陆成功");
            }
        } catch (JSONException e) {
            showToast("出现内部错误，稍后再试");
        }
    }



    private void postOkHttpRequest(){


        if(telText.getText().toString().equals("") || psdText.getText().toString().equals("")){
            Toast.makeText(NetworkConnect.this,"用户名或密码不能为空",Toast.LENGTH_SHORT).show();
        }else{
            Map<String,String> params = new HashMap<String, String>() ;
            params.put("telephone",telText.getText().toString());
            params.put("password",psdText.getText().toString());
            HttpUtil.postRequest("login", new HttpCallBackLisioner() {
                @Override
                public void onFinish(String requestString) {
                    showJsonResponse(requestString);
                }

                @Override
                public void onError(Exception e) {
                    Toast.makeText(NetworkConnect.this,"网络访问错误",Toast.LENGTH_SHORT).show();
                }
            },params);
        }


    }
}
