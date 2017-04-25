package edu.shu.styluo.collegeentranceexamination.view.viewhodler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;
import edu.shu.styluo.collegeentranceexamination.R;

/**
 * 圈子列表的Holder实现
 * author: styluo
 * date: 2017/4/25 13:18
 * e-mail: shu_jiahuili@foxmail.com
 */

public class WorldRecyclerViewHolder extends RecyclerView.ViewHolder{
    private TextView mTextViewTitle;

    public WorldRecyclerViewHolder(View itemView){
        super(itemView);

        mTextViewTitle = ButterKnife.findById(itemView, R.id.tv_world_title);
    }

    public TextView getTextViewTitle(){
        return mTextViewTitle;
    }
}
