package com.nwei.shen.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.nwei.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

/**
 * Created by admin on 2018/2/6.
 */

public class ProductIntroduceFragment extends Fragment implements View.OnClickListener{

    View view;
    Context mContext;
    ImageView uploadImg;

    Button takePhotoBtn;
    Button bt_album;
    Button bt_camera;
    Button bt_cancle;

    private final int TAKE_PHOTO = 1;
    private final int OPEN_ALBUM = 2;
    Uri imageUri;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getActivity();
        view = inflater.inflate(R.layout.fragment_product_introduce,container,false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        uploadImg = view.findViewById(R.id.uploadImg);
        takePhotoBtn = view.findViewById(R.id.takePhotoBtn);

        takePhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("测试上传图片", "onClick: ");
                popupWindow();
            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (getId()){
            default:
                break;
        }
    }

    private void popupWindow(){
        View popupView = LayoutInflater.from(mContext).inflate(R.layout.popup3_window,null,false);


        bt_album = popupView.findViewById(R.id.btn_pop_album);
        bt_camera = popupView.findViewById(R.id.btn_pop_camera);
        bt_cancle = popupView.findViewById(R.id.btn_pop_cancel);

        //获取屏幕宽高
        int weight = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels*1/3;
        final PopupWindow window = new PopupWindow(popupView,weight,height,false);
        window.setOutsideTouchable(true);
        window.setTouchable(true);

        bt_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera();
                window.dismiss();
            }
        });

        bt_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, OPEN_ALBUM);
                window.dismiss();
            }
        });

        bt_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });

        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                lp.alpha = 1.0f;
                getActivity().getWindow().setAttributes(lp);
            }
        });

        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.5f;    //更改窗口透明度
        getActivity().getWindow().setAttributes(lp);
        window.showAtLocation(popupView, Gravity.BOTTOM,0,50);
    }


    /**
     * 打开相机拍照
     */
    private void openCamera(){
        File outputImage = new File(mContext.getExternalCacheDir(),"output_image.jpg");
        try {
            if(outputImage.exists()){
                outputImage.delete();
            }
            outputImage.createNewFile();

        } catch (IOException e) {
            e.printStackTrace();
        }

        if(Build.VERSION.SDK_INT >= 24){
            imageUri = FileProvider.getUriForFile(mContext,"com.take.image",outputImage);
        }else{
            imageUri = Uri.fromFile(outputImage);
        }

        //启动相机
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        startActivityForResult(intent,TAKE_PHOTO);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case TAKE_PHOTO:
                if(requestCode == RESULT_OK){
                    try {
                        Log.d("拍照相机", imageUri+"");
                        //将拍摄的照片显示出来
                        Bitmap bitmap = BitmapFactory.decodeStream(mContext.getContentResolver().openInputStream(imageUri));
                        Log.d("图片bitmap", bitmap.toString());
                        uploadImg.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                Log.d("没有走进拍照", "onActivityResult: ");
                break;
        }
    }
}
