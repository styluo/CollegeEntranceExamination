package edu.shu.styluo.collegeentranceexamination.presenter;

import android.support.v4.widget.SwipeRefreshLayout;

import java.util.List;

import edu.shu.styluo.collegeentranceexamination.BasePresenter;
import edu.shu.styluo.collegeentranceexamination.BaseView;
import edu.shu.styluo.collegeentranceexamination.data.remote.entity.HotNews;

/**
 * World圈子的V和P约束类
 * author: styluo
 * date: 2017/4/25 7:27
 * e-mail: shu_jiahuili@foxmail.com
 */

public interface WorldContract {
    interface view extends BaseView<presenter> {
        /**
         * 初始化adapter
         * @param hotNewsList
         */
        public void initAdapter(List<HotNews.RowsBean> hotNewsList);

        /**
         * 关闭下拉刷新
         */
        public void closeRefersh();

        /**
         * 检测下拉刷新状态
         */
        public boolean isRefershing();

        /**
         * 获取新的数据
         * @param hotNewsList
         */
        public void getRefershData(List<HotNews.RowsBean> hotNewsList);

        /**
         * 隐藏LoadingProgressDialog
         */
        public void hideLoadingProgressDialog();

        /**
         * 是否正在加载数据
         * @param isLoading
         */
        public void setIsLoading(boolean isLoading);

        /**
         * 获取根视图
         */
        public SwipeRefreshLayout getRootView();
    }

    interface presenter extends BasePresenter {

        /**
         * 获取列表数据
         */
        public void initData();

        /**
         * 下拉刷新数据
         */
        public void refershData();
    }
}
