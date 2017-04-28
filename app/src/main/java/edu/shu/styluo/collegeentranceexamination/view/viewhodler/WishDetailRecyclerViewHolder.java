package edu.shu.styluo.collegeentranceexamination.view.viewhodler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;
import edu.shu.styluo.collegeentranceexamination.R;

/**
 * author: styluo
 * date: 2017/4/28 10:25
 * e-mail: shu_jiahuili@foxmail.com
 */

public class WishDetailRecyclerViewHolder extends RecyclerView.ViewHolder {
    private TextView mTextViewCollegeName; //大学名称

    public WishDetailRecyclerViewHolder(View itemView){
        super(itemView);

        mTextViewCollegeName = ButterKnife.findById(itemView, R.id.tv_wishdeitail_college_name);
    }

    public TextView getTextViewCollegeName(){
        return mTextViewCollegeName;
    }
}
