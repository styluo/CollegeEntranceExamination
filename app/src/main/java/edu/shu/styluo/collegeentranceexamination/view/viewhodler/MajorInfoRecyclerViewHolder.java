package edu.shu.styluo.collegeentranceexamination.view.viewhodler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;
import edu.shu.styluo.collegeentranceexamination.R;

/**
 * 专业列表的ViewHolder
 * author: styluo
 * date: 2017/4/26 19:46
 * e-mail: shu_jiahuili@foxmail.com
 */

public class MajorInfoRecyclerViewHolder extends RecyclerView.ViewHolder {

    private TextView mTextViewMajorName; //专业
    private TextView mTextViewSalary; //薪水

    public MajorInfoRecyclerViewHolder(View itemView){
        super(itemView);

        mTextViewMajorName = ButterKnife.findById(itemView, R.id.tv_major_name);
        mTextViewSalary = ButterKnife.findById(itemView, R.id.tv_major_salary);
    }

    public TextView getTextViewMajorName(){
        return mTextViewMajorName;
    }

    public TextView getTextViewSalary(){
        return mTextViewSalary;
    }
}
