package com.yucheng.byxf.mini.miniLoan.fragment;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MiniFragmentAdapter extends FragmentPagerAdapter {
	private List<Fragment> fragmentList;
	FragmentManager fm;
	public void setFragmentList(List<Fragment> fragmentList) {
		this.fragmentList = fragmentList;
	}

	public MiniFragmentAdapter(FragmentManager fm) {
		super(fm);
		this.fm = fm;
	}

	@Override
	public Fragment getItem(int arg0) {
		return fragmentList.get(arg0);
	}

	@Override
	public int getCount() {
		return fragmentList.size();
	}

}
