package com.nwei.materialtest;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Context mContext;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            //Toolbar 最左侧的按钮就叫做 HomeAsUp 按钮,这个按钮的 id 永远都是 android.R.id.home
            actionBar.setDisplayHomeAsUpEnabled(true); //让导航按钮显示
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu); //设置一个导航按钮图标
        }

        navigationView.setCheckedItem(R.id.nav_call);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers(); //将滑动菜单关闭
                return true;
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.backup:
                Toast.makeText(mContext,"you click backup",Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(mContext,"you click delete",Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(mContext,"you click settings",Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }
}
