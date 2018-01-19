package com.victtech.cfapp.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.victtech.cfapp.fragment.ChuFang1Fragment;
import com.victtech.cfapp.fragment.ChuFang2Fragment;
import com.victtech.cfapp.fragment.ChuFang3Fragment;
import com.victtech.cfapp.fragment.ChuFangBaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Richard on 2018/1/18.
 * 处方转换页面的适配器
 */

public class ConvertFragmentAdapter extends FragmentStatePagerAdapter {
    private String[] typeList = {"未处理","有效处方","无效处方"};
    private List<ChuFangBaseFragment> mList = new ArrayList<>();

    public ConvertFragmentAdapter(FragmentManager fm) {
        super(fm);
        for(int i=0;i<typeList.length;i++){
            switch (typeList[i]){
                case "未处理":
                    mList.add(new ChuFang1Fragment(typeList[i]));
                    break;
                case "有效处方":
                    mList.add(new ChuFang2Fragment(typeList[i]));
                    break;
                case "无效处方":
                    mList.add(new ChuFang3Fragment(typeList[i]));
                    break;
            }
        }

    }


    @Override
    public ChuFangBaseFragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return typeList.length;
    }


}
