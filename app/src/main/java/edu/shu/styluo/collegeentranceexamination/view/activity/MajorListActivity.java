package edu.shu.styluo.collegeentranceexamination.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import edu.shu.styluo.collegeentranceexamination.R;
import edu.shu.styluo.collegeentranceexamination.presenter.MajorListContract;
import edu.shu.styluo.collegeentranceexamination.presenter.MajorListPresenter;
import edu.shu.styluo.collegeentranceexamination.utils.StatusBarLightModeUtils;

/**
 * 专业信息列表的activity,ButterKnife绑定和设置statusbar的样式可以提取到BaseActivity中
 * 时间关系先不做提取，等到功能实现后做代码重构
 * author: styluo
 * date: 2017/4/24 14:25
 * e-mail: shu_jiahuili@foxmail.com
 */

public class MajorListActivity extends AppCompatActivity implements MajorListContract.view{
    private MajorListContract.presenter mMajorListPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_major_list);

        ButterKnife.bind(this);
        StatusBarLightModeUtils.setStatusBarLightMode(this);

        mMajorListPresenter = new MajorListPresenter(this);
    }

    @Override
    public void setPresenter(MajorListContract.presenter presenter) {
        this.mMajorListPresenter = presenter;
    }
}
