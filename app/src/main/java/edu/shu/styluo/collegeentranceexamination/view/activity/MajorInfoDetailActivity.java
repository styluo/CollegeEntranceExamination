package edu.shu.styluo.collegeentranceexamination.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.shu.styluo.collegeentranceexamination.R;
import edu.shu.styluo.collegeentranceexamination.data.local.entity.MajorInfo;
import edu.shu.styluo.collegeentranceexamination.presenter.MajorInfoDetailContract;
import edu.shu.styluo.collegeentranceexamination.presenter.MajorInfoDetailPresenter;
import edu.shu.styluo.collegeentranceexamination.utils.StatusBarLightModeUtils;

/**
 * 专业详情界面
 * author: styluo
 * date: 2017/4/26 23:13
 * e-mail: shu_jiahuili@foxmail.com
 */

public class MajorInfoDetailActivity extends AppCompatActivity implements MajorInfoDetailContract.view{

    private String majorId;
    private MajorInfoDetailContract.presenter mMajorInfoDetailPresenter;

    @BindView(R.id.tv_majordetail_maincourse)
    TextView mTextViewMainCourse; //主要课程
    @BindView(R.id.tv_majordetail_maingoal)
    TextView mTextViewMainGoal; //专业培养目标
    @BindView(R.id.tv_majordetail_mainability)
    TextView mTextViewMainAbility; //专业培养能力
    @BindView(R.id.btn_majordetail)
    Button mButton;

    //数据是否已经获取完成
    private boolean isLocal = false;
    private boolean isRemote = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_majorinfo_detail);

        ButterKnife.bind(this);
        StatusBarLightModeUtils.setStatusBarLightMode(this);

        majorId = getIntent().getStringExtra("majorId");

        mMajorInfoDetailPresenter = new MajorInfoDetailPresenter(this);

        mMajorInfoDetailPresenter.initData(majorId);

        //弹出显示开设大学信息
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void setPresenter(MajorInfoDetailContract.presenter presenter) {
        this.mMajorInfoDetailPresenter = presenter;
    }

    /**
     * 初始化本地数据
     * @param majorInfoList
     */
    @Override
    public void getLocalData(List<MajorInfo> majorInfoList) {
        if(majorInfoList.size() == 0){
            return;
        }
        MajorInfo majorInfo = majorInfoList.get(0);
        mTextViewMainCourse.setText(majorInfo.getMain_course());
        mTextViewMainGoal.setText(majorInfo.getMajor_goal());
        mTextViewMainAbility.setText(majorInfo.getMajor_ability());
        isLocal = true;
    }
}
