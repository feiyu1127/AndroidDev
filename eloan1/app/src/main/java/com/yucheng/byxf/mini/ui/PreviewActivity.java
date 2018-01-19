package com.yucheng.byxf.mini.ui;

import java.io.File;

import com.yucheng.byxf.mini.domain.MiniPersonInfo;
import com.yucheng.byxf.mini.utils.ViewTools;
import com.yucheng.byxf.service.ImageDispose;
import com.yucheng.byxf.util.ImageStatis;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class PreviewActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.preview_layout);

		ImageView imageView = (ImageView) findViewById(R.id.preview_iv_image);
		if ("1".equals(getIntent().getStringExtra("image_view"))) {
			Bitmap bitmap = ViewTools.getBitMap(MiniPersonInfo.compress_photoPath1,3);
			imageView.setImageBitmap(bitmap);
		}
		if ("2".equals(getIntent().getStringExtra("image_view"))) {
			Bitmap bitmap = ViewTools.getBitMap(MiniPersonInfo.compress_photoPath2,3);
			imageView.setImageBitmap(bitmap);
		}
		if ("3".equals(getIntent().getStringExtra("image_view"))) {
			Bitmap bitmap = ViewTools.getBitMap(MiniPersonInfo.compress_photoPath3,3);
			imageView.setImageBitmap(bitmap);
		}
		if ("4".equals(getIntent().getStringExtra("image_view"))) {
			Bitmap bitmap = ViewTools.getBitMap(MiniPersonInfo.compress_photoPath4,3);
			imageView.setImageBitmap(bitmap);
		}
		if ("5".equals(getIntent().getStringExtra("image_view"))) {
			Bitmap bitmap = ViewTools.getBitMap(MiniPersonInfo.compress_photoPath5,3);
			imageView.setImageBitmap(bitmap);
		}
		
		imageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

}
