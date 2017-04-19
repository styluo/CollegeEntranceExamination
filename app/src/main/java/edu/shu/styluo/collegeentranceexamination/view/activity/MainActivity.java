package edu.shu.styluo.collegeentranceexamination.view.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import edu.shu.styluo.collegeentranceexamination.R;
import edu.shu.styluo.collegeentranceexamination.view.adapter.MainFragmentPagerAdapter;
import edu.shu.styluo.collegeentranceexamination.view.fragment.HomeFragment;
import edu.shu.styluo.collegeentranceexamination.view.fragment.MyFragment;
import edu.shu.styluo.collegeentranceexamination.view.fragment.WishFragment;
import edu.shu.styluo.collegeentranceexamination.view.fragment.WorldFragment;

/**
 * 主页面包涵单Activity+多Fragment
 * author: styluo
 * date: 2017/4/18 21:52
 * e-mail: shu_jiahuili@foxmail.com
 */

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    //初始化部分res
    @BindArray(R.array.title_main)
    String[] mTitleMainArray;

    @BindDrawable(R.drawable.tab_main_selector)
    Drawable mDrawableTabMain;
    @BindDrawable(R.drawable.tab_wish_selector)
    Drawable mDrawableTabWish;
    @BindDrawable(R.drawable.tab_world_selector)
    Drawable mDrawableTabWorld;
    @BindDrawable(R.drawable.tab_my_selector)
    Drawable getmDrawableTabMy;

    //加载控件
    @BindView(R.id.tl_main_navigation)
    TabLayout mTabLayoutNavi;
    @BindView(R.id.vp_main_view)
    ViewPager mViewPager;
    @BindView(R.id.tb_main_bar)
    Toolbar mToolBar;
    @BindView(R.id.dl_main)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.nv_main_drawer)
    NavigationView mNavigationView;

    private List<Fragment> mFragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        //ps:必须在setSupportActionBar之前设置
        mToolBar.setTitle("ToolbarDemo");

        setSupportActionBar(mToolBar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_navgation_logo);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        mToolBar.setNavigationIcon(R.drawable.ic_menu_black);

        //ps: 必须在setSupportActionBar之后设置
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO DrawLayout
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

        mNavigationView.setNavigationItemSelectedListener(this);

        initFragment();
        mViewPager.setAdapter(new MainFragmentPagerAdapter(getSupportFragmentManager(), mFragmentList));
        mTabLayoutNavi.setupWithViewPager(mViewPager);

        //因为setupWithViewPager中有移除xml中TabItem定义所以需要重新赋值,可查源码或Google
        initTab();
        mViewPager.setCurrentItem(0);
    }

    /**
     * 初始化Fragment
     * @return
     */
    private void initFragment(){
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new HomeFragment());
        mFragmentList.add(new WishFragment());
        mFragmentList.add(new WorldFragment());
        mFragmentList.add(new MyFragment());
    }

    /**
     *为TabLayout加入Tab
     * @return
     */
    private void initTab(){
        mTabLayoutNavi.getTabAt(0).setText(mTitleMainArray[0]).setIcon(mDrawableTabMain);
        mTabLayoutNavi.getTabAt(1).setText(mTitleMainArray[1]).setIcon(mDrawableTabWish);
        mTabLayoutNavi.getTabAt(2).setText(mTitleMainArray[2]).setIcon(mDrawableTabWorld);
        mTabLayoutNavi.getTabAt(3).setText(mTitleMainArray[3]).setIcon(getmDrawableTabMy);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_test:
                Snackbar.make(mDrawerLayout, "点击事件测试", Snackbar.LENGTH_LONG).show();
                break;
        }

        return true;
    }
}
