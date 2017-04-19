package edu.shu.styluo.collegeentranceexamination.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.shu.styluo.collegeentranceexamination.R;
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Snackbar.make(view, getResources().getString(mHomeGridViewAdapter.getItem(position).getFunDesId()) + "点击事件响应，有待开发", Snackbar.LENGTH_SHORT).show();
    }
}
