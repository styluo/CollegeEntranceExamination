package edu.shu.styluo.collegeentranceexamination.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.shu.styluo.collegeentranceexamination.R;
import edu.shu.styluo.collegeentranceexamination.data.local.entity.CollegeInfo;
import edu.shu.styluo.collegeentranceexamination.listener.RecyclerViewClickListener;
import edu.shu.styluo.collegeentranceexamination.presenter.CollegeListContract;
import edu.shu.styluo.collegeentranceexamination.presenter.CollegeListPresenter;
import edu.shu.styluo.collegeentranceexamination.utils.StatusBarLightModeUtils;
import edu.shu.styluo.collegeentranceexamination.view.adapter.CollegeListRecyclerAdapter;

/**
 * 高校列表
 * author: styluo
 * date: 2017/5/17 14:38
 * e-mail: shu_jiahuili@foxmail.com
 */

public class CollegeListActivity extends AppCompatActivity implements CollegeListContract.view{
    @BindView(R.id.srl_college_list)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.rv_college_list)
    RecyclerView mRecyclerView;

    CollegeListContract.presenter mPresenter;
    CollegeListRecyclerAdapter mCollegeListRecyclerAdapter;
    LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_list);

        StatusBarLightModeUtils.setStatusBarLightMode(this);
        ButterKnife.bind(this);

        mPresenter = new CollegeListPresenter(this);

        mPresenter.initData();
    }

    @Override
    public void setPresenter(CollegeListContract.presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void initAdapter(List<CollegeInfo> list) {
        mCollegeListRecyclerAdapter = new CollegeListRecyclerAdapter(list);
        mRecyclerView.setAdapter(mCollegeListRecyclerAdapter);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        //默认动画，可继承自定义动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //下拉刷新
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pullDownRefersh();
            }
        });

        //点击事件加入
        mRecyclerView.addOnItemTouchListener(new RecyclerViewClickListener(this, new RecyclerViewClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //TODO goto DetailActivity
                int collegeId = mPresenter.getCollegeId(position);
                Intent intent = new Intent(CollegeListActivity.this, CollegeDetailActivity.class);
                intent.putExtra("CollegeId", collegeId);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                //TODO nothing
            }
        }));
    }

    @Override
    public void refershData(List<CollegeInfo> list) {
        mCollegeListRecyclerAdapter.add(list);

        mCollegeListRecyclerAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    /**
     *  下拉刷新
     */
    private void pullDownRefersh(){
        mCollegeListRecyclerAdapter.removeAll();

        mPresenter.refreshData();
    }
}
