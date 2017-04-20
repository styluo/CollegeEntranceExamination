package edu.shu.styluo.collegeentranceexamination.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.shu.styluo.collegeentranceexamination.R;
import edu.shu.styluo.collegeentranceexamination.constant.FunType;
import edu.shu.styluo.collegeentranceexamination.customview.CaptionedSquareLayout;
import edu.shu.styluo.collegeentranceexamination.utils.StatusBarLightModeUtils;

/**
 * 专业模拟Activity
 * author: styluo
 * date: 2017/4/20 23:43
 * e-mail: shu_jiahuili@foxmail.com
 */

public class MajorSimulationActivity extends AppCompatActivity {
    private static final String EXTRA_TOOL_TYPE = "edu.shu.styluo.collegeentranceexamination.view.activity.MajorSimulationActivity";

    @BindView(R.id.csv_simulation_header)
    CaptionedSquareLayout mCaptionedSquareLayout;

    private FunType mFunType;

    public static void startActivity(Activity activity, FunType funType, Bundle activityOptions){
        final Intent intent = new Intent(activity, MajorSimulationActivity.class);
        intent.putExtra(EXTRA_TOOL_TYPE, funType);
        ActivityCompat.startActivity(activity, intent, activityOptions);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulation);

        StatusBarLightModeUtils.setStatusBarLightMode(this);

        ButterKnife.bind(this);

        //初始化视图
        mFunType = (FunType) getIntent().getSerializableExtra(EXTRA_TOOL_TYPE);
        mCaptionedSquareLayout.getImageView().setSquare(false);
        mCaptionedSquareLayout.getTextView().setText(mFunType.getFunNameId());
        mCaptionedSquareLayout.setImageResource(mFunType.getFunImageId());
    }
}
