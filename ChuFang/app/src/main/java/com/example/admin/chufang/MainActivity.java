package com.example.admin.chufang;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.chufang.entity.ErrorModel;
import com.example.admin.chufang.entity.LoginEntity;
import com.example.admin.chufang.entity.LoginModel;
import com.example.admin.chufang.gson.ParseJson;
import com.example.admin.chufang.httpRequest.HttpCallBackLisioner;
import com.example.admin.chufang.httpRequest.HttpUtil;

import org.json.JSONException;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "MainActivity";
    private DrawerLayout mDrawerLayout;
    private EditText loginUserName;
    private EditText loginPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navView = findViewById(R.id.nav_view);

        Button loginBtn = findViewById(R.id.login_btn);
        loginUserName = findViewById(R.id.login_username);
        loginPassword = findViewById(R.id.password);

        loginBtn.setOnClickListener(this);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true); //让导航按钮显示
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu); //设置按钮图标
        }


        navView.setCheckedItem(R.id.user_list); //默认选中
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.user_list:
//                        Toast.makeText(MainActivity.this,"user_list",Toast.LENGTH_SHORT).show();

                        break;
                    default:
                        break;
                }

                return  true;
            }
        });

    }


//    private void setIconEnable(Menu menu, boolean flag) {
//        //判断menu是否为空
//        if(menu != null) {
//            try {
//                //如果不为空,就反射拿到menu的setOptionalIconsVisible方法
//                Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
//                method.setAccessible(true);
//                method.invoke(menu, flag);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_btn: //用户登录
                  String telephone = loginUserName.getText().toString();
                  String password = loginPassword.getText().toString();

                  if(telephone == null || password == null || telephone.isEmpty() || password.isEmpty()){
                        Toast.makeText(this,"用户名或密码不能为空",Toast.LENGTH_SHORT).show();
                        break;
                  }

                  Map<String,String> loginData = new HashMap<>();
                  loginData.put("telephone",telephone);
                  loginData.put("password",password);

                  HttpUtil.postRequest("login", new HttpCallBackLisioner() {
                      @Override
                      public void onFinish(String requestString) {
                          showJsonResponse(requestString);
                      }

                      @Override
                      public void onError(Exception e) {
                            Toast.makeText(MainActivity.this,"网络错误",Toast.LENGTH_SHORT).show();
                      }
                  },loginData);
                break;
            default:
                break;
        }
    }


    private void showJsonResponse(final String jsonResponse){
        try {
            Object baseModel = ParseJson.parseJson(jsonResponse, LoginModel.class);

            if(baseModel instanceof ErrorModel){ //判断左边的对象是不是右边类的实体
                ErrorModel errorModel = (ErrorModel) baseModel;
                showToastInThread(errorModel.getMessage());

            }else{
                LoginModel loginModel = (LoginModel) baseModel;
                final LoginEntity loginEntity = loginModel.getData();

                //修改 header 导航的显示信息
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView username = findViewById(R.id.username);
                        TextView mail = findViewById(R.id.mail);
                        username.setText(loginEntity.getName());
                        mail.setText(loginEntity.getTelephone());
                    }
                });

                Log.d(TAG, "登录成功" + loginEntity.getName());
            }
        } catch (JSONException e) {
            showToastInThread("返回解析json错误");
        }

    }


    private void showToastInThread(final String msgToast){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this,msgToast,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
//        setIconEnable(menu,true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.access_baidu:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.baidu.com"));
                startActivity(intent);
                break;
            case R.id.exit_sys:
                Toast.makeText(this,"sdddd",Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.add_user:
                Intent addUserIntent = new Intent(MainActivity.this,AddUserActivity.class);
                startActivity(addUserIntent);
                break;
            default:
                break;
        }
        return true;
    }
}
