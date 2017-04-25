package edu.shu.styluo.collegeentranceexamination.presenter;

/**
 * 分离专业列表中业务和视图层次，因为部分activity逻辑较为简单，
 * 时间关系未分离，代码重构可分离
 * author: styluo
 * date: 2017/4/24 14:26
 * e-mail: shu_jiahuili@foxmail.com
 */

public class MajorListPresenter implements MajorListContract.presenter{
    MajorListContract.view mMajorListView;

    /**
     * presenter保持对view的引用，耦合度是有的，可以通过Dagger2注入形式解除耦合，时间关系直接一点
     * @param majorListView
     */
    public MajorListPresenter(MajorListContract.view majorListView){
        this.mMajorListView = majorListView;
    }

    @Override
    public void start() {

    }
}
