package com.yucheng.byxf.mini.ui;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.yucheng.byxf.mini.domain.MiniPersonInfo;
import com.yucheng.byxf.mini.easyloan.ui.ImageDispose;
import com.yucheng.byxf.mini.utils.ImageCompress;
import com.yucheng.byxf.mini.utils.ViewTools;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MiniPaperPhotoActivity extends BaseActivity implements
		OnClickListener {
	private Button next_button;
	private List<ImageView> imageList;

	private ImageView imageView1;
	private ImageView imageView2;
	private ImageView imageView3;
	private ImageView imageView4;
	private ImageView imageView5;
	private ImageView iv_menu_common;

	private String photoPath1;
	private String photoPath2;
	private String photoPath3;
	private String photoPath4;
	private String photoPath5;
	private static final int PHOTORESOULT = 8;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mini_paper_photo);
		// clearPublicPhoto();
		initView();
	}
	private void initView() {
		next_button = (Button) findViewById(R.id.next_button);
		next_button.setOnClickListener(this);
		iv_menu_common = (ImageView) findViewById(R.id.iv_menu_common);
		iv_menu_common.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		imageView1 = (ImageView) findViewById(R.id.imageView1);
		imageView2 = (ImageView) findViewById(R.id.imageView2);
		imageView3 = (ImageView) findViewById(R.id.imageView3);
		imageView4 = (ImageView) findViewById(R.id.imageView4);
		imageView5 = (ImageView) findViewById(R.id.imageView5);
		/* 创建一个list用来给imageView编号 */
		imageList = new ArrayList<ImageView>();
		imageList.add(imageView1);
		imageList.add(imageView2);
		imageList.add(imageView3);
		imageList.add(imageView4);
		imageList.add(imageView5);
		imageView1.setOnClickListener(this);
		imageView2.setOnClickListener(this);
		imageView3.setOnClickListener(this);
		imageView4.setOnClickListener(this);
		imageView5.setOnClickListener(this);
	}

	@Override
	protected void onResume() {
		if (MiniPersonInfo.compress_photoPath1 != null) {
			imageList.get(0).setImageBitmap(ViewTools.getBitMap(MiniPersonInfo.compress_photoPath1,10));
		}
		if (MiniPersonInfo.compress_photoPath2 != null) {
			imageList.get(1).setImageBitmap(ViewTools.getBitMap(MiniPersonInfo.compress_photoPath2,10));
		}
		if (MiniPersonInfo.compress_photoPath3 != null) {
			imageList.get(2).setImageBitmap(ViewTools.getBitMap(MiniPersonInfo.compress_photoPath3,10));
		}
		if (MiniPersonInfo.compress_photoPath4 != null) {
//			imageList.get(3).setImageDrawable(MiniPersonInfo.image3);
			imageList.get(3).setImageBitmap(ViewTools.getBitMap(MiniPersonInfo.compress_photoPath4,10));
		}
		if (MiniPersonInfo.compress_photoPath5 != null) {
//			imageList.get(4).setImageDrawable(MiniPersonInfo.image4);
			imageList.get(4).setImageBitmap(ViewTools.getBitMap(MiniPersonInfo.compress_photoPath5,10));
		}

		super.onResume();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.next_button:
			if (MiniPersonInfo.compress_photoPath1 != null && MiniPersonInfo.compress_photoPath2 != null
					&& MiniPersonInfo.compress_photoPath3 != null
					&& MiniPersonInfo.compress_photoPath4 != null) {
				// Intent intent = new Intent(MiniPaperPhotoActivity.this,
				// MiniCardSelectActivity.class);
				Intent intent = new Intent(MiniPaperPhotoActivity.this,
						MiniCardSelectActivity.class);
//				Intent intent = new Intent(MiniPaperPhotoActivity.this,
//						MiniConfirmInfoActivity.class);
				startActivity(intent);
			} else {
				// CommonUtil.commonToast(getApplicationContext(), "请完成证件拍照！");
				AlertDialog.Builder builder = new AlertDialog.Builder(
						new ContextThemeWrapper(this, R.style.AlertDialogCustom));
				builder.setTitle("提示信息");
				builder.setMessage("请完成证件拍照！");
				builder.setPositiveButton("确定",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
							}
						});
				builder.create();
				builder.show();
			}
			break;
		case R.id.imageView1:
			// buttonId = 0;
			// getPhoto(buttonId);
			photoPath1 = ImageDispose.getPath("byxf", "01");
			MiniPersonInfo.photoPath1 = photoPath1;
			System.out.println("photoPath1==>" + photoPath1);
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			intent.putExtra(MediaStore.EXTRA_OUTPUT,
					Uri.fromFile(new File(photoPath1)));
			startActivityForResult(intent, 1);
			break;
		case R.id.imageView2:
			// buttonId = 1;
			// getPhoto(buttonId);
			photoPath2 = ImageDispose.getPath("byxf", "02");
			System.out.println("photoPath2==>" + photoPath2);
			MiniPersonInfo.photoPath2 = photoPath2;
			Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			intent2.putExtra(MediaStore.EXTRA_OUTPUT,
					Uri.fromFile(new File(photoPath2)));
			startActivityForResult(intent2, 2);
			break;
		case R.id.imageView3:
			// buttonId = 2;
			// getPhoto(buttonId);
			photoPath3 = ImageDispose.getPath("byxf", "03");
			MiniPersonInfo.photoPath3 = photoPath3;
			Intent intent3 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			intent3.putExtra(MediaStore.EXTRA_OUTPUT,
					Uri.fromFile(new File(photoPath3)));
			startActivityForResult(intent3, 3);
			break;
		case R.id.imageView4:
			// buttonId = 3;
			// getPhoto(buttonId);
			photoPath4 = ImageDispose.getPath("byxf", "04");
			MiniPersonInfo.photoPath4 = photoPath4;
			Intent intent4 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			intent4.putExtra(MediaStore.EXTRA_OUTPUT,
					Uri.fromFile(new File(photoPath4)));
			startActivityForResult(intent4, 4);
			break;
		case R.id.imageView5:
			// buttonId = 4;
			// getPhoto(buttonId);
			photoPath5 = ImageDispose.getPath("byxf", "05");
			MiniPersonInfo.photoPath5 = photoPath5;
			Intent intent5 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			intent5.putExtra(MediaStore.EXTRA_OUTPUT,
					Uri.fromFile(new File(photoPath5)));
			startActivityForResult(intent5, 5);
			break;
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		ImageCompress compress = new ImageCompress();
		if (requestCode == 1) {
			if (resultCode == RESULT_CANCELED) {
				return;
			}
			Bitmap bitmap = ViewTools.getBitMap(photoPath1, 10);
			Bitmap compress_bitmap = compress.bitmapComp(photoPath1);
			String path = ImageDispose.saveJPGE_After(compress_bitmap, "new01");
			MiniPersonInfo.compress_photoPath1 = path;
			System.out.println(path);
			Bitmap newBitmap = ViewTools.cutCreateBitMap(bitmap);
			Drawable drawable = new BitmapDrawable(newBitmap);
			imageList.get(0).setImageDrawable(drawable);
//			MiniPersonInfo.image0 = drawable;
			System.out.println("存储了图片1");
			imageView1.setImageDrawable(drawable);
			drawable = null;
			bitmap = null;
			compress_bitmap = null;
		}
		if (requestCode == 2) {
			if (resultCode == RESULT_CANCELED) {
				return;
			}
			Bitmap bitmap = ViewTools.getBitMap(photoPath2, 10);
			Bitmap compress_bitmap = compress.bitmapComp(photoPath2);
			String path = ImageDispose.saveJPGE_After(compress_bitmap, "new02");
			MiniPersonInfo.compress_photoPath2 = path;
			System.out.println(path);
			Bitmap newBitmap = ViewTools.cutCreateBitMap(bitmap);
			// imageView2.setImageBitmap(newBitmap);
			// ImageStatis.bm2 = newBitmap;
			Drawable drawable = new BitmapDrawable(newBitmap);
			imageList.get(1).setImageDrawable(drawable);
//			MiniPersonInfo.image1 = drawable;
			System.out.println("存储了图片2");
			imageView2.setImageDrawable(drawable);
			drawable = null;
			bitmap = null;
			compress_bitmap = null;
		}
		if (requestCode == 3) {
			if (resultCode == RESULT_CANCELED) {
				return;
			}
			Bitmap bitmap = ViewTools.getBitMap(photoPath3, 10);
			Bitmap compress_bitmap = compress.bitmapComp(photoPath3);
			String path = ImageDispose.saveJPGE_After(compress_bitmap, "new03");
			MiniPersonInfo.compress_photoPath3 = path;
			System.out.println(path);
			Bitmap newBitmap = ViewTools.cutCreateBitMap(bitmap);
			// imageView3.setImageBitmap(newBitmap);
			// ImageStatis.bm3 = newBitmap;
			Drawable drawable = new BitmapDrawable(newBitmap);
			imageList.get(2).setImageDrawable(drawable);
//			MiniPersonInfo.image2 = drawable;
			imageView3.setImageDrawable(drawable);
			drawable = null;
			bitmap = null;
			compress_bitmap = null;
		}

		if (requestCode == 4) {
			if (resultCode == RESULT_CANCELED) {
				return;
			}
			Bitmap bitmap = ViewTools.getBitMap(photoPath4, 10);
			Bitmap compress_bitmap = compress.bitmapComp(photoPath4);
			String path = ImageDispose.saveJPGE_After(compress_bitmap, "new04");
			MiniPersonInfo.compress_photoPath4 = path;
			System.out.println(path);
			Bitmap newBitmap = ViewTools.cutCreateBitMap(bitmap);
			// imageView4.setImageBitmap(newBitmap);
			// ImageStatis.bm4 = newBitmap;
			Drawable drawable = new BitmapDrawable(newBitmap);
			imageList.get(3).setImageDrawable(drawable);
//			MiniPersonInfo.image3 = drawable;
			imageView4.setImageDrawable(drawable);
			drawable = null;
			bitmap = null;
			compress_bitmap = null;
		}

		if (requestCode == 5) {
			if (resultCode == RESULT_CANCELED) {
				return;
			}
			Bitmap bitmap = ViewTools.getBitMap(photoPath5, 10);
			Bitmap compress_bitmap = compress.bitmapComp(photoPath5);
			String path = ImageDispose.saveJPGE_After(compress_bitmap, "new05");
			MiniPersonInfo.compress_photoPath5 = path;
			System.out.println(path);
			Bitmap newBitmap = ViewTools.cutCreateBitMap(bitmap);
			// imageView5.setImageBitmap(newBitmap);
			// ImageStatis.bm5 = newBitmap;
			Drawable drawable = new BitmapDrawable(newBitmap);
			imageList.get(4).setImageDrawable(drawable);
//			MiniPersonInfo.image4 = drawable;

			imageView5.setImageDrawable(drawable);
			drawable = null;
			bitmap = null;
			compress_bitmap = null;
		}

		if (requestCode == PHOTORESOULT + 1) {
			if (resultCode == RESULT_CANCELED) {
				return;
			}
			Bitmap photo = data.getParcelableExtra("data");
			Bundle extras = data.getExtras();
			if (extras != null) {
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
				// ImageStatis.bm1 = photo;
				Drawable drawable = new BitmapDrawable(photo);
				imageList.get(2).setImageDrawable(drawable);
//				MiniPersonInfo.image0 = drawable;
				imageView1.setImageBitmap(photo);
			}
		}

		if (requestCode == PHOTORESOULT + 2) {
			Bundle extras = data.getExtras();
			if (extras != null) {
			}
		}
		if (requestCode == PHOTORESOULT + 3) {
			Bundle extras = data.getExtras();
			if (extras != null) {
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
			}
		}
		if (requestCode == PHOTORESOULT + 4) {
			Bundle extras = data.getExtras();
			if (extras != null) {
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
			}
		}
		if (requestCode == PHOTORESOULT + 5) {
			Bundle extras = data.getExtras();
			if (extras != null) {
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
			}
		}
	}

	/**
	 * 质量压缩
	 * 
	 * @param image
	 * @return
	 */
	private Bitmap compressImage(Bitmap image) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int options = 90;
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中

		while (baos.toByteArray().length / 2048 > 200) { // 循环判断如果压缩后图片是否大于200kb,大于继续压缩

			// System.out.println("现在的图片大小是=========》》》》》"
			// + baos.toByteArray().length);
			baos.reset();// 重置baos即清空baos
			options -= 10;// 每次都减少10
			image.compress(Bitmap.CompressFormat.JPEG, options, baos);
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中

		Bitmap bitmap = null;
		try {
			bitmap = BitmapFactory.decodeStream(isBm, null, null);
		} catch (OutOfMemoryError e1) {
		}
		try {
			baos.flush();
			baos.close();
			isBm.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
	}

	/**
	 * 图片压缩方法实现
	 * 
	 * @param srcPath
	 * @return
	 */
	private Bitmap comp(Bitmap image) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		if (baos.toByteArray().length / 2048 > 256) {// 判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
			baos.reset();// 重置baos即清空baos
			image.compress(Bitmap.CompressFormat.JPEG, 90, baos);// 这里压缩50%，把压缩后的数据存放到baos中
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		// 开始读入图片，此时把options.inJustDecodeBounds 设回true了
		newOpts.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		// 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
		float hh = 1280f;// 这里设置高度为800f
		float ww = 720f;// 这里设置宽度为480f
		// 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
		int be = 1;// be=1表示不缩放
		if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
			be = (int) (newOpts.outWidth / ww);
		} else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
			be = (int) (newOpts.outHeight / hh);
		}
		if (be <= 0)
			be = 1;
		newOpts.inSampleSize = be;// 设置缩放比例
		// 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
		isBm = new ByteArrayInputStream(baos.toByteArray());
		try {
			bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
		} catch (OutOfMemoryError e1) {
		}
		try {
			baos.flush();
			baos.close();
			isBm.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return compressImage(bitmap);// 压缩好比例大小后再进行质量压缩
	}
}
