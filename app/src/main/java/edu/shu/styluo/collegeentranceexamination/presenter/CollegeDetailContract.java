package edu.shu.styluo.collegeentranceexamination.presenter;

import edu.shu.styluo.collegeentranceexamination.BasePresenter;
import edu.shu.styluo.collegeentranceexamination.BaseView;

/**
 * author: styluo
 * date: 2017/5/17 16:32
 * e-mail: shu_jiahuili@foxmail.com
 */

public interface CollegeDetailContract {
    public interface view extends BaseView<presenter>{

    }

    public interface presenter extends BasePresenter{
        /**
         * 获取大学信息
         * @param collegeId 大学Id
         */
        public void initData(int collegeId);
    }
}
