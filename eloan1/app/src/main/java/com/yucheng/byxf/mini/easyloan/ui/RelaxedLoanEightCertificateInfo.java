package com.yucheng.byxf.mini.easyloan.ui;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.yucheng.byxf.mini.domain.MiniPersonInfo;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.ChooseDialog;
import com.yucheng.byxf.mini.utils.ChooseDialog.OnDialogClickListener;
import com.yucheng.byxf.mini.utils.ImageCompress;
import com.yucheng.byxf.mini.utils.ViewTools;

public class RelaxedLoanEightCertificateInfo extends BaseRelaxedLoanActivity {
	private List<ImageView> imageList;
	private ImageView imageView1;
	private ImageView imageView2;
	private ImageView imageView3;
	private ImageView imageView4;
	private ImageView imageView5;

	private ImageView imageView51;
	private ImageView imageView52;
	private ImageView imageView53;
	private ImageView imageView54;
	private ImageView imageView55;
	private RelativeLayout rl_51;
	private RelativeLayout rl_52;
	private RelativeLayout rl_53;
	private RelativeLayout rl_54;
	private RelativeLayout rl_55;
	private RelativeLayout rl_tianjia_right;
	private RelativeLayout rl_tianjia_right2;
	private RelativeLayout rl_tianjia_right3;

	private ImageView tanjia;
	private ImageView tianjia_last;
	private ImageView tianjia_two;
	private ImageView tianjia_three;

	private Button bt_example;

	private String photoPath1;
	private String photoPath2;
	private String photoPath3;
	private String photoPath4;
	private String photoPath5;

	private String photoPath51;
	private String photoPath52;
	private String photoPath53;
	private String photoPath54;
	private String photoPath55;

	private Button commit_Info_next_button;

	private static final int PHOTORESOULT = 8;

	private boolean[] flag = { true, false, false, false, false };

	private ChooseDialog dialog;

	// private List<Bitmap> listBitmaps = new ArrayList<Bitmap>();

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		setContentView(R.layout.easy_loan_eight_certificate_photogragh);
		super.init();
		easy_loan_head.setText("证件拍照");
		commit_Info_next_button = (Button) findViewById(R.id.commit_Info_next_button);
		commit_Info_next_button.setOnClickListener(this);
		dialog = new ChooseDialog();
		dialog.createDialog(this);
		imageView1 = (ImageView) findViewById(R.id.imageView1);
		imageView2 = (ImageView) findViewById(R.id.imageView2);
		imageView3 = (ImageView) findViewById(R.id.imageView3);
		imageView4 = (ImageView) findViewById(R.id.imageView4);
		imageView5 = (ImageView) findViewById(R.id.imageView5);
		bt_example = (Button) findViewById(R.id.bt_example);

		imageView51 = (ImageView) findViewById(R.id.imageView51);
		imageView52 = (ImageView) findViewById(R.id.imageView52);
		imageView53 = (ImageView) findViewById(R.id.imageView53);
		imageView54 = (ImageView) findViewById(R.id.imageView54);
		imageView55 = (ImageView) findViewById(R.id.imageView55);
		tanjia = (ImageView) findViewById(R.id.tianjia);
		tianjia_two = (ImageView) findViewById(R.id.tianjia_two);
		tianjia_three = (ImageView) findViewById(R.id.tianjia_three);

		tianjia_last = (ImageView) findViewById(R.id.tianjia_last);
		rl_51 = (RelativeLayout) findViewById(R.id.rl_51);
		rl_52 = (RelativeLayout) findViewById(R.id.rl_52);
		rl_53 = (RelativeLayout) findViewById(R.id.rl_53);
		rl_54 = (RelativeLayout) findViewById(R.id.rl_54);
		rl_55 = (RelativeLayout) findViewById(R.id.rl_55);
		rl_tianjia_right = (RelativeLayout) findViewById(R.id.rl_tianjia_right);
		rl_tianjia_right2 = (RelativeLayout) findViewById(R.id.rl_tianjia_right2);
		rl_tianjia_right3 = (RelativeLayout) findViewById(R.id.rl_tianjia_right3);
		/* 创建一个list用来给imageView编号 */
		imageList = new ArrayList<ImageView>();
		imageList.add(imageView1);
		imageList.add(imageView2);
		imageList.add(imageView3);
		imageList.add(imageView4);
		imageList.add(imageView5);

		imageList.add(imageView51);
		imageList.add(imageView52);
		imageList.add(imageView53);
		imageList.add(imageView54);
		imageList.add(imageView55);
		imageList.add(tanjia);
		imageView1.setOnClickListener(this);
		imageView2.setOnClickListener(this);
		imageView3.setOnClickListener(this);
		imageView4.setOnClickListener(this);
		imageView5.setOnClickListener(this);
		bt_example.setOnClickListener(this);

		imageView51.setOnClickListener(this);
		imageView52.setOnClickListener(this);
		imageView53.setOnClickListener(this);
		imageView54.setOnClickListener(this);
		imageView55.setOnClickListener(this);
		tanjia.setOnClickListener(this);
		tianjia_two.setOnClickListener(this);
		tianjia_three.setOnClickListener(this);
		tianjia_last.setOnClickListener(this);

