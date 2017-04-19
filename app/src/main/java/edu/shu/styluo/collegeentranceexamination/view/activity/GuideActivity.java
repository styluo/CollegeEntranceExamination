package edu.shu.styluo.collegeentranceexamination.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import edu.shu.styluo.collegeentranceexamination.R;
import edu.shu.styluo.collegeentranceexamination.view.adapter.GuideViewPagerAdapter;

/**
 *引导页，第一次启动
 * author: styluo
 * date: 2017/4/17 22:58
 * e-mail: shu_jiahuili@foxmail.com
 */

public class GuideActivity extends AppCompatActivity{
    //控件初始化
    @BindView(R.id.vp_guide)
    ViewPager mViewPagerGuide;
    @BindViews({R.id.dot_guide_first, R.id.dot_guide_second, R.id.dot_guide_three})
    List<ImageView> mImageViewDotList;

    //成员变量定义
    private GuideViewPagerAdapter mGuideViewPagerAdapter;
    private List<View> mViewList;
    private static final int[] mGuideViewArray = {R.layout.guide_view_first,
            R.layout.guide_view_second, R.layout.guide_view_three};
    private int mCurrentIndex;

    //View中控件
    private Button mButtonEntry;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        ButterKnife.bind(this);

        initViews();
    }

    /**
     * 初始化
     * @return
     */
    private void initViews(){
        mViewList = new ArrayList<View>();

        for(int position : mGuideViewArray){
            View view = LayoutInflater.from(this).inflate(position, null);

            if(position == mGuideViewArray[mGuideViewArray.length - 1]){
                mButtonEntry = ButterKnife.findById(view, R.id.btn_guide_entry);
                mButtonEntry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(GuideActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }

            mViewList.add(view);
        }

        mGuideViewPagerAdapter = new GuideViewPagerAdapter(mViewList);
        mViewPagerGuide.setAdapter(mGuideViewPagerAdapter);
        mViewPagerGuide.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setCurDot(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        initDots();
    }

    /**
     * 初始化点
     * @return
     */
    private void initDots(){
        for(int i = 0; i < mImageViewDotList.size(); i++){
            mImageViewDotList.get(i).setEnabled(false);
            mImageViewDotList.get(i).setTag(i);
            mImageViewDotList.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = (Integer) v.getTag();
                    setCurView(position);
                    setCurDot(position);
                }
            });
        }

        mCurrentIndex = 0;
        mImageViewDotList.get(mCurrentIndex).setEnabled(true);
    }

    /**
     * 设置当前指示点
     * @param position 当前位置
     */
    private void setCurDot(int position){
        if(position < 0 || position > mGuideViewArray.length || mCurrentIndex == position){
            return;
        }
        mImageViewDotList.get(position).setEnabled(true);
        mImageViewDotList.get(mCurrentIndex).setEnabled(false);
        mCurrentIndex = position;
    }

    /**
     * 设置当前View
     * @param position 当前位置
     */
    private void setCurView(int position){
        if(position < 0 || position >= mGuideViewArray.length){
            return;
        }
        mViewPagerGuide.setCurrentItem(position);
    }
}
