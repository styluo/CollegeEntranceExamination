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
 * RecyclerView的Adapter可以抽象一个BaseAdapter用来继承，add和remove方法都是公共的，通过Entity来代替可能出现的实体类型
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

    public void remove(int position) {
        if (position > 0 && position < mHotNewsList.size()) {
            mHotNewsList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void removeAll() {
        int oldSize = mHotNewsList.size();
        mHotNewsList.clear();
        notifyItemRangeRemoved(0, oldSize);
    }

    public void add(List<HotNews.RowsBean> newList) {
        if (newList != null && !newList.isEmpty()) {
            int oldSize = mHotNewsList.size();
            mHotNewsList.addAll(newList);
            notifyItemRangeChanged(oldSize - 1, newList.size());
        }
    }

    public void add(HotNews.RowsBean entity, int position) {
        if (position > 0 && position <= mHotNewsList.size()) {
            mHotNewsList.add(entity);
            notifyItemInserted(position);
        }
    }

    public HotNews.RowsBean getPositionItem(int position){
        return mHotNewsList.get(position);
    }

    public void notifyItemRangeChanged() {
        notifyItemRangeChanged(0, mHotNewsList.size());
    }
}
