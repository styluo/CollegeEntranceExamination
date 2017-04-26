package edu.shu.styluo.collegeentranceexamination.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
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
import edu.shu.styluo.collegeentranceexamination.data.local.entity.MajorInfo;
import edu.shu.styluo.collegeentranceexamination.listener.RecyclerViewClickListener;
import edu.shu.styluo.collegeentranceexamination.presenter.MajorListContract;
import edu.shu.styluo.collegeentranceexamination.presenter.MajorListPresenter;
import edu.shu.styluo.collegeentranceexamination.utils.StatusBarLightModeUtils;
import edu.shu.styluo.collegeentranceexamination.view.adapter.MajorInfoRecyclerAdapter;

/**
 * 专业信息列表的activity,ButterKnife绑定和设置statusbar的样式可以提取到BaseActivity中
 * 时间关系先不做提取，等到功能实现后做代码重构
 * 给一个Excel要自己导入sqlite，然后如果做二级联动
 * 这个数据库也不是树形数据库，还是简单点来吧O(∩_∩)O
 * author: styluo
 * date: 2017/4/24 14:25
 * e-mail: shu_jiahuili@foxmail.com
 */

public class MajorListActivity extends AppCompatActivity implements MajorListContract.view{
    @BindView(R.id.srl_major_info)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.rv_major_info)
    RecyclerView mRecyclerView;

    private MajorListContract.presenter mMajorListPresenter;

    private List<MajorInfo> mMajorInfoList;
    private MajorInfoRecyclerAdapter mMajorInfoRecyclerAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    private boolean isLoadMore = true;

    private final String PROMOT_INFO = "没有更多数据";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_major_list);

        ButterKnife.bind(this);
        StatusBarLightModeUtils.setStatusBarLightMode(this);

        mMajorListPresenter = new MajorListPresenter(this);

        mMajorListPresenter.initData();
    }

    @Override
    public void setPresenter(MajorListContract.presenter presenter) {
        this.mMajorListPresenter = presenter;
    }

    @Override
    public void initAdapter(List<MajorInfo> majorInfoList) {
        mMajorInfoList = majorInfoList;
        mMajorInfoRecyclerAdapter = new MajorInfoRecyclerAdapter(mMajorInfoList);
        mRecyclerView.setAdapter(mMajorInfoRecyclerAdapter);
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
            }

            @Override
            public void onItemLongClick(View view, int position) {
                //TODO nothing
            }
        }));

        //上滑自动加载
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItem = ((LinearLayoutManager) mLinearLayoutManager).findLastVisibleItemPosition();
                int totalItemCount = mLinearLayoutManager.getItemCount();
                //lastVisibleItem >= totalItemCount - 4 表示剩下4个item自动加载
                if (lastVisibleItem >= totalItemCount - 4 && dy > 0) {
                    if(isLoadMore){
                        mMajorListPresenter.loadMore(totalItemCount);
                    }
                }
            }
        });
    }

    /**
     * 获取下拉刷新数据
     * @param majorInfoList
     */
    @Override
    public void getRefershData(List<MajorInfo> majorInfoList) {
        for (MajorInfo majorInfo : majorInfoList) {
            mMajorInfoList.add(majorInfo);
        }
        mMajorInfoRecyclerAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    /**
     * 自动加载更多数据
     * @param majorInfoList
     */
    @Override
    public void getMoreData(List<MajorInfo> majorInfoList) {
        if(majorInfoList.size() == 0){
            Snackbar.make(mSwipeRefreshLayout, PROMOT_INFO, Snackbar.LENGTH_SHORT).show();
            isLoadMore = false;
            return;
        }

        for (MajorInfo majorInfo : majorInfoList) {
            mMajorInfoList.add(majorInfo);
        }
        mMajorInfoRecyclerAdapter.notifyDataSetChanged();
    }

    /**
     *  下拉刷新
     */
    private void pullDownRefersh(){
        mMajorInfoList.clear();
        mMajorListPresenter.refreshData();
    }
}
