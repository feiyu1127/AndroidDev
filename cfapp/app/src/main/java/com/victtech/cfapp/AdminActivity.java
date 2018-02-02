package com.victtech.cfapp;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.victtech.cfapp.fragment.ChuFang1Fragment;
import com.victtech.cfapp.fragment.ConvertFragment;
import com.victtech.cfapp.fragment.ShopFragment;
import com.victtech.component.CustomViewPager;
import com.victtech.tools.ActivityContent;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdminActivity extends BaseActivity {
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        initNavigation();
        initToolBar();
        initPages();

        drawerLayout = findViewById(R.id.main_drawer);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 初始化工具栏
     */
    private void initToolBar(){
        Toolbar toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitle("处方处理系统");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorText) );
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_reorder_white_24dp));
        setSupportActionBar(toolbar);
    }

    /**
     * 初始化侧边栏，动态加载头像。
     */
    private void initNavigation(){
        final NavigationView navigationView = findViewById(R.id.nav_view);
//        RelativeLayout headerLayout =  (RelativeLayout)navigationView.inflateHeaderView(R.layout.nav_header);
        RelativeLayout headerLayout = (RelativeLayout)navigationView.getHeaderView(0);
        //动态设置头像
        CircleImageView userAvatar = headerLayout.findViewById(R.id.user_avatar);
        userAvatar.setImageDrawable(getResources().getDrawable(R.drawable.nav_avatar,null));

        ColorStateList cl = CFApplication.getContext().getColorStateList(R.color.navigation_menu_item_color);
        navigationView.setItemTextColor(cl);
        navigationView.setItemBackgroundResource(R.drawable.navigation_menu_item_bg);

        navigationView.setCheckedItem(R.id.convert);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                navigationView.setCheckedItem(item.getItemId());
                switch (item.getItemId()){
                    case R.id.convert:
                        loadFragment(new ConvertFragment());
                        break;
                    case R.id.shop:
                        loadFragment(new ShopFragment());
                        break;
                    case R.id.user:
                        loadFragment(new ChuFang1Fragment("zzz"));
                        break;
                    case R.id.exit:
                        CFApplication.ClearTmpUser();
                        ActivityContent.finishAll();
                        break;
                    case R.id.upload_cf:
                        Intent intent = new Intent(CFApplication.getContext(),UploadActivity.class);
                        startActivity(intent);
                        break;
                }
                drawerLayout.closeDrawers();
                return false;
            }
        });

    }

    private void initPages(){
       loadFragment(new ConvertFragment());
    }

    private void loadFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.main_content,fragment);
        transaction.commit();
    }
}
