package com.yucheng.byxf.mini.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.view.View;
import android.widget.LinearLayout;

public class ViewTools {
	/**
	 * 
	 * @param s
	 *            传入的字符串
	 * @return 生成的带空格的字符串。例如:传入"1s2dadadsd",返回"1s 2dad adsd";
	 */
	public static String getAddBlankSpaceString(String s) {
		String result = "";
		String path = "";
		if (s.length() > 4) {
			while (s.length() > 4) {
				path = s.substring(s.length() - 4, s.length()) + " " + path;
				s = s.substring(0, s.length() - 4);
			}
			path = path.substring(0, path.length() - 1);
			result = s + " " + path;
		} else {
			result = s;
		}
		return result;
	}

	public static Bitmap getBitMap(String path ,int size) {
		FileInputStream stream = null;
		Bitmap bitmap = null;
		try {
			stream = new FileInputStream(new File(path));
			BitmapFactory.Options opts = new BitmapFactory.Options();
			opts.inSampleSize = size;
			bitmap = BitmapFactory.decodeStream(stream, null, opts);
			// if(!bitmap.isRecycled() ){
			// bitmap.recycle() ;
			// System.gc() ;
			// }
			int degree = ViewTools.readPictureDegree(path);
			if (bitmap!=null) {//TODO 处理空指针
				bitmap = ViewTools.rotaingImageView(degree, bitmap);
			}
			
//			upload(bitmap);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bitmap;
	}
	
	public static Bitmap cutCreateBitMap(Bitmap bt) {
		Bitmap bitmap; // 裁剪生成的新图片的bitmap值

		int x = 0; // 从图片的x轴的x处开始裁剪
		int y = 0; // 从图片的y轴的y处开始裁剪
		int image_width; // 裁剪生成新图皮的宽
		int image_height; // 裁剪生成新图皮的高
		if (bt.getHeight() > bt.getWidth()) {
			image_width = bt.getWidth();
			image_height = bt.getWidth();
		} else {
			image_width = bt.getHeight();
			image_height = bt.getHeight();

		}
		// 获取图片bitmap值
		bitmap = Bitmap.createBitmap(bt, x, y, image_width, image_height);
		return bitmap;

	}
	
	public static Bitmap cutCreateBitMap(Bitmap bt,List<Bitmap> list) {
		Bitmap bitmap; // 裁剪生成的新图片的bitmap值
		int x = 0; // 从图片的x轴的x处开始裁剪
		int y = 0; // 从图片的y轴的y处开始裁剪
		int image_width; // 裁剪生成新图皮的宽
		int image_height; // 裁剪生成新图皮的高
		if (bt.getHeight() > bt.getWidth()) {
			image_width = bt.getWidth();
			image_height = bt.getWidth();
		} else {
			image_width = bt.getHeight();
			image_height = bt.getHeight();

		}
		// 获取图片bitmap值
		bitmap = Bitmap.createBitmap(bt, x, y, image_width, image_height);
		list.add(bitmap);
		return bitmap;

	}

	/**
	 * 读取图片属性：旋转的角度
	 * 
	 * @param path
	 *            图片绝对路径
	 * @return degree旋转的角度
	 */
	public static int readPictureDegree(String path) {
		int degree = 0;
		try {
			ExifInterface exifInterface = new ExifInterface(path);
			int orientation = exifInterface.getAttributeInt(
					ExifInterface.TAG_ORIENTATION,
					ExifInterface.ORIENTATION_NORMAL);
			switch (orientation) {
			case ExifInterface.ORIENTATION_ROTATE_90:
				degree = 90;
				break;
			case ExifInterface.ORIENTATION_ROTATE_180:
				degree = 180;
				break;
			case ExifInterface.ORIENTATION_ROTATE_270:
				degree = 270;
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return degree;
	}

	public static void setAnimation1(final LinearLayout ll_title,
			final LinearLayout ll_title2) {
		int mShortAnimationDuration = 800;
		ll_title2.setAlpha(0f);
		ll_title2.setVisibility(View.VISIBLE);
		ll_title2.animate().alpha(1f).setDuration(mShortAnimationDuration)
				.setListener(null);
		ll_title.animate().alpha(0f).setDuration(mShortAnimationDuration)
				.setListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						ll_title.setVisibility(View.GONE);
					}
				});

	}

	public static void setAnimation2(final LinearLayout ll_title,
			final LinearLayout ll_title2) {
		int mShortAnimationDuration = 800;
		if (ll_title2.getVisibility() == View.VISIBLE) {
			ll_title.setAlpha(0f);
			ll_title.setVisibility(View.VISIBLE);
			ll_title.animate().alpha(0f).setDuration(mShortAnimationDuration)
					.setListener(null);
			ll_title.animate().alpha(1f).setDuration(mShortAnimationDuration)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							ll_title2.setVisibility(View.GONE);
						}
					});
		}

	}
	
	/**
	 * 旋转图片
	 * @param angle
	 * @param bitmap
	 * @return Bitmap
	 */
	public static Bitmap rotaingImageView(int angle , Bitmap bitmap) {
        //旋转图片 动作
		Matrix matrix = new Matrix();;
        matrix.postRotate(angle);
        System.out.println("angle2=" + angle);
        // 创建新的图片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
        		bitmap.getWidth(), bitmap.getHeight(), matrix, true);
		return resizedBitmap;
	}
	

}
