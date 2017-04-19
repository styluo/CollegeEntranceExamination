package edu.shu.styluo.collegeentranceexamination.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Fragment与ViewPager的适配器
 *@author Created by 29043 on 2017/4/18
 * @Time 2017.4.18.
 */

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;

    /**
     * 构造函数
     * @param fm 传入FragmentManager
     */
    public MainFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragments = fragmentList;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }
}
