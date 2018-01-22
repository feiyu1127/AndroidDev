package com.example.admin.chufang.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.chufang.R;
import com.example.admin.chufang.adapter.ConvertFragmentAdapter;

/**
 * Created by admin on 2018/1/22.
 */

public class ConvertFragment extends Fragment {
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.convert_layout,container,false);
        return rootView;
    }

    /**
     * onCreateView是创建的时候调用，onViewCreated是在onCreateView后被触发的事件.且onStart运行时间位于onViewCreated之后
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initData();
        super.onViewCreated(view, savedInstanceState);
    }

    private void initData(){
        ViewPager viewPager = rootView.findViewById(R.id.vp_content);

        ConvertFragmentAdapter adapter = new ConvertFragmentAdapter(getFragmentManager());
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = rootView.findViewById(R.id.tl_tab);
        tabLayout.setupWithViewPager(viewPager);

        for(int i=0;i<adapter.getCount();i++){
            tabLayout.getTabAt(i).setText(adapter.getItem(i).getType());
        }

    }

}
