package com.yucheng.byxf.mini.utils;



import com.yucheng.byxf.mini.utils.ImageDownLoader.onImageLoaderListener;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.AbsListView;
import android.widget.ImageView;

public class down {
	private int mFirstVisibleItem;
	private boolean isFirstEnter = true;
	private String[] imageThumbUrls;
	/**
	 * 所有照片的个数
	 */
	private int mVisibleItemCount;
	// 上下文
	private Context context;
	
	private String[] easyLoan = null;
	private String[] rapidlyLoan = null;
	String type = null;
	
	private int position = 0;
	
	// 数组

	private ImageDownLoader mImageDownLoader;

	public down(Context context, String[] imageThumbUrls, String[] esayLoan,String[] rapidlyLoan,String type) {
		this.context = context;

		this.imageThumbUrls = imageThumbUrls;
		this.easyLoan = easyLoan;
		this.rapidlyLoan = rapidlyLoan;
		this.type = type;
		mImageDownLoader = new ImageDownLoader(context);

	}

	public void downpic() {
		mVisibleItemCount = imageThumbUrls.length;
		System.out.println(mVisibleItemCount + "-----"
				+ "imageThumbUrls.length");
		showImage(mFirstVisibleItem, mVisibleItemCount);
		isFirstEnter = false;
	}

	/**
	 * 显示当前屏幕的图片，先会去查找LruCache，LruCache没有就去sd卡或者手机目录查找，在没有就开启线程去下载
	 * 
	 * @param firstVisibleItem
	 * @param visibleItemCount
	 */
	private void showImage(int firstVisibleItem, int visibleItemCount) {
		Bitmap bitmap = null;
		int i = 0;
		for (i = firstVisibleItem; i < firstVisibleItem + visibleItemCount; i++) {
			position = i;
			String mImageUrl = imageThumbUrls[i];
			bitmap = mImageDownLoader.downloadImage(mImageUrl,
					new onImageLoaderListener() {

						@Override
						public void onImageLoader(Bitmap bitmap, String url) {
							// if(mImageView != null && bitmap != null){
							// mImageView.setImageBitmap(bitmap);
							// }
							if ("0".equals(type)) {// 轻松贷
								String subUrl = url.replaceAll("[^\\w]", "");
								easyLoan[position] = subUrl+".jpg";
								
							}else if("1".equals(type)) {// 极速贷
								String subUrl = url.replaceAll("[^\\w]", "");
								rapidlyLoan[position] = subUrl+".jpg";
								System.out.println("shuzu==="+rapidlyLoan.toString());
							}
						}
					});
		}
	}

}
