package edu.shu.styluo.collegeentranceexamination.presenter;

import android.support.design.widget.Snackbar;

import edu.shu.styluo.collegeentranceexamination.data.remote.RetrofitFactory;
import edu.shu.styluo.collegeentranceexamination.data.remote.entity.NewsDetail;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * NewsDetail业务类
 * author: styluo
 * date: 2017/4/25 21:10
 * e-mail: shu_jiahuili@foxmail.com
 */

public class NewsDetailPresenter implements NewsDetailContract.presenter{
    NewsDetailContract.view mNewsDetailView;
    private final String NETWORK_ERROR = "NetWorkError, Please check the network environment";

    public NewsDetailPresenter(NewsDetailContract.view view){
        mNewsDetailView = view;
    }

    @Override
    public void getData(int newsId) {
        RetrofitFactory.getInstance().getNewsDetailById(newsId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NewsDetail>() {
                    @Override
                    public void accept(NewsDetail newsDetail) throws Exception {
                        mNewsDetailView.initView(newsDetail.getRows().get(0));
                        mNewsDetailView.hideLoadingProgressDialog();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Snackbar.make(mNewsDetailView.getRootView(), NETWORK_ERROR, Snackbar.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void start() {

    }
}
