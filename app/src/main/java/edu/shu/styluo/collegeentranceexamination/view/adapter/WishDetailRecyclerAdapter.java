package edu.shu.styluo.collegeentranceexamination.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import edu.shu.styluo.collegeentranceexamination.R;
import edu.shu.styluo.collegeentranceexamination.data.remote.entity.WishRankCollege;
import edu.shu.styluo.collegeentranceexamination.view.viewhodler.WishDetailRecyclerViewHolder;

/**
 * RecyclerView的Adapter可以抽象一个BaseAdapter用来继承，add和remove方法都是公共的，通过Entity来代替可能出现的实体类型
 * https://issuetracker.google.com/issues/37030377，修复下拉刷新可能导致的IndexOutOfBoundsException
 * author: styluo
 * date: 2017/4/28 10:25
 * e-mail: shu_jiahuili@foxmail.com
 */

public class WishDetailRecyclerAdapter extends RecyclerView.Adapter {
    private List<WishRankCollege.RowsBean> mWishCollegeList;

    public WishDetailRecyclerAdapter(List<WishRankCollege.RowsBean> wishCollegeList){
        this.mWishCollegeList = wishCollegeList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_wishdetail_list, parent, false);
        return new WishDetailRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        WishDetailRecyclerViewHolder wishDetailRecyclerViewHolder = (WishDetailRecyclerViewHolder) holder;
        WishRankCollege.RowsBean rowsBean = (WishRankCollege.RowsBean) mWishCollegeList.get(position);

        wishDetailRecyclerViewHolder.getTextViewCollegeName().setText(rowsBean.getUnivName());
    }

    @Override
    public int getItemCount() {
        return mWishCollegeList.size();
    }

    public void remove(int position) {
        if (position > 0 && position < mWishCollegeList.size()) {
            mWishCollegeList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void removeAll() {
        int oldSize = mWishCollegeList.size();
        mWishCollegeList.clear();
        notifyItemRangeRemoved(0, oldSize);
    }

    public void add(List<WishRankCollege.RowsBean> newList) {
        if (newList != null && !newList.isEmpty()) {
            int oldSize = mWishCollegeList.size();
            mWishCollegeList.addAll(newList);
            notifyItemRangeChanged(oldSize - 1, newList.size());
        }
    }

    public void add(WishRankCollege.RowsBean entity, int position) {
        if (position > 0 && position <= mWishCollegeList.size()) {
            mWishCollegeList.add(entity);
            notifyItemInserted(position);
        }
    }

    public WishRankCollege.RowsBean getPositionItem(int position){
        return mWishCollegeList.get(position);
    }

    public void notifyItemRangeChanged() {
        notifyItemRangeChanged(0, mWishCollegeList.size());
    }
}
