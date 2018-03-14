package com.nwei.shen.activities.main;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;

import com.nwei.R;
import com.nwei.shen.activities.BaseActivity;

public class MainActivity extends BaseActivity {

//    TabLayout mTablayout;
//    ViewPager mViewPager;

    private DrawerLayout mDrawerLayout;

//    private TabLayout.Tab home;
//    private TabLayout.Tab productIntroduce;
//    private TabLayout.Tab aboutUs;
//    private TabLayout.Tab contactUs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.mtoolbar);
        toolbar.setTitle("我是标题");
        setSupportActionBar(toolbar);

//        NavigationView navigationView = findViewById(R.id.nav_view);
//        mDrawerLayout = findViewById(R.id.drawer_layout);

//        ActionBar actionBar = getSupportActionBar();
//        if(actionBar != null){
//            //Toolbar 最左侧的按钮就叫做 HomeAsUp 按钮,这个按钮的 id 永远都是 android.R.id.home
//            actionBar.setDisplayHomeAsUpEnabled(true); //让导航按钮显示
//            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu); //设置一个导航按钮图标
//        }

//        initViews();
//        initEvents();

    }


//    public boolean onCreateOptionsMenu(Menu menu){
//        getMenuInflater().inflate(R.menu.toolbar,menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case android.R.id.home:
//                mDrawerLayout.openDrawer(GravityCompat.START);
//            case R.id.delete:
//               customUIToast("我点了Delete");
//               break;
//            default:
//        }
//        return true;
//    }


//    private void initViews(){
//
////        mTablayout = findViewById(R.id.tabLayout);
//        mViewPager = findViewById(R.id.viewPager);
//
//        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
//
//            private String[] mTitles = new String[]{
//                    "首页","产品介绍","关于我们","联系我们"
//            };
//
//            @Override
//            public Fragment getItem(int position) {
//                switch (position){
//                    case 1: // 产品介绍
//                        return new ProductIntroduceFragment();
//                    case 2: // 关于我们
//                        return new AboutUsFragment();
//                    case 3: // 联系我们
//                        return new ContactUsFragment();
//                }
//
//                // 默认显示首页
//                return new HomeFragment();
//            }
//
//            @Override
//            public int getCount() {
//                return mTitles.length;
//            }
//
//            @Override
//            public CharSequence getPageTitle(int position) {
//                return mTitles[position];
//            }
//        });
//
//
//        mTablayout.setupWithViewPager(mViewPager);
//
//        home = mTablayout.getTabAt(0);
//        productIntroduce = mTablayout.getTabAt(1);
//        aboutUs = mTablayout.getTabAt(2);
//        contactUs = mTablayout.getTabAt(3);
//
//        //加图标
//        home.setIcon(getResources().getDrawable(R.drawable.ic_home_black_24dp));
//        productIntroduce.setIcon(getResources().getDrawable(R.drawable.ic_donut_small_black_24dp));
//        aboutUs.setIcon(getResources().getDrawable(R.drawable.ic_contact_mail_black_24dp));
//        contactUs.setIcon(getResources().getDrawable(R.drawable.ic_message_black_24dp));
//
//    }
//
//    private void initEvents(){
//        mTablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                if (tab == mTablayout.getTabAt(0)) {
//                    home.setIcon(getResources().getDrawable(R.drawable.ic_home_black_24dp));
//                    mViewPager.setCurrentItem(0);
//                } else if (tab == mTablayout.getTabAt(1)) {
//                    productIntroduce.setIcon(getResources().getDrawable(R.drawable.ic_donut_small_black_24dp));
//                    mViewPager.setCurrentItem(1);
//                } else if (tab == mTablayout.getTabAt(2)) {
//                    aboutUs.setIcon(getResources().getDrawable(R.drawable.ic_contact_mail_black_24dp));
//                    mViewPager.setCurrentItem(2);
//                }else if (tab == mTablayout.getTabAt(3)){
//                    contactUs.setIcon(getResources().getDrawable(R.drawable.ic_message_black_24dp));
//                    mViewPager.setCurrentItem(3);
//                }
//
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
////                if (tab == mTablayout.getTabAt(0)) {
////                    home.setIcon(getResources().getDrawable(R.drawable.ic_home_black_24dp));
////                } else if (tab == mTablayout.getTabAt(1)) {
////                    productIntroduce.setIcon(getResources().getDrawable(R.drawable.ic_donut_small_black_24dp));
////                } else if (tab == mTablayout.getTabAt(2)) {
////                    aboutUs.setIcon(getResources().getDrawable(R.drawable.ic_contact_mail_black_24dp));
////                }else if (tab == mTablayout.getTabAt(3)){
////                    contactUs.setIcon(getResources().getDrawable(R.drawable.ic_message_black_24dp));
////                }
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//    }

}




