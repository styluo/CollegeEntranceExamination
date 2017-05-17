package edu.shu.styluo.collegeentranceexamination.presenter;

import edu.shu.styluo.collegeentranceexamination.BasePresenter;
import edu.shu.styluo.collegeentranceexamination.BaseView;
import edu.shu.styluo.collegeentranceexamination.data.ParcelableEntity.QueryInfo;

/**
 * author: styluo
 * date: 2017/5/17 18:31
 * e-mail: shu_jiahuili@foxmail.com
 */

public interface RecommendCollegeContract {

    public interface view extends BaseView<presenter>{
        /**
         * 根据分数线估计显示内容
         * @param isShow 是否推荐高校专业
         */
        public void initShow(int isShow, String[] data);
    }

    public interface presenter extends BasePresenter{
        /**
         * 根据对象初始化数据
         * @param queryInfo 传入查询信息获取数据
         */
        public void initData(QueryInfo queryInfo);
    }
}
