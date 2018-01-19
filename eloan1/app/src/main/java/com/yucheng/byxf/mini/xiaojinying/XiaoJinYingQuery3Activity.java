package com.yucheng.byxf.mini.xiaojinying;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.yucheng.byxf.mini.ui.BaseActivity;
import com.yucheng.byxf.mini.ui.R;
import com.yucheng.byxf.mini.utils.Contents;
import com.yucheng.byxf.mini.utils.NumberUtil;
import com.yucheng.byxf.mini.utils.RoundProgressBar;

public class XiaoJinYingQuery3Activity extends BaseActivity {
	private TextView tx_keyongedu;

	private TextView tx_yiyongedu;

	private TextView tx_renminbi;

	// 信用额度_TextView
	private TextView xinyongerdu_new;

	private ImageView back;

	// 画小圆圈
	private RoundProgressBar mRoundProgressBar1;

	private int progress = 0;

	Double yiyongedu;

	/**
	 * 总额
	 */
	private Double crAmt;

	/**
	 * 溢缴款
	 */
	private Double osAmt;

	/**
	 * 可用额度
	 */
	private Double availAmt;

	/**
	 * 查询页面2 明细查询
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.xiaojinyin_eduxiangqing_activity);
		back = (ImageView) findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				finish();
			}
		});
		tx_keyongedu = (TextView) findViewById(R.id.tx_keyongedu);
		tx_yiyongedu = (TextView) findViewById(R.id.tx_yiyongedu);
		tx_renminbi = (TextView) findViewById(R.id.tv_renminbi);
		// 信用额度
		xinyongerdu_new = (TextView) findViewById(R.id.xinyongerdu_new);

		mRoundProgressBar1 = (RoundProgressBar) findViewById(R.id.roundProgressBar1);

		mRoundProgressBar1.setCricleColor(Color.parseColor("#6fceea"));
		mRoundProgressBar1.setCricleProgressColor(Color.parseColor("#ff6c00"));

		tx_keyongedu.setText("¥ "
				+ String.valueOf(NumberUtil.decimalFormat(Double
						.valueOf(Contents.xiaoChaXunResult2.getAvailAmt()))));
		Log.d("============================",
				Contents.xiaoChaXunResult2.getAvailAmt());
		Log.d("============================",
				Contents.xiaoChaXunResult2.getCrAmt());
		// 已用额度=信用额度+溢缴款-可用额度
		// =crAmt+osAmt-availAmt

		if (Contents.xiaoChaXunResult2.getCrAmt() != null) {
			// 信用额度
			crAmt = Double.valueOf(Contents.xiaoChaXunResult2.getCrAmt());
			xinyongerdu_new.setText("¥ "
					+ String.valueOf(NumberUtil.decimalFormat(crAmt)));
		}

		if (Contents.xiaoChaXunResult2.getOsAmt() != null) {
			osAmt = Double.valueOf(Contents.xiaoChaXunResult2.getOsAmt());
		}

		if (Contents.xiaoChaXunResult2.getAvailAmt() != null) {
			// 可用额度
			availAmt = Double.valueOf(Contents.xiaoChaXunResult2.getAvailAmt());
		}

		// yiyongedu = crAmt + osAmt - availAmt;

		yiyongedu = NumberUtil.sub(NumberUtil.add(crAmt, osAmt), availAmt);

		tx_yiyongedu.setText("¥ "
				+ String.valueOf(NumberUtil.decimalFormat(yiyongedu)));// 已用额度

		thread.start();

	}

	Thread thread = new Thread(new Runnable() {
		@Override
		public void run() {
			// 进度条进分比 初始为0
			int baifenbi = 0;

			// 如果总额不等于0
			if (crAmt != 0) {
				// 可用余额百分比
				Double keyong = NumberUtil.mul(
						NumberUtil.div(yiyongedu, crAmt), 100);
				// 转成字符串
				String keyongString = String.valueOf(keyong);
				// 切割 字符串
				String[] array = keyongString.split("\\.");

				if (array.length > 0) {
					// 取整 数位
					baifenbi = Integer.parseInt(array[0]);

					if (baifenbi < 1) { // 小于1的情况下看小数位

						if (array.length > 1) { // 有小数位 则 取约等1
							baifenbi = 1;
						}
					
					}
				}
			}

			if (yiyongedu<=0) {
				baifenbi=-1;
			}

			
			while (progress < baifenbi) {
				progress += 1;

				mRoundProgressBar1.setProgress(progress);

				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	});
}
