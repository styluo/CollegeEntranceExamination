package edu.shu.styluo.collegeentranceexamination.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.shu.styluo.collegeentranceexamination.R;
import edu.shu.styluo.collegeentranceexamination.view.viewhodler.RecommendCollegeRecyclerViewHolder;

/**
 * author: styluo
 * date: 2017/5/17 21:39
 * e-mail: shu_jiahuili@foxmail.com
 */

public class RecommendCollegeRecyclerAdapter extends RecyclerView.Adapter {
    String[] mData;

    public RecommendCollegeRecyclerAdapter(String[] data) {
        mData = data;
    }

    @Override
    public RecommendCollegeRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recommend_subject, parent, false);
        return new RecommendCollegeRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RecommendCollegeRecyclerViewHolder recommendCollegeRecyclerViewHolder = (RecommendCollegeRecyclerViewHolder) holder;

        recommendCollegeRecyclerViewHolder.bindTo(mData[position]);
    }

    @Override
    public int getItemCount() {
        return mData.length;
    }
}
