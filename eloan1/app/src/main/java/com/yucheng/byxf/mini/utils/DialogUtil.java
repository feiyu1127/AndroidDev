package com.yucheng.byxf.mini.utils;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.yucheng.byxf.comm.CommonContants;
import com.yucheng.byxf.comm.MyApplication;
import com.yucheng.byxf.comm.ScreenManager;
import com.yucheng.byxf.mini.ui.HomeActivity;
import com.yucheng.byxf.mini.ui.MyLoginActivity;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.ui.RegisterActivity;
import com.yucheng.byxf.sqlite.CommSaveInfoDBHelper;

public class DialogUtil {
	public static void createDialog(final Context context, int flag,
			String message) {
		final Dialog dialog = new Dialog(context, R.style.myDialog);
		dialog.setContentView(R.layout.mydialog_style);
		TextView tvMessage = (TextView) dialog.getWindow().getDecorView()
				.findViewById(R.id.tvMessage);
		Button pos = (Button) dialog.getWindow().getDecorView()
				.findViewById(R.id.btPos);
		Button neg = (Button) dialog.getWindow().getDecorView()
				.findViewById(R.id.btNeg);
		switch (flag) {
		case 0:
			tvMessage.setText("网络连接异常！");
			pos.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					dialog.cancel();
				}
			});
			neg.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					dialog.cancel();
				}
			});
			break;
		case 1:
			tvMessage.setText(message);
			pos.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent mIntent = new Intent(CommonContants.ACTION_NAME);
					context.sendBroadcast(mIntent);

				}
			});
			neg.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					dialog.dismiss(); 

				}
			});
			break;
		case 2:
			tvMessage.setText("连接超时！");
			pos.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					dialog.cancel();
				}
			});
			neg.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					dialog.cancel();
				}
			});
			break;

		case 3:
			tvMessage.setText("确定退出程序？");
			pos.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					MyApplication.setExit(true);
					CommSaveInfoDBHelper commSaveInfoDBHelper = new CommSaveInfoDBHelper(
							context);
					commSaveInfoDBHelper.close();
					ScreenManager screenManager = ScreenManager
							.getScreenManager();
					screenManager.popAllActivityExceptOne();
					System.exit(0);
				}
			});
			neg.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					dialog.cancel();
				}
			});
			break;
		case 4:
			tvMessage.setText(message);
			pos.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					dialog.cancel();
				}
			});
			neg.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					dialog.cancel();
				}
			});
			break;
		case 5:
			tvMessage.setText(message);
			pos.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Contents.IS_SAVE = true;
					ScreenManager.getScreenManager()
							.clearHistorynavigationFrom(
									HomeActivity.class.getSimpleName(), false);
				}
			});
			neg.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Contents.IS_SAVE = false;
					ScreenManager.getScreenManager()
							.clearHistorynavigationFrom(
									HomeActivity.class.getSimpleName(), false);
				}
			});

			break;
		case 6:
			tvMessage.setText(message);
			pos.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					ScreenManager.getScreenManager()
							.clearHistorynavigationFrom(
									MyLoginActivity.class.getSimpleName(),
									false);
				}
			});
			neg.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					dialog.cancel();
				}
			});
			break;
		case 7:
			tvMessage.setText(message);
			pos.setText("点击注册");
			pos.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent mIntent = new Intent();
