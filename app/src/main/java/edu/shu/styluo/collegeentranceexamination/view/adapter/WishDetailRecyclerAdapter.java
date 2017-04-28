package edu.shu.styluo.collegeentranceexamination.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import edu.shu.styluo.collegeentranceexamination.R;
import edu.shu.styluo.collegeentranceexamination.data.remote.entity.WishRankCollege;
import edu.shu.styluo.collegeentranceexamination.view.viewhodler.WishDetailRecyclerViewHolder;

/**
 * author: styluo
 * date: 2017/4/28 10:25
 * e-mail: shu_jiahuili@foxmail.com
 */

public class WishDetailRecyclerAdapter extends RecyclerView.Adapter {
    private List<WishRankCollege.RowsBean> mWishCollegeList;

    public WishDetailRecyclerAdapter(List<WishRankCollege.RowsBean> wishCollegeList){
        this.mWishCollegeList = wishCollegeList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_wishdetail_list, parent, false);
        return new WishDetailRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        WishDetailRecyclerViewHolder wishDetailRecyclerViewHolder = (WishDetailRecyclerViewHolder) holder;
        WishRankCollege.RowsBean rowsBean = (WishRankCollege.RowsBean) mWishCollegeList.get(position);

        wishDetailRecyclerViewHolder.getTextViewCollegeName().setText(rowsBean.getUnivName());
    }

    @Override
    public int getItemCount() {
        return mWishCollegeList.size();
    }
}
