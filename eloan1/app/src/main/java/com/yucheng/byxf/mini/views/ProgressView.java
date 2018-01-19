package com.yucheng.byxf.mini.views;

import com.yucheng.byxf.mini.ui.R;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ProgressView {
	public static Dialog createLoadingDialog(Context context) {

		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.common_progress_layout, null);// 得到加载view
		LinearLayout layout = (LinearLayout) v
				.findViewById(R.id.comm_progress_ll_all_view);// 加载布局
		// layout.setBackgroundColor(Color.TRANSPARENT);
		// main.xml中的ImageView
		ImageView spaceshipImage = (ImageView) v
				.findViewById(R.id.comm_progress_iv_anim);
		// // 加载动画
		// Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
		// context, R.anim.loading_animation);
		// // 使用ImageView显示动画
		// spaceshipImage.startAnimation(hyperspaceJumpAnimation);

		// // 加载动画
		AnimationDrawable animation = (AnimationDrawable) spaceshipImage
				.getBackground();
		// 使用ImageView显示动画
		animation.start();

		Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);// 创建自定义样式dialog

		// loadingDialog.setCancelable(false);// 不可以用“返回键”取消
		loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
		return loadingDialog;

	}
}
