package com.example.richard.webview;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.richard.tools.ActivityContent;

import java.util.Timer;
import java.util.TimerTask;
import java.util.zip.Inflater;

/**
 * Created by Richard on 2018/1/2.
 */

public class Header extends LinearLayout implements View.OnClickListener {
    private Button backBtn;
    private TextView headerTxt;
    public Header(Context context, @Nullable AttributeSet attrs) {
        super(context,attrs);
        LayoutInflater.from(context).inflate(R.layout.activity_header,this);
        backBtn = findViewById(R.id.header_back);
        headerTxt = findViewById(R.id.header_txt);
        backBtn.setOnClickListener(this);

        Button exit_btn = findViewById(R.id.header_exit);
        exit_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.header_back:
                ((Activity)v.getContext()).finish();
                break;
            case R.id.header_exit:
                final AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
                alert.setTitle("");
                alert.setMessage("确定退出系统吗？");
                alert.setCancelable(true);
                alert.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alert.setPositiveButton("退出", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityContent.finishAll();
                    }
                });
                alert.show();
                break;
            default:
        }

    }

    public void setheaderTxt(String header_txt){
        headerTxt.setText(header_txt);
    }
}
