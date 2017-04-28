package edu.shu.styluo.collegeentranceexamination.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import edu.shu.styluo.collegeentranceexamination.R;
import edu.shu.styluo.collegeentranceexamination.data.remote.entity.CollegeByMajor;
import edu.shu.styluo.collegeentranceexamination.view.viewhodler.MajorDetailPopupRecyclerViewHolder;

/**
 * RecyclerView的Adapter可以抽象一个BaseAdapter用来继承，add和remove方法都是公共的，通过Entity来代替可能出现的实体类型
 * author: styluo
 * date: 2017/4/27 1:55
 * e-mail: shu_jiahuili@foxmail.com
 */

public class MajorDetailPopupRecyclerAdapter extends RecyclerView.Adapter {

    private List<CollegeByMajor.RowsBean> mCollegeList;

    public MajorDetailPopupRecyclerAdapter(List<CollegeByMajor.RowsBean> collegeList){
        this.mCollegeList = collegeList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_majordetail_college, parent, false);
        return new MajorDetailPopupRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MajorDetailPopupRecyclerViewHolder majorDetailPopupRecyclerViewHolder = (MajorDetailPopupRecyclerViewHolder) holder;
        CollegeByMajor.RowsBean rowsBean = (CollegeByMajor.RowsBean) mCollegeList.get(position);

        majorDetailPopupRecyclerViewHolder.getTextViewCollegeName().setText(rowsBean.getUnivName());
    }

    @Override
    public int getItemCount() {
        return mCollegeList.size();
    }

    public void remove(int position) {
        if (position > 0 && position < mCollegeList.size()) {
            mCollegeList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void removeAll() {
        int oldSize = mCollegeList.size();
        mCollegeList.clear();
        notifyItemRangeRemoved(0, oldSize);
    }

    public void add(List<CollegeByMajor.RowsBean> newList) {
        if (newList != null && !newList.isEmpty()) {
            int oldSize = mCollegeList.size();
            mCollegeList.addAll(newList);
            notifyItemRangeChanged(oldSize - 1, newList.size());
        }
    }

    public void add(CollegeByMajor.RowsBean entity, int position) {
        if (position > 0 && position <= mCollegeList.size()) {
            mCollegeList.add(entity);
            notifyItemInserted(position);
        }
    }

    public CollegeByMajor.RowsBean getPositionItem(int position){
        return mCollegeList.get(position);
    }
}
