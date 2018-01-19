package com.yucheng.byxf.mini.miniLoan.fragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.yucheng.apfx.utl.RegexResult;
import com.yucheng.byxf.mini.easyloan.ui.ImageDispose;
import com.yucheng.byxf.mini.map.DemoApplication;
import com.yucheng.byxf.mini.miniLoan.MiniVideoActivity;
import com.yucheng.byxf.mini.rapidly.RapidlyLoanInfoConfrim;
import com.yucheng.byxf.mini.rapidly.RapidlyLoanInfoContents;
import com.yucheng.byxf.mini.rapidly.TranscribeVideoActivity;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.ImageCompress;
import com.yucheng.byxf.mini.utils.NameDialogFragment;
import com.yucheng.byxf.mini.utils.ViewTools;
import com.yucheng.byxf.mini.views.RoundAngleImageView;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Thumbnails;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 证件拍照Fragment
 *
 */
public class MiniIDCardInfoFragment extends Fragment implements OnClickListener {
	// 三个图片按钮
	private RoundAngleImageView img1;
	private RoundAngleImageView img2;
	private RoundAngleImageView img3;
	private RoundAngleImageView img4;

	private String path1;
	private String path2;
	private String path3;
	private String path4;

	private Bitmap mCutBitmap;// 视频截图
	private ImageView back6;

