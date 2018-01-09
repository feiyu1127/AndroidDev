package com.example.richard.webview;


import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MediaActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "MediaActivity";
    private ImageView pciture;
    private Uri imageUri;

    private final int TAKEPHOTO = 1;
    private final int CHOOSEPHOTO = 2;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case TAKEPHOTO:
                if(resultCode == RESULT_OK){
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        pciture.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);
        final Intent intent = getIntent();
        int flag = intent.getIntExtra("flag",0);
        Log.d(TAG, "onCreate: +++"+flag);
        TextView textView = findViewById(R.id.notify_txt);
        textView.setText(String.valueOf(flag));

        Button takePhoto = findViewById(R.id.take_photo);
        pciture = findViewById(R.id.picture);
        takePhoto.setOnClickListener(this);

        Button getGallery = findViewById(R.id.get_gallery);
        getGallery.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.take_photo:
                takePhoto();
                break;
            case R.id.get_gallery:
                getGallery();
                break;
            default:
        }
    }

    private void takePhoto(){
        //创建file, 用于存储牌照后的照片
        File outputImage = new File(getExternalCacheDir(),"output_image.jpg");

        try {
            if(outputImage.exists()){
                outputImage.delete();
            }
            outputImage.createNewFile();
            Log.d(TAG, "onClick: +++"+outputImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(Build.VERSION.SDK_INT >= 24){
            imageUri = FileProvider.getUriForFile(MediaActivity.this
                    ,"com.example.richard.webview.fileprovider",outputImage);
        }else{
            imageUri = Uri.fromFile(outputImage);
        }
        //启动相机
        Intent intent1 = new Intent("android.media.action.IMAGE_CAPTURE");
        intent1.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        startActivityForResult(intent1,TAKEPHOTO);
    }

    private void getGallery(){
        //申请动态权限
        if(ContextCompat.checkSelfPermission(MediaActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MediaActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }else{

        }
    }

}
