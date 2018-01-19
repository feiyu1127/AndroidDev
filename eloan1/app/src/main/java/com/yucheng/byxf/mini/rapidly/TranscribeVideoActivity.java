package com.yucheng.byxf.mini.rapidly;

import java.io.File;
import java.io.IOException;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.media.AudioManager;
import android.media.CamcorderProfile;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.SurfaceHolder.Callback;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yucheng.byxf.mini.message.LoginMessage;
import com.yucheng.byxf.mini.message.LoginResponse;
import com.yucheng.byxf.mini.ui.BaseActivity;
import com.yucheng.byxf.mini.ui.ForgetPassWordActivity;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.ui.RegisterActivity;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.DialogUtil;
import com.yucheng.byxf.mini.utils.FileUtils;

public class TranscribeVideoActivity extends BaseActivity implements
		OnClickListener {

	private TextView quit;
	private TextView sure;
	private Camera mCamera;
	private SurfaceView mSurfaceView;
	private SurfaceHolder mSurfaceHolder;
	private Button startButton;
	private boolean mIsRecording = false;
	private MediaRecorder mediaRecorder;

	private boolean playFlag = true;
	private MediaPlayer mediaPlayer;

	private String content = "\n\n\n请问您是否已知悉并同意该贷款所有条款？\n请问您在该贷款中使用的所有材料\n是否真实有效？\n请问您是否同意我们查询您的征信记录？\n如您同意上述条款\n请点击下一步进行视频录制:\n\n";

	private TextView tv_contents1;
	private TextView tv_contents2;
	private TextView tv_contents3;
	private Button startMedia;

	private int time18 = 21;

	private TextView tv_time;

	private boolean isTrue = true;

	private RelativeLayout video_bottom;

	// 8.14
	private Button bt_next;
	private CheckBox checkbox_next;
	private boolean isplay = true;

	private ImageView flagImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.transcribevideo_activity);
		initView();
		audio();

	}

	private void initView() {
		video_bottom = (RelativeLayout) findViewById(R.id.video_bottom);

		startButton = (Button) findViewById(R.id.start);
		sure = (TextView) findViewById(R.id.sure);
		quit = (TextView) findViewById(R.id.quit);

		tv_time = (TextView) findViewById(R.id.time);
		mSurfaceView = (SurfaceView) findViewById(R.id.surfaceview);

		startButton.setOnClickListener(this);
		sure.setOnClickListener(this);
		quit.setOnClickListener(this);

		bt_next = (Button) findViewById(R.id.bt_next);
		checkbox_next = (CheckBox) findViewById(R.id.checkb);
		checkbox_next.setChecked(true);

		flagImage = (ImageView) findViewById(R.id.flagImage);

		bt_next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (checkbox_next.isChecked() && !isplay) {
					// luxiang();

					quit.setVisibility(View.VISIBLE);
					sure.setVisibility(View.INVISIBLE);
					startButton.setVisibility(View.VISIBLE);
					tv_contents1.setVisibility(View.VISIBLE);
					tv_contents3.setVisibility(View.VISIBLE);
					tv_contents1.setTextSize(18);
					tv_contents2.setVisibility(View.GONE);
					startMedia.setVisibility(View.GONE);
					checkbox_next.setVisibility(View.GONE);
					bt_next.setVisibility(View.GONE);
					video_bottom.setVisibility(View.VISIBLE);
					flagImage.setVisibility(View.VISIBLE);
					mSurfaceView.setVisibility(View.VISIBLE);

				} else {
					if (!isplay) {
						Toast.makeText(TranscribeVideoActivity.this, "请确认上述条款",
								Toast.LENGTH_SHORT).show();

					} else {
						Toast.makeText(TranscribeVideoActivity.this, "请听完整录音",
								Toast.LENGTH_SHORT).show();
					}
				}

			}
		});

		mediaPlayer = MediaPlayer.create(this, R.raw.music_text);
		mediaPlayer.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				playFlag = false;
				isplay = false;
			}
		});

		tv_contents2 = (TextView) findViewById(R.id.tv_contents2);
		tv_contents1 = (TextView) findViewById(R.id.tv_contents1);
		tv_contents3 = (TextView) findViewById(R.id.tv_contents3);

		String cipName = "";
		if(Contents.response != null && Contents.response.getResult() != null)
			cipName = Contents.response.getResult().getCipNamecn();
		String contents = Contents.poetry_str + "\n本人"
				+ cipName
				// + ",\n身份证号" + Contents.response.getResult().getIdNo()
				+ ",同意上述条款。";

		tv_contents1.setText(contents);
		tv_contents3.setText("请用标准普通话复述下面文字:");
		tv_contents2.setText(content);

		startMedia = (Button) findViewById(R.id.startMedia);
		startMedia.setOnClickListener(this);

		mSurfaceHolder = mSurfaceView.getHolder();
		mSurfaceHolder.addCallback(new Callback() {
			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				releaseCamera();
			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				initpreview();
			}

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {

			}
		});
	}

	public void audio() {
		// 强制音量
		AudioManager mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		int maxVolume = mAudioManager
				.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

		int Volume = (int) Math.ceil(maxVolume * 0.7);
		System.out.println("音量maxVolume" + maxVolume);
		System.out.println("我想要的音量Volume" + Volume);
		mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, Volume, 0);
	};

	public void luxiang() {
		RapidlyLoanInfoContents.videoPath = null;
		RapidlyLoanInfoContents.videoPath = "eloanvideo.3gp";
		Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
		// intent.putExtra("camerasensortype", 2); // 调用前置摄像头
		intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0.8);
		intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 21);// 限制时长
		intent.putExtra(MediaStore.EXTRA_SIZE_LIMIT, 1024 * 1024);// 限制大小
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(
				Environment.getExternalStorageDirectory(), "eloanvideo.3gp")));
		System.out.println("--------->" + RapidlyLoanInfoContents.videoPath);
		startActivityForResult(intent, 1);
		finish();
	};

	// @Override
	// protected void onActivityResult(int requestCode, int resultCode, Intent
	// intent) {
	// // TODO 自动生成的方法存根
	// super.onActivityResult(requestCode, resultCode, intent);
	// RapidlyLoanInfoContents.videoPath=System.currentTimeMillis()+".3gp";
	// File videoFile = new
	// File(Environment.getExternalStorageDirectory()+"/aaaaa.3gp",
	// RapidlyLoanInfoContents.videoPath);
	//
	//
	//
	// }

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		// if (mediaPlayer != null) {
		// mediaPlayer.stop();
		// }
	}

	@Override
	protected void onStop() {
		super.onPause();
	};

	@SuppressLint("HandlerLeak")
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0) {

			} else if (msg.what == 1) {
				if (isTrue) {
					tv_time.setVisibility(View.VISIBLE);
					tv_time.setText("剩余时间：" + time18 + " 秒");

					if (time18 == 0) {
						stopmediaRecorder();
						mIsRecording = !mIsRecording;
						startButton
								.setBackgroundResource(R.drawable.video_stop);
						quit.setText("重录");
						sure.setVisibility(View.VISIBLE);
					}
				}
			}
		};
	};

	protected void releaseCamera() {
		if (mCamera != null) {
			try {
				mCamera.stopPreview();
				mCamera.release();
			} catch (RuntimeException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				mCamera = null;
			}
		}
	}

	@SuppressLint("NewApi")
	protected void initpreview() {
		String alt = null;
		try {
			// 调用前置摄像头
			mCamera = Camera.open(CameraInfo.CAMERA_FACING_FRONT);
			// 判断 是否获取到照相机信息
			// if(mCamera==null){
			// DialogUtil.showDialogOneButton(TranscribeVideoActivity.this,
			// "无法获取到您的摄像头，请查看是否被安全软件拦截。", new OnClickListener(){
			//
			// @Override
			// public void onClick(View v) {
			// // TODO Auto-generated method stub
			// Intent it1 = new Intent(TranscribeVideoActivity.this,
			// RapidlyLoanInfo1Activity.class);
			// startActivity(it1);
			// finish();
			// }
			//
			// });
			// }
			// //判断 是否获取到照相机信息
			// if(mCamera==null){
			// DialogUtil.showDialogOneButton(TranscribeVideoActivity.this,
			// "无法获取到您的摄像头，请查看是否被安全软件拦截。", new OnClickListener(){
			//
			// @Override
			// public void onClick(View v) {
			// // TODO Auto-generated method stub
			// Intent it1 = new Intent(TranscribeVideoActivity.this,
			// RapidlyLoanInfo1Activity.class);
			// startActivity(it1);
			// finish();
			// }
			//
			// });
			// }
			CamcorderProfile profile = getCamcorderProfile();
			Camera.Parameters parameters = mCamera.getParameters();
			parameters.setPreviewSize(profile.videoFrameWidth,
					profile.videoFrameHeight);
			mCamera.setParameters(parameters);
			// mCamera = Camera.open();
			setCameraDisplayOrientation(this, CameraInfo.CAMERA_FACING_FRONT,
					mCamera);
			mCamera.startPreview();
			mCamera.setPreviewDisplay(mSurfaceHolder);
		} catch (RuntimeException e) {
			e.printStackTrace();
			alt = "请确认摄像头没有被禁用！\n要继续请开启摄像头重试。";
		} catch (IOException e) {
			e.printStackTrace();
			alt = "请确认摄像头可用！\n要继续请返回重试。";
		} catch (Exception e) {
			e.printStackTrace();
			alt = "调用摄像头时出现未知错误！\n无法继续请退出应用返回重试。";
		}
		if (null != alt) {
			//
			tv_contents1.setVisibility(View.GONE);
			tv_contents3.setVisibility(View.GONE);
			tv_contents2.setVisibility(View.VISIBLE);
			startMedia.setVisibility(View.VISIBLE);
			checkbox_next.setVisibility(View.VISIBLE);
			bt_next.setVisibility(View.VISIBLE);
			mSurfaceView.setVisibility(View.INVISIBLE);
			flagImage.setVisibility(View.INVISIBLE);
			video_bottom.setVisibility(View.INVISIBLE);
			//
			AlertDialog.Builder alert = new AlertDialog.Builder(
					new ContextThemeWrapper(this, R.style.AlertDialogCustom));
			alert.setTitle("提示信息");
			alert.setMessage(alt);
			alert.setPositiveButton("确定",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
						}
					});
			alert.create();
			alert.show();
		}
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint({ "NewApi", "InlinedApi" })
	private CamcorderProfile getCamcorderProfile() {
		int quality = CamcorderProfile.QUALITY_LOW;
		// if (CamcorderProfile.hasProfile(CameraInfo.CAMERA_FACING_FRONT,
		// CamcorderProfile.QUALITY_720P)) {
		// quality = CamcorderProfile.QUALITY_720P;
		// } else if
		// (CamcorderProfile.hasProfile(CameraInfo.CAMERA_FACING_FRONT,
		// CamcorderProfile.QUALITY_HIGH)) {
		// quality = CamcorderProfile.QUALITY_HIGH;
		// } else {
		// quality = CamcorderProfile.QUALITY_LOW;
		// }
		if (CamcorderProfile.hasProfile(CameraInfo.CAMERA_FACING_FRONT,
				CamcorderProfile.QUALITY_720P)) {
			quality = CamcorderProfile.QUALITY_720P;
		}
		if (CamcorderProfile.hasProfile(CameraInfo.CAMERA_FACING_FRONT,
				CamcorderProfile.QUALITY_480P)) {
			quality = CamcorderProfile.QUALITY_480P;
		}
		return CamcorderProfile.get(CameraInfo.CAMERA_FACING_FRONT, quality);
	}

	/**
	 * 矫正偏移
	 * 
	 * @param activity
	 * @param cameraId
	 * @param camera
	 */
	public static void setCameraDisplayOrientation(Activity activity,
			int cameraId, android.hardware.Camera camera) {
		android.hardware.Camera.CameraInfo info = new android.hardware.Camera.CameraInfo();
		android.hardware.Camera.getCameraInfo(cameraId, info);
		int rotation = activity.getWindowManager().getDefaultDisplay()
				.getRotation();
		int degrees = 0;
		switch (rotation) {
		case Surface.ROTATION_0:
			degrees = 0;
			break;
		case Surface.ROTATION_90:
			degrees = 90;
			break;
		case Surface.ROTATION_180:
			degrees = 180;
			break;
		case Surface.ROTATION_270:
			degrees = 270;
			break;
		}

		int result;
		if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
			result = (info.orientation + degrees) % 360;
			result = (360 - result) % 360; // compensate the mirror
		} else { // back-facing
			result = (info.orientation - degrees + 360) % 360;
		}
		camera.setDisplayOrientation(result);
	}

	@SuppressLint("NewApi")
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void startmediaRecorder() {
		String alt = null;
		try {
			isTrue = true;
			time18 = 21;
			mCamera.stopPreview();
			mCamera.unlock();
			mIsRecording = true;
			mediaRecorder = new MediaRecorder();
			mediaRecorder.reset();
			mediaRecorder.setCamera(mCamera);

			mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
			// 从照相机采集视频
			mediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);

			CamcorderProfile profile = getCamcorderProfile();

			mediaRecorder.setOutputFormat(profile.fileFormat);
			mediaRecorder.setVideoEncoder(profile.videoCodec);
			mediaRecorder.setAudioEncoder(profile.audioCodec);
			File videoFile = new File(
					Environment.getExternalStorageDirectory(),
					RapidlyLoanInfoContents.videoPath);
			mediaRecorder.setOutputFile(videoFile.getAbsolutePath());
			mediaRecorder.setVideoSize(profile.videoFrameWidth,
					profile.videoFrameHeight);
			mediaRecorder.setVideoFrameRate(profile.videoFrameRate);
			mediaRecorder.setVideoEncodingBitRate(1024 * 1024 * 1);
			mediaRecorder.setPreviewDisplay(mSurfaceHolder.getSurface());
			try {
				mediaRecorder.prepare();// 预期准备
				mediaRecorder.start();// 开始刻录
				setTime();
			} catch (Exception e) {
				e.printStackTrace();
				mIsRecording = false;
				RapidlyLoanInfoContents.videoPath = null;
				Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show();
				e.printStackTrace();
				mCamera.lock();
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			alt = "请确认话筒录音没有被禁用！\n要继续请开启话筒录音重试。";
		} catch (Exception e) {
			e.printStackTrace();
			alt = "调用话筒录音时出现未知错误！\n无法继续请退出应用返回重试。";
		}
		if (null != alt) {
			//
			stopmediaRecorder();
			mIsRecording = false;
			//
			tv_contents3.setVisibility(View.GONE);
			tv_contents1.setVisibility(View.GONE);
			tv_contents2.setVisibility(View.VISIBLE);
			startMedia.setVisibility(View.VISIBLE);
			checkbox_next.setVisibility(View.VISIBLE);
			bt_next.setVisibility(View.VISIBLE);
			mSurfaceView.setVisibility(View.INVISIBLE);
			flagImage.setVisibility(View.INVISIBLE);
			startButton.setBackgroundResource(R.drawable.video_stop);
			video_bottom.setVisibility(View.INVISIBLE);
			//
			AlertDialog.Builder alert = new AlertDialog.Builder(
					new ContextThemeWrapper(this, R.style.AlertDialogCustom));
			alert.setTitle("提示信息");
			alert.setMessage(alt);
			alert.setPositiveButton("确定",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
						}
					});
			alert.create();
			alert.show();
		}
	}

	private void setTime() {
		new Thread() {
			public void run() {
				while (time18 > 0) {
					time18--;
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					handler.sendEmptyMessage(1);
				}
			};
		}.start();
	}

	private void stopmediaRecorder() {
		if (mediaRecorder != null) {
			try {
				tv_time.setVisibility(View.GONE);
				time18 = -1;
				isTrue = false;
				if (mIsRecording) {
					mediaRecorder.stop();
					mediaRecorder.reset();
					mediaRecorder.release();
					mediaRecorder = null;
					mIsRecording = false;
					try {
						mCamera.reconnect();
						mCamera.stopPreview();
					} catch (IOException e) {
						Toast.makeText(this, "reconect fail",
								Toast.LENGTH_SHORT).show();
						e.printStackTrace();
					}
				}
			} catch (RuntimeException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				mediaRecorder = null;
			}
		}
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		if (v == startButton) {
			if (!mIsRecording) {
				flagImage.setVisibility(View.INVISIBLE);
				mIsRecording = !mIsRecording;
				RapidlyLoanInfoContents.videoPath = "eloanvideo.3gp";
				startButton.setBackgroundResource(R.drawable.video_start);
				startmediaRecorder();
				quit.setText("取消");
				sure.setVisibility(View.INVISIBLE);
			} else {
				stopmediaRecorder();
				mIsRecording = !mIsRecording;
				startButton.setBackgroundResource(R.drawable.video_stop);
				quit.setText("重录");
				sure.setVisibility(View.VISIBLE);
			}
		} else if (v == quit) {
			if (quit.getText().toString().equals("重录")) {
				mIsRecording = !mIsRecording;
				startButton.setBackgroundResource(R.drawable.video_start);
				quit.setText("取消");
				sure.setVisibility(View.GONE);
				startmediaRecorder();
			} else {
				stopmediaRecorder();
				finish();
				RapidlyLoanInfoContents.videoPath = null;
			}
		} else if (v == sure) {
			Intent intent = new Intent();
			setResult(100, intent);
			finish();
		} else if (v == startMedia) {
			if (playFlag) {
				playFlag = !playFlag;
				startMedia.setBackgroundResource(R.drawable.media_pause);
				if (mediaPlayer != null) {
					mediaPlayer.stop();
				}
				try {
					mediaPlayer.prepare();
					mediaPlayer.start();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			stopmediaRecorder();
			if (null != RapidlyLoanInfoContents.videoPath
					&& !"".equals(RapidlyLoanInfoContents.videoPath)) {
				FileUtils.deleteFile(new File(Environment
						.getExternalStorageDirectory(),
						RapidlyLoanInfoContents.videoPath));
			}
			mediaPlayer.stop();
			finish();
			RapidlyLoanInfoContents.videoPath = null;
		}
		return true;
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
}
