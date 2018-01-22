package com.example.admin.chufang.fragments;

import android.support.v4.app.Fragment;

/**
 * Created by admin on 2018/1/22.
 */

public class BaseRecipeFragment extends Fragment {
    private String type; //处方类型,有效/无效/未处理

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
