package edu.shu.styluo.collegeentranceexamination.presenter;

import android.support.design.widget.Snackbar;

import edu.shu.styluo.collegeentranceexamination.data.remote.RetrofitFactory;
import edu.shu.styluo.collegeentranceexamination.data.remote.entity.WishRankCollege;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 大学排名信息P层
 * author: styluo
 * date: 2017/4/28 10:18
 * e-mail: shu_jiahuili@foxmail.com
 */

public class WishDetailListPresenter implements WishDetailListContract.presenter{
    WishDetailListContract.view mView;

    private final String XIAO_YOU_HUI = "xyh";
    private final String WU_SHU_LIAN = "wsl";

    private final String NETWORK_ERROR = "NetWorkError, Please check the network environment";

    public WishDetailListPresenter(WishDetailListContract.view view){
        mView = view;
    }

    @Override
    public void start() {
    }

    @Override
    public void initData(String info) {
        if(info.equals(XIAO_YOU_HUI)){
            RetrofitFactory.getInstance().getWishRankCollegeXyh()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<WishRankCollege>() {
                        @Override
                        public void accept(WishRankCollege wishRankCollege) throws Exception {
                            mView.initAdapter(wishRankCollege.getRows());
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            Snackbar.make(mView.getRootView(), NETWORK_ERROR, Snackbar.LENGTH_SHORT).show();
                        }
                    });

        } else if(info.equals(WU_SHU_LIAN)){

        }
    }

    @Override
    public void refershData(String info) {
        if(info.equals(XIAO_YOU_HUI)){
            RetrofitFactory.getInstance().getWishRankCollegeXyh()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<WishRankCollege>() {
                        @Override
                        public void accept(WishRankCollege wishRankCollege) throws Exception {
                            mView.getRefershData(wishRankCollege.getRows());
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            Snackbar.make(mView.getRootView(), NETWORK_ERROR, Snackbar.LENGTH_SHORT).show();
                        }
                    });

        } else if(info.equals(WU_SHU_LIAN)){

        }
    }
}
