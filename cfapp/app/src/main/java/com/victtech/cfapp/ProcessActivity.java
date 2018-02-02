package com.victtech.cfapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.sunfusheng.glideimageview.GlideImageView;
import com.victtech.cfapp.adapter.MedicineAdapter;
import com.victtech.component.CustomButton;
import com.victtech.component.CustomEditText;
import com.victtech.component.CustomLinerLayoutManager;
import com.victtech.http.HttpCallBackLisioner;
import com.victtech.http.HttpUtil;
import com.victtech.http.ParseJson;
import com.victtech.model.ChuFangSingleModel;
import com.victtech.model.entity.ChuFang;
import com.victtech.model.entity.Medicine;
import com.victtech.tools.LogUtil;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Richard on 2018/1/23.
 */

public class ProcessActivity extends BaseActivity {
    private int id;
    private List<Medicine> mList = new ArrayList<>();
    private CustomEditText hospital;
    private CustomEditText doctor;
    private CustomButton processButton;
    private GlideImageView cfPhoto;
    private GlideImageView cfLoading;
    private MedicineAdapter adapter;
    private RadioGroup radioGroup;
    private int radioValue;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process);

        Intent intent = getIntent();
        id = intent.getIntExtra("id",-1);
        RecyclerView recyclerView = findViewById(R.id.medicine_list);

        adapter = new MedicineAdapter(mList);

        CustomLinerLayoutManager manger = new CustomLinerLayoutManager(this);
        manger.setScrollEnable(true);//禁止滚动
        recyclerView.setLayoutManager(manger);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setFocusable(false);

        hospital = findViewById(R.id.hospital_name);
        doctor = findViewById(R.id.doctor_name);
        cfPhoto = findViewById(R.id.chufang_photo);
        cfPhoto.setVisibility(View.GONE);
        cfLoading = findViewById(R.id.chufang_loading);
        cfLoading.loadLocalImage(R.drawable.loading,R.drawable.loading);
        processButton = findViewById(R.id.process_btn);
        processButton.setOnClickListener(new ProcessListener());

        //单选按钮选中事件。
        radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = findViewById(group.getCheckedRadioButtonId());
                LogUtil.d("radio Button-------",rb.getText().toString());
                switch (rb.getText().toString()){
                    case "有效":
                        radioValue = 1;
                        break;
                    case "无效":
                        radioValue = 0;
                        break;
                    default:
                        radioValue = -1;
                }
            }
        });
        initData();
    }


    private void initData(){
        String token = null;
        try {
            token = CFApplication.getToken();
        } catch (JSONException e) {
            UIToast("token获取失败");
            finish();
            return;
        }
        HttpUtil.getRequest("recipes/" + String.valueOf(id), new HttpCallBackLisioner() {
            @Override
            public void onFinish(String requestString) {
                try {
                    ChuFangSingleModel singleModel = ParseJson.parseJson(requestString, ChuFangSingleModel.class);
                    final ChuFang cf = singleModel.getData();
                    Medicine[] medicines =  cf.getMedicines();
                    for(int i=0;i<medicines.length;i++){
                        mList.add(medicines[i]);
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            cfPhoto.loadImage("https://static.victtech.com/"+cf.getRecipe_img(),R.drawable.loading);
                            cfLoading.setVisibility(View.GONE);
                            cfPhoto.setVisibility(View.VISIBLE);
                            adapter.notifyDataSetChanged();
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Exception e) {

            }
        },token);
//        for(int i=0;i<10;i++){
//            Medicine medicine = new Medicine();
//            medicine.setCode(String.valueOf(i));
//            medicine.setManuName("厂商"+String.valueOf(i));
//            medicine.setName("药品名称"+String.valueOf(i));
//            mList.add(medicine);
//        }
    }

    class ProcessListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            String token;
            try {
                token = CFApplication.getToken();
            } catch (JSONException e) {
                e.printStackTrace();
                UIToast("token获取失败");
                return;
            }
            Map<String,String> form = new HashMap<>();
            form.put("doctor",doctor.getText().toString());
            form.put("hospital",hospital.getText().toString());
            form.put("recipe_id",String.valueOf(id));
            form.put("status",String.valueOf(radioValue));
            HttpUtil.postRequest("recipe/transform", new HttpCallBackLisioner() {
                @Override
                public void onFinish(String requestString) {
                    UIToast("处理成功");
                    Intent intent = new Intent();
                    intent.putExtra("return",id);
                    setResult(RESULT_OK,intent);
                    finish();
                }

                @Override
                public void onError(Exception e) {
                    UIToast("网络访问错误");
                }
            },form,token);

        }
    }
}