	private Button Next_bt;
	// 验证结果
	private RegexResult result;
	// 信息有误
	private final int INFO_ERROR = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View view = inflater.inflate(R.layout.minifragment7, container, false);
		img1 = (RoundAngleImageView) view.findViewById(R.id.imageViewMini1);
		img2 = (RoundAngleImageView) view.findViewById(R.id.imageViewMini2);
		img3 = (RoundAngleImageView) view.findViewById(R.id.imageViewMini3);
		img4 = (RoundAngleImageView) view.findViewById(R.id.imageViewMini4);
		img1.setOnClickListener((OnClickListener) this);
		img2.setOnClickListener((OnClickListener) this);
		img3.setOnClickListener((OnClickListener) this);
		img4.setOnClickListener((OnClickListener) this);
		Next_bt = (Button) view.findViewById(R.id.next_button);
		Next_bt.setOnClickListener(this);
		back6 = (ImageView) view.findViewById(R.id.back6);
		back6.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				FragmentManager fm = getFragmentManager();
				fm.popBackStack();
			}
		});

		initInfo();
		return view;
	}

	private void initInfo() {
		// TODO Auto-generated method stub
		// String path1 = ImageDispose.getPath("byxfmini", "001");
		// String path2 = ImageDispose.getPath("byxfmini", "002");
		// ImageCompress compress = new ImageCompress();
		// getBitmap1(compress, path1, path1, img1);
		// getBitmap1(compress, path2, path2, img2);
		MiniApplyInfo.MinicomPhoto1Path = null;
		MiniApplyInfo.MinicomPhoto2Path = null;
		MiniApplyInfo.MinicomPhoto4Path = null;
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public void onActivityResult(int reqCode, int rstCode, Intent itt) {
		System.gc();
		String pthOrig = null;
		String pthNail = null;
		Uri uri = null;
		RoundAngleImageView imgView = null;
		switch (reqCode) {
		case 1:
			pthOrig = path1;
			pthNail = MiniApplyInfo.MinicomPhoto1Path;
			imgView = img1;
			break;
		case 2:
			pthOrig = path2;
			pthNail = MiniApplyInfo.MinicomPhoto2Path;
			imgView = img2;
			break;
		case 100:
			mCutBitmap = ThumbnailUtils.createVideoThumbnail(
					Environment.getExternalStorageDirectory() + "/"
							+ MiniApplyInfo.MninvideoPath,
					Thumbnails.FULL_SCREEN_KIND);
			if (mCutBitmap != null) {
				img3.setImageBitmap(mCutBitmap);
			}
			break;
		case 4:
			pthOrig = path4;
			pthNail = MiniApplyInfo.MinicomPhoto4Path;
			imgView = img4;
			break;
		default:
			break;
		}

		if (null != pthOrig) {
			if (-1 == rstCode) {
				try {
					Bitmap bitmap = new ImageCompress().getSmallBitmap(pthOrig,
							imgView.getWidth(), imgView.getHeight());
					if (null != bitmap) {
						saveJPGE_After(bitmap, pthNail);
						if (!bitmap.isRecycled()) {
							bitmap.recycle();
							bitmap = null;
							System.gc();
						}
						uri = Uri.fromFile(new File(pthNail));
					}
					switch (reqCode) {
					case 1:
						MiniApplyInfo.MinicomPhoto1Path = pthOrig;
						ImageCompress compress = new ImageCompress();
						// wz
						getBitmap1(compress, path1,
								MiniApplyInfo.MinicomPhoto1Path, img1);

						break;
					case 2:
						MiniApplyInfo.MinicomPhoto2Path = pthOrig;
						// wz
						ImageCompress compress2 = new ImageCompress();
						getBitmap1(compress2, path2,
								MiniApplyInfo.MinicomPhoto2Path, img2);
						break;
					case 100:
						mCutBitmap = ThumbnailUtils.createVideoThumbnail(
								Environment.getExternalStorageDirectory() + "/"
										+ MiniApplyInfo.MninvideoPath,
								Thumbnails.FULL_SCREEN_KIND);
						if (mCutBitmap != null) {
							img3.setImageBitmap(mCutBitmap);
						}
						break;
					case 4:
						MiniApplyInfo.MinicomPhoto2Path = pthOrig;
						// wz
						ImageCompress compress4 = new ImageCompress();
						getBitmap1(compress4, path4,
								MiniApplyInfo.MinicomPhoto4Path, img4);
						break;

					default:
						break;
					}
				} catch (Exception e) {
					// uri = null;
				}
			}
			if (null != uri) {
				imgView.setImageURI(null);
				imgView.setImageURI(uri);
			} else {
				switch (reqCode) {
				case 1:
					MiniApplyInfo.MinicomPhoto1Path = null;
					path1 = null;
					break;
				case 2:
					MiniApplyInfo.MinicomPhoto2Path = null;
					path2 = null;
					break;
				case 4:
					MiniApplyInfo.MinicomPhoto4Path = null;
					path4 = null;
					break;
				default:
					break;
				}
				imgView.setImageResource(R.drawable.idcard_selector);
			}
		}
		super.onActivityResult(reqCode, rstCode, itt);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imageViewMini1:
			path1 = ImageDispose.getPath("byxfmini", "001");
			Log.d("xxx", "path" + path1);
			MiniApplyInfo.MinicomPhoto1Path = ImageDispose.getPath("byxfmini",
					"001");

			Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			intent1.putExtra(MediaStore.EXTRA_OUTPUT,
					Uri.fromFile(new File(path1)));
			startActivityForResult(intent1, 1);
			break;
		case R.id.imageViewMini2:
			path2 = ImageDispose.getPath("byxfmini", "002");
			Log.d("xxx", "path" + path2);
			MiniApplyInfo.MinicomPhoto2Path = ImageDispose.getPath("byxfmini",
					"002");

			Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			intent2.putExtra(MediaStore.EXTRA_OUTPUT,
					Uri.fromFile(new File(path2)));
			startActivityForResult(intent2, 2);
			break;
		case R.id.imageViewMini3:
			Intent intent = new Intent();
			intent.setClass(getActivity(), MiniVideoActivity.class);
			startActivityForResult(intent, 100);
			break;
		case R.id.imageViewMini4:
			path4 = ImageDispose.getPath("byxfmini", "004");
			Log.d("xxx", "path" + path4);
			MiniApplyInfo.MinicomPhoto4Path = ImageDispose.getPath("byxfmini",
					"004");

			Intent intent4 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			intent4.putExtra(MediaStore.EXTRA_OUTPUT,
					Uri.fromFile(new File(path4)));
			startActivityForResult(intent4, 4);
			break;

		case R.id.next_button:

			// // wz8.4a
			if (null == MiniApplyInfo.MinicomPhoto1Path
					|| "".equals(MiniApplyInfo.MinicomPhoto1Path)) {
				result = new RegexResult(false, "请完成证件拍照！");
				// Toast.makeText(DemoApplication.getApplication(),INFO_ERROR ,
				// 3000);
				showEditDialog(v, result.msg);
				return;
			}
			if (null == MiniApplyInfo.MinicomPhoto2Path
					|| "".equals(MiniApplyInfo.MinicomPhoto2Path)) {
				result = new RegexResult(false, "请完成证件拍照！");
				// Toast.makeText(DemoApplication.getApplication(),INFO_ERROR ,
				// 3000);
				showEditDialog(v, result.msg);
				return;
			}
			//
			// if (null == MiniApplyInfo.MninvideoPath
			// || "".equals(MiniApplyInfo.MninvideoPath)) {
			// result = new RegexResult(false, "请完成视频录制！");
			// showEditDialog(v, result.msg);
			// // Toast.makeText(DemoApplication.getApplication(),INFO_ERROR ,
			// 3000);
			// return;
			// }
			//
			// if(MiniApplyInfo.MninvideoPath.getBytes().length<1){
			// result = new RegexResult(false, "录制的视频有问题，请重新录制");
			// // Toast.makeText(DemoApplication.getApplication(),INFO_ERROR ,
			// 3000);
			// showEditDialog(v, result.msg);
			// }
			//
			// shareinit();
			MiniCreditCardInfoFragment fffs = new MiniCreditCardInfoFragment();
			FragmentManager fms = getFragmentManager();
			FragmentTransaction txs = fms.beginTransaction();
			txs.replace(R.id.id_content, fffs, "Eight");
			txs.addToBackStack("Seven");
			txs.commit();

			break;
		default:
			break;
		}

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
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

	private void getBitmap1(ImageCompress compress, String path1,
			String comPath, ImageView imageView) {
		imageView.setImageBitmap(null);
		Bitmap bitmap = compress.bitmapComp(path1);
		saveJPGE_After(bitmap, comPath);
		Bitmap newBitmap = ViewTools.cutCreateBitMap(bitmap);
		imageView.setImageBitmap(newBitmap);
		if (bitmap != null && !bitmap.isRecycled()) {
			bitmap.recycle();
			bitmap = null;
			System.gc();
		}
	}

	public void showEditDialog(View view, String str) {
		DialogFragment myFragment = NameDialogFragment.newInstance(str);
		myFragment.show(getFragmentManager(), "EditNameDialog");
	}
}
