package edu.shu.styluo.collegeentranceexamination.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.shu.styluo.collegeentranceexamination.R;
import edu.shu.styluo.collegeentranceexamination.customview.BasePopupWindow;
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
    @BindView(R.id.sv_majordetail)
    ScrollView mScrollView; //根布局

    private final String NO_DATA = "暂无信息";

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

        //本地数据库中Id需处理后才能使用到网络请求
        mMajorInfoDetailPresenter.initData(majorId);

        //弹出显示开设大学信息
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BasePopupWindow popupWindow = new BasePopupWindow(MajorInfoDetailActivity.this);
                popupWindow.setContentView(LayoutInflater.from(MajorInfoDetailActivity.this).inflate(R.layout.popupwindow_majorinfo_college, null));
                popupWindow.showAtLocation(mScrollView, Gravity.CENTER, 0, 0);
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
        String mainCourse = majorInfo.getMain_course();
        mTextViewMainCourse.setText( (mainCourse==null) ? NO_DATA : mainCourse);
        String majorGoal = majorInfo.getMajor_goal();
        mTextViewMainGoal.setText( (majorGoal==null) ? NO_DATA : majorGoal);
        String majorAbility = majorInfo.getMajor_ability();
        mTextViewMainAbility.setText( (majorAbility==null) ? NO_DATA : majorAbility);
        isLocal = true;
    }
}
