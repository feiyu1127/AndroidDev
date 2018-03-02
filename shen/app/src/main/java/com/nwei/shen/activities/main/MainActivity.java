package com.nwei.shen.activities.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hjm.bottomtabbar.BottomTabBar;
import com.nwei.R;
import com.nwei.http.ParseJson;
import com.nwei.model.entity.User;
import com.nwei.shen.activities.BaseActivity;
import com.nwei.shen.activities.MyApplication;
import com.nwei.shen.fragments.AboutUsFragment;
import com.nwei.shen.fragments.ContactUsFragment;
import com.nwei.shen.fragments.HomeFragment;
import com.nwei.shen.fragments.ProductIntroduceFragment;

import org.json.JSONException;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    Context mContent;
    private DrawerLayout mDrawerLayout;
    private BottomTabBar mBottomTabBar;
    private CircleImageView avatarImg;
    TextView userNameTxt;
    TextView userMailTxt;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContent = MyApplication.getContext();
        Toolbar toolbar = findViewById(R.id.mtoolbar);
        toolbar.setTitle("参宝财富");
        setSupportActionBar(toolbar);

        mNavigationView = findViewById(R.id.nav_view);
        mDrawerLayout = findViewById(R.id.drawer_layout);


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //Toolbar 最左侧的按钮就叫做 HomeAsUp 按钮,这个按钮的 id 永远都是 android.R.id.home
            actionBar.setDisplayHomeAsUpEnabled(true); //让导航按钮显示
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu); //设置一个导航按钮图标
        }

        mNavigationView.setCheckedItem(R.id.nav_call);//设置默认选中
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_call:

                        break;
                    case R.id.profile:
                        Intent intent = new Intent(mContent,ProfileActivity.class);
                        mContent.startActivity(intent);
                        break;
                    default:
                }
                return false;
            }
        });

        updateProfile(); //从缓存中更新用户数据
        initBottomTabBar(); //初始化底部导航栏

    }


    /**
     * 初始化底部导航栏
     */
    private void initBottomTabBar() {
        mBottomTabBar = findViewById(R.id.bottom_tab_bar);
        mBottomTabBar.init(getSupportFragmentManager())
                .setImgSize(60, 60)
                .setFontSize(12)
                .setTabPadding(5, 2, 8)
                .setChangeColor(getResources().getColor(R.color.red), getResources().getColor(R.color.lightblue))
                .addTabItem("首页", getResources().getDrawable(R.drawable.ic_home_black_24dp), HomeFragment.class)
                .addTabItem("产品介绍", getResources().getDrawable(R.drawable.ic_donut_small_black_24dp), ProductIntroduceFragment.class)
                .addTabItem("关于我们", getResources().getDrawable(R.drawable.ic_contact_mail_black_24dp), AboutUsFragment.class)
                .addTabItem("联系我们", getResources().getDrawable(R.drawable.ic_message_black_24dp), ContactUsFragment.class)
                .setDividerColor(getResources().getColor(R.color.darkgoldenrod));
    }


    /**
     * 从缓存中更新用户数据
     */
    private void updateProfile() {
        //从缓存中读取登录的用户信息
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
        if (prefs.getString("userInfo", null) != null) {
            String userInfoString = prefs.getString("userInfo", null);
            try {
                User user = ParseJson.parseJson(userInfoString, User.class);

                RelativeLayout relativeLayout = (RelativeLayout) mNavigationView.getHeaderView(0); //header 布局通常是0号元素

                avatarImg = relativeLayout.findViewById(R.id.avatar_img);
                userNameTxt = relativeLayout.findViewById(R.id.username);
                userMailTxt = relativeLayout.findViewById(R.id.mail);

                userMailTxt.setText(user.getPhone());
                userNameTxt.setText(user.getName());
                Glide.with(getApplicationContext())
                        .load(user.getAvatar())
                        .into(avatarImg);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.logout_item:
                Toast.makeText(MyApplication.getContext(), "退出登录", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }
}
