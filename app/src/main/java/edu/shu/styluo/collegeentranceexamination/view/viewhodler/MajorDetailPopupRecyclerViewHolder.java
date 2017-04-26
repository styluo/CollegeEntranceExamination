package edu.shu.styluo.collegeentranceexamination.view.viewhodler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;
import edu.shu.styluo.collegeentranceexamination.R;

/**
 * author: styluo
 * date: 2017/4/27 1:53
 * e-mail: shu_jiahuili@foxmail.com
 */

public class MajorDetailPopupRecyclerViewHolder extends RecyclerView.ViewHolder {
    private TextView mTextViewCollegeName; //高校名称

    public MajorDetailPopupRecyclerViewHolder(View itemView){
        super(itemView);

        mTextViewCollegeName = ButterKnife.findById(itemView, R.id.tv_majordetail_college);
    }

    public TextView getTextViewCollegeName(){
        return mTextViewCollegeName;
    }
}
