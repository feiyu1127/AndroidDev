package com.nwei.qiniuUtils;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;
import android.util.Log;

import com.qiniu.android.common.FixedZone;
import com.qiniu.android.common.Zone;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by admin on 2018/2/23.
 */

public class Upload2QiNiu {

    private static final String TAG = "Upload2QiNiu";
    
    public static void singleUpload2qiniu(Bitmap imgPath){

        Configuration config = new Configuration.Builder()
                .chunkSize(256 * 1024)  //分片上传时，每片的大小。 默认 256K
                .putThreshhold(512 * 1024)  // 启用分片上传阀值。默认 512K
                .connectTimeout(10) // 链接超时。默认 10秒
                .responseTimeout(60) // 服务器响应超时。默认 60秒
                .zone(FixedZone.zone2) // 设置区域，指默认 Zone.zone0
                .build();

        UploadManager uploadManager = new UploadManager(config);
        //设置图片名字
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String key = "icon_" + sdf.format(new Date()) ;
        Bitmap picPath = imgPath;
        
        Log.i(TAG, "picPath: " + picPath);

        uploadManager.put(Bitmap2Bytes(picPath), key, Auth.create("ugfiU6nWyHTqI2DMYECl0bmPKgR6Kg98FrdVHVqx", "9LN1RvkAiw9SPM34k6SvGNnGn1ulxhrhhYJ4lcoh").uploadToken("photo"), new UpCompletionHandler() {
            @Override
            public void complete(String key, ResponseInfo info, JSONObject response) {

                Log.d("开始上传前", "complete: ");
                if(info.isOK()){
                    Log.i(TAG, "token===" + Auth.create("ugfiU6nWyHTqI2DMYECl0bmPKgR6Kg98FrdVHVqx", "9LN1RvkAiw9SPM34k6SvGNnGn1ulxhrhhYJ4lcoh").uploadToken("photo"));
                    String headpicPath = "http://oukftd5d3.bkt.clouddn.com/" + key;
                    Log.i("图片已上传成功,路径为: ", "complete:" + headpicPath);

                }else{
                    Log.d("失败错误原因", info.error);
                }

                //     uploadpictoQianMo(headpicPath, picPath);
            }
        },null);
    }


    public static byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }



}
