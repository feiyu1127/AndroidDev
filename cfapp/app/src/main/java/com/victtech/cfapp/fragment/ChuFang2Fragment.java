package com.victtech.cfapp.fragment;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.victtech.cfapp.R;
import com.victtech.cfapp.adapter.ChuFang2Adapter;
import com.victtech.model.entity.ChuFang;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Richard on 2018/1/18.
 */

@SuppressLint("ValidFragment")
public class ChuFang2Fragment extends ChuFangBaseFragment implements SwipeRefreshLayout.OnRefreshListener  {
    private View view;
    private List<ChuFang> mList = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private ChuFang2Adapter adapter;

    private boolean loading = false;

    public ChuFang2Fragment(String type){
        this.setType(type);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.chufang2_layout,container,false);
        return view;
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        adapter = new ChuFang2Adapter(mList);
        //设置加载动画颜色
        swipeRefreshLayout = view.findViewById(R.id.cf2_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,R.color.colorAccent,
                R.color.cardview_shadow_end_color,R.color.colorPrimaryDark);
        swipeRefreshLayout.setOnRefreshListener(this); //设置刷新监听器
        swipeRefreshLayout.post(new Runnable() {//设置页面初始加载时，显示load图标
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                new LoadItemTask().execute();
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());

        recyclerView =  view.findViewById(R.id.cf2_card_list);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int totalItem = manager.getItemCount();
                int lastItem = manager.findLastVisibleItemPosition();
                if(!loading && totalItem < (lastItem + 2)){
                    adapter.showFooterView(recyclerView);
                    loading = true;
                    new LoadItemTask().execute();
                }
//                if(loading == true && totalItem >= (lastItem + 2)){
//                    Log.d("----", "onScrolled: ----");
//                    adapter.hideFooterView();
//                    loading = false;
//                }
            }
        });

    }

    @Override
    public void onRefresh() {
        new LoadItemTask().execute();
    }

    class LoadItemTask extends AsyncTask<Integer,Void,List<ChuFang>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<ChuFang> doInBackground(Integer... integers) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            initData();
            publishProgress();
            return mList;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            swipeRefreshLayout.setRefreshing(false);

            if(loading && !adapter.finished){
                loading = false;
                adapter.hideFooterView();
            }else{
                adapter.notifyDataSetChanged();
            }
        }

        private void initData(){
            if(mList.size() >20){
                adapter.setFinished();
            }else{
                mList.remove(null);
                for(int i=0;i<10;i++){
                    ChuFang cf = new ChuFang();
                    cf.setName("张三  "+(mList.size()+1));
                    mList.add(cf);
                }
            }
        }


    }
}
