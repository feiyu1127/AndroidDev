package com.victtech.cfapp.fragment;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import com.victtech.cfapp.CFApplication;

/**
 * Created by Richard on 2018/1/18.
 */

public class ChuFangBaseFragment extends Fragment {
    public static final int CHUFANG_PROCESS = 1;
    public static final int CHUFANG_LOOK = 2;
    private String type;
    protected boolean isVisible;
    private boolean loadOnce; //是否加载过数据
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()){
            isVisible = true;
            onVisible();
        }else{
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible(){
        if(!isVisible || loadOnce){
            return;
        }else{
            lazyLoad();
        }
        loadOnce = true;

    }

    protected void onInvisible(){}

    protected void lazyLoad(){

    }

    protected void UIToast(final String msg){
        final String tmpMsg = msg;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(CFApplication.getContext(),msg,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
