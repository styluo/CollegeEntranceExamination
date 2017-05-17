package edu.shu.styluo.collegeentranceexamination.view.viewhodler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;
import edu.shu.styluo.collegeentranceexamination.R;

/**
 * 高校列表
 * author: styluo
 * date: 2017/5/17 15:09
 * e-mail: shu_jiahuili@foxmail.com
 */

public class CollegeListRecyclerViewHolder extends RecyclerView.ViewHolder {

    private TextView mTextViewCollegeName; //高校名称

    public CollegeListRecyclerViewHolder(View itemView){
        super(itemView);

        mTextViewCollegeName = ButterKnife.findById(itemView, R.id.tv_college_name);
    }

    public TextView getmTextViewCollegeName(){
        return mTextViewCollegeName;
    }
}
