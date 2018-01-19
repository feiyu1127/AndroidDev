/**
 * 图片压缩处理类
 */
package com.yucheng.byxf.mini.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageCompress {
  private static final int SMALL_W_AUTO = 160;
  private static final int SMALL_H_AUTO = 90;

	public Bitmap compressImage(Bitmap image) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int options = 90;
		image.compress(Bitmap.CompressFormat.JPEG, 90, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中

		while (baos.toByteArray().length / 1024 > 200) { // 循环判断如果压缩后图片是否大于200kb,大于继续压缩

			baos.reset();// 重置baos即清空baos
			options -= 5;// 每次都减少10
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
	public Bitmap comp(Bitmap image) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		if (baos.toByteArray().length / 1024 > 128) {// 判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
			baos.reset();// 重置baos即清空baos
			image.compress(Bitmap.CompressFormat.JPEG, 10, baos);// 这里压缩50%，把压缩后的数据存放到baos中
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		// 开始读入图片，此时把options.inJustDecodeBounds 设回true了，不把图片读入到内存中
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

	// --------------------------------------------下边的方法能保证图片压缩而不失真-----------------------------------------------

	/**
	 * 计算图片的缩放值
	 * 
	 * @param options
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */
	public int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {
			final int heightRatio = Math.round((float) height
					/ (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}
		return inSampleSize;
	}

  /**
   * 缩小图片以满足指定幅
   * 
   * @param lfn
   *          图片路径
   * @param w
   *          幅的宽
   * @param h
   *          幅的高
   * @return 输出位图
   */
  public Bitmap getSmallBitmap(String lfn, int w, int h) {
    if (SMALL_W_AUTO * SMALL_H_AUTO < w * h) {
      w = SMALL_W_AUTO;
      h = SMALL_H_AUTO;
    }
    BitmapFactory.Options ops = new BitmapFactory.Options();
    ops.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(lfn, ops);
    ops.inSampleSize = calculateInSampleSize(ops, w, h);
    ops.inJustDecodeBounds = false;
    ops.inScaled = false;
    ops.inPurgeable = true;
    ops.inTempStorage = new byte[1024 * 16];
    boolean ipt = true;
    do {
      if (0 == "samsung".compareToIgnoreCase(android.os.Build.MANUFACTURER)) {
        break;
      }
      // Add ipt MANUFACTURER above
      ipt = false;
    }
    while (false);
    if (ipt) {
      ops.inPreferredConfig = Bitmap.Config.ARGB_4444;
    }
    else {
      ops.inPreferredConfig = Bitmap.Config.RGB_565;
    }
    return BitmapFactory.decodeFile(lfn, ops);
  }
	
	/**
	 * 根据路径获得图片并压缩，返回bitmap用于显示
	 * 
	 * @param filePath
	 * @return
	 */
	public Bitmap getSmallBitmap(String filePath, float percent) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		// inJustDecodeBounds设置为true，可以不把图片读到内存中,但依然可以计算出图片的大小
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filePath, options);

		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options,
				(int) (480 * percent), (int) (800 * percent));

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;

		return BitmapFactory.decodeFile(filePath, options);
	}

	/**
	 * 压缩图片
	 * 
	 * @param filePath
	 * @return
	 */
	public Bitmap bitmapComp(String filePath) {
		int length = 0;
		int comp = 100;
		Bitmap bm = getSmallBitmap(filePath, 1);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.JPEG, comp, baos);
		length = baos.toByteArray().length;
		if (length < 600 * 1024) {

		} else if (length < 1024 * 1024) {
			comp = 80;
		} else if (length < 1024 * 1024 * 2) {
			comp = 60;
		} else {
			comp = 40;
		}
		while (length > 1024 * 600) {
			comp -= 5;
			baos.reset();
			bm.compress(Bitmap.CompressFormat.JPEG, comp, baos);
			length = baos.toByteArray().length;
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
}
