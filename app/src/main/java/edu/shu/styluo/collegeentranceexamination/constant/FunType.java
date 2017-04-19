package edu.shu.styluo.collegeentranceexamination.constant;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import edu.shu.styluo.collegeentranceexamination.R;

/**
 * HomeFragment需要的功能枚举类
 * Created by 29043 on 2017/4/19.
 */

public enum FunType {
    COLLEGES(R.string.college_info_description, R.string.college_info_name, R.drawable.bg_main_grid_first),
    MAJORS(R.string.major_info_description, R.string.major_info_name, R.drawable.bg_main_grid_second),
    SIMULATIONS(R.string.simulation_info_description, R.string.simulation_info_name, R.drawable.bg_main_grid_three),
    RECOMMENDS(R.string.recommend_info_description, R.string.recommend_info_name, R.drawable.bg_main_grid_four),
    OTHERS(R.string.other_info_description, R.string.other_info_name, R.drawable.bg_main_grid_five);

    private final int mFunNameId;
    private final int mFunDesId;
    private final int mFunImageId;

    private FunType(@StringRes int funNameId, @StringRes int funDesId, @DrawableRes int funImageId){
        mFunNameId = funNameId;
        mFunDesId = funDesId;
        mFunImageId = funImageId;
    }

    @StringRes
    public int getFunNameId(){
        return mFunNameId;
    }

    @StringRes
    public int getFunDesId(){
        return mFunDesId;
    }

    @DrawableRes
    public int getFunImageId(){
        return mFunImageId;
    }
}
