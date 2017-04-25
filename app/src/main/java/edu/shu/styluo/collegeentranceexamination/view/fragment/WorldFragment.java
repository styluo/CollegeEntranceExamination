package edu.shu.styluo.collegeentranceexamination.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
 * @author  Created by 29043 on 2017/4/18.
 *@Time 2017.4.18
 */

public class WorldFragment extends Fragment implements WorldContract.view{
    @BindView(R.id.srl_world)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.rv_world)
    RecyclerView mRecyclerView;

    private WorldContract.presenter mWorldPresenter;
    private WorldRecyclerAdapter mWorldRecyclerAdapter;
    private List<HotNews.RowsBean> mHotNewsList;
    private Handler handler = new Handler();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_world, container, false);
        ButterKnife.bind(this, view);

        mWorldPresenter = new WorldPresenter(this);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pullDownRefersh();
            }
        });

        mWorldPresenter.initData();

        return view;
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

    /**
     * 下拉刷新
     */
    private void pullDownRefersh(){
        mHotNewsList.clear();
        mWorldPresenter.refershData();
    }

}
