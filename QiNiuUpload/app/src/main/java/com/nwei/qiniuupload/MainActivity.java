package com.nwei.qiniuupload;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.nwei.qiniuUtils.Upload2QiNiu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    Context mContext;
    ImageView uploadImg;

    Button selectPicBtn;
    Button upload2QNBtn;
    Button albumBtn;
    Button cameraBtn;
    Button cancleBtn;

    private Uri imageUri;

    private final int TAKE_PHOTO = 1; //拍摄照片
    private final int CHOOSE_PHOTO = 2; //选择本地图片

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        selectPicBtn = findViewById(R.id.selectPicBtn);
        uploadImg = findViewById(R.id.uploadImg);
        upload2QNBtn = findViewById(R.id.upload2QNBtn);

        selectPicBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.selectPicBtn:
                popupWindow();
                break;
            default:
                break;
        }
    }

    private void popupWindow() {
        View popupView = LayoutInflater.from(mContext).inflate(R.layout.popup3_window, null, false);

        albumBtn = popupView.findViewById(R.id.btn_pop_album);
        cameraBtn = popupView.findViewById(R.id.btn_pop_camera);
        cancleBtn = popupView.findViewById(R.id.btn_pop_cancel);

        //获取屏幕宽高
        int weight = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels * 1 / 3;
        final PopupWindow window = new PopupWindow(popupView, weight, height, false);
        window.setOutsideTouchable(true);
        window.setTouchable(true);


        //相册选择
        albumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, CHOOSE_PHOTO);
                window.dismiss();
            }
        });

        //相机拍照
        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera();
                window.dismiss();
            }
        });

        //取消按钮
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
        window.showAtLocation(popupView, Gravity.BOTTOM, 0, 50);
    }


    /**
     * 相机拍照
     */
    private void openCamera() {
        //getExternalCacheDir() 获取应用关联缓存目录
        File outputImage = new File(mContext.getExternalCacheDir(), "output_image.jpg");
        try {
            if (outputImage.exists()) {
                outputImage.delete();
            }
            outputImage.createNewFile();

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (Build.VERSION.SDK_INT >= 24) {
            imageUri = FileProvider.getUriForFile(mContext, "com.take.image", outputImage);
        } else {
            imageUri = Uri.fromFile(outputImage);
        }

        //启动相机程序
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, TAKE_PHOTO);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CHOOSE_PHOTO:
                if (data != null) {
                    imageUri = data.getData();
                    Bitmap bitmap = null;
                    try {
                        bitmap = BitmapFactory.decodeStream(mContext.getContentResolver().openInputStream(imageUri));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    Log.d("图片路径bitmap", bitmap.toString());
                    Log.d("图片相对路径路径", data.getData().getPath());

                    uploadImg.setImageBitmap(bitmap);
                    Upload2QiNiu.singleUpload2qiniu(bitmap);
//                    Upload2QiNiu.singleUpload2qiniu(getFilePathFromContentUri(imageUri,mContext.getContentResolver()));

                }
                break;
            default:
                break;
        }
    }


    /**
     * URI 转绝对路径
     * @param selectedVideoUri
     * @param contentResolver
     * @return
     */
    public static String getFilePathFromContentUri(Uri selectedVideoUri, ContentResolver contentResolver) {
        String filePath;
        String[] filePathColumn = {MediaStore.MediaColumns.DATA};

        Cursor cursor = contentResolver.query(selectedVideoUri, filePathColumn, null, null, null);
//      也可用下面的方法拿到cursor
//      Cursor cursor = this.context.managedQuery(selectedVideoUri, filePathColumn, null, null, null);

        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        filePath = cursor.getString(columnIndex);
        cursor.close();
        return filePath;
    }


}
