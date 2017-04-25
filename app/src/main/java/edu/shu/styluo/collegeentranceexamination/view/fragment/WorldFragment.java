package edu.shu.styluo.collegeentranceexamination.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.shu.styluo.collegeentranceexamination.R;
import edu.shu.styluo.collegeentranceexamination.customview.LoadingProgressDialog;
import edu.shu.styluo.collegeentranceexamination.data.remote.entity.HotNews;
import edu.shu.styluo.collegeentranceexamination.listener.RecyclerViewClickListener;
import edu.shu.styluo.collegeentranceexamination.presenter.WorldContract;
import edu.shu.styluo.collegeentranceexamination.presenter.WorldPresenter;
import edu.shu.styluo.collegeentranceexamination.view.activity.NewsDetailActivity;
import edu.shu.styluo.collegeentranceexamination.view.adapter.WorldRecyclerAdapter;

/**
 *圈子页的View视图
 * RecyclerView点击事件，用Github上第三方RecyclerView可以很快实现，这里直接自己实现，
 *addOnChildAttachStateChangeListener这种方案在这里使用可能比较麻烦所以先不用
 * 预加载和懒加载的解决方案比较繁琐，可以优化，时间关系，我就没优化直接写了
 * @author  Created by 29043 on 2017/4/18.
 *@Time 2017.4.18
 */

public class WorldFragment extends Fragment implements WorldContract.view{
    @BindView(R.id.srl_world)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.rv_world)
    RecyclerView mRecyclerView;

    private LoadingProgressDialog mLoadingProgressDialog;
    private boolean mIsLoading;
    private boolean mIsVisible;
    private boolean mIsCreate = false;

    private WorldContract.presenter mWorldPresenter;
    private WorldRecyclerAdapter mWorldRecyclerAdapter;
    private List<HotNews.RowsBean> mHotNewsList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_world, container, false);
        ButterKnife.bind(this, view);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pullDownRefersh();
            }
        });

        if(mLoadingProgressDialog == null){
            mLoadingProgressDialog = new LoadingProgressDialog(getActivity());
        }

        mWorldPresenter = new WorldPresenter(this);
        if(!mIsCreate){
            mIsCreate = true;
            lazyLoad();
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     * 当Fragment可见时开始加载数据，因为ViewPager+FragmentPagerAdapter的缓存策略，如果create
     * 直接加载数据，会导致dialog在相邻Tab就开始显示并开始网路请求数据
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(getUserVisibleHint()){
            mIsVisible = true;
            lazyLoad();
        }else{
            mIsVisible = false;
        }
    }

    @Override
    public void setIsLoading(boolean isLoading) {
        this.mIsLoading = isLoading;
    }

    @Override
    public void setPresenter(WorldContract.presenter presenter) {
        mWorldPresenter = presenter;
    }

    /**
     * 初始化列表adapter
     */
    @Override
    public void initAdapter(List<HotNews.RowsBean> hotNewsList) {
        mHotNewsList = hotNewsList;
        mWorldRecyclerAdapter = new WorldRecyclerAdapter(mHotNewsList);
        mRecyclerView.setAdapter(mWorldRecyclerAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //点击事件加入
        mRecyclerView.addOnItemTouchListener(new RecyclerViewClickListener(getActivity(),new RecyclerViewClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //跳转咨询详情
                Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                intent.putExtra("newsId", mHotNewsList.get(position).getNewsId());
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                //TODO nothing
            }
        }));
    }

    /**
     * 检测是否在下拉刷新状态
     */
    @Override
    public boolean isRefershing(){
        return  mSwipeRefreshLayout.isRefreshing();
    }

    /**
     * 关闭下拉刷新显示
     */
    @Override
    public void closeRefersh(){
        mSwipeRefreshLayout.setRefreshing(false);
    }

    /**
     * 获取新的数据
     * 这里注意不能直接赋值，需要通过add，否则改变了指向的对象，显示为空
     */
    @Override
    public void getRefershData(List<HotNews.RowsBean> hotNewsList){
        for (HotNews.RowsBean hotNew : hotNewsList) {
            mHotNewsList.add(hotNew);
        }
        mWorldRecyclerAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void hideLoadingProgressDialog() {
        mLoadingProgressDialog.dismiss();
    }

    @Override
    public SwipeRefreshLayout getRootView(){
        return mSwipeRefreshLayout;
    }

    /**
     * 下拉刷新
     */
    private void pullDownRefersh(){
        mHotNewsList.clear();
        mWorldPresenter.refershData();
    }

    /**
     * 懒加载数据
     */
    private void lazyLoad(){
        if(!mIsVisible || !mIsCreate){
            return;
        }else{
            mLoadingProgressDialog.show();
            mWorldPresenter.initData();
        }
    }
}
