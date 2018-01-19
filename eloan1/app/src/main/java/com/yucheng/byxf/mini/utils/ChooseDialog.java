package com.yucheng.byxf.mini.utils;

import com.yucheng.byxf.mini.ui.R;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ChooseDialog {
	
	OnDialogClickListener onDialogClickListener;
	Dialog dialog;
	public interface OnDialogClickListener{
		public void onPos();
		public void onNeg();
	}

	public void setOnDialogClickListener(OnDialogClickListener onDialogClickListener) {
		this.onDialogClickListener = onDialogClickListener;
	}

	public Dialog createDialog(Context context){
		dialog = new Dialog(context, R.style.myDialog);
		dialog.setContentView(R.layout.choose_dialog);
		Button xiangceButton = (Button) dialog.getWindow().getDecorView()
				.findViewById(R.id.choose_xiangce);
		Button xiangjiButton = (Button) dialog.getWindow().getDecorView()
				.findViewById(R.id.choose_xiangji);
		xiangceButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onDialogClickListener.onPos();
			}
		});
		xiangjiButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onDialogClickListener.onNeg();
			}
		});
		
		dialog.setCanceledOnTouchOutside(false);
		return dialog;
	}
	
	public void dismissDialog(){
		dialog.dismiss();
	}
	
	public void showDialog(){
		dialog.show();
	}
	
	public Dialog createFengxianDialog(final Context context){
		dialog = new Dialog(context, R.style.myDialog);
		dialog.setContentView(R.layout.fengxian_dialog);
		Button button = (Button) dialog.getWindow().getDecorView()
				.findViewById(R.id.button);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onDialogClickListener.onPos();
			}
		});
		dialog.setCanceledOnTouchOutside(false);
		dialog.setCancelable(false);
		return dialog;
	}
	
	public Dialog createTipsDialog(final Context context){
		dialog = new Dialog(context, R.style.myDialog);
		dialog.setContentView(R.layout.tips_dialog);
		Button button = (Button) dialog.getWindow().getDecorView()
				.findViewById(R.id.button);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onDialogClickListener.onPos();
			}
		});
		dialog.setCanceledOnTouchOutside(false);
		dialog.setCancelable(false);
		return dialog;
}}
