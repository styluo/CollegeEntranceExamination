package edu.shu.styluo.collegeentranceexamination.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import edu.shu.styluo.collegeentranceexamination.R;
import edu.shu.styluo.collegeentranceexamination.constant.FunType;
import edu.shu.styluo.collegeentranceexamination.customview.CaptionedSquareLayout;
import edu.shu.styluo.collegeentranceexamination.view.activity.CollegeListActivity;
import edu.shu.styluo.collegeentranceexamination.view.activity.MajorListActivity;
import edu.shu.styluo.collegeentranceexamination.view.activity.MajorSimulationActivity;
import edu.shu.styluo.collegeentranceexamination.view.activity.RecommendActivity;
import edu.shu.styluo.collegeentranceexamination.view.adapter.HomeGridViewAdapter;

/**
 * Home页的View视图
 * @author Created by 29043 on 2017/4/18.
 * @Time 2017.4.18
 */

public class HomeFragment extends Fragment implements AdapterView.OnItemClickListener{
    //初始化控件
    @BindView(R.id.gv_home_view)
    GridView mGridViewHomeFun;

    //定义成员变量
    private HomeGridViewAdapter mHomeGridViewAdapter;

    @BindString(R.string.tv_transition_name)
    String mTextViewTransition;
    @BindString(R.string.iv_transition_name)
    String mImageViewTransition;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        mHomeGridViewAdapter = new HomeGridViewAdapter(getActivity());
        mGridViewHomeFun.setAdapter(mHomeGridViewAdapter);
        mGridViewHomeFun.setOnItemClickListener(this);

        return view;
    }

    /**
     * 如果每个GridViewItem都一样，则复用Activity，Bundle传参，否则直接此处判断跳转
     * 主要的四个功能决定还是分四个模块，不复用，此处判断跳转
     * position compare with enum variable
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(mHomeGridViewAdapter.getItem(position) == FunType.COLLEGES){ //直接跳转
            Intent intent = new Intent(getActivity(), CollegeListActivity.class);
            startActivity(intent);
        }else if(mHomeGridViewAdapter.getItem(position) == FunType.SIMULATIONS) { //动画转场
            final CaptionedSquareLayout captionedSquareLayout = (CaptionedSquareLayout) view;
            ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    getActivity(),
                    new Pair<View, String>(captionedSquareLayout.getImageView(), mImageViewTransition),
                    new Pair<View, String>(captionedSquareLayout.getTextView(), mTextViewTransition)
            );

            MajorSimulationActivity.startActivity(getActivity(), mHomeGridViewAdapter.getItem(position), activityOptionsCompat.toBundle());

        }else if(mHomeGridViewAdapter.getItem(position) == FunType.MAJORS) {
            Intent intent = new Intent(getActivity(), MajorListActivity.class);
            startActivity(intent);
        }else if(mHomeGridViewAdapter.getItem(position) == FunType.RECOMMENDS){
            Intent intent = new Intent(getActivity(), RecommendActivity.class);
            startActivity(intent);
        } else{
            Snackbar.make(view, getResources().getString(mHomeGridViewAdapter.getItem(position).getFunDesId()) + "点击事件响应，有待开发", Snackbar.LENGTH_SHORT).show();
        }
    }
}
