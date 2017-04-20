package edu.shu.styluo.collegeentranceexamination.view.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.shu.styluo.collegeentranceexamination.R;
import edu.shu.styluo.collegeentranceexamination.utils.StatusBarLightModeUtils;

/**
 * 大学详细信息Activity
 * @author styluo
 * @Time 2017.4.20
 */
public class CollegeDetailActivity extends AppCompatActivity {
    @BindView(R.id.tb_college_bar)
    Toolbar mToolBar;
    @BindView(R.id.ctl_college_layout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.iv_college_bg)
    ImageView mImageViewCollegeBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_detail);

        ButterKnife.bind(this);

        StatusBarLightModeUtils.setStatusBarLightMode(this);

        //初始化设置
        setSupportActionBar(mToolBar);
        mCollapsingToolbarLayout.setTitle("上海大学(待开发)");
        Picasso.with(this)
                .load(R.drawable.bg_college)
                .into(mImageViewCollegeBackground);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
