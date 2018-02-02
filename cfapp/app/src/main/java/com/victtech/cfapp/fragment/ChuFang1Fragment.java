package com.victtech.cfapp.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.victtech.cfapp.CFApplication;
import com.victtech.cfapp.R;
import com.victtech.cfapp.adapter.ChuFang1Adapter;
import com.victtech.http.HttpCallBackLisioner;
import com.victtech.http.HttpUtil;
import com.victtech.http.ParseJson;
import com.victtech.model.BaseModel;
import com.victtech.model.ChuFangModel;
import com.victtech.model.ErrorModel;
import com.victtech.model.entity.ChuFang;
import com.victtech.model.entity.Paginator;
import com.victtech.tools.LogUtil;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Richard on 2018/1/18.
 */

@SuppressLint("ValidFragment")
public class ChuFang1Fragment extends ChuFangBaseFragment implements OnRefreshListener {
    private View view;
    private List<ChuFang> mList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    final ChuFang1Fragment frag1 = this;
    private ChuFang1Adapter adapter;
    private boolean loading = false;
    private boolean finished = false;
    private int nextPage = 1;
    public ChuFang1Fragment(String type){
        this.setType(type);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.chufang1_layout,container,false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView =  view.findViewById(R.id.cf1_card_list);
        recyclerView.addOnScrollListener(new ChufangScrollListener(recyclerView));
        recyclerView.setLayoutManager(linearLayoutManager);
        swipeRefreshLayout = view.findViewById(R.id.cf1_swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setRefreshing(true);
        initData();
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case CHUFANG_PROCESS:
                if(resultCode == RESULT_OK){
                    int result = data.getIntExtra("return",-1);
                    Toast.makeText(this.getContext(),"处理结束:"+result,Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    private void initData(){
        getData(1);
    }

    private void refresh(){
        getData(1);
    }

    private void getData(final int page){

        String token = null;
        try {
            token = CFApplication.getToken();
            LogUtil.d("token======","|||||||"+token);
            HttpUtil.getRequest("recipes?status=5&page="+page, new HttpCallBackLisioner() {
                @Override
                public void onFinish(String requestString) {
                    if(page == 1) {
                        mList.clear();
                        loading = false;
                    }
                    LogUtil.d("++++++++++处方列表",requestString);
                    try {
                        BaseModel baseModel = ParseJson.parseJson(requestString, ChuFangModel.class);
                        if(baseModel instanceof ErrorModel){
                            UIToast(baseModel.getMessage());
                            getActivity().finish();
                        }else{
                            ChuFangModel modelList = (ChuFangModel)baseModel;
                            Paginator paginator = modelList.getData().getPaginator().getPaginator();
                            nextPage = paginator.getCurrent_page()+1;
                            if(page > paginator.getLast_page()){
                                finished = true;
                            }else{
                                ChuFang[] chuFangs = modelList.getData().getData();
                                for(int i=0; i<chuFangs.length;i++){
                                    chuFangs[i].setAvatar("https://static.victtech.com/"+chuFangs[i].getRecipe_img());
                                    mList.add(chuFangs[i]);
                                    LogUtil.d("--------------",chuFangs[i].getCreated_at());
                                }
                                loading = false;
                            }
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if(page == 1){
                                        adapter = new ChuFang1Adapter(mList,frag1);
                                        recyclerView.setAdapter(adapter);
                                        swipeRefreshLayout.setRefreshing(false);
                                    }else if(!finished){
                                        adapter.hideFooter();
                                        adapter.notifyDataSetChanged();
                                    }else{
                                        adapter.end();
                                        adapter.notifyDataSetChanged();
                                    }
                                }
                            });
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        UIToast("网络请求失败");
                    }
                }
                @Override
                public void onError(Exception e) {
                    e.printStackTrace();
                    UIToast("网络请求失败");
                }
            },token);
        } catch (JSONException e) {
            e.printStackTrace();
            UIToast("Token获取失败");
        }
    }

    class ChufangScrollListener extends RecyclerView.OnScrollListener {
        private RecyclerView cycleView;
        public ChufangScrollListener(RecyclerView cycleView){
            this.cycleView = cycleView;
        }
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
            int totalItem = manager.getItemCount();
            int lastItem = manager.findLastVisibleItemPosition();
            if(!loading && (lastItem+2) > totalItem){
                adapter.showFooter(cycleView);
                loading = true;
                LogUtil.d("---------",String.valueOf(nextPage) );
                getData(nextPage);


            }

        }
    }
}
