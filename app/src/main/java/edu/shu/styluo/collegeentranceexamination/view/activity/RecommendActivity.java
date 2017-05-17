package edu.shu.styluo.collegeentranceexamination.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import edu.shu.styluo.collegeentranceexamination.utils.StatusBarLightModeUtils;

/**
 * 专业推荐界面实现
 * author: styluo
 * date: 2017/5/17 16:52
 * e-mail: shu_jiahuili@foxmail.com
 */

public class RecommendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        StatusBarLightModeUtils.setStatusBarLightMode(this);
        ButterKnife.bind(this);

    }
}
