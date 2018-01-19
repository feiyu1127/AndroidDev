package com.yucheng.byxf.mini.rapidly;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Thumbnails;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yucheng.apfx.utl.RegexResult;
import com.yucheng.byxf.comm.ScreenManager;
import com.yucheng.byxf.mini.easyloan.ui.ImageDispose;
import com.yucheng.byxf.mini.ui.BaseActivity;
import com.yucheng.byxf.mini.ui.HomeActivity;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.ChooseDialog;
import com.yucheng.byxf.mini.utils.ChooseDialog.OnDialogClickListener;
import com.yucheng.byxf.mini.utils.DialogUtil;
import com.yucheng.byxf.mini.utils.ImageCompress;
import com.yucheng.byxf.mini.utils.ViewTools;
import com.yucheng.byxf.mini.views.RoundAngleImageView;

/**
 * 类名: RapidlyLoanInfo1Activity</br>
 * 描述:极速贷采集影像资料页 </br>
 * 开发人员： weiyb</br>
 * 创建时间： 2015-7-20
 */

public class RapidlyLoanInfo1Activity extends BaseActivity implements OnClickListener
{
    
    private RoundAngleImageView imageView1;
    
    private RoundAngleImageView imageView2;
    
    private RoundAngleImageView imageView3;
    
    /**
     * 取消
     */
    private Button quit;
    
    private Button next_button;
    
    private String path1;
    
    private String path2;
    
    Dialog dialog2;
    
    private ChooseDialog dialog;
    
    private LinearLayout include;
    
    private Button back;
    
    private TextView tittle_test;
    
    private TextView easy_loan_head;
    
    // 验证结果
    private RegexResult result;
    
    // 信息有误
    private final int INFO_ERROR = 0;
    
    private Bitmap mCutBitmap;// 视频截图
    
