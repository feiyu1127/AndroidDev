package com.victtech.cfapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.bumptech.glide.Glide;
import com.sunfusheng.glideimageview.GlideImageLoader;
import com.sunfusheng.glideimageview.GlideImageView;
import com.victtech.component.ImageDialog;
import com.victtech.tools.LogUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UploadActivity extends BaseActivity implements View.OnClickListener {
    ImageView cfPhoto;
    Uri photoUri;
    Button upload_btn;
    Button save_btn;
    private final int RESULT_CAMERA_IMAGE = 1;
    private final int RESULT_LOAD_IMAGE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        cfPhoto = findViewById(R.id.cf_photo);
        cfPhoto.setOnClickListener(this);

        upload_btn = findViewById(R.id.upload_btn);
        upload_btn.setOnClickListener(this);

        save_btn = findViewById(R.id.cToolbar_save);
        save_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cf_photo:
                ImageDialog.Builder build = new ImageDialog.Builder(this);
                ImageDialog dialog = build.create();
                dialog.show();
                dialog.setImageSrc(cfPhoto.getDrawable());
                break;
            case R.id.upload_btn:
//                openCamera();
                popupWindow();
                break;
            case R.id.cToolbar_save:
                if(photoUri!=null){

                }else{
                    UIToast("");
                }
                break;
        }
    }

    private void popupWindow(){
        View popupView = LayoutInflater.from(this).inflate(R.layout.popup_layout,null,false);

        Button cameraBtn = popupView.findViewById(R.id.take_photo);
        Button galleryBtn = popupView.findViewById(R.id.from_gallery);
        Button cancleBtn = popupView.findViewById(R.id.pop_cancel);

        //获取屏幕宽高
        int weight = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels*1/3;
        final PopupWindow window = new PopupWindow(popupView,weight,height,false);
//        window.setBackgroundDrawable(new ColorDrawable(getColor(R.color.colorPrimaryDark)));
        window.setOutsideTouchable(true);
        window.setTouchable(true);

        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera();
                window.dismiss();
            }
        });

        galleryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
                window.dismiss();
            }
        });

        cancleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });

        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1.0f;
                getWindow().setAttributes(lp);
            }
        });

        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;    //更改窗口透明度
        getWindow().setAttributes(lp);
        window.showAtLocation(popupView, Gravity.BOTTOM,0,50);
    }

    private void openCamera(){
        File outputImage = new File(getExternalCacheDir(),"cf_photo.jpg");
        try {
            if(outputImage.exists()){
                outputImage.delete();
            }
            outputImage.createNewFile();
            if(Build.VERSION.SDK_INT >= 24){
                photoUri = FileProvider.getUriForFile(UploadActivity.this
                        ,"cf",outputImage);
            }else{
                photoUri = Uri.fromFile(outputImage);
            }
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
            startActivityForResult(intent,RESULT_CAMERA_IMAGE);

        } catch (IOException e) {
            e.printStackTrace();
            UIToast("相机打开失败");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case RESULT_CAMERA_IMAGE:
                if(resultCode == RESULT_OK){
                    try {
                        LogUtil.d("photo Uri=========",""+photoUri);
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(photoUri));
                        cfPhoto.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        UIToast("相机文件显示失败");
                    }
                }
                break;
            case RESULT_LOAD_IMAGE:
                LogUtil.d("============",data.toString());
                if(data != null){
                    try {
                        photoUri = data.getData();
                        Bitmap bitmap = null;
                        bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(photoUri));
                        cfPhoto.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        UIToast("相机文件显示失败");
                    }

                }
                break;
        }
    }
}


