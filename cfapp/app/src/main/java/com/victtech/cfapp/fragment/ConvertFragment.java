package com.victtech.cfapp.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.victtech.cfapp.R;
import com.victtech.cfapp.adapter.ConvertFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Richard on 2018/1/17.
 */

public class ConvertFragment extends Fragment {
    private View rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.convert_layout,container,false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initData();
        super.onViewCreated(view, savedInstanceState);

    }

    private void initData(){
        ViewPager viewPager = rootView.findViewById(R.id.cf_view);
        viewPager.setOffscreenPageLimit(1);
        ConvertFragmentAdapter adapter = new ConvertFragmentAdapter(getFragmentManager());
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = rootView.findViewById(R.id.cf_tab);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setTabTextColors(getResources().getColor(R.color.colorPrimaryDark,null),getResources().getColor(R.color.colorPrimaryDark,null));
        for(int i=0;i<adapter.getCount();i++){
            tabLayout.getTabAt(i).setText(adapter.getItem(i).getType());
        }
    }
}
