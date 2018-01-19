package com.yucheng.byxf.mini.ui;

import com.yucheng.byxf.mini.domain.MiniPersonInfo;
import com.yucheng.byxf.mini.utils.DialogUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MiniCardSelectActivity extends BaseActivity implements
		OnClickListener {

	int width;
	int height;
	int bitmapWidth1;
	int bitmapHeight1;
	int bitmapWidth2;
	int bitmapHeight2;
	ImageView image1;
	ImageView image2;
	Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.minicardselect);

		image1 = (ImageView) findViewById(R.id.image1);
		image2 = (ImageView) findViewById(R.id.image2);
		button = (Button) findViewById(R.id.next_button);
		image1.setOnClickListener(this);
		image2.setOnClickListener(this);
		button.setOnClickListener(this);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (null == MiniPersonInfo.card || "".equals(MiniPersonInfo.card)) {
			image2.setBackgroundResource(R.drawable.card2_normal);
			image1.setBackgroundResource(R.drawable.card1_normal);
		} else if (MiniPersonInfo.card.equals("A")) {
			image1.setBackgroundResource(R.drawable.card1_select);
			image2.setBackgroundResource(R.drawable.card2_normal);
		} else if (MiniPersonInfo.card.equals("B")) {
			image1.setBackgroundResource(R.drawable.card1_normal);
			image2.setBackgroundResource(R.drawable.card2_select);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.image1) {
			image1.setBackgroundResource(R.drawable.card1_select);
			image2.setBackgroundResource(R.drawable.card2_normal);
			MiniPersonInfo.card = "A";
		} else if (v.getId() == R.id.image2) {
			image1.setBackgroundResource(R.drawable.card1_normal);
			image2.setBackgroundResource(R.drawable.card2_select);
			MiniPersonInfo.card = "B";
		} else if (v.getId() == R.id.next_button) {
			if (MiniPersonInfo.card == null || "".equals(MiniPersonInfo.card)) {
				DialogUtil.showDialogOneButton2(MiniCardSelectActivity.this,
						"请选择卡面！");
			} else {
				Intent intent = new Intent();
				intent.setClass(MiniCardSelectActivity.this,
						MiniConfirmInfoActivity.class);
				startActivity(intent);
			}
		}
	}
}
