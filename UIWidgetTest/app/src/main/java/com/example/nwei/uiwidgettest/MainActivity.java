package com.example.nwei.uiwidgettest;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editText;
    private ImageView imageView;
    private Integer imgStatus = 0;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        editText = findViewById(R.id.edit_text);
        imageView = findViewById(R.id.image_view);
        progressBar = findViewById(R.id.progress_bar);

        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                //逻辑
//                String inputText = editText.getText().toString();
//                Toast.makeText(MainActivity.this,inputText,Toast.LENGTH_SHORT).show();
//                if(imgStatus == 1){
//                    imageView.setImageResource(R.drawable.img_1);
//                    imgStatus = 0;
//                }else{
//                    imageView.setImageResource(R.drawable.img_2);
//                    imgStatus = 1;
//                }


//                if(progressBar.getVisibility() == View.GONE){
//                    progressBar.setVisibility(View.VISIBLE);
//                }else{
//                    progressBar.setVisibility(View.GONE);
//                }


//                int progerss = progressBar.getProgress();
//                progerss = progerss + 10;
//                progressBar.setProgress(progerss);


//                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
//                dialog.setTitle("这是一个 dialog");
//                dialog.setMessage("一些重要的提示");
//                dialog.setCancelable(false);
//                dialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//
//                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//                dialog.show();


                ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setTitle("这是 progressDialog");
                progressDialog.setMessage("loding");
                progressDialog.setCancelable(true);
                progressDialog.show();

                break;
            default:
                break;
        }
    }
}
