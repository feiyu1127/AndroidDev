package com.yucheng.byxf.mini.miniLoan.fragment;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.yucheng.apfx.net.HttpHelper;
import com.yucheng.byxf.mini.message.CustInfoResponseResult;
import com.yucheng.byxf.mini.miniLoan.MiniLoanActivity;
import com.yucheng.byxf.mini.ui.BaseActivity;
import com.yucheng.byxf.mini.ui.HomeActivity;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.ContantsAddress;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.ViewTools;
import com.yucheng.byxf.mini.views.ProgressView;
import com.yucheng.byxf.mini.xiaojinying.XiaoJinYingAdActivity;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * 
 * 合约书Fragment
 * 
 */
public class MiniRiskPromptFragment extends Fragment implements OnClickListener {

	private Button mini_loan_btn_disagree_button;
	private Button mini_loan_btn_agree_button;
	private WebView webView;
	private Button disagree_button;
	private ImageView back;
	MiniLoanActivity miniActivity;
	private Dialog dialog;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
//		String url = "http://10.160.7.220:9080/eloan-services/upload/article/mini.html";
		String url = ContantsAddress.MINIBOOK;
		View view = inflater.inflate(R.layout.minifragment1, container, false);
		mini_loan_btn_disagree_button = (Button) view
				.findViewById(R.id.mini_loan_btn_disagree_button);
		mini_loan_btn_disagree_button.setOnClickListener(this);
		mini_loan_btn_agree_button = (Button) view
				.findViewById(R.id.mini_loan_btn_agree_button);
		mini_loan_btn_agree_button.setOnClickListener(this);
		back = (ImageView) view.findViewById(R.id.back1);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				getActivity().finish();
			}
		});

		webView = (WebView) view
				.findViewById(R.id.mini_risk_prompt_loan_webview);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setDefaultTextEncodingName("GBK");
		webView.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});
		webView.loadUrl(url);

		return view;

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.mini_loan_btn_disagree_button:
			getActivity().finish();

			break;
		case R.id.mini_loan_btn_agree_button:
			new getContactInfo().execute("20", Contents.response.getResult()
					.getIdNo());
		}
	}

	class getContactInfo extends AsyncTask<String, Object, Object> {

		@Override
		protected Object doInBackground(String... params) {
			HttpHelper httpHelper = HttpHelper.getInstance(getActivity());
			List<NameValuePair> arg = new ArrayList<NameValuePair>();
			arg.add(new BasicNameValuePair("idType", params[0]));
			arg.add(new BasicNameValuePair("idNo", params[1]));
			return httpHelper.post(ContantsAddress.CUST_INFO, arg,
					CustInfoResponseResult.class);
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			showProgressDialog();
		}

		@Override
		protected void onPostExecute(Object result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			CustInfoResponseResult response = (CustInfoResponseResult) result;
			if (response == null) {
				Toast.makeText(getActivity(), "网络连接不稳定", Toast.LENGTH_LONG)
						.show();
			} else {
				if (response.getCode() == 0) {
					Contents.custInfoResponseResult = response;
					MiniCustomInfoFragment fTwo = new MiniCustomInfoFragment();
					FragmentManager fm = getFragmentManager();
					FragmentTransaction tx = fm.beginTransaction();
					tx.replace(R.id.id_content, fTwo, "two");
					tx.commit();
				} else {
					Toast.makeText(getActivity(), response.getMsg(),
							Toast.LENGTH_LONG).show();
					MiniLoanActivity minil = new MiniLoanActivity();
					minil.finish();
				}

			}
			dismissProgressDialog();
		}
	}

//	public void showProgressDialog() {
//		// TODO Auto-generated method stub
//		if (null != dialog && dialog.isShowing()) {
//			dialog.dismiss();
//		}
//	}

	protected boolean showProgressDialog() {
		boolean isSuccess = false;
		dialog = ProgressView.createLoadingDialog(getActivity());
		dialog.show();
		dialog.setCanceledOnTouchOutside(false);
		dialog.setCancelable(false);
		isSuccess = true;
		return isSuccess;
	}

	protected void dismissProgressDialog() {
		// closeView();
		if (null != dialog  && dialog.isShowing()) {
			dialog.dismiss();
		}
	}
}
