package edu.shu.styluo.collegeentranceexamination.presenter;

import android.widget.ScrollView;

import java.util.List;

import edu.shu.styluo.collegeentranceexamination.BasePresenter;
import edu.shu.styluo.collegeentranceexamination.BaseView;
import edu.shu.styluo.collegeentranceexamination.data.local.entity.MajorInfo;
import edu.shu.styluo.collegeentranceexamination.data.remote.entity.CollegeByMajor;

/**
 * 专业详情的约束类
 * author: styluo
 * date: 2017/4/26 23:24
 * e-mail: shu_jiahuili@foxmail.com
 */

public interface MajorInfoDetailContract {
    interface view extends BaseView<MajorInfoDetailContract.presenter> {
        /**
         * 初始化本地数据
         * @param majorInfoList
         */
        public void getLocalData(List<MajorInfo> majorInfoList);

        /**
         * 获取根视图
         * @return
         */
        public ScrollView getRootView();

        /**
         * 初始化popup
         * @param mCollegeList
         */
        public void getPopupData(List<CollegeByMajor.RowsBean> collegeList);

        /**
         * 加载更多数据
         * @param collegeList
         */
        public void getLoadMore(List<CollegeByMajor.RowsBean> collegeList);

        /**
         * 设置加载状态
         * @param isLoading
         */
        public void setLoadingState(boolean isLoading);
    }

    interface presenter extends BasePresenter {
        /**
         * 加载数据
         */
        public void initData(String majorId);

        /**
         * 初始化该课程开设大学列表
         * @param majorId
         */
        public void initPopup(String majorId);

        /**
         * 自动加载更多
         */
        public void loadMore(String majorId, int page);
    }
}
