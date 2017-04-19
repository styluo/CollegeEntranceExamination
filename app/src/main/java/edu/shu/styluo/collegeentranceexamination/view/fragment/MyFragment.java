package edu.shu.styluo.collegeentranceexamination.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.shu.styluo.collegeentranceexamination.R;

/**
 * 我的页的view视图
 *@author  Created by 29043 on 2017/4/18.
 *@Time 2017.4.18
 */

public class MyFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my, container, false);
    }
}
