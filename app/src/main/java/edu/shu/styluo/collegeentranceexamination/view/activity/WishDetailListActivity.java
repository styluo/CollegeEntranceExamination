package edu.shu.styluo.collegeentranceexamination.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.shu.styluo.collegeentranceexamination.R;
import edu.shu.styluo.collegeentranceexamination.customview.LoadingProgressDialog;
import edu.shu.styluo.collegeentranceexamination.data.remote.entity.WishRankCollege;
import edu.shu.styluo.collegeentranceexamination.presenter.WishDetailListContract;
import edu.shu.styluo.collegeentranceexamination.presenter.WishDetailListPresenter;
import edu.shu.styluo.collegeentranceexamination.utils.StatusBarLightModeUtils;
import edu.shu.styluo.collegeentranceexamination.view.adapter.WishDetailRecyclerAdapter;

/**
 * 排名信息列表查看，接口格式并不统一，但是因为本处仅使用大学名称，所以并没有将四个排名分开，而是直接复用
 * 四个排名接口返回的Json对象中字段并不统一，讲道理本来应该统一的，不讲道理我也没办法，因为只使用大学名称所以
 * 我就直接复用了同一个Entity实体，如果需要返回字段中的某些特殊信息需要拆分并创建不同Entity
 * author: styluo
 * date: 2017/4/28 9:23
 * e-mail: shu_jiahuili@foxmail.com
 */

public class WishDetailListActivity extends AppCompatActivity implements WishDetailListContract.view{
    @BindView(R.id.rv_wish_college_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.srl_wish_college_list)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private String info; //定义info确定当前列表显示内容

    private WishDetailListContract.presenter mPresenter;
    private WishDetailRecyclerAdapter mRecyclerAdapter;
    private List<WishRankCollege.RowsBean> mWishCollegeList; //不通过这个list来修改，直接使用adapter
    private LinearLayoutManager mLinearLayoutManager;

    private LoadingProgressDialog mLoadingProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_college_rank);

        ButterKnife.bind(this);
        StatusBarLightModeUtils.setStatusBarLightMode(this);
        info = getIntent().getStringExtra("info");

        if(mLoadingProgressDialog == null){
            mLoadingProgressDialog = new LoadingProgressDialog(this);
        }

        mLoadingProgressDialog.show();

        mPresenter = new WishDetailListPresenter(this);
        mPresenter.initData(info);
    }

    @Override
    public void setPresenter(WishDetailListContract.presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public SwipeRefreshLayout getRootView() {
        return mSwipeRefreshLayout;
    }

    /**
     * 初始化
     * @param wishRankCollegeList
     */
    @Override
    public void initAdapter(List<WishRankCollege.RowsBean> wishRankCollegeList) {
        mWishCollegeList = wishRankCollegeList;
        mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerAdapter = new WishDetailRecyclerAdapter(mWishCollegeList);
        mRecyclerView.setAdapter(mRecyclerAdapter);

        //设置下拉刷新
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pullDownRefersh();
            }
        });
    }

    @Override
    public boolean isRefershing(){
        return  mSwipeRefreshLayout.isRefreshing();
    }

    @Override
    public void getRefershData(List<WishRankCollege.RowsBean> wishRankCollegeList) {
        mRecyclerAdapter.add(wishRankCollegeList);

        mRecyclerAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void hideLoadingProgressDialog() {
        mLoadingProgressDialog.dismiss();
    }

    /**
     * 下拉刷新
     */
    private void pullDownRefersh(){
        mRecyclerAdapter.removeAll();
        mPresenter.refershData(info);
    }
}
