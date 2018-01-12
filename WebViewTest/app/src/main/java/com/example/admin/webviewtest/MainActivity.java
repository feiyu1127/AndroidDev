package com.example.admin.webviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true); //设置浏览器的属性,支持 js 脚本
        webView.setWebViewClient(new WebViewClient()); //创建一个 WebView 实例,将网页在 webview 中显示,而不是打开系统浏览器
        webView.loadUrl("https://www.baidu.com");

    }
}
