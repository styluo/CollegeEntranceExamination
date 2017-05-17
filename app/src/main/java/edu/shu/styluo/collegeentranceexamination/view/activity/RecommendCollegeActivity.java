package edu.shu.styluo.collegeentranceexamination.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.shu.styluo.collegeentranceexamination.R;
import edu.shu.styluo.collegeentranceexamination.data.ParcelableEntity.QueryInfo;
import edu.shu.styluo.collegeentranceexamination.presenter.RecommendCollegeContract;
import edu.shu.styluo.collegeentranceexamination.presenter.RecommendCollegePresenter;
import edu.shu.styluo.collegeentranceexamination.utils.StatusBarLightModeUtils;
import edu.shu.styluo.collegeentranceexamination.view.adapter.RecommendCollegeRecyclerAdapter;

/**
 * 专业推荐详情
 * author: styluo
 * date: 2017/5/17 18:22
 * e-mail: shu_jiahuili@foxmail.com
 */

public class RecommendCollegeActivity extends AppCompatActivity implements RecommendCollegeContract.view{

    RecommendCollegeContract.presenter mPresenter;

    @BindView(R.id.tv_recommend_college_entry)
    TextView mTextViewEntry;
    @BindView(R.id.rv_recommend_college_subject)
    RecyclerView mRecyclerView;

    private final String SUCCESS_INFO = "恭喜,达到上海大学录取分数线";
    private final String FAIL_INFO = "抱歉,未达到上海大学录取分数线";
    private final String DEFECT_INFO = "系统未收录相关数据";
    private final int SUCCESS = 1;
    private final int FAIL = 0;
    private final int DEFECT = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_college);

        QueryInfo queryInfo = (QueryInfo) getIntent().getParcelableExtra("queryInfo");

        StatusBarLightModeUtils.setStatusBarLightMode(this);
        ButterKnife.bind(this);

        mPresenter = new RecommendCollegePresenter(this);
        mPresenter.initData(queryInfo);
    }

    @Override
    public void setPresenter(RecommendCollegeContract.presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void initShow(int isShow, String[] data) {
        switch(isShow){
            case SUCCESS:
                mTextViewEntry.setText(SUCCESS_INFO);
                initAdapter(data);
                break;
            case FAIL:
                mTextViewEntry.setText(FAIL_INFO);
                break;
            default:
                mTextViewEntry.setText(DEFECT_INFO);
        }
    }

    private void initAdapter(String[] data){
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager();
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setAlignItems(AlignItems.STRETCH);
        mRecyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter adapter = new RecommendCollegeRecyclerAdapter(data);
        mRecyclerView.setAdapter(adapter);
    }
}