//					context.sendBroadcast(mIntent);	
					mIntent.setClass(context, RegisterActivity.class);
	                   context.startActivity(mIntent);
				}
			});
			neg.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					dialog.cancel();
				}
			});

			break;
			
			
		default:
			break;
		}
		dialog.show();
		dialog.setCanceledOnTouchOutside(false);
	}

	/* 自定义内容的ProgressDialog */
	@SuppressLint("NewApi")
	public static Dialog showDialog(Context ctx, String message,
			android.view.View.OnClickListener posListener) {
		final Dialog dialog = new Dialog(ctx, R.style.myDialog);
		dialog.setContentView(R.layout.mydialog_style);
		TextView tvMessage = (TextView) dialog.getWindow().getDecorView()
				.findViewById(R.id.tvMessage);
		tvMessage.setText(message);
		Button pos = (Button) dialog.getWindow().getDecorView()
				.findViewById(R.id.btPos);
		pos.setOnClickListener(posListener);
		Button neg = (Button) dialog.getWindow().getDecorView()
				.findViewById(R.id.btNeg);
		neg.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				dialog.cancel();
			}
		});
		dialog.show();
		dialog.setCanceledOnTouchOutside(false);
		return dialog;

	}
	
	public static Dialog showDialogTwoButton(Context ctx, String message,
			android.view.View.OnClickListener posListener,
			android.view.View.OnClickListener negListener) {
		final Dialog dialog = new Dialog(ctx, R.style.myDialog);
		dialog.setContentView(R.layout.mydialog_style);
		TextView tvMessage = (TextView) dialog.getWindow().getDecorView()
				.findViewById(R.id.tvMessage);
		tvMessage.setText(message);
		Button pos = (Button) dialog.getWindow().getDecorView()
				.findViewById(R.id.btPos);
		pos.setText("取消");
		pos.setOnClickListener(posListener);
		Button neg = (Button) dialog.getWindow().getDecorView()
				.findViewById(R.id.btNeg);
		neg.setText("确定");
		neg.setOnClickListener(negListener);
		dialog.show();
		dialog.setCanceledOnTouchOutside(false);
		return dialog;

	}

	/* 自定义内容的ProgressDialog ,只有一个按钮，可以实现监听 */
	@SuppressLint("NewApi")
	public static Dialog showDialogOneButton(Context ctx, String message,
			android.view.View.OnClickListener posListener) {
		final Dialog dialog = new Dialog(ctx, R.style.myDialog);
		dialog.setContentView(R.layout.mydialog_style2);
		TextView tvMessage = (TextView) dialog.getWindow().getDecorView()
				.findViewById(R.id.tvMessage2);
		tvMessage.setText(message);
		Button pos = (Button) dialog.getWindow().getDecorView()
				.findViewById(R.id.btPos2);
		pos.setOnClickListener(posListener);
		// pos.setOnClickListener(new View.OnClickListener() {
		// @Override
		// public void onClick(View arg0) {
		// dialog.cancel();
		// }
		// });
		dialog.show();
		dialog.setCanceledOnTouchOutside(false);
		dialog.setCancelable(false);
		return dialog;

	}


	/* 自定义内容的ProgressDialog ,只有一个按钮，可以实现监听 */
	@SuppressLint("NewApi")
	public static Dialog showDialogOneButton_tijiao(Context ctx, String message,
			android.view.View.OnClickListener posListener) {
		final Dialog dialog = new Dialog(ctx, R.style.myDialog);
		dialog.setContentView(R.layout.mydialog_style2_tijiao);
		TextView tvMessage = (TextView) dialog.getWindow().getDecorView()
				.findViewById(R.id.tvMessage2);
		//tvMessage.setText(message);
		Button pos = (Button) dialog.getWindow().getDecorView()
				.findViewById(R.id.btPos2);
		pos.setOnClickListener(posListener);
		// pos.setOnClickListener(new View.OnClickListener() {
		// @Override
		// public void onClick(View arg0) {
		// dialog.cancel();
		// }
		// });
		dialog.show();
		dialog.setCanceledOnTouchOutside(false);
		dialog.setCancelable(false);
		return dialog;

	}
	/* 自定义内容的ProgressDialog ,只有一个按钮，并且只能关闭对话框 */
	@SuppressLint("NewApi")
	public static Dialog showDialogOneButton2(Context ctx, String message) {
		final Dialog dialog = new Dialog(ctx, R.style.myDialog);
		dialog.setContentView(R.layout.mydialog_style2);
		TextView tvMessage = (TextView) dialog.getWindow().getDecorView()
				.findViewById(R.id.tvMessage2);
		tvMessage.setText(message);
		Button pos = (Button) dialog.getWindow().getDecorView()
				.findViewById(R.id.btPos2);
		// pos.setOnClickListener(posListener);
		pos.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				dialog.cancel();
			}
		});
		dialog.show();
		dialog.setCanceledOnTouchOutside(false);
		return dialog;

	}
	
	/* 自定义内容的ProgressDialog ,只有一个按钮，可以实现监听----用于升级apk */
	@SuppressLint("NewApi")
	public static Dialog showDialogOneButton_shengjiapk(Context ctx, String message,
			android.view.View.OnClickListener posListener) {
		final Dialog dialog = new Dialog(ctx, R.style.myDialog);
		dialog.setContentView(R.layout.mydialog_style2_apk);
		TextView tvMessage = (TextView) dialog.getWindow().getDecorView()
				.findViewById(R.id.tvMessage2);
		tvMessage.setText(message);
		Button pos = (Button) dialog.getWindow().getDecorView()
				.findViewById(R.id.btPos2);
		pos.setOnClickListener(posListener);
		// pos.setOnClickListener(new View.OnClickListener() {
		// @Override
		// public void onClick(View arg0) {
		// dialog.cancel();
		// }
		// });
		dialog.show();
		dialog.setCanceledOnTouchOutside(false);
		dialog.setCancelable(false);
		return dialog;

	}
	
	/* 自定义内容的ProgressDialog 可以实现监听----用于升级apk*/
	@SuppressLint("NewApi")
	public static Dialog showDialog_apk(Context ctx, String message,
			android.view.View.OnClickListener posListener) {
		final Dialog dialog = new Dialog(ctx, R.style.myDialog);
		dialog.setContentView(R.layout.mydialog_style_apk);
		TextView tvMessage = (TextView) dialog.getWindow().getDecorView()
				.findViewById(R.id.tvMessage);
		tvMessage.setText(message);
		Button pos = (Button) dialog.getWindow().getDecorView()
				.findViewById(R.id.btPos);
		pos.setOnClickListener(posListener);
		Button neg = (Button) dialog.getWindow().getDecorView()
				.findViewById(R.id.btNeg);
		neg.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				dialog.cancel();
			}
		});
		dialog.show();
		dialog.setCanceledOnTouchOutside(false);
		return dialog;

	}
	

	/* 自定义内容的ProgressDialog ,只有一个按钮，可以实现监听 长条信息 */
	@SuppressLint("NewApi")
	public static Dialog showDialogOneBigButton(Context ctx, String message,
			android.view.View.OnClickListener posListener) {
		final Dialog dialog = new Dialog(ctx, R.style.myDialog);
		dialog.setContentView(R.layout.mydialog_style2);
		TextView tvMessage = (TextView) dialog.getWindow().getDecorView()
				.findViewById(R.id.tvMessage2);
		tvMessage.setText(message);
		Button pos = (Button) dialog.getWindow().getDecorView()
				.findViewById(R.id.btPos2);
		pos.setOnClickListener(posListener);
		// pos.setOnClickListener(new View.OnClickListener() {
		// @Override
		// public void onClick(View arg0) {
		// dialog.cancel();
		// }
		// });
		dialog.show();
		dialog.setCanceledOnTouchOutside(false);
		dialog.setCancelable(false);
		return dialog;

	}
	/* 自定义内容的ProgressDialog ,只有一个按钮，并且只能关闭对话框 */
	@SuppressLint("NewApi")
	public static Dialog showDialogOneButton2_big(Context ctx, String message) {
		final Dialog dialog = new Dialog(ctx, R.style.myDialog);
		dialog.setContentView(R.layout.mydialog_style2big);
		TextView tvMessage = (TextView) dialog.getWindow().getDecorView()
				.findViewById(R.id.tvMessage2);
		tvMessage.setText(message);
		Button pos = (Button) dialog.getWindow().getDecorView()
				.findViewById(R.id.btPos2);
		// pos.setOnClickListener(posListener);
		pos.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				dialog.cancel();
			}
		});
		dialog.show();
		dialog.setCanceledOnTouchOutside(false);
		return dialog;

	}
	
	/* 自定义内容的ProgressDialog ,只有一个按钮，并且只能关闭对话框 */
	@SuppressLint("NewApi")
	public static Dialog showOtherLoginDialog(final Context ctx, String message) {
		final Dialog dialog = new Dialog(ctx, R.style.myDialog);
		dialog.setContentView(R.layout.mydialog_loginshow);
		TextView tvMessage = (TextView) dialog.getWindow().getDecorView()
				.findViewById(R.id.tvMessages);
		tvMessage.setText(message);
		Button pos = (Button) dialog.getWindow().getDecorView()
				.findViewById(R.id.btPoss);
		pos.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				dialog.cancel();
				Intent inten = new Intent(ctx, MyLoginActivity.class);
                inten.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
                ctx.startActivity(inten);
//                DemoApplication.getApplication().startActivity(inten);
			}
		});
		dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		dialog.show();
		dialog.setCanceledOnTouchOutside(false);
		return dialog;

	}

}
