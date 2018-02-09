package com.nwei.shen.activities.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.nwei.R;
import com.nwei.shen.fragments.AboutUsFragment;
import com.nwei.shen.fragments.ContactUsFragment;
import com.nwei.shen.fragments.HomeFragment;
import com.nwei.shen.fragments.ProductIntroduceFragment;

public class MainActivity extends AppCompatActivity {

    TabLayout mTablayout;
    ViewPager mViewPager;

    private TabLayout.Tab home;
    private TabLayout.Tab productIntroduce;
    private TabLayout.Tab aboutUs;
    private TabLayout.Tab contactUs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initEvents();

    }


    private void initViews(){
        mTablayout = findViewById(R.id.tabLayout);
        mViewPager = findViewById(R.id.viewPager);

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            private String[] mTitles = new String[]{
                    "首页","产品介绍","关于我们","联系我们"
            };

            @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 1: // 产品介绍
                        return new ProductIntroduceFragment();
                    case 2: // 关于我们
                        return new AboutUsFragment();
                    case 3: // 联系我们
                        return new ContactUsFragment();
                }

                // 默认显示首页
                return new HomeFragment();
            }

            @Override
            public int getCount() {
                return mTitles.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles[position];
            }
        });


        mTablayout.setupWithViewPager(mViewPager);

        home = mTablayout.getTabAt(0);
        productIntroduce = mTablayout.getTabAt(1);
        aboutUs = mTablayout.getTabAt(2);
        contactUs = mTablayout.getTabAt(3);

        //加图标
        home.setIcon(getResources().getDrawable(R.drawable.ic_home_black_24dp));
        productIntroduce.setIcon(getResources().getDrawable(R.drawable.ic_donut_small_black_24dp));
        aboutUs.setIcon(getResources().getDrawable(R.drawable.ic_contact_mail_black_24dp));
        contactUs.setIcon(getResources().getDrawable(R.drawable.ic_message_black_24dp));

    }

    private void initEvents(){
        mTablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab == mTablayout.getTabAt(0)) {
                    home.setIcon(getResources().getDrawable(R.drawable.ic_home_black_24dp));
                    mViewPager.setCurrentItem(0);
                } else if (tab == mTablayout.getTabAt(1)) {
                    productIntroduce.setIcon(getResources().getDrawable(R.drawable.ic_donut_small_black_24dp));
                    mViewPager.setCurrentItem(1);
                } else if (tab == mTablayout.getTabAt(2)) {
                    aboutUs.setIcon(getResources().getDrawable(R.drawable.ic_contact_mail_black_24dp));
                    mViewPager.setCurrentItem(2);
                }else if (tab == mTablayout.getTabAt(3)){
                    contactUs.setIcon(getResources().getDrawable(R.drawable.ic_message_black_24dp));
                    mViewPager.setCurrentItem(3);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
//                if (tab == mTablayout.getTabAt(0)) {
//                    home.setIcon(getResources().getDrawable(R.drawable.ic_home_black_24dp));
//                } else if (tab == mTablayout.getTabAt(1)) {
//                    productIntroduce.setIcon(getResources().getDrawable(R.drawable.ic_donut_small_black_24dp));
//                } else if (tab == mTablayout.getTabAt(2)) {
//                    aboutUs.setIcon(getResources().getDrawable(R.drawable.ic_contact_mail_black_24dp));
//                }else if (tab == mTablayout.getTabAt(3)){
//                    contactUs.setIcon(getResources().getDrawable(R.drawable.ic_message_black_24dp));
//                }

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}
