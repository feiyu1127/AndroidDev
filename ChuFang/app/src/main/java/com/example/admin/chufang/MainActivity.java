package com.example.admin.chufang;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.admin.chufang.fragments.ConvertFragment;
import com.example.admin.chufang.fragments.ShopFragment;


public class MainActivity extends BaseActivity implements View.OnClickListener{

    private static final String TAG = "MainActivity";
    private DrawerLayout mDrawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        initNavigation();
        initToolbar();
        initPages();

    }

    /**
     * 初始化 Toolbar
     */
    private void initToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true); //让导航按钮显示
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu); //设置按钮图标
        }
    }


    /**
     * 初始化导航
     */
    private void initNavigation(){
        final NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.recipe_convert); //默认选中
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.recipe_convert: //处方转化
                        loadFragment(new ConvertFragment());
                        break;
                    case R.id.shop_manage: //药店管理
                        loadFragment(new ShopFragment());
                        break;
                    default:
                        break;
                }
                mDrawerLayout.closeDrawers();
                return  true;
            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            default:
                break;
        }
    }


    private void showToastInThread(final String msgToast){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this,msgToast,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }


    /**
     * title 按钮功能
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.access_baidu:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.baidu.com"));
                startActivity(intent);
                break;
            case R.id.exit_sys:
                Toast.makeText(this,"sdddd",Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                break;
        }
        return true;
    }


    private void initPages(){
        loadFragment(new ConvertFragment());
    }

    /**
     * 加载布局
     * @param fragment
     */
    private void loadFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_content,fragment);
        transaction.commit();
    }

}
