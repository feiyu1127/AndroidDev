package com.yucheng.byxf.mini.miniLoan.fragment;

import com.yucheng.byxf.mini.ui.HomeActivity;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.DialogUtil;
import com.yucheng.byxf.mini.utils.SharedPreferencesUtils;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 卡面选择Fragment
 * 
 */
public class MiniCreditCardInfoFragment extends Fragment implements
		OnClickListener {
	private ImageButton img1;
	private ImageButton img2;
	private Button bt_next;
	private int result = 0;
	private ImageView back2;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		View view = inflater.inflate(R.layout.minicardselect, container, false);
		img1 = (ImageButton) view.findViewById(R.id.image1);
		img2 = (ImageButton) view.findViewById(R.id.image2);
		img1.setOnClickListener(this);
		img2.setOnClickListener(this);
		bt_next = (Button) view.findViewById(R.id.next_button);
		bt_next.setOnClickListener(this);
		back2=(ImageView) view.findViewById(R.id.back2);
		back2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				FragmentManager fm = getFragmentManager();
				fm.popBackStack();
			}
		});
		SharedPreferencesUtils preferencesUtils = new SharedPreferencesUtils(
				getActivity(), "MiniSix");
		MiniApplyInfo miniApplyInfo = preferencesUtils.readObj();
		if (miniApplyInfo != null) {
			if (miniApplyInfo.getIdCard().equals(
					Contents.response.getResult().getIdNo())) {
				System.out.println("----------------------------------"+miniApplyInfo.getCardxuanze());
				if (miniApplyInfo.getCardxuanze().equals("A")) {
					img1.setBackgroundResource(R.drawable.ob);
					img1.setFocusable(true);
					img2.setBackgroundResource(R.drawable.ts);
					result = 1;
				} else if (miniApplyInfo.getCardxuanze().equals("B")) {
					img2.setBackgroundResource(R.drawable.tb);
					img2.setFocusable(true);
					img1.setBackgroundResource(R.drawable.os);
					result = 2;
				}
			}
		}


		return view;

	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public void onClick(View v) {

		MiniApplyInfo info = new MiniApplyInfo();

		switch (v.getId()) {
		case R.id.image1:
			img1.setBackgroundResource(R.drawable.ob);
			img1.setTag("1");
			img2.setBackgroundResource(R.drawable.ts);
			img2.setTag("0");
			result = 1;
			info.setFlag(1);
			break;

		case R.id.image2:
			img2.setBackgroundResource(R.drawable.tb);
			img1.setTag("0");
			img1.setBackgroundResource(R.drawable.os);
			img2.setTag("1");
			result = 2;
			info.setFlag(2);
			break;

		case R.id.next_button:
			if (result == 0) {
				DialogUtil.showDialogOneButton2(getActivity(), "请选择卡的样式！");
				return;
			} else {
				MiniInfoConfirmFragment aaa = new MiniInfoConfirmFragment();
				FragmentManager fma = getFragmentManager();
				FragmentTransaction txa = fma.beginTransaction();
				txa.replace(R.id.id_content, aaa, "Nine");
				txa.addToBackStack("Eight");
				txa.commit();
			}
			MiniApplyInfo saveapplyinfo = new MiniApplyInfo();
			SharedPreferencesUtils preferencesUtils = new SharedPreferencesUtils(
					getActivity(), "MiniSix");
			saveapplyinfo.setIdCard(Contents.response.getResult().getIdNo());
			if (img1.getTag().equals("1")) {
				System.out.println(img1.getTag()+"..........................................");
				saveapplyinfo.setCardxuanze("A");
			} else if (img2.getTag().equals("1")) {
				System.out.println(img2.getTag()+"..........................................");
				saveapplyinfo.setCardxuanze("B");
			}
			preferencesUtils.saveObj(saveapplyinfo);

			MiniInfoConfirmFragment fff = new MiniInfoConfirmFragment();
			FragmentManager fm = getFragmentManager();
			FragmentTransaction tx = fm.beginTransaction();
			tx.replace(R.id.id_content, fff, "Nine");
			tx.addToBackStack("Eight");
			tx.commit();

			break;

		default:
			break;
		}

	}
}
