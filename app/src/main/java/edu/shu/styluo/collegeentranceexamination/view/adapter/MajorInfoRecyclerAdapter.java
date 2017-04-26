package edu.shu.styluo.collegeentranceexamination.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import edu.shu.styluo.collegeentranceexamination.R;
import edu.shu.styluo.collegeentranceexamination.data.local.entity.MajorInfo;
import edu.shu.styluo.collegeentranceexamination.view.viewhodler.MajorInfoRecyclerViewHolder;

/**
 * 专业信息列表Adapter
 * author: styluo
 * date: 2017/4/26 19:45
 * e-mail: shu_jiahuili@foxmail.com
 */

public class MajorInfoRecyclerAdapter extends RecyclerView.Adapter {

    private List<MajorInfo> mMajorInfoList;

    public MajorInfoRecyclerAdapter(List<MajorInfo> majorInfoList){
        this.mMajorInfoList = majorInfoList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_major_list, parent, false);
        return new MajorInfoRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MajorInfoRecyclerViewHolder majorInfoRecyclerViewHolder = (MajorInfoRecyclerViewHolder) holder;
        MajorInfo majorInfo = (MajorInfo) mMajorInfoList.get(position);

        majorInfoRecyclerViewHolder.getTextViewMajorName().setText(majorInfo.getMajor_name());
        majorInfoRecyclerViewHolder.getTextViewSalary().setText(majorInfo.getMajor_salary());
    }

    @Override
    public int getItemCount() {
        return mMajorInfoList.size();
    }

}
