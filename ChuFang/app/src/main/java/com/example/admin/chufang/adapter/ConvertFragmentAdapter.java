package com.example.admin.chufang.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.admin.chufang.fragments.BaseRecipeFragment;
import com.example.admin.chufang.fragments.InvalidRecipeFragment;
import com.example.admin.chufang.fragments.UntreatedRecipeFragment;
import com.example.admin.chufang.fragments.ValidRecipeFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/1/18.
 */

public class ConvertFragmentAdapter extends FragmentPagerAdapter {
    private String[] titles = new String[]{
            "有效处方",
            "无效处方",
            "未处理"
    };

    public final int COUNT = titles.length;
    private List<BaseRecipeFragment> mList = new ArrayList<>();


//    private Context context;

    public ConvertFragmentAdapter(FragmentManager fm) {
        super(fm);
        for(int i=0;i<titles.length;i++){
            switch (titles[i]){
                case "有效处方":
                    mList.add(new ValidRecipeFragment(titles[i]));
                    break;
                case "无效处方":
                    mList.add(new InvalidRecipeFragment(titles[i]));
                    break;
                case "未处理":
                    mList.add(new UntreatedRecipeFragment(titles[i]));
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public BaseRecipeFragment getItem(int position) {
        return mList.get(position);
    }


    @Override
    public int getCount() {
        return COUNT;
    }

}
