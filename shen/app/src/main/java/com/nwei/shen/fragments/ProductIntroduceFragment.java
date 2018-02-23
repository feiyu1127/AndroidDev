package com.nwei.shen.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
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
import com.nwei.tools.Auth;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_OK;

/**
 * Created by admin on 2018/2/6.
 */

public class ProductIntroduceFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "ProductIntroduceFragmen";

    View view;
    Context mContext;
    ImageView uploadImg;

    Button takePhotoBtn;
    Button confirmUploadBtn;
    Button bt_album;
    Button bt_camera;
    Button bt_cancle;

    private final int TAKE_PHOTO = 1;
    private final int CHOOSE_PHOTO = 2;
    Uri imageUri;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getActivity();
        view = inflater.inflate(R.layout.fragment_product_introduce, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        uploadImg = view.findViewById(R.id.uploadImg);
        takePhotoBtn = view.findViewById(R.id.takePhotoBtn);
        confirmUploadBtn = view.findViewById(R.id.confirmUploadBtn);

        confirmUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UploadManager uploadManager = new UploadManager();
                //设置图片名字
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                String key = "icon_" + sdf.format(new Date());

                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(mContext);
                String uploadImgPath = pref.getString("uploadImgPath", null);
                Log.d("我是path", uploadImgPath);

                String picPath = uploadImgPath;
                Log.i(TAG, "picPath: " + picPath);

                uploadManager.put(picPath, key, Auth.create("ugfiU6nWyHTqI2DMYECl0bmPKgR6Kg98FrdVHVqx", "9LN1RvkAiw9SPM34k6SvGNnGn1ulxhrhhYJ4lcoh").uploadToken("photo"), new UpCompletionHandler() {
                    @Override
                    public void complete(String key, ResponseInfo info, JSONObject response) {
                        if(info.isOK()){
                            Log.d(TAG, "token="+ Auth.create("ugfiU6nWyHTqI2DMYECl0bmPKgR6Kg98FrdVHVqx", "9LN1RvkAiw9SPM34k6SvGNnGn1ulxhrhhYJ4lcoh").uploadToken("photo"));
                            String headpicPath = "http://oukftd5d3.bkt.clouddn.com/"+key;
                            Log.d(TAG, "complete" + headpicPath);
                        }

//                         uploadpictoQianMo(headpicPath, picPath);

                    }
                },null);

            }
        });

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
        switch (getId()) {

            default:
                break;
        }
    }

    private void popupWindow() {
        //
        View popupView = LayoutInflater.from(mContext).inflate(R.layout.popup3_window, null, false);

        bt_album = popupView.findViewById(R.id.btn_pop_album);
        bt_camera = popupView.findViewById(R.id.btn_pop_camera);
        bt_cancle = popupView.findViewById(R.id.btn_pop_cancel);

        //获取屏幕宽高
        int weight = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels * 1 / 3;
        final PopupWindow window = new PopupWindow(popupView, weight, height, false);
        window.setOutsideTouchable(true);
        window.setTouchable(true);

        //相机拍照
        bt_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera();
                window.dismiss();
            }
        });

        //相册选择
        bt_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, CHOOSE_PHOTO);
                window.dismiss();
            }
        });

        //取消按钮
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
        window.showAtLocation(popupView, Gravity.BOTTOM, 0, 50);
    }


    /**
     * 打开相机拍照
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

        //启动相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, TAKE_PHOTO);

    }


    @Override
    public void onActivityResult(final int requestCode, int resultCode, final Intent data) {

        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Log.d("拍照相机", imageUri + "");
                        //将拍摄的照片显示出来
                        Bitmap bitmap = BitmapFactory.decodeStream(mContext.getContentResolver().openInputStream(imageUri));
                        Log.d("图片bitmap", bitmap.toString());
                        uploadImg.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case CHOOSE_PHOTO: //选择相册
                if (data != null) {
                    imageUri = data.getData();

                    Log.d("12相册图片的路径", data.getData().getPath()); //图片路径
                    Log.d("文件的绝对储存位置", mContext.getExternalCacheDir().getAbsolutePath());
                    Log.d("文件的储存位置getPath", mContext.getExternalCacheDir().getPath());
                    Log.d("文件的储存位置getParent", mContext.getExternalCacheDir().getParent());

                    Bitmap bitmap = null;
                    try {
                        bitmap = BitmapFactory.decodeStream(mContext.getContentResolver().openInputStream(imageUri));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    uploadImg.setImageBitmap(bitmap);

                    //将需要上传的图片的写到缓存中
                    SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(mContext).edit();
                    editor.putString("uploadImgPath", data.getData().getPath());
                    editor.apply();


//                    File saveCompressImgName = null;
//                    try {
//                        bitmap = revitionImageSize(data.getData());
//                        saveCompressImgName = saveBitmapFile(bitmap);
//                        Log.d("保存到SD", saveCompressImgName.getPath());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }

                }
                break;
            default:
                Log.d("没有走进拍照", "onActivityResult:");
                break;
        }
    }


    /**
     * @param path
     * @return
     * @throws IOException 压缩图片
     */
    public static Bitmap revitionImageSize(String path) throws IOException {
        //根据文件路径,创建一个字节缓冲输入流
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(new File(path)));
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        //根据流返回一个位图也就是bitmap，当options.inJustDecodeBounds = true的时候不需要完全解码，
        // 它仅仅会把它的宽，高取回来给你，这样就不会占用太多的内存，也就不会那么频繁的发生OOM了
        BitmapFactory.decodeStream(in, null, options);
        //关闭流
        in.close();
        int i = 0;
        Bitmap bitmap = null;
        while (true) {
            // options.outWidth >> i 。右移运算符，num >> 1,相当于num除以2
            if ((options.outWidth >> i <= 1000) && (options.outHeight >> i <= 1000)) {
                //得到一个输入流
                in = new BufferedInputStream(new FileInputStream(new File(path)));
                //为了解决图片解码时候出现SanpleSize错误，设置恰当的inSampleSize可以使BitmapFactory分配更少的空间以消除该错误
                //你将 inSampleSize 赋值为2,那就是每隔2行采1行,每隔2列采一列,那你解析出的图片就是原图大小的1/4.
                // Math.pow(2.0D, i)次方运算，2的i次方是多少
                options.inSampleSize = (int) Math.pow(2.0D, i);
                // 这里之前设置为了true，所以要改为false，否则就创建不出图片
                options.inJustDecodeBounds = false;
                bitmap = BitmapFactory.decodeStream(in, null, options);
                break;
            }
            i += 1;
        }
        return bitmap;
    }


    /**
     * @param bitmap 保存图片到SD卡的方法
     */
    private static File saveBitmapFile(Bitmap bitmap) {
        //Environment.getExternalStorageDirectory() 获取Android外部存储的空间，当有外部SD卡就在外部SD卡上建立。
        //没有外部SD卡就在内部SD卡的非data/data/目录建立目录。（data/data/目录才是真正的内存目录。）
        //IMAGE_NAME文件的名字，随便起。比如（xxx.jpg）
        String saveBitmapImgName = randomStr();
        File tempFile = new File(Environment.getExternalStorageDirectory(), saveBitmapImgName + ".jpg");

        try {
            //创建一个输出流，将数据写入到创建的文件对象中。
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(tempFile));
            ////30 是压缩率，表示压缩70%; 如果不压缩是100，
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
           /* 为什么要调用flush()方法？当FileOutputStream作为BufferedOutputStream构造函数的参数传入，然后对BufferedOutputStream进行写入操作，才能利用缓冲及flush()。
            查看BufferedOutputStream的源代码，发现所谓的buffer其实就是一个byte[]。
            BufferedOutputStream的每一次write其实是将内容写入byte[]，当buffer容量到达上限时，会触发真正的磁盘写入。
            而另一种触发磁盘写入的办法就是调用flush()了。*/
            bos.flush();
            //关闭流对象
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tempFile;
    }


    private static String randomStr() {
        String strRand = "";
        for (int i = 0; i < 20; i++) {
            strRand += String.valueOf((int) (Math.random() * 10));
        }
        return strRand;
    }

}
