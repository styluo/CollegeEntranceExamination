package edu.shu.styluo.collegeentranceexamination.presenter;

import android.support.v4.widget.SwipeRefreshLayout;

import java.util.List;

import edu.shu.styluo.collegeentranceexamination.BasePresenter;
import edu.shu.styluo.collegeentranceexamination.BaseView;
import edu.shu.styluo.collegeentranceexamination.data.remote.entity.WishRankCollege;

/**
 * 大学排名列表约束类
 * author: styluo
 * date: 2017/4/28 10:17
 * e-mail: shu_jiahuili@foxmail.com
 */

public interface WishDetailListContract {
    interface view extends BaseView<presenter>{
        /**
         * 获取根布局
         * @return
         */
        public SwipeRefreshLayout getRootView();

        public void initAdapter(List<WishRankCollege.RowsBean> wishRankCollegeList);

        /**
         * 判断是否已经在刷新
         * @return
         */
        public boolean isRefershing();

        /**
         * 获取下拉刷新数据
         * @param wishRankCollegeList
         */
        public void getRefershData(List<WishRankCollege.RowsBean> wishRankCollegeList);
    }

    interface presenter extends BasePresenter{
        /**
         * 根据传入info值加载对应data
         * @param info
         */
        public void initData(String info);

        /**
         * 下拉刷新数据
         */
        public void refershData(String info);
    }
}
