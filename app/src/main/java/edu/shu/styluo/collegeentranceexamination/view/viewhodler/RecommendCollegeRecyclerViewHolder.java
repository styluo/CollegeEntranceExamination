package edu.shu.styluo.collegeentranceexamination.view.viewhodler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayoutManager;

import butterknife.ButterKnife;
import edu.shu.styluo.collegeentranceexamination.R;

/**
 * author: styluo
 * date: 2017/5/17 21:39
 * e-mail: shu_jiahuili@foxmail.com
 */

public class RecommendCollegeRecyclerViewHolder extends RecyclerView.ViewHolder {
    private TextView mTextView;

    public RecommendCollegeRecyclerViewHolder(View itemView) {
        super(itemView);
        mTextView = ButterKnife.findById(itemView, R.id.tv_recommend_college_subject);
    }

    public void bindTo(String info) {
        mTextView.setText(info);
        ViewGroup.LayoutParams lp = mTextView.getLayoutParams();
        if (lp instanceof FlexboxLayoutManager.LayoutParams) {
            FlexboxLayoutManager.LayoutParams flexboxLp = (FlexboxLayoutManager.LayoutParams) lp;
            flexboxLp.setFlexGrow(1.0f);
        }
    }

}
