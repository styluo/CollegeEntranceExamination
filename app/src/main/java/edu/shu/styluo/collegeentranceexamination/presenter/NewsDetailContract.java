package edu.shu.styluo.collegeentranceexamination.presenter;

import edu.shu.styluo.collegeentranceexamination.BasePresenter;
import edu.shu.styluo.collegeentranceexamination.BaseView;
import edu.shu.styluo.collegeentranceexamination.data.remote.entity.NewsDetail;

/**
 * NewsContract的约束类
 * author: styluo
 * date: 2017/4/25 21:09
 * e-mail: shu_jiahuili@foxmail.com
 */

public interface NewsDetailContract {
    interface view extends BaseView<presenter> {

        /**
         * 咨询详细信息
         * @param newsDetail 咨询详情entity实体类
         */
        public void initView(NewsDetail.RowsBean newsDetail);
    }

    interface presenter extends BasePresenter {
        /**
         * 根据newsId获取详细咨询信息
         * @param newsId 咨询Id
         */
        public void getData(int newsId);
    }
}
