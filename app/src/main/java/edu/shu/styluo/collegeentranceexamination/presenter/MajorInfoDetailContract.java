package edu.shu.styluo.collegeentranceexamination.presenter;

import java.util.List;

import edu.shu.styluo.collegeentranceexamination.BasePresenter;
import edu.shu.styluo.collegeentranceexamination.BaseView;
import edu.shu.styluo.collegeentranceexamination.data.local.entity.MajorInfo;

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
    }

    interface presenter extends BasePresenter {
        /**
         * 加载数据
         */
        public void initData(String majorId);
    }
}
