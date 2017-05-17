package edu.shu.styluo.collegeentranceexamination.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import edu.shu.styluo.collegeentranceexamination.R;
import edu.shu.styluo.collegeentranceexamination.data.local.entity.CollegeInfo;
import edu.shu.styluo.collegeentranceexamination.view.viewhodler.CollegeListRecyclerViewHolder;

/**
 * 高校列表
 * author: styluo
 * date: 2017/5/17 15:06
 * e-mail: shu_jiahuili@foxmail.com
 */

public class CollegeListRecyclerAdapter extends RecyclerView.Adapter {

    private List<CollegeInfo> mCollegeInfoList;

    public CollegeListRecyclerAdapter(List<CollegeInfo> collegeInfoList){
        this.mCollegeInfoList = collegeInfoList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_college_list, parent, false);
        return new CollegeListRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CollegeListRecyclerViewHolder collegeListRecyclerViewHolder = (CollegeListRecyclerViewHolder) holder;
        CollegeInfo collegeInfo = (CollegeInfo) mCollegeInfoList.get(position);

        collegeListRecyclerViewHolder.getmTextViewCollegeName().setText(collegeInfo.getCollegeName());
    }

    @Override
    public int getItemCount() {
        return mCollegeInfoList.size();
    }

    public void remove(int position) {
        if (position > 0 && position < mCollegeInfoList.size()) {
            mCollegeInfoList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void removeAll() {
        int oldSize = mCollegeInfoList.size();
        mCollegeInfoList.clear();
        notifyItemRangeRemoved(0, oldSize);
    }

    public void add(List<CollegeInfo> newList) {
        if (newList != null && !newList.isEmpty()) {
            int oldSize = mCollegeInfoList.size();
            mCollegeInfoList.addAll(newList);
            notifyItemRangeChanged(oldSize - 1, newList.size());
        }
    }

    public void add(CollegeInfo entity, int position) {
        if (position > 0 && position <= mCollegeInfoList.size()) {
            mCollegeInfoList.add(entity);
            notifyItemInserted(position);
        }
    }

    public CollegeInfo getPositionItem(int position){
        return mCollegeInfoList.get(position);
    }
}
