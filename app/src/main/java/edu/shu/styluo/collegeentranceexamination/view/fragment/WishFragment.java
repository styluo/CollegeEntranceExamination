package edu.shu.styluo.collegeentranceexamination.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.shu.styluo.collegeentranceexamination.R;
import edu.shu.styluo.collegeentranceexamination.view.activity.WishDetailListActivity;

/**
 * 志愿页的View视图
 *@Author Created by 29043 on 2017/4/18.
 *@Time 2017.4.18
 */
public class WishFragment extends Fragment implements View.OnClickListener{

    @BindView(R.id.btn_wish_xyh)
    Button mButtonXyh;
    @BindView(R.id.btn_wish_wsl)
    Button mButtonWsl;
    @BindView(R.id.btn_wish_jd)
    Button mButtonJd;
    @BindView(R.id.btn_wish_qs)
    Button mButtonQs;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wish, container, false);
        ButterKnife.bind(this, view);

        init();

        return view;
    }

    /**
     * 根据点击传递不同信息给Activity，跳转后根据info信息请求相应地址获取信息
     * @param v
     */
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.btn_wish_xyh:
                intent = new Intent(getActivity(), WishDetailListActivity.class);
                intent.putExtra("info", "xyh");
                startActivity(intent);
                break;
            case R.id.btn_wish_wsl:
                intent = new Intent(getActivity(), WishDetailListActivity.class);
                intent.putExtra("info", "wsl");
                startActivity(intent);
                break;
            case R.id.btn_wish_jd:
                intent = new Intent(getActivity(), WishDetailListActivity.class);
                intent.putExtra("info", "jd");
                startActivity(intent);
                break;
            case R.id.btn_wish_qs:
                intent = new Intent(getActivity(), WishDetailListActivity.class);
                intent.putExtra("info", "qs");
                startActivity(intent);
                break;
        }
    }

    private void init(){
        mButtonWsl.setOnClickListener(this);
        mButtonXyh.setOnClickListener(this);
        mButtonJd.setOnClickListener(this);
        mButtonQs.setOnClickListener(this);
    }
}
