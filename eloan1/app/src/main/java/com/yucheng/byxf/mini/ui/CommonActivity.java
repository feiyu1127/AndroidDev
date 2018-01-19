package com.yucheng.byxf.mini.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class CommonActivity extends BaseAllActivity {
	
	private SimpleAdapter adapter;
	private List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	private ListView listView;
	private ImageView back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.common_activity);
		initData();
		initView();
	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		// TODO Auto-generated method stub
		back = (ImageView) findViewById(R.id.back);
		listView = (ListView) findViewById(R.id.listview);
		adapter = new SimpleAdapter(CommonActivity.this, list,
				R.layout.common_listitem, new String[] { "title", "content" },
				new int[] { R.id.title, R.id.content });

		listView.setAdapter(adapter);
		
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

	/**
	 * 初始化数据源
	 */
	private void initData() {
		// TODO Auto-generated method stub		
		String[] title = getResources().getStringArray(R.array.common_title);
		String[] content = getResources().getStringArray(R.array.common_content);
		
		for (int i = 0; i < title.length; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("title", title[i] + "");
			map.put("content", content[i] + "");
			list.add(map);
		}
	}
}
