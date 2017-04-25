package edu.shu.styluo.collegeentranceexamination.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.shu.styluo.collegeentranceexamination.R;
import edu.shu.styluo.collegeentranceexamination.data.remote.entity.NewsDetail;
import edu.shu.styluo.collegeentranceexamination.presenter.NewsDetailContract;
import edu.shu.styluo.collegeentranceexamination.presenter.NewsDetailPresenter;
import edu.shu.styluo.collegeentranceexamination.utils.StatusBarLightModeUtils;

/**
 * ButterKnife和StatusBar的样式可以提取到BaseView时间关系，代码重构再考虑吧
 * 连ProgressBar都还没封装，需要封装
 * Content真的够长，一个TextView加上TextApperaanceSmall都放不下，而且还没fitsSystemWindows都放不下
 * 部分内容为空，需要特殊处理，具体处理等需求再看
 *
 *
 * Ps:传回来的content中空格是中文空格，没有换行，需要自己手动处理
 * author: styluo
 * date: 2017/4/25 19:50
 * e-mail: shu_jiahuili@foxmail.com
 */

public class NewsDetailActivity extends AppCompatActivity implements NewsDetailContract.view{
    @BindView(R.id.tv_news_detail)
    TextView mTextViewNewsDetail;
    @BindView(R.id.tv_news_title)
    TextView mTextViewNewsTitle;
    @BindView(R.id.tb_news_detail)
    Toolbar mToolBar;

    private int newsId;
    private NewsDetailContract.presenter mNewsDetailPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        newsId = getIntent().getIntExtra("newsId", -1);

        ButterKnife.bind(this);
        StatusBarLightModeUtils.setStatusBarLightMode(this);

        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mNewsDetailPresenter = new NewsDetailPresenter(this);

        mNewsDetailPresenter.getData(newsId);
    }

    @Override
    public void setPresenter(NewsDetailContract.presenter presenter) {
        mNewsDetailPresenter = presenter;
    }

    @Override
    public void initView(NewsDetail.RowsBean newsDetail) {
        mTextViewNewsTitle.setText(newsDetail.getNewsTitle());
        mTextViewNewsDetail.setText(newsDetail.getNewsContent().replaceAll("　　", "\n        "));
    }
}
