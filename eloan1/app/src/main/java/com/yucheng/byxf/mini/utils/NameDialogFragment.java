package com.yucheng.byxf.mini.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

public class NameDialogFragment extends DialogFragment {

	private TextView str;

	private static String STR = "STR";

	public static NameDialogFragment newInstance(String currentTime) {
		// 创建一个新的带有指定参数的Fragment实例
		NameDialogFragment fragment = new NameDialogFragment();
		Bundle args = new Bundle();
		args.putString(STR, currentTime);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// 使用AlertBuilder创建新的对话框
		AlertDialog.Builder timeDialog = new AlertDialog.Builder(getActivity());
		// 配置对话框UI
		timeDialog.setTitle("提示信息！");
		timeDialog.setMessage(getArguments().getString(STR));
		// 返回配置完成的对话框
		return timeDialog.create();
	}
}