		// SharedPreferencesUtils preferencesUtils =new
		// SharedPreferencesUtils(this);
		// if(preferencesUtils.hasData("compress_photoPath1")){
		// editinit();
		// }
		editinit();
	}

	private void shareclear() {
//		SharedPreferencesUtils preferencesUtils = new SharedPreferencesUtils(
//				this);
		if (preferencesUtils.hasData("compress_photoPath1")) {
			preferencesUtils.removeData("compress_photoPath1");
			preferencesUtils.removeData("compress_photoPath2");
			preferencesUtils.removeData("compress_photoPath3");
			preferencesUtils.removeData("compress_photoPath4");
			preferencesUtils.removeData("compress_photoPath5");
			preferencesUtils.removeData("compress_photoPath51");
			preferencesUtils.removeData("compress_photoPath52");
			preferencesUtils.removeData("compress_photoPath53");
			preferencesUtils.removeData("compress_photoPath54");
			preferencesUtils.removeData("compress_photoPath55");
		}
	}

	private void shareinit() {
//		SharedPreferencesUtils preferencesUtils = new SharedPreferencesUtils(
//				this);
		// 姓名
		preferencesUtils.setData("compress_photoPath1",
				MiniPersonInfo.compress_photoPath1);
		preferencesUtils.setData("compress_photoPath2",
				MiniPersonInfo.compress_photoPath2);
		preferencesUtils.setData("compress_photoPath3",
				MiniPersonInfo.compress_photoPath3);
		preferencesUtils.setData("compress_photoPath4",
				MiniPersonInfo.compress_photoPath4);
		if (null != MiniPersonInfo.compress_photoPath5) {
			preferencesUtils.setData("compress_photoPath5",
					MiniPersonInfo.compress_photoPath5);
		}

		if (null != MiniPersonInfo.compress_photoPath51) {
			preferencesUtils.setData("compress_photoPath51",
					MiniPersonInfo.compress_photoPath51);
		}
		if (null != MiniPersonInfo.compress_photoPath52) {
			preferencesUtils.setData("compress_photoPath52",
					MiniPersonInfo.compress_photoPath52);
		}
		if (null != MiniPersonInfo.compress_photoPath53) {
			preferencesUtils.setData("compress_photoPath53",
					MiniPersonInfo.compress_photoPath53);
		}
		if (null != MiniPersonInfo.compress_photoPath54) {
			preferencesUtils.setData("compress_photoPath54",
					MiniPersonInfo.compress_photoPath54);
		}
		if (null != MiniPersonInfo.compress_photoPath55) {
			preferencesUtils.setData("compress_photoPath55",
					MiniPersonInfo.compress_photoPath55);
		}

	}

	private void editinit() {
//		SharedPreferencesUtils preferencesUtils = new SharedPreferencesUtils(
//				this);
		MiniPersonInfo.compress_photoPath1 = preferencesUtils.getData(
				"compress_photoPath1", "");
		MiniPersonInfo.compress_photoPath2 = preferencesUtils.getData(
				"compress_photoPath2", "");
		MiniPersonInfo.compress_photoPath3 = preferencesUtils.getData(
				"compress_photoPath3", "");
		MiniPersonInfo.compress_photoPath4 = preferencesUtils.getData(
				"compress_photoPath4", "");
		MiniPersonInfo.compress_photoPath5 = preferencesUtils.getData(
				"compress_photoPath5", "");
		MiniPersonInfo.compress_photoPath51 = preferencesUtils.getData(
				"compress_photoPath51", "");
		MiniPersonInfo.compress_photoPath52 = preferencesUtils.getData(
				"compress_photoPath52", "");
		MiniPersonInfo.compress_photoPath53 = preferencesUtils.getData(
				"compress_photoPath53", "");
		MiniPersonInfo.compress_photoPath54 = preferencesUtils.getData(
				"compress_photoPath54", "");
		MiniPersonInfo.compress_photoPath55 = preferencesUtils.getData(
				"compress_photoPath55", "");
	}

	@Override
	protected void onResume() {
		if (MiniPersonInfo.compress_photoPath1 != null
				&& !MiniPersonInfo.compress_photoPath1.equals("")) {
			setListBitmap(MiniPersonInfo.compress_photoPath1, imageList.get(0));
		}
		if (MiniPersonInfo.compress_photoPath2 != null
				&& !MiniPersonInfo.compress_photoPath2.equals("")) {
			setListBitmap(MiniPersonInfo.compress_photoPath2, imageList.get(1));
		}
		if (MiniPersonInfo.compress_photoPath3 != null
				&& !MiniPersonInfo.compress_photoPath3.equals("")) {
			setListBitmap(MiniPersonInfo.compress_photoPath3, imageList.get(2));
		}
		if (MiniPersonInfo.compress_photoPath4 != null
				&& !MiniPersonInfo.compress_photoPath4.equals("")) {
			setListBitmap(MiniPersonInfo.compress_photoPath4, imageList.get(3));
		}
		if (MiniPersonInfo.compress_photoPath5 != null
				&& !MiniPersonInfo.compress_photoPath5.equals("")) {
			setListBitmap(MiniPersonInfo.compress_photoPath5, imageList.get(4));
		}

		if (MiniPersonInfo.compress_photoPath51 != null
				&& !MiniPersonInfo.compress_photoPath51.equals("")) {
			rl_51.setVisibility(View.VISIBLE);
			rl_tianjia_right.setVisibility(View.GONE);
			rl_tianjia_right2.setVisibility(View.VISIBLE);
			rl_53.setVisibility(View.INVISIBLE);
			flag[0] = false;
			flag[1] = true;
			setListBitmap(MiniPersonInfo.compress_photoPath51, imageList.get(5));
		}

		if (MiniPersonInfo.compress_photoPath52 != null
				&& !MiniPersonInfo.compress_photoPath52.equals("")) {
			rl_52.setVisibility(View.VISIBLE);
			rl_53.setVisibility(View.GONE);
			flag[1] = false;
			flag[2] = true;
			setListBitmap(MiniPersonInfo.compress_photoPath52, imageList.get(6));
		}

		if (MiniPersonInfo.compress_photoPath53 != null
				&& !MiniPersonInfo.compress_photoPath53.equals("")) {
			rl_53.setVisibility(View.VISIBLE);
			rl_tianjia_right2.setVisibility(View.GONE);
			rl_tianjia_right3.setVisibility(View.VISIBLE);
			rl_55.setVisibility(View.INVISIBLE);
			rl_54.setVisibility(View.GONE);
			flag[2] = false;
			flag[3] = true;
			setListBitmap(MiniPersonInfo.compress_photoPath53, imageList.get(7));
		}

		if (MiniPersonInfo.compress_photoPath54 != null
				&& !MiniPersonInfo.compress_photoPath54.equals("")) {
			rl_54.setVisibility(View.VISIBLE);
			rl_55.setVisibility(View.GONE);
			flag[3] = false;
			flag[4] = true;
			setListBitmap(MiniPersonInfo.compress_photoPath54, imageList.get(8));
		}

		if (MiniPersonInfo.compress_photoPath55 != null
				&& !MiniPersonInfo.compress_photoPath55.equals("")) {
			rl_55.setVisibility(View.VISIBLE);
			rl_tianjia_right3.setVisibility(View.GONE);
			flag[4] = false;
			setListBitmap(MiniPersonInfo.compress_photoPath55, imageList.get(9));
		}

		super.onResume();
	}

	private void setListBitmap(String path, ImageView imageView) {
		// if (listBitmaps == null) {
		// listBitmaps = new ArrayList<Bitmap>();
		// }
		Bitmap bitmap = ViewTools.getBitMap(path, 10);
		imageView.setImageBitmap(bitmap);
		// listBitmaps.add(bitmap);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.commit_Info_next_button:
			if (MiniPersonInfo.compress_photoPath1 != null
					&& MiniPersonInfo.compress_photoPath2 != null
					&& MiniPersonInfo.compress_photoPath3 != null
					&& MiniPersonInfo.compress_photoPath4 != null
					&& !"".equals(MiniPersonInfo.compress_photoPath1)
					&& !"".equals(MiniPersonInfo.compress_photoPath2)
					&& !"".equals(MiniPersonInfo.compress_photoPath3)
					&& !"".equals(MiniPersonInfo.compress_photoPath4)) {
				// for (Bitmap bitmap : listBitmaps) {
				// if (bitmap != null && !bitmap.isRecycled()) {
				// bitmap.recycle();
				// bitmap = null;
				// }
				// }
				// listBitmaps.clear();
				// listBitmaps = null;
				// System.gc();
				shareclear();
				shareinit();

				Intent intent = new Intent(
						RelaxedLoanEightCertificateInfo.this,
						RelaxedLoanNineCommitInfo.class);
				startActivity(intent);
			} else {
				// CommonUtil.commonToast(getApplicationContext(), "请完成证件拍照！");
				AlertDialog.Builder builder = new AlertDialog.Builder(
						new ContextThemeWrapper(this, R.style.AlertDialogCustom));
				builder.setTitle("提示信息");
				builder.setMessage("请完成证件拍照！");
				builder.setPositiveButton("确定",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
							}
						});
				builder.create();
				builder.show();
			}
			break;
		case R.id.imageView1:
			// buttonId = 0;
			// getPhoto(buttonId);

			dialog.showDialog();
			dialog.setOnDialogClickListener(new OnDialogClickListener() {

				@Override
				public void onPos() {
					// TODO Auto-generated method stub
					photoPath1 = ImageDispose.getPath("byxf", "01");
					MiniPersonInfo.photoPath1 = photoPath1;
					MiniPersonInfo.compress_photoPath1 = ImageDispose.getPath(
							"byxf", "new01");
					System.out.println("photoPath1==>" + photoPath1);
					dialog.dismissDialog();
					Intent intent = new Intent();
					intent.setAction("android.intent.action.PICK");
					intent.setType("image/*");
					intent.addCategory(Intent.CATEGORY_DEFAULT);
					startActivityForResult(intent, 11);
				}

				@Override
				public void onNeg() {
					// TODO Auto-generated method stub
					photoPath1 = ImageDispose.getPath("byxf", "01");
					MiniPersonInfo.photoPath1 = photoPath1;
					MiniPersonInfo.compress_photoPath1 = ImageDispose.getPath(
							"byxf", "new01");
					System.out.println("photoPath1==>" + photoPath1);
					dialog.dismissDialog();
					Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					intent.putExtra(MediaStore.EXTRA_OUTPUT,
							Uri.fromFile(new File(photoPath1)));
					startActivityForResult(intent, 1);
				}
			});

			break;
		case R.id.imageView2:
			// buttonId = 1;
			// getPhoto(buttonId);

			dialog.showDialog();
			dialog.setOnDialogClickListener(new OnDialogClickListener() {

				@Override
				public void onPos() {
					// TODO Auto-generated method stub
					photoPath2 = ImageDispose.getPath("byxf", "02");
					System.out.println("photoPath2==>" + photoPath2);
					MiniPersonInfo.photoPath2 = photoPath2;
					MiniPersonInfo.compress_photoPath2 = ImageDispose.getPath(
							"byxf", "new02");
					dialog.dismissDialog();
					Intent intent = new Intent();
					intent.setAction("android.intent.action.PICK");
					intent.setType("image/*");
					intent.addCategory(Intent.CATEGORY_DEFAULT);
					startActivityForResult(intent, 21);
				}

				@Override
				public void onNeg() {
					// TODO Auto-generated method stub
					photoPath2 = ImageDispose.getPath("byxf", "02");
					System.out.println("photoPath2==>" + photoPath2);
					MiniPersonInfo.photoPath2 = photoPath2;
					MiniPersonInfo.compress_photoPath2 = ImageDispose.getPath(
							"byxf", "new02");
					dialog.dismissDialog();
					Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					intent2.putExtra(MediaStore.EXTRA_OUTPUT,
							Uri.fromFile(new File(photoPath2)));
					startActivityForResult(intent2, 2);
				}
			});

			break;
		case R.id.imageView3:
			// buttonId = 2;
			// getPhoto(buttonId);

			dialog.showDialog();
			dialog.setOnDialogClickListener(new OnDialogClickListener() {

				@Override
				public void onPos() {
					// TODO Auto-generated method stub
					photoPath3 = ImageDispose.getPath("byxf", "03");
					MiniPersonInfo.photoPath3 = photoPath3;
					MiniPersonInfo.compress_photoPath3 = ImageDispose.getPath(
							"byxf", "new03");
					dialog.dismissDialog();
					Intent intent = new Intent();
					intent.setAction("android.intent.action.PICK");
					intent.setType("image/*");
					intent.addCategory(Intent.CATEGORY_DEFAULT);
					startActivityForResult(intent, 31);
				}

				@Override
				public void onNeg() {
					photoPath3 = ImageDispose.getPath("byxf", "03");
					MiniPersonInfo.photoPath3 = photoPath3;
					MiniPersonInfo.compress_photoPath3 = ImageDispose.getPath(
							"byxf", "new03");
					dialog.dismissDialog();
					Intent intent3 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					intent3.putExtra(MediaStore.EXTRA_OUTPUT,
							Uri.fromFile(new File(photoPath3)));
					startActivityForResult(intent3, 3);
				}
			});

			break;
		case R.id.imageView4:
			// buttonId = 3;
			// getPhoto(buttonId);

			dialog.showDialog();
			dialog.setOnDialogClickListener(new OnDialogClickListener() {

				@Override
				public void onPos() {
					// TODO Auto-generated method stub
					photoPath4 = ImageDispose.getPath("byxf", "04");
					MiniPersonInfo.photoPath4 = photoPath4;
					MiniPersonInfo.compress_photoPath4 = ImageDispose.getPath(
							"byxf", "new04");
					dialog.dismissDialog();
					Intent intent = new Intent();
					intent.setAction("android.intent.action.PICK");
					intent.setType("image/*");
					intent.addCategory(Intent.CATEGORY_DEFAULT);
					startActivityForResult(intent, 41);
				}

				@Override
				public void onNeg() {
					// TODO Auto-generated method stub
					photoPath4 = ImageDispose.getPath("byxf", "04");
					MiniPersonInfo.photoPath4 = photoPath4;
					MiniPersonInfo.compress_photoPath4 = ImageDispose.getPath(
							"byxf", "new04");
					dialog.dismissDialog();
					Intent intent4 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					intent4.putExtra(MediaStore.EXTRA_OUTPUT,
							Uri.fromFile(new File(photoPath4)));
					startActivityForResult(intent4, 4);
				}
			});

			break;
		case R.id.imageView5:
			// buttonId = 4;
			// getPhoto(buttonId);
			if (null == MiniPersonInfo.compress_photoPath1
					|| "".equals(MiniPersonInfo.compress_photoPath1)) {
				Toast.makeText(this, "请拍摄身份证正面", Toast.LENGTH_SHORT).show();
				return;
			}
			if (null == MiniPersonInfo.compress_photoPath2
					|| "".equals(MiniPersonInfo.compress_photoPath2)) {
				Toast.makeText(this, "请拍摄身份证反面", Toast.LENGTH_SHORT).show();
				return;
			}
			if (null == MiniPersonInfo.compress_photoPath3
					|| "".equals(MiniPersonInfo.compress_photoPath3)) {
				Toast.makeText(this, "请拍摄客户照片", Toast.LENGTH_SHORT).show();
				return;
			}
			if (null == MiniPersonInfo.compress_photoPath4
					|| "".equals(MiniPersonInfo.compress_photoPath4)) {
				Toast.makeText(this, "请拍摄人行征信报告", Toast.LENGTH_SHORT).show();
				return;
			}

			dialog.showDialog();
			dialog.setOnDialogClickListener(new OnDialogClickListener() {

				@Override
				public void onPos() {
					// TODO Auto-generated method stub
					photoPath5 = ImageDispose.getPath("byxf", "05");
					MiniPersonInfo.photoPath5 = photoPath5;
					MiniPersonInfo.compress_photoPath5 = ImageDispose.getPath(
							"byxf", "new05");
					dialog.dismissDialog();
					Intent intent = new Intent();
					intent.setAction("android.intent.action.PICK");
					intent.setType("image/*");
					intent.addCategory(Intent.CATEGORY_DEFAULT);
					startActivityForResult(intent, 501);
				}

				@Override
				public void onNeg() {
					// TODO Auto-generated method stub
					photoPath5 = ImageDispose.getPath("byxf", "05");
					MiniPersonInfo.photoPath5 = photoPath5;
					MiniPersonInfo.compress_photoPath5 = ImageDispose.getPath(
							"byxf", "new05");
					dialog.dismissDialog();
					Intent intent5 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					intent5.putExtra(MediaStore.EXTRA_OUTPUT,
							Uri.fromFile(new File(photoPath5)));
					startActivityForResult(intent5, 5);
				}
			});
			break;
		case R.id.bt_example:
			Intent intent6 = new Intent(RelaxedLoanEightCertificateInfo.this,
					ShowPicture.class);
			startActivity(intent6);

			break;
		case R.id.imageView51:
			// buttonId = 4;
			// getPhoto(buttonId);

			dialog.showDialog();
			dialog.setOnDialogClickListener(new OnDialogClickListener() {

				@Override
				public void onPos() {
					// TODO Auto-generated method stub
					photoPath51 = ImageDispose.getPath("byxf", "051");
					MiniPersonInfo.photoPath51 = photoPath51;
					MiniPersonInfo.compress_photoPath51 = ImageDispose.getPath(
							"byxf", "new051");
					dialog.dismissDialog();
					Intent intent = new Intent();
					intent.setAction("android.intent.action.PICK");
					intent.setType("image/*");
					intent.addCategory(Intent.CATEGORY_DEFAULT);
					startActivityForResult(intent, 511);
				}

				@Override
				public void onNeg() {
					// TODO Auto-generated method stub
					photoPath51 = ImageDispose.getPath("byxf", "051");
					MiniPersonInfo.photoPath51 = photoPath51;
					MiniPersonInfo.compress_photoPath51 = ImageDispose.getPath(
							"byxf", "new051");
					dialog.dismissDialog();
					Intent intent51 = new Intent(
							MediaStore.ACTION_IMAGE_CAPTURE);
					intent51.putExtra(MediaStore.EXTRA_OUTPUT,
							Uri.fromFile(new File(photoPath51)));
					startActivityForResult(intent51, 51);
				}
			});

			break;
		case R.id.imageView52:
			// buttonId = 4;
			// getPhoto(buttonId);

			dialog.showDialog();
			dialog.setOnDialogClickListener(new OnDialogClickListener() {

				@Override
				public void onPos() {
					// TODO Auto-generated method stub
					photoPath52 = ImageDispose.getPath("byxf", "052");
					MiniPersonInfo.photoPath52 = photoPath52;
					MiniPersonInfo.compress_photoPath52 = ImageDispose.getPath(
							"byxf", "new052");
					dialog.dismissDialog();
					Intent intent = new Intent();
					intent.setAction("android.intent.action.PICK");
					intent.setType("image/*");
					intent.addCategory(Intent.CATEGORY_DEFAULT);
					startActivityForResult(intent, 521);
				}

				@Override
				public void onNeg() {
					// TODO Auto-generated method stub
					photoPath52 = ImageDispose.getPath("byxf", "052");
					MiniPersonInfo.photoPath52 = photoPath52;
					MiniPersonInfo.compress_photoPath52 = ImageDispose.getPath(
							"byxf", "new052");
					dialog.dismissDialog();
					Intent intent52 = new Intent(
							MediaStore.ACTION_IMAGE_CAPTURE);
					intent52.putExtra(MediaStore.EXTRA_OUTPUT,
							Uri.fromFile(new File(photoPath52)));
					startActivityForResult(intent52, 52);
				}
			});

			break;
		case R.id.imageView53:
			// buttonId = 4;
			// getPhoto(buttonId);

			dialog.showDialog();
			dialog.setOnDialogClickListener(new OnDialogClickListener() {

				@Override
				public void onPos() {
					// TODO Auto-generated method stub
					photoPath53 = ImageDispose.getPath("byxf", "053");
					MiniPersonInfo.photoPath53 = photoPath53;
					MiniPersonInfo.compress_photoPath53 = ImageDispose.getPath(
							"byxf", "new053");
					dialog.dismissDialog();
					Intent intent = new Intent();
					intent.setAction("android.intent.action.PICK");
					intent.setType("image/*");
					intent.addCategory(Intent.CATEGORY_DEFAULT);
					startActivityForResult(intent, 531);
				}

				@Override
				public void onNeg() {
					// TODO Auto-generated method stub
					photoPath53 = ImageDispose.getPath("byxf", "053");
					MiniPersonInfo.photoPath53 = photoPath53;
					MiniPersonInfo.compress_photoPath53 = ImageDispose.getPath(
							"byxf", "new053");
					dialog.dismissDialog();
					Intent intent53 = new Intent(
							MediaStore.ACTION_IMAGE_CAPTURE);
					intent53.putExtra(MediaStore.EXTRA_OUTPUT,
							Uri.fromFile(new File(photoPath53)));
					startActivityForResult(intent53, 53);
				}
			});

			break;
		case R.id.imageView54:
			// buttonId = 4;
			// getPhoto(buttonId);

			dialog.showDialog();
			dialog.setOnDialogClickListener(new OnDialogClickListener() {

				@Override
				public void onPos() {
					// TODO Auto-generated method stub
					photoPath54 = ImageDispose.getPath("byxf", "054");
					MiniPersonInfo.photoPath54 = photoPath54;
					MiniPersonInfo.compress_photoPath54 = ImageDispose.getPath(
							"byxf", "new054");
					dialog.dismissDialog();
					Intent intent = new Intent();
					intent.setAction("android.intent.action.PICK");
					intent.setType("image/*");
					intent.addCategory(Intent.CATEGORY_DEFAULT);
					startActivityForResult(intent, 541);
				}

				@Override
				public void onNeg() {
					// TODO Auto-generated method stub
					photoPath54 = ImageDispose.getPath("byxf", "054");
					MiniPersonInfo.photoPath54 = photoPath54;
					MiniPersonInfo.compress_photoPath54 = ImageDispose.getPath(
							"byxf", "new054");
					dialog.dismissDialog();
					Intent intent54 = new Intent(
							MediaStore.ACTION_IMAGE_CAPTURE);
					intent54.putExtra(MediaStore.EXTRA_OUTPUT,
							Uri.fromFile(new File(photoPath54)));
					startActivityForResult(intent54, 54);
				}
			});
			break;
		case R.id.imageView55:
			// buttonId = 4;
			// getPhoto(buttonId);

			dialog.showDialog();
			dialog.setOnDialogClickListener(new OnDialogClickListener() {

				@Override
				public void onPos() {
					// TODO Auto-generated method stub
					photoPath55 = ImageDispose.getPath("byxf", "055");
					MiniPersonInfo.photoPath55 = photoPath55;
					MiniPersonInfo.compress_photoPath55 = ImageDispose.getPath(
							"byxf", "new055");
					dialog.dismissDialog();
					Intent intent = new Intent();
					intent.setAction("android.intent.action.PICK");
					intent.setType("image/*");
					intent.addCategory(Intent.CATEGORY_DEFAULT);
					startActivityForResult(intent, 551);
				}

				@Override
				public void onNeg() {
					// TODO Auto-generated method stub
					photoPath55 = ImageDispose.getPath("byxf", "055");
					MiniPersonInfo.photoPath55 = photoPath55;
					MiniPersonInfo.compress_photoPath55 = ImageDispose.getPath(
							"byxf", "new055");
					dialog.dismissDialog();
					Intent intent55 = new Intent(
							MediaStore.ACTION_IMAGE_CAPTURE);
					intent55.putExtra(MediaStore.EXTRA_OUTPUT,
							Uri.fromFile(new File(photoPath55)));
					startActivityForResult(intent55, 55);
				}
			});

			break;
		case R.id.tianjia:
			if (MiniPersonInfo.compress_photoPath5 != null
					&& !MiniPersonInfo.compress_photoPath5.equals("")) {
				if (flag[0]) {
					rl_51.setVisibility(View.VISIBLE);
					rl_tianjia_right.setVisibility(View.GONE);
					rl_tianjia_right2.setVisibility(View.VISIBLE);
					rl_53.setVisibility(View.INVISIBLE);
					flag[0] = false;
					flag[1] = true;
					break;
				}
			} else {
				Toast.makeText(this, "有未拍照的相框，不能添加", Toast.LENGTH_LONG).show();
				break;
			}

			// rl_51.postInvalidate();
		case R.id.tianjia_two:
			if (MiniPersonInfo.compress_photoPath51 != null
					&& !MiniPersonInfo.compress_photoPath51.equals("")) {
				if (flag[1]) {
					rl_52.setVisibility(View.VISIBLE);
					rl_53.setVisibility(View.GONE);
					flag[1] = false;
					flag[2] = true;
					break;
				}
			} else {
				Toast.makeText(this, "有未拍照的相框，不能添加", Toast.LENGTH_LONG).show();
				break;
			}
			if (MiniPersonInfo.compress_photoPath52 != null
					&& !MiniPersonInfo.compress_photoPath52.equals("")) {
				if (flag[2]) {
					rl_53.setVisibility(View.VISIBLE);
					rl_tianjia_right2.setVisibility(View.GONE);
					rl_tianjia_right3.setVisibility(View.VISIBLE);
					rl_55.setVisibility(View.INVISIBLE);
					rl_54.setVisibility(View.GONE);
					flag[2] = false;
					flag[3] = true;
					break;
				}
			} else {
				Toast.makeText(this, "有未拍照的相框，不能添加", Toast.LENGTH_LONG).show();
				break;
			}

			break;
		case R.id.tianjia_three:
			if (MiniPersonInfo.compress_photoPath53 != null
					&& !MiniPersonInfo.compress_photoPath53.equals("")) {
				if (flag[3]) {
					rl_54.setVisibility(View.VISIBLE);
					rl_55.setVisibility(View.GONE);
					flag[3] = false;
					flag[4] = true;
					break;
				}
			} else {
				Toast.makeText(this, "有未拍照的相框，不能添加", Toast.LENGTH_LONG).show();
				break;
			}
			if (MiniPersonInfo.compress_photoPath54 != null
					&& !MiniPersonInfo.compress_photoPath54.equals("")) {
				if (flag[4]) {
					rl_55.setVisibility(View.VISIBLE);
					rl_tianjia_right3.setVisibility(View.GONE);
					flag[4] = false;
					break;
				}
			} else {
				Toast.makeText(this, "有未拍照的相框，不能添加", Toast.LENGTH_LONG).show();
				break;
			}

			break;
		}
		super.onClick(v);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		ImageCompress compress = new ImageCompress();
		ContentResolver resolver = getContentResolver();
		if (requestCode == 1) {
			if (resultCode == RESULT_CANCELED) {
				MiniPersonInfo.compress_photoPath1 = null;
				MiniPersonInfo.photoPath1 = null;
				return;
			}
			getBitmap1(compress, MiniPersonInfo.photoPath1,
					MiniPersonInfo.compress_photoPath1, imageList.get(0));
			System.out.println(MiniPersonInfo.compress_photoPath1);
		}

		if (requestCode == 11) {
			if (resultCode == RESULT_CANCELED) {
				MiniPersonInfo.compress_photoPath1 = null;
				MiniPersonInfo.photoPath1 = null;
				return;
			}
			getBitmap2(data, compress, resolver, MiniPersonInfo.photoPath1,
					MiniPersonInfo.compress_photoPath1, imageList.get(0),
					"new01");
		}

		if (requestCode == 2) {
			if (resultCode == RESULT_CANCELED) {
				MiniPersonInfo.compress_photoPath2 = null;
				MiniPersonInfo.photoPath2 = null;
				return;
			}
			getBitmap1(compress, MiniPersonInfo.photoPath2,
					MiniPersonInfo.compress_photoPath2, imageList.get(1));
		}

		if (requestCode == 21) {
			if (resultCode == RESULT_CANCELED) {
				MiniPersonInfo.compress_photoPath2 = null;
				MiniPersonInfo.photoPath2 = null;
				return;
			}
			getBitmap2(data, compress, resolver, MiniPersonInfo.photoPath2,
					MiniPersonInfo.compress_photoPath2, imageList.get(1),
					"new02");
		}

		if (requestCode == 3) {
			if (resultCode == RESULT_CANCELED) {
				MiniPersonInfo.compress_photoPath3 = null;
				MiniPersonInfo.photoPath3 = null;
				return;
			}
			getBitmap1(compress, MiniPersonInfo.photoPath3,
					MiniPersonInfo.compress_photoPath3, imageList.get(2));
		}

		if (requestCode == 31) {
			if (resultCode == RESULT_CANCELED) {
				MiniPersonInfo.compress_photoPath3 = null;
				MiniPersonInfo.photoPath3 = null;
				return;
			}
			getBitmap2(data, compress, resolver, MiniPersonInfo.photoPath3,
					MiniPersonInfo.compress_photoPath3, imageList.get(2),
					"new03");
		}

		if (requestCode == 4) {
			if (resultCode == RESULT_CANCELED) {
				MiniPersonInfo.compress_photoPath4 = null;
				MiniPersonInfo.photoPath4 = null;
				return;
			}
			getBitmap1(compress, MiniPersonInfo.photoPath4,
					MiniPersonInfo.compress_photoPath4, imageList.get(3));
		}

		if (requestCode == 41) {
			if (resultCode == RESULT_CANCELED) {
				MiniPersonInfo.compress_photoPath4 = null;
				MiniPersonInfo.photoPath4 = null;
				return;
			}
			getBitmap2(data, compress, resolver, MiniPersonInfo.photoPath4,
					MiniPersonInfo.compress_photoPath4, imageList.get(3),
					"new04");
		}

		if (requestCode == 5) {
			if (resultCode == RESULT_CANCELED) {
				MiniPersonInfo.compress_photoPath5 = null;
				MiniPersonInfo.photoPath5 = null;
				return;
			}
			getBitmap1(compress, MiniPersonInfo.photoPath5,
					MiniPersonInfo.compress_photoPath5, imageList.get(4));
		}

		if (requestCode == 501) {
			if (resultCode == RESULT_CANCELED) {
				MiniPersonInfo.compress_photoPath5 = null;
				MiniPersonInfo.photoPath5 = null;
				return;
			}
			getBitmap2(data, compress, resolver, MiniPersonInfo.photoPath5,
					MiniPersonInfo.compress_photoPath5, imageList.get(4),
					"new05");
		}

		if (requestCode == 51) {
			if (resultCode == RESULT_CANCELED) {
				MiniPersonInfo.compress_photoPath51 = null;
				MiniPersonInfo.photoPath51 = null;
				return;
			}
			getBitmap1(compress, MiniPersonInfo.photoPath51,
					MiniPersonInfo.compress_photoPath51, imageList.get(5));
		}

		if (requestCode == 511) {
			if (resultCode == RESULT_CANCELED) {
				MiniPersonInfo.compress_photoPath51 = null;
				MiniPersonInfo.photoPath51 = null;
				return;
			}
			getBitmap2(data, compress, resolver, MiniPersonInfo.photoPath51,
					MiniPersonInfo.compress_photoPath51, imageList.get(5),
					"new051");
		}

		if (requestCode == 52) {
			if (resultCode == RESULT_CANCELED) {
				MiniPersonInfo.compress_photoPath52 = null;
				MiniPersonInfo.photoPath52 = null;
				return;
			}
			getBitmap1(compress, MiniPersonInfo.photoPath52,
					MiniPersonInfo.compress_photoPath52, imageList.get(6));
		}

		if (requestCode == 521) {
			if (resultCode == RESULT_CANCELED) {
				MiniPersonInfo.compress_photoPath52 = null;
				MiniPersonInfo.photoPath52 = null;
				return;
			}
			getBitmap2(data, compress, resolver, MiniPersonInfo.photoPath52,
					MiniPersonInfo.compress_photoPath52, imageList.get(6),
					"new052");
		}

		if (requestCode == 53) {
			if (resultCode == RESULT_CANCELED) {
				MiniPersonInfo.compress_photoPath53 = null;
				MiniPersonInfo.photoPath53 = null;
				return;
			}
			getBitmap1(compress, MiniPersonInfo.photoPath53,
					MiniPersonInfo.compress_photoPath53, imageList.get(7));
		}

		if (requestCode == 531) {
			if (resultCode == RESULT_CANCELED) {
				MiniPersonInfo.compress_photoPath53 = null;
				MiniPersonInfo.photoPath53 = null;
				return;
			}
			getBitmap2(data, compress, resolver, MiniPersonInfo.photoPath53,
					MiniPersonInfo.compress_photoPath53, imageList.get(7),
					"new053");
		}

		if (requestCode == 54) {
			if (resultCode == RESULT_CANCELED) {
				MiniPersonInfo.compress_photoPath54 = null;
				MiniPersonInfo.photoPath54 = null;
				return;
			}
			getBitmap1(compress, MiniPersonInfo.photoPath54,
					MiniPersonInfo.compress_photoPath54, imageList.get(8));
		}

		if (requestCode == 541) {
			if (resultCode == RESULT_CANCELED) {
				MiniPersonInfo.compress_photoPath54 = null;
				MiniPersonInfo.photoPath54 = null;
				return;
			}
			getBitmap2(data, compress, resolver, MiniPersonInfo.photoPath54,
					MiniPersonInfo.compress_photoPath54, imageList.get(8),
					"new054");
		}

		if (requestCode == 55) {
			if (resultCode == RESULT_CANCELED) {
				MiniPersonInfo.compress_photoPath55 = null;
				MiniPersonInfo.photoPath55 = null;
				return;
			}
			getBitmap1(compress, MiniPersonInfo.photoPath55,
					MiniPersonInfo.compress_photoPath55, imageList.get(9));
		}

		if (requestCode == 551) {
			if (resultCode == RESULT_CANCELED) {
				MiniPersonInfo.compress_photoPath55 = null;
				MiniPersonInfo.photoPath55 = null;
				return;
			}
			getBitmap2(data, compress, resolver, MiniPersonInfo.photoPath55,
					MiniPersonInfo.compress_photoPath55, imageList.get(9),
					"new055");
		}

	}

	private void getBitmap1(ImageCompress compress, String path1,
			String comPath, ImageView imageView) {
		Bitmap bitmap = ViewTools.getBitMap(path1, 10);
		Bitmap comBitmap = compress.bitmapComp(path1);
		saveJPGE_After(comBitmap, comPath);
		Bitmap newBitmap = ViewTools.cutCreateBitMap(comBitmap);
		imageView.setImageBitmap(newBitmap);
		if (bitmap != null && !bitmap.isRecycled()) {
			comBitmap.recycle();
			comBitmap = null;
			bitmap.recycle();
			bitmap = null;
			System.gc();
		}
	}

	private void getBitmap2(Intent data, ImageCompress compress,
			ContentResolver resolver, String path1, String comPath,
			ImageView imageView, String newPath) {
		byte[] bitmap = null;
		Uri uri = data.getData();
		try {
			bitmap = readStream(resolver.openInputStream(Uri.parse(uri
					.toString())));
		} catch (Exception e) {
			// TODO: handle exception
		}
		Bitmap mBitmap = getPicFromBytes(bitmap, null);
		if (mBitmap != null) {
			// 获取图片路径
			String[] proj = { MediaStore.Images.Media.DATA };// 多媒体数据库的封装接口
			@SuppressWarnings("deprecation")
			Cursor cursor = managedQuery(uri, proj, null, null, null);
			int index = cursor
					.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			cursor.moveToFirst();
			path1 = cursor.getString(index);
			mBitmap = compress.bitmapComp(path1);
			Bitmap bitmap2 = ViewTools.cutCreateBitMap(mBitmap);
			imageView.setImageBitmap(bitmap2);
			String path = saveJPGE_After(mBitmap, comPath);
			comPath = path;
		} else {
			path1 = null;
			comPath = null;
			Toast.makeText(RelaxedLoanEightCertificateInfo.this, "照片有问题!", Toast.LENGTH_LONG)
					.show();
		}

		if (mBitmap != null && !mBitmap.isRecycled()) {
			mBitmap.recycle();
			mBitmap = null;
			System.gc();
		}
	}

	/**
	 * 质量压缩
	 * 
	 * @param image
	 * @return
	 */
	private Bitmap compressImage(Bitmap image) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int options = 90;
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中

		while (baos.toByteArray().length / 2048 > 200) { // 循环判断如果压缩后图片是否大于200kb,大于继续压缩

			// System.out.println("现在的图片大小是=========》》》》》"
			// + baos.toByteArray().length);
			baos.reset();// 重置baos即清空baos
			options -= 10;// 每次都减少10
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
	private Bitmap comp(Bitmap image) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		if (baos.toByteArray().length / 2048 > 128) {// 判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
			baos.reset();// 重置baos即清空baos
			image.compress(Bitmap.CompressFormat.JPEG, 90, baos);// 这里压缩50%，把压缩后的数据存放到baos中
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		// 开始读入图片，此时把options.inJustDecodeBounds 设回true了
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

	/**
	 * 将选择的图片通过IO流读入内存中
	 * 
	 * @param inStream
	 * @return
	 * @throws Exception
	 */
	private byte[] readStream(InputStream inStream) throws Exception {
		byte[] buffer = new byte[1024];
		int len = -1;
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		byte[] data = outStream.toByteArray();
		outStream.close();
		inStream.close();
		return data;

	}

	/**
	 * 将byte数组写成图片
	 * 
	 * @param bytes
	 * @param opts
	 * @return
	 */
	private Bitmap getPicFromBytes(byte[] bytes, BitmapFactory.Options opts) {
		if (bytes != null)
			if (opts != null) {
				try {
					return BitmapFactory.decodeByteArray(bytes, 0,
							bytes.length, opts);
				} catch (OutOfMemoryError e) {
				}

			} else {
				try {
					return BitmapFactory
							.decodeByteArray(bytes, 0, bytes.length);
				} catch (OutOfMemoryError e) {
				}
			}
		return null;
	}

	/**
	 * 保存图片为JPEG
	 * 
	 * @param bitmap
	 * @param path
	 */
	public String saveJPGE_After(Bitmap bitmap, String path) {
		File file = new File(path);
		try {
			FileOutputStream out = new FileOutputStream(file);
			if (bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)) {
				out.flush();
				out.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
}