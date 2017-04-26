package edu.shu.styluo.collegeentranceexamination.presenter;

import java.util.List;

import edu.shu.styluo.collegeentranceexamination.BasePresenter;
import edu.shu.styluo.collegeentranceexamination.BaseView;
import edu.shu.styluo.collegeentranceexamination.data.local.entity.MajorInfo;

/**
 * MajorList的V和P的约束类
 * author: styluo
 * date: 2017/4/25 7:17
 * e-mail: shu_jiahuili@foxmail.com
 */

public interface MajorListContract {
    interface view extends BaseView<presenter>{
        /**
         * 初始化列表
         * @param majorInfoList
         */
        public void initAdapter(List<MajorInfo> majorInfoList);

        /**
         * 获取下拉刷新的最新10条数据
         * @param majorInfoList
         */
        public void getRefershData(List<MajorInfo> majorInfoList);

        /**
         * 自动加载数据
         * @param majorInfoList
         */
        public void getMoreData(List<MajorInfo> majorInfoList);
    }

    interface presenter extends BasePresenter{
        /**
         * 从本地数据库加载数据
         */
        public void initData();

        /**
         *下拉刷新
         */
        public void refreshData();

        /**
         * 自动加载更多
         * @param totalItemCount 当前已显示个数
         */
        public void loadMore(int totalItemCount);
    }
}
