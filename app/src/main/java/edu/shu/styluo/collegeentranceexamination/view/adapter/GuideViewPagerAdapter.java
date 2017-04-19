package edu.shu.styluo.collegeentranceexamination.view.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

/**
 * author: styluo
 * date: 2017/4/18 22:39
 * e-mail: shu_jiahuili@foxmail.com
 */

public class GuideViewPagerAdapter extends PagerAdapter {
    private List<View> mViewList;

    public GuideViewPagerAdapter(List<View> viewList){
        super();
        this.mViewList = viewList;
    }

    /**
     * 获取View的总数
     * @return 返回view的总数
     */
    @Override
    public int getCount() {
        if(mViewList != null){
            return mViewList.size();
        }else{
            return 0;
        }
    }

    /**
     * 确认view对象与实例对象是否保持一致
     * @param view viewpager显示的view的内容
     * @param object 在instantiateItem中交给viewPager保存的实例
     * @return 是否一致
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (View) object);
    }

    /**
     *为给定位置创建view，并自行添加到container中
     * @param container viewpager本身
     * @param position 位置
     * @return 提交给viewpager保存的实例
     */
    @Override
    public Object instantiateItem(View container, int position) {
        ((ViewPager) container).addView(mViewList.get(position));
        return mViewList.get(position);
    }

    /**
     * 为给定位置移除相应View,一般缓冲3个item，所以要考虑notify
     * @param container viewpager本身
     * @param position 位置
     * @param object 提交给viewpager保存的实例
     */
    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager) container).removeView(mViewList.get(position));
    }
}
