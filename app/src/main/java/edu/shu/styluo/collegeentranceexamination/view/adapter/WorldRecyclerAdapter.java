package edu.shu.styluo.collegeentranceexamination.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import edu.shu.styluo.collegeentranceexamination.R;
import edu.shu.styluo.collegeentranceexamination.data.remote.entity.HotNews;
import edu.shu.styluo.collegeentranceexamination.view.viewhodler.WorldRecyclerViewHolder;

/**
 * 圈子列表的Adapter实现
 * author: styluo
 * date: 2017/4/25 13:09
 * e-mail: shu_jiahuili@foxmail.com
 */

public class WorldRecyclerAdapter extends RecyclerView.Adapter{
    private List<HotNews.RowsBean> mHotNewsList;

    public WorldRecyclerAdapter(List<HotNews.RowsBean> hotNewsList){
        this.mHotNewsList = hotNewsList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_world_list, parent, false);
        return new WorldRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        WorldRecyclerViewHolder worldRecyclerViewHolder = (WorldRecyclerViewHolder) holder;
        HotNews.RowsBean rowsBean = (HotNews.RowsBean) mHotNewsList.get(position);

        worldRecyclerViewHolder.getTextViewTitle().setText(rowsBean.getNewsTitle());
        worldRecyclerViewHolder.getTextViewTime().setText(rowsBean.getNewsDate());
    }

    @Override
    public int getItemCount() {
        return mHotNewsList.size();
    }
}
