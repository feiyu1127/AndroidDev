package com.example.admin.chufang;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.chufang.service.EffectiveRecipe;
import com.example.admin.chufang.service.EffectiveRecipeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/1/18.
 */

public class PageFragment extends Fragment {
    private static final String TAG = "PageFragment";
    public static final String ARGS_PAGE = "args_page";
    private int mPage;

    private List<EffectiveRecipe> effectiveRecipeList = new ArrayList<>();

    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();

        args.putInt(ARGS_PAGE, page);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARGS_PAGE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        switch (mPage){
            case 0: // 有效处方
                view = inflater.inflate(R.layout.effective_recipe,container,false);
                initEffectiveRecipe();
                RecyclerView recyclerView = view.findViewById(R.id.effective_recipe);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(linearLayoutManager);
                EffectiveRecipeAdapter adapter = new EffectiveRecipeAdapter(effectiveRecipeList);
                recyclerView.setAdapter(adapter);
                break;
            case 1: // 无效处方

                break;
            case 2: // 未处理

                break;
            default:
                break;
        }
        return view;
    }


    /**
     * 初始化有效处方
     */
    private void initEffectiveRecipe(){
        for(int i = 0;i < 10;i++){
            EffectiveRecipe effectiveRecipe0 = new EffectiveRecipe(R.drawable.t1,"口腔医院"+i,"0赵医生","2018-01-01","有效");
            effectiveRecipeList.add(effectiveRecipe0);
            EffectiveRecipe effectiveRecipe1 = new EffectiveRecipe(R.drawable.t1,"口腔医院"+i,i+"赵医生","2018-01-02","有效");
            effectiveRecipeList.add(effectiveRecipe1);
            EffectiveRecipe effectiveRecipe2 = new EffectiveRecipe(R.drawable.t1,"口腔医院"+i,i+"赵医生","2018-01-03","有效");
            effectiveRecipeList.add(effectiveRecipe2);
            EffectiveRecipe effectiveRecipe3 = new EffectiveRecipe(R.drawable.t1,"口腔医院"+i,i+"赵医生","2018-01-03","有效");
            effectiveRecipeList.add(effectiveRecipe3);
            EffectiveRecipe effectiveRecipe4 = new EffectiveRecipe(R.drawable.t1,"口腔医院"+i,i+"赵医生","2018-01-04","有效");
            effectiveRecipeList.add(effectiveRecipe4);
            EffectiveRecipe effectiveRecipe5 = new EffectiveRecipe(R.drawable.t1,"口腔医院"+i,i+"赵医生","2018-01-05","有效");
            effectiveRecipeList.add(effectiveRecipe5);

        }

    }


}
