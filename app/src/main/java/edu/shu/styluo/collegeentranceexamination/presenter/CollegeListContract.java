package edu.shu.styluo.collegeentranceexamination.presenter;

import java.util.List;

import edu.shu.styluo.collegeentranceexamination.BasePresenter;
import edu.shu.styluo.collegeentranceexamination.BaseView;
import edu.shu.styluo.collegeentranceexamination.data.local.entity.CollegeInfo;

/**
 * author: styluo
 * date: 2017/5/17 14:46
 * e-mail: shu_jiahuili@foxmail.com
 */

public interface CollegeListContract {

    interface view extends BaseView<presenter>{
        /**
         * 初始化适配器，通过文件数据
         * @param list 文件数据列表
         */
        public void initAdapter(List<CollegeInfo> list);

        /**
         * 刷新数据
         * @param list 文件数据列表
         */
        public void refershData(List<CollegeInfo> list);
    }

    interface presenter extends BasePresenter{
        /**
         * 初始化数据
         */
        public void initData();

        /**
         * 下拉刷新更新数据
         */
        public void refreshData();

        /**
         * 获取院校Id
         * @return 返回院校Id
         */
        public int getCollegeId(int position);
    }
}
