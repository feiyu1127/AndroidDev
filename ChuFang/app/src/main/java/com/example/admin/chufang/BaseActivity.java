package com.example.admin.chufang;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.admin.chufang.activities.DrugstoreManageActivity;

/**
 * Created by admin on 2018/1/18.
 */

public class BaseActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityCollector.addActivity(this);

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        mDrawerLayout = findViewById(R.id.drawer_layout);
//        NavigationView navView = findViewById(R.id.nav_view);

//        ActionBar actionBar = getSupportActionBar();
//        if(actionBar != null){
//            actionBar.setDisplayHomeAsUpEnabled(true); //让导航按钮显示
//            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu); //设置按钮图标
//        }
//        navView.setCheckedItem(R.id.recipe_transform); //默认选中

    }


//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case android.R.id.home:
//                mDrawerLayout.openDrawer(GravityCompat.START);
//                break;
//            default:
//                break;
//        }
//        return true;
//    }

}