    private final int VIDEO_REQUEST_CODE = 100;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rapidly_loan_info1_activity);
        
        dialog = new ChooseDialog();
        dialog.createDialog(this);
        
        include = (LinearLayout)findViewById(R.id.head);
        back = (Button)include.findViewById(R.id.bt_back_common);
        back.setOnClickListener(new OnClickListener()
        {
            
            @Override
            public void onClick(View v)
            {
                // 返回 极速贷申请信息-采集页
                
                Intent it = new Intent(RapidlyLoanInfo1Activity.this, RapidlyLoanInfoActivity.class);
                startActivity(it);
                
            }
        });
        initView();
        RapidlyLoanInfoContents.videoPath = null;
    }
    
    private void pathinit()
    {
        // SharedPreferencesUtils preferencesUtils = new SharedPreferencesUtils(
        // this);
        RapidlyLoanInfoContents.comPhoto1Path = preferencesUtils.getData("comPhoto1Path", "");
        RapidlyLoanInfoContents.comPhoto2Path = preferencesUtils.getData("comPhoto2Path", "");
        // RapidlyLoanInfoContents.videoPath = preferencesUtils.getData(
        // "videoPath", "");
        // System.out.println("RapidlyLoanInfoContents.videoPath --------------->" + RapidlyLoanInfoContents.videoPath);
        
    }
    
    private void shareclear()
    {
        // SharedPreferencesUtils preferencesUtils = new SharedPreferencesUtils(
        // this);
        if (preferencesUtils.hasData("comPhoto1Path"))
        {
            preferencesUtils.removeData("comPhoto1Path");
            preferencesUtils.removeData("comPhoto2Path");
            // preferencesUtils.removeData("videoPath");
        }
        
    }
    
    private void shareinit()
    {
        // SharedPreferencesUtils preferencesUtils = new SharedPreferencesUtils(
        // this);
        preferencesUtils.setData("comPhoto1Path", RapidlyLoanInfoContents.comPhoto1Path);
        preferencesUtils.setData("comPhoto2Path", RapidlyLoanInfoContents.comPhoto2Path);
        // preferencesUtils
        // .setData("videoPath", RapidlyLoanInfoContents.videoPath);
    }
    
    public boolean fileIsExists()
    {
        try
        {
            File f = new File(Environment.getExternalStorageDirectory() + "/" + "eloanvideo.3gp");
            if (!f.exists())
            {
                System.out.println("==========>没有");
                return false;
            }
            System.out.println("==========>有");
        }
        catch (Exception e)
        {
            
            return false;
        }
        return true;
    }
    
    /**
     * return 视频存在 录制视频的全路径；视频不存在 空
     * add by hyx
     */
    // private String getVideoPath(){
    // String result = "";
    // if(fileIsExists()){
    // result = Environment.getExternalStorageDirectory() + "/" + "eloanvideo.3gp";
    // }
    // return result;
    // }
    
    private void initView()
    {
        // 初始化 布局
        imageView1 = (RoundAngleImageView)findViewById(R.id.imageView1);
        imageView2 = (RoundAngleImageView)findViewById(R.id.imageView2);
        imageView3 = (RoundAngleImageView)findViewById(R.id.imageView3);
        
        quit = (Button)findViewById(R.id.quit);
        next_button = (Button)findViewById(R.id.next_button);
        
        imageView1.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        imageView3.setOnClickListener(this);
        quit.setOnClickListener(this);
        next_button.setOnClickListener(this);
        
        easy_loan_head = (TextView)include.findViewById(R.id.easy_loan_head);
        back = (Button)include.findViewById(R.id.bt_back_common);
        easy_loan_head.setText("采集影像资料");
        
        pathinit();
        if (null != RapidlyLoanInfoContents.comPhoto1Path && !"".equals(RapidlyLoanInfoContents.comPhoto1Path))
        {
            setImageView(imageView1, RapidlyLoanInfoContents.comPhoto1Path);
        }
        
        if (null != RapidlyLoanInfoContents.comPhoto2Path && !"".equals(RapidlyLoanInfoContents.comPhoto2Path))
        {
            setImageView(imageView2, RapidlyLoanInfoContents.comPhoto2Path);
        }
        if (fileIsExists())
        {
            // imageView3.setImageResource(R.drawable.video_ok);
            if (mCutBitmap == null)
            {
                mCutBitmap =
                    ThumbnailUtils.createVideoThumbnail(Environment.getExternalStorageDirectory() + "/"
                        + RapidlyLoanInfoContents.videoPath, Thumbnails.FULL_SCREEN_KIND);
                if (mCutBitmap != null)
                {
                    imageView3.setImageBitmap(mCutBitmap);
                }
            }
            else
            {
                imageView3.setImageBitmap(mCutBitmap);
            }
        }
        else
        {
            imageView3.setImageResource(R.drawable.video_selector);
            RapidlyLoanInfoContents.videoPath = null;
        }
        if (null != RapidlyLoanInfoContents.videoPath && !"".equals(RapidlyLoanInfoContents.videoPath))
        {
            // imageView3.setImageResource(R.drawable.video_ok);
            if (mCutBitmap == null)
            {
                mCutBitmap =
                    ThumbnailUtils.createVideoThumbnail(Environment.getExternalStorageDirectory() + "/"
                        + RapidlyLoanInfoContents.videoPath, Thumbnails.FULL_SCREEN_KIND);
                if (mCutBitmap != null)
                {
                    imageView3.setImageBitmap(mCutBitmap);
                }
            }
            else
            {
                imageView3.setImageBitmap(mCutBitmap);
            }
        }
        
    }
    
    private void setImageView(ImageView imageView, String path)
    {
        Bitmap bitmap = ViewTools.getBitMap(path, 10);
        imageView.setImageBitmap(bitmap);
    }
    
    @Override
    protected void onActivityResult(int reqCode, int rstCode, Intent itt)
    {
        System.gc();
        String pthOrig = null;
        String pthNail = null;
        Uri uri = null;
        RoundAngleImageView imgView = null;
        switch (reqCode)
        {
            case 1:
                pthOrig = path1;
                pthNail = RapidlyLoanInfoContents.comPhoto1Path;
                imgView = imageView1;
                break;
            case 2:
                pthOrig = path2;
                pthNail = RapidlyLoanInfoContents.comPhoto2Path;
                imgView = imageView2;
                break;
            case VIDEO_REQUEST_CODE:
                mCutBitmap =
                    ThumbnailUtils.createVideoThumbnail(Environment.getExternalStorageDirectory() + "/"
                        + RapidlyLoanInfoContents.videoPath, Thumbnails.FULL_SCREEN_KIND);
                if (mCutBitmap != null)
                {
                    imageView3.setImageBitmap(mCutBitmap);
                }
                break;
            default:
                break;
        }
        if (null != pthOrig)
        {
            if (RESULT_OK == rstCode)
            {
                try
                {
                    Bitmap bitmap =
                        new ImageCompress().getSmallBitmap(pthOrig, imgView.getWidth(), imgView.getHeight());
                    if (null != bitmap)
                    {
                        saveJPGE_After(bitmap, pthNail);
                        if (!bitmap.isRecycled())
                        {
                            bitmap.recycle();
                            bitmap = null;
                            System.gc();
                        }
                        uri = Uri.fromFile(new File(pthNail));
                    }
                    switch (reqCode)
                    {
                        case 1:
                            RapidlyLoanInfoContents.comPhoto1Path = pthOrig;
                            ImageCompress compress = new ImageCompress();
                            // wz
                            getBitmap1(compress, path1, RapidlyLoanInfoContents.comPhoto1Path, imageView1);
                            
                            break;
                        case 2:
                            RapidlyLoanInfoContents.comPhoto2Path = pthOrig;
                            // wz
                            ImageCompress compress2 = new ImageCompress();
                            getBitmap1(compress2, path2, RapidlyLoanInfoContents.comPhoto2Path, imageView2);
                            break;
                        default:
                            break;
                    }
                }
                catch (Exception e)
                {
                    // uri = null;
                }
            }
            if (null != uri)
            {
                imgView.setImageURI(null);
                imgView.setImageURI(uri);
            }
            else
            {
                switch (reqCode)
                {
                    case 1:
                        RapidlyLoanInfoContents.comPhoto1Path = null;
                        path1 = null;
                        break;
                    case 2:
                        RapidlyLoanInfoContents.comPhoto2Path = null;
                        path2 = null;
                        break;
                    default:
                        break;
                }
                imgView.setImageResource(R.drawable.idcard_selector);
            }
        }
        super.onActivityResult(reqCode, rstCode, itt);
        System.gc();
    }
    
    private void getBitmap1(ImageCompress compress, String path1, String comPath, ImageView imageView)
    {
        imageView.setImageBitmap(null);
        Bitmap bitmap = compress.bitmapComp(path1);
        saveJPGE_After(bitmap, comPath);
        Bitmap newBitmap = ViewTools.cutCreateBitMap(bitmap);
        imageView.setImageBitmap(newBitmap);
        if (bitmap != null && !bitmap.isRecycled())
        {
            bitmap.recycle();
            bitmap = null;
            System.gc();
        }
    }
    
    private void getBitmap2(Intent data, ImageCompress compress, ContentResolver resolver, String path1,
        String comPath, ImageView imageView)
    {
        byte[] bitmap = null;
        Uri uri = data.getData();
        try
        {
            bitmap = readStream(resolver.openInputStream(Uri.parse(uri.toString())));
        }
        catch (Exception e)
        {
           
        }
        Bitmap mBitmap = getPicFromBytes(bitmap, null);
        if (mBitmap != null)
        {
            // 获取图片路径
            String[] proj = {MediaStore.Images.Media.DATA};// 多媒体数据库的封装接口
            @SuppressWarnings("deprecation")
            Cursor cursor = managedQuery(uri, proj, null, null, null);
            int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            path1 = cursor.getString(index);
            mBitmap = compress.bitmapComp(path1);
            Bitmap bitmap2 = ViewTools.cutCreateBitMap(mBitmap);
            imageView.setImageBitmap(bitmap2);
            String path = saveJPGE_After(mBitmap, comPath);
            comPath = path;
        }
        else
        {
            path1 = null;
            comPath = null;
            Toast.makeText(RapidlyLoanInfo1Activity.this, "照片有问题!", Toast.LENGTH_LONG).show();
            imageView.setImageResource(R.drawable.idcard_selector);
        }
        
        if (mBitmap != null && !mBitmap.isRecycled())
        {
            mBitmap.recycle();
            mBitmap = null;
            System.gc();
        }
    }
    
    /**
     * 将选择的图片通过IO流读入内存中
     * @param inStream
     * @return
     * @throws Exception
     */
    private byte[] readStream(InputStream inStream)
        throws Exception
    {
        byte[] buffer = new byte[1024];
        int len = -1;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        while ((len = inStream.read(buffer)) != -1)
        {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();
        outStream.close();
        inStream.close();
        return data;
    }
    
    /**
     * 将byte数组写成图片
     * @param bytes
     * @param opts
     * @return
     */
    private Bitmap getPicFromBytes(byte[] bytes, BitmapFactory.Options opts)
    {
        if (bytes != null)
            if (opts != null)
            {
                try
                {
                    return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, opts);
                }
                catch (OutOfMemoryError e)
                {
                }
                
            }
            else
            {
                try
                {
                    return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                }
                catch (OutOfMemoryError e)
                {
                }
            }
        return null;
    }
    
    /**
     * 保存图片为JPEG
     * @param bitmap
     * @param path
     */
    public String saveJPGE_After(Bitmap bitmap, String path)
    {
        File file = new File(path);
        try
        {
            FileOutputStream out = new FileOutputStream(file);
            if (bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out))
            {
                out.flush();
                out.close();
            }
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return path;
    }
    
    @Override
    public void onClick(View v)
    {
        Intent intent = new Intent();
        if (v == imageView1)
        {
            
            //
            // dialog.showDialog();
            // dialog.setOnDialogClickListener(new OnDialogClickListener() {
            //
            // @Override
            // public void onPos() {
            // //  Auto-generated method stub
            // path1 = ImageDispose.getPath("byxf", "001");
            // RapidlyLoanInfoContents.comPhoto1Path = ImageDispose.getPath(
            // "byxf", "com001");
            // dialog.dismissDialog();
            // Intent intent = new Intent();
            // intent.setAction("android.intent.action.PICK");
            // intent.setType("image/*");
            // intent.addCategory(Intent.CATEGORY_DEFAULT);
            // startActivityForResult(intent, 11);
            // }
            //
            // @Override
            // public void onNeg() {
           
            path1 = ImageDispose.getPath("byxf", "001");
            RapidlyLoanInfoContents.comPhoto1Path = ImageDispose.getPath("byxf", "com001");
            dialog.dismissDialog();
            Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent1.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(path1)));
            startActivityForResult(intent1, 1);
            // }
            // });
            //
            // createDialogPhoto(1, 11, path1);
        }
        else if (v == imageView2)
        {
            
            // createDialogPhoto(2, 21, path2);
            // dialog.showDialog();
            // dialog.setOnDialogClickListener(new OnDialogClickListener() {
            //
            // @Override
            // public void onPos() {
            // //  Auto-generated method stub
            // path2 = ImageDispose.getPath("byxf", "002");
            // RapidlyLoanInfoContents.comPhoto2Path = ImageDispose.getPath(
            // "byxf", "com002");
            // dialog.dismissDialog();
            // Intent intent = new Intent();
            // intent.setAction("android.intent.action.PICK");
            // intent.setType("image/*");
            // intent.addCategory(Intent.CATEGORY_DEFAULT);
            // startActivityForResult(intent, 21);
            // }
            //
            // @Override
            // public void onNeg() {
            // //  Auto-generated method stub
            path2 = ImageDispose.getPath("byxf", "002");
            RapidlyLoanInfoContents.comPhoto2Path = ImageDispose.getPath("byxf", "com002");
            dialog.dismissDialog();
            Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(path2)));
            startActivityForResult(intent2, 2);
            // }
            // });
        }
        else if (v == imageView3)
        {
            // intent.setClass(this, TranscribeVideoActivity.class);
            // startActivity(intent);
            intent.setClass(this, TranscribeVideoActivity.class);
            startActivityForResult(intent, VIDEO_REQUEST_CODE);
        }
        else if (v == quit)
        {
            // SharedPreferencesUtils preferUtil = new SharedPreferencesUtils(
            // RapidlyLoanInfo1Activity.this);
            // preferUtil.clearData();
            // //------------存登陆标志信息
            // preferUtil.setData("login_sign","login_sign" );
            ScreenManager screenManager = ScreenManager.getScreenManager();
            screenManager.popAllActivityExceptOne();
            intent.setClass(this, HomeActivity.class);
            startActivity(intent);
        }
        else if (v == next_button)
        {
            // wz8.4a
            if (null == RapidlyLoanInfoContents.comPhoto1Path || "".equals(RapidlyLoanInfoContents.comPhoto1Path))
            {
                result = new RegexResult(false, "请完成证件拍照！");
                showDialog(INFO_ERROR);
                return;
            }
            if (null == RapidlyLoanInfoContents.comPhoto2Path || "".equals(RapidlyLoanInfoContents.comPhoto2Path))
            {
                result = new RegexResult(false, "请完成证件拍照！");
                showDialog(INFO_ERROR);
                return;
            }
            
            if (null == RapidlyLoanInfoContents.videoPath || "".equals(RapidlyLoanInfoContents.videoPath))
            {
                result = new RegexResult(false, "请完成视频录制！");
                showDialog(INFO_ERROR);
                return;
            }
            // 2015.3.3
            if (RapidlyLoanInfoContents.videoPath.getBytes().length < 1)
            {
                result = new RegexResult(false, "录制的视频有问题，请重新录制");
                showDialog(INFO_ERROR);
            }
            
            shareclear();
            shareinit();
            intent.setClass(this, RapidlyLoanInfoConfrim.class);
            startActivity(intent);
            finish();
        }
    }
    
    private void createDialogPhoto(final int photograph, final int choose, final String path)
    {
        dialog.showDialog();
        dialog.setOnDialogClickListener(new OnDialogClickListener()
        {
            
            @Override
            public void onPos()
            {
               
                dialog.dismissDialog();
                Intent intent = new Intent();
                intent.setAction("android.intent.action.PICK");
                intent.setType("image/*");
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                startActivityForResult(intent, choose);
            }
            
            @Override
            public void onNeg()
            {
              
                dialog.dismissDialog();
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(path)));
                startActivityForResult(intent, photograph);
            }
        });
    }
    
    @Override
    protected Dialog onCreateDialog(int id, Bundle args)
    {
        Dialog dialog = null;
        StringBuffer sb = new StringBuffer();
        switch (id)
        {
            case INFO_ERROR:
                sb.append(result.msg);
                dialog = DialogUtil.showDialogOneButton2(RapidlyLoanInfo1Activity.this, sb.toString());
                break;
            default:
                break;
        }
        return super.onCreateDialog(id, args);
    }
    
    @Override
    protected void onResume()
    {
       
        super.onResume();
        if (fileIsExists())
        {
            // imageView3.setImageResource(R.drawable.video_ok);
            if (mCutBitmap == null)
            {
                mCutBitmap =
                    ThumbnailUtils.createVideoThumbnail(Environment.getExternalStorageDirectory() + "/"
                        + RapidlyLoanInfoContents.videoPath, Thumbnails.FULL_SCREEN_KIND);
                if (mCutBitmap != null)
                {
                    imageView3.setImageBitmap(mCutBitmap);
                }
            }
            else
            {
                imageView3.setImageBitmap(mCutBitmap);
            }
        }
        else
        {
            imageView3.setImageResource(R.drawable.video_selector);
            RapidlyLoanInfoContents.videoPath = null;
        }
        // if (null != RapidlyLoanInfoContents.videoPath
        // && !"".equals(RapidlyLoanInfoContents.videoPath)) {
        // imageView3.setImageResource(R.drawable.video_ok);
        // } else {
        // imageView3.setImageResource(R.drawable.video_selector);
        // }
    }
    
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            // 这里重写返回键
            Intent it = new Intent(RapidlyLoanInfo1Activity.this, RapidlyLoanInfoActivity.class);
            startActivity(it);
            return true;
        }
        return false;
        
    }
    
}
