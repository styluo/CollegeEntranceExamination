package edu.shu.styluo.collegeentranceexamination.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.shu.styluo.collegeentranceexamination.R;
import edu.shu.styluo.collegeentranceexamination.data.ParcelableEntity.QueryInfo;
import edu.shu.styluo.collegeentranceexamination.utils.StatusBarLightModeUtils;

/**
 * 专业推荐界面实现
 * author: styluo
 * date: 2017/5/17 16:52
 * e-mail: shu_jiahuili@foxmail.com
 */

public class RecommendActivity extends AppCompatActivity {

    @BindView(R.id.ll_recommend)
    LinearLayout mLinearLayout;
    @BindView(R.id.et_recommend_score)
    EditText mRecommendScore;
    @BindView(R.id.spinner_recommend_province)
    Spinner mSpinnerProvince;
    @BindView(R.id.spinner_recommend_subject)
    Spinner mSpinnerSubject;
    @BindView(R.id.btn_query)
    Button mButtonQuery;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recommend);

        StatusBarLightModeUtils.setStatusBarLightMode(this);
        ButterKnife.bind(this);

        mButtonQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query();
            }
        });
    }

    private void query(){
        String score = mRecommendScore.getText().toString().trim();

        //分数不能为空
        if(score == null || score.equals("")){
            Snackbar.make(mLinearLayout, "分数不能为空", Snackbar.LENGTH_LONG).show();
            return;
        }

        String province = mSpinnerProvince.getSelectedItem().toString().trim();
        String subject = mSpinnerSubject.getSelectedItem().toString().trim();

        QueryInfo queryInfo = new QueryInfo(score, province, subject);

        Intent intent = new Intent(this, RecommendCollegeActivity.class);
        intent.putExtra("queryInfo", queryInfo);
        startActivity(intent);
    }
}
