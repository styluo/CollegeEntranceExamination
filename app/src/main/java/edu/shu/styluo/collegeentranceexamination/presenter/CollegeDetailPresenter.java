package edu.shu.styluo.collegeentranceexamination.presenter;

/**
 * author: styluo
 * date: 2017/5/17 16:32
 * e-mail: shu_jiahuili@foxmail.com
 */

public class CollegeDetailPresenter implements CollegeDetailContract.presenter {
    CollegeDetailContract.view mView;

    public CollegeDetailPresenter(CollegeDetailContract.view view){
        mView = view;
    }

    @Override
    public void start() {
    }

    @Override
    public void initData(int collegeId) {

    }
}
