package edu.shu.styluo.collegeentranceexamination.presenter;

import edu.shu.styluo.collegeentranceexamination.data.remote.RetrofitFactory;
import edu.shu.styluo.collegeentranceexamination.data.remote.entity.HotNews;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 圈子的P层
 * author: styluo
 * date: 2017/4/25 7:27
 * e-mail: shu_jiahuili@foxmail.com
 */

public class WorldPresenter implements WorldContract.presenter{
    WorldContract.view mWorldView;

    /**
     * 构造函数传入View引用，注意这里有耦合（Dagger2解决）
     * @param worldView
     */
    public WorldPresenter(WorldContract.view worldView){
        this.mWorldView = worldView;
    }

    @Override
    public void start() {

    }

    /**
     * 通过网络请求加载数据
     * @return
     */
    @Override
    public void initData(){
        RetrofitFactory.getInstance().getHotNews("1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HotNews>() {
                    @Override
                    public void accept(HotNews hotNewses) throws Exception {
                        mWorldView.initAdapter(hotNewses.getRows());
                    }
                });
    }

    /**
     * 下拉刷新数据
     */
    @Override
    public void refershData() {
        RetrofitFactory.getInstance().getHotNews("1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HotNews>() {
                    @Override
                    public void accept(HotNews hotNewses) throws Exception {
                        if(mWorldView.isRefershing()){
                            mWorldView.getRefershData(hotNewses.getRows());
                        }
                    }
                });
    }
}
